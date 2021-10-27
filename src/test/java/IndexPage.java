import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class IndexPage extends Page{
    @FindBy(id="email")
    private WebElement email;
    @FindBy(id= "password")
    private WebElement  password;

    public IndexPage(WebDriver driver) {
        super(driver);
        this.driver.get(path + "index.jsp");
    }

    public void setEmail(String email) {
        this.email.clear();
        this.email.sendKeys(email);
    }

    public void setPassword(String password) {
        this.password.clear();
        this.password.sendKeys(password);
    }

    public void login(){
        setEmail("testadmin@ucll.be");
        setPassword("t");
        WebElement button=driver.findElement(By.id("login"));
        button.click();
    }
}
