package com.abiha.springboot.bootcampproject.entities;

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

    public long getTokenid() {
        return tokenid;
    }

    public void setTokenid(long tokenid) {
        this.tokenid = tokenid;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(User userEntity) {
        this.userEntity = userEntity;
    }

    public String getActivationToken() {
        return activationToken;
    }


    public void setActivationToken(String activationToken) {
        this.activationToken = activationToken;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

}