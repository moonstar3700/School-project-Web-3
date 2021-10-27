import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterUserPage extends Page{

    @FindBy(id="firstName")
    private WebElement firstname;
    @FindBy(id= "lastName")
    private WebElement  lastName;
    @FindBy(id="email")
    private WebElement email;
    @FindBy(id="password")
    private WebElement password;

    public RegisterUserPage(WebDriver driver) {
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

    public void register(){
        WebElement button=driver.findElement(By.id("signUp"));
        button.click();
    }

    public boolean hasErrorMessage (String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        return (message.equals(errorMsg.getText()));
    }

    public boolean hasStickyFirstName (String fname) {
        return fname.equals(firstname.getAttribute("value"));
    }
    public boolean hasStickyLastName (String food) {
        return food.equals(lastName.getAttribute("value"));
    }
    public boolean hasStickyEmail (String mail) {
        return mail.equals(email.getAttribute("value"));
    }
    public boolean hasEmptyFirstname () {
        return firstname.getAttribute("value").isEmpty();
    }
    public boolean hasEmptyLastName () {
        return lastName.getAttribute("value").isEmpty();
    }
    public boolean hasEmptyEmail () {
        return email.getAttribute("value").isEmpty();
    }
    public boolean hasEmptyPassword () {
        return password.getAttribute("value").isEmpty();
    }

}
