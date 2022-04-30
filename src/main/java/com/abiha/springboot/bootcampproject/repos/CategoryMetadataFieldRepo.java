package com.abiha.springboot.bootcampproject.repos;

import com.abiha.springboot.bootcampproject.entities.CategoryMetadataField;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryMetadataFieldRepo extends JpaRepository<CategoryMetadataField,Long> {

    @Query(value = "from CategoryMetadataField")
    List<CategoryMetadataField> findAllMetadataFields(Pageable pageable);
}
