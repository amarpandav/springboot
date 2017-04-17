package com.example;

import com.example.config.Profiles;
import com.example.testdata.TestDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

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

    @Autowired
    private Environment env;

    @Autowired
    TestDataService testDataCreator;

	public static void main(String[] args) {
	    //Bootstrap the application
		//SpringApplication.run(Application.class, args);

        //When i used DatabaseManagerSwing I got java.awt.HeadlessException hence using SpringApplicationBuilder
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
        builder.headless(false);
        ConfigurableApplicationContext context = builder.run(args);
	}

    @PostConstruct
    public void getDbManager(){
        if (env.acceptsProfiles(Profiles.DEVELOPMENT)) {
            testDataCreator.createTestData();
            DatabaseManagerSwing.main(
                    new String[] { "--url", "jdbc:hsqldb:mem:application", "--user", "sa", "--password", ""});

        }
    }
}
