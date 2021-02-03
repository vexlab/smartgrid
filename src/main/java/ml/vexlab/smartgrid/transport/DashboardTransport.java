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

import ml.vexlab.smartgrid.service.DashboardService;
import ml.vexlab.smartgrid.transport.dto.DashboardDTO;
import ml.vexlab.smartgrid.transport.dto.GenericDataDTO;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin()

public class DashboardTransport {
	
	@Autowired
	private DashboardService dashboardService;
	
	@GetMapping("/all")
	public List<GenericDataDTO> getAll() {
		return dashboardService.getAll();
	}
	
	@GetMapping("/{dashboardId}")
	public DashboardDTO get(@PathVariable String dashboardId) {
		return dashboardService.get(dashboardId);
	}
	
	@PostMapping
	public GenericDataDTO edit(@RequestBody DashboardDTO dashboardDTO) {
		return dashboardService.create(dashboardDTO);
	}
	
	@PutMapping
	public GenericDataDTO create(@RequestBody DashboardDTO dashboardDTO) {
		return dashboardService.create(dashboardDTO);
	}
	
	@DeleteMapping("/{dashboardId}")
	public GenericDataDTO delete(@PathVariable String dashboardId) {
		return dashboardService.delete(dashboardId);
	}	


}
