package com.example.AdFraudDetection.Class;

import jakarta.persistence.*;
import jakarta.persistence.Id;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ipdetails")
public class IPDetail {

    @Id
    @SequenceGenerator(
            name = "ip_sequence",
            sequenceName = "ip_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ip_sequence"
    )
    protected Long id;
    protected String ipAddress;
    protected boolean fraud;
    protected String country;

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
