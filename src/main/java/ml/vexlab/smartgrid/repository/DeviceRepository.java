package ml.vexlab.smartgrid.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import ml.vexlab.smartgrid.entity.Device;

public interface DeviceRepository extends CrudRepository<Device, UUID> {

}