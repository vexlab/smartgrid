package ml.vexlab.smartgrid.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import ml.vexlab.smartgrid.entity.Snapshot;

public interface SnapshotRepository extends CrudRepository<Snapshot, UUID> {

	List<Snapshot> findByDeviceAndKey(String device, String key);

}
