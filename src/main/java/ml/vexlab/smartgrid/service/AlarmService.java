package ml.vexlab.smartgrid.service;

import java.util.List;

import ml.vexlab.smartgrid.transport.dto.AlarmDTO;
import ml.vexlab.smartgrid.transport.dto.GenericDataDTO;

public interface AlarmService {

	List<GenericDataDTO> getAll();

	GenericDataDTO delete(String alarmId);

	GenericDataDTO create(AlarmDTO alarmDTO);

	AlarmDTO get(String alarmId);

}
