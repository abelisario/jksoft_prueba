package com.jikkosoft.proof.distributedcache.repository;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * Repositorio encargado de interactuar con Redis para operaciones de caché.
 * Proporciona métodos para guardar, recuperar y eliminar valores en la caché distribuida.
 */
@Repository
public class CacheRepository {

    private final RedisTemplate<String, Object> redisTemplate;

     /**
     * Constructor que inyecta el template de Redis.
     *
     * @param redisTemplate Template de Redis para operaciones de valor.
     */
    public CacheRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * Guarda un valor en Redis con una clave y un tiempo de vida (TTL) en segundos.
     *
     * @param key        Clave única para el valor.
     * @param value      Valor a almacenar.
     * @param ttlSeconds Tiempo de vida en segundos.
     */    
    public void save(String key, Object value, long ttlSeconds) {
        redisTemplate.opsForValue().set(key, value, ttlSeconds, TimeUnit.SECONDS);
    }

    /**
     * Recupera un valor de Redis usando la clave proporcionada.
     *
     * @param key Clave del valor a recuperar.
     * @return El valor almacenado o null si no existe.
     */    
    public Object find(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * Elimina un valor de Redis usando la clave proporcionada.
     *
     * @param key Clave del valor a eliminar.
     */    
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}