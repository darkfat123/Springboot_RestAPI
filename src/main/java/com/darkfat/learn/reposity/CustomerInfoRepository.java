package com.darkfat.learn.reposity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.darkfat.learn.entity.CustomerInfo;

public interface CustomerInfoRepository extends JpaRepository<CustomerInfo, Long> {
    List<CustomerInfo> findByFirstnameAndLastname(String firstname,String lastname);
}

