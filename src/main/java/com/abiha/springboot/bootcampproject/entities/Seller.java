package com.abiha.springboot.bootcampproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Seller {

    @Id
    @Column(name = "user_id")
    private Long id;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;


    @Column(unique = true)
    private String gst;

    @Column(unique = true)
    private String companyName;

    private String companyContact;

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(String companyContact) {
        this.companyContact = companyContact;
    }
}
