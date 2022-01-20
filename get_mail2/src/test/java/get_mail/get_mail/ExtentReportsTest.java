package get_mail.get_mail;
import static org.testng.Assert.assertEquals;
import java.io.File;
import javax.mail.Message;
import javax.mail.MessagingException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportsTest{
	ExtentReports extent;
	ExtentTest logger;
	CheckingMails mail;
	
	@BeforeClass
	public void init() {
		CheckingMails t=new CheckingMails("imap.googlemail.com", "*****@gmail.com ", "*******");// change accordingly
	}
	@BeforeTest
	public void startReport(){
	
		extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/STMExtentReport.html", true);
		
		extent
                .addSystemInfo("Host Name", "TestingMaterial")
                .addSystemInfo("Environment", "Automation Testing")
                .addSystemInfo("User Name", "ROY");
               
                extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
	}
	
	
	  @Test
	  public void AssertSubjectMail() throws MessagingException {
		  
		  
		  logger = extent.startTest("find mail by SubjectMail");
		  Message message = mail.checkMailSubject("Call friends and family who aren’t on Skype.");// change accordingly
		  Assert.assertEquals("Call friends and family who aren’t on Skype.", message.getSubject().toString());// change accordingly
		  Assert.assertEquals("Skype <news@email.skype.com>", message.getFrom()[0].toString().trim());// change accordingly
		  logger.log(LogStatus.PASS, "Test Case Passed AssertSubjectMail");
	  }
	  
	  @Test
	  public void AssertMailFrom() throws MessagingException {
		  logger = extent.startTest("check Mail From");
		  Message message = mail.checkMailFrom("Call friends and family who aren’t on Skype.");// change accordingly
		  Assert.assertEquals("Skype <news@email.skype.com>", message.getFrom()[0].toString().trim());// change accordingly
		  logger.log(LogStatus.PASS, "Test Case Passed check Mail From");// change accordingly
	  }
		

	
	@AfterMethod
	public void getResult(ITestResult result){
		if(result.getStatus() == ITestResult.FAILURE){
			logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
		}else if(result.getStatus() == ITestResult.SKIP){
			logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		}
		
		extent.endTest(logger);
	}
	@AfterTest
	public void endReport(){
		
                extent.flush();
                
                extent.close();
    }
}