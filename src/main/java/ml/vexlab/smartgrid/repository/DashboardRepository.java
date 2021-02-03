package ml.vexlab.smartgrid.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import ml.vexlab.smartgrid.entity.Dashboard;

public interface DashboardRepository extends CrudRepository<Dashboard, UUID> {

}
