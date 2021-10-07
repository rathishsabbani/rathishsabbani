package com.Vtiger;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Cswitcwindow {

	
	public class TC_003_Cswitchwindow
	{
		@Test
		public void CreateContact() throws InterruptedException
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
			WebElement intialsDD=driver.findElement(By.name("salutationtype"));

			Select intialsDropdown= new Select(intialsDD);
			intialsDropdown.selectByVisibleText("Mr.");

			driver.findElement(By.name("firstname")).sendKeys("RATHISH");

			driver.findElement(By.name("lastname")).sendKeys("S");

			//Select the Existing Org
			driver.findElement(By.xpath("//img[@title='Select']")).click();

			Set<String> windows = driver.getWindowHandles();
			Iterator<String> window = windows.iterator();

			String parentWindow= window.next();
			String childWindow=window.next();

			driver.switchTo().window(childWindow);

			driver.findElement(By.id("search_txt")).sendKeys("SRS");

			driver.findElement(By.name("search")).click();

			driver.findElement(By.xpath("//a[@id='1' and text()='SRS]")).click();

			driver.switchTo().window(parentWindow);
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//a[text()='Contacts']")).click();
			driver.findElement(By.xpath("//input[@class='txtBox']")).sendKeys("RATHISH");

			WebElement rs=driver.findElement(By.name("search_field"));
			Select rss = new Select(rs);
			rss.selectByVisibleText("First Name");
			driver.findElement(By.name("submit")).click();
			WebElement actulelement=driver.findElement(By.xpath("//a[@title='Contacts' and text()='RATHISH']"));
			System.out.println(actulelement.isDisplayed());
			Assert.assertEquals(actulelement.isDisplayed(), true);
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).build().perform();
			driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
			Thread.sleep(5000);
			driver.close();	
		}
	}

	}
