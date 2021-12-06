import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchMatchByDateTest {
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
        driver.get("http://localhost:8080/Groep1_17_war2/register.jsp");
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_SearchMatch_AllFieldsFilledInCorrectly_MatchIsFound() {
        IndexPage index = PageFactory.initElements(driver, IndexPage.class);
        index.login();
        RegisterMatchPage registerMatchPage = PageFactory.initElements(driver, RegisterMatchPage.class);
        registerMatchPage.setHome("Dojo Kamiyama");
        registerMatchPage.setAway("Dojo Testie");
        registerMatchPage.setDate("05052025");
        registerMatchPage.setTime("1500PM");
        registerMatchPage.register();

        SearchMatchPage searchMatchPage = PageFactory.initElements(driver, SearchMatchPage.class);
        searchMatchPage.setDate("05-05-2025");


       SearchFoundByDatePage searchFoundByDatePage = PageFactory.initElements(driver, SearchFoundByDatePage.class);

        assertTrue(searchFoundByDatePage.containsString("Dojo Kamiyama"));
        assertTrue(searchFoundByDatePage.containsString("Dojo Testie"));
        assertTrue(searchFoundByDatePage.containsString("2025-05-05"));
        assertTrue(searchFoundByDatePage.containsString("15:00"));
        assertTrue(searchFoundByDatePage.containsString("RECREATION"));
    }

    @Test
    public void test_SearchMatch_AllFieldsFilledInCorrectly_MatchDoesNotExist() {

        SearchMatchPage searchMatchPage = PageFactory.initElements(driver, SearchMatchPage.class);
        searchMatchPage.setDate("08-08-1412");
        searchMatchPage.search2();

        assertEquals("Not Found", searchMatchPage.getTitle());
    }


}
