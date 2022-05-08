package com.abiha.springboot.bootcampproject.contollers;

import com.abiha.springboot.bootcampproject.entities.Orders;
import com.abiha.springboot.bootcampproject.enums.Status;
import com.abiha.springboot.bootcampproject.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    // customer APIs
    @PostMapping("/all-products")
    public ResponseEntity<String> addAll(){
        String message = orderService.orderAllProducts();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/partial-products")
    public ResponseEntity<String> addPartialOrder(@RequestBody List<Long> ids){
        String message = orderService.orderPartialProducts(ids);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/directly/{id}")
    public ResponseEntity<String> addDirectly(@Valid @PathVariable Long id,@RequestParam int quantity){
        String message = orderService.orderDirectly(id,quantity);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PatchMapping("/cancel/{id}")
    public ResponseEntity<String> cancel(@Valid @PathVariable Long id){
        String message = orderService.cancel(id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @PatchMapping("/return/{id}")
    public ResponseEntity<String> returnOrder(@Valid @PathVariable Long id){
        String message = orderService.returnOrder(id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @GetMapping("/view/{id}")
    public Orders view(@Valid @PathVariable Long id){
        return orderService.view(id);
    }

    @GetMapping("/view-list")
    public List<Orders> list(){
        return orderService.list();
    }


    // seller APIs
    @PatchMapping("/change-status/{id}")
    public ResponseEntity<String> changeStatus(@Valid @PathVariable Long id, @RequestParam Status fromStatus, @RequestParam Status toStatus){
        String message = orderService.changeStatus(id,fromStatus,toStatus);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }


    // admin APIs
    @GetMapping("/view-all")
    public List<Orders> listAll(){
        return orderService.listAll();
    }


    @PatchMapping("/change-order-status/{id}")
    public ResponseEntity<String> changeOrderStatus(@Valid @PathVariable Long id, @RequestParam Status fromStatus, @RequestParam Status toStatus){
        String message = orderService.changeOrderStatus(id,fromStatus,toStatus);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }


}
