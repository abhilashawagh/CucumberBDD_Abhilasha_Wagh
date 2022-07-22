@healthCheck
Feature: E-Commerce website healthcheck

###################### Background ########################################################

Background: Navigation to the URL
Given User navigate to URL and open the landing page

####################### Scenario_1 ########################################################

@URLRedirection
Scenario: User naviaget to URL, User redirect to landing page with expected current URL
When User is on landing page
Then Validate current URL of landing page with expected URL

####################### Scenario_2 #########################################################

@LandingPageTitle
Scenario: User naviaget to URL, User redirect to landing page with expected page title
When User is on landing page
Then Validate title of landing page with expected title as "My Store"

####################### Scenario_3 #########################################################

@ProductCategory
Scenario: User able to see product category on landing page
When User see the product category
Then Validate product category as per expected product category listed below
|     WOMEN    |
|    DRESSES   |
|    T-SHIRTS  |
And Size of product category should be 3 

####################### Scenario_4 #########################################################

@DisplayLogo
Scenario: User able to see logo of application on landing page
When User is on landing page
Then User see the logo of application

####################### Scenario_5 #########################################################

@LogoHeight
Scenario: Logo present on landing page with specific height dimension
When fetch the height of logo
Then Height of logo should be "99"

####################### Scenario_6 #########################################################

@LogoWidth
Scenario: Logo present on landing page with specific width dimension
When fetch the width of logo
Then Width of logo should be "350"

####################### Scenario_7 #########################################################

@SignInPage
Scenario: User click on SignIn button and navigate to respective page
Given User see SignIn button
When User click on SignIn button
Then User is on signIn page which have expected page title as "Login - My Store"

####################### Scenario_8 #########################################################

@SearchProduct
Scenario: User search a product and see the suggested product list 
Given User able to see searchBox
When User search for product "Dress" in given searchBox
Then User see the suggested product list
And Fetch number of products which contain "Dress" as product name and validate with result as 5

####################### Scenario_9 #########################################################

@TwitterHandle
Scenario: User click on twitter link and navigate to respective page
          and see the twitter account name
Given User click on twitter link
When navigate to twitter account page
Then User see the twitter account name "Selenium Framework"

####################### Scenario_10 #########################################################

@NewsLetterSubscription
Scenario: User enter email id in newsletter text box and click on proceed button,
          see the text message for email subscription
Given User see newsletter text box and proceed button
When User enter email id in newsletter text box and click on proceed button
Then User see the text message for email subscription

#############################################################################################




















