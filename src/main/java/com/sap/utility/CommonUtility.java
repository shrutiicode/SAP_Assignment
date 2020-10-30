package com.sap.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class CommonUtility {

	FileInputStream fis;
	Properties prop = new Properties();
	static Workbook book;
	static Sheet sheet;
	public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir") + "/resource/TestData.xlsx";
	Logger log = Logger.getLogger(getClass().getSimpleName());

	public void loadConfigProperty(String sConfigPath) throws Exception {
		log.info("Current dir using System:" + sConfigPath);
		FileInputStream fis = new FileInputStream(sConfigPath);
		prop.load(fis);
		System.getProperties().putAll(prop);
	}

	public void loadLog4jProperty(String sLog4jPath) throws Exception {
		log.info("Current dir using System:" + sLog4jPath);
		FileInputStream fis = new FileInputStream(sLog4jPath);
		prop.load(fis);
		System.getProperties().putAll(prop);
		PropertyConfigurator.configure(prop);
	}

	public Object[][] getExceldata(String sheetName) {
		log.info("Current dir using System:" + TESTDATA_SHEET_PATH);

		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}

		}
		return data;

	}

	public static String takeScreenShotWebReturnPath(WebDriver driver, String getMethodName) throws IOException {
		String sDestDir = "/screenshots/";
		String sImageName = System.getProperty("user.dir") + sDestDir + getMethodName + ".jpg";
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(driver);
		ImageIO.write(screenshot.getImage(), "jpg", new File(sImageName));
		System.out.println(sImageName);
		return sImageName;
	}

	// To add message in extent report
	public boolean verifyEqual(String actual, String expected, String message, ExtentTest extLogger) {

		String[][] data = { { "Message", message }, { "Actual", actual }, { "Expected", expected } };
		Markup m = MarkupHelper.createTable(data);

		if (actual.equals(expected)) {
			extLogger.pass(m);
			return true;
		} else {
			extLogger.fail(m);
		}
		return false;
	}

}
