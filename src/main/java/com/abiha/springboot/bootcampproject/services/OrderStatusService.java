package com.abiha.springboot.bootcampproject.services;

import com.abiha.springboot.bootcampproject.entities.OrderProduct;
import com.abiha.springboot.bootcampproject.entities.OrderStatus;
import com.abiha.springboot.bootcampproject.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderStatusService {

    public List<OrderStatus> saveAll(List<OrderProduct> orderProductList){

        List<OrderStatus> orderStatusList = orderProductList.stream().map(orderProduct -> {
            OrderStatus orderStatus = new OrderStatus();
            orderStatus.setFromStatus(Status.ORDER_PLACED);
            orderStatus.setOrderProduct(orderProduct);
            return orderStatus;
        }).collect(Collectors.toList());

        return orderStatusList;

    }


}
