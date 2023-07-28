package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.frameworkexception.FrameworkException;

public class DriverFactory {
	WebDriver driver;
	public  static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();;
	private OptionsManager options;
public WebDriver initDriver(Properties prop) {
String browserName=prop.getProperty("browser");
	//String browserName= System.getProperty("browser");
	System.out.println("browser name is"+browserName);
	 options=new OptionsManager(prop);
	
	switch(browserName.toLowerCase().trim()) {
	case "chrome":
		//driver=new ChromeDriver(options.getChromeOptions());
		tlDriver.set(new ChromeDriver(options.getChromeOptions()));
		break;
	case "firefox":
		tlDriver.set(new FirefoxDriver(options.getFirefoxOptions())); 
		break;
	case "edge":
		tlDriver.set(new EdgeDriver(options.getEdgeOptions()));
		break;
	case "safari":
		tlDriver.set(new SafariDriver()); 
		break;
	default:
		System.out.println("pls pass the right browsername"+browserName);
		throw new FrameworkException("NOBROWSERFOUNDEXCEPTION");
		}
	getDriver().manage().window().maximize();
	getDriver().manage().deleteAllCookies(); 
	getDriver().get(prop.getProperty("url"));
	return getDriver();
}
public synchronized static WebDriver getDriver() {
	return tlDriver.get();
}
public Properties intiprop() {
	FileInputStream ip=null;
	//mvn clean install -Denv="qa"
	 String envname=System.getProperty("env");
	 System.out.println("environment name is"+envname);
	 Properties prop=new Properties();
try {
	 if(envname==null) {
		 ip=new FileInputStream("./src/main/resources/config/qa.config.properties");
		 }
	 else {
		 switch(envname.toLowerCase().trim()) {
		 case "qa":
			 ip=new FileInputStream("./src/main/resources/config/qa.config.properties");
			  break;
		 case "stage":
			 ip=new FileInputStream("./src/main/resources/config/stage.config.properties");
			  break;
		 case "dev":
			 ip=new FileInputStream("./src/main/resources/config/dev.config.properties");
			  break;
		 case "uat":
			 ip=new FileInputStream("./src/main/resources/config/uat.config.properties");
			  break;
			   default:
				   System.out.println("pls pass the right environment name"+envname);
				   throw new FrameworkException("NOVALIDENVIRONMENT"); 
			 }
	 }
}

	catch (FileNotFoundException e) {
	    e.printStackTrace();
	} 
try {
	prop.load(ip);
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	return prop;
	
	
	
	
}
public static String getScreenshot() {
	File srcFile=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
	String path=System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
	File destination=new File(path);
	try {
	FileUtils.copyFile(srcFile,destination);
	}catch(IOException e)
	{
		e.printStackTrace();
	}
	
	return path;
	
}
}
