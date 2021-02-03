package ml.vexlab.smartgrid.service;

import java.util.List;

import ml.vexlab.smartgrid.transport.dto.GenericDataDTO;
import ml.vexlab.smartgrid.transport.dto.TelemetryDTO;
import ml.vexlab.smartgrid.transport.dto.TelemetryRequestDTO;

public interface TelemetryService {

	GenericDataDTO create(TelemetryDTO telemetryDTO);
	
	GenericDataDTO create(List<TelemetryDTO> telemetryDTOList);

	List<TelemetryDTO> getTelemetry(TelemetryRequestDTO telemetryRequestDTO);
	
	GenericDataDTO delete(TelemetryDTO telemetryDTO);

}
