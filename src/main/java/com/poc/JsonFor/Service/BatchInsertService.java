package com.poc.JsonFor.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.JsonFor.model.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class BatchInsertService {

    private static final String TOPIC = "benefit-events";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void publishCustomersFromJson(String filePath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Customers> customers = mapper.readValue(new File(filePath), new TypeReference<List<Customers>>() {});
            for (Customers c : customers) {
                kafkaTemplate.send(TOPIC, c);
                Thread.sleep(100); // optional throttle
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}