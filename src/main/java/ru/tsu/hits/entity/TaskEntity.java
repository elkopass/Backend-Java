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
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {
    @Id
    @Column(name = "id")
    private  String uuid;


    @Temporal(TemporalType.DATE)
    @Column
    private Date createDate;


    @Temporal(TemporalType.DATE)
    @Column
    private Date editDate;

    @Column
    @NotBlank
    private String name;

    @Column
    @NotBlank
    @Size(min = 9)
    private  String description;

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private  UserEntity creator;

    @ManyToOne
    @JoinColumn(name = "executer_id", referencedColumnName = "id")
    private  UserEntity executer;

    @Column
    private  String priority;


    @Column
    private  String mark;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private  ProjectEntity projectId;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "task")
    private List<CommentEntity> comments;



}
