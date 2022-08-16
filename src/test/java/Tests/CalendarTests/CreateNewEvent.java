package Tests.CalendarTests;

import Tests.BaseTest;
import Tests.Page.CalendarPage;
import io.appium.java_client.MobileBy;
import org.junit.Test;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class CreateNewEvent extends BaseTest {

    @Test
    public void createNewEvent() throws InterruptedException {

        androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        CalendarPage calendar = new CalendarPage(androidDriver, wait);

        calendar.activateCalendarApp();

        calendar.clickMenu();
        calendar.clickSchedule();

        String eventDay = (androidDriver.findElement(By.xpath("//android.view.View[contains(@content-desc, 'Open Day View')]")).getAttribute("content-desc")).split(",")[0];

        calendar.clickCreateNewEvent();

        calendar.enterTitleOfNewEvent("kfz24_tech_task");

        calendar.clickStart();

        androidDriver.findElementByAccessibilityId("6").click(); //set hour
        androidDriver.findElementByAccessibilityId("25").click();  //set minute
        calendar.clickOk();

        String startTime= calendar.getDay("start");
        String endTime= calendar.getDay("end");

        calendar.clickSaveNewEvent();
        calendar.clickMenu();
        calendar.clickSearch();
        calendar.searchEvent("kfz24_tech_task");
        calendar.clickEnter();


        // Validate Event is accessible
        Assert.assertTrue(androidDriver.findElement(By.xpath("//android.view.View[contains(@content-desc,'kfz24_tech_task')]")).isDisplayed());

        //Assertion of Day-Year
        Assert.assertEquals(eventDay,calendar.actualEventDay());

        //Assertion of Time
        Assert.assertEquals(startTime,calendar.actualEventHour(0));
        Assert.assertEquals(endTime,calendar.actualEventHour(1));

        calendar.clickBack();

    }

    @Test
    public void createAnotherEvent() throws InterruptedException {

        androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        CalendarPage calendar = new CalendarPage(androidDriver, wait);

        calendar.activateCalendarApp();
        calendar.clickMenu();

        //androidDriver.findElement(By.xpath("//android.widget.TextView[contains(@content-desc, 'Schedule')]")).click();
        calendar.clickSchedule();

        calendar.clickMonthPicker();

        androidDriver.findElement(MobileBy.AccessibilityId("Friday 26 August 2022")).click();
        Thread.sleep(3000);
        calendar.clickCreateNewEvent();
        calendar.clickCreateNewEvent();

        calendar.enterTitleOfNewEvent("kfz24_tech_task2");
        calendar.clickSaveNewEvent();

        // Validate another Event is accessible
        Assert.assertTrue(androidDriver.findElement(By.xpath("//android.view.View[contains(@content-desc,'kfz24_tech_task2')]")).isDisplayed());

    }

}
