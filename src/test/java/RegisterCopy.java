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

public class RegisterCopy {
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

        WebElement button=driver.findElement(By.id("signUp"));
        button.click();

        String title = driver.getTitle();
        assertEquals("Home",title);

        driver.get("http://localhost:8080/Groep1_17_war2/Controller?command=Overview");

        ArrayList<WebElement> listItems=(ArrayList<WebElement>) driver.findElements(By.cssSelector("table tr"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains("jan.janssens@hotmail.com") && listItem.getText().contains("Jan") && listItem.getText().contains("Janssens") && listItem.getText().contains("TRAINER")) {
                found=true;
            }
        }
        assertTrue(found);
    }


    @Test
    public void test_Register_FirstNameNotFilledIn_ErrorMessageGivenForFirstNameAndOtherFieldsValueKept(){
        submitForm("", "Janssens", "jan.janssens@hotmail.com", "1234");

        String title = driver.getTitle();
        assertEquals("Sign Up",title);

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("No first name given", errorMsg.getText());

        WebElement fieldFirstName=driver.findElement(By.id("firstName"));
        assertEquals("",fieldFirstName.getAttribute("value"));

        WebElement fieldLastName=driver.findElement(By.id("lastName"));
        assertEquals("Janssens",fieldLastName.getAttribute("value"));

        WebElement fieldEmail=driver.findElement(By.id("email"));
        assertEquals("jan.janssens@hotmail.com",fieldEmail.getAttribute("value"));
    }

    @Test
    public void test_Register_LastNameNotFilledIn_ErrorMessageGivenForLastNameAndOtherFieldsValueKept(){
        submitForm("Jan", "", "jan.janssens@hotmail.com", "1234");

        String title = driver.getTitle();
        assertEquals("Sign Up",title);

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("No last name given", errorMsg.getText());

        WebElement fieldFirstName=driver.findElement(By.id("firstName"));
        assertEquals("Jan",fieldFirstName.getAttribute("value"));

        WebElement fieldLastName=driver.findElement(By.id("lastName"));
        assertEquals("",fieldLastName.getAttribute("value"));

        WebElement fieldEmail=driver.findElement(By.id("email"));
        assertEquals("jan.janssens@hotmail.com",fieldEmail.getAttribute("value"));
    }

    @Test
    public void test_Register_EmailNotFilledIn_ErrorMessageGivenForEmailAndOtherFieldsValueKept(){
        submitForm("Jan", "Janssens", "", "1234");

        String title = driver.getTitle();
        assertEquals("Sign Up",title);

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("No email given", errorMsg.getText());

        WebElement fieldFirstName=driver.findElement(By.id("firstName"));
        assertEquals("Jan",fieldFirstName.getAttribute("value"));

        WebElement fieldLastName=driver.findElement(By.id("lastName"));
        assertEquals("Janssens",fieldLastName.getAttribute("value"));

        WebElement fieldEmail=driver.findElement(By.id("email"));
        assertEquals("",fieldEmail.getAttribute("value"));
    }


    @Test
    public void test_Register_PasswordNotFilledIn_ErrorMessageGivenForEmailAndOtherFieldsValueKept(){
        submitForm("Jan", "Janssens", "jan.janssens@hotmail.com", "");

        String title = driver.getTitle();
        assertEquals("Sign Up",title);

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("No password given", errorMsg.getText());

        WebElement fieldFirstName=driver.findElement(By.id("firstName"));
        assertEquals("Jan",fieldFirstName.getAttribute("value"));

        WebElement fieldLastName=driver.findElement(By.id("lastName"));
        assertEquals("Janssens",fieldLastName.getAttribute("value"));

        WebElement fieldEmail=driver.findElement(By.id("email"));
        assertEquals("jan.janssens@hotmail.com",fieldEmail.getAttribute("value"));
    }

    @Test
    public void test_Register_UserAlreadyExists_ErrorMessageGiven(){
        submitForm("Pieter", "Pieters", "pieter.pieters@hotmail.com", "1234");

        driver.get("http://localhost:8080/Groep1_17_war2/register.jsp");

        submitForm( "Pieter", "Pieters", "pieter.pieters@hotmail.com", "1234");

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("Email already in use", errorMsg.getText());

        WebElement fieldFirstName=driver.findElement(By.id("firstName"));
        assertEquals("Pieter",fieldFirstName.getAttribute("value"));

        WebElement fieldLastName=driver.findElement(By.id("lastName"));
        assertEquals("Pieters",fieldLastName.getAttribute("value"));

        WebElement fieldEmail=driver.findElement(By.id("email"));
        assertEquals("pieter.pieters@hotmail.com",fieldEmail.getAttribute("value"));
    }


    private void fillOutField(String name,String value) {
        WebElement field=driver.findElement(By.id(name));
        field.clear();
        field.sendKeys(value);
    }

    private void submitForm(String firstName,String lastName, String email, String password) {
        fillOutField("firstName", firstName);
        fillOutField("lastName",lastName);
        fillOutField("email", email);
        fillOutField("password", password);

        WebElement button=driver.findElement(By.id("signUp"));
        button.click();
    }

}
