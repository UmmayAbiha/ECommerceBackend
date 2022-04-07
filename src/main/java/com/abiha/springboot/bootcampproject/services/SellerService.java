package com.abiha.springboot.bootcampproject.services;

import com.abiha.springboot.bootcampproject.dto.SellerDto;
import com.abiha.springboot.bootcampproject.exception.BadRequestException;
import com.abiha.springboot.bootcampproject.model.Seller;
import com.abiha.springboot.bootcampproject.model.User;
import com.abiha.springboot.bootcampproject.repos.SellerRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    @Autowired
    private SellerRepo sellerRepo;

    @Autowired
    private ModelMapper modelMapper;


    public Seller registerSeller(SellerDto sellerDto)
    {
        User user = modelMapper.map(sellerDto, User.class);
        Seller seller = modelMapper.map(sellerDto, Seller.class);
        seller.setUser(user);

        if (! sellerDto.getPassword().equals(sellerDto.getConfirmPassword())) {
            throw new BadRequestException("Passwords not matched!");
        }
        return sellerRepo.save(seller);
    }

}
