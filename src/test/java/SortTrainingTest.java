import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertTrue;

public class SortTrainingTest {
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
        driver.get("http://localhost:8080/Groep1_17_war_exploded/registertraining.jsp");
        //driver.get("http://localhost:8080/Groep1_17_war2/registertraining.jsp");
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_sort_by_dates_gives_list_sorted_by_date(){
        /*RegisterUserPage registerpage = PageFactory.initElements(driver, RegisterUserPage.class);
        registerpage.setFirstname("sort");
        registerpage.setlastName("test");
        registerpage.setEmail("sort.test@hotmail.com");
        registerpage.setPassword("123");
        registerpage.register();*/

        IndexPage index = PageFactory.initElements(driver, IndexPage.class);
        index.loginSort();

        RegisterTrainingPage registerTrainingpage = PageFactory.initElements(driver, RegisterTrainingPage.class);
        registerTrainingpage.setDate("02032020");
        registerTrainingpage.setStart("09:05");
        registerTrainingpage.setEnd("10:31");
        registerTrainingpage.register();

        RegisterTrainingPage registerTrainingpage2 = PageFactory.initElements(driver, RegisterTrainingPage.class);

        registerTrainingpage2.setDate("02032021");
        registerTrainingpage2.setStart("00:01");
        registerTrainingpage2.setEnd("10:31");
        registerTrainingpage2.register();

        RegisterTrainingPage registerTrainingpage3 = PageFactory.initElements(driver, RegisterTrainingPage.class);

        registerTrainingpage3.setDate("01010001");
        registerTrainingpage3.setStart("09:05");
        registerTrainingpage3.setEnd("10:31");
        registerTrainingpage3.register();

        TrainingOverviewPage overview = PageFactory.initElements(driver, TrainingOverviewPage.class);
        overview.selectSort("date");
        assertTrue(overview.lookAtFirstElementDate("0001-01-01"));
        overview.selectSort("start time");
        assertTrue(overview.lookAtFirstElementStartTime("00:01"));
    }

    //Er zijn geen unhappy path testen voor de sorteer functie --> er zijn geen trainingen om te soorteren
}
