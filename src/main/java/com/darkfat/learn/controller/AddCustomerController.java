package com.darkfat.learn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.darkfat.learn.entity.CustomerInfo;
import com.darkfat.learn.reposity.CustomerInfoRepository;
import com.darkfat.learn.schema.request.CustomerRequest;
import com.darkfat.learn.schema.response.CustomerResponse;
import com.darkfat.utils.DateUtils;

import java.util.Date;

@RestController
@RequestMapping("/")
public class AddCustomerController {
    @SuppressWarnings("unused")
    private final Logger logger = LoggerFactory.getLogger(AddCustomerController.class); 
    
    @Autowired
    private CustomerInfoRepository customerInfoRepository;
    
    @PostMapping("addCustomer")
    public ResponseEntity<CustomerResponse> addCustomer(@RequestBody CustomerRequest customerRequest) {
        CustomerResponse response = new CustomerResponse();
        try {
            CustomerInfo newCustomer = new CustomerInfo();
            newCustomer.setFirstname(customerRequest.getFirstname());
            newCustomer.setLastname(customerRequest.getLastname());
            newCustomer.setEmail(customerRequest.getEmail());
            newCustomer.setTelephone(customerRequest.getTelephone());
            newCustomer.setGender(customerRequest.getGender());
            newCustomer.setRecordCreatedDate(new Date());
            newCustomer.setRecordUpdatedDate(new Date());

            customerInfoRepository.save(newCustomer);

            response.setResponse("Success");
            response.setFirstname(customerRequest.getFirstname());
            response.setLastname(customerRequest.getLastname());
            response.setEmail(customerRequest.getEmail());
            response.setTelephone(customerRequest.getTelephone());
            response.setGender(customerRequest.getGender());
            response.setRecordCreatedDate(DateUtils.format(newCustomer.getRecordCreatedDate()));
            response.setRecordUpdatedDate(DateUtils.format(newCustomer.getRecordUpdatedDate()));

            logger.debug("Date Format: " + DateUtils.format(newCustomer.getRecordCreatedDate()));
            logger.debug("Date: " + newCustomer.getRecordCreatedDate());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponse("Error");
            return ResponseEntity.status(500).body(response);
        }
    }


}
