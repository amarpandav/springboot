package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriverBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

/**
 * Testing a running application
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SeleniumTest {

    @Autowired
    private WebApplicationContext webAppContext;

    private MockMvc mockMvc;

    private static WebDriver webDriver;

    @FindBy(className = "pageTitle")
    private WebElement pageTitleHomePage;

    @FindBy(name = "username")
    private WebElement username;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(tagName = "form")
    private WebElement form;

    @FindBy(className = "pageTitle")
    private WebElement pageTitleAfterLogin;

    private static String BASE_URL = "http://localhost:8080";


    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webAppContext)
                .apply(springSecurity())
                .build();

        //browser = new RemoteWebDriver(getRemoteUrl(), getDesiredCapabilities());
        webDriver = MockMvcHtmlUnitDriverBuilder.mockMvcSetup(mockMvc).build();

        webDriver.manage().timeouts()
                .implicitlyWait(10, TimeUnit.SECONDS);

        PageFactory.initElements(webDriver, this);
    }

    @After
    public void destroy() {
        webDriver.quit();
    }

    @Test
    public void testHomePage() {
        webDriver.get(BASE_URL);

        assertThat(pageTitleHomePage.getText()).contains("Home page");

        //browser.findElementByName("author").sendKeys("BOOK AUTHOR");
    }

    @Test
    public void testLogin() {
        webDriver.get(BASE_URL + "/login");

        String username = "amar";
        this.username.sendKeys(username);
        password.sendKeys("amar123");
        form.submit();

        assertThat(pageTitleAfterLogin.getText()).isEqualTo("Admin page - Hey " + username);
        //assertThat(webDriver.findElement(By.className("pageTitle")).getText()).isEqualTo("Admin page - Hey "+username);
    }
}
