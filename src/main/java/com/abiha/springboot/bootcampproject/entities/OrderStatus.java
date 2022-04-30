
package com.abiha.springboot.bootcampproject.entities;

import com.abiha.springboot.bootcampproject.enums.Status;
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
    private Status fromStatus;

    @Enumerated(EnumType.STRING)
    private Status toStatus;

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

    public Status getFromStatus() {
        return fromStatus;
    }

    public void setFromStatus(Status fromStatus) {
        this.fromStatus = fromStatus;
    }

    public Status getToStatus() {
        return toStatus;
    }

    public void setToStatus(Status toStatus) {
        this.toStatus = toStatus;
    }

    public String getTransitionNotesComments() {
        return transitionNotesComments;
    }

    public void setTransitionNotesComments(String transitionNotesComments) {
        this.transitionNotesComments = transitionNotesComments;
    }
}




