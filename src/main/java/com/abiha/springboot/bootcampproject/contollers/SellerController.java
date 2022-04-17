package com.abiha.springboot.bootcampproject.contollers;

import com.abiha.springboot.bootcampproject.dto.SellerDto;
import com.abiha.springboot.bootcampproject.dto.UserDto;
import com.abiha.springboot.bootcampproject.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @Transactional
    @PatchMapping("/updateProfileDetails")
    public ResponseEntity<Object> updatingSellerProfileDetails(@Valid @RequestBody SellerDto sellerDto) {
        return sellerService.updateSellerProfile(sellerDto);
    }

    @PatchMapping("/updatePassword")
    public ResponseEntity<Object> updatingSellerPassword(@Valid @RequestBody SellerDto sellerDto) {
        return sellerService.updateSellerPassword(sellerDto);
    }

    @Transactional
    @PatchMapping("/updateAddress/{id}")
    public ResponseEntity<Object> updatingSellerAddress(@Valid @PathVariable Long id, @RequestBody UserDto userDto){
        return sellerService.updateAddress(id,userDto);

    }
}
