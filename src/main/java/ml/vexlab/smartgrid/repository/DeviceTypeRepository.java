package ml.vexlab.smartgrid.repository;

import java.util.UUID;
import ml.vexlab.smartgrid.entity.DeviceType;
import org.springframework.data.repository.CrudRepository;

public interface DeviceTypeRepository extends CrudRepository<DeviceType, UUID> {}
