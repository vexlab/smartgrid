package ml.vexlab.smartgrid.transport;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ml.vexlab.smartgrid.service.DeviceService;
import ml.vexlab.smartgrid.transport.dto.DeviceDTO;
import ml.vexlab.smartgrid.transport.dto.GenericDataDTO;

@RestController
@RequestMapping("/api/device")
@CrossOrigin()

public class DeviceTransport {
	
	@Autowired
	private DeviceService deviceService;
	
	@GetMapping("/all")
	public List<GenericDataDTO> getAll() {
		return deviceService.getAll();
	}
	
	@GetMapping("/{deviceId}")
	public DeviceDTO get(@PathVariable String deviceId) {
		return deviceService.get(deviceId);
	}
	
	@PostMapping
	public GenericDataDTO edit(@RequestBody DeviceDTO deviceDTO) {
		return deviceService.create(deviceDTO);
	}
	
	@PutMapping
	public GenericDataDTO create(@RequestBody DeviceDTO deviceDTO) {
		return deviceService.create(deviceDTO);
	}
	
	@DeleteMapping("/{deviceId}")
	public GenericDataDTO delete(@PathVariable String deviceId) {
		return deviceService.delete(deviceId);
	}	

}
