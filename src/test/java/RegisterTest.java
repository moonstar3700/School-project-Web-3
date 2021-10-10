import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class RegisterTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/Controller";

    @Before
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "/Users/.../web3pers/chromedriver");
        // windows: gebruik dubbele \\ om pad aan te geven
        // hint: zoek een werkende test op van web 2 ...
        System.setProperty("webdriver.chrome.driver", "D:\\informatica cursus\\IT 2de jaar\\Web 3\\chrome driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/Groep1_17_war2/register.jsp");
    }

    /*@After
    public void clean() {
        driver.quit();
    }*/

    @Test
    public void test_Register_AllFieldsFilledInCorrectly_UserIsRegistered_and_RoleIsTrainer() {
        //submitForm("Jan", "Janssens", "jan.janssens@hotmail.com" , "1234");

        RegisterPage registerpage = PageFactory.initElements(driver, RegisterPage.class);
        registerpage.setFirstname("Jan");
        registerpage.setlastName("Janssens");
        registerpage.setEmail("jan.janssens@hotmail.com");
        registerpage.setPassword("1234");
        registerpage.register();

        assertEquals("Home",registerpage.getTitle());

        OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);
        assertTrue(overviewPage.containsString("Jan"));
        assertTrue(overviewPage.containsString("Janssens"));
        assertTrue(overviewPage.containsString("jan.janssens@hotmail.com"));
        assertTrue(overviewPage.containsString("TRAINER"));
    }


    @Test
    public void test_Register_FirstNameNotFilledIn_ErrorMessageGivenForFirstNameAndOtherFieldsValueKept(){
        //submitForm("", "Janssens", "jan.janssens@hotmail.com", "1234");

        RegisterPage registerpage = PageFactory.initElements(driver, RegisterPage.class);
        registerpage.setFirstname("");
        registerpage.setlastName("Janssens");
        registerpage.setEmail("jan.janssens@hotmail.com");
        registerpage.setPassword("1234");
        registerpage.register();

        assertEquals("Sign Up",registerpage.getTitle());

        assertTrue(registerpage.hasErrorMessage("No first name given"));
        assertTrue(registerpage.hasEmptyFirstname());
        assertTrue(registerpage.hasStickyLastName("Janssens"));
        assertTrue(registerpage.hasStickyEmail("jan.janssens@hotmail.com"));
    }

    @Test
    public void test_Register_LastNameNotFilledIn_ErrorMessageGivenForLastNameAndOtherFieldsValueKept(){
        //submitForm("Jan", "", "jan.janssens@hotmail.com", "1234");

        RegisterPage registerpage = PageFactory.initElements(driver, RegisterPage.class);
        registerpage.setFirstname("Jan");
        registerpage.setlastName("");
        registerpage.setEmail("jan.janssens@hotmail.com");
        registerpage.setPassword("1234");
        registerpage.register();

        assertEquals("Sign Up",registerpage.getTitle());

        assertTrue(registerpage.hasErrorMessage("No last name given"));
        assertTrue(registerpage.hasEmptyLastName());
        assertTrue(registerpage.hasStickyFirstName("Jan"));
        assertTrue(registerpage.hasStickyEmail("jan.janssens@hotmail.com"));
    }

    @Test
    public void test_Register_EmailNotFilledIn_ErrorMessageGivenForEmailAndOtherFieldsValueKept(){
        //submitForm("Jan", "Janssens", "", "1234");

        RegisterPage registerpage = PageFactory.initElements(driver, RegisterPage.class);
        registerpage.setFirstname("Jan");
        registerpage.setlastName("Janssens");
        registerpage.setEmail("");
        registerpage.setPassword("1234");
        registerpage.register();

        assertEquals("Sign Up",registerpage.getTitle());

        assertTrue(registerpage.hasErrorMessage("No email given"));
        assertTrue(registerpage.hasEmptyEmail());
        assertTrue(registerpage.hasStickyFirstName("Jan"));
        assertTrue(registerpage.hasStickyLastName("Janssens"));

    }


    @Test
    public void test_Register_PasswordNotFilledIn_ErrorMessageGivenForEmailAndOtherFieldsValueKept(){
        //submitForm("Jan", "Janssens", "jan.janssens@hotmail.com", "");

        RegisterPage registerpage = PageFactory.initElements(driver, RegisterPage.class);
        registerpage.setFirstname("Jan");
        registerpage.setlastName("Janssens");
        registerpage.setEmail("jan.janssens@hotmail.com");
        registerpage.setPassword("");
        registerpage.register();

        assertEquals("Sign Up",registerpage.getTitle());

        assertTrue(registerpage.hasErrorMessage("No password given"));
        assertTrue(registerpage.hasStickyFirstName("Jan"));
        assertTrue(registerpage.hasStickyLastName("Janssens"));
        assertTrue(registerpage.hasStickyEmail("jan.janssens@hotmail.com"));
    }

    @Test
    public void test_Register_UserAlreadyExists_ErrorMessageGiven(){
        //submitForm("Pieter", "Pieters", "pieter.pieters@hotmail.com", "1234");

        RegisterPage registerpage = PageFactory.initElements(driver, RegisterPage.class);
        registerpage.setFirstname("Jan");
        registerpage.setlastName("Janssens");
        registerpage.setEmail("jan.janssens@hotmail.com");
        registerpage.setPassword("1234");
        registerpage.register();

        assertEquals("Sign Up",registerpage.getTitle());

        assertTrue(registerpage.hasErrorMessage("Email already in use"));
        assertTrue(registerpage.hasStickyFirstName("Jan"));
        assertTrue(registerpage.hasStickyLastName("Janssens"));
        assertTrue(registerpage.hasStickyEmail("jan.janssens@hotmail.com"));

    }

}
