import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends Page{

    @FindBy(id="firstName")
    private WebElement firstname;
    @FindBy(id= "lastName")
    private WebElement  lastName;
    @FindBy(id="email")
    private WebElement email;
    @FindBy(id="password")
    private WebElement password;

    public RegisterPage(WebDriver driver) {
        super(driver);
        this.driver.get(path + "register.jsp");
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
        password.clear();
        password.sendKeys(pass);
    }


}
