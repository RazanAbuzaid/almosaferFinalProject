package homePage;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

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
	Random rand = new Random();

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
	
	@Test (priority = 5)
	public void CheckHotelTabIsNotSelected() {
		
		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		
		String ActualValue = HotelTab.getAttribute("aria-selected");		
		String ExpectedValue = "false" ; 
		Assert.assertEquals(ActualValue, ExpectedValue);
		
		
		
		
	}
	
	
	@Test (priority = 6)
	public void CheckDepartureDate() {
		
		
		LocalDate todayDate= LocalDate.now();
		
		int Today = todayDate.getDayOfMonth();
		
		int Tomorrow = todayDate.plusDays(1).getDayOfMonth();
	//	System.out.println(Today);
	//	System.out.println(Tomorrow);
		
		List<WebElement> depatureAndArrivalDates = driver.findElements(By.className("LiroG"));
		
		String ActualDepartureDate = depatureAndArrivalDates.get(0).getText();
		
		int ActualDepartureDateInt = Integer.parseInt(ActualDepartureDate);
		
		Assert.assertEquals(ActualDepartureDateInt, Tomorrow);
		

		System.out.println(ActualDepartureDateInt);
		System.out.println(Tomorrow);

		
	}
	
	@Test(priority = 7)
	public void CheckReturnDate() {
		
		LocalDate todayDate = LocalDate.now();
		
		int Today = todayDate.getDayOfMonth();
		int TheDayAfterTomorrow = todayDate.plusDays(2).getDayOfMonth();
		
		List<WebElement> depatureAndArrivalDates = driver.findElements(By.className("LiroG"));
		
		String ActualReturnDate=depatureAndArrivalDates.get(1).getText();
		
		int ActualReturnDateInt = Integer.parseInt(ActualReturnDate);
		
		Assert.assertEquals(ActualReturnDateInt, TheDayAfterTomorrow);
		
		System.out.println(ActualReturnDateInt);
		System.out.println(TheDayAfterTomorrow);
		
		
		
		
	}
	
	@Test (priority = 8)
	public void RandomlyChangeTheLanguage() {
		
		String [] URLs = {"https://www.almosafer.com/en" , "https://www.almosafer.com/ar"};
		
		int RandomIndex = rand.nextInt(URLs.length);
		
		
		
driver.get(URLs[RandomIndex]);		
		
	}
	

}
