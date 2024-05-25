package com.darkfat.learn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.darkfat.learn.entity.CustomerInfo;
import com.darkfat.learn.reposity.CustomerInfoRepository;
import com.darkfat.learn.schema.request.CustomerRequest;
import com.darkfat.learn.schema.response.CustomerResponse;
import com.darkfat.utils.DateUtils;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/")
public class EditCustomerController {
    @SuppressWarnings("unused")
    private final Logger logger = LoggerFactory.getLogger(EditCustomerController.class); 
    
    @Autowired
    private CustomerInfoRepository customerInfoRepository;

    @Transactional
    @PutMapping("/editCustomer")
    public ResponseEntity<CustomerResponse> editCustomer(@RequestBody CustomerRequest customerRequest) {
        CustomerResponse response = new CustomerResponse();
        try {
            List<CustomerInfo> existingCustomers = customerInfoRepository.findByFirstnameAndLastname(customerRequest.getFirstname(),customerRequest.getLastname());

            if (!existingCustomers.isEmpty()) {
                CustomerInfo existingCustomer = existingCustomers.get(0); // Assuming you want to update the first one found
                existingCustomer.setLastname(customerRequest.getLastname());
                existingCustomer.setEmail(customerRequest.getEmail());
                existingCustomer.setTelephone(customerRequest.getTelephone());
                existingCustomer.setGender(customerRequest.getGender());
                existingCustomer.setRecordUpdatedDate(new Date()); 

                customerInfoRepository.save(existingCustomer);
                
                response.setResponse("Updated Success");
                response.setFirstname(existingCustomer.getFirstname());
                response.setLastname(existingCustomer.getLastname());
                response.setEmail(existingCustomer.getEmail());
                response.setTelephone(existingCustomer.getTelephone());
                response.setGender(existingCustomer.getGender());
                response.setRecordCreatedDate(DateUtils.format(existingCustomer.getRecordCreatedDate()));
                response.setRecordUpdatedDate(DateUtils.format(existingCustomer.getRecordUpdatedDate()));

                return ResponseEntity.ok(response);
            } else {
                response.setResponse("Data Not Found");
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponse("Error " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}
