package treinamento;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

@DisplayName("Testing alerts")
public class AlertTest {

    @Test
    @DisplayName("Should interacts with simple alert")
    void shouldInteractsWithSimpleAlert() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200,720));
        driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/testPages/componentes.html");

        driver.findElement(By.id("alert")).click();
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        assertEquals("Alert Simples", text);
        alert.accept();
        driver.findElement(By.id("elementosForm:nome")).sendKeys(text);
        driver.quit();
    }


    @Test
    @DisplayName("Should interacts with confirm alert")
    void shouldInteractsWithConfirmAlert() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200,720));
        driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/testPages/componentes.html");

        driver.findElement(By.id("confirm")).click();
        Alert alert = driver.switchTo().alert();
        assertEquals("Confirm Simples", alert.getText());
        alert.accept();
        assertEquals("Confirmado", alert.getText());
        alert.accept();

        driver.findElement(By.id("confirm")).click();
        alert = driver.switchTo().alert();
        assertEquals("Confirm Simples", alert.getText());
        alert.dismiss();
        assertEquals("Negado", alert.getText());
        alert.accept();
        driver.quit();
    }

    @Test
    @DisplayName("Should interacts with prompt alert")
    void shouldInteractsWithPromptAlert() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200,720));
        driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/testPages/componentes.html");
        driver.findElement(By.id("prompt")).click();

        Alert alert = driver.switchTo().alert();
        String numero = "1234";

        assertEquals("Digite um numero", alert.getText());
        alert.sendKeys(numero);
        alert.accept();
        assertEquals("Era " +numero +"?", alert.getText());
        alert.accept();
        assertEquals(":D", alert.getText());
        alert.accept();
        driver.quit();
    }
}
