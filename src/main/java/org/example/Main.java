package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Main {

    WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void startTest() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        // navigate to url
        driver.get("https://www.automationexercise.com");

        // verify that home page is visible successfully
        WebElement checkLogo = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[1]/div/a/img"));
        Assert.assertTrue(checkLogo.isDisplayed());

        // click on 'Products' button
        driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[2]/a")).click();

        // verify user is navigated to ALL PRODUCTS page successfully
        String expectedUrl = "https://www.automationexercise.com/products";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);

        // enter product name in search input and click search button
        WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"search_product\"]"));
        searchBox.sendKeys("tshirt");
        driver.findElement(By.xpath("//*[@id=\"submit_search\"]")).click();

        // verify 'SEARCHED PRODUCTS' is visible
        WebElement searched = driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div/h2"));
        Assert.assertTrue(searched.isDisplayed());
    }

    @After
    public void endTest() {
        driver.quit();
    }
}