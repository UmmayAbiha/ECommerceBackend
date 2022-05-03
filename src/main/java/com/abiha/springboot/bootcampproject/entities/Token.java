package com.abiha.springboot.bootcampproject.entities;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Data
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private long tokenid;

    private String activationToken;

    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;


    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id",referencedColumnName = "id")
    private User userEntity;

    public Token() {}

    public Token(User userEntity) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(calendar.getTime().getTime()));

        this.userEntity = userEntity;
        createdDate = new Date(calendar.getTime().getTime());
        calendar.add(Calendar.MINUTE,10);

        expiryDate = new Date(calendar.getTime().getTime());
        activationToken = UUID.randomUUID().toString();
    }
}