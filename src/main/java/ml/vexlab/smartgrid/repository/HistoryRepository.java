package ml.vexlab.smartgrid.repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import ml.vexlab.smartgrid.entity.Device;
import ml.vexlab.smartgrid.entity.History;

public interface HistoryRepository extends CrudRepository<History, UUID> {

	List<History> findByDeviceAndKeyAndCreationDate(Device device, String key, Date ts);
	
	void deleteByKeyAndDeviceAndCreationDate(String key, Device device, Date creationDate);

}
