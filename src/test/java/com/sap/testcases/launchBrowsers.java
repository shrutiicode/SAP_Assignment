package com.sap.testcases;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class launchBrowsers {
RemoteWebDriver driver;
	
	@BeforeTest
	public void setUp() throws MalformedURLException {
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
		dc.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
		URL url = new URL("http://localhost:4444/wd/hub");
		driver = new RemoteWebDriver(url, dc);
		driver.get("https://cai.tools.sap/");
	}
	
	@Test
	public void signup() throws InterruptedException {
		System.out.println("title is: "+driver.getTitle());
		Thread.sleep(3000);
		System.out.println("url is: "+driver.getCurrentUrl());
		Thread.sleep(3000);
		System.out.println("sign up done");
	}
	
	public void tearDown() {
		driver.quit();
		System.out.println("driver closes");
	}
}
