package ml.vexlab.smartgrid.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import ml.vexlab.smartgrid.entity.AssetType;

public interface AssetTypeRepository extends CrudRepository<AssetType, UUID> {

}
