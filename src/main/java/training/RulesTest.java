package training;

import dsl.DSL;
import dsl.FieldTrainingPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@DisplayName("Testing rules")
public class RulesTest {

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
    @DisplayName("Register an user")
    void register(){

        page.setName("Jeane");
        page.setSurname("Cardoso");
        page.setFemaleSex();
        page.favoriteFoodMeat();
        page.favoriteFoodChicken();
        page.favoriteFoodPizza();
        page.setScholarity("Superior");
        page.setSport("Natacao");
        page.setSport("Corrida");
        page.setSugestion("I'm registering an user");
        page.register();

        assertTrue(page.getFormResult().startsWith("Cadastrado!"));
        assertTrue(page.getText().endsWith("Jeane"));
        assertEquals("Sobrenome: Cardoso", page.getSurnameText());
        assertEquals("Sexo: Feminino", page.getSexText());
        assertEquals("Escolaridade: superior", page.getEscholarityText());
        assertEquals("Comida: Carne Frango Pizza", page.getFoodText());
        assertEquals("Esportes: Natacao Corrida", page.getSportText());
        assertEquals("Sugestoes: I'm registering an user", page.getDescriptionText());
    }

    @Test
    @DisplayName("Should valid name text")
    void validName(){
        page.register();
        assertEquals("Nome eh obrigatorio", dsl.getAlertTextAccept());
    }
    @Test
    @DisplayName("Should valid surname text")
    void validSurname(){

        page.setName("Name");
        page.register();
        assertEquals("Sobrenome eh obrigatorio", dsl.getAlertTextAccept());
    }

    @Test
    @DisplayName("Should valid sex button")
    void validSex(){

        page.setName("Name");
        page.setSurname("Surname");
        page.register();
        Alert alert = driver.switchTo().alert();
        assertEquals("Sexo eh obrigatorio", alert.getText());
    }
    @Test
    @DisplayName("Should valid with eats meat or not")
    void validFood(){

        page.setName("Name");
        page.setSurname("Surname");
        page.setFemaleSex();
        page.favoriteFoodMeat();
        page.favoriteFoodVegetarian();
        page.register();
        assertEquals("Tem certeza que voce eh vegetariano?", dsl.getAlertTextAccept());
    }
    @Test
    @DisplayName("Should valid with play sports or not")
    void validSports(){

        page.setName("Name");
        page.setSurname("Surname");
        page.setFemaleSex();
        page.favoriteFoodMeat();
        page.setSport("Karate");
        page.setSport("O que eh esporte?");
        page.register();
        assertEquals("Voce faz esporte ou nao?", dsl.getAlertTextAccept());
    }

    @Test
    void shouldClickTableButton(){
        dsl.clickButtonTable("Nome", "Maria", "Botao", "elementosForm:tableUsuarios");
    }

}
