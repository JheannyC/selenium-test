package training;

import dsl.DSL;
import dsl.FieldTrainingPage;
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
    private FieldTrainingPage page;

    @BeforeEach
    void setUp(){
        driver.manage().window().setSize(new Dimension(1200,720));
        driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/testPages/componentes.html");
        page = new FieldTrainingPage(driver);
    }
    @AfterEach
    void finish(){
        driver.quit();
    }

    @Test
    @DisplayName("Should write a text in a text field")
    void shouldWriteTextField (){

        page.setName( "Teste de escrita");
        assertEquals("Teste de escrita", dsl.getFieldValue("elementosForm:nome"));
    }

    @Test
    @DisplayName("Should write a text in text area")
    void shoulWritTextArea(){

        page.setSugestion("testing");
        assertEquals("testing", dsl.getFieldValue("elementosForm:sugestoes"));
    }

    @Test
    @DisplayName("Should mark an option in radio button")
    void shoulMarkRadioButton(){

        page.setMaleSex();
        assertTrue(dsl.isRadioMarked("elementosForm:sexo:0"));
    }

    @Test
    @DisplayName("Should mark an option in check box")
    void shoulMarkCheckBox(){

        page.favoriteFoodPizza();
        assertTrue(dsl.isRadioMarked("elementosForm:comidaFavorita:2"));
    }

    @Test
    @DisplayName("Should select an option")
    void shoulSelect(){

        page.setScholarity("Mestrado");
        assertEquals("Mestrado", dsl.getComboValue("elementosForm:escolaridade"));
    }

    @Test
    @DisplayName("Should select an option")
    void shoulVerifyComboValues(){

        assertEquals(8, dsl.getComboOptions("elementosForm:escolaridade"));
        assertTrue(dsl.verifyComboOption("elementosForm:escolaridade", "Mestrado"));
    }

    @Test
    @DisplayName("Should select multiple options")
    void shoulVerifyComboMultiple() {

        dsl.selectCombo("elementosForm:esportes", "Natacao");
        dsl.selectCombo("elementosForm:esportes", "Corrida");

        List<String> allSelectedOptions = dsl.getComboValues("elementosForm:esportes");
        assertEquals(2, allSelectedOptions.size());

        dsl.deselectCombo("elementosForm:esportes", "Corrida");
        allSelectedOptions = dsl.getComboValues("elementosForm:esportes");
        assertEquals(1, allSelectedOptions.size());
        assertTrue(allSelectedOptions.contains("Natacao"));
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

    @Test
    @DisplayName("Testint with Javascript")
    void jsTest(){
        dsl.executeJS("document.getElementById('elementosForm:nome').value = 'Escrita com js'");

        WebElement element = driver.findElement(By.id("elementosForm:nome"));
        dsl.executeJS("arguments[0].style.border = arguments[1]",element, "solid 4px red");
    }

}



