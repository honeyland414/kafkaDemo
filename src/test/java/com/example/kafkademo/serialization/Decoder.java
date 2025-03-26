package com.example.kafkademo.serialization;

import com.alibaba.fastjson.JSON;
import com.example.kafkademo.User;
import org.apache.kafka.common.serialization.Deserializer;

public class Decoder implements Deserializer<User> {

    @Override
    public User deserialize(String s, byte[] data) {
        return JSON.parseObject(data, User.class);
    }

}
