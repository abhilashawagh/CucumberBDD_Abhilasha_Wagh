package Assignment_2_CucumberBDD.PageObjects;
import java.util.NoSuchElementException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Assignment_2_CucumberBDD.Core.WebDriverFactory;
import io.cucumber.java.Scenario;


public class FooterSectionObject {
	private static final Logger logger= LogManager.getLogger(FooterSectionObject.class);
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	Scenario scn;
	
//============= Locators for Footer section WebElements=====================================//

	private By twitterLink= By.xpath("//li[@class='twitter']");
	private By twitterAcName= By.xpath("(//div[@dir='auto']//span[text()='Selenium Framework'])[1]");
	private By newsLetterElement= By.id("newsletter-input");
	private By proceedBtn= By.name("submitNewsletter");
	private By successSubscriptionMsgField= By.xpath("//div[@class='clearfix']/following-sibling::p[@class='alert alert-success']");
	private By failSubscriptionMsgField=By.xpath("//div[@class='clearfix']/following-sibling::p[@class='alert alert-danger']");

//============= Expected Results ==========================================================//

	String twitterPageTitle= "Selenium Framework (@seleniumfrmwrk) / Twitter";
	String emailId= "abhidya295@gmail.com";
	String successedSubMsg= " Newsletter : You have successfully subscribed to this newsletter.";
	String failSubMsg= " Newsletter : This email address is already registered.";
	
//============= Constructor ===============================================================//
	public FooterSectionObject(WebDriver driver,Scenario scn)
	{
		this.driver= driver;
		this.scn=scn;
	}
	
//============ 1. Method to set twitter link =================================================//
    public void setTwitterLink()
    {
    	WebElement twitterElement =driver.findElement(twitterLink);
    	//Scroll till twitter link element available on screen using Javascript executor
		js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", twitterElement);
		Assert.assertEquals(true, twitterElement.isDisplayed());
    	logger.info("Validate the twitter link");
    }
    
//============ 2. Method to click the twitter link ===========================================//
    public void clickOnTwitterLink()
    {
    	WebElement twitterElement =driver.findElement(twitterLink);
    	twitterElement.click();
    	logger.info("Click the twitter link");
    	scn.log("Click the twitter link");
    }
    
//============ 3. Method to validate twitter account page ===================================//
    public void twitterAcPage()
    {
    	WebDriverFactory.switchToNewWindow();
    	logger.info("Switch to Twitter Account window");
    	wait= new WebDriverWait(driver, 20);
    	boolean p =wait.until(ExpectedConditions.titleIs(twitterPageTitle));
    	Assert.assertEquals(true, p);
    	logger.info("Validate twitter account page with its title, title is: "+ twitterPageTitle);
    	scn.log("navigate to twitter account page, page title is: "+ twitterPageTitle);
    }

//============ 4. Method to validate twitter account name ==================================//
    public void twitterAcNameValidation(String AcName)
    {
    	WebElement twitterAcNameElement =driver.findElement(twitterAcName);
    	Assert.assertEquals(AcName, twitterAcNameElement.getText());
    	logger.info("Validate twitter account name, account name is: "+twitterAcNameElement.getText());
    	scn.log("Validate twitter account name, account name is: "+twitterAcNameElement.getText());
    }
    
//============ 5. Method to set newsletter element ========================================//
    public void setNewsLetter()
    {
    	WebElement newsLetterTextBox =driver.findElement(newsLetterElement);
    	//Scroll till Newsletter element available on screen using Javascript executor
    	js= (JavascriptExecutor)driver;
    	js.executeScript("arguments[0].scrollIntoView(true);", newsLetterTextBox);
    	Assert.assertEquals(true, newsLetterTextBox.isDisplayed());
    	logger.info("Validate newsletter text box");
    	WebElement proceedButton= driver.findElement(proceedBtn);
    	Assert.assertEquals(true, proceedButton.isDisplayed());
    	logger.info("Validate newsletter's proceed button");
    	scn.log("Validate newsletter text box and proceed button");
    }
    
//============ 6. Method to enter emailId in newsLetter ===================================//
    public void enterEmailId()
    {
    	WebElement newsLetterTextBox =driver.findElement(newsLetterElement);
    	newsLetterTextBox.sendKeys(emailId);
    	logger.info("Enter emailId in newsletter text box");
    	scn.log("Enter emailId in newsletter text box");
    }
    
//============ 7. Method to click on proceed button ========================================//
    public void clickOnProceedBtn()
    {
    	WebElement proceedButton= driver.findElement(proceedBtn);
    	proceedButton.click();
    	logger.info("Click on proceed button");
    	scn.log("Click on proceed button");
    }
    
//============ 8. Method to fetch subscription message ====================================================//
    public void fetchSubscriptionMsg()
    {
    	try {
    	    WebElement subscriptionMsg= driver.findElement(successSubscriptionMsgField);
    		logger.info("Fetch the message text for successful email subscription,subscription msg is: "+ subscriptionMsg.getText());
    		scn.log("Fetch the message text for successful email subscription, ,subscription msg is: "+ subscriptionMsg.getText());
    		} 
    	catch(Exception e){
    	    WebElement failSubscriptionMsg= driver.findElement(failSubscriptionMsgField);
    	    logger.info("Fetch the message text for failed email subscription: "+failSubscriptionMsg.getText());
    	    scn.log("Fetch the message text for failed email subscription: "+failSubscriptionMsg.getText());
    	    }
    }
    
//============ 9. Method to validate subscription message =================================================//
    public void validateSubscriptionMsg()
    {
	    try 
	    {
	     WebElement subscriptionMsg= driver.findElement(successSubscriptionMsgField);
		 subscriptionMsg.getText().equals(successedSubMsg);
		 logger.info("Validate the message text for successful email subscription,subscription msg is: "+ subscriptionMsg.getText());
		 scn.log("Validate the message text for successful email subscription, ,subscription msg is: "+ subscriptionMsg.getText());
		} 
	    catch(Exception e)
	    {
	     WebElement failSubscriptionMsg= driver.findElement(failSubscriptionMsgField);
	     failSubscriptionMsg.getText().equals(failSubMsg);
	     logger.info("Validate the message text for failed email subscription: "+failSubscriptionMsg.getText());
	     scn.log("Validate the message text for failed email subscription: "+failSubscriptionMsg.getText());
	    }
    }
    
//=======================================================================================================================//
}
        
    
    

