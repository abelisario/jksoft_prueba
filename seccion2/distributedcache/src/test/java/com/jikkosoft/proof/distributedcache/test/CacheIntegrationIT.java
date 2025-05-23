package com.jikkosoft.proof.distributedcache.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CacheIntegrationIT {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final String testKey = "integrationKey";

    @AfterEach
    void cleanUp() {
        redisTemplate.delete(testKey);
        System.out.println("- Clave de integración eliminada.");
    }

    @Test
    void testStoreAndRetrieve() {
        String value = "Hello, Redis!";

        redisTemplate.opsForValue().set(testKey, value);
        System.out.println("- Valor almacenado en Redis.");

        Object retrieved = redisTemplate.opsForValue().get(testKey);
        assertEquals(value, retrieved);
        System.out.println("- Recuperación correcta.");
    }

    @Test
    void testExpiration() throws InterruptedException {
        String value = "Temporary Data";

        redisTemplate.opsForValue().set(testKey, value, 2, TimeUnit.SECONDS);
        System.out.println("- Almacenado con TTL de 2 segundos.");

        Thread.sleep(3000);
        Object expiredValue = redisTemplate.opsForValue().get(testKey);
        assertNull(expiredValue);
        System.out.println("- Expiración validada.");
    }
}
