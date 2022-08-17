# CalendarApp


Prerequisites

For this Project to be run, user should login his Google Account before. 
Google Email Account and Calendar Synchronization steps are out of scope of this task.

This project is for Android Calendar app, used Appium as automation framework, Java is used as programming language, TestNG is used as Assertion tool

There are desired Capabilities In order to run the test properly, you should use this kind of Emulator with the versions and specificaions.
{
  "platformName": "Android",
  "appium:version": "10.0",
  "appium:deviceName": "Pixel_2_10",
  "appium:addPackage": "com.google.android.calendar",
  "appium:appActivity": "com.google.android.calendar.AllInOneCalendarActivity"
}


Screenshot is added. After tests are run, the screenshot will be created under regarding file. 

Note: No much test cases were not added, intentionally. There are some negative scenarios needs to be added but since we are focusing Automation test
structure by using Appium rather than the manuel test cases, to avoid some time consuming few test cases have been added. 
