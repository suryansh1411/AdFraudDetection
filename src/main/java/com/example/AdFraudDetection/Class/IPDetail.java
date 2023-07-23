package com.example.AdFraudDetection.Class;

import jakarta.persistence.*;
import jakarta.persistence.Id;

@Entity
@Table(name = "ipdetails")
public class IPDetail {

    @Id
    private Long id;
    private String ipAddress;
    private boolean fraud;
    private String country;

    public IPDetail(){}
    public IPDetail(String ipAddress, boolean fraud, String country) {
        this.ipAddress = ipAddress;
        this.fraud = fraud;
        this.country = country;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public boolean isFraud() {
        return fraud;
    }

    public void setFraud(boolean fraud) {
        this.fraud = fraud;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
