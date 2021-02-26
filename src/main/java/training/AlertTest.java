package training;

import dsl.DSL;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

@DisplayName("Testing alerts")
public class AlertTest {

    private WebDriver driver = new ChromeDriver();
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
    @DisplayName("Should interacts with simple alert")
    void shouldInteractsWithSimpleAlert() {

        dsl.clickButton("alert");
        dsl.switchAlert();
        String text = dsl.getAlertText();
        assertEquals("Alert Simples", text);
        dsl.acceptAlert();
        dsl.write("elementosForm:nome", text);
    }


    @Test
    @DisplayName("Should interacts with confirm alert")
    void shouldInteractsWithConfirmAlert() {

        dsl.clickButton("confirm");
        dsl.switchAlert();
        assertEquals("Confirm Simples", dsl.getAlertText());
        dsl.acceptAlert();
        assertEquals("Confirmado", dsl.getAlertText());
        dsl.acceptAlert();

        dsl.clickButton("confirm");
        dsl.switchAlert();
        assertEquals("Confirm Simples", dsl.getAlertText());
        dsl.dismissAlert();
        assertEquals("Negado", dsl.getAlertText());
        dsl.acceptAlert();
    }

    @Test
    @DisplayName("Should interacts with prompt alert")
    void shouldInteractsWithPromptAlert() {

        dsl.clickButton("prompt");
        dsl.switchAlert();
        String number = "1234";
        assertEquals("Digite um numero", dsl.getAlertText());
        dsl.sendAlertValue(number);
        dsl.acceptAlert();
        assertEquals("Era " + number +"?", dsl.getAlertText());
        dsl.acceptAlert();
        assertEquals(":D", dsl.getAlertText());
        dsl.acceptAlert();
    }
}
