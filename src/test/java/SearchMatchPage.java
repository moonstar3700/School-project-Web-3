import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class SearchMatchPage extends Page {
    @FindBy(id="home")
    private WebElement home;
    @FindBy(id= "away")
    private WebElement  away;
    @FindBy(id="date")
    private WebElement date;
    @FindBy(id="time")
    private WebElement time;


    public SearchMatchPage(WebDriver driver) {
        super(driver);
        this.driver.get(path + "searchMatch.jsp");
    }

    public void setHome(String h){
        home.clear();
        home.sendKeys(h);
    }
    public void setAway(String a){
        away.clear();
        away.sendKeys(a);
    }

    public void selectGroup(String g) {
        Select group = new Select(driver.findElement(By.id("group")));
        group.selectByVisibleText(g);
    }

    public void setDate(String d) {
        date.clear();
        date.sendKeys(d);
    }

    public void search() {
        WebElement button = driver.findElement(By.id("searchMatch"));
        button.click();
    }

    public void search2() {
        WebElement button = driver.findElement(By.id("searchMatch2"));
        button.click();
    }

    public boolean hasErrorMessage (String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        return (message.equals(errorMsg.getText()));
    }

    public boolean hasStickyHome(String h) {
        return h.equals(home.getAttribute("value"));
    }

    public boolean hasStickyAway(String a) {
        return a.equals(away.getAttribute("value"));
    }

    public boolean hasStickyDate(String d) {
        return d.equals(date.getAttribute("value"));
    }

    public boolean hasStickyTime(String t) {
        return t.equals(time.getAttribute("value"));
    }

    public boolean hasEmptyHome () {
        return home.getAttribute("value").isEmpty();
    }
    public boolean hasEmptyAway () {
        return away.getAttribute("value").isEmpty();
    }
    public boolean hasEmptyDate () {
        return date.getAttribute("value").isEmpty();
    }
    public boolean hasEmptyTime () {
        return time.getAttribute("value").isEmpty();
    }
}
