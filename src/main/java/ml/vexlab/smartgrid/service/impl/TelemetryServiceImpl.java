package ml.vexlab.smartgrid.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ml.vexlab.smartgrid.entity.Device;
import ml.vexlab.smartgrid.entity.History;
import ml.vexlab.smartgrid.entity.Snapshot;
import ml.vexlab.smartgrid.exception.CustomException;
import ml.vexlab.smartgrid.repository.DeviceRepository;
import ml.vexlab.smartgrid.repository.HistoryRepository;
import ml.vexlab.smartgrid.repository.SnapshotRepository;
import ml.vexlab.smartgrid.service.TelemetryService;
import ml.vexlab.smartgrid.transport.dto.GenericDataDTO;
import ml.vexlab.smartgrid.transport.dto.TelemetryDTO;
import ml.vexlab.smartgrid.transport.dto.TelemetryRequestDTO;

@Service(value = "telemetryService")
public class TelemetryServiceImpl implements TelemetryService {

	@Autowired
	private DeviceRepository deviceRepository;
	@Autowired
	private HistoryRepository historyRepository;
	@Autowired
	private SnapshotRepository snapshotRepository;
	@Autowired
	EntityManager entityManager;

	public GenericDataDTO create(TelemetryDTO dto) {
		if (dto.getDevice() != null) {
			Optional<Device> d = deviceRepository.findById(UUID.fromString(dto.getDevice()));
			if (d.isPresent() && dto.getKey() != null && dto.getValue() != null) {
				if (dto.getTs() == null) {
					dto.setTs(new Date());
				}
				Object value = dto.getValue();
				History h = null;
				Snapshot s = null;
				if (value instanceof Boolean) {
					h = new History(d.get(), dto.getKey(), dto.getTs(), (Boolean) value);
					s = new Snapshot(dto.getDevice(), dto.getKey(), dto.getTs(), (Boolean) value);
				} else if (value instanceof Double) {
					h = new History(d.get(), dto.getKey(), dto.getTs(), (Double) value);
					s = new Snapshot(dto.getDevice(), dto.getKey(), dto.getTs(), (Double) value);
				} else if (value instanceof Integer) {
					h = new History(d.get(), dto.getKey(), dto.getTs(), (Integer) value);
					s = new Snapshot(dto.getDevice(), dto.getKey(), dto.getTs(), (Integer) value);
				} else {
					h = new History(d.get(), dto.getKey(), dto.getTs(), (String) value);
					s = new Snapshot(dto.getDevice(), dto.getKey(), dto.getTs(), (String) value);
				}

				List<Snapshot> snapshot = snapshotRepository.findByDeviceAndKey(dto.getDevice(), dto.getKey());
				if (snapshot.size() > 0) {
					s.setId(snapshot.get(0).getId());
				}

				List<History> history = historyRepository.findByDeviceAndKeyAndCreationDate(d.get(), dto.getKey(),
						dto.getTs());
				if (history.size() > 0) {
					h.setId(history.get(0).getId());
				}

				historyRepository.save(h);
				snapshotRepository.save(s);
				return new GenericDataDTO.Builder().display("Data saved.").build();
			}
		}
		throw new CustomException("Data error.", HttpStatus.NOT_FOUND);
	}

	public GenericDataDTO create(List<TelemetryDTO> telemetryDTOList) {
		boolean onError = true;
		if (!telemetryDTOList.isEmpty()) {
			List<GenericDataDTO> results = new ArrayList<>();
			for (TelemetryDTO telemetryDTO : telemetryDTOList) {
				results.add(create(telemetryDTO));
			}
			onError = results.stream().allMatch(n -> n.isError());
		}
		if (onError) {
			throw new CustomException("Data error.", HttpStatus.NOT_FOUND);
		}
		return new GenericDataDTO.Builder().display("Data saved.").build();
	}

	@Override
	public List<TelemetryDTO> getTelemetry(TelemetryRequestDTO telemetryRequestDTO) {
		List<TelemetryDTO> dtos = new ArrayList<TelemetryDTO>();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<javax.persistence.Tuple> query = builder.createTupleQuery();
		Root<History> root = query.from(History.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		// @formatter:off
		query.multiselect(root.get("device").alias("device"), root.get("key").alias("key"),
				root.get("creationDate").alias("creationDate"), root.get("bool_value").alias("bool_value"),
				root.get("str_value").alias("str_value"), root.get("int_value").alias("int_value"),
				root.get("dbl_value").alias("dbl_value"));
		// @formatter:on
		if (telemetryRequestDTO.getFrom() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("creationDate"), telemetryRequestDTO.getFrom()));
		}
		if (telemetryRequestDTO.getTo() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("creationDate"), telemetryRequestDTO.getTo()));
		}
		if (telemetryRequestDTO.getDevice() != null) {
			predicates.add(builder.equal(root.get("device"), telemetryRequestDTO.getDevice()));
		}
		if (telemetryRequestDTO.getKey() != null) {
			predicates.add(builder.equal(root.get("key"), telemetryRequestDTO.getKey()));
		}
		query.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		query.orderBy(builder.desc(root.get("creationDate")));
		TypedQuery<Tuple> createQuery = entityManager.createQuery(query);
		if (telemetryRequestDTO.getLast() != null) {
			createQuery.setMaxResults(telemetryRequestDTO.getLast().intValue());
		}
		List<Tuple> resultList = createQuery.getResultList();
		for (Tuple tuple : resultList) {
			String device = tuple.get("device", String.class);
			String key = tuple.get("device", String.class);
			Date creationDate = tuple.get("creationDate", Date.class);
			Boolean bool_value = tuple.get("bool_value", Boolean.class);
			String str_value = tuple.get("str_value", String.class);
			Integer int_value = tuple.get("int_value", Integer.class);
			Double dbl_value = tuple.get("dbl_value", Double.class);

			dtos.add(new TelemetryDTO.Builder().device(device).key(key).ts(creationDate)
					.value(bool_value != null ? bool_value
							: (str_value != null ? str_value
									: (int_value != null ? int_value : (dbl_value != null ? dbl_value : null))))
					.build());
		}
		return dtos;
	}

	@Override
	public GenericDataDTO delete(TelemetryDTO telemetryDTO) {
		Optional<Device> d = deviceRepository.findById(UUID.fromString(telemetryDTO.getDevice()));
		if (d.isPresent()) {
			historyRepository.deleteByKeyAndDeviceAndCreationDate(telemetryDTO.getKey(), d.get(), telemetryDTO.getTs());
			return new GenericDataDTO.Builder().display("Telemetry removed.").build();
		}
		throw new CustomException("Data error.", HttpStatus.NOT_FOUND);
	}

}
