import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterTrainingPage extends Page{
    @FindBy(id="date")
    private WebElement date;
    @FindBy(id= "start")
    private WebElement  start;
    @FindBy(id="end")
    private WebElement end;

    public RegisterTrainingPage(WebDriver driver) {
        super(driver);
        this.driver.get(path + "register.jsp");
    }

    public void setDate(String d) {
        date.clear();
        date.sendKeys(d);
    }

    public void setStart(String s) {
        start.clear();
        start.sendKeys(s);
    }

    public void setEnd(String e) {
        end.clear();
        end.sendKeys();
    }

    public void register(){
        WebElement button=driver.findElement(By.id("addTraining"));
        button.click();
    }

    public boolean hasErrorMessage (String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        return (message.equals(errorMsg.getText()));
    }


}
