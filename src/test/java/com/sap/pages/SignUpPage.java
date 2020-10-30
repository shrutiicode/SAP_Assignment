package com.sap.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.sap.utility.TestBase;

public class SignUpPage extends TestBase {

	@FindBy(xpath = "//div[text()='OK']")
	WebElement cookies;
	@FindBy(xpath = "//div[text()='Sign up']")
	WebElement signUpBtn;
	@FindBy(tagName = "iframe")
	WebElement frame;
	@FindBy(xpath = "//h1[text()='Registration']")
	WebElement regPopUp;
	@FindBy(name = "firstName")
	WebElement enterFirstName;
	@FindBy(id = "lastName")
	WebElement enterLastName;
	@FindBy(id = "mail")
	WebElement enterEmail;
	@FindBy(id = "newPasswordInput")
	WebElement enterPassword;
	@FindBy(id = "retypeNewPasswordInput")
	WebElement re_EnterPassword;
	@FindBy(id = "pdAccept")
	WebElement privacyStmt;
	@FindBy(id = "touAccept")
	WebElement termsCondition;
	@FindBy(id = "sapStoreRegisterFormSubmit")
	WebElement submitBtn;
	@FindBy(xpath = "//h1[text()='Thank you for registering with SAP Conversational AI']")
	WebElement successMsg;
	@FindBy(xpath = "//a[@title='Close']")
	WebElement popUpclose;

	Logger log = Logger.getLogger(getClass().getSimpleName());

	public SignUpPage(RemoteWebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void acceptCookies() throws Exception {
		if (oBrowserUtil.isDisplayed(cookies)) {
			cookies.click();
			extLogger.log(Status.INFO,"Clicked on cookies button");
		} else {
			throw new Exception("Unable to find cookies button ");
			//extLogger.log(Status.ERROR,"Unable to find cookies button");
			//log.info("Cookies button not available");
		}
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public void clickSignUp() throws Exception {
		if (oBrowserUtil.isDisplayed(signUpBtn)) {
			signUpBtn.click();
			extLogger.log(Status.INFO,"Clicked on Sign Up button");
		} else
			throw new Exception("Sign Up button not displayed ");
		oBrowserUtil.waitForElementVisible(driver, frame, 2);
	}

	public String verifyRegisterPopUp() throws InterruptedException {
		oBrowserUtil.waitForElementVisible(driver, regPopUp, 3);
		return regPopUp.getText();
	}

	public void fillDetails(String firstName, String LastName, String Email, String password, String reEnterPasswprd)
			throws InterruptedException {
		enterFirstName.sendKeys(firstName);
		enterLastName.sendKeys(LastName);
		enterEmail.sendKeys(Email);
		enterPassword.sendKeys(password);
		re_EnterPassword.sendKeys(reEnterPasswprd);
		privacyStmt.click();
		termsCondition.click();
		extLogger.log(Status.INFO,"Filled All the details");
	}

	public void submit() {
		if (oBrowserUtil.isDisplayed(submitBtn)) {
			submitBtn.click();
			extLogger.log(Status.INFO,"Clicked on Submit button");
		} else {
			log.info("Submit button not available");

		}
	}

	public String getSucessMsg() throws InterruptedException {
		oBrowserUtil.waitForElementVisible(driver, successMsg, 5);
		return successMsg.getText();
	}

	public void closePopUp() {
		if (oBrowserUtil.isDisplayed(popUpclose)) {
			popUpclose.click();
			extLogger.log(Status.INFO,"Clicked on pop up close button");
		} else {
			log.info("Pop Up close button not available");
		}
	}
}
