import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Calendar;

@RunWith(JUnitParamsRunner.class)
public class SocialSecurityTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setUp() throws Exception {
        driver = new FirefoxDriver();
    }

    @Test
    @Parameters(value={"1990,55000,2445","1980,65000,3169","1970,75000,3648"})
    public void testEarnings(String year, String earnings, double expectedEarnings) {
        driver.get("https://www.ssa.gov/OACT/quickcalc/");
        WebElement day = driver.findElement(By.id("month"));
        day.clear();
        day.sendKeys("1");
        WebElement month = driver.findElement(By.id("month"));
        month.clear();
        month.sendKeys("1");
        WebElement yearElement = driver.findElement(By.id("year"));
        yearElement.clear();
        yearElement.sendKeys(year);
        WebElement submit = driver.findElement(By.xpath("/html/body/table[4]/tbody/tr[2]/td[2]/form/table/tbody/tr[5]/td/input"));
        submit.click();
        WebElement submit2 = driver.findElement(By.xpath("/html/body/table[3]/tbody/tr[3]/td/form/input[10]"));
        submit2.click();
        int birthYear = Integer.parseInt(year);
        int start = birthYear + 18;
        int end = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = start; i <= end; i++) {
            WebElement e = driver.findElement(By.name(i+""));
            e.clear();
            e.sendKeys(earnings);
        }

        WebElement submit3 = driver.findElement(By.xpath("/html/body/table[3]/tbody/tr[1]/td[1]/form/input[6]"));
        submit3.click();

        WebElement benefitAmount = driver.findElement(By.id("est_fra"));
        String actualString = benefitAmount.getText().replace("$","").replace(",","");
        double actual = Double.parseDouble(actualString);

        Assert.assertEquals(expectedEarnings, actual, 50);
    }

    @Ignore
    @Test
    public void calendarTest() {
        System.out.println(Calendar.getInstance().get(Calendar.YEAR));
    }


    @Test
    @Ignore
    public void testMaxBenefit() {
        driver.get("https://www.ssa.gov/OACT/quickcalc/");
        WebElement yob = driver.findElement(By.id("year"));
        yob.clear();
        yob.sendKeys("1986");
        WebElement submit = driver.findElement(By.cssSelector("body > table:nth-child(6) > tbody > tr:nth-child(2) > td:nth-child(2) > form > table > tbody > tr:nth-child(5) > td > input[type=submit]"));
        submit.click();
        WebElement next = driver.findElement(By.cssSelector("body > table:nth-child(3) > tbody > tr:nth-child(3) > td > form > input[type=submit]:nth-child(10)"));
        next.click();
        int startYear = 1986 + 18;
        int endYear = 2024;
        for (int i = startYear; i <= endYear; i++) {
            WebElement element = driver.findElement(By.name(i + ""));
            element.clear();
            element.sendKeys("170000");
        }
        WebElement goToResult = driver.findElement(By.cssSelector("body > table:nth-child(3) > tbody > tr:nth-child(1) > td:nth-child(1) > form > input[type=submit]:nth-child(7)"));
        goToResult.click();
        WebElement result = driver.findElement(By.id("est_late"));

        Assert.assertTrue(Double.parseDouble(result.getText().replace("$","").replace(",","")) > 5000);
    }

    @Test
    @Parameters(value={"1992","2002","2012"})
    public void testDiffTenYears(int startYear) {
        String year = "1970";
        String earning = "75000";
        driver.get("https://www.ssa.gov/OACT/quickcalc/");

        WebElement day = driver.findElement(By.id("day"));
        day.clear();
        day.sendKeys("1");
        WebElement month = driver.findElement(By.id("month"));
        month.clear();
        month.sendKeys("1");
        WebElement yearElement = driver.findElement(By.id("year"));
        yearElement.clear();
        yearElement.sendKeys(year);
        WebElement submit = driver.findElement(By.xpath("/html/body/table[4]/tbody/tr[2]/td[2]/form/table/tbody/tr[5]/td/input"));
        submit.click();
        WebElement submit2 = driver.findElement(By.xpath("/html/body/table[3]/tbody/tr[3]/td/form/input[10]"));
        submit2.click();
        int birthYear = Integer.parseInt(year);
        for (int i = birthYear+17; i < startYear; i++) {
            WebElement e = driver.findElement(By.name(i+""));
            e.clear();
            e.sendKeys("0");
        }
        int start = startYear;
        int end = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = start; i <= end; i++) {
            WebElement e = driver.findElement(By.name(i+""));
            e.clear();
            e.sendKeys(earning);
        }

        WebElement submit3 = driver.findElement(By.xpath("/html/body/table[3]/tbody/tr[1]/td[1]/form/input[6]"));
        submit3.click();

        WebElement benefitAmount = driver.findElement(By.id("est_fra"));
        String actualString = benefitAmount.getText().replace("$","").replace(",","");
        double actual = Double.parseDouble(actualString);
        System.out.println(actual);
    }



}
