package ml.vexlab.smartgrid.repository;

import java.util.UUID;
import ml.vexlab.smartgrid.entity.Device;
import org.springframework.data.repository.CrudRepository;

public interface DeviceRepository extends CrudRepository<Device, UUID> {}
