package test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@DisplayName("Testing Google browser")
public class GoogleTest {

    @Test
    @DisplayName("Checking google title")
    public void test(){
        //System.setProperty("webdriver.chrome.driver", "D:/Drivers");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200, 720));
        driver.get("http://www.google.com");
        Assert.assertEquals("Google", driver.getTitle());
        driver.quit();
    }
    @Test
    @DisplayName("Checking google title")
    public void shouldSearchOnGoogle(){

        WebDriver driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.get("http://www.google.com");
        driver.findElement(By.name("q")).click();
        driver.findElement(By.name("q")).sendKeys("kung fu panda");
        driver.findElement(By.name("q")).submit();

        //driver.quit();
    }

}

