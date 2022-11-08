import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AnnaAbgTest {

    /**
     * //TC_1_1  - Тест кейс:
     * //1. Открыть страницу (baseURL) https://openweathermap.org/
     * //2. Набрать в строке поиска город Paris
     * //3. Нажать пункт меню Search
     * //4. Из выпадающего списка выбрать Paris, FR
     * //5. Подтвердить, что заголовок изменился на "Paris, FR"
     */

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();  // WebDriver - Interface from Selenium
        // Arrange
        // Test Data
        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        // Act
        driver.get(url);  // open Base Page

        Thread.sleep(5000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id='weather-widget']//input[@placeholder='Search city']")
        );

        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id='weather-widget']//button[@type='submit']")
        );

        searchButton.click();

        Thread.sleep(3000);

        WebElement choiceParisFRInDropDownMenu = driver.findElement(
                By.xpath("//ul[@class='search-dropdown-menu']/li/span[text()='Paris, FR ']")
        );

        choiceParisFRInDropDownMenu.click();

        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@id='weather-widget']//h2")
        );

        Thread.sleep(2000);
        String actualResult = h2CityCountryHeader.getText();

        // Assert
        Assert.assertEquals(actualResult, expectedResult);

        Thread.sleep(3000);

        driver.quit();

    }

}
