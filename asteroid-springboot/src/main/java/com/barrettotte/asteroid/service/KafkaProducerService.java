package com.barrettotte.asteroid.service;

import com.barrettotte.asteroid.model.Asteroid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerService.class);

    @Value("${asteroid-topic}")
    private String asteroidTopic;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendAsteroid(Asteroid asteroid) {
        LOGGER.info(String.format("Asteroid sent to kafka -> %s", asteroid.toString()));
        kafkaTemplate.send(asteroidTopic, asteroid.toString());
    }
}
