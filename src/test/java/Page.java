import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class Page {
    WebDriver driver;
     String path = "http://localhost:8080/Groep1_17_war2/";
     //String path = "http://localhost:8080/Groep1_17_war_exploded/";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\informatica cursus\\IT 2de jaar\\Web 3\\chrome driver\\chromedriver.exe");
        driver = new ChromeDriver();
        //driver.get("http://localhost:8080/Groep1_17_war2/register.jsp");
        driver.get("http://localhost:8080/Groep1_17_war_exploded/register.jsp");
    }

    public Page (WebDriver driver){
        this.driver = driver;
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
