package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

//Format: Ctrl+ Alt+ L
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @Column(columnDefinition = "BIGINT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(columnDefinition = "VARCHAR(255)")
    @NotBlank
    @Size(min = 3, max = 50)
    private String accountName;

    @JsonIgnore
    @Column(columnDefinition = "VARCHAR(255)")
    @NotBlank
    @Size(min = 6, max = 50)
    private String password;

    @Column(columnDefinition = "VARCHAR(255)")
    @NotBlank
    @Email
    private String email;

    @Column(columnDefinition = "VARCHAR(50)")
    private String resetPasswordToken;


    //    It
    @OneToOne(mappedBy = "account")
    @JsonBackReference
    private Employee employee;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "account", cascade = CascadeType.REMOVE)
    @JsonBackReference
    private Set<AccountRole> roleSet;


    public Account(@NotBlank @Size(min = 3, max = 50) String userName,
                   @NotBlank @Size(min = 6, max = 50)String encode) {
            this.accountName = userName;
            this.password = encode;
    }
}
