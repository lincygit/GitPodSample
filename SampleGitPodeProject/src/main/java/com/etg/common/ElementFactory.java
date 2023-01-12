package com.etg.common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementFactory {

	/**
	 * This method is to to wait for java script and jquery to load
	 * @param driver
	 * @return false
	 * @lastModified Ravi
	 */
	public static boolean waitForJSandJQueryToLoad(WebDriver driver) {

		try {

			WebDriverWait wait = new WebDriverWait(driver, 180);
			// wait for jQuery to load
			ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					try {
						return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
					} catch (Exception e) {
						// no jQuery present
						return true;
					}
				}
			};

			// wait for Javascript to load
			ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
							.equals("complete");
				}
			};

			return wait.until(jQueryLoad) && wait.until(jsLoad);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	/**
	 * This method is used to wait for element
	 * @param by
	 * @param driver
	 * @return element
	 * @lastModified Ravi
	 */
	public static WebElement waitForElement(By by, WebDriver driver) {

		WebElement ele = null;
		int i;
		waitForJSandJQueryToLoad(driver);
		for (i = 0; i < 15; i++) {
			try {
				ele = driver.findElement(by);
				if (ele.isDisplayed() && ele.isEnabled()) {
					System.out.println("Element found successfully");
					return ele;
				}
			} catch (Exception e) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					System.out.println("Waiting for element to appear on DOM");
				}
			}

		}
		if (i == 15)
			System.out.println("Loop expired");
		return ele;

	}
}
