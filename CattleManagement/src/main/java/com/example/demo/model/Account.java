package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

//Format: Ctrl+ Alt+ L
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @Column(columnDefinition = "VARCHAR(255)")
    private String setAccountId;

    @Column(columnDefinition = "VARCHAR(255)")
    private String accountName;

    @Column(columnDefinition = "VARCHAR(255)")
    private String password;

    //    It
    @OneToOne(mappedBy = "account")
    @JsonBackReference
    private Employee employee;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "account", cascade = CascadeType.REMOVE)
    private Set<AccountRole> roleSet;

}
