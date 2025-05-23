package com.jikkosoft.proof.distributedcache.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * Representa una entrada en la caché distribuida.
 * 
 * Contiene la clave y el valor asociados a un elemento almacenado en la caché.
 * Implementa {@link Serializable} para permitir su transmisión o almacenamiento.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CacheEntry implements Serializable {

    /**
     * Clave única de la entrada en la caché.
     */    
    private String key;

    /**
     * Valor asociado a la clave en la caché.
     */
    private Object value;
}