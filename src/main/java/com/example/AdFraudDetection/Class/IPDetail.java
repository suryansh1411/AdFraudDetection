package com.example.AdFraudDetection.Class;

import jakarta.persistence.*;
import jakarta.persistence.Id;


@Entity(name = "ipdetails")
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


}
