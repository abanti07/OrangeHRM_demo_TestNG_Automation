package TestRunner;

import Config.SetUp;
import Pages.EmployeeDashBoard;
import Pages.LoginPage;
import io.qameta.allure.Allure;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Utils;

import java.io.IOException;

public class EmployeeTestRunner extends SetUp {
    LoginPage loginpage;
    EmployeeDashBoard employeeDashBoard;

    @Test(priority = 1,description = "New employee tried to login with valid creds!",groups = "smoke")
    public void userLogin() throws IOException, ParseException, InterruptedException {
        loginpage = new LoginPage(driver);
        JSONArray empList = Utils.readJSONList("./src/test/resources/employees.json");
        JSONObject empObject = (JSONObject) empList.get(empList.size() - 1);
        String username = (String) empObject.get("username");
        String password = (String) empObject.get("password");
        loginpage.doLogin(username, password);

        //full name_6
        String firstname = (String) empObject.get("firstname");
        String lastname = (String) empObject.get("lastname");
        String fullname = firstname + " " + lastname;
        Thread.sleep(5000);

        SoftAssert softAssert = new SoftAssert();
        //softAssert.assertTrue(driver.findElement(By.className("oxd-userdropdown-img")).isDisplayed());
        String textActual = driver.findElement(By.className("oxd-userdropdown-name")).getText();
        softAssert.assertEquals(textActual, fullname);
        softAssert.assertAll();
        Allure.description("New employee login successfully with valid creds!");
    }

    @Test(priority = 2,description = "New employee can update informations")
    public void SelectEmployeeDetails() throws InterruptedException {
        employeeDashBoard = new EmployeeDashBoard(driver);
        driver.findElements(By.className("oxd-main-menu-item")).get(2).click();
        Thread.sleep(5000);
        employeeDashBoard.selectGender();
        //Thread.sleep(7000);
        //Utils.Scroll(driver,0,500);
        //employeeDashBoard.selectBlood();
        Allure.description("New employee successfully update information");
    }
}
