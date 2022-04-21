package ru.tsu.hits.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.tsu.hits.entity.CommentEntity;
import ru.tsu.hits.entity.ProjectEntity;
import ru.tsu.hits.entity.TaskEntity;
import ru.tsu.hits.entity.UserEntity;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, String>, JpaSpecificationExecutor<TaskEntity> {
    List<TaskEntity> findAll(Specification<TaskEntity> specification);
    List<TaskEntity> findByExecuter(UserEntity userEntity);
    List<TaskEntity>  findByProjectId(ProjectEntity projectEntity);
}
