package com.example.AdFraudDetection.Class;

import jakarta.persistence.*;
import jakarta.persistence.Id;


@Entity(name = "ipdetails")
@Table(name = "ipdetails")
public class IPDetail {
    @Id
    @SequenceGenerator(
            name = "ip_seq",
            sequenceName = "ip_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ip_seq"
    )
    protected Long id;

    @ManyToOne // Many books can be written by one author
    @JoinColumn(name = "ipData_id")
    protected IPData ipData;

    protected String accept_encoding;
    protected String connection;
    protected String user_agent;

    public IPData getIpData() {
        return ipData;
    }

    public void setIpData(IPData ipData) {
        this.ipData = ipData;
    }

    public Long getId() {
        return id;
    }

    public String getAccept_encoding() {
        return accept_encoding;
    }

    public void setAccept_encoding(String accept_encoding) {
        this.accept_encoding = accept_encoding;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getUser_agent() {
        return user_agent;
    }

    public void setUser_agent(String user_agent) {
        this.user_agent = user_agent;
    }
}
