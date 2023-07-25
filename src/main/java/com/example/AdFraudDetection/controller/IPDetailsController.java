package com.example.AdFraudDetection.controller;

import com.example.AdFraudDetection.Class.IPData;
import com.example.AdFraudDetection.Class.IPDetail;
import com.example.AdFraudDetection.repository.IPDataRepository;
import com.example.AdFraudDetection.repository.IPRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.constant.ConstantDescs.NULL;

@RestController
public class IPDetailsController {

    @Autowired
    private IPRepository ipRepo;
    @Autowired
    private IPDataRepository ipDataRepo;


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
        IPData ipdata= ipDataRepo.findByIpAddress(ipAddr);
        if(ipdata != null) {
            IPDetail newIpDetail = new IPDetail(ipAddr, ipdata.isFraud(), ipdata.getCountry());
            ipRepo.save(newIpDetail);
            return newIpDetail;
        }
        return null;
    }
}
