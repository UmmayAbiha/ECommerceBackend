package com.abiha.springboot.bootcampproject.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(CategoryMetadataFieldValuesPK.class)
public class CategoryMetadataFieldValues implements Serializable {

    @Id
    @ManyToOne
    private CategoryMetadataField categoryMetadataField;

    @Id
    @ManyToOne
    private Category category;

    private String categoryValues;

}

