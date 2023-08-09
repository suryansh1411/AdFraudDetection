package com.example.AdFraudDetection.Configuration;

import com.example.AdFraudDetection.Class.IPData;
import com.example.AdFraudDetection.repository.IPDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.abs;

@Configuration
@Component
public class DataInitialise {


    @Autowired
    private IPDataRepository ipDataRepo;

    private boolean dataExists=false;

//    @Bean("DataInitialisation")
//    @Scheduled(fixedRate = 10000)
    public void addData()
    {
        Random random = new Random();
        int fraudInt = abs(random.nextInt()%10)+1;


        List ipDataList= new ArrayList();

        for(int i=0; i<256; i++)
        {
            if(i%fraudInt==0)
            {
                ipDataList.add(new IPData("0.0.0."+String.valueOf(i), true, "India"));
            }
            else {
                ipDataList.add(new IPData("0.0.0."+String.valueOf(i), false, "India"));
            }
        }

        ipDataRepo.saveAll(ipDataList);
        System.out.println("Data Initialised!");
        System.out.println(fraudInt);
    }

}
