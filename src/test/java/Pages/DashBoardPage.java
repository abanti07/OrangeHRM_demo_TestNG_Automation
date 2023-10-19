package Pages;

import Config.EmployeeModel;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DashBoardPage {
    @FindBy(className = "oxd-main-menu-item--name")
    List<WebElement> menuItems;
    @FindBy(className = "oxd-button")
    List<WebElement> buttons;
    @FindBy(className = "oxd-input")
    List<WebElement> formTxtField;
    @FindBy(className = "oxd-switch-input")
    WebElement btnSwitch;

    public DashBoardPage(WebDriver driver){

        PageFactory.initElements(driver,this);
    }
    public void createEmployee(EmployeeModel model){
        menuItems.get(1).click(); //Click PIM
        buttons.get(2).click();//click add
        formTxtField.get(1).sendKeys(model.getFirstname());//first name
        formTxtField.get(3).sendKeys(model.getLastname());//last name
        formTxtField.get(4).sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
        formTxtField.get(4).sendKeys(model.getUserId()); //userid
        btnSwitch.click();
        formTxtField.get(5).sendKeys(model.getUsername());//user name
        formTxtField.get(6).sendKeys(model.getPassword());//password
        formTxtField.get(7).sendKeys(model.getPassword());//confirm password
        buttons.get(1).click();
    }

    public void CreateEmployeeWithoutName(EmployeeModel model){
        menuItems.get(1).click(); //Click PIM
        buttons.get(2).click();//click add
        formTxtField.get(4).sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
        formTxtField.get(4).sendKeys(model.getUserId()); //userid
        btnSwitch.click();
        formTxtField.get(5).sendKeys(model.getUsername());//user name
        formTxtField.get(6).sendKeys(model.getPassword());//password
        formTxtField.get(7).sendKeys(model.getPassword());//confirm password
        buttons.get(1).click();
    }
    public void createEmployeeWithoutUsername(EmployeeModel model){
        menuItems.get(1).click(); //Click PIM
        buttons.get(2).click();//click add
        formTxtField.get(1).sendKeys(model.getFirstname());//first name
        formTxtField.get(3).sendKeys(model.getLastname());//last name
        formTxtField.get(4).sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
        formTxtField.get(4).sendKeys(model.getUserId()); //userid
        btnSwitch.click();
        formTxtField.get(6).sendKeys(model.getPassword());//password
        formTxtField.get(7).sendKeys(model.getPassword());//confirm password
        buttons.get(1).click();
    }
    public void CreateEmployeeWithInvalidUsername(EmployeeModel model){
        menuItems.get(1).click(); //Click PIM
        buttons.get(2).click();//click add
        formTxtField.get(1).sendKeys(model.getFirstname());//first name
        formTxtField.get(3).sendKeys(model.getLastname());//last name
        formTxtField.get(4).sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
        formTxtField.get(4).sendKeys(model.getUserId()); //userid
        btnSwitch.click();
        formTxtField.get(5).sendKeys(model.getUsername());//user name
        formTxtField.get(6).sendKeys(model.getPassword());//password
        formTxtField.get(7).sendKeys(model.getPassword());//confirm password
        buttons.get(1).click();
    }

    public void createEmployeeWithWrongPassword(EmployeeModel model) throws InterruptedException {
        menuItems.get(1).click(); //Click PIM
        buttons.get(2).click();//click add
        formTxtField.get(1).sendKeys(model.getFirstname());//first name
        formTxtField.get(3).sendKeys(model.getLastname());//last name
        formTxtField.get(4).sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
        formTxtField.get(4).sendKeys(model.getUserId()); //userid
        Thread.sleep(2000);
        btnSwitch.click();
        formTxtField.get(5).sendKeys(model.getUsername());//user name
        formTxtField.get(6).sendKeys(model.getPassword());//password
        formTxtField.get(7).sendKeys("wrongpassword");//confirm password
        buttons.get(1).click();
    }

    public void SearchEmployeeByEmployeeId(String employeeId){
        menuItems.get(1).click();//click PIM
        formTxtField.get(1).sendKeys(employeeId);
        buttons.get(1).click();
    }
}
