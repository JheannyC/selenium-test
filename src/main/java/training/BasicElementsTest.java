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
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.tagName;

@DisplayName("Testing html training")
public class BasicElementsTest {

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
    @DisplayName("Should write a text in a text field")
    void shouldWriteTextField (){

        dsl.write("elementosForm:nome", "Teste de escrita");
        assertEquals("Teste de escrita", dsl.getFieldValue("elementosForm:nome"));
    }

    @Test
    @DisplayName("Should write a text in text area")
    void shoulWritTextArea(){

        dsl.write("elementosForm:sugestoes", "testing");
        assertEquals("testing", dsl.getFieldValue("elementosForm:sugestoes"));
    }

    @Test
    @DisplayName("Should mark an option in radio button")
    void shoulMarkRadioButton(){

        dsl.clickRadio("elementosForm:sexo:0");
        assertTrue(dsl.isRadioMarked("elementosForm:sexo:0"));
    }

    @Test
    @DisplayName("Should mark an option in check box")
    void shoulMarkCheckBox(){

        dsl.clickRadio("elementosForm:comidaFavorita:2");
        assertTrue(dsl.isRadioMarked("elementosForm:comidaFavorita:2"));
    }

    @Test
    @DisplayName("Should select an option")
    void shoulSelect(){

        dsl.selectCombo("elementosForm:escolaridade", "Mestrado");
        assertEquals("Mestrado", dsl.getComboValue("elementosForm:escolaridade"));
    }

    @Test
    @DisplayName("Should select an option")
    void shoulVerifyComboValues(){

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
    }

    @Test
    @DisplayName("Should select an option")
    void shoulVerifyComboMultiple() {

        dsl.selectCombo("elementosForm:esportes", "Natacao");
        dsl.selectCombo("elementosForm:esportes", "Corrida");

        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);
        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();

        assertEquals(2, allSelectedOptions.size());

        combo.deselectByVisibleText("Corrida");
        allSelectedOptions = combo.getAllSelectedOptions();

        assertEquals(1, allSelectedOptions.size());
    }

    @Test
    @DisplayName("Should click button")
    void shouldClickButton() {

        dsl.clickRadio("buttonSimple");
        WebElement button = driver.findElement(By.id("buttonSimple"));
        assertEquals("Obrigado!", button.getAttribute("value"));
    }

    @Test
    @DisplayName("Should click link")
    void shouldClickLink() {

        dsl.clickLink("Voltar");
        assertEquals("Voltou!", dsl.getText("resultado"));
    }

    @Test
    @DisplayName("Should search text in page")
    void shouldSearchText() {

        assertEquals("Campo de Treinamento", dsl.getText(tagName("h3")));
        assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.getText(className("facilAchar")));
    }

}



