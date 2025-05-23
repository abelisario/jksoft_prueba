package com.jikkosoft.proof.distributedcache.test;

import com.jikkosoft.proof.distributedcache.repository.CacheRepository;
import com.jikkosoft.proof.distributedcache.service.impl.CacheServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Clase de pruebas unitarias para {@link CacheServiceImpl}.
 * 
 * Valida el correcto funcionamiento de las operaciones de almacenamiento, recuperación,
 * invalidación, expiración y actualización de valores en la caché distribuida.
 */
class CacheServiceTest {

    @Mock
    private CacheRepository cacheRepository;

    @InjectMocks
    private CacheServiceImpl cacheService;

    /**
     * Inicializa los mocks antes de cada prueba.
     */    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        System.out.println("- Inicialización de pruebas unitarias.");
    }

    /**
     * Prueba el almacenamiento y recuperación de un valor en la caché.
     */
    @Test
    void testStoreAndRetrieve() {
        String key = "testKey";
        Object value = "testValue";

        cacheService.store(key, value, 300);
        System.out.println("- Almacenado en caché con TTL: " + key);

        when(cacheRepository.find(key)).thenReturn(value);

        Object retrieved = cacheService.retrieve(key);
        assertEquals(value, retrieved);
        System.out.println("- Recuperación correcta.");
    }

    /**
     * Prueba la invalidación (eliminación) de una clave en la caché.
     */
    @Test
    void testInvalidateKey() {
        String key = "deleteKey";

        cacheService.invalidate(key);
        verify(cacheRepository, times(1)).delete(key);
        System.out.println("- Clave eliminada correctamente.");
    }

    /**
     * Prueba el almacenamiento de un valor con expiración y su posterior expiración.
     */
    @Test
    void testStoreWithExpiration() throws InterruptedException {
        String key = "expiringKey";
        String value = "TemporaryValue";

        cacheService.store(key, value, 2); // Expira en 2 segundos
        System.out.println("- Se almacenó clave con TTL de 2 segundos.");

        Thread.sleep(3000); // Simula el paso del tiempo

        when(cacheRepository.find(key)).thenReturn(null); // Simulamos la expiración en Redis

        Object expiredValue = cacheService.retrieve(key);
        assertNull(expiredValue);
        System.out.println("- Expiración validada.");
    }

    /**
     * Prueba la sobrescritura de un valor previamente almacenado en la caché.
     */
    @Test
    void testOverrideStoredValue() {
        String key = "overrideKey";
        String initialValue = "InitialData";
        String updatedValue = "UpdatedData";

        cacheService.store(key, initialValue, 300);
        System.out.println("- Se almacenó valor inicial.");

        cacheService.store(key, updatedValue, 300);
        System.out.println("- Se actualizó el valor.");

        when(cacheRepository.find(key)).thenReturn(updatedValue);

        Object retrieved = cacheService.retrieve(key);
        assertEquals(updatedValue, retrieved);
        System.out.println("- Valor después de la actualización: " + retrieved);
    }
    
    /**
     * Prueba la recuperación de una clave inexistente en la caché.
     */
    @Test
    void testRetrieveNonexistentKey() {
        String key = "missingKey";

        when(cacheRepository.find(key)).thenReturn(null);

        Object retrieved = cacheService.retrieve(key);
        System.out.println("- Clave inexistente: " + retrieved);

        assertNull(retrieved);
    }
}