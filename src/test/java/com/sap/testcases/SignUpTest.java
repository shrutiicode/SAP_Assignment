package com.sap.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sap.pages.SignUpPage;
import com.sap.utility.Constants;
import com.sap.utility.TestBase;

public class SignUpTest extends TestBase {
	SignUpPage sp;
//	ExtentTest t1;

	@BeforeMethod
	public void setUp() {
		sp = new SignUpPage(driver);
		sClassNameForScreenShot = getClass().getSimpleName();
		// t1=extLogger.createNode("Verify Text");
	}

	@Test(priority = 1)
	public void verifyTitle() throws InterruptedException {
		System.out.println("im verify title");
		oCommon.verifyEqual(sp.getTitle(), Constants.pageTitle, "Verify Page Title",
				extLogger.createNode("Verify Title"));
		Assert.assertEquals(Constants.pageTitle, sp.getTitle());
		System.out.println("title matched");

	}

	@Test(priority = 2)
	public void cookies() throws Exception {
		sp.acceptCookies();
		System.out.println("cookies accepted");
	}

	@Test(priority = 3)
	public void clickSignUpBtn() throws Exception {

		sp.clickSignUp();
		System.out.println("clicked on Sign Up button");
		oBrowserUtil.switchFrame();
	}

	@Test(priority = 4)
	public void verifyRegisterPopUp() throws InterruptedException {
		oCommon.verifyEqual(sp.verifyRegisterPopUp(), Constants.popUpText, "Verify PopUp Text",
				extLogger.createNode("Verify PopUp"));
		Assert.assertEquals(Constants.popUpText, sp.verifyRegisterPopUp());
	}

	@DataProvider
	public Object[][] retriveData() {
		Object data[][] = oCommon.getExceldata("UserData");
		return data;
	}

	@Test(priority = 5, dataProvider = "retriveData")
	public void EnterUserDetails(String firstName, String LastName, String Email, String password,
			String reEnterPasswprd) throws InterruptedException {
		sp.fillDetails(firstName, LastName, Email, password, reEnterPasswprd);
		sp.submit();
	}

	@Test(priority = 6)
	public void verifySuccessMsg() throws InterruptedException {
		oCommon.verifyEqual(sp.getSucessMsg(), Constants.successMsg, "Verify Success Message Text",
				extLogger.createNode("Verify Success Msg"));
		Assert.assertEquals(Constants.successMsg, sp.getSucessMsg());
		sp.closePopUp();
	}

//	@Test(priority = 7)
//	public void failTest() {
//		oCommon.verifyEqual(sp.getTitle(),Constants.pageText, "Verify Page Title", extLogger.createNode("Verify Title"));
//	
//		Assert.assertEquals(true,false);
//	}

}
