package tests.seleniumeasy.specs.table;

import base.DriverBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import tests.seleniumeasy.pages.table.TableDataSearchPage;

public class TableDataSearchSpec extends DriverBase{
    @Test
    public void TableDataSearchTest(){
        WebDriver driver = getDriver();
        TableDataSearchPage tableDataSearchPage = new TableDataSearchPage(driver);

        tableDataSearchPage.goTableDataSearchPage()
                .entertaskInput("SEO tags")
                .verifyTotalItemDisplayIs(1)
                .entertaskInput(" ")
                .verifyTotalItemDisplayIs(7)
                .entertaskInput("se")
                .verifyTotalItemDisplayIs(2)
                .entertaskInput("asd")
                .verifyTotalItemDisplayIs(0);
    }
}
