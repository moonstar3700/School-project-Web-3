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

    public void test_Register_AllFieldsFilledInCorrectly_UserIsRegistered_and_RoleIsTrainer() {
        submitForm("Jan", "Janssens", "jan.janssens@hotmail.com" , "1234");

        String title = getTitle();
        assertEquals("Home",title);

        driver.get("http://localhost:8080/Groep1_17_war2/Controller?command=Overview");

        ArrayList<WebElement> listItems=(ArrayList<WebElement>) driver.findElements(By.cssSelector("table tr"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains("jan.janssens@hotmail.com") && listItem.getText().contains("Jan") && listItem.getText().contains("Janssens") && listItem.getText().contains("TRAINER")) {
                found=true;
            }
        }
        assertTrue(found);
    }
}
