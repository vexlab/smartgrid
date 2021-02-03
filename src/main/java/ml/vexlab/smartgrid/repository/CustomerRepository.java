package ml.vexlab.smartgrid.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import ml.vexlab.smartgrid.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, UUID> {

}
