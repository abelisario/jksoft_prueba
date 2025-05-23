package com.jikkosoft.proof.distributedcache.controller;

import com.jikkosoft.proof.distributedcache.service.CacheService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache")
public class CacheController {

    private final CacheService service;

    public CacheController(CacheService service) {
        this.service = service;
    }

    @PostMapping("/{key}")
    public void store(@PathVariable String key, @RequestBody Object value, @RequestParam(required = false, defaultValue = "300") long ttlSeconds) {
        service.store(key, value, ttlSeconds);
    }

    @GetMapping("/{key}")
    public Object retrieve(@PathVariable String key) {
        return service.retrieve(key);
    }

    @DeleteMapping("/{key}")
    public void invalidate(@PathVariable String key) {
        service.invalidate(key);
    }
}