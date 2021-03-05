package dsl;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class FieldTrainingPage {

    private DSL dsl;

    public FieldTrainingPage(WebDriver driver){
        dsl = new DSL(driver);
    }

    public void setName (String name){
        dsl.write("elementosForm:nome",name);
    }
    public void setSurname (String surname){
        dsl.write("elementosForm:sobrenome",surname);
    }
    public void setMaleSex(){
        dsl.clickRadio("elementosForm:sexo:0");
    }
    public void setFemaleSex(){
        dsl.clickRadio("elementosForm:sexo:1");
    }
    public void favoriteFoodMeat(){
        dsl.clickRadio("elementosForm:comidaFavorita:0");
    }
    public void favoriteFoodChicken(){
        dsl.clickRadio("elementosForm:comidaFavorita:1");
    }
    public void favoriteFoodPizza(){
        dsl.clickRadio("elementosForm:comidaFavorita:2");
    }
    public void favoriteFoodVegetarian(){
        dsl.clickRadio("elementosForm:comidaFavorita:3");
    }
    public void setScholarity(String scholarity){
        dsl.selectCombo("elementosForm:escolaridade", scholarity);
    }
    public void setSport(String... sports){
        for (String sport:sports){
            dsl.selectCombo("elementosForm:esportes", sport);
        }
    }
    public void setSugestion(String sugestion){
        dsl.write("elementosForm:sugestoes",sugestion);
    }
    public void register(){
        dsl.clickButton("elementosForm:cadastrar");
    }
    public String getFormResult(){
        return  dsl.getText("resultado");
    }
    public String getText(){
        return dsl.getText("descNome");
    }

    public String getSurnameText() {
        return dsl.getText("descSobrenome");
    }

    public String getSexText() {
        return dsl.getText("descSexo");
    }

    public String getEscholarityText() {
        return dsl.getText("descEscolaridade");
    }

    public String getFoodText() {
        return dsl.getText("descComida");
    }

    public String getSportText() {
        return dsl.getText("descEsportes");
    }

    public String getDescriptionText() {
        return dsl.getText("descSugestoes");
    }
    public void switchAlert(WebDriver driver){
        driver.switchTo().alert();
    }

}
