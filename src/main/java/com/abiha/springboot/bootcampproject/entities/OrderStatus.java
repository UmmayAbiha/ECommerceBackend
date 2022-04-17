
package com.abiha.springboot.bootcampproject.entities;

import com.abiha.springboot.bootcampproject.enums.fromStatus;
import com.abiha.springboot.bootcampproject.enums.toStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToOne
    @MapsId
    private OrderProduct orderProduct;

    @Enumerated(EnumType.STRING)
    private com.abiha.springboot.bootcampproject.enums.fromStatus fromStatus;

    @Enumerated(EnumType.STRING)
    private com.abiha.springboot.bootcampproject.enums.toStatus toStatus;

    private String transitionNotesComments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderProduct getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(OrderProduct orderProduct) {
        this.orderProduct = orderProduct;
    }

    public com.abiha.springboot.bootcampproject.enums.fromStatus getFromStatus() {
        return fromStatus;
    }

    public void setFromStatus(com.abiha.springboot.bootcampproject.enums.fromStatus fromStatus) {
        this.fromStatus = fromStatus;
    }

    public com.abiha.springboot.bootcampproject.enums.toStatus getToStatus() {
        return toStatus;
    }

    public void setToStatus(com.abiha.springboot.bootcampproject.enums.toStatus toStatus) {
        this.toStatus = toStatus;
    }

    public String getTransitionNotesComments() {
        return transitionNotesComments;
    }

    public void setTransitionNotesComments(String transitionNotesComments) {
        this.transitionNotesComments = transitionNotesComments;
    }
}




