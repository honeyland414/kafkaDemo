package com.example.kafkademo.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

public class Consumer {
//    public void testConsumer() {
//        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(setProperties());
//        ArrayList<String> consumerGroup1 = new ArrayList<>();
//        consumerGroup1.add("first");
//        kafkaConsumer.subscribe(consumerGroup1);
//
//        while (true) {
//            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofSeconds(1));
//            for (ConsumerRecord<String, String> record : records) {
//                System.out.println(records);
//            }
//        }
//    }
//
//    public Properties setProperties() {
//        Properties properties = new Properties();
//        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.88.128:9092");
//        //StringSerializer.class.getName(): "org.apache.kafka.common.serialization.StringSerializer"
//        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer1");
//        //properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "com.example.kafkademo.partitioner.MyPartitioner");
//
//        return properties;
//    }
}
