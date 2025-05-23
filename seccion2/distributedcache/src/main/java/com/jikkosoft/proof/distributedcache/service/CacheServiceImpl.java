package com.jikkosoft.proof.distributedcache.service;

import com.jikkosoft.proof.distributedcache.repository.CacheRepository;
import org.springframework.stereotype.Service;

@Service
public class CacheServiceImpl implements CacheService {

    private final CacheRepository repository;

    public CacheServiceImpl(CacheRepository repository) {
        this.repository = repository;
    }

    @Override
    public void store(String key, Object value, long ttlSeconds) {
        repository.save(key, value, ttlSeconds);
    }

    @Override
    public Object retrieve(String key) {
        return repository.find(key);
    }

    @Override
    public void invalidate(String key) {
        repository.delete(key);
    }
}
