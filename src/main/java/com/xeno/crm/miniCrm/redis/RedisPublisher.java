package com.xeno.crm.miniCrm.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class RedisPublisher {

    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    public RedisPublisher(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = new ObjectMapper();
    }

    public void publishDeliveryUpdates(List<UUID> logIds, List<String> statuses) {
        if (logIds.size() != statuses.size()) {
            throw new IllegalArgumentException("logIds and statuses must match in size.");
        }

        Map<String, String> payload = new HashMap<>();
        for (int i = 0; i < logIds.size(); i++) {
            payload.put(logIds.get(i).toString(), statuses.get(i));
        }

        try {
            String message = objectMapper.writeValueAsString(payload);
            redisTemplate.convertAndSend("deliveryUpdates", message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize delivery updates", e);
        }
    }
}
