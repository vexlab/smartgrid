package ml.vexlab.smartgrid.repository;

import java.util.UUID;
import ml.vexlab.smartgrid.entity.Dashboard;
import org.springframework.data.repository.CrudRepository;

public interface DashboardRepository extends CrudRepository<Dashboard, UUID> {}
