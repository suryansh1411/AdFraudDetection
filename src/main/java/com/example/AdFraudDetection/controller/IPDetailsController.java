package com.example.AdFraudDetection.controller;

import com.example.AdFraudDetection.Class.IPData;
import com.example.AdFraudDetection.Class.IPDetail;
import com.example.AdFraudDetection.repository.IPDataRepository;
import com.example.AdFraudDetection.repository.IPRepository;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.util.List;

import static java.lang.constant.ConstantDescs.NULL;

@RestController
public class IPDetailsController {

    @Autowired
    private IPRepository ipRepo;

//    @Autowired
    private static Jedis jedis;

    @Autowired
    IPDetailsController(Jedis jedis)
    {
        this.jedis = jedis;
    }

    public IPData convertJsonToObject(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, IPData.class);
    }


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
        IPData ipdata= convertJsonToObject(jedis.get(ipAddr));
        System.out.println(ipdata.getIpAddress());
//        if(ipdata != null) {
//            IPDetail newIpDetail = new IPDetail(ipAddr, ipdata.isFraud(), ipdata.getCountry());
//            ipRepo.save(newIpDetail);
//            return newIpDetail;
//        }
        return null;
    }
}
