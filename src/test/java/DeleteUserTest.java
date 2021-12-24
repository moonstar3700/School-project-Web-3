import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DeleteUserTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/Controller";

    @Before
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "/Users/.../web3pers/chromedriver");
        // windows: gebruik dubbele \\ om pad aan te geven
        // hint: zoek een werkende test op van web 2 ...
        System.setProperty("webdriver.chrome.driver", "C:/Users/Sarah/Toegepaste Informatica/1ste Fase/Webontwikkeling 2/chromedriver.exe");
        //System.setProperty("webdriver.chrome.driver", "D:\\informatica cursus\\IT 2de jaar\\Web 3\\chrome driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/Groep1_17_war_exploded/register.jsp");
        //driver.get("http://localhost:8080/Groep1_17_war2/register.jsp");
    }

    @After
    public void clean() {
        driver.quit();
    }


    @Test
    public void test_User_verwijderen_annuleren(){
        // als bob bestaat wordt hij ingelogd, anders wordt hij eerst geregistreerd
        IndexPage index = PageFactory.initElements(driver, IndexPage.class);
        index.setEmail("bob@hotmail.com");
        index.setPassword("1234");
        index.login2();

        RegisterUserPage registerpage = PageFactory.initElements(driver, RegisterUserPage.class);
        registerpage.setFirstname("Bob");
        registerpage.setlastName("Bobbens");
        registerpage.setEmail("bob@hotmail.com");
        registerpage.setPassword("1234");
        registerpage.register();

        UserOverviewPage overviewPage = PageFactory.initElements(driver, UserOverviewPage.class);
        assertTrue(overviewPage.containsString("bob@hotmail.com"));
        overviewPage.findKnopInTable("delete");
        overviewPage.findKnop("cancel");

        assertTrue(overviewPage.containsString("bob@hotmail.com"));
    }

    @Test
    public void test_User_verwijderen(){
        // als bob bestaat wordt hij ingelogd, anders wordt hij eerst geregistreerd
        IndexPage index = PageFactory.initElements(driver, IndexPage.class);
        index.setEmail("bob@hotmail.com");
        index.setPassword("1234");
        index.login2();

        RegisterUserPage registerpage = PageFactory.initElements(driver, RegisterUserPage.class);
        registerpage.setFirstname("Bob");
        registerpage.setlastName("Bobbens");
        registerpage.setEmail("bob@hotmail.com");
        registerpage.setPassword("1234");
        registerpage.register();

        UserOverviewPage overviewPage = PageFactory.initElements(driver, UserOverviewPage.class);
        assertTrue(overviewPage.containsString("bob@hotmail.com"));
        overviewPage.findKnopInTable("delete");
        overviewPage.findKnop("delete");

        assertTrue(!overviewPage.containsString("bob@hotmail.com"));

    }


    @Test
    public void test_naar_confirm_delete_gaan_en_van_pagina_wisselen(){
        IndexPage index = PageFactory.initElements(driver, IndexPage.class);
        index.setEmail("Jin@hotmail.com");
        index.setPassword("1234");
        index.login2();

        RegisterUserPage registerpage = PageFactory.initElements(driver, RegisterUserPage.class);
        registerpage.setFirstname("Jin");
        registerpage.setlastName("Jinsen");
        registerpage.setEmail("Jin@hotmail.com");
        registerpage.setPassword("1234");
        registerpage.register();

        UserOverviewPage overviewPage = PageFactory.initElements(driver, UserOverviewPage.class);
        assertTrue(overviewPage.containsString("Jin@hotmail.com"));
        overviewPage.findKnopInTable("delete");

        overviewPage.findKnop("Home");
        assertEquals("Home",overviewPage.getTitle());

        UserOverviewPage overviewPage2 = PageFactory.initElements(driver, UserOverviewPage.class);
        assertTrue(overviewPage2.containsString("Jin@hotmail.com"));
    }




}
