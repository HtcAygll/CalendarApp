package Tests.CalendarTests;

import Tests.BaseTest;
import Tests.Page.CalendarPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.junit.Test;


import java.util.concurrent.TimeUnit;

public class DeleteEvent extends BaseTest {

    @Test
    public void deleteEvent() throws InterruptedException {

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

        //deleting event
        androidDriver.findElement(By.xpath("//android.view.View[contains(@content-desc,'kfz24_tech_task')]")).click();
        calendar.clickMoreOptions();
        calendar.clickDeleteEvent();
        calendar.clickBack();

        //Assert Event is deleted
        Assert.assertTrue(calendar.checkIfEventIsDeleted());

    }

}
