import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EditTrainingTest {
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

    @After
    public void clean() {
        driver.quit();
    }


    @Test
    public void test_update_with_emtpy_start_time_throws_exception(){
        IndexPage index = PageFactory.initElements(driver, IndexPage.class);
        index.login();

        RegisterTrainingPage registerpage = PageFactory.initElements(driver, RegisterTrainingPage.class);

        registerpage.setDate("09092000");
        registerpage.setStart("09:05");
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


}
