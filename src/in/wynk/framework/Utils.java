package in.wynk.framework;

import in.wynk.common.DriverActionUtils;
import in.wynk.framework.Driver.HashMapNew;
import io.appium.java_client.MobileBy;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils extends DriverActionUtils {

  private HashMapNew Environment;
  private in.wynk.common.Utils utils;

  public Utils(Reporting Reporter, in.wynk.framework.Assert Assert,
      in.wynk.framework.SoftAssert SoftAssert, ThreadLocal<HashMapNew> sTestDetails) {
    super(Reporter, Assert, SoftAssert, sTestDetails);
    utils = new in.wynk.common.Utils(Environment);
    if (DriverFactory.getEnvironment() != null) {
      Environment = DriverFactory.getEnvironment();
    } else {
      Environment = utils.getEnvValues();
      String environment = System.getProperty("env") != null && !System.getProperty("env").trim()
          .equalsIgnoreCase("") ? System.getProperty("env").trim() : Environment.get("env").trim();
      if (!environment.trim().equalsIgnoreCase(Environment.get("env").trim())) {
        Environment = utils.getEnvValues(environment);
      }
    }
  }



  public void sleep(Long sTime) {
    try {
      Thread.sleep(sTime);
    } catch (InterruptedException e) {
      //Do Nothing
    }
  }

  public boolean envcheck() throws Exception {
    return true;
  }

//  public boolean handlePopups() {
//    if (getDriverType().trim().toUpperCase().contains("BACKEND")) {
//      return true;
//    }
//    boolean flag = false;
//    boolean getPageSource =
//        System.getProperty("getPageSource") != null && !System.getProperty("getPageSource").trim()
//            .equalsIgnoreCase("") ? Boolean.valueOf(System.getProperty("getPageSource").trim())
//            : Boolean.valueOf(Environment.get("getPageSource").trim());
//    String screenText = "";
//    if (getPageSource) {
//      screenText = getDriver().getPageSource();
//    } else {
//      if (getDriverType().trim().toUpperCase().contains("ANDROID") || getDriverType().trim()
//          .toUpperCase().contains("CHROME") || getDriverType().trim().toUpperCase()
//          .contains("IOS")) {
//        ArrayList<String> imagesPath = new ArrayList<String>();
//        try {
//          imagesPath = captureScreenshots(1);
//          if (new java.io.File(imagesPath.get(0)).exists()) {
//            String proxyAddress = sTestDetails.get().get("restApiAddress").trim();
//            int counter = 3;
//            do {
//              screenText = utils
//                  .getTextFromImage(imagesPath.get(0), new String[]{"English"}, proxyAddress);
//              counter--;
//            } while (screenText.trim().equalsIgnoreCase("") && counter > 0);
//          }
//        } catch (Exception e) {
//          e.printStackTrace();
//        }
//      }
//    }
//    if (getDriverType().trim().toUpperCase().contains("ANDROID")) {
//      if (screenText.trim().toLowerCase().contains("deny")) {
//        if (checkIfElementPresent(denyButton, 1)) {
//          getDriver().findElement(denyButton).click();
//          flag = true;
//        }
//      }
//    }
//    if (getDriverType().trim().toUpperCase().contains("ANDROID")) {
//      if (screenText.trim().toLowerCase().contains("Explore Airtel Xstream Premium".toLowerCase())
//          || screenText.trim().toLowerCase().contains("Unlocked!".toLowerCase()) || screenText
//          .trim().toLowerCase().contains("Bundled with Airtel Broadband Plan".toLowerCase())) {
//        if (checkIfElementPresent(exploreAirtelXstreamButton, 1)) {
//          navigateBack();
//          flag = true;
//        }
//      }
//      if (screenText.trim().toLowerCase().contains("cancel")) {
//        if (checkIfElementPresent(By.id("android:id/alertTitle"), 1)) {
//          //Do nothing
//        } else {
//          if (checkIfElementPresent(By.xpath(
//              ".//*[contains(@content-desc, 'cancel') or contains(@text, 'cancel') or contains(@content-desc, 'Cancel') or contains(@text, 'Cancel')]"),
//              1)) {
//            getDriver().findElement(By.xpath(
//                ".//*[contains(@content-desc, 'cancel') or contains(@text, 'cancel') or contains(@content-desc, 'Cancel') or contains(@text, 'Cancel')]"))
//                .click();
//            flag = true;
//          }
//        }
//      }
//      if (screenText.trim().toLowerCase().contains("Storage Is Running Low".toLowerCase())) {
//        if (checkIfElementPresent(By.id("android:id/button2"), 1)) {
//          getDriver().findElement(By.id("android:id/button2")).click();
//          flag = true;
//        }
//      }
//      if (screenText.trim().toLowerCase().contains("later")) {
//        if (checkIfElementPresent(By.name("Later"), 1)) {
//          getDriver().findElement(By.name("Later")).click();
//          flag = true;
//        }
//      }
//      if (screenText.trim().toLowerCase().contains("ok") || screenText.trim().toLowerCase()
//          .contains("0k") || screenText.trim().toLowerCase()
//          .contains("The connected device is unable to access data".toLowerCase()) || screenText
//          .trim().toLowerCase().contains("connecxed devlce Is unable".toLowerCase()) || screenText
//          .trim().toLowerCase().contains("connemed devlce Is unable".toLowerCase()) || screenText
//          .trim().toLowerCase().contains("samsun".toLowerCase())) {
//        if (checkIfElementPresent(By.id("android:id/button1"), 1)) {
//          getDriver().findElement(By.id("android:id/button1")).click();
//          flag = true;
//        } else if (checkIfElementPresent(By.xpath(
//            ".//*[contains(@content-desc, 'ok') or contains(@text, 'ok') or contains(@content-desc, 'Ok') or contains(@text, 'Ok') or contains(@content-desc, '0k') or contains(@text, '0k')]"),
//            1)) {
//          getDriver().findElement(By.xpath(
//              ".//*[contains(@content-desc, 'ok') or contains(@text, 'ok') or contains(@content-desc, 'Ok') or contains(@text, 'Ok') or contains(@content-desc, '0k') or contains(@text, '0k')]"))
//              .click();
//          flag = true;
//        }
//      }
//      if (screenText.trim().toLowerCase().contains("just once")) {
//        if (checkIfElementPresent(justOnce, 1)) {
//
//          getDriver().findElement(justOnce).click();
//          flag = true;
//        }
//      }
//      boolean clrChromeAppCache = System.getProperty("clearchromeappcache") != null && !System
//          .getProperty("clearchromeappcache").trim().equalsIgnoreCase("") ? Boolean
//          .valueOf(System.getProperty("clearchromeappcache").trim())
//          : Boolean.valueOf(Environment.get("clearChromeAppCache").trim());
//      if (clrChromeAppCache) {
//        if (screenText.trim().toLowerCase().contains("accept") && screenText.trim().toLowerCase()
//            .contains("continue")) {
//          if (checkIfElementPresent(By.xpath(
//              ".//android.widget.Button[@text='Accept and continue' or @text='Accept & continue']"))) {
//            getDriver().findElement(By.xpath(
//                ".//android.widget.Button[@text='Accept and continue' or @text='Accept & continue']"))
//                .click();
//            if (checkIfElementPresent(
//                By.xpath(".//android.widget.Button[@text='No thanks' or @text='No, thank you']"))) {
//              getDriver().findElement(
//                  By.xpath(".//android.widget.Button[@text='No thanks' or @text='No, thank you']"))
//                  .click();
//            }
//            flag = true;
//          }
//        }
//      }
//    }
//    return flag;
//  }

  public String readPINFromMessagesCustom(String recieverName) throws Exception {
    String messageText = readlastMessageText(recieverName);
    String pin = messageText.replaceAll("\\D+", "");
    return pin;
  }

  public String readlastMessageText(String title, long... time) throws Exception {
    assert time.length <= 2;
    long seconds = time.length > 0 ? time[0] : 25;
    String message = "";
    boolean devicefarm = Boolean.valueOf(Environment.get("devicefarm"));
    String output = utils.runCommandUsingTerminal(devicefarm,
        "adb -s " + Driver.deviceList.get(getDriverType().trim().toUpperCase()).trim()
            + " shell ps | grep io.appium.networktoggle", false, "1");
    if (!output.trim().contains("io.appium.networktoggle")) {
      launchApp("io.appium.networktoggle", "io.appium.networktoggle.MainActivity");
      utils.runCommandUsingTerminal(devicefarm,
          "adb -s " + Driver.deviceList.get(getDriverType().trim().toUpperCase()).trim()
              + " shell ps | grep io.appium.networktoggle", false, "5");
      sleep(2000L);
      navigateBack();
      return message;
    }
    if (title.trim().toUpperCase().contains("WYNKED") || title.trim().toUpperCase()
        .contains("VSIPAY") || title.trim().toUpperCase().contains("AIRAPP") || title.trim()
        .toUpperCase().contains("AIRINF") || title.trim().toUpperCase().contains("AIRSEP") || title
        .trim().toUpperCase().contains("CVHTUN")) {
      try {
        WebElement alertTitle = getElementWhenVisible(By.id("android:id/alertTitle"), seconds);
        if (alertTitle.getText().trim().contains(title.trim().toUpperCase())) {
          WebElement alertMessage = getElementWhenVisible(By.id("android:id/message"));
          message = alertMessage.getText().trim();
        }
        click(By.id("android:id/button1"), "Alert Ok button");
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    return message;
  }

  public boolean checkIfElementPresent(By locator, long... waitSeconds) {
    assert waitSeconds.length <= 1;
    long seconds = waitSeconds.length > 0 ? waitSeconds[0] : Long.valueOf(this.Environment.get("implicitWait")) / 1000;
    WebDriverWait wait  = new WebDriverWait(getDriver(), seconds);
    long implicitWait = Long.valueOf(sTestDetails.get().get("implicitWait")) / 1000;
    try{
        getDriver().manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return true;
    } catch(Exception ex) {
        return false;
    } finally {
        getDriver().manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
    }
  }
}