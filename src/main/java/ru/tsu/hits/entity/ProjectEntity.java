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
@Table(name = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectEntity {

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

    @OneToMany(mappedBy = "projectId")
    private List<TaskEntity> tasks;

}
