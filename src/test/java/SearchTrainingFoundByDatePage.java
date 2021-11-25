import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class SearchTrainingFoundByDatePage extends Page{

    public SearchTrainingFoundByDatePage(WebDriver driver) {
        super(driver);
        WebElement button = driver.findElement(By.id("searchTraining"));
        button.click();

    }

    public boolean containsString(String string) {
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) driver.findElements(By.cssSelector("table tr"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains(string)) {
                found=true;
            }
        }
        return found;
    }

}
