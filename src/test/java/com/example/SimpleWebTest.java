package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Testing a running application
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleWebTest {

    @Test
    public void setupMockMvc() {
        RestTemplate rest = new RestTemplate();
        Object weather = rest.getForObject(
                "http://api.openweathermap.org/data/2.5/weather?q=zurich&units=metric&appid=9d7551bc369f7e793efe31f5798f72d9", Object.class);
        assertThat(weather).isNotNull();

    }

}
