package com.jikkosoft.proof.distributedcache.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.beans.factory.annotation.Value;

/**
 * Configuración de Redis para la aplicación.
 * 
 * Define los beans necesarios para la conexión y operación con Redis,
 * incluyendo la fábrica de conexiones y el template de Redis.
 */
@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    /**
     * Crea y configura la fábrica de conexiones a Redis usando Lettuce.
     *
     * @return RedisConnectionFactory instancia de la fábrica de conexiones.
     */    
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        LettuceConnectionFactory factory = new LettuceConnectionFactory(redisHost, redisPort);
        return factory;
    }

    /**
     * Crea y configura el RedisTemplate para operaciones con Redis.
     *
     * @param redisConnectionFactory Fábrica de conexiones a Redis.
     * @return RedisTemplate para operaciones de clave-valor.
     */    
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}
