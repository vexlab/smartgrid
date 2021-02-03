package ml.vexlab.smartgrid.repository;

import java.util.UUID;
import ml.vexlab.smartgrid.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, UUID> {}
