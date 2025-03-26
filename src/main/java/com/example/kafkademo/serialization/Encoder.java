package com.example.kafkademo.serialization;

import com.alibaba.fastjson.JSON;

import com.example.kafkademo.pojo.User;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;

public class Encoder implements Serializer<User> {

    @Override
    public byte[] serialize(String s, User data) {
        return JSON.toJSON(data).toString().getBytes(StandardCharsets.UTF_8);
    }

}
