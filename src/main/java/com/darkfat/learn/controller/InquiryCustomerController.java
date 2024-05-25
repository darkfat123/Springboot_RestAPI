package com.darkfat.learn.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.darkfat.learn.entity.CustomerInfo;
import com.darkfat.learn.reposity.CustomerInfoRepository;
import com.darkfat.learn.schema.request.CustomerRequest;
import com.darkfat.learn.schema.response.CustomerResponse;
import com.darkfat.utils.DateUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/")
public class InquiryCustomerController {
    private final Logger logger = LoggerFactory.getLogger(InquiryCustomerController.class);

    @Autowired
    CustomerInfoRepository customerInfoRepository;

    @Transactional
    @PostMapping("/inquiryCustomerInfoByFirstname")
    public ResponseEntity<CustomerResponse> inquiryCustomerInfoByFirstname(
            @RequestBody CustomerRequest customerRequest) {
        CustomerResponse response = new CustomerResponse();
        try {
            List<CustomerInfo> existingCustomers = customerInfoRepository.findByFirstnameAndLastname(customerRequest.getFirstname(),customerRequest.getLastname());
            logger.debug(customerRequest.getFirstname());
            if (!existingCustomers.isEmpty()) {
                CustomerInfo existingCustomer = existingCustomers.get(0); 
                logger.debug("existingCustomer: " + existingCustomer);
                response.setResponse("Success");
                response.setFirstname(existingCustomer.getFirstname());
                response.setLastname(existingCustomer.getLastname());
                response.setEmail(existingCustomer.getEmail());
                response.setTelephone(existingCustomer.getTelephone());
                response.setGender(existingCustomer.getGender());
                response.setRecordCreatedDate(DateUtils.format(existingCustomer.getRecordCreatedDate()));
                response.setRecordCreatedDate(DateUtils.format(existingCustomer.getRecordUpdatedDate()));

                return ResponseEntity.ok(response);
            } else {
                response.setResponse("Data Not Found");
                return ResponseEntity.status(404).body(response);
            }

        } catch (Exception e) {
            response.setResponse("Error: " + e.getMessage());
            return ResponseEntity.status(400).body(response);
        }

    }
}
