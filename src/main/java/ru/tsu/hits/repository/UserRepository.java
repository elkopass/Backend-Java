package ru.tsu.hits.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.tsu.hits.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface UserRepository  extends CrudRepository<UserEntity, String>, JpaSpecificationExecutor<UserEntity> {
    List<UserEntity> findAll(Specification<UserEntity> specification);
}
