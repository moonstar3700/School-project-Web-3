import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MatchOverviewTest {
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
        driver.get("http://localhost:8080/Groep1_17_war_exploded/Controller?command=MatchOverview.jsp");
        //driver.get("http://localhost:8080/Groep1_17_war2/register.jsp");
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_MatchOverview_OverviewConstainsMatches_MatchInfoIsShown() {
        MatchOverviewPage overviewPage = PageFactory.initElements(driver, MatchOverviewPage.class);
        assertTrue(overviewPage.containsString("Group"));
        assertTrue(overviewPage.containsString("Date"));
        assertTrue(overviewPage.containsString("Time"));
        assertTrue(overviewPage.containsString("Home"));
        assertTrue(overviewPage.containsString("Away"));
        assertTrue(overviewPage.containsString("Winner"));
        assertTrue(overviewPage.containsString("Creator"));

    }

    @Test
    public void test_MatchOverview_OverviewDoesNotContainMatches_MessageIsShown() {
        /*
        Het is moeilijk om een test te maken zonder matchen, aangezien de databank hiervoor leeg moet zijn.
        De boodschap 'Er zijn momenteel geen wedstrijden geregistreerd. U kan hier wedstrijden
                toevoegen' wordt normaal getoond.
        */

    }


}
