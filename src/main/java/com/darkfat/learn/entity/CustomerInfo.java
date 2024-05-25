package com.darkfat.learn.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name = "customer_info")
@Data
public class CustomerInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recId;
    private String firstname;
    private String lastname;
    private String email;
    private String telephone;
    private String gender;
    private Date recordCreatedDate;
    private Date recordUpdatedDate;

}



