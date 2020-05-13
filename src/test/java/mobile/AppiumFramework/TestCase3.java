package mobile.AppiumFramework;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import pageObjects.FormPage;

public class TestCase3 extends AppiumDriver{
	@BeforeTest
	public void killAllNodes() throws InterruptedException, IOException {
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);
	}
	public static double getAmount(String value) {
		value=value.substring(1);
		double amount=Double.parseDouble(value);
		return amount;
	}
	@Test
	public void totalValidation() throws InterruptedException, IOException {
		service=startServer();
		AndroidDriver<AndroidElement> driver=capabilities("General-Store");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Utilities utilities=new Utilities(driver);
		FormPage formPage=new FormPage(driver);
		formPage.getTextField().sendKeys("Hello");
		driver.hideKeyboard();
		formPage.getRadio().click();
		formPage.getDropDown().click();
		utilities.scrollToView("Argentina");
		formPage.getCountry().click();
		formPage.getShopButton().click();
		
		formPage.getAddToCart().click();
		formPage.getAddToCart().click();
		
		formPage.getCartButton().click();
		
		Thread.sleep(4000);
		int count= formPage.getProductPrice().size();
		double sum =0;
		for(int i=0;i<count;i++) {
			String amount=formPage.getProductPrice().get(i).getText();
			sum=sum+getAmount(amount);
		}
		String Total=formPage.getTotalPrice().getText();
		double total=getAmount(Total);
		Assert.assertEquals(sum, total);
		System.out.println(sum+":::::"+total);
		
		WebElement checkbox= formPage.getDiscountCheckbox();
		TouchAction action =new TouchAction(driver);
		action.tap(tapOptions().withElement(element(checkbox))).perform();
		
		WebElement terms=formPage.gettnc();
		action.longPress(longPressOptions().withElement(element(terms)).withDuration(ofSeconds(4))).release().perform();
		formPage.getCloseTNC().click();
		formPage.getVisitWebsite().click();
		
		Thread.sleep(10000);
		Set<String> context=driver.getContextHandles();
		for(String eachContext:context) {
			System.out.println("context:"+eachContext);
		}
		driver.context("WEBVIEW_com.androidsample.generalstore");
		driver.findElement(By.name("q")).sendKeys("new app");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.context("NATIVE_APP");
		service.stop();
	}
	@AfterTest
	public void killServices() throws IOException {
		
		Runtime.getRuntime().exec("adb kill-server");
	}

}
