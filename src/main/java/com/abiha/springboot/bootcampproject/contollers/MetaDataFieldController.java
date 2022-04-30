package com.abiha.springboot.bootcampproject.contollers;

import com.abiha.springboot.bootcampproject.entities.CategoryMetadataField;
import com.abiha.springboot.bootcampproject.services.MetadataFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/metadataField")
public class MetaDataFieldController {

    @Autowired
    MetadataFieldService metadataFieldService;

    //Admin apis

    @PostMapping("/add")
    public String addMetadatField(@Valid @RequestBody CategoryMetadataField categoryMetadataField){
        return metadataFieldService.addMetadataField(categoryMetadataField);
    }

    @GetMapping("/view")
    public List<CategoryMetadataField> viewAllMetaDataField(){
        return metadataFieldService.viewAllMetaDataField();
    }

}
