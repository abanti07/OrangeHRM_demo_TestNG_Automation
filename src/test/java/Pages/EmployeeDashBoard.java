package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EmployeeDashBoard {
    @FindBy(className = "oxd-main-menu-item--name")
    public List<WebElement> menuItems;
    @FindBy(className = "oxd-radio-input")
    List<WebElement> btnRadio;
    @FindBy(css = "[type=submit]")
    List<WebElement> submit;
    @FindBy(className = "oxd-select-text")
    List<WebElement> dropDown;

    public EmployeeDashBoard(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void selectGender() throws InterruptedException {

        Thread.sleep(5000);
        //clicking on gender
        btnRadio.get(1).click();
        submit.get(0).click();
    }

    public void selectBlood() throws InterruptedException {
        //clicking on Blood group
        //Thread.sleep(5000);
        dropDown.get(2).click();
        dropDown.get(2).sendKeys(Keys.ARROW_DOWN);
        dropDown.get(2).sendKeys(Keys.ARROW_DOWN);
        dropDown.get(2).sendKeys(Keys.ARROW_DOWN);
        dropDown.get(2).sendKeys(Keys.ARROW_DOWN);
        dropDown.get(2).sendKeys(Keys.ARROW_DOWN);
        dropDown.get(2).sendKeys(Keys.ENTER);
        submit.get(0).click();
    }
}
