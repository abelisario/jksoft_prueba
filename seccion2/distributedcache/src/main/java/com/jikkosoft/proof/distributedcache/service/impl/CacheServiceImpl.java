package com.jikkosoft.proof.distributedcache.service.impl;

import com.jikkosoft.proof.distributedcache.repository.CacheRepository;
import com.jikkosoft.proof.distributedcache.service.CacheService;

import org.springframework.stereotype.Service;

/**
 * Implementación de la interfaz {@link CacheService} que utiliza un {@link CacheRepository}
 * para gestionar operaciones de almacenamiento, recuperación e invalidación en la caché distribuida.
 */
@Service
public class CacheServiceImpl implements CacheService {

    private final CacheRepository repository;

     /**
     * Constructor que inyecta el repositorio de caché.
     *
     * @param repository Repositorio encargado de la persistencia en caché.
     */
    public CacheServiceImpl(CacheRepository repository) {
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void store(String key, Object value, long ttlSeconds) {
        repository.save(key, value, ttlSeconds);
    }

    /**
     * {@inheritDoc}
     */    
    @Override
    public Object retrieve(String key) {
        return repository.find(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void invalidate(String key) {
        repository.delete(key);
    }
}
