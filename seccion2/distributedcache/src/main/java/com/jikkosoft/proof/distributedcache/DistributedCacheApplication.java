package com.jikkosoft.proof.distributedcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Spring Boot para el sistema de caché distribuida.
 * 
 * Esta clase contiene el método main que inicia la aplicación.
 */
@SpringBootApplication
public class DistributedCacheApplication {

    /**
     * Método principal que inicia la aplicación Spring Boot.
     *
     * @param args Argumentos de línea de comandos.
     */
	public static void main(String[] args) {
		SpringApplication.run(DistributedCacheApplication.class, args);
	}

}
