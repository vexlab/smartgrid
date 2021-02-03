package ml.vexlab.smartgrid.repository;

import java.util.UUID;
import ml.vexlab.smartgrid.entity.AssetType;
import org.springframework.data.repository.CrudRepository;

public interface AssetTypeRepository extends CrudRepository<AssetType, UUID> {}
