package dsl;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {

    private final WebDriver driver;

    public DSL(WebDriver driver){
        this.driver = driver;
    }

    public void write(String fieldId, String text){
        driver.findElement(By.id(fieldId)).sendKeys(text);
    }

    public void write(By by, String text){
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

    public void selectCombo(String id, String value){
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        combo.selectByVisibleText(value);
    }

    public String getComboValue(String id){
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        return combo.getFirstSelectedOption().getText();
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
    public void clickButton(String id){
        driver.findElement(By.id(id)).click();
   }
    public void switchFrame(String frame){
       driver.switchTo().frame(frame);
   }
    public Alert switchAlert(){
      return driver.switchTo().alert();
   }
    public String getAlertText(){
        return switchAlert().getText();
   }
    public void acceptAlert(){
       switchAlert().accept();
   }
    public void dismissAlert(){
        switchAlert().dismiss();
    }
    public void defaultContent(){
        driver.switchTo().defaultContent();
    }
    public void switchPopUp(String popup){
        driver.switchTo().window(popup);
    }
    public void switchPopUp(Integer index){
        driver.switchTo().window( (String) driver.getWindowHandles().toArray()[index]);
    }
    public void sendAlertValue(Integer number){
        switchAlert().sendKeys(String.valueOf(number));
    }
    public void sendAlertValue(String text){
        switchAlert().sendKeys(String.valueOf(text));
    }
}
