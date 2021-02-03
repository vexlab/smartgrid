package ml.vexlab.smartgrid.service;

import java.util.List;

import ml.vexlab.smartgrid.transport.dto.CustomerDTO;
import ml.vexlab.smartgrid.transport.dto.GenericDataDTO;

public interface CustomerService {

	GenericDataDTO create(CustomerDTO customerDTO);

	GenericDataDTO delete(String customerId);

	List<GenericDataDTO> getAll();

	CustomerDTO get(String customerId);

}
