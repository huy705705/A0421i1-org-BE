package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class NewType {
    @Id
    @Column(columnDefinition = "VARCHAR(255)")
    private String typeId;

    @Column(columnDefinition = "VARCHAR(255)")
    private String typeName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "newType", cascade = CascadeType.REMOVE)
    @JsonBackReference(value = "JsonBackNews")
    private Set<News> news;
}
