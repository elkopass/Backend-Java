package ru.tsu.hits.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

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
    private String sno;

    @Column
    private  String password;

    @Column
    private String username;


    @Enumerated(EnumType.STRING)
    @Column
    private  Role role;


}
