package ml.vexlab.smartgrid.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import ml.vexlab.smartgrid.entity.DeviceType;

public interface DeviceTypeRepository extends CrudRepository<DeviceType, UUID> {

}
