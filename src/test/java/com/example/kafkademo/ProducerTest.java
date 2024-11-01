package com.example.kafkademo;


import com.example.kafkademo.partitioner.MyPartitioner;
import com.example.kafkademo.serialization.Encoder;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Properties;


public class ProducerTest {
    private KafkaProducer<String, User> kafkaProducer;
    private MyPartitioner myPartitioner;

    @Before
    public void setup() {
        kafkaProducer = new KafkaProducer<>(setProperties());
        myPartitioner = Mockito.mock(MyPartitioner.class);
    }

    @Test
    public void testSend() {
//        kafkaProducer.send(new ProducerRecord<>("first", value));

        try {
            for (int i = 0; i < 1; i++) {
                User user = new User(i, "tom" + i, 22 + 1);

                kafkaProducer.send(new ProducerRecord<>("demo", user), (recordMetadata, e) -> {
                    if(e == null) {
                        System.out.println("值: "+ user + ", 主题: " + recordMetadata.topic() + ", 分区: "+ recordMetadata.partition());
                    } else {
                        e.printStackTrace();
                    }
                });
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        kafkaProducer.close();
    }

    public Properties setProperties() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.66.129:9092");
        //StringSerializer.class.getName(): "org.apache.kafka.common.serialization.StringSerializer"
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, Encoder.class.getName());
        //properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "com.example.kafkademo.partitioner.MyPartitioner");

        return properties;
    }
}
