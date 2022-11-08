import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OpenWeather_WebsiteTest {


    /**
     * TC# 1
     * 1. Открыть базовую ссылку
     * 2. Нажать на пункт меню Guide
     * 3. Подтвердить, что вы перешли на страницу со ссылкой
     * https://openweathermap.org/guide и что title этой страницы
     * OpenWeatherMap API guide - OpenWeatherMap
     */

    @Test(priority = 1)
    public void testH1Title_WhenSearchingCityCountry() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";

        // Arrange
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        // Act
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement linkPage = driver.findElement(By.xpath("//div[@id='desktop-menu']//a[@href='/guide']"));
        linkPage.click();


        // String getCurrentUrl = driver.getCurrentUrl();
        String titlePage = driver.getTitle();

        // Assert
        Assert.assertEquals(driver.getCurrentUrl(), expectedResult1);
        Thread.sleep(3000);
        Assert.assertEquals(titlePage, expectedResult2);

        driver.quit();
    }


    /**
     * TC_11_02
     * 1. Открыть базовую ссылку
     * 2. Нажать на единицы измерения Imperial: °F, mph
     * 3.  Подтвердить, что температура для города показана в Фарингейтах
     */

    @Test(priority = 2)
    public void testHeading_TemperatureScaleInFahrenheit() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";

        String tempF = "°F";
        String expectedResult = "°F";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        driver.findElement(By.xpath("// div[text()='Imperial: °F, mph']")).click();
        Thread.sleep(3000);

        String tempScaleFahrenheit = driver.findElement(
                By.xpath("//div[@class='current-temp']/span")).getText();

        String actualResult = tempScaleFahrenheit.substring((tempScaleFahrenheit.length() - 2));
        Thread.sleep(3000);

        Assert.assertEquals(actualResult, expectedResult);
        // Assert.assertTrue(tempScaleFahrenheit.getText().contains(tempF));   Option # 2

        driver.quit();
    }


    /**
     * TC_11_03 //
     * 1. Открыть базовую ссылку
     * 2. Подтвердить, что внизу страницы есть панель с текстом
     * “We use cookies which are essential for the site to work. We also use non-essential cookies to help us improve
     * our services. Any data collected is anonymised. You can allow all cookies or manage them individually.”
     * 3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”
     */

    @Test(priority = 3)
    public void testButtons_TemperatureScaleInFahrenheit() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";

        String expectedResult = "We use cookies which are essential for the site to work. "
                + "We also use non-essential cookies to help us improve our services. "
                + "Any data collected is anonymised. "
                + "You can allow all cookies or manage them individually.";


        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        // Assert.assertTrue(driver.findElement(By.className("stick-footer-panel__container")).isDisplayed()); // Optional

        WebElement cookiesDescription = driver.findElement(By.className("stick-footer-panel__description"));
        String footerPanelDescription = cookiesDescription.getText();
        Assert.assertEquals(footerPanelDescription, expectedResult);

        Assert.assertEquals(driver.findElements(
                By.xpath("//div[@class='stick-footer-panel__btn-container']/*")).size(), 2);

        Assert.assertTrue(driver.findElement(By.xpath("//button[text()='Allow all']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[@href='/cookies-settings']")).isDisplayed());

        driver.quit();

    }

    /**
     * TC_11_04
     * 1. Открыть базовую ссылку
     * 2. Подтвердить, что в меню Support есть 3 подменю с названиями
     * “FAQ”, “How to start” и “Ask a question”
     */

    @Test(priority = 4)
    public void testThreeSubmenus_InMenuSupport() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();  // WebDriver - Interface from Selenium
        String url = "https://openweathermap.org/";

        String expectedResult1 = "FAQ";
        String expectedResult2 = "How to start";
        String expectedResult3 = "Ask a question";


        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        driver.findElement(By.xpath("//div[@id='support-dropdown']")).click();
        // driver.findElement(By.xpath("//ul[@id='support-dropdown-menu']")).getText();

        String actualResult1 = driver.findElement(By.xpath("//a[@href='/faq']")).getText();
        String actualResult2 = driver.findElement(By.xpath("//a[@href='/appid']")).getText();
        String actualResult3 = driver.findElement(By.xpath("//a[@href='https://home.openweathermap.org/questions']")).getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        Thread.sleep(3000);
        Assert.assertEquals(actualResult2, expectedResult2);
        Thread.sleep(3000);
        Assert.assertEquals(actualResult3, expectedResult3);

        driver.quit();
    }


    /**
     * TC_11_05
     * 1. Открыть базовую ссылку
     * 2. Нажать пункт меню Support → Ask a question
     * 3. Заполнить поля Email, Subject, Message
     * 4. Не подтвердив CAPTCHA, нажать кнопку Submit
     * 5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.”
     */

    @Test(priority = 5)
    public void testErrorMessage() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";


        String expectedResult = "reCAPTCHA verification failed, please try again.";
        String email = "tester@gmail.com";
        String message = "Slow loading of the website";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        driver.findElement(By.xpath("//div[@id='support-dropdown']")).click();
        driver.findElement(By.xpath("//a[@href='https://home.openweathermap.org/questions']")).click();

        String mainWindows = driver.getWindowHandle(); // getting ID of Windows

        for (String windowsHandle : driver.getWindowHandles()) {
            if (!mainWindows.contentEquals(windowsHandle)) {
                driver.switchTo().window(windowsHandle);
                break;
            }
        }

        // Option # 2 -Move to another page / window
        //   ArrayList<String> tab2 = new ArrayList<String>(driver.getWindowHandles());
        //   driver.switchTo().window(tab2.get(1));


        WebElement emailField = driver.findElement(By.xpath("//input[@class='form-control string email required']"));
        emailField.click();
        emailField.sendKeys(email);


        WebElement subjectField = driver.findElement(By.xpath("//select[@id='question_form_subject']"));
        subjectField.click();

        WebElement selectSubjectField = driver.findElement(By.xpath("//select[@id='question_form_subject']/option[@value='Other']"));
        selectSubjectField.click();

        WebElement messageField = driver.findElement(By.xpath("//textarea[@id='question_form_message']"));
        messageField.sendKeys(message);

        WebElement submitButton = driver.findElement(By.xpath("//input[@class='btn btn-default']"));
        submitButton.click();
        Thread.sleep(3000);

        WebElement recaptchaText = driver.findElement(By.xpath("//div[@class='help-block']"));
        String errorMessage = recaptchaText.getText();

        Assert.assertEquals(errorMessage, expectedResult);

        driver.quit();
    }


    /**
     * TC_11_06
     * 1. Открыть базовую ссылку
     * 2. Нажать пункт меню Support → Ask a question
     * 3. Оставить значение по умолчанию в checkbox Are you an OpenWeather user?
     * 4. Оставить пустым поле Email
     * 5. Заполнить поля Subject, Message
     * 6. Подтвердить CAPTCHA
     * 7. Нажать кнопку Submit
     * 8. Подтвердить, что в поле Email пользователю будет показана ошибка
     * “can't be blank”
     */


    @Test(priority = 6)
    public void testCaptchaErrorMessage() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";

        String message = "Slow loading of the website";
        String expectedResult = "can't be blank";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);


        driver.findElement(By.xpath("//div[@id='support-dropdown']")).click();
        driver.findElement(By.xpath("//a[@href='https://home.openweathermap.org/questions']")).click();

        String mainWindows = driver.getWindowHandle(); // getting ID of Windows

        for (String windowsHandle : driver.getWindowHandles()) {
            if (!mainWindows.contentEquals(windowsHandle)) {
                driver.switchTo().window(windowsHandle);
                break;
            }
        }

        Thread.sleep(5000);
        driver.findElement(By.xpath("//select[@id='question_form_subject']")).click();
        driver.findElement(By.xpath("//select[@id='question_form_subject']/option[@value='Other']")).click();
        driver.findElement(By.xpath("//textarea[@id='question_form_message']")).sendKeys(message);
        Thread.sleep(5000);

        String mainWindows2 = driver.getWindowHandle();
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[title='reCAPTCHA']")));
        //  driver.findElement(By.xpath("//span[@id='recaptcha-anchor']")).click();

        driver.findElement(By.xpath("//div[@class='rc-anchor-center-container']")).click();

        // driver.findElement(By.xpath("//div[@class='rc-anchor-center-item rc-anchor-checkbox-holder']/span")).click();

        Thread.sleep(10000);

        driver.switchTo().window(mainWindows2);

        WebElement pressSubmit = driver.findElement(By.xpath("//*[@id='new_question_form']/div[9]/input"));
        pressSubmit.click();

        WebElement confirmErrorEmail = driver.findElement(By.xpath("//span[@class='help-block']"));

        String actualResult = confirmErrorEmail.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }


    /**
     * TC_11_07
     * 1. Открыть базовую ссылку
     * 2. Нажать на единицы измерения Imperial: °F, mph
     * 3. Нажать на единицы измерения Metric: °C, m/s
     * 4. Подтвердить, что в результате этих действий, единицы измерения температуры изменились с F на С
     */


    @Test(priority = 7)
    public void testTemperatureScaleChange() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";

        String tempValue = "°C";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        driver.findElement(By.xpath("// div[text()='Imperial: °F, mph']")).click();
        //driver.navigate().back();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[text()='Metric: °C, m/s']")).click();
        //tempScaleFahrenheit.click();

        Thread.sleep(3000);

        String tempScaleC = driver.findElement(
                By.xpath("//span[@class='heading'][contains(text(),'°C')]")).getText();

        Boolean actualResult = tempScaleC.contains(tempValue);

        Assert.assertTrue(actualResult);

        driver.quit();
    }


    /**
     * TC_11_08
     * 1. Открыть базовую ссылку
     * 2. Нажать на лого компании
     * 3. Дождаться, когда произойдет перезагрузка сайта, и подтвердить, что текущая ссылка не изменилась
     */

    @Test(priority = 8)
    public void testCurrentURL() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";

        String expectedResult = "https://openweathermap.org/";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        driver.findElement(
                By.xpath("//img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']")).click();

        String actualResult = driver.getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);

        driver.close();
    }


    /**
     * TC_11_09
     * 1. Открыть базовую ссылку
     * 2. В строке поиска в навигационной панели набрать "Rome"
     * 3. Нажать клавишу Enter
     * 4. Подтвердить, что вы перешли на страницу в ссылке которой содержатся слова “find” и “Rome”
     * 5. Подтвердить, что в строке поиска на новой странице вписано слово “Rome”
     */

    @Test(priority = 9)
    public void testLinkPage_TestWordRomeOnThePage() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";

        String expectedResultCity = "Rome";
        String expectedSearchVerb = "find";
        String expectedSearchNoun = "Rome";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement searchCityField = driver.findElement(By.xpath("//input[@type='text']"));
        searchCityField.click();
        searchCityField.sendKeys(expectedResultCity);
        searchCityField.sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        String currentUrl = driver.getCurrentUrl();

        Boolean actualResult;
        if (currentUrl.contains(expectedSearchVerb) && currentUrl.contains(expectedSearchNoun)) {
            actualResult = true;
        } else {
            actualResult = false;
        }

        Boolean expectedResult = currentUrl.contains(expectedSearchVerb) && currentUrl.contains(expectedSearchNoun);
        Assert.assertEquals(actualResult, expectedResult);

        String citySearch = driver.findElement(By.xpath("//input[@class]")).getAttribute("value");
        Assert.assertEquals(citySearch, expectedResultCity);

        driver.quit();
    }


    /**
     * TC_11_10
     * 1. Открыть базовую ссылку
     * 2. Нажать на пункт меню API
     * 3. Подтвердить, что на открывшейся странице пользователь видит 30
     * оранжевых кнопок
     */


    @Test(priority = 10)
    public void testThirtyButtons() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";
        int expectedResult = 30;

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);


        driver.findElement(By.xpath("//a[@href='/api']")).click();
        Thread.sleep(5000);

        int countButton = driver.findElements(
                By.xpath("//a[contains(@class, 'btn_block orange round') " +
                        "or contains(@class, 'ow-btn round btn-orange') ]")).size();

        // Option # 2
        // int cButton = driver.findElements(By.xpath("// a[contains(@class,'orange')]")).size();
        // int actualResult = cButton;

        Assert.assertEquals(countButton, expectedResult);

        driver.quit();
    }
}

