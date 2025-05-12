package com.xeno.crm.miniCrm.redis;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xeno.crm.miniCrm.model.enums.CampaginStatus;
import com.xeno.crm.miniCrm.model.CommunicationLog;
import com.xeno.crm.miniCrm.repository.CommunicationLogRepo;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public class RedisSubscriber implements MessageListener {

    private final CommunicationLogRepo communicationLogRepo;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public RedisSubscriber(CommunicationLogRepo communicationLogRepo) {
        this.communicationLogRepo = communicationLogRepo;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String json = new String(message.getBody(), StandardCharsets.UTF_8);
            Map<String, String> updates = objectMapper.readValue(json, new TypeReference<>() {});
            for (Map.Entry<String, String> entry : updates.entrySet()) {
                UUID logId = UUID.fromString(entry.getKey());
                CampaginStatus status = CampaginStatus.valueOf(entry.getValue());

                Optional<CommunicationLog> optionalLog = communicationLogRepo.findById(logId);
                optionalLog.ifPresent(log -> {
                    log.setStatus(status);
                    communicationLogRepo.save(log);
                });
            }
        } catch (Exception e) {
            System.out.println("Error in Redis Subscriber class----->");
            System.out.println("Error in receiving message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
