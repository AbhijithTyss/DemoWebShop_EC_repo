package com.tricenties.ec.genericutility;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.tricenties.ec.objectrepository.HomePage;
import com.tricenties.ec.objectrepository.LoginPage;
import com.tricenties.ec.objectrepository.WelcomePage;

public class BaseClass {
	public static ExtentReports extReport;
	public ExtentTest test;
	public static WebDriver driver;
	public WebDriverWait eWait;
	
	public FileUtility fLib=new FileUtility();
	public JavaUtility jLib=new JavaUtility();
	public ExcelUtility eLib=new ExcelUtility();
	
	public WelcomePage wp;
	public LoginPage lp;
	public HomePage hp;
		
	@BeforeSuite
	public void configReports() {
		String TIME = jLib.getSystemTime().toString().replace(":", "-");
		ExtentSparkReporter spark=new ExtentSparkReporter("./reports/ExtentReport_"+TIME+".html");
		extReport=new ExtentReports();
		extReport.attachReporter(spark);
	}
	@Parameters("Browser")
	@BeforeClass
	public void launchBrowser(@Optional("chrome") String browserName) throws IOException {
		if(browserName.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}else if (browserName.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
		}else if (browserName.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		eWait=new WebDriverWait(driver, Duration.ofSeconds(20));
		String URL = fLib.getProperty("url");
		driver.get(URL);
	}
	@BeforeMethod
	public void login(Method method) throws EncryptedDocumentException, IOException {
		test=extReport.createTest(method.getName());
		wp=new WelcomePage(driver);
		wp.getLoginLink().click();
		lp=new LoginPage(driver);
		String EMAIL = eLib.getDataFromExcel("Login", 1, 0);
		String PASSWORD = eLib.getDataFromExcel("Login", 1, 1);
		String ExpectedTitle = eLib.getDataFromExcel("login", 1, 2);
		lp.getEmailTextField().sendKeys(EMAIL);
		lp.getPasswordTextField().sendKeys(PASSWORD);
		lp.getLoginButton().click();
		Assert.assertEquals(driver.getTitle(), ExpectedTitle,"User failed to login");
		test.log(Status.PASS, "user logged in successfully");
	}
	@AfterMethod
	public void logout() {
		hp=new HomePage(driver);
		
		hp.getLogoutLink().click();
		test.log(Status.PASS, "User logged out successfully");
	}
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
	@AfterSuite
	public void reportBackup() {
		extReport.flush();
	}
}
