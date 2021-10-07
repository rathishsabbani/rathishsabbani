package com.Vtiger;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Contactmodule {
public class TC_002_CreateContactmodule
		{
			@Test
			public void createcontact() throws InterruptedException 
			{
				
				WebDriver driver = new ChromeDriver();
				driver.get("http://localhost:8888/");
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

				
				driver.findElement(By.name("user_name")).sendKeys("admin");
				driver.findElement(By.name("user_password")).sendKeys("12345");
				driver.findElement(By.id("submitButton")).click();

				
				driver.findElement(By.xpath("//a[text()='Contacts']")).click();
				driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				WebElement sc=driver.findElement(By.name("salutationtype"));
				Select scr= new Select(sc);
				
				scr.selectByVisibleText("Mr.");
				driver.findElement(By.name("firstname")).sendKeys("RATHISH");
				driver.findElement(By.name("lastname")).sendKeys("S");
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				Thread.sleep(2000);
				
				driver.findElement(By.xpath("//a[text()='Contacts']")).click();
				driver.findElement(By.xpath("//input[@class='txtBox']")).sendKeys("RATHISH");
				WebElement viewD =driver.findElement(By.name("search_field"));
				Select view = new Select(viewD);
				view.selectByVisibleText("First Name");
				driver.findElement(By.name("submit")).click();

				
				WebElement actulelement=driver.findElement(By.xpath("//a[@title='Contacts' and text()='RATHISH']"));
				System.out.println(actulelement.isDisplayed());
				Assert.assertEquals(actulelement.isDisplayed(), true);
				Actions action = new Actions(driver);
				action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).build().perform();
				driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
				Thread.sleep(4000);
				driver.close();

	}
		}
}
