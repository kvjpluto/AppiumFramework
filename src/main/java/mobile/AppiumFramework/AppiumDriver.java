package mobile.AppiumFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class AppiumDriver {
	public static AndroidDriver<AndroidElement> driver;
	//taskkill /F /IM node.exe // to kill all ports
	public static AppiumDriverLocalService service;
	public AppiumDriverLocalService startServer() {
		boolean flag=checkIfServerIsRunning(4723);
		if(!flag) {
			service=AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
		return service;
	}
	public static boolean checkIfServerIsRunning(int port) {
		boolean isServerRunning=false;
		ServerSocket serverSocket;
		try {
			serverSocket=new ServerSocket(port);
			serverSocket.close();
		}catch(IOException e) {
			isServerRunning=true;
		}finally {
			serverSocket=null;
		}
		return isServerRunning;
	}
	public static void startEmulator() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("adb start-server");
		Runtime.getRuntime().exec("adb devices");
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\mobile\\AppiumFramework\\startEmulator.bat");
		Thread.sleep(15000);
	}
	
	public static AndroidDriver<AndroidElement> capabilities(String appName) throws IOException, InterruptedException{
		
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\mobile\\AppiumFramework\\global.properties");
		Properties prop=new Properties();
		prop.load(fis);
		File appDir=new File("src");
		File app=new File(appDir,(String) prop.get(appName));
		DesiredCapabilities desiredcapabilities= new DesiredCapabilities();
		String device=prop.getProperty("device");
		//String device=System.getProperty("deviceName");
		if(device.contains("Emulator")) {
			startEmulator();
		}
		desiredcapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,device);
		desiredcapabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		desiredcapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Uiautomator2");
		driver=new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),desiredcapabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	public static void getScreenShot(String name) throws IOException {
			File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File("C:\\Users\\KARAN VISHAVJIT\\work\\"+name+".png"));
	}
}
