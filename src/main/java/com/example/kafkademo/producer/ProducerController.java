package com.example.kafkademo.producer;


import com.example.kafkademo.partitioner.MyPartitioner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.CompletableFuture;


@RestController
public class ProducerController {
//    private final KafkaTemplate<String, String> kafkaTemplate;
//    private final MyPartitioner myPartitioner;
//
//    @Autowired
//    public ProducerController(KafkaTemplate<String, String> kafkaTemplate, MyPartitioner myPartitioner) {
//        this.kafkaTemplate = kafkaTemplate;
//        this.myPartitioner = myPartitioner;
//    }
//
//    @GetMapping("/produce/{value}")
//    public String producer(@PathVariable("value") String value) {
//        try {
//            CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send("first", 1, "", value);
//            future.whenComplete((res, ex) -> {
//                if (ex == null) {
//                    System.out.println("发送成功: " + res.getProducerRecord());
//                } else {
//                    System.out.println("发送失败");
//                }
//            });
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        return "ok";
//    }

}
