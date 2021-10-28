import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegisterTrainingTest {
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
        driver.get("http://localhost:8080/Groep1_17_war_exploded/registertraining.jsp");
        //driver.get("http://localhost:8080/Groep1_17_war2/registertraining.jsp");
    }

    /*@After
    public void clean() {
        driver.quit();
    }*/

    @Test
    public void test_TrainingRegister_filled_in_correctly(){

        IndexPage index = PageFactory.initElements(driver, IndexPage.class);
        index.login();

        RegisterTrainingPage registerpage = PageFactory.initElements(driver, RegisterTrainingPage.class);

        registerpage.setDate("09092020");
        registerpage.setStart("09:01");
        registerpage.setEnd("10:31");
        registerpage.register();

        assertEquals("Training overview",registerpage.getTitle());

        TrainingOverviewPage overviewPage = PageFactory.initElements(driver, TrainingOverviewPage.class);
        assertTrue(overviewPage.containsString("2020-09-09"));
        assertTrue(overviewPage.containsString("09:01"));
        assertTrue(overviewPage.containsString("10:31"));
    }
    @Test
    public void test_start_time_and_end_time_filled_in_correctly_and_empty_date_throws_exception(){
        IndexPage index = PageFactory.initElements(driver, IndexPage.class);
        index.login();

        RegisterTrainingPage registerpage = PageFactory.initElements(driver, RegisterTrainingPage.class);

        registerpage.setDate("");
        registerpage.setStart("09:01");
        registerpage.setEnd("10:31");
        registerpage.register();


        assertTrue(registerpage.hasErrorMessage("No date selected"));
    }

    @Test
    public void test_update_with_emtpy_start_time_throws_exception(){
        IndexPage index = PageFactory.initElements(driver, IndexPage.class);
        index.login();

        RegisterTrainingPage registerpage = PageFactory.initElements(driver, RegisterTrainingPage.class);

        registerpage.setDate("09099999");
        registerpage.setStart("09:01");
        registerpage.setEnd("10:31");
        registerpage.register();

        EditTrainingPage edit = PageFactory.initElements(driver, EditTrainingPage.class);
        edit.setStart("");
        edit.findKnop("editTraining");
        assertTrue(edit.hasErrorMessageLocator("Bad input for start time"));
    }

    @Test
    public void test_update_with_valid_unique_values(){
        IndexPage index = PageFactory.initElements(driver, IndexPage.class);
        index.login();

        EditTrainingPage edit = PageFactory.initElements(driver, EditTrainingPage.class);
        edit.setDate("09091111");
        edit.setStart("09:39");
        edit.setEnd("10:39");
        edit.findKnop("editTraining");

        assertTrue(edit.containsString("1111-09-09"));
        assertTrue(edit.containsString("09:39"));
        assertTrue(edit.containsString("10:39"));

    }

    @Test
    public void test_sort_by_dates_gives_list_sorted_by_date(){
        IndexPage index = PageFactory.initElements(driver, IndexPage.class);
        index.login();

        EditTrainingPage edit = PageFactory.initElements(driver, EditTrainingPage.class);
        edit.setDate("01010001");
        edit.setStart("09:39");
        edit.setEnd("10:39");
        edit.findKnop("editTraining");

        TrainingOverviewPage overview = PageFactory.initElements(driver, TrainingOverviewPage.class);
        overview.lookAtFirstElement("01010001");
    }

    //Er zijn geen unhappy path testen voor de sorteer functie
}
