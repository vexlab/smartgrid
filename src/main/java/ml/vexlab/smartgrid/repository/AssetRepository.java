package ml.vexlab.smartgrid.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import ml.vexlab.smartgrid.entity.Asset;

public interface AssetRepository extends CrudRepository<Asset, UUID> {

}
