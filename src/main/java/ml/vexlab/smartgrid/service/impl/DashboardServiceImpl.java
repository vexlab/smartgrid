package ml.vexlab.smartgrid.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ml.vexlab.smartgrid.entity.Customer;
import ml.vexlab.smartgrid.entity.Dashboard;
import ml.vexlab.smartgrid.exception.CustomException;
import ml.vexlab.smartgrid.repository.CustomerRepository;
import ml.vexlab.smartgrid.repository.DashboardRepository;
import ml.vexlab.smartgrid.service.DashboardService;
import ml.vexlab.smartgrid.transport.dto.DashboardDTO;
import ml.vexlab.smartgrid.transport.dto.GenericDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service(value = "dashboardService")
public class DashboardServiceImpl implements DashboardService {

  @Autowired private DashboardRepository dashboardRepository;
  @Autowired private CustomerRepository customerRepository;
  @Autowired EntityManager entityManager;

  @CachePut(value = "dashboard", key = "#id")
  public GenericDataDTO create(DashboardDTO dashboardDTO) {
    if (dashboardDTO.getCustomer() == null) {
      return new GenericDataDTO.Builder().display("Customer not found.").error(true).build();
    }
    Dashboard d = new Dashboard();
    if (dashboardDTO.getId() != null) {
      Optional<Dashboard> dashboard =
          dashboardRepository.findById(UUID.fromString(dashboardDTO.getId()));
      if (dashboard.isPresent()) {
        // edit
        d = dashboard.get();
      }
    }
    d.setTitle(dashboardDTO.getTitle());
    d.setValue(dashboardDTO.getValue());
    Optional<Customer> c = customerRepository.findById(UUID.fromString(dashboardDTO.getCustomer()));
    if (!c.isPresent()) {
      throw new CustomException("Customer not found.", HttpStatus.NOT_FOUND);
    }
    d.setCustomer(c.get());
    dashboardRepository.save(d);
    return new GenericDataDTO.Builder()
        .id(d.getId().toString())
        .display(d.getTitle())
        .error(false)
        .build();
  }

  @CacheEvict(value = "dashboard", key = "#id")
  public GenericDataDTO delete(String dashboardId) {
    UUID id = UUID.fromString(dashboardId);
    Optional<Dashboard> d = dashboardRepository.findById(id);
    if (d.isPresent()) {
      dashboardRepository.delete(d.get());
      return new GenericDataDTO.Builder().id(dashboardId).display("Dashboard deleted.").build();
    }
    throw new CustomException("Dashboard not found.", HttpStatus.NOT_FOUND);
  }

  @Cacheable(value = "dashboard", key = "#id")
  public List<GenericDataDTO> getAll() {
    List<GenericDataDTO> dtos = new ArrayList<GenericDataDTO>();
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<javax.persistence.Tuple> query = builder.createTupleQuery();
    Root<Dashboard> root = query.from(Dashboard.class);
    // @formatter:off
    query.multiselect(
        root.get("id").alias("id"),
        root.get("title").alias("title"),
        root.get("value").alias("value"));
    // @formatter:on
    query.orderBy(builder.asc(root.get("title")));
    TypedQuery<Tuple> createQuery = entityManager.createQuery(query);
    List<Tuple> resultList = createQuery.getResultList();
    for (Tuple tuple : resultList) {
      String id = tuple.get("id", UUID.class).toString();
      String title = tuple.get("title", String.class);
      HashMap<String, Object> data = new HashMap<>();
      data.put("value", tuple.get("value", String.class));
      dtos.add(new GenericDataDTO.Builder().id(id).display(title).additionalData(data).build());
    }
    return dtos;
  }

  public DashboardDTO get(String dashboardId) {
    UUID id = UUID.fromString(dashboardId);
    Optional<Dashboard> d = dashboardRepository.findById(id);
    if (d.isPresent()) {
      Dashboard dashboard = d.get();
      return new DashboardDTO.Builder()
          .id(dashboardId)
          .title(dashboard.getTitle())
          .value(dashboard.getValue())
          .build();
    }
    throw new CustomException("Dashboard not found.", HttpStatus.NOT_FOUND);
  }
}
