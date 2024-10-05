package homePage;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Parameters {

	WebDriver driver = new ChromeDriver();

	String AlmosaferURL = "https://www.almosafer.com/en";

	String ExpectedDefaultLanage = "en";
	Random rand = new Random();

	String ExpectedCurrency = "SAR";
	String ExpectedContactNumber = "+966554400000";
	boolean ExpectedResultForThelogo = true;
	String ExpectedValue = "false";
	LocalDate todayDate = LocalDate.now();

	int Today = todayDate.getDayOfMonth();

	int Tomorrow = todayDate.plusDays(1).getDayOfMonth();
	int TheDayAfterTomorrow = todayDate.plusDays(2).getDayOfMonth();

	String[] ArabicCities = { "دبي", "جدة" };
	int ArabicCityIndex = rand.nextInt(ArabicCities.length);

	String[] EnglishCities = { "Dubai", "Jeddah", "Riyadh" };
	int EnglishCityIndex = rand.nextInt(EnglishCities.length);
	boolean ExpectedResult = true;
	boolean expectedResults = true;

	public void GeneralSetup() {

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.get(AlmosaferURL);
	}

	public void RandomlySelectTheLanguage() {
		String[] URLs = { "https://www.almosafer.com/en", "https://www.almosafer.com/ar" };

		int RandomIndex = rand.nextInt(URLs.length);

		driver.get(URLs[RandomIndex]);
	}

}
