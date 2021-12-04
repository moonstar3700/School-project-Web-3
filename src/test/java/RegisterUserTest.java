import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class RegisterUserTest {
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
    public void test_ARegister_AllFieldsFilledInCorrectly_UserIsRegistered_and_RoleIsTrainer() {
        //submitForm("Jan", "Janssens", "jan.janssens@hotmail.com" , "1234");

        RegisterUserPage registerpage = PageFactory.initElements(driver, RegisterUserPage.class);
        registerpage.setFirstname("Jan");
        registerpage.setlastName("Janssens");
        registerpage.setEmail("jan.janssens@hotmail.com");
        registerpage.setPassword("1234");
        registerpage.register();

        //assertEquals("Home",registerpage.getTitle());

        IndexPage index = PageFactory.initElements(driver, IndexPage.class);
        index.login();

        UserOverviewPage overviewPage = PageFactory.initElements(driver, UserOverviewPage.class);
        assertTrue(overviewPage.containsString("Jan"));
        assertTrue(overviewPage.containsString("Janssens"));
        assertTrue(overviewPage.containsString("jan.janssens@hotmail.com"));
        assertTrue(overviewPage.containsString("TRAINER"));
    }


    @Test
    public void test_Register_FirstNameNotFilledIn_ErrorMessageGivenForFirstNameAndOtherFieldsValueKept(){
        //submitForm("", "Janssens", "jan.janssens@hotmail.com", "1234");

        RegisterUserPage registerpage = PageFactory.initElements(driver, RegisterUserPage.class);
        registerpage.setFirstname("");
        registerpage.setlastName("Janssens");
        registerpage.setEmail("jan.janssens@hotmail.com");
        registerpage.setPassword("1234");
        registerpage.register();

        assertEquals("Sign Up",registerpage.getTitle());

        assertTrue(registerpage.hasErrorMessage("No first name given"));
        assertTrue(registerpage.hasEmptyFirstname());
        assertTrue(registerpage.hasStickyLastName("Janssens"));
        assertTrue(registerpage.hasStickyEmail("jan.janssens@hotmail.com"));
    }

    @Test
    public void test_Register_LastNameNotFilledIn_ErrorMessageGivenForLastNameAndOtherFieldsValueKept(){
        //submitForm("Jan", "", "jan.janssens@hotmail.com", "1234");

        RegisterUserPage registerpage = PageFactory.initElements(driver, RegisterUserPage.class);
        registerpage.setFirstname("Jan");
        registerpage.setlastName("");
        registerpage.setEmail("jan.janssens@hotmail.com");
        registerpage.setPassword("1234");
        registerpage.register();

        assertEquals("Sign Up",registerpage.getTitle());

        assertTrue(registerpage.hasErrorMessage("No last name given"));
        assertTrue(registerpage.hasEmptyLastName());
        assertTrue(registerpage.hasStickyFirstName("Jan"));
        assertTrue(registerpage.hasStickyEmail("jan.janssens@hotmail.com"));
    }

    @Test
    public void test_Register_EmailNotFilledIn_ErrorMessageGivenForEmailAndOtherFieldsValueKept(){
        //submitForm("Jan", "Janssens", "", "1234");

        RegisterUserPage registerpage = PageFactory.initElements(driver, RegisterUserPage.class);
        registerpage.setFirstname("Jan");
        registerpage.setlastName("Janssens");
        registerpage.setEmail("");
        registerpage.setPassword("1234");
        registerpage.register();

        assertEquals("Sign Up",registerpage.getTitle());

        assertTrue(registerpage.hasErrorMessage("No email given"));
        assertTrue(registerpage.hasEmptyEmail());
        assertTrue(registerpage.hasStickyFirstName("Jan"));
        assertTrue(registerpage.hasStickyLastName("Janssens"));

    }


    @Test
    public void test_Register_PasswordNotFilledIn_ErrorMessageGivenForEmailAndOtherFieldsValueKept(){
        //submitForm("Jan", "Janssens", "jan.janssens@hotmail.com", "");

        RegisterUserPage registerpage = PageFactory.initElements(driver, RegisterUserPage.class);
        registerpage.setFirstname("Jan");
        registerpage.setlastName("Janssens");
        registerpage.setEmail("jan.janssens@hotmail.com");
        registerpage.setPassword("");
        registerpage.register();

        assertEquals("Sign Up",registerpage.getTitle());

        assertTrue(registerpage.hasErrorMessage("No password given"));
        assertTrue(registerpage.hasStickyFirstName("Jan"));
        assertTrue(registerpage.hasStickyLastName("Janssens"));
        assertTrue(registerpage.hasStickyEmail("jan.janssens@hotmail.com"));
    }

    @Test
    public void test_Register_UserAlreadyExists_ErrorMessageGiven(){
        //submitForm("Pieter", "Pieters", "pieter.pieters@hotmail.com", "1234");

        RegisterUserPage registerpage = PageFactory.initElements(driver, RegisterUserPage.class);
        registerpage.setFirstname("Jan");
        registerpage.setlastName("Janssens");
        registerpage.setEmail("jan.janssens@hotmail.com");
        registerpage.setPassword("1234");
        registerpage.register();

        assertEquals("Sign Up",registerpage.getTitle());

        assertTrue(registerpage.hasErrorMessage("Email already in use"));
        assertTrue(registerpage.hasStickyFirstName("Jan"));
        assertTrue(registerpage.hasStickyLastName("Janssens"));
        assertTrue(registerpage.hasStickyEmail("jan.janssens@hotmail.com"));

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


    /*@Test
    public void test_naar_confirm_delete_gaan_en_van_pagina_wisselen(){

        RegisterPage registerpage = PageFactory.initElements(driver, RegisterPage.class);
        registerpage.setFirstname("Jin");
        registerpage.setlastName("Jinsen");
        registerpage.setEmail("Jin@hotmail.com");
        registerpage.setPassword("1234");
        registerpage.register();

        OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);
        assertTrue(overviewPage.containsString("Jin@hotmail.com"));

        overviewPage.findKnop("click");
        assertEquals("Home",overviewPage.getTitle());

        OverviewPage overviewPage2 = PageFactory.initElements(driver, OverviewPage.class);
        assertTrue(overviewPage2.containsString("Jin@hotmail.com"));
    }*/


    @Test
    public void test_User_email_aanpassen(){
        IndexPage index = PageFactory.initElements(driver, IndexPage.class);
        index.setEmail("Ham@hotmail.com");
        index.setPassword("1234");
        index.login2();

        RegisterUserPage registerpage = PageFactory.initElements(driver, RegisterUserPage.class);
        registerpage.setFirstname("Ham");
        registerpage.setlastName("Hammens");
        registerpage.setEmail("Ham@hotmail.com");
        registerpage.setPassword("1234");
        registerpage.register();

        EditUserPage editPage = PageFactory.initElements(driver, EditUserPage.class);
        editPage.setEmail("bam@hotmail.com");
        editPage.findKnop("edit");
        assertTrue(editPage.containsString("bam@hotmail.com"));
        assertTrue(!editPage.containsString("Ham@hotmail.com"));
    }

    @Test
    public void test_user_email_aanpassen_naar_bestaande_email_geeft_errorMessage(){

        RegisterUserPage registerpage = PageFactory.initElements(driver, RegisterUserPage.class);
        registerpage.setFirstname("John");
        registerpage.setlastName("Johnson");
        registerpage.setEmail("John@hotmail.com");
        registerpage.setPassword("1234");
        registerpage.register();

        RegisterUserPage registerpage2 = PageFactory.initElements(driver, RegisterUserPage.class);
        registerpage2.setFirstname("Ban");
        registerpage2.setlastName("Babbens");
        registerpage2.setEmail("bab@hotmail.com");
        registerpage2.setPassword("1234");
        registerpage2.register();

        IndexPage index = PageFactory.initElements(driver, IndexPage.class);
        index.setEmail("bab@hotmail.com");
        index.setPassword("1234");
        index.login2();

        EditUserPage editPage = PageFactory.initElements(driver, EditUserPage.class);
        editPage.setEmail("John@hotmail.com");
        editPage.findKnop("edit");
        assertEquals("Edit",editPage.getTitle());
        assertTrue(editPage.hasErrorMessage("Email already in use"));
    }

    @Test
    public void test_User_updaten_en_emailveld_leeg_laten_geeft_errorMessage(){
        IndexPage index = PageFactory.initElements(driver, IndexPage.class);
        index.setEmail("Jason@hotmail.com");
        index.setPassword("1234");
        index.login2();

        RegisterUserPage registerpage = PageFactory.initElements(driver, RegisterUserPage.class);
        registerpage.setFirstname("Jason");
        registerpage.setlastName("Jasons");
        registerpage.setEmail("Jason@hotmail.com");
        registerpage.setPassword("1234");
        registerpage.register();

        EditUserPage editPage = PageFactory.initElements(driver, EditUserPage.class);
        editPage.setEmail("");
        editPage.findKnop("edit");
        assertEquals("Edit",editPage.getTitle());
        assertTrue(editPage.hasErrorMessage("No email given"));
    }



}
