package com.jikkosoft.proof.distributedcache.service;

public interface CacheService {
    void store(String key, Object value, long ttlSeconds);
    Object retrieve(String key);
    void invalidate(String key);
}
