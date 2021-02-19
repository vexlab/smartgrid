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
import ml.vexlab.smartgrid.entity.Customer;
import ml.vexlab.smartgrid.entity.Dashboard;
import ml.vexlab.smartgrid.exception.CustomException;
import ml.vexlab.smartgrid.repository.AssetRepository;
import ml.vexlab.smartgrid.repository.CustomerRepository;
import ml.vexlab.smartgrid.repository.DashboardRepository;
import ml.vexlab.smartgrid.service.CustomerService;
import ml.vexlab.smartgrid.transport.dto.CustomerDTO;
import ml.vexlab.smartgrid.transport.dto.GenericDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService {

  @Autowired private AssetRepository assetRepository;
  @Autowired private DashboardRepository dashboardRepository;
  @Autowired private CustomerRepository customerRepository;
  @Autowired EntityManager entityManager;

  @CachePut(value = "customer", key = "#id")
  public GenericDataDTO create(CustomerDTO customerDTO) {
    Customer c = new Customer();
    if (customerDTO.getId() != null) {
      Optional<Customer> customer =
          customerRepository.findById(UUID.fromString(customerDTO.getId()));
      if (customer.isPresent()) {
        // edit
        c = customer.get();
      }
    }
    c.setTitle(customerDTO.getTitle());
    c.setDescription(customerDTO.getDescription());
    c.setCountry(customerDTO.getCountry());
    c.setCity(customerDTO.getCity());
    c.setState(customerDTO.getState());
    c.setPostalCode(customerDTO.getPostalCode());
    c.setAddress(customerDTO.getAddress());
    c.setPhone(customerDTO.getPhone());
    c.setEmail(customerDTO.getEmail());
    List<String> assetList =
        customerDTO.getAssets() != null ? customerDTO.getAssets() : new ArrayList<String>();
    Set<Asset> assets = new HashSet<>();
    for (String assetId : assetList) {
      Optional<Asset> asset = assetRepository.findById(UUID.fromString(assetId));
      if (asset.isPresent()) {
        assets.add(asset.get());
      }
    }
    c.setAssets(assets);
    List<String> dashboardList =
        customerDTO.getDashboards() != null ? customerDTO.getDashboards() : new ArrayList<String>();
    Set<Dashboard> dashboards = new HashSet<>();
    for (String dashboardId : dashboardList) {
      Optional<Dashboard> dashboard = dashboardRepository.findById(UUID.fromString(dashboardId));
      if (dashboard.isPresent()) {
        dashboards.add(dashboard.get());
      }
    }
    c.setDashboards(dashboards);
    customerRepository.save(c);
    return new GenericDataDTO.Builder()
        .id(c.getId().toString())
        .display(c.getTitle())
        .error(false)
        .build();
  }

  @CacheEvict(value = "customer", key = "#id")
  public GenericDataDTO delete(String customerId) {
    UUID id = UUID.fromString(customerId);
    Optional<Customer> c = customerRepository.findById(id);
    if (c.isPresent()) {
      customerRepository.delete(c.get());
      return new GenericDataDTO.Builder().id(customerId).display("Customer deleted.").build();
    }
    return new GenericDataDTO.Builder()
        .id(customerId)
        .display("Generic error.")
        .error(true)
        .build();
  }

  @Cacheable(value = "customer", key = "#id")
  public List<GenericDataDTO> getAll() {
    List<GenericDataDTO> dtos = new ArrayList<GenericDataDTO>();
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<javax.persistence.Tuple> query = builder.createTupleQuery();
    Root<Customer> root = query.from(Customer.class);
    // @formatter:off
    query.multiselect(
        root.get("id").alias("id"),
        root.get("title").alias("title"),
        root.get("description").alias("description"),
        root.get("country").alias("country"),
        root.get("city").alias("city"),
        root.get("state").alias("state"),
        root.get("postalCode").alias("postalCode"),
        root.get("address").alias("address"),
        root.get("phone").alias("phone"),
        root.get("email").alias("email"));
    // @formatter:on
    query.orderBy(builder.asc(root.get("title")));
    TypedQuery<Tuple> createQuery = entityManager.createQuery(query);
    List<Tuple> resultList = createQuery.getResultList();
    for (Tuple tuple : resultList) {
      String id = tuple.get("id", UUID.class).toString();
      String title = tuple.get("title", String.class);
      HashMap<String, Object> data = new HashMap<>();
      data.put("description", tuple.get("description", String.class));
      data.put("country", tuple.get("country", String.class));
      data.put("city", tuple.get("city", String.class));
      data.put("state", tuple.get("state", String.class));
      data.put("postalCode", tuple.get("postalCode", String.class));
      data.put("address", tuple.get("address", String.class));
      data.put("phone", tuple.get("phone", String.class));
      data.put("email", tuple.get("email", String.class));
      dtos.add(new GenericDataDTO.Builder().id(id).display(title).additionalData(data).build());
    }
    return dtos;
  }

  public CustomerDTO get(String customerId) {
    UUID id = UUID.fromString(customerId);
    Optional<Customer> c = customerRepository.findById(id);
    if (c.isPresent()) {
      Customer customer = c.get();
      return new CustomerDTO.Builder()
          .id(customerId)
          .title(customer.getTitle())
          .description(customer.getDescription())
          .address(customer.getAddress())
          .assets(
              customer.getAssets().stream()
                  .map(Asset::getId)
                  .map(UUID::toString)
                  .collect(Collectors.toList()))
          .dashboards(
              customer.getDashboards().stream()
                  .map(Dashboard::getId)
                  .map(UUID::toString)
                  .collect(Collectors.toList()))
          .city(customer.getCity())
          .country(customer.getCountry())
          .email(customer.getEmail())
          .phone(customer.getPhone())
          .postalCode(customer.getPostalCode())
          .state(customer.getState())
          .build();
    }
    throw new CustomException("Customer not found.", HttpStatus.NOT_FOUND);
  }
}
