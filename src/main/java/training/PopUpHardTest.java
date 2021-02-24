package training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PopUpHardTest {

    private final WebDriver driver = new ChromeDriver();

    @BeforeEach
    void setUp(){
        driver.manage().window().setSize(new Dimension(1200,720));
        driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/testPages/componentes.html");
    }

    @Test
    void shouldInteractsWithPopUpHard(){

        driver.findElement(By.id("buttonPopUpHard")).click();
        driver.switchTo().window( (String) driver.getWindowHandles().toArray()[1]);
        driver.findElement(By.tagName("textarea")).sendKeys("Certinho");
        driver.switchTo().window( (String) driver.getWindowHandles().toArray()[0]);
        driver.findElement(By.tagName("textarea")).sendKeys("Tudo ok!");
        driver.quit();
    }
}
