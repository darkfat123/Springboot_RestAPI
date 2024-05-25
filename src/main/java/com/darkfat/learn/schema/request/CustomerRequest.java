package com.darkfat.learn.schema.request;

import lombok.Data;

@Data
public class CustomerRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String telephone;
    private String gender;
    private String record_created_date;
}

