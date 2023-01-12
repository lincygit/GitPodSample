package com.etg.tests;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import com.etg.common.ElementFactory;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {
  @Test
  public void scenario1() throws InterruptedException {
    String email = null;
	  Random ran = new Random();
	  WebDriver driver;
	  WebDriverManager.chromedriver().setup();
      ChromeOptions options=new ChromeOptions();
      //options.setHeadless(true);
      System.out.println("Testing");
      options.addArguments("window-size=1920,1200");
      driver=new ChromeDriver(options);
      driver.get("https://chargenet:chargenet@app.chargenet.preprod.testcharge.net.nz/");
      email = "tester.etg" + ran.nextInt(1000) + "@energy-tech.in";
      Thread.sleep(5000);
      WebElement we = driver.findElement(By.xpath("//a[contains(text(),'have an account')]"));
      ElementFactory.waitForElement(By.xpath("//a[contains(text(),'have an account')]"), driver).click();
		ElementFactory.waitForElement(By.xpath("//div/input[@name='email']"),driver).sendKeys(email);
		ElementFactory.waitForElement(By.xpath("//div/input[@name='phoneNumber']"),driver).sendKeys("0412525878");
		ElementFactory.waitForElement(By.xpath("//div/input[@type='password']"),driver).sendKeys("12345678");
		ElementFactory.waitForElement(By.xpath("//*[@name='password_confirm']"),driver).sendKeys("12345678");
		ElementFactory.waitForElement(By.xpath("//input[@name='firstName']"),driver).sendKeys("Customer");
		ElementFactory.waitForElement(By.xpath("//input[@name='lastName']"),driver).sendKeys("Chargenet");
		ElementFactory.waitForElement(By.xpath("//span/input[@name='acceptTCs']"),driver).click();
		ElementFactory.waitForElement(By.xpath("//button[@name='submit']"),driver).click(); 
		ElementFactory.waitForElement(By.xpath("//span[contains(text(),'Skip')]"),driver).click();	
		driver.navigate().refresh();
		ElementFactory.waitForElement(By.xpath("//span[contains(text(),'Find a Charger')]"), driver).click();
		driver.get("https://app.chargenet.preprod.testcharge.net.nz/account");
		Thread.sleep(2000);
		System.out.println("Account Created for " + email);
		WebElement ele = ElementFactory.waitForElement(By.xpath("//ul/li[1]/div[2]/span"),driver);
		String accountNumber = ele.getText();
		ElementFactory.waitForElement(By.xpath("//button/span[contains(text(),'Logout')]"), driver).click();			
		System.out.println("*********** Your chargenet account number is "+accountNumber +" and email id is  "+email +" ***********");


  }
}
