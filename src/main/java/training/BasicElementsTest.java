package treinamento;

import org.junit.Ignore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import javax.lang.model.element.Element;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@DisplayName("Testing html training")
public class BasicElementsTest {

    @Test
    @DisplayName("Shoul write a text in a text field")
    void shouldWriteTextField (){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200,720));
        driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/testPages/componentes.html");
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");
        assertEquals("Teste de escrita", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
        driver.quit();
    }

    @Test
    @DisplayName("Should write a text in text area")
    void shoulWritTextArea(){

        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200,720));
        driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/testPages/componentes.html");
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("test");
        assertEquals("test", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
        driver.quit();
    }

    @Test
    @DisplayName("Should mark an option in radio button")
    void shoulMarkRadioButton(){

        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200,720));
        driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/testPages/componentes.html");
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
        driver.quit();
    }

    @Test
    @DisplayName("Should mark an option in check box")
    void shoulMarkCheckBox(){

        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200,720));
        driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/testPages/componentes.html");
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
        assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
        driver.quit();
    }

    @Test
    @DisplayName("Should select an option")
    void shoulSelect(){

        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200,720));
        driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/testPages/componentes.html");
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        //combo.selectByIndex(3);
        //combo.selectByValue("superior");
        combo.selectByVisibleText("Mestrado");

        assertEquals("Mestrado", combo.getFirstSelectedOption().getText());
        driver.quit();
    }

    @Test
    @DisplayName("Should select an option")
    void shoulVerifyComboValues(){

        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200,720));
        driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/testPages/componentes.html");
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();

        assertEquals(8, options.size());
        boolean find = false;
        for(WebElement option : options){
            if(option.getText().equals("Mestrado")){
                find = true;
                break;
            }
        }
        assertTrue(find);
        driver.quit();
    }

    @Test
    @DisplayName("Should select an option")
    void shoulVerifyComboMultiple() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200, 720));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/testPages/componentes.html");
        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);
        combo.selectByVisibleText("Natacao");
        combo.selectByVisibleText("Corrida");
        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();

        assertEquals(2, allSelectedOptions.size());

        combo.deselectByVisibleText("Corrida");
        allSelectedOptions = combo.getAllSelectedOptions();

        assertEquals(1, allSelectedOptions.size());
        driver.quit();
    }

    @Test
    @DisplayName("Should click button")
    void shouldClickButton() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200, 720));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/testPages/componentes.html");
        WebElement button = driver.findElement(By.id("buttonSimple"));
        button.click();

        assertEquals("Obrigado!", button.getAttribute("value"));
        driver.quit();

    }

    @Test
    @DisplayName("Should click link")
    void shouldClickLink() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200, 720));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/testPages/componentes.html");
        WebElement link = driver.findElement(By.linkText("Voltar"));
        link.click();

        assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());
        driver.quit();
    }

    @Test
    @DisplayName("Should search text in page")
    void shouldSearchText() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200, 720));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/testPages/componentes.html");
        //assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
        assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());
        assertEquals("Cuidado onde clica, muitas armadilhas...", driver.findElement(By.className("facilAchar")).getText());
        driver.quit();
    }

}
