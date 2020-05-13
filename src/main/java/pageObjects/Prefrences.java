package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Prefrences {
	
	public Prefrences(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Preference']")
	private WebElement prefrence;
	public WebElement getPrefrence() {
		return prefrence;
	}
	@AndroidFindBy(xpath="//android.widget.TextView[@text='3. Preference dependencies']")
	private WebElement dependencies;
	public WebElement getDependencies() {
		return dependencies;
	}
	@AndroidFindBy(id = "android:id/checkbox")
	private WebElement checkbox;
	public WebElement getCheckbox() {
		return checkbox;
	}
	@AndroidFindBy(xpath="(//android.widget.RelativeLayout)[2]")
	private WebElement settings;
	public WebElement getSettings() {
		return settings;
	}
	@AndroidFindBy(id = "android:id/edit")
	private WebElement wifiValue;
	public WebElement getWifivalue() {
		return wifiValue;
	}
	@AndroidFindBy(className = "android.widget.Button")
	private List<WebElement> button;
	public List<WebElement> getButton(){
		return button;
	}

}
