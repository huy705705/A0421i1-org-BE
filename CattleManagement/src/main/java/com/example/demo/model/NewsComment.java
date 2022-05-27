package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class NewsComment {
    @Id
    @Column(columnDefinition = "BIGINT")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long commentId;

    @Column(columnDefinition = "VARCHAR(2000)")
    private String content;

    private Boolean isDelete;

    @ManyToOne(targetEntity = News.class)
    @JoinColumn(name = "newsId", referencedColumnName = "newsId")
    private News news;

    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "employeeId", referencedColumnName = "employeeId")
    private Employee employee;
}
