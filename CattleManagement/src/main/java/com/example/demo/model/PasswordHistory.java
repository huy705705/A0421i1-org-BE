package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PasswordHistory {

    @Id
    @Column(columnDefinition = "BIGINT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(targetEntity = Account.class)
    @JoinColumn(name = "accountId", referencedColumnName = "accountId")
    private Account account;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate editDate;

    @NotNull
    private String oldPassword;

    public PasswordHistory(Account account, LocalDate editDate, @NotNull String oldPassword) {
        this.account = account;
        this.editDate = editDate;
        this.oldPassword = oldPassword;
    }

    @Override
    public String toString() {
        return "PasswordHistory{" +
                "id=" + id +
                ", account=" + account +
                ", editDate=" + editDate +
                ", oldPassword='" + oldPassword + '\'' +
                '}';
    }
}
