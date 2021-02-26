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
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@DisplayName("Testing rules")
public class RulesTest {

    private final WebDriver driver = new ChromeDriver();
    private final DSL dsl = new DSL(driver);;

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
    @DisplayName("Register an user")
    void register(){

        dsl.write("elementosForm:nome","Jeane");
        dsl.write("elementosForm:sobrenome","Cardoso");
        dsl.clickRadio("elementosForm:sexo:1");
        dsl.clickRadio("elementosForm:comidaFavorita:0");
        dsl.clickRadio("elementosForm:comidaFavorita:1");
        dsl.clickRadio("elementosForm:comidaFavorita:2");
        dsl.selectCombo("elementosForm:escolaridade","Superior");
        dsl.selectCombo("elementosForm:esportes","Natacao");
        dsl.selectCombo("elementosForm:esportes","Corrida");
        dsl.write("elementosForm:sugestoes", "I'm registering an user");
        dsl.clickButton("elementosForm:cadastrar");

        assertTrue(dsl.getText("resultado").startsWith("Cadastrado!"));
        assertTrue(dsl.getText("descNome").endsWith("Jeane"));
        assertEquals("Sobrenome: Cardoso", dsl.getText("descSobrenome"));
        assertEquals("Sexo: Feminino", dsl.getText("descSexo"));
        assertEquals("Escolaridade: superior", dsl.getText("descEscolaridade"));
        assertEquals("Comida: Carne Frango Pizza", dsl.getText(("descComida")));
        assertEquals("Esportes: Natacao Corrida", dsl.getText(("descEsportes")));
        assertEquals("Sugestoes: I'm registering an user", dsl.getText("descSugestoes"));
    }

    @Test
    @DisplayName("Should valid name text")
    void validName(){

        dsl.clickButton("elementosForm:cadastrar");
        Alert alert = driver.switchTo().alert();
        assertEquals("Nome eh obrigatorio", alert.getText());
    }
    @Test
    @DisplayName("Should valid surname text")
    void validSurname(){

        dsl.write("elementosForm:nome","Name");
        dsl.clickButton("elementosForm:cadastrar");
        Alert alert = driver.switchTo().alert();
        assertEquals("Sobrenome eh obrigatorio", alert.getText());
    }

    @Test
    @DisplayName("Should valid sex button")
    void validSex(){

        dsl.write("elementosForm:nome","Name");
        dsl.write("elementosForm:sobrenome","Surname");
        dsl.clickButton("elementosForm:cadastrar");
        Alert alert = driver.switchTo().alert();
        assertEquals("Sexo eh obrigatorio", alert.getText());
    }
    @Test
    @DisplayName("Should valid with eats meat or not")
    void validFood(){

        dsl.write("elementosForm:nome","Name");
        dsl.write("elementosForm:sobrenome","Surname");
        dsl.clickRadio("elementosForm:sexo:1");
        dsl.clickRadio("elementosForm:comidaFavorita:1");
        dsl.clickRadio("elementosForm:comidaFavorita:3");
        dsl.clickButton("elementosForm:cadastrar");
        Alert alert = driver.switchTo().alert();
        assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
    }
    @Test
    @DisplayName("Should valid with play sports or not")
    void validSports(){

        dsl.write("elementosForm:nome","Name");
        dsl.write("elementosForm:sobrenome","Surname");
        dsl.clickRadio("elementosForm:sexo:1");
        dsl.clickRadio("elementosForm:comidaFavorita:1");
        dsl.selectCombo("elementosForm:esportes","Karate");
        dsl.selectCombo("elementosForm:esportes","O que eh esporte?");
        dsl.clickButton("elementosForm:cadastrar");
        Alert alert = driver.switchTo().alert();
        assertEquals("Voce faz esporte ou nao?", alert.getText());
    }

}
