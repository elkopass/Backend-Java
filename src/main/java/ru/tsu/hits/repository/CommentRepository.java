package ru.tsu.hits.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.tsu.hits.entity.CommentEntity;
import ru.tsu.hits.entity.ProjectEntity;
import ru.tsu.hits.entity.TaskEntity;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, String> {

    @Query(value = "SELECT * FROM comments c where c.comment_Text like %:template%", nativeQuery = true)
    List<CommentEntity> findCommentEntityByTemplate(@Param("template") String template);
    //List<CommentEntity> findByTask(TaskEntity taskEntity);
}
