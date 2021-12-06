import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchTrainingByDateTest {
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
    public void test_SearchTraining_AllFieldsFilledInCorrectly_TrainingIsFound() {
        IndexPage index = PageFactory.initElements(driver, IndexPage.class);
        index.login();
        RegisterTrainingPage registerpage = PageFactory.initElements(driver, RegisterTrainingPage.class);

        registerpage.setDate("05052015");
        registerpage.setStart("09:00");
        registerpage.setEnd("10:30");
        registerpage.register();

        SearchTrainingPage searchTrainingPage = PageFactory.initElements(driver, SearchTrainingPage.class);
        searchTrainingPage.setDate("05-05-2015");


        SearchTrainingFoundByDatePage searchTrainingFoundByDatePage = PageFactory.initElements(driver, SearchTrainingFoundByDatePage.class);

        assertTrue(searchTrainingFoundByDatePage.containsString("2015-05-05"));
        assertTrue(searchTrainingFoundByDatePage.containsString("9:00"));
        assertTrue(searchTrainingFoundByDatePage.containsString("10:30"));
    }

    @Test
    public void test_SearchTraining_AllFieldsFilledInCorrectly_TrainingDoesNotExist() {
        IndexPage index = PageFactory.initElements(driver, IndexPage.class);
        index.login();
        SearchTrainingPage searchTrainingPage = PageFactory.initElements(driver, SearchTrainingPage.class);
        searchTrainingPage.setDate("05-05-1015");
        searchTrainingPage.search();

        assertEquals("Not Found", searchTrainingPage.getTitle());
    }


}
