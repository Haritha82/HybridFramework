package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LinkedinLoggedinPage extends BasePageWeb{
	
	private Logger log=Logger.getLogger(LinkedinLoggedinPage.class);
	
	//create constructor
	public LinkedinLoggedinPage()
	{
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//div[contains(@class,'feed-identity-module')]")
	private WebElement profileRailCard;
	
	@FindBy(xpath="//img[contains(@class,'global-nav__me-photo ember-view')]")			
	private WebElement profilePicIcon;

		
	@FindBy(xpath = "//a[@class='global-nav__secondary-link mv1'][contains(.,'Sign Out')]")	
	private WebElement signOutLink;

	@FindBy(xpath = "//input [contains(@class,'search-global-type')]")
	private WebElement searchEditBox;
	
	String loggedinPageTitle="Feed | LinkedIn";
	

public void verifyLinkedinLiginPageTitle()
{
	log.debug("Started executing the verifyLinkedinLiginPageTitle()");
	wait.until(ExpectedConditions.titleContains(loggedinPageTitle));
	Assert.assertTrue(driver.getTitle().contains(loggedinPageTitle));
	
}
public void profileRailCard() throws InterruptedException
{
	log.debug("verify the linkedin profile rail card element");
	Assert.assertTrue(isDisplayedElement(profileRailCard));
	
}
	
public void doLogOut() throws InterruptedException
{
	log.debug("Started executing the doLogOut()******************************************");
	wait.until(ExpectedConditions.elementToBeClickable(profilePicIcon));
	wait.until(ExpectedConditions.visibilityOf(profilePicIcon));
	log.debug("Click on profile image icon");
	click(profilePicIcon);
	log.debug("wait for the sign out link");
	wait.until(ExpectedConditions.elementToBeClickable(signOutLink));
	wait.until(ExpectedConditions.visibilityOf(signOutLink));
	log.debug("Click on signOut link");
	click(signOutLink);			
}

	public SearchResutlsPage doPeopelSearch(String keyword) throws InterruptedException  {
		log.debug("Started executing  the doPeopelSearch() for the keyword::::" + keyword);
		log.debug("clear the content in the search editbox");
		clearText(searchEditBox);
		log.debug("type the search keyword:" + keyword + "in search editbox");
		sendKey(searchEditBox, keyword);
		log.debug("Press the ENTER key on searchEditbox");
		searchEditBox.sendKeys(Keys.ENTER);
		
		return new SearchResutlsPage();
	
}
}