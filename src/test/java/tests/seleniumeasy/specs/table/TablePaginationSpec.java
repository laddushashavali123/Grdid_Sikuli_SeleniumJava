package tests.seleniumeasy.specs.table;

import base.DriverBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import tests.seleniumeasy.pages.table.TablePaginationPage;

public class TablePaginationSpec extends DriverBase {
    @Test
    public void TablePaginationTest() throws InterruptedException {
        WebDriver driver = getDriver();
        TablePaginationPage tablePaginationPage = new TablePaginationPage(driver);

        tablePaginationPage.goToPaginationPage()
                .verifyPreviousButtonHidden()
                .clickPaginationButton("3")
                .verifyNextButtonHidden();
    }
}
