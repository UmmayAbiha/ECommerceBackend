package com.abiha.springboot.bootcampproject.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class CategoryMetadataField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    public CategoryMetadataField() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}