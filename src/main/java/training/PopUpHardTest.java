package training;

import dsl.DSL;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.openqa.selenium.By.tagName;

public class PopUpHardTest {

    private final WebDriver driver = new ChromeDriver();
    private final DSL dsl = new DSL(driver);

    @BeforeEach
    void setUp(){
        driver.manage().window().setSize(new Dimension(1200,720));
        driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/testPages/componentes.html");
    }
    @AfterEach
    void finish(){
        driver.quit();
    }

    @Test
    void shouldInteractsWithPopUpHard(){

        dsl.clickButton("buttonPopUpHard");
        dsl.changeWindow((String) driver.getWindowHandles().toArray()[1]);
        dsl.write(By.tagName("textarea"), "Deu certo?");
        dsl.changeWindow((String) driver.getWindowHandles().toArray()[0]);
        dsl.write(By.tagName("textarea"), "e agora?");

    }

}

