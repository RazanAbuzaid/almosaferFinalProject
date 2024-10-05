package homePage;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class myTestCases extends Parameters {

	@BeforeTest
	public void mySetUp() {

		GeneralSetup();

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

		Assert.assertEquals(ActualCurrency, ExpectedCurrency);

	}

	@Test(priority = 3)

	public void CheckTheContactNumbers() {

		String ActualContactNumber = driver.findElement(By.tagName("strong")).getText();

		Assert.assertEquals(ActualContactNumber, ExpectedContactNumber);

	}

	@Test(priority = 4)
	public void CheckQitafLogo() {

		WebElement TheFooter = driver.findElement(By.tagName("footer"));

		WebElement logo = TheFooter.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF"));

		boolean ActualResultForThelogo = logo.isDisplayed();

		Assert.assertEquals(ActualResultForThelogo, ExpectedResultForThelogo);

	}

	@Test(priority = 5)
	public void CheckHotelTabIsNotSelected() {

		String ActualValue = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"))
				.getAttribute("aria-selected");

		Assert.assertEquals(ActualValue, ExpectedValue);

	}

	@Test(priority = 6)
	public void CheckDepartureAndArrivalDate() {

		List<WebElement> depatureAndArrivalDates = driver.findElements(By.className("LiroG"));

		String ActualDepartureDate = depatureAndArrivalDates.get(0).getText();
		String ActualReturnDate = depatureAndArrivalDates.get(1).getText();

		int ActualDepartureDateInt = Integer.parseInt(ActualDepartureDate);
		int ActualReturnDateInt = Integer.parseInt(ActualReturnDate);

		Assert.assertEquals(ActualDepartureDateInt, Tomorrow);
		Assert.assertEquals(ActualReturnDateInt, TheDayAfterTomorrow);

	}

	@Test(priority = 7)
	public void RandomlyChangeTheLanguage() {

		RandomlySelectTheLanguage();

	}

	@Test(priority = 8)
	public void FillHotelTab() throws InterruptedException {

		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		HotelTab.click();

		String WebsiteURL = driver.getCurrentUrl();

		WebElement LocationField = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));

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

		WebElement SelectorVisitor = driver
				.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));

		Select select = new Select(SelectorVisitor);

		// By Index
		int RandomIndex = rand.nextInt(2);

		select.selectByIndex(RandomIndex);
		WebElement SearchHotelButton = driver
				.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		SearchHotelButton.click();

		// By value
		// select.selectByValue("B");

		// by visibleText
		/*
		 * if(driver.getCurrentUrl().contains("ar")) {
		 * select.selectByVisibleText("1 غرفة، 1 بالغ، 0 أطفال");
		 * 
		 * }else { select.selectByValue("1 Room, 1 Adult, 0 Children"); }
		 */

	}

	@Test(priority = 10)
	public void CheckThePageFullyLoaded() throws InterruptedException {

		Thread.sleep(10000);
		String results = driver.findElement(By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']"))
				.getText();

		boolean finished = results.contains("وجدنا") || results.contains("found");

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

		Assert.assertEquals(ActualResults, expectedResults);

	}

}
