package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class News {
    @Id
    @Column(columnDefinition = "VARCHAR(255)")
    private String newsId;

    @Column(columnDefinition = "VARCHAR(255)")
    private String newsName;

    @Column(columnDefinition = "VARCHAR(255)")
    private String detailDescription;

    @Column(columnDefinition = "VARCHAR(2000)")
    private String content;

    @Column(columnDefinition = "VARCHAR(300)")
    private String highlight;

    @Column(columnDefinition = "VARCHAR(500)")
    private String image;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;

    private Boolean isDelete;

    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "employeeId", referencedColumnName = "employeeId")
    @JsonBackReference
    private Employee employee;

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public String getDetailDescription() {
        return detailDescription;
    }

    public void setDetailDescription(String detailDescription) {
        this.detailDescription = detailDescription;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
