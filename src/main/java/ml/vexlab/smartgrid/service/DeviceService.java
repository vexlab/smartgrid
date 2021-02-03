package ml.vexlab.smartgrid.service;

import java.util.List;

import ml.vexlab.smartgrid.transport.dto.DeviceDTO;
import ml.vexlab.smartgrid.transport.dto.GenericDataDTO;

public interface DeviceService {

	GenericDataDTO create(DeviceDTO deviceDTO);

	GenericDataDTO delete(String deviceId);

	DeviceDTO get(String deviceId);

	List<GenericDataDTO> getAll();

}
