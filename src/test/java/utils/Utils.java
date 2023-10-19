package utils;

import Config.EmployeeModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {
    public static void saveEmployeeInfo(EmployeeModel model) throws IOException, ParseException {
        String file="./src/test/resources/employees.json";

        JSONParser jsonParser=new JSONParser();
        JSONArray jsonArray= (JSONArray) jsonParser.parse(new FileReader(file));

        JSONObject empObject=new JSONObject();
        empObject.put("firstname",model.getFirstname());
        empObject.put("lastname",model.getLastname());
        empObject.put("userId",model.getUserId());
        empObject.put("username",model.getUsername());
        empObject.put("password",model.getPassword());

        jsonArray.add(empObject);

        FileWriter writer=new FileWriter(file);
        writer.write(jsonArray.toJSONString());
        writer.flush();
        writer.close();
    }
    public static JSONArray readJSONList(String filename) throws IOException, ParseException {

        JSONParser parser=new JSONParser();
       JSONArray jsonArray= (JSONArray) parser.parse(new FileReader(filename));
       return jsonArray;

    }
    public static void Scroll(WebDriver driver, int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(" + x + "," + y + ")");
    }

}
