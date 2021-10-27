import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class TrainingOverviewPage extends Page{

    public TrainingOverviewPage(WebDriver driver) {
        super(driver);
        this.driver.get(path + "Controller?command=TrainingOverview");
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

    public void findKnopInTable(String string){
        List<WebElement> links = driver.findElements(By.id(string));
        links.get(links.size()-1).click();
    }

    public void findKnop(String string){
        driver.findElement(By.id(string)).click();
}
}
