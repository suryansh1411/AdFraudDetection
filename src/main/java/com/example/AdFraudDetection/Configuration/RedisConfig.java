package com.example.AdFraudDetection.Configuration;

import com.example.AdFraudDetection.Class.IPData;
import com.example.AdFraudDetection.repository.IPDataRepository;
import com.example.AdFraudDetection.repository.IPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Jedis;
import com.google.gson.Gson;

//
@Configuration
@EnableRedisRepositories
public class RedisConfig {

    @Autowired
    private IPDataRepository ipDataRepo;

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
        Jedis jedis= pool.getResource();

        System.out.println(ipDataRepo.findAll().size());
        for(IPData ipData : ipDataRepo.findAll())
        {
            String strIPData = convertObjectToJson(ipData);
            jedis.set(ipData.getIpAddress(), strIPData);
        }


        return jedis;
    }
}