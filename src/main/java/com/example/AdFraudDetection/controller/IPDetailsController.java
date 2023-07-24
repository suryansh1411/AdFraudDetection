package com.example.AdFraudDetection.controller;

import com.example.AdFraudDetection.Class.IPDetail;
import com.example.AdFraudDetection.repository.IPRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IPDetailsController {

    @Autowired
    private IPRepository ipRepo;


    @GetMapping("/api")
    public String welcome()
    {
        return "hi";
    }

    @GetMapping("/api/ipDetails")
    public List<IPDetail> getAll()
    {
        return ipRepo.findAll();
    }



    @PostMapping("/api/ipDetails")
    public IPDetail addIpDetails(HttpServletRequest request)
    {
        String ipAddr = request.getHeader("ipAddr");
        String country = request.getHeader("country");

        IPDetail newIpDetail = new IPDetail(ipAddr, true, country);
        ipRepo.save(newIpDetail);

        return newIpDetail;
    }
}
