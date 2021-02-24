package training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertEquals;

@DisplayName("Testing rules")
public class RulesTest {

    private final WebDriver driver = new ChromeDriver();

    @BeforeEach
    void setUp(){
        driver.manage().window().setSize(new Dimension(1200,720));
        driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/testPages/componentes.html");
    }

    @Test
    @DisplayName("Should valid name text")
    void validName(){

        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        assertEquals("Nome eh obrigatorio", alert.getText());
        driver.quit();
    }
    @Test
    @DisplayName("Should valid surname text")
    void validSurname(){

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Name");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        assertEquals("Sobrenome eh obrigatorio", alert.getText());
        driver.quit();
    }

    @Test
    @DisplayName("Should valid sex button")
    void validSex(){

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Name");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Surname");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        assertEquals("Sexo eh obrigatorio", alert.getText());
        driver.quit();
    }
    @Test
    @DisplayName("Shouls valid with eats meat or not")
    void validFood(){

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Name");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Surname");
        driver.findElement(By.id("elementosForm:sexo:1")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
        driver.quit();
    }
    @Test
    @DisplayName("Should valid with play sports or not")
    void validSports(){

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Name");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Surname");
        driver.findElement(By.id("elementosForm:sexo:1")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();
        Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));
        combo.selectByVisibleText("Karate");
        combo.selectByVisibleText("O que eh esporte?");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        assertEquals("Voce faz esporte ou nao?", alert.getText());
        driver.quit();
    }

}
