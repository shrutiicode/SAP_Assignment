package com.sap.utility;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserUtility extends TestBase {

	DesiredCapabilities dc;
	Logger log = Logger.getLogger(getClass().getSimpleName());

	public void launchBrowser(String browser) throws MalformedURLException {
		dc = new DesiredCapabilities();

//		if(driver==null) {
		if (browser.equalsIgnoreCase("chrome")) {
			log.info("Launching Browser: " + browser);
		dc.setCapability(CapabilityType.BROWSER_NAME,BrowserType.CHROME);
		dc.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX );
		//	dc.setBrowserName(BrowserType.CHROME);
			URL url = new URL(System.getProperty("Remote_WebDriver_IP"));
			driver = new RemoteWebDriver(url, dc);

		} else if (browser.equalsIgnoreCase("firefox")) {
			log.info("Launching Browser: " + browser);
			dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
			dc.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
		//	dc.setBrowserName(BrowserType.FIREFOX);
			URL url = new URL(System.getProperty("Remote_WebDriver_IP"));
			driver = new RemoteWebDriver(url, dc);
		}
//		}

//		URL url = new URL(System.getProperty("Remote_WebDriver_IP"));
//		driver = new RemoteWebDriver(url, dc);
		driver.get(System.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	public boolean isDisplayed(WebElement ele) {
		boolean bRes_flag = false;
		try {
			if (ele.isDisplayed()) {
				log.info("Displayed " + ele);
				bRes_flag = true;
			}
		} catch (Exception e) {
		}

		return bRes_flag;
	}

	public boolean waitForElementVisible(WebDriver driver, WebElement ele, int iTimeInSeconds)
			throws InterruptedException {
		boolean bRes_flag = false;
		for (int i = 0; i < iTimeInSeconds; i++) {
			if (!isDisplayed(ele))
				Thread.sleep(1000);
			else
				break;
		}

		return bRes_flag;

	}

	public void switchFrame() {

		driver.switchTo().frame(
				"https://accounts.sap.com/ui/public/showRegisterForm?sourceUrl=https%3A%2F%2Fcai.tools.sap%2F&targetUrl=https%3A%2F%2Fcai.tools.sap%2Flogin%2FloggedIn&spName=https%3A%2F%2Fsapcai-community.authentication.eu10.hana.ondemand.com#https%3A%2F%2Fcai.tools.sap%2F");

	}

}
