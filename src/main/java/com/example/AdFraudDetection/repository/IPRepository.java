package com.example.AdFraudDetection.repository;

import com.example.AdFraudDetection.Class.IPDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPRepository extends JpaRepository<IPDetail, Long> {
}
