package ml.vexlab.smartgrid.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ml.vexlab.smartgrid.entity.Asset;
import ml.vexlab.smartgrid.entity.AssetType;
import ml.vexlab.smartgrid.entity.Customer;
import ml.vexlab.smartgrid.entity.Device;
import ml.vexlab.smartgrid.exception.CustomException;
import ml.vexlab.smartgrid.repository.AssetRepository;
import ml.vexlab.smartgrid.repository.AssetTypeRepository;
import ml.vexlab.smartgrid.repository.CustomerRepository;
import ml.vexlab.smartgrid.repository.DeviceRepository;
import ml.vexlab.smartgrid.service.AssetService;
import ml.vexlab.smartgrid.transport.dto.AssetDTO;
import ml.vexlab.smartgrid.transport.dto.GenericDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service(value = "assetService")
@CacheConfig(cacheNames = {"asset"})
public class AssetServiceImpl implements AssetService {

  @Autowired private AssetRepository assetRepository;
  @Autowired private AssetTypeRepository assetTypeRepository;
  @Autowired private DeviceRepository deviceRepository;
  @Autowired private CustomerRepository customerRepository;
  @Autowired EntityManager entityManager;

  @CachePut
  public GenericDataDTO create(AssetDTO assetDTO) {
    if (assetDTO.getCustomer() == null) {
      throw new CustomException("Customer not found.", HttpStatus.NOT_FOUND);
    }
    Asset a = new Asset();
    if (assetDTO.getId() != null) {
      Optional<Asset> asset = assetRepository.findById(UUID.fromString(assetDTO.getId()));
      if (asset.isPresent()) {
        // edit
        a = asset.get();
      }
    }
    a.setName(assetDTO.getName());
    a.setDescription(assetDTO.getDescription());
    Optional<Customer> c = customerRepository.findById(UUID.fromString(assetDTO.getCustomer()));
    if (!c.isPresent()) {
      throw new CustomException("Customer not found.", HttpStatus.NOT_FOUND);
    }
    a.setCustomer(c.get());
    List<String> assetList = assetDTO.getType();
    if (assetList != null) {
      Set<AssetType> typeSet = new HashSet<>();
      for (String t : assetDTO.getType()) {
        Optional<AssetType> type = assetTypeRepository.findById(UUID.fromString(t));
        if (type.isPresent()) {
          typeSet.add(type.get());
        }
      }
      a.setType(typeSet);
    }

    List<String> deviceList = assetDTO.getDevices();
    if (deviceList != null) {
      Set<Device> deviceSet = new HashSet<>();
      for (String d : assetDTO.getDevices()) {
        Optional<Device> device = deviceRepository.findById(UUID.fromString(d));
        if (device.isPresent()) {
          deviceSet.add(device.get());
        }
      }
      a.setDevices(deviceSet);
    }
    assetRepository.save(a);
    return new GenericDataDTO.Builder()
        .id(a.getId().toString())
        .display(a.getName())
        .error(false)
        .build();
  }

  @Cacheable
  public List<GenericDataDTO> getAll() {
    List<GenericDataDTO> dtos = new ArrayList<GenericDataDTO>();
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<javax.persistence.Tuple> query = builder.createTupleQuery();
    Root<Asset> root = query.from(Asset.class);
    // @formatter:off
    query.multiselect(
        root.get("id").alias("id"),
        root.get("name").alias("name"),
        root.get("description").alias("description"),
        root.get("customer").alias("customer"));
    // @formatter:on
    query.orderBy(builder.asc(root.get("name")));
    TypedQuery<Tuple> createQuery = entityManager.createQuery(query);
    List<Tuple> resultList = createQuery.getResultList();
    for (Tuple tuple : resultList) {
      String id = tuple.get("id", UUID.class).toString();
      String name = tuple.get("name", String.class);
      HashMap<String, Object> data = new HashMap<>();
      data.put("description", tuple.get("description", String.class));
      if (tuple.get("customer", Customer.class) != null) {
        data.put("customer", tuple.get("customer", Customer.class).getId().toString());
      }
      dtos.add(new GenericDataDTO.Builder().id(id).display(name).additionalData(data).build());
    }
    return dtos;
  }

  public AssetDTO get(String assetId) {
    UUID id = UUID.fromString(assetId);
    Optional<Asset> a = assetRepository.findById(id);
    if (a.isPresent()) {
      Asset asset = a.get();
      return new AssetDTO.Builder()
          .id(assetId)
          .name(asset.getName())
          .description(asset.getName())
          .devices(
              asset.getDevices().stream()
                  .map(Device::getId)
                  .map(UUID::toString)
                  .collect(Collectors.toList()))
          .type(
              asset.getType().stream()
                  .map(AssetType::getId)
                  .map(UUID::toString)
                  .collect(Collectors.toList()))
          .customer(asset.getCustomer().getId().toString())
          .build();
    }
    throw new CustomException("Asset not found.", HttpStatus.NOT_FOUND);
  }

  public GenericDataDTO delete(String assetId) {
    UUID id = UUID.fromString(assetId);
    Optional<Asset> a = assetRepository.findById(id);
    if (a.isPresent()) {
      assetRepository.delete(a.get());
      return new GenericDataDTO.Builder().id(assetId).display("Asset deleted.").build();
    }
    throw new CustomException("Asset not found.", HttpStatus.NOT_FOUND);
  }
}
