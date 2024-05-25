package com.darkfat.learn.schema.response;


import lombok.Data;

@Data
public class CustomerResponse {
    private String response;
    private String firstname;
    private String lastname;
    private String email;
    private String telephone;
    private String gender;
    private String recordCreatedDate;
    private String recordUpdatedDate;
}
