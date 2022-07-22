package Assignment_2_CucumberBDD.StepDef;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import Assignment_2_CucumberBDD.Core.WebDriverFactory;
import Assignment_2_CucumberBDD.PageObjects.CommonPageObjects;
import Assignment_2_CucumberBDD.PageObjects.FooterSectionObject;
import Assignment_2_CucumberBDD.PageObjects.ProdCategoryObject;
import Assignment_2_CucumberBDD.PageObjects.SignInPageObject;
import Assignment_2_CucumberBDD.PageObjects.searchProdPageObject;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefFile {
	private static final Logger logger = LogManager.getLogger(StepDefFile.class);
	WebDriver driver;
	Scenario scn;
	String base_url = "http://automationpractice.com/";

//====================== Declare object reference of pageObjects classes =========================================//
	
	CommonPageObjects cmnPageObject;
	FooterSectionObject footerObject;
	ProdCategoryObject prodCatObject;
	searchProdPageObject searchProdObject;
	SignInPageObject signInObject;
	
//====================== Before Hook =========================================================//	
	@Before
	public void setUp(Scenario scn)
	{
		this.scn=scn;

		//Get the desired browser to be invoked
		String browserName= WebDriverFactory.getBrowserName();
		driver=WebDriverFactory.getWebDriverForBrowser(browserName); 
		scn.log("Browser get invoked");

		//Initialize object of pageobjects classes 
		 cmnPageObject= new CommonPageObjects(driver, scn);
		 footerObject= new FooterSectionObject(driver, scn);
		 prodCatObject= new ProdCategoryObject(driver, scn);
		 searchProdObject= new searchProdPageObject(driver, scn);
		 signInObject= new SignInPageObject(driver, scn);
	}

//====================== After Hook =========================================================//
	
	@After(order=2)
	//Capture screenshot if test case get failed
	public void captureScreenshot(Scenario scn)
	{
		if(scn.isFailed())
		{
			TakesScreenshot srnshot= ((TakesScreenshot)driver);
			byte [] data =srnshot.getScreenshotAs(OutputType.BYTES);
			scn.attach(data, "image/png", "Name of failed step is: "+ scn.getName());
			scn.log("Attach a screenshot as step get failed");
		}
		else
		{
			scn.log("Test case get passed, no screenshot is captured");
		}
	}
	
	@After(order=1)
	//Quit the browser
	public void tearDown(Scenario scn)
	{
		WebDriverFactory.quitTheBrowser();
		scn.log("Browser is quit");
	}
	
//===================================================================================================//
//===================================================================================================//
	
	
//====================== Background =================================================================//
	@Given("User navigate to URL and open the landing page")
	public void user_navigate_to_url_and_open_the_landing_page() {
		WebDriverFactory.navigateToURL(base_url);
		scn.log("Navigate to base URL");
	    }

//===================== 1. URLRedirection Scenario =================================================//

	@When("User is on landing page")
	public void user_is_on_landing_page() {
		logger.info("User is on landing page after navigating to base URL");
	    scn.log("User is on landing page after navigating to base URL");
	}
	@Then("Validate current URL of landing page with expected URL")
	public void validate_current_url_of_landing_page_with_expected_url() {
		 cmnPageObject.validatePageURL();
		 scn.log("Validate current URL is: "+ driver.getCurrentUrl());
	}
//===================== 2. LandingPageTitle Scenario ===============================================//
	
	@Then("Validate title of landing page with expected title as {string}")
	public void validate_title_of_landing_page_with_expected_title_as(String titleOfPage) {
		cmnPageObject.validatePageTitle(titleOfPage);
		scn.log("Validate page title is: "+ driver.getTitle());
	}
	
//===================== 3. ProductCategory Scenario ===============================================//
	
	@When("User see the product category")
	public void user_see_the_product_category() {
		prodCatObject.setProdCategory();
	    }

	@Then("Validate product category as per expected product category listed below")
	public void validate_product_category_as_per_expected_product_category_listed_below(List<String> prodCat) {
		prodCatObject.validateProdCategory(prodCat); 
		scn.log("Validate the product category with expected datatable");
	}
	
	@Then("Size of product category should be {int}")
	public void size_of_product_category_should_be(Integer prodCount) {
		prodCatObject.sizeOfProdCategory(prodCount);
	}

//===================== 4. DisplayLogo Scenario ==================================================//
	
	@Then("User see the logo of application")
	public void user_see_the_logo_of_application() {
		cmnPageObject.displayLogo();
		scn.log("Display the application logo on landing page");
	}

//===================== 5. LogoHeight Scenario ===================================================//
	
	@When("fetch the height of logo")
	public void fetch_the_height_of_logo() {
		cmnPageObject.fetchLogoHeight();
	}

	@Then("Height of logo should be {string}")
	public void height_of_logo_should_be(String height) {
		cmnPageObject.logoHeightValid(height);
	}
	
//===================== 6. LogoWidth Scenario ===================================================//	    
	
	@When("fetch the width of logo")
	public void fetch_the_width_of_logo() {
		cmnPageObject.fetchLogoWidth(); 
	}

	@Then("Width of logo should be {string}")
	public void width_of_logo_should_be(String width) {
		cmnPageObject.logoWidthValid(width);
	}
	
//===================== 7. SignInPage Scenario ===================================================//	
	@Given("User see SignIn button")
	public void user_see_sign_in_button() {
		signInObject.signInBtnValidation();
		scn.log("Display the signIn Button");
	}

	@When("User click on SignIn button")
	public void user_click_on_sign_in_button() {
		signInObject.clickOnSignInBtn();
	}
	@Then("User is on signIn page which have expected page title as {string}")
	public void user_is_on_sign_in_page_which_have_expected_page_title_as(String SignInPageTitle) {
		signInObject.validateSignInPage(SignInPageTitle);
	}
	
//===================== 8. SearchProduct Scenario ===================================================//
	@Given("User able to see searchBox")
	public void user_able_to_see_search_box() {
		 searchProdObject.setSearchBox();
	}

	@When("User search for product {string} in given searchBox")
	public void user_search_for_product_in_given_search_box(String product) {
		 searchProdObject.searchProduct(product);
	}
	@Then("User see the suggested product list")
	public void user_see_the_suggested_product_list() {
		 searchProdObject.getSuggestedProdList();
	}
	@Then("Fetch number of products which contain {string} as product name and validate with result as {int}")
	public void fetch_number_of_products_which_contain_as_product_name_and_validate_with_result_as(String prodName, Integer prodSize) {
		 searchProdObject.validateSuggProdList(prodName, prodSize);
	}

//===================== 9. TwitterHandle Scenario ===================================================//	

	@Given("User click on twitter link")
	public void user_click_on_twitter_link() {
		footerObject.setTwitterLink();
		footerObject.clickOnTwitterLink();
	}

	@When("navigate to twitter account page")
	public void navigate_to_twitter_account_page() {
		footerObject.twitterAcPage();
	}
	@Then("User see the twitter account name {string}")
	public void user_see_the_twitter_account_name(String AcName) {
		footerObject.twitterAcNameValidation(AcName);
	}
	
//===================== 10. NewsLetterSubscription Scenario ===================================================//	
	@Given("User see newsletter text box and proceed button")
	public void user_see_newsletter_text_box_and_proceed_button() {
		footerObject.setNewsLetter();
	}

	@When("User enter email id in newsletter text box and click on proceed button")
	public void user_enter_email_id_in_newsletter_text_box_and_click_on_proceed_button() {
		footerObject.enterEmailId();
		footerObject.clickOnProceedBtn();
	}
	
	@Then("User see the text message for email subscription")
	public void user_see_the_text_message_for_email_subscription() {
		footerObject.fetchSubscriptionMsg();
		footerObject.validateSubscriptionMsg();
	}


//===================================================================================================//


	
}
