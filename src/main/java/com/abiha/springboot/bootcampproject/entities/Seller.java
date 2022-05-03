package com.abiha.springboot.bootcampproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seller implements Serializable {

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


    public Seller(User user, String gst, String companyName, String companyContact) {
        this.user = user;
        this.gst = gst;
        this.companyName = companyName;
        this.companyContact = companyContact;
    }
}
