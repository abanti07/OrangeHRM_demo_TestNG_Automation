package TestRunner;

import Config.EmployeeModel;
import Config.SetUp;
import Pages.DashBoardPage;
import Pages.LoginPage;
import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.Utils;

import java.io.IOException;

public class DashBoardTestRunner extends SetUp {
    DashBoardPage dashBoardPage;

    @BeforeTest(groups = "smoke")
    public void dologin() throws IOException, ParseException {
        LoginPage loginPage = new LoginPage(driver);
        JSONArray jsonArray = Utils.readJSONList("./src/test/resources/employees.json");
        JSONObject empObject = (JSONObject) jsonArray.get(0);
        loginPage.doLogin(empObject.get("username").toString(), empObject.get("password").toString());

    }

    @Test(priority = 1,description = "Admin created new Employee with valid information")
    public void CreateEmployee() throws IOException, ParseException, InterruptedException {
        dashBoardPage = new DashBoardPage(driver);
        Faker faker = new Faker();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String userId = String.valueOf(faker.random().nextInt(1000, 9999));
        String userName = faker.name().username();
        String password = faker.internet().password();



        EmployeeModel model = new EmployeeModel();
        model.setFirstname(firstName);
        model.setLastname(lastName);
        model.setUserId(userId);
        model.setUsername(userName);
        model.setPassword(password);
        Thread.sleep(5000);

        dashBoardPage.createEmployee(model);
        String textTitle = driver.findElement(By.xpath("//*[contains(text(),\"Personal Detail\")]")).getText();
        if (textTitle.contains("Personal Details")) {
            Utils.saveEmployeeInfo(model);
        }
        Allure.description("Admin created new employee successfully");
    }

    @Test(priority = 2,description = "Admin tried to create new Employee without name")
    public void CreateEmpoyeeWithoutName() throws InterruptedException {
        dashBoardPage = new DashBoardPage(driver);
        Faker faker = new Faker();

        String userId = String.valueOf(faker.random().nextInt(1000, 9999));
        Thread.sleep(3000);
        String userName = faker.name().username();
        String password = faker.internet().password();


        EmployeeModel model = new EmployeeModel();

        model.setUserId(userId);
        model.setUsername(userName);
        model.setPassword(password);
        dashBoardPage.CreateEmployeeWithoutName(model);
        Thread.sleep(7000);


        String txtTitle = driver.findElement(By.className("oxd-input-field-error-message")).getText();
        Assert.assertEquals(txtTitle, "Required");

    }

    @Test(priority = 3,description = "Admin tried to create new Employee without user name")
    public void createEmployeeWithoutUsername() throws InterruptedException {
        dashBoardPage = new DashBoardPage(driver);
        Faker faker = new Faker();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String userId = String.valueOf(faker.random().nextInt(1000, 9999));
        Thread.sleep(3000);
        String password = faker.internet().password();


        EmployeeModel model = new EmployeeModel();
        model.setFirstname(firstName);
        model.setLastname(lastName);
        model.setUserId(userId);
        model.setPassword(password);
        Thread.sleep(5000);

        dashBoardPage.createEmployeeWithoutUsername(model);
        String txtTitle = driver.findElement(By.className("oxd-input-field-error-message")).getText();
        Assert.assertEquals(txtTitle, "Required");
    }

    @Test(priority = 4,description = "Admin tried to create new employee with invalid username")
    public void createEmployeewithInvalidUsername() throws InterruptedException {
        dashBoardPage = new DashBoardPage(driver);
        Faker faker = new Faker();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String userId = String.valueOf(faker.random().nextInt(1000, 9999));
        Thread.sleep(3000);
        String userName = "test";
        String password = faker.internet().password();

        EmployeeModel model = new EmployeeModel();
        model.setFirstname(firstName);
        model.setLastname(lastName);
        model.setUserId(userId);
        model.setUsername(userName);
        model.setPassword(password);
        dashBoardPage.CreateEmployeeWithInvalidUsername(model);
        Thread.sleep(5000);

        String txtActual = driver.findElement(By.className("oxd-input-field-error-message")).getText();
        Assert.assertEquals(txtActual, "Should be at least 5 characters");

    }

    @Test(priority = 5,description = "Admin tried to create new employee with wrong password")
    public void createEmployeeWithWrongPassword() throws InterruptedException {
        dashBoardPage = new DashBoardPage(driver);
        Faker faker = new Faker();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String userId = String.valueOf(faker.random().nextInt(1000, 9999));
        Thread.sleep(3000);
        String userName = faker.name().username();
        String password = faker.internet().password();

        EmployeeModel model = new EmployeeModel();
        model.setFirstname(firstName);
        model.setLastname(lastName);
        model.setUserId(userId);
        model.setUsername(userName);
        model.setPassword(password);
        dashBoardPage.createEmployeeWithWrongPassword(model);
        Thread.sleep(5000);

        String txtTitle = driver.findElement(By.className("oxd-input-field-error-message")).getText();
        Assert.assertEquals(txtTitle, "Passwords do not match");
    }


    @Test(priority = 6,description = "Admin can search employee by employeeId",groups = "smoke")
    public void SearchEmployeebyEmployeeId() throws IOException, ParseException, InterruptedException {
        dashBoardPage = new DashBoardPage(driver);

        JSONArray empArray = Utils.readJSONList("./src/test/resources/employees.json");
        JSONObject empObj = (JSONObject) empArray.get(empArray.size() - 1);
        String employeeId = empObj.get("userId").toString();
        Thread.sleep(2000);
        dashBoardPage.SearchEmployeeByEmployeeId(employeeId);
        Allure.description("Admin search employee by employeeId");
    }

    @Test(priority = 7,description = "Admin successfully logs out",groups = "smoke")
    public void logout() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogout();
        String txtActual= driver.findElement(By.className("orangehrm-login-title")).getText();
        Assert.assertEquals(txtActual,"Login");
        Allure.description("Admin successfully logs out!");
    }

}
