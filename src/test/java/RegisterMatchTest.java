import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegisterMatchTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/Controller";

    @Before
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "/Users/.../web3pers/chromedriver");
        // windows: gebruik dubbele \\ om pad aan te geven
        // hint: zoek een werkende test op van web 2 ...
        System.setProperty("webdriver.chrome.driver", "C:/Users/Sarah/Toegepaste Informatica/1ste Fase/Webontwikkeling 2/chromedriver.exe");
         //System.setProperty("webdriver.chrome.driver", "D:\\informatica cursus\\IT 2de jaar\\Web 3\\chrome driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/Groep1_17_war_exploded/registermatch.jsp");
        // driver.get("http://localhost:8080/Groep1_17_war2/register.jsp");
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_ARegister_AllFieldsFilledInCorrectly_MatchIsAdded() {
        IndexPage index = PageFactory.initElements(driver, IndexPage.class);
        index.login();
        RegisterMatchPage registerMatchPage = PageFactory.initElements(driver, RegisterMatchPage.class);
        registerMatchPage.setHome("Dojo Kamiyama");
        registerMatchPage.setAway("Dojo Test");
        registerMatchPage.setDate("05052025");
        registerMatchPage.setTime("1500PM");
        registerMatchPage.register();

        assertEquals("Match Overview",registerMatchPage.getTitle());

        MatchOverviewPage overviewPage = PageFactory.initElements(driver, MatchOverviewPage.class);
        assertTrue(overviewPage.containsString("Dojo Kamiyama"));
        assertTrue(overviewPage.containsString("Dojo Test"));
        assertTrue(overviewPage.containsString("2025-05-05"));
        assertTrue(overviewPage.containsString("15:00"));
    }

    @Test
    public void test_Register_AllFieldsFilledInCorrectly_MatchAlreadyExists() {
        IndexPage index = PageFactory.initElements(driver, IndexPage.class);
        index.login();
        RegisterMatchPage registerMatchPage = PageFactory.initElements(driver, RegisterMatchPage.class);
        registerMatchPage.setHome("Dojo Kamiyama");
        registerMatchPage.setAway("Dojo Test");
        registerMatchPage.setDate("05052025");
        registerMatchPage.setTime("1500PM");
        registerMatchPage.register();

        assertEquals("Add Match",registerMatchPage.getTitle());

        assertTrue(registerMatchPage.hasErrorMessage("Match already exists"));
    }



}
