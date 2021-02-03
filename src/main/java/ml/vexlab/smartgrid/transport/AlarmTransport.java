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

import ml.vexlab.smartgrid.service.AlarmService;
import ml.vexlab.smartgrid.transport.dto.AlarmDTO;
import ml.vexlab.smartgrid.transport.dto.GenericDataDTO;

@RestController
@RequestMapping("/api/alarm")
@CrossOrigin()

public class AlarmTransport {
	
	@Autowired
	private AlarmService alarmService;

	@GetMapping("/all")
	public List<GenericDataDTO> getAll() {
		return alarmService.getAll();
	}
	
	@GetMapping("/{alarmId}")
	public AlarmDTO get(@PathVariable String alarmId) {
		return alarmService.get(alarmId);
	}
	
	@PostMapping
	public GenericDataDTO edit(@RequestBody AlarmDTO alarmDTO) {
		return alarmService.create(alarmDTO);
	}
	
	@PutMapping
	public GenericDataDTO create(@RequestBody AlarmDTO alarmDTO) {
		return alarmService.create(alarmDTO);
	}
	
	@DeleteMapping("/{alarmId}")
	public GenericDataDTO delete(@PathVariable String alarmId) {
		return alarmService.delete(alarmId);
	}	

}
