package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage {
	public FormPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
 	}	
	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Enter name here']")
	private WebElement textField;
	public WebElement getTextField() {
		return textField;
	}
	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Female']")
	private WebElement radio;
	public WebElement getRadio() {
		return radio;
	}
	@AndroidFindBy(uiAutomator = "text(\"Afghanistan\")")
	private WebElement dropDown;
	public WebElement getDropDown() {
		return dropDown;
	}
	@AndroidFindBy(uiAutomator = "text(\"Argentina\")")
	private WebElement country;
	public WebElement getCountry() {
		return country;
	}
	@AndroidFindBy(uiAutomator = "text(\"Let's  Shop\")")	
	private WebElement shopButton;
	public WebElement getShopButton() {
		return shopButton;
	}
	@AndroidFindBy(xpath = "//*[@text='ADD TO CART']")
	private WebElement addToCart;
	public WebElement getAddToCart() {
		return addToCart;
	}
	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement cartButton;
	public WebElement getCartButton() {
		return cartButton;
	}
	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productPrice;
	public List<WebElement> getProductPrice() {
		return productPrice;
	}
	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalPrice;
	public WebElement getTotalPrice() {
		return totalPrice;
	}
	@AndroidFindBy(className = "android.widget.CheckBox")
	private WebElement discountcheckbox;
	public WebElement getDiscountCheckbox() {
		return discountcheckbox;
	}
	@AndroidFindBy(xpath = "//*[@text='Please read our terms of conditions']")
	private WebElement tnc;
	public WebElement gettnc() {
		return tnc;
	}
	@AndroidFindBy(id = "android:id/button1")
	private WebElement closeTNC;
	public WebElement getCloseTNC() {
		return closeTNC;
	}
	@AndroidFindBy(uiAutomator = "text(\"Visit to the website to complete purchase\")")
	private WebElement visitWebsite;
	public WebElement getVisitWebsite() {
		return visitWebsite;
	}
}
