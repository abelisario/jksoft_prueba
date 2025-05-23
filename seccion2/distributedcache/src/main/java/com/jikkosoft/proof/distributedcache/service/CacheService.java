package com.jikkosoft.proof.distributedcache.service;

/**
 * Interfaz para servicios de caché distribuida.
 * Proporciona métodos para almacenar, recuperar e invalidar elementos en la caché.
 */
public interface CacheService {
    /**
     * Almacena un valor en la caché con una clave y un tiempo de vida (TTL) en segundos.
     *
     * @param key        Clave única para identificar el valor en la caché.
     * @param value      Valor a almacenar en la caché.
     * @param ttlSeconds Tiempo de vida del valor en la caché, en segundos.
     */    
    void store(String key, Object value, long ttlSeconds);

     /**
     * Recupera un valor de la caché usando la clave proporcionada.
     *
     * @param key Clave del valor a recuperar.
     * @return El valor almacenado o null si no existe o ha expirado.
     */   
    Object retrieve(String key);

    /**
     * Invalida (elimina) un valor de la caché usando la clave proporcionada.
     *
     * @param key Clave del valor a invalidar.
     */    
    void invalidate(String key);
}
