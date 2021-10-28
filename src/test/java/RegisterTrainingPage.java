import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RegisterTrainingPage extends Page{

    @FindBy(id="date")
    private WebElement date;
    @FindBy(id= "start")
    private WebElement  start;
    @FindBy(id="end")
    private WebElement end;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String string;

    public RegisterTrainingPage(WebDriver driver) {
        super(driver);
        this.driver.get(path + "registertraining.jsp");
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



    public void register(){
        WebElement button=driver.findElement(By.id("addTraining"));
        button.click();
    }

    public void login(){
        WebElement button=driver.findElement(By.id("login"));
        button.click();
    }

    public boolean hasErrorMessage (String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        return (message.equals(errorMsg.getText()));
    }




}
