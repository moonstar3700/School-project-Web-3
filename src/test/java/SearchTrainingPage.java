import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class SearchTrainingPage extends Page {
    @FindBy(id="date")
    private WebElement date;


    public SearchTrainingPage(WebDriver driver) {
        super(driver);
        this.driver.get(path + "searchTraining.jsp");
    }


    public void setDate(String d) {
        date.clear();
        date.sendKeys(d);
    }

    public void search() {
        WebElement button = driver.findElement(By.id("searchTraining"));
        button.click();
    }

    public boolean hasErrorMessage (String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        return (message.equals(errorMsg.getText()));
    }

    public boolean hasStickyDate(String d) {
        return d.equals(date.getAttribute("value"));
    }

    public boolean hasEmptyDate () {
        return date.getAttribute("value").isEmpty();
    }
}
