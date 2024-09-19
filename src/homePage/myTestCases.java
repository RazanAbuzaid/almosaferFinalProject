package homePage;

import java.lang.classfile.instruction.SwitchCase;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
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

	@Test(priority = 5)
	public void CheckHotelTabIsNotSelected() {

		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));

		String ActualValue = HotelTab.getAttribute("aria-selected");
		String ExpectedValue = "false";
		Assert.assertEquals(ActualValue, ExpectedValue);

	}

	@Test(priority = 6)
	public void CheckDepartureAndArrivalDate() {

		LocalDate todayDate = LocalDate.now();

		int Today = todayDate.getDayOfMonth();

		int Tomorrow = todayDate.plusDays(1).getDayOfMonth();
		int TheDayAfterTomorrow = todayDate.plusDays(2).getDayOfMonth();

		System.out.println(Today);
		System.out.println(Tomorrow);
		System.out.println(TheDayAfterTomorrow);

		List<WebElement> depatureAndArrivalDates = driver.findElements(By.className("LiroG"));

		String ActualDepartureDate = depatureAndArrivalDates.get(0).getText();
		String ActualReturnDate = depatureAndArrivalDates.get(1).getText();

		int ActualDepartureDateInt = Integer.parseInt(ActualDepartureDate);
		int ActualReturnDateInt = Integer.parseInt(ActualReturnDate);

		Assert.assertEquals(ActualDepartureDateInt, Tomorrow);
		Assert.assertEquals(ActualReturnDateInt, TheDayAfterTomorrow);

		System.out.println(ActualDepartureDateInt);
		System.out.println(ActualReturnDateInt);

	}

	@Test(priority = 7)
	public void RandomlyChangeTheLanguage() {

		String[] URLs = { "https://www.almosafer.com/en", "https://www.almosafer.com/ar" };

		int RandomIndex = rand.nextInt(URLs.length);

		driver.get(URLs[RandomIndex]);

	}

	@Test(priority = 8)
	public void FillHotelTab() throws InterruptedException {

		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		HotelTab.click();

		String WebsiteURL = driver.getCurrentUrl();

		WebElement LocationField = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));

		String[] ArabicCities = { "دبي", "جدة" };
		int ArabicCityIndex = rand.nextInt(ArabicCities.length);

		String[] EnglishCities = { "Dubai", "Jeddah", "Riyadh" };
		int EnglishCityIndex = rand.nextInt(EnglishCities.length);

		if (WebsiteURL.contains("en")) {

			LocationField.sendKeys(EnglishCities[EnglishCityIndex]);
		} else {
			LocationField.sendKeys(ArabicCities[ArabicCityIndex]);
		}

		WebElement ListOfLocations = driver.findElement(By.className("sc-phbroq-4"));

		WebElement FirstResult = ListOfLocations.findElements(By.tagName("li")).get(1);

		// System.out.println(Results.get(1).getText());

		FirstResult.click();

	}

	@Test(priority = 9)
	public void SelectTheNumberOfVistor() {

		WebElement SelectorVisitor = driver.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));

		Select select = new Select(SelectorVisitor);

		// By Index
		int RandomIndex = rand.nextInt(2);

		select.selectByIndex(RandomIndex);

		// By value
		// select.selectByValue("B");

		// by visibleText
		/*
		 * if(driver.getCurrentUrl().contains("ar")) {
		 * select.selectByVisibleText("1 غرفة، 1 بالغ، 0 أطفال");
		 * 
		 * }else { select.selectByValue("1 Room, 1 Adult, 0 Children"); }
		 */

		WebElement SearchHotelButton = driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		SearchHotelButton.click();

	}

	@Test(priority = 10)
	public void CheckThePageFullyLoaded() throws InterruptedException {

		Thread.sleep(10000);
		String results = driver.findElement(By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']")).getText();

		boolean finished = results.contains("وجدنا") || results.contains("found");

		boolean ExpectedResult = true;

		Assert.assertEquals(finished, ExpectedResult);

	}

	@Test(priority = 11)
	public void SortResultsLowestToHighestPrice() {

		WebElement LowestPriceButton = driver
				.findElement(By.xpath("//button[@data-testid='HotelSearchResult__sort__LOWEST_PRICE']"));

		LowestPriceButton.click();

		WebElement PricesContainer = driver.findElement(By.cssSelector(".sc-htpNat.KtFsv.col-9"));

		List<WebElement> AllPrices = PricesContainer.findElements(By.className("Price__Value"));

		String LowestPrice = AllPrices.get(0).getText();
		String HighestPrice = AllPrices.get(AllPrices.size() - 1).getText();

		System.out.println(LowestPrice);
		System.out.println(HighestPrice);

		int LowestPriceAsInt = Integer.parseInt(LowestPrice);
		int HighestPriceAsInt = Integer.parseInt(HighestPrice);

		boolean ActualResults = LowestPriceAsInt < HighestPriceAsInt;
		boolean expectedResults = true;

		Assert.assertEquals(ActualResults, expectedResults);

	}

}
