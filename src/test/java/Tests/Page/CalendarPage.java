package Tests.Page;

import Tests.BaseTest;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CalendarPage extends BaseTest {

    private static final By CLICK_CREATE_NEW_EVENT_BUTTON = MobileBy.AccessibilityId("Nothing planned. Tap to create.");
    private static final By MENU=By.xpath("//android.widget.ImageButton[@content-desc=\"Show Calendar List and Settings drawer\"]");
    private static final By SEARCH=MobileBy.AccessibilityId("Search");
    private static final By SEARCH_EVENT_TEXTFIELD = By.id("com.google.android.calendar:id/search_text");
    private static final By ACTUAL_EVENT_DAY = By.xpath("(//android.view.View)[3]");
    private static final By ACTUAL_EVENT_HOUR = By.xpath("(//android.view.View)[4]");
    private static final By MORE_OPTIONS_BUTTON = MobileBy.AccessibilityId("More options");
    private static final By BACK_BUTTON=MobileBy.AccessibilityId("Back");
    private static final By DATE_PICKER=By.id("com.google.android.calendar:id/date_picker_text_view");
    private static final By TITLE= By.id("com.google.android.calendar:id/title_edit_text");
    private static final By SAVE_NEW_EVENT_BUTTON = By.id("com.google.android.calendar:id/save");
    private static final By DELETE_EVENT_BUTTON = By.xpath("//android.widget.TextView[@text='Delete']");
    private static final By OK_BUTTON = By.id("android:id/button1");
    private static final By SCHEDULE= By.xpath("//android.widget.TextView[contains(@content-desc, 'Schedule')]");
    private static final By START= By.xpath("//android.widget.Button[contains(@content-desc, 'Start time')]");
    private static final By END= By.xpath("//android.widget.Button[contains(@content-desc, 'End time')]");


    public AndroidDriver androidDriver;
    public WebDriverWait wait;

    public CalendarPage(AndroidDriver androidDriver, WebDriverWait wait) {
        this.androidDriver = androidDriver;
        this.wait = wait;
    }

    public void clickMenu(){
        androidDriver.findElement(MENU).click();
    }

    public void searchEvent(String searchText){
        androidDriver.findElement(SEARCH_EVENT_TEXTFIELD).sendKeys(searchText);
    }

    public void clickSearch() {
        androidDriver.findElement(SEARCH).click();
    }

    public void clickEnter(){  //Actually this method should belong to useful method class that every page can access.
        androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    public String actualEventDay(){
        return androidDriver.findElement(ACTUAL_EVENT_DAY).getAttribute("content-desc");
    }

    public String actualEventHour(int num) {  //num =0 is start hour , num=1 is end hour

        return ((androidDriver.findElement(ACTUAL_EVENT_HOUR).getAttribute("content-desc")).split(",")[1]).split("–")[num].trim();
    }

    public void waitForScreenToLoad(By element, int seconds){

        WebDriverWait wait = new WebDriverWait(androidDriver, seconds);
        wait.until(ExpectedConditions.visibilityOf((androidDriver.findElement(element))));
    }

    public void activateCalendarApp() {
        androidDriver.activateApp("com.google.android.calendar");

    }

    public void clickCreateNewEvent() throws InterruptedException {
        Thread.sleep(2000);
        androidDriver.findElement(CLICK_CREATE_NEW_EVENT_BUTTON).click();
    }

    public void enterTitleOfNewEvent(String title) {
        androidDriver.findElement(TITLE).sendKeys(title);
    }

    public void clickSaveNewEvent() {
        waitForScreenToLoad(SAVE_NEW_EVENT_BUTTON, 5);
        androidDriver.findElement(SAVE_NEW_EVENT_BUTTON).click();
    }

    public void clickMoreOptions() throws InterruptedException {
        Thread.sleep(2000);
        androidDriver.findElement(MORE_OPTIONS_BUTTON).click();
    }

    public void clickBack() {

        androidDriver.findElement(BACK_BUTTON).click();
    }

    public void clickDeleteEvent() {
        androidDriver.findElement(DELETE_EVENT_BUTTON).click();
        androidDriver.findElement(OK_BUTTON).click();
    }

    public void clickOk(){
        androidDriver.findElement(OK_BUTTON).click();
    }

    public boolean checkIfEventIsDeleted() throws InterruptedException {
        Thread.sleep(2000);
        boolean isNewEventDeleted = androidDriver.findElement(CLICK_CREATE_NEW_EVENT_BUTTON).isDisplayed();

        return isNewEventDeleted;
    }

    public void clickMonthPicker() {
        androidDriver.findElement(DATE_PICKER).click();
    }

    public void clickSchedule(){
        androidDriver.findElement(SCHEDULE).click();
    }

    public void clickStart(){
        androidDriver.findElement(START).click();

    }

    public String getDay(String day){  // Parameter should be either Start or End

        String result = "";

        if ( day=="start")
            result = (androidDriver.findElement(START).getAttribute("content-desc")).split(": ")[1];

        else if (day =="end")
            result = (androidDriver.findElement(END).getAttribute("content-desc")).split(": ")[1];

        return result;
    }

}
