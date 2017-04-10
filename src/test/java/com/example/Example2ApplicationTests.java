package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class) //SpringRunner extends SpringJUnit4ClassRunner OR @RunWith(SpringJUnit4ClassRunner.class)

//@SpringApplicationConfiguration(classes = Example2Application.class) -> This class is not found in spring boot 1.5
@SpringBootTest
public class Example2ApplicationTests {

	@Test
	public void contextLoads() {
	}

}
