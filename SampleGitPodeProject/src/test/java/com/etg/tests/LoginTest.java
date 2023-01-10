package com.etg.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {
  @Test
  public void scenario1() throws InterruptedException {
	  WebDriver driver;
	  WebDriverManager.chromedriver().setup();
      ChromeOptions options=new ChromeOptions();
      options.setHeadless(true);
      System.out.println("Testing");
      options.addArguments("window-size=1920,1200");
      driver=new ChromeDriver(options);
      driver.get("https://opensource-demo.orangehrmlive.com/");
      Thread.sleep(2000);
  }
}
