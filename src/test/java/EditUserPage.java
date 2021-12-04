import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditUserPage extends UserOverviewPage {
    @FindBy(id="firstName")
    private WebElement firstname;
    @FindBy(id= "lastName")
    private WebElement  lastName;
    @FindBy(id="email")
    private WebElement email;
    @FindBy(id="password")
    private WebElement password;


    public EditUserPage(WebDriver driver) {
        super(driver);
        this.driver.get(path + "Controller?command=UserOverview");
        findKnopInTable("edit");
    }

    public void setFirstname(String fname){
        firstname.clear();
        firstname.sendKeys(fname);
    }
    public void setlastName(String lname){
        lastName.clear();
        lastName.sendKeys(lname);
    }

    public void setEmail(String mail){
        email.clear();
        email.sendKeys(mail);
    }

    public void setPassword(String pass){
        WebElement email = driver.findElement(By.id("password"));
        password.clear();
        password.sendKeys(pass);
    }

    public boolean hasErrorMessage (String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        return (message.equals(errorMsg.getText()));
    }


}
