package com.abiha.springboot.bootcampproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class CategoryMetadataFieldValues {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="categoryId",referencedColumnName = "id",nullable = false)
    private Category category;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="categoryMetadataId",referencedColumnName = "id",nullable = false)
    private CategoryMetadataField categoryMetadataField;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public com.abiha.springboot.bootcampproject.entities.Category getCategory() {
        return category;
    }

    public void setCategory(com.abiha.springboot.bootcampproject.entities.Category category) {
        this.category = category;
    }

    public com.abiha.springboot.bootcampproject.entities.CategoryMetadataField getCategoryMetadataField() {
        return categoryMetadataField;
    }

    public void setCategoryMetadataField(com.abiha.springboot.bootcampproject.entities.CategoryMetadataField categoryMetadataField) {
        this.categoryMetadataField = categoryMetadataField;
    }
}