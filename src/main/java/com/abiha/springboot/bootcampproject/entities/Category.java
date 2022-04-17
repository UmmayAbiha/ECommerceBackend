package com.abiha.springboot.bootcampproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Category
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;

    @ManyToOne
    @JoinColumn(name="parentCategoryId",referencedColumnName = "id")
    @JsonIgnore
    private Category category;

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public com.abiha.springboot.bootcampproject.entities.Category getCategory() {
        return category;
    }

    public void setCategory(com.abiha.springboot.bootcampproject.entities.Category category) {
        this.category = category;
    }
}