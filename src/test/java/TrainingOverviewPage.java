import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
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

    public void selectSort(String s) {
        Select sort = new Select(driver.findElement(By.id("filter")));
        sort.selectByVisibleText(s);
        findKnop("filterButton");
    }

    public void findKnop(String string){
        driver.findElement(By.id(string)).click();
}

    public boolean lookAtFirstElement(String string){
        String locator = "#container > main:nth-child(3) > table:nth-child(3) > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(2)";
        WebElement topdate = driver.findElement(By.cssSelector(locator));

        return string.equals(locator);
        //List<WebElement> dates = driver.findElements(By.cssSelector(locator));

        //ArrayList<String> memberNums = new ArrayList<String>();

    }
    public boolean sortedorder() {
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) driver.findElements(By.cssSelector("table tr > td:nth-child(2)"));
        for (int i = 1; i < listItems.size(); i++) {
            LocalDate d1 = LocalDate.parse(listItems.get(i-1).getText());
            LocalDate d2 = LocalDate.parse(listItems.get(i).getText());
            if (d1.isAfter(d2)) {
                return false;
            }
        }
        return true;
    }
}
