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

import ml.vexlab.smartgrid.service.CustomerService;
import ml.vexlab.smartgrid.transport.dto.CustomerDTO;
import ml.vexlab.smartgrid.transport.dto.GenericDataDTO;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin
public class CustomerTransport {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/all")
	public List<GenericDataDTO> getAll() {
		return customerService.getAll();
	}
	
	@GetMapping("/{customerId}")
	public CustomerDTO get(@PathVariable String customerId) {
		return customerService.get(customerId);
	}
	
	@PostMapping
	public GenericDataDTO edit(@RequestBody CustomerDTO customerDTO) {
		return customerService.create(customerDTO);
	}
	
	@PutMapping
	public GenericDataDTO create(@RequestBody CustomerDTO customerDTO) {
		return customerService.create(customerDTO);
	}
	
	@DeleteMapping("/{customerId}")
	public GenericDataDTO delete(@PathVariable String customerId) {
		return customerService.delete(customerId);
	}	

}
