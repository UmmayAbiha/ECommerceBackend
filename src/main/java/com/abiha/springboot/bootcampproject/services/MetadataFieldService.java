package com.abiha.springboot.bootcampproject.services;

import com.abiha.springboot.bootcampproject.entities.CategoryMetadataField;
import com.abiha.springboot.bootcampproject.repos.CategoryMetadataFieldRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetadataFieldService {

    @Autowired
    CategoryMetadataFieldRepo categoryMetadataFieldRepo;


    public String addMetadataField(CategoryMetadataField categoryMetadataField) {
        CategoryMetadataField c1=categoryMetadataFieldRepo.save(categoryMetadataField);
        return "MetadataField is added "+" with id " +c1.getId();
    }

    public List<CategoryMetadataField> viewAllMetaDataField() {
        return categoryMetadataFieldRepo.findAllMetadataFields(PageRequest.of(0, 10, Sort.Direction.ASC, "id"));
    }


}
