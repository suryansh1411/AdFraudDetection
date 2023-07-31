package com.example.AdFraudDetection.Class;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.context.annotation.Bean;


@Entity
@Table(name = "ip_data")
public class IPData extends IPDetail{

    public IPData (){};
    public IPData(String ipAddress, boolean fraud, String country)
    {
        this.ipAddress = ipAddress;
        this.fraud = fraud;
        this.country = country;
    }


}
