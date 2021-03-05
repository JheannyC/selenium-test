package training;

import dsl.DSL;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    @DisplayName("Should interacts with frames")
    void shouldInteractsWithFrames(){

        dsl.enterFrame("frame1");
        dsl.clickButton("frameButton");
        String text = dsl.getAlertTextAccept();
        assertEquals("Frame OK!", text);
        dsl.exitFrame();
        dsl.write("elementosForm:nome",text);
    }

    @Test
    @DisplayName("Should interacts with hidden frame")
    void hiddenFrame(){
        WebElement frame = driver.findElement(By.id("frame2"));
        dsl.executeJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
        dsl.enterFrame("frame2");
        dsl.clickButton("frameButton");
        String message = dsl.getAlertTextAccept();
        assertEquals("Frame OK!", message);
    }

}
