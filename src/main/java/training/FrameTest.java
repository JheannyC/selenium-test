package training;

import dsl.DSL;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

@DisplayName("Testing frames")
public class FrameTest {

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
    void shouldInteractsWithFrames(){

        dsl.switchFrame("frame1");
        dsl.clickButton("frameButton");
        dsl.switchAlert();
        String text = dsl.getAlertText();
        assertEquals("Frame OK!", text);
        dsl.acceptAlert();
        dsl.defaultContent();
        dsl.write("elementosForm:nome",text);
    }
}
