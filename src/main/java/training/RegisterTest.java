package treinamento;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegisterTest {

    @Test
    void register(){

        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200,720));
        driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/testPages/componentes.html");

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Jeane");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Cardoso");
        driver.findElement(By.id("elementosForm:sexo:1")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();

        new Select(driver.findElement(By.id("elementosForm:escolaridade"))).selectByVisibleText("Superior");
        new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Natacao");
        new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Corrida");
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("I'm registering an user");
        driver.findElement(By.id("elementosForm:cadastrar")).click();

        assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
        assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("Jeane"));
        assertEquals("Sobrenome: Cardoso", driver.findElement(By.id("descSobrenome")).getText());
        assertEquals("Sexo: Feminino", driver.findElement(By.id("descSexo")).getText());
        assertEquals("Escolaridade: superior", driver.findElement(By.id("descEscolaridade")).getText());
        assertEquals("Comida: Carne Frango Pizza", driver.findElement(By.id("descComida")).getText());
        assertEquals("Esportes: Natacao Corrida", driver.findElement(By.id("descEsportes")).getText());
        assertEquals("Sugestoes: I'm registering an user", driver.findElement(By.id("descSugestoes")).getText());


        driver.quit();
    }
}
