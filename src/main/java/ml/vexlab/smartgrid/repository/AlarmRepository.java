package ml.vexlab.smartgrid.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import ml.vexlab.smartgrid.entity.Alarm;

public interface AlarmRepository extends CrudRepository<Alarm, UUID> {

}
