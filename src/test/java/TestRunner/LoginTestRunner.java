package TestRunner;

import Config.SetUp;
import Pages.LoginPage;
import io.qameta.allure.Allure;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Utils;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class LoginTestRunner extends SetUp {
    LoginPage loginpage;
    @Test(priority = 1,description = "Admin tried to login with wrong creds!")
    public void LoginWithWrongCreds(){
        loginpage=new LoginPage(driver);
        loginpage.doLogin("admin","wrongpassword");
        String txtActual= driver.findElement(By.className("oxd-alert-content-text")).getText();
        Assert.assertTrue(txtActual.contains("Invalid credentials"));
    }
    @Test(priority = 2,description = "Admin can login with valid creds!",groups = "smoke")
    public void doLogin() throws InterruptedException, IOException, ParseException {
        loginpage=new LoginPage(driver);
        JSONArray jsonArray=Utils.readJSONList("./src/test/resources/employees.json");
        JSONObject empObject= (JSONObject) jsonArray.get(0);
        if((System.getProperty("username")!=null) && (System.getProperty("password")!=null)){
            loginpage.doLogin(System.getProperty("username"), System.getProperty("password"));
        }
        else {
            loginpage.doLogin(empObject.get("username").toString(), empObject.get("password").toString());
        }

        SoftAssert softAssert=new SoftAssert();
        softAssert.assertTrue(driver.findElement(By.className("oxd-userdropdown-img")).isDisplayed());
        String txtActual = String.valueOf(driver.findElement(By.className("oxd-topbar-header-breadcrumb-module")).getText());
        softAssert.assertTrue(txtActual.contains("Dashboard"));
        softAssert.assertAll();
        Allure.description("Admin login successfully.");
    }

}
