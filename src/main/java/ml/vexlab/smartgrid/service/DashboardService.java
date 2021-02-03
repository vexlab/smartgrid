package ml.vexlab.smartgrid.service;

import java.util.List;
import ml.vexlab.smartgrid.transport.dto.DashboardDTO;
import ml.vexlab.smartgrid.transport.dto.GenericDataDTO;

public interface DashboardService {

  List<GenericDataDTO> getAll();

  DashboardDTO get(String dashboardId);

  GenericDataDTO delete(String dashboardId);

  GenericDataDTO create(DashboardDTO dashboardDTO);
}
