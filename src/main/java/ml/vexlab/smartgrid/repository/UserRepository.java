package ml.vexlab.smartgrid.repository;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import ml.vexlab.smartgrid.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, UUID> {

	boolean existsByUsername(String username);

	UserEntity findByUsername(String username);

	@Transactional
	void deleteByUsername(String username);
}
