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
import ml.vexlab.smartgrid.entity.Alarm;
import ml.vexlab.smartgrid.entity.Asset;
import ml.vexlab.smartgrid.entity.Device;
import ml.vexlab.smartgrid.entity.DeviceType;
import ml.vexlab.smartgrid.exception.CustomException;
import ml.vexlab.smartgrid.repository.AlarmRepository;
import ml.vexlab.smartgrid.repository.AssetRepository;
import ml.vexlab.smartgrid.repository.DeviceRepository;
import ml.vexlab.smartgrid.repository.DeviceTypeRepository;
import ml.vexlab.smartgrid.service.DeviceService;
import ml.vexlab.smartgrid.transport.dto.DeviceDTO;
import ml.vexlab.smartgrid.transport.dto.GenericDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service(value = "deviceService")
public class DeviceServiceImpl implements DeviceService {

  @Autowired private DeviceRepository deviceRepository;
  @Autowired private AssetRepository assetRepository;
  @Autowired private DeviceTypeRepository deviceTypeRepository;
  @Autowired private AlarmRepository alarmRepository;
  @Autowired EntityManager entityManager;

  @Override
  public GenericDataDTO create(DeviceDTO deviceDTO) {
    if (deviceDTO.getAsset() == null) {
      return new GenericDataDTO.Builder().display("Asset not found.").error(true).build();
    }
    Device d = new Device();
    if (deviceDTO.getId() != null) {
      Optional<Device> device = deviceRepository.findById(UUID.fromString(deviceDTO.getId()));
      if (device.isPresent()) {
        // edit
        d = device.get();
      }
    }
    d.setDescription(deviceDTO.getDescription());
    if (deviceDTO.getAsset() != null || !deviceDTO.getAsset().isEmpty()) {
      Optional<Asset> a = assetRepository.findById(UUID.fromString(deviceDTO.getAsset()));
      if (a.isPresent()) {
        d.setAsset(a.get());
      } else {
        throw new CustomException("Asset not found.", HttpStatus.NOT_FOUND);
      }
    }

    if (deviceDTO.getType() != null) {
      Set<DeviceType> types = new HashSet<DeviceType>();
      for (String t : deviceDTO.getType()) {
        Optional<DeviceType> type = deviceTypeRepository.findById(UUID.fromString(t));
        if (type.isPresent()) {
          types.add(type.get());
        } else {
          throw new CustomException("DeviceType not found.", HttpStatus.NOT_FOUND);
        }
      }
      d.setType(types);
    }

    if (deviceDTO.getAlarm() != null) {
      Set<Alarm> alarms = new HashSet<Alarm>();
      for (String a : deviceDTO.getAlarm()) {
        Optional<Alarm> alarm = alarmRepository.findById(UUID.fromString(a));
        if (alarm.isPresent()) {
          alarms.add(alarm.get());
        } else {
          throw new CustomException("Alarm not found.", HttpStatus.NOT_FOUND);
        }
      }
      d.setAlarms(alarms);
    }

    d.setAccessToken(deviceDTO.getAccessToken());

    deviceRepository.save(d);
    return new GenericDataDTO.Builder()
        .id(d.getId().toString())
        .display(d.getDescription())
        .error(false)
        .build();
  }

  @Override
  public DeviceDTO get(String deviceId) {
    UUID id = UUID.fromString(deviceId);
    Optional<Device> d = deviceRepository.findById(id);
    if (d != null) {
      Device device = d.get();
      return new DeviceDTO.Builder()
          .id(deviceId)
          .description(device.getDescription())
          .accessToken(device.getAccessToken())
          .asset(device.getAsset().getId().toString())
          .alarm(
              device.getAlarms().stream()
                  .map(Alarm::getId)
                  .map(UUID::toString)
                  .collect(Collectors.toList()))
          .build();
    }
    throw new CustomException("Device not found.", HttpStatus.NOT_FOUND);
  }

  @Override
  public GenericDataDTO delete(String deviceId) {
    UUID id = UUID.fromString(deviceId);
    Optional<Device> d = deviceRepository.findById(id);
    if (d != null) {
      deviceRepository.delete(d.get());
      return new GenericDataDTO.Builder().id(deviceId).display("Dashboard deleted.").build();
    }
    throw new CustomException("Device not found.", HttpStatus.NOT_FOUND);
  }

  @Override
  public List<GenericDataDTO> getAll() {
    List<GenericDataDTO> dtos = new ArrayList<GenericDataDTO>();
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<javax.persistence.Tuple> query = builder.createTupleQuery();
    Root<Device> root = query.from(Device.class);
    // @formatter:off
    query.multiselect(
        root.get("id").alias("id"),
        root.get("description").alias("description"),
        root.get("accessToken").alias("accessToken"));
    // @formatter:on
    query.orderBy(builder.asc(root.get("description")));
    TypedQuery<Tuple> createQuery = entityManager.createQuery(query);
    List<Tuple> resultList = createQuery.getResultList();
    for (Tuple tuple : resultList) {
      String id = tuple.get("id", UUID.class).toString();
      String title = tuple.get("description", String.class);
      HashMap<String, Object> data = new HashMap<>();
      data.put("value", tuple.get("accessToken", String.class));
      dtos.add(new GenericDataDTO.Builder().id(id).display(title).additionalData(data).build());
    }
    return dtos;
  }
}
