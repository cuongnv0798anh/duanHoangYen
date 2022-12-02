package com.example.duan_dulich.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRole;
    @Column(columnDefinition = "NVARCHAR(100)", nullable = false)
    private String nameRole;
    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "roles")
    private Collection<Account> accounts;
//    @OneToMany(mappedBy = "role",cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
//    private Collection<Account> account;
//    @PreRemove
//    private void preRemove(){
//        account.forEach(account -> account.setRole(null));
//    }

    public Role(String nameRole) {
        this.nameRole = nameRole;
    }
}
