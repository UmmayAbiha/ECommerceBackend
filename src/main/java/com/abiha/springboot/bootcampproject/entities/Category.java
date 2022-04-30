package com.abiha.springboot.bootcampproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Category
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Product> products;

    @ManyToOne
    @JoinColumn(name="parentCategory",referencedColumnName = "id")
    @JsonIgnore
    private Category leafCategory;  //child category

    //YE SAMAJHNA H
    @ManyToOne
    @JoinColumn(name = "parentId")
    private Category parentCategory;


    public Category() {
    }

    public Category(String name) {
        this.name = name;    }

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

    public Category getLeafCategory() {
        return leafCategory;
    }

    public void setLeafCategory(Category leafCategory) {
        this.leafCategory = leafCategory;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}