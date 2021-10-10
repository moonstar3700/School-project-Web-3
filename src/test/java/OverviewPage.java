import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OverviewPage extends Page{
    public OverviewPage(WebDriver driver) {
        super(driver);
        this.driver.get(path + "Controller?command=Overview");
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
