import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class SearchNotFoundPage extends Page{

    public SearchNotFoundPage(WebDriver driver) {
        super(driver);
        WebElement button = driver.findElement(By.id("searchMatch"));
        button.click();

    }


}
