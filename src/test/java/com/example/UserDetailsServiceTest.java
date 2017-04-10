package com.example;

import com.example.controller.MyController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import java.util.Enumeration;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class) //SpringRunner extends SpringJUnit4ClassRunner OR @RunWith(SpringJUnit4ClassRunner.class)

//@SpringApplicationConfiguration(classes = Example2Application.class) -> This class is not found in spring boot 1.5
@SpringBootTest
public class UserDetailsServiceTest {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private WebApplicationContext webContext;

	@Test
	public void contextLoads() {
        UserDetails userDetails = userDetailsService.loadUserByUsername("amar");
        assertThat(userDetails).isNotNull();
    }

}
