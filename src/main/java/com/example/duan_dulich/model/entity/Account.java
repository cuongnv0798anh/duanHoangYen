package com.example.duan_dulich.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "account")
public class Account extends TimeModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAccount;
    @Column(columnDefinition = "NVARCHAR(100)", nullable = false)
    private String userName;
    @Column(columnDefinition = "NVARCHAR(100)", nullable = false)
    private String fullName;
    @Column(columnDefinition = "NVARCHAR(100)", nullable = false)
    private String passWord;
    @Column(columnDefinition = "NVARCHAR(100)", nullable = false)
    private String email;
    @Column(columnDefinition = "NVARCHAR(100)", nullable = false)
    private String phone;
    @Column(columnDefinition = "NVARCHAR(100)")
    private String company;
    @Column(columnDefinition = "boolean default false")
    private boolean isActive ;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(name = "account_role",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;
    @OneToMany(fetch = FetchType.LAZY)
            @JsonIgnore
    List<TourEntity> tourEntities = new ArrayList<>() ;

}
