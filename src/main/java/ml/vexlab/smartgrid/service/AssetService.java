package ml.vexlab.smartgrid.service;

import java.util.List;
import ml.vexlab.smartgrid.transport.dto.AssetDTO;
import ml.vexlab.smartgrid.transport.dto.GenericDataDTO;

public interface AssetService {

  GenericDataDTO create(AssetDTO assetDTO);

  List<GenericDataDTO> getAll();

  AssetDTO get(String assetId);

  GenericDataDTO delete(String assetId);
}
