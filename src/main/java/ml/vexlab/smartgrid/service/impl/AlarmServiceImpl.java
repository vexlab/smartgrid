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
import ml.vexlab.smartgrid.entity.Alarm;
import ml.vexlab.smartgrid.entity.Device;
import ml.vexlab.smartgrid.enums.AlarmType;
import ml.vexlab.smartgrid.exception.CustomException;
import ml.vexlab.smartgrid.repository.AlarmRepository;
import ml.vexlab.smartgrid.repository.DeviceRepository;
import ml.vexlab.smartgrid.service.AlarmService;
import ml.vexlab.smartgrid.transport.dto.AlarmDTO;
import ml.vexlab.smartgrid.transport.dto.GenericDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service(value = "alarmService")
public class AlarmServiceImpl implements AlarmService {

  @Autowired private AlarmRepository alarmRepository;
  @Autowired private DeviceRepository deviceRepository;
  @Autowired EntityManager entityManager;

  @Cacheable(value = "alarm", key = "#id")
  public List<GenericDataDTO> getAll() {
    List<GenericDataDTO> dtos = new ArrayList<GenericDataDTO>();
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<javax.persistence.Tuple> query = builder.createTupleQuery();
    Root<Alarm> root = query.from(Alarm.class);
    // @formatter:off
    query.multiselect(
        root.get("id").alias("id"),
        root.get("detail").alias("detail"),
        root.get("originator").alias("originator"),
        root.get("status").alias("status"),
        root.get("severity").alias("severity"));
    // @formatter:on
    query.orderBy(builder.asc(root.get("status")));
    TypedQuery<Tuple> createQuery = entityManager.createQuery(query);
    List<Tuple> resultList = createQuery.getResultList();
    for (Tuple tuple : resultList) {
      String id = tuple.get("id", UUID.class).toString();
      String status = tuple.get("status", String.class);
      HashMap<String, Object> data = new HashMap<>();
      data.put("originator", tuple.get("originator", String.class));
      data.put("severity", tuple.get("severity", String.class));
      dtos.add(new GenericDataDTO.Builder().id(id).display(status).additionalData(data).build());
    }
    return dtos;
  }

  @CacheEvict(value = "alarm", key = "#id")
  public GenericDataDTO delete(String alarmId) {
    UUID id = UUID.fromString(alarmId);
    Optional<Alarm> a = alarmRepository.findById(id);
    if (a.isPresent()) {
      alarmRepository.delete(a.get());
      return new GenericDataDTO.Builder().id(alarmId).display("Alarm deleted.").build();
    }
    throw new CustomException("Alarm not found.", HttpStatus.NOT_FOUND);
  }

  @CachePut(value = "alarm", key = "#id")
  public GenericDataDTO create(AlarmDTO alarmDTO) {
    if (alarmDTO.getDevice() == null) {
      throw new CustomException("Device not found.", HttpStatus.NOT_FOUND);
    }
    Alarm a = new Alarm();
    if (alarmDTO.getId() != null) {
      Optional<Alarm> alarm = alarmRepository.findById(UUID.fromString(alarmDTO.getId()));
      if (alarm.isPresent()) {
        // edit
        a = alarm.get();
      }
    }
    a.setDetail(alarmDTO.getDetail());
    a.setOriginator(alarmDTO.getOriginator());
    a.setSeverity(alarmDTO.getSeverity());
    a.setStatus(alarmDTO.getStatus());

    if (alarmDTO.getType() != null) {
      a.setType(AlarmType.valueOf(alarmDTO.getType()));
    }

    Optional<Device> d = deviceRepository.findById(UUID.fromString(alarmDTO.getDevice()));
    if (!d.isPresent()) {
      throw new CustomException("Device not found.", HttpStatus.NOT_FOUND);
    }
    a.setDevice(d.get());

    alarmRepository.save(a);
    return new GenericDataDTO.Builder()
        .id(a.getId().toString())
        .display(a.getStatus())
        .error(false)
        .build();
  }

  public AlarmDTO get(String alarmId) {
    UUID id = UUID.fromString(alarmId);
    Optional<Alarm> a = alarmRepository.findById(id);
    if (a.isPresent()) {
      Alarm alarm = a.get();
      return new AlarmDTO.Builder()
          .id(alarmId)
          .detail(alarm.getDetail())
          .device(alarm.getDevice().getId().toString())
          .originator(alarm.getOriginator())
          .severity(alarm.getSeverity())
          .status(alarm.getStatus())
          .build();
    }
    throw new CustomException("Alarm not found.", HttpStatus.NOT_FOUND);
  }
}
