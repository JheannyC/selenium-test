package dsl;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class DSL {

    private WebDriver driver;

    public DSL(WebDriver driver){
        this.driver = driver;
    }

    public void write(String fieldId, String text){
        driver.findElement(By.id(fieldId)).sendKeys(text);
    }

    public void write(By by, String text){
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(text);
    }

    public String getFieldValue (String fielId){
        return driver.findElement(By.id(fielId)).getAttribute("value");
    }

    public void clickRadio(String id){
        driver.findElement(By.id(id)).click();
    }

    public boolean isRadioMarked(String id){
        return driver.findElement(By.id(id)).isSelected();
    }

    public void clickCheck(String id){
        driver.findElement(By.id(id)).click();
    }
    public void isClickCheck(String id){
        driver.findElement(By.id(id)).isSelected();
    }
    public void selectCombo(String id, String value){
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        combo.selectByVisibleText(value);
    }
    public void deselectCombo(String id, String value){
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        combo.deselectByVisibleText(value);
    }

    public String getComboValue(String id){
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        return combo.getFirstSelectedOption().getText();
    }
    public List<String> getComboValues(String id) {
        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);
        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        List<String> values = new ArrayList<String>();
        for(WebElement opcao: allSelectedOptions) {
            values.add(opcao.getText());
        }
        return values;
    }
    public boolean verifyComboOption(String id, String opt){
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
        for(WebElement option: options) {
            if(option.getText().equals(opt)){
                return true;
            }
        }
        return false;
    }

    public int getComboOptions(String id){
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
        return options.size();
    }

    public void clickButton(String id){
        driver.findElement(By.id(id)).click();
    }

    public String getElementValue(String id){
       return driver.findElement(By.id(id)).getAttribute("value");
    }

    public void clickLink(String id){
        driver.findElement(By.linkText(id)).click();
    }
    public String getText(By by){
        return  driver.findElement(by).getText();
   }
    public String getText(String id){
        return getText(By.id(id));
   }

    public String getAlertText(){
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    public String getAlertTextAccept(){
        Alert alert = driver.switchTo().alert();
        String valor = alert.getText();
        alert.accept();
        return valor;

    }

    public String getAlertTextDismiss(){
        Alert alert = driver.switchTo().alert();
        String valor = alert.getText();
        alert.dismiss();
        return valor;

    }

    public void writeAlert (String valor) {
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(valor);
        alert.accept();
    }

    public void enterFrame(String id){
        driver.switchTo().frame(id);
    }
    public void exitFrame(){
        driver.switchTo().defaultContent();
    }
    public void changeWindow(String id){
        driver.switchTo().window(id);
    }

}
