
package Cal_test;
import java.io.FileReader; 
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest; 
import org.testng.annotations.Test; 
import com.opencsv.CSVReader; 
import org.testng.Assert;
import org.openqa.selenium.Keys;


//import java.awt.List;
public class CSVRead {

 //Provide CSV file path. 
//String CSV_PATH= "~/Documents/workspace/Calculator/calculator.csv";
String CSV_PATH= System.getProperty("user.dir") + "/" + "calculator.csv";

 WebDriver driver; 
 static String driverPath = "Documents/workspace/Calculator"; //Chrome driver location

@BeforeTest
 public void setup() throws Exception{ 
System.setProperty("webdriver.chrome.driver","chromedriver");
driver = new ChromeDriver();              
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
driver.get("http://web2.0calc.com");
}

@Test

public void csvDataRead() throws IOException, InterruptedException { 
CSVReader reader = new CSVReader(new FileReader(CSV_PATH)); 
String [] csvCell;
String Calres=null;
String result=null;
int i=0;


while ((csvCell = reader.readNext()) != null) {  //Read data from every row and save in a array.
if (i==0) {          //Read data from second row
i++;
continue;
}
String N1 = ""+csvCell[0]; 
String N2  = ""+csvCell[1]; 
String Operation = csvCell[2]; 
 result=""+csvCell[3];
 Calres=CalFunction(N1,N2,Operation);
 Assert.assertEquals(result,Calres); 
}
}    //assertion to compare result



 public String CalFunction(String n1, String n2, String operation) throws InterruptedException
 {
WebElement a = driver.findElement(By.id("input"));
 String res;

 a.clear();
switch(operation)
{
case "Multiplication":
   a.sendKeys(n1);
   a.sendKeys("*");
   a.sendKeys(n2);
   a.sendKeys(Keys.RETURN);  
		Thread.sleep(1000);
	
   
    res= driver.findElement(By.id("input")).getAttribute("value");
   break;
case "Division":
	
  a.sendKeys(n1);
  a.sendKeys("/");
  a.sendKeys(n2);
   a.sendKeys(Keys.RETURN);
   Thread.sleep(1000);
  
   res= driver.findElement(By.id("input")).getAttribute("value");
   break;
case "Addition":
	  a.sendKeys(n1);
	  a.sendKeys("+");
	  a.sendKeys(n2);
   a.sendKeys(Keys.RETURN);
   Thread.sleep(1000);
  
   res= driver.findElement(By.id("input")).getAttribute("value");
   break;
case "Subtraction":
	  a.sendKeys(n1);
	  a.sendKeys("-");
	  a.sendKeys(n2);
	   a.sendKeys(Keys.RETURN);
	   Thread.sleep(1000);
	   res= driver.findElement(By.id("input")).getAttribute("value");
	   break;
default:
	res = null;

}
return(res);
}
}

