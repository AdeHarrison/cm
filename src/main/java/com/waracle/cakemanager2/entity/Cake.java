package com.waracle.cakemanager2.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Employee",
        uniqueConstraints = { @UniqueConstraint(columnNames = "ID"), @UniqueConstraint(columnNames = "EMAIL") })
public class Cake implements Serializable {

    private static final long serialVersionUID = -1798070786993154676L;

    public Cake(String title, String description, String image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer employeeId;

    @Column(name = "EMAIL", unique = true, nullable = false, length = 100)
    private String title;

    @Column(name = "FIRST_NAME", nullable = false, length = 100)
    private String description;

    @Column(name = "LAST_NAME", nullable = false, length = 300)
    private String image;
}