package com.example.kafkademo;

import com.example.kafkademo.serialization.Decoder;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Stream;

public class ConsumerTest {

    @Test
    public void testConsume() {

        KafkaConsumer<String, User> kafkaConsumer = new KafkaConsumer<>(setProperties());

        //consume all partitions
        ArrayList<String> consumerGroup1 = new ArrayList<>();
        consumerGroup1.add("demo");
        kafkaConsumer.subscribe(consumerGroup1);

//        ArrayList<TopicPartition> topicPartitions = new ArrayList<>();
//        topicPartitions.add(new TopicPartition("first", 0));
//        kafkaConsumer.assign(topicPartitions);

        while (true) {
            ConsumerRecords<String, User> records = kafkaConsumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, User> record : records) {
                System.out.println(records.partitions());
                System.err.println(record.headers());

            }
        }
    }


    public Properties setProperties() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.66.129:9092");
        //StringSerializer.class.getName(): "org.apache.kafka.common.serialization.StringDeserializer"
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        //properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, Decoder.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer1");

        return properties;
    }
}
