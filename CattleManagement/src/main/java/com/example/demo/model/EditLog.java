package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class EditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String cageId;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate editDate;

    private String employeeId;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String editedFields;

    public EditLog(String cageId, LocalDate editDate, String employeeId, String editedFields) {
        this.cageId = cageId;
        this.editDate = editDate;
        this.employeeId = employeeId;
        this.editedFields = editedFields;
    }

    @Override
    public String toString() {
        return "EditLog{" +
                "id=" + id +
                ", cageId='" + cageId + '\'' +
                ", editDate=" + editDate +
                ", employeeId='" + employeeId + '\'' +
                ", editedFields='" + editedFields + '\'' +
                '}';
    }
}
