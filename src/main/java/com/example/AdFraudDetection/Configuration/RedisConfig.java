package com.example.AdFraudDetection.Configuration;

import com.example.AdFraudDetection.Class.IPData;
import com.example.AdFraudDetection.repository.IPDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.scheduling.annotation.Scheduled;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Jedis;
import com.google.gson.Gson;

import javax.xml.crypto.Data;

//
@Configuration
@EnableRedisRepositories
public class RedisConfig{

    @Autowired
    private IPDataRepository ipDataRepo;

    @Autowired
    private DataInitialise dataInitialise;
    private Jedis jedis;

    public String convertObjectToJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    @Bean
    public Jedis connectionFactory()
    {
//        localhost
//        JedisPool pool = new JedisPool("localhost", 6379);
//        docker
//        JedisPool pool = new JedisPool("redis", 6379);
//        kubernetes
        System.out.println("hi");
        JedisPool pool = new JedisPool("redis-service", 6379);
        jedis= pool.getResource();


        return jedis;
    }

    @Scheduled(fixedRate = 30000)
    public void redisRefresh()
    {
        dataInitialise.addData();

        for(IPData ipData : ipDataRepo.findAll())
        {
            String strIPData = convertObjectToJson(ipData);
            jedis.set(ipData.getIpAddress(), strIPData);
        }
        System.out.println("Redis Reloaded!");
    }
}