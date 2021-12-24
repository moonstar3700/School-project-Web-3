import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EditUserTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/Controller";

    @Before
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "/Users/.../web3pers/chromedriver");
        // windows: gebruik dubbele \\ om pad aan te geven
        // hint: zoek een werkende test op van web 2 ...
        //System.setProperty("webdriver.chrome.driver", "C:/Users/Sarah/Toegepaste Informatica/1ste Fase/Webontwikkeling 2/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "D:\\informatica cursus\\IT 2de jaar\\Web 3\\chrome driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/Groep1_17_war_exploded/register.jsp");
        //driver.get("http://localhost:8080/Groep1_17_war2/register.jsp");
    }

    @After
    public void clean() {
        driver.quit();
    }


    @Test
    public void test_User_email_aanpassen(){
        IndexPage index = PageFactory.initElements(driver, IndexPage.class);
        index.setEmail("Ham@hotmail.com");
        index.setPassword("1234");
        index.login2();

        RegisterUserPage registerpage = PageFactory.initElements(driver, RegisterUserPage.class);
        registerpage.setFirstname("Ham");
        registerpage.setlastName("Hammens");
        registerpage.setEmail("Ham@hotmail.com");
        registerpage.setPassword("1234");
        registerpage.register();

        EditUserPage editPage = PageFactory.initElements(driver, EditUserPage.class);
        editPage.setEmail("bam@hotmail.com");
        editPage.findKnop("edit");
        assertTrue(editPage.containsString("bam@hotmail.com"));
        assertTrue(!editPage.containsString("Ham@hotmail.com"));
    }

    @Test
    public void test_user_email_aanpassen_naar_bestaande_email_geeft_errorMessage(){

        RegisterUserPage registerpage = PageFactory.initElements(driver, RegisterUserPage.class);
        registerpage.setFirstname("John");
        registerpage.setlastName("Johnson");
        registerpage.setEmail("John1@hotmail.com");
        registerpage.setPassword("1234");
        registerpage.register();
        registerpage.logout();


        RegisterUserPage registerpage2 = PageFactory.initElements(driver, RegisterUserPage.class);
        registerpage2.setFirstname("Ban");
        registerpage2.setlastName("Babbens");
        registerpage2.setEmail("bab@hotmail.com");
        registerpage2.setPassword("1234");
        registerpage2.register();

        //IndexPage index = PageFactory.initElements(driver, IndexPage.class);
        //index.setEmail("bab@hotmail.com");
        //index.setPassword("1234");
        //index.login2();

        EditUserPage editPage = PageFactory.initElements(driver, EditUserPage.class);
        editPage.setEmail("John1@hotmail.com");
        editPage.findKnop("edit");
        assertEquals("Edit",editPage.getTitle());
        assertTrue(editPage.hasErrorMessage("Email already in use"));
    }

    @Test
    public void test_User_updaten_en_emailveld_leeg_laten_geeft_errorMessage(){
        IndexPage index = PageFactory.initElements(driver, IndexPage.class);
        index.setEmail("Jason@hotmail.com");
        index.setPassword("1234");
        index.login2();

        RegisterUserPage registerpage = PageFactory.initElements(driver, RegisterUserPage.class);
        registerpage.setFirstname("Jason");
        registerpage.setlastName("Jasons");
        registerpage.setEmail("Jason@hotmail.com");
        registerpage.setPassword("1234");
        registerpage.register();

        EditUserPage editPage = PageFactory.initElements(driver, EditUserPage.class);
        editPage.setEmail("");
        editPage.findKnop("edit");
        assertEquals("Edit",editPage.getTitle());
        assertTrue(editPage.hasErrorMessage("No email given"));
    }



}
