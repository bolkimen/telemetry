package com.blogspot.mvnblogbuild.telemetry.kafkatest.service;

import com.blogspot.mvnblogbuild.telemetry.kafkatest.dto.DeviceDataDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class StarshipServiceImpl implements StarshipService {

    private Logger logger = LoggerFactory.getLogger(StarshipServiceImpl.class);

    private final KafkaTemplate<Long, DeviceDataDTO> kafkaStarshipTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public StarshipServiceImpl(KafkaTemplate<Long, DeviceDataDTO> kafkaStarshipTemplate,
                               ObjectMapper objectMapper) {
        this.kafkaStarshipTemplate = kafkaStarshipTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void send(DeviceDataDTO dto) {
        kafkaStarshipTemplate.send("server.starship", dto);
    }

    @Override
    @KafkaListener(id = "Starship", topics = {"server.starship"}, containerFactory = "singleFactory")
    public void consume(DeviceDataDTO dto) {
        logger.info("=> consumed {}", writeValueAsString(dto));
    }

    private String writeValueAsString(DeviceDataDTO dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Writing value to JSON failed: " + dto.toString());
        }
    }
}
