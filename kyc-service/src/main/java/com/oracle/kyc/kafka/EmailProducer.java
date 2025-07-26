package com.oracle.kyc.kafka;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.oracle.kafka.model.EmailRequest; 

@Component
public class EmailProducer {

    private static final String TOPIC = "email-topic";

    @Autowired
    private KafkaTemplate<String, EmailRequest> kafkaTemplate;

    public void sendEmail(EmailRequest request) {
        ProducerRecord<String, EmailRequest> record = new ProducerRecord<>(TOPIC, request);
        
        // 🟡 Override type with consumer-side class
        record.headers().add(new RecordHeader("__TypeId__", "com.oracle.kafka.model.EmailRequest".getBytes()));

        kafkaTemplate.send(record);
    }
}
