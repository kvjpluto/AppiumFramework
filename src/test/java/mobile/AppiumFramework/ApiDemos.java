package mobile.AppiumFramework;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.Prefrences;

public class ApiDemos extends AppiumDriver{
	@Test(dataProvider = "InputData")
	public void setWIFI(String input) throws IOException, InterruptedException {
		service=startServer();
		AndroidDriver<AndroidElement> driver=capabilities("ApiDemo");
		
		Prefrences prefrences=new Prefrences(driver);
		prefrences.getPrefrence().click();
		prefrences.getDependencies().click();
		prefrences.getCheckbox().click();
		prefrences.getSettings().click();
		prefrences.getWifivalue().sendKeys(input);
		prefrences.getButton().get(1).click();
		service.stop();
	}
	@DataProvider(name="InputData")
	public String[] getDataEdit() throws IOException, ParseException {
		FileReader reader= new FileReader(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\Data.json");
	JSONParser parser =new JSONParser();
	Object obj=parser.parse(reader);
	JSONObject jsonobject=(JSONObject)obj;
	JSONArray dataArray=(JSONArray)jsonobject.get("values");
	String object[]= new String[dataArray.size()];
	for(int i=0;i<dataArray.size();i++) {
		object[i]=(String)dataArray.get(i);
	}
	return object;

	}

}
