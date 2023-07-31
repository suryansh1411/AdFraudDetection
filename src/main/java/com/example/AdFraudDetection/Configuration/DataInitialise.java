package com.example.AdFraudDetection.Configuration;

import com.example.AdFraudDetection.Class.IPData;
import com.example.AdFraudDetection.Class.IPDetail;
import com.example.AdFraudDetection.repository.IPDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class DataInitialise implements CommandLineRunner {

    @Autowired
    private IPDataRepository ipDataRepo;
    @Override
    public void run(String... args)
    {
        List ipDataList= new ArrayList();
        for(int i=0; i<256; i++)
        {
            if(i%8==0)
            {
                ipDataList.add(new IPData("0.0.0."+String.valueOf(i), true, "India"));
            }
            else {
                ipDataList.add(new IPData("0.0.0."+String.valueOf(i), false, "India"));
            }
        }

        ipDataRepo.saveAll(ipDataList);
    }


}
