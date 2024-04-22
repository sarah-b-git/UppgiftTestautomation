package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class MyStepdefs {

    private WebDriver driver;
    private String randMail;
    @Given("I have opened the site in {string}")
    public void iHaveOpenedTheSiteIn(String browser) {
        if(browser.equals("chrome")){
            driver = new ChromeDriver();
        }else if(browser.equals("safari")){
            driver = new SafariDriver();
        }else if(browser.equals("firefox")){
            driver = new FirefoxDriver();
        }
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
    }

    @And("I have entered a date of birth")
    public void iHaveEnteredADateOfBirth() {
        WebElement bdayField = driver.findElement(By.id("dp"));
        bdayField.sendKeys("12/12/1990");
    }

    @And("I have entered first name {string}")
    public void iHaveEnteredFirstName(String name) {
        WebElement firstNameField = driver.findElement(By.id("member_firstname"));
        firstNameField.sendKeys(name);
    }

    @And("I have entered last name {string}")
    public void iHaveEnteredLastName(String name) {
        WebElement lastNameField = driver.findElement(By.id("member_lastname"));
        lastNameField.sendKeys(name);
    }

    @And("I have not entered last name {string}")
    public void iHaveNotEnteredLastName(String name) {
        //WebElement lastNameField = driver.findElement(By.id("member_lastname"));
        //lastNameField.sendKeys(name);
    }

    @And("I have entered a unique email address")
    public void iHaveEnteredAUniqueEmailAddress() {
        WebElement emailField = driver.findElement(By.id("member_emailaddress"));
        generateEmail();
        emailField.sendKeys(randMail);
    }

    @And("I have entered the email in the confirmation field")
    public void iHaveEnteredTheEmailInTheConfirmationField() {
        WebElement emailConfirm = driver.findElement(By.id("member_confirmemailaddress"));
        emailConfirm.sendKeys(randMail);
    }

    @And("I have entered a valid password")
    public void iHaveEnteredAValidPassword() {
        WebElement passField = driver.findElement(By.id("signupunlicenced_password"));
        passField.sendKeys("myPass1234");

    }

    @And("I entered the password correctly in the confirmation field")
    public void iEnteredThePasswordCorrectlyInTheConfirmationField() {
        WebElement passConfirm = driver.findElement(By.id("signupunlicenced_confirmpassword"));
        passConfirm.sendKeys("myPass1234");
    }

    @And("I entered the password incorrectly in the confirmation field")
    public void iEnteredThePasswordincorrectlyInTheConfirmationField() {
        WebElement passConfirm = driver.findElement(By.id("signupunlicenced_confirmpassword"));
        passConfirm.sendKeys("myPas123");
    }

    @And("I have selected agree to terms and conditions")
    public void iHaveSelectedAgreeToTermsAndConditions() {
        WebElement tAndC = driver.findElement(By.cssSelector("label[for='sign_up_25']"));
        tAndC.click();

    }

    @And("I have not selected agree to terms and conditions")
    public void iHaveNotSelectedAgreeToTermsAndConditions() {
        //Don't select agree
    }

    @And("I have selected to confirm age limit")
    public void iHaveSelectedToConfirmAgeLimit() {
        WebElement ageConfirm = driver.findElement(By.cssSelector("label[for='sign_up_26']"));
        ageConfirm.click();
    }

    @And("I have agreed to the code of conduct")
    public void iHaveAgreedToTheCodeOfConduct() {
        WebElement conduct = driver.findElement(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct']"));
        conduct.click();
    }

    @When("I click the register user button")
    public void iClickTheRegisterUserButton() {
        WebElement joinButton = driver.findElement(By.name("join"));
        joinButton.click();
    }

    @Then("I see the confirmation message")
    public void iSeeTheConfirmationMessage() {
        String expected = "THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND";
        //WebElement confirmation = driver.findElement(By.tagName("h2"));
        //String actual = confirmation.getText();
        String actual = retrieve(driver, By.tagName("h2"));

        assertEquals(expected, actual);
    }

    @Then("I see the missing last name message")
    public void iSeeTheMissingLastNameMessage() {
        String expected = "Last Name is required";
        String actual = retrieve(driver, By.cssSelector("span[for='member_lastname']"));

        assertEquals(expected, actual);
    }

    @Then("I see the wrong password message")
    public void iSeeTheWrongPasswordMessage() {
        String expected = "Password did not match";
        String actual = retrieve(driver, By.cssSelector("span[for='signupunlicenced_confirmpassword']"));

        assertEquals(expected, actual);
    }

    @Then("I see the terms not checked message")
    public void iSeeTheTermsNotCheckedMessage() {
        String expected = "You must confirm that you have read and accepted our Terms and Conditions";
        String actual = retrieve(driver, By.cssSelector("span[for='TermsAccept']"));

        assertEquals(expected, actual);
    }

    public void generateEmail() {
        int min = 1;
        int max = 10000;
        int range = max - min + 1;
        int randNum = (int) (Math.random() * range) + min;

        randMail = "myrandommail" + randNum + "@mymail.com";
    }

    private static String retrieve(WebDriver driver, By by) {

        (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.presenceOfElementLocated(by));

        return driver.findElement(by).getText();

    }

}
