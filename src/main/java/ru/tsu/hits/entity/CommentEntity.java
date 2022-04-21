package ru.tsu.hits.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {
    @Id
    @Column(name = "id")
    private  String uuid;


    @Temporal(TemporalType.DATE)
    @Column
    private Date createDate;


    @Temporal(TemporalType.DATE)
    @Column
    private Date editDate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "task_and_comments",
            joinColumns = @JoinColumn(name = "comment_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id")
    )
    private List<TaskEntity> task;

    @Column
    @NotBlank
    private  String commentText;
}
