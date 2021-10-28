import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

public class EditTrainingPage extends TrainingOverviewPage{

    @FindBy(id="date")
    private WebElement date;
    @FindBy(id= "start")
    private WebElement  start;
    @FindBy(id="end")
    private WebElement end;

    public EditTrainingPage(WebDriver driver) {
        super(driver);
        this.driver.get(path + "Controller?command=TrainingOverview");
        findKnopInTable("edit");
    }

    public void setDate(String d) {
        date.clear();
        //LocalDate localDate = LocalDate.parse(d, formatter);
        date.sendKeys(d);
    }

    public void setStart(String s) {
        start.clear();
        start.sendKeys(s);
    }

    public void setEnd(String e) {
        end.clear();
        end.sendKeys(e);
    }

    public boolean hasErrorMessage (String message) {

        WebElement errorMsg = driver.findElement(By.cssSelector(""));
        return (message.equals(errorMsg.getText()));
    }

    public boolean hasErrorMessageLocator(String message) {
        //String locator = "div.alert-danger ul li:nth-child(2)";
        //WebElement error = driver.findElement(By.cssSelector(locator));
        //return (message.equals(error.getText()));

        ArrayList<WebElement> listItems=(ArrayList<WebElement>) driver.findElements(By.cssSelector("div.alert-danger ul li"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains(message)) {
                found=true;
            }
        }
        return found;
    }
}
