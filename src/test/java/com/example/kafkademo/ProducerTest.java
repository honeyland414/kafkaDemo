package com.example.kafkademo;


import com.example.kafkademo.partitioner.MyPartitioner;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.Properties;


public class ProducerTest {
    private KafkaProducer<String, String> kafkaProducer;
    private MyPartitioner myPartitioner;

    @Before
    public void setup() {
        kafkaProducer = new KafkaProducer<String, String>(setProperties());
        myPartitioner = Mockito.mock(MyPartitioner.class);
    }

    @Test
    public void testSend() {
        String value = "hello: " + new Date(System.currentTimeMillis());
//        kafkaProducer.send(new ProducerRecord<>("first", value));

        try {
            kafkaProducer.send(new ProducerRecord<>("first", value), (recordMetadata, e) -> {
                if(e == null) {
                    System.out.println("值: "+ value + ", 主题: " + recordMetadata.topic() + ", 分区: "+ recordMetadata.partition());
                } else {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        kafkaProducer.close();
    }

    public Properties setProperties() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.88.128:9092");
        //StringSerializer.class.getName(): "org.apache.kafka.common.serialization.StringSerializer"
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "com.example.kafkademo.partitioner.MyPartitioner");

        return properties;
    }

    @Test
    public void testTest() {
        System.out.println("*********");
        System.out.println();
    }
}
