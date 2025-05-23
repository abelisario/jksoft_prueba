package com.jikkosoft.proof.distributedcache.controller;

import com.jikkosoft.proof.distributedcache.service.CacheService;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para gestionar operaciones sobre la caché distribuida.
 * 
 * Expone endpoints para almacenar, recuperar e invalidar valores en la caché.
 */
@RestController
@RequestMapping("/cache")
public class CacheController {

    private final CacheService service;

    /**
     * Constructor que inyecta el servicio de caché.
     *
     * @param service Servicio encargado de la lógica de caché.
     */    
    public CacheController(CacheService service) {
        this.service = service;
    }

    /**
     * Almacena un valor en la caché con una clave y un TTL opcional.
     *
     * @param key        Clave única para el valor.
     * @param value      Valor a almacenar.
     * @param ttlSeconds Tiempo de vida en segundos (por defecto 300).
     */    
    @PostMapping("/{key}")
    public void store(@PathVariable String key, @RequestBody Object value, @RequestParam(required = false, defaultValue = "300") long ttlSeconds) {
        service.store(key, value, ttlSeconds);
    }

    /**
     * Recupera un valor de la caché usando la clave proporcionada.
     *
     * @param key Clave del valor a recuperar.
     * @return El valor almacenado o null si no existe o ha expirado.
     */
    @GetMapping("/{key}")
    public Object retrieve(@PathVariable String key) {
        return service.retrieve(key);
    }

    /**
     * Invalida (elimina) un valor de la caché usando la clave proporcionada.
     *
     * @param key Clave del valor a invalidar.
     */    
    @DeleteMapping("/{key}")
    public void invalidate(@PathVariable String key) {
        service.invalidate(key);
    }
}