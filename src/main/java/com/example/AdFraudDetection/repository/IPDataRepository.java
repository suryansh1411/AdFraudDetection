package com.example.AdFraudDetection.repository;

import com.example.AdFraudDetection.Class.IPData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPDataRepository extends JpaRepository<IPData, Long> {

    IPData findByIpAddress(String ipAddress);
}
