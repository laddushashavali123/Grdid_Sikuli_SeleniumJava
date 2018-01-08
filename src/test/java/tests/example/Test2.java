package tests.example;

import base.DriverBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import static base.DriverUtils.clickWhenReadyJavascript;

public class Test2 extends DriverBase {

   /* @Test
    public void LS() throws Exception {
        WebDriver driver = getDriver();

        driver.get("https://www.google.com.vn");
        System.out.println("Action on " + Thread.currentThread().getId());


        *//*driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div/form/div[1]/input")).sendKeys("test321");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div/form/div[2]/input")).sendKeys("Kandy-1234");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div/form/input")).submit();
        clickWhenReadyJavascript(By.xpath("//div[contains(text(),'Voice Chat Request')]"), 15 );*//*

        Thread.sleep(2000);
        System.out.println("Test1");
        //driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[3]/center/input[2]"));
    }*/

    @Test
    public void test2() throws Exception{
        Assert.assertEquals("test", "testfail");
    }

    /*@Test
    public void test3() throws Exception{
        WebDriver driver = getDriver();
        driver.get("https://www.google.com.vn");
        Thread.sleep(1000);
        System.out.println("Test3");
        driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[3]/center/input[2]"));
    }*/
}
