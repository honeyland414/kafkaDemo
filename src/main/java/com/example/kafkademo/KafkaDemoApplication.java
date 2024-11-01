package com.example.kafkademo;

import com.example.kafkademo.pojo.User;
import com.example.kafkademo.serialization.Encoder;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.Properties;

@SpringBootApplication
public class KafkaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaDemoApplication.class, args);

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.66.129:9092");
        //StringSerializer.class.getName(): "org.apache.kafka.common.serialization.StringSerializer"
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, Encoder.class.getName());
        //properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "com.example.kafkademo.partitioner.MyPartitioner");

        KafkaProducer<String, User> kafkaProducer = new KafkaProducer<>(properties);

        String value = "hello: " + new Date(System.currentTimeMillis());
//        kafkaProducer.send(new ProducerRecord<>("first", value));

        try {
            for (int i = 0; i < 2; i++) {
                User user = new User(i, "tom" + i, 22 + 1);

                kafkaProducer.send(new ProducerRecord<>("demo1", user), (recordMetadata, e) -> {
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


}
