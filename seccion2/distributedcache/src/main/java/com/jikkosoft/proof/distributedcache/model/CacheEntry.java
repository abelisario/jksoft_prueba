package com.jikkosoft.proof.distributedcache.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CacheEntry implements Serializable {
    private String key;
    private Object value;
}