import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;

public class EditMatchPage extends UserOverviewPage {
    @FindBy(id="home")
    private WebElement home;
    @FindBy(id= "away")
    private WebElement  away;
    @FindBy(id="date")
    private WebElement date;
    @FindBy(id="time")
    private WebElement time;


    public EditMatchPage(WebDriver driver) {
        super(driver);
        this.driver.get(path + "Controller?command=MatchOverview");
        findKnopInTable("edit");
    }

    public void setHome(String h){
        home.clear();
        home.sendKeys(h);
    }
    public void setAway(String a){
        away.clear();
        away.sendKeys(a);
    }

    public void setDate(String d){
        date.clear();
        date.sendKeys(d);
    }

    public void setTime(String t){
        time.clear();
        time.sendKeys(t);
    }

    public boolean hasErrorMessage (String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        return (message.equals(errorMsg.getText()));
    }
}
