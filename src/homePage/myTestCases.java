package homePage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class myTestCases {

	WebDriver driver = new ChromeDriver();

	String AlmosaferURL = "https://www.almosafer.com/en";
	

	String ExpectedDefaultLanage = "en";

	@BeforeTest
	public void mySetUp() {

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.get(AlmosaferURL);
	
	}

	@Test(priority = 1)
	public void CheckTheDefaultLanguage() {

		String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");

		System.out.println(ActualLanguage);
		Assert.assertEquals(ActualLanguage, ExpectedDefaultLanage);

	}

	@Test(priority = 2)

	public void CheckTheDefaultCurrency() {

		String ActualCurrency = driver.findElement(By.xpath("//button[normalize-space()='SAR']")).getText();

		String ExpectedCurrency = "SAR";
		Assert.assertEquals(ActualCurrency, ExpectedCurrency);

	}

	@Test(priority = 3)

	public void CheckTheContactNumbers() {

		String ExpectedContactNumber = "+966554400000";

		String ActualContactNumber = driver.findElement(By.tagName("strong")).getText();

		Assert.assertEquals(ActualContactNumber, ExpectedContactNumber);

	}

	@Test(priority = 4)
	public void CheckQitafLogo() {

		WebElement TheFooter = driver.findElement(By.tagName("footer"));

		WebElement logo = TheFooter.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF"));

		/*
		 * WebElement logo = theFooter.findElement(By.cssSelector(".sc-fihHvN.eYrDjb"))
		 * .findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF"));
		 */

		boolean ActualResultForThelogo = logo.isDisplayed();

		boolean ExpectedResultForThelogo = true;
		Assert.assertEquals(ActualResultForThelogo, ExpectedResultForThelogo);
		

	}
	
	

}
