package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class is both a bootstrap class and a configuration class.
 *
 *
 */

//Enable component-scanning and auto-configuration
@SpringBootApplication
//If we remove above annotation, we will get exception -
//Unable to start EmbeddedWebApplicationContext due to missing EmbeddedServletContainerFactory bean.
public class Application {

	public static void main(String[] args) {

	    //Bootstrap the application
		SpringApplication.run(Application.class, args);
	}
}
