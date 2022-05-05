package in.wynk.pages;

import in.wynk.API.API;
import in.wynk.API.APICommon;
import in.wynk.common.DriverActionUtils;
import in.wynk.framework.Driver;
import in.wynk.framework.Reporting;
import io.restassured.response.Response;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class CommonPage extends DriverActionUtils {


    API api;
    private By bannerName = By.id("home-banner");
    private By homePagRailsName = By.id("rail-title");
    private By clickableBradCrumbs = By.id("home-clickable-bradcrumbs");
    private By nonClickableBradCrumbs = By.id("home-non-clickable-bradcrumbs");
    private By activeNavTab = By.xpath("//li[contains(@class , 'active')]//a");
    private By railTitle = By.id("railTitle");
    private By homeButton = By.xpath("//nav[@class='left-sidebar-content']//li[@id='more1']//following-sibling::li[1]//a");

    public String getActiveTab() {
        return getText(activeNavTab , 1000);
    }

    public String getRailTitle() {
        return getText(railTitle , 1000);
    }


    public boolean isBreadcrumbsVisible() {
        return isElementDisplayed(clickableBradCrumbs, "breadcrumbs", true);
    }

    public enum PageID {HOME, TVSHOWS, MOVIES, LiveTV}


    public CommonPage(Reporting Reporter, in.wynk.framework.Assert Assert, in.wynk.framework.SoftAssert SoftAssert, ThreadLocal<Driver.HashMapNew> sTestDetails) {
        super(Reporter, Assert, SoftAssert);
        api = new API(Reporter, Assert, SoftAssert, sTestDetails);

    }

    public void reload_page() {
        getDriver().navigate().refresh();
    }


    public int get_Time_diff_in_Sec(String time1, String time2) throws ParseException {
        SimpleDateFormat simpleformat = new SimpleDateFormat("HH:mm:ss");
        return Math.abs(Seconds.secondsBetween(new DateTime(simpleformat.parse(time1)), new DateTime(simpleformat.parse(time2))).getSeconds() % 60);
    }

    // selenium
    public void mouse_Hover(By locator, boolean iterationFlag) {
        int count = 0;
        if (iterationFlag) {
            while (count <= 20)
                try {
                    new Actions(getDriver()).moveToElement(getDriver().findElement(locator)).perform();
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                    count++;
                }
        } else {
            new Actions(getDriver()).moveToElement(getDriver().findElement(locator)).perform();
        }

    }

    // selenium
    public void mouse_Hover_click(By locator) {
        new Actions(getDriver()).moveToElement(getDriver().findElement(locator)).click().perform();
    }

    public int time_to_milliSec(String time) {
        String hours = StringUtils.substringBefore(time, ":");
        String second = StringUtils.substringAfterLast(time, ":");
        String min = StringUtils.substringBetween(time, hours + ":", ":" + second);
        return (Integer.parseInt(hours) * 60 * 60 * 1000) + (Integer.parseInt(min) * 60 * 1000) + (Integer.parseInt(second) * 1000);

    }

    public void clickONDownArrow4Times() {
        Actions actions = new Actions(getDriver());
        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).perform();
//        new Actions(getDriver()).sendKeys(Keys.ARROW_DOWN).perform();
    }

    public void clickONDownArrow(int noOfTime) {
        Actions actions = new Actions(getDriver());
        for (int i = 0; i <= noOfTime; i++) {
            //  actions.sendKeys(Keys.ARROW_DOWN).perform();
            actions.sendKeys(Keys.END).perform();
            //   actions.sendKeys(Keys.COMMAND);

        }
    }


    public void pageUP() {
        Actions actions = new Actions(getDriver());
        actions.sendKeys(Keys.HOME).perform();
    }


    public Set<String> getAllElementFromPageWithKeyDown(int scrollingTime, By Locator, String attribute) {
        JavascriptExecutor javascript = (JavascriptExecutor) getDriver();
        int count = 0;
        Long value1 = (Long) javascript.executeScript("return window.pageYOffset;");
        Set<String> rail_Set = new HashSet<String>();
        List<WebElement> webElements = getDriver().findElements(Locator);
        long current_Time = System.currentTimeMillis() + scrollingTime;
        while (current_Time > System.currentTimeMillis()) {
            for (int i = 0; i <= webElements.size() - 1; i++) {
                rail_Set.add(webElements.get(i).getAttribute(attribute.trim()));
            }
            clickONDownArrow4Times();
            sleep(100);
            webElements = getDriver().findElements(Locator);
            Long value2 = (Long) javascript.executeScript("return window.pageYOffset;");
            System.out.println("pageYOffset " + value2);
            if (value1.intValue() == value2.intValue()) {
                count++;
                if (count > 3) {
                    break;
                }
            }
            value1 = value2;
        }
        return rail_Set;
    }

    public Set<String> getAllElementFromPageWithKeyDown(int scrollingTime, By Locator, int noOfTimeClickDown, String attribute) {
        JavascriptExecutor javascript = (JavascriptExecutor) getDriver();
        int count = 0;
        Long value1 = (Long) javascript.executeScript("return window.pageYOffset;");
        Set<String> rail_Set = new HashSet<String>();

        long current_Time = System.currentTimeMillis() + scrollingTime;
        while (current_Time > System.currentTimeMillis()) {
            clickONDownArrow(noOfTimeClickDown);
            Long value2 = (Long) javascript.executeScript("return window.pageYOffset;");
            if (value1.intValue() == value2.intValue()) {
                count++;
                if (count > 10) {
                    System.out.println("Scroll Stop when  window.pageYOffset value is " + value2);
                    break;
                }
            }
            value1 = value2;
        }
        sleep(10000);
        List<WebElement> webElements = getDriver().findElements(Locator);
        if (attribute.contains("text")) {
            for (WebElement webElement : webElements) {
                System.out.println(webElement.getText().trim());
                rail_Set.add(webElement.getText().trim());
            }
        } else {
            for (WebElement webElement : webElements) {
                System.out.println(webElement.getAttribute(attribute.trim()));
                rail_Set.add(webElement.getAttribute(attribute.trim()));
            }
        }
        return rail_Set;
    }

    public Set<String> getElementFromPage(By Locator, String attribute) {
        Set<String> rail_Set = new HashSet<String>();
        List<WebElement> webElements = getDriver().findElements(Locator);
        for (int i = 0; i <= webElements.size() - 1; i++) {
            if (attribute.contains("text")) {
                System.out.println(("------" + webElements.get(i).getText().trim()));
                rail_Set.add(webElements.get(i).getText().trim());
            } else {
                rail_Set.add(webElements.get(i).getAttribute(attribute.trim()));
            }
        }
        return rail_Set;
    }
    public boolean AssertionBetweenTwoList(List<String> listFromAPI, List<String> listFromUI, String query) {

        ArrayList<String> intersection = (ArrayList<String>) CollectionUtils.intersection(listFromAPI, listFromUI);
        ArrayList<String> extraInAPI = (ArrayList<String>) CollectionUtils.disjunction(listFromAPI, intersection);
        ArrayList<String> extraInUI = (ArrayList<String>) CollectionUtils.disjunction(listFromUI, intersection);

        if (extraInAPI.size() > 0 && extraInUI.size() > 0) {
           System.out.println("AutoSuggestiondo not matcxh with API response");
           return false;
        }
        if (extraInAPI.size() > 0) {
            System.out.println("AutoSuggestiondo not matcxh with API response");
            return false;
        }
        if (extraInUI.size() > 0) {
            System.out.println("AutoSuggestiondo not matcxh with API response");
            return false;
        }
        return true;
    }










    /**
     * @param setFromAPI        " fist set"
     * @param setFromUI         " second set"
     * @param verificationTopic " verification topic "
     * @param PageName          " page name "
     * @param language          " language "
     * @param JiraID            " jiraID "
     */
    public void aPIuIAssertionBetweenTwoSet(Set<String> setFromAPI, Set<String> setFromUI, String verificationTopic, String PageName, String language, String JiraID) {

        ArrayList<String> intersection = (ArrayList<String>) CollectionUtils.intersection(setFromAPI, setFromUI);
        ArrayList<String> extraInAPI = (ArrayList<String>) CollectionUtils.disjunction(setFromAPI, intersection);
        ArrayList<String> extraInUI = (ArrayList<String>) CollectionUtils.disjunction(setFromUI, intersection);

        if (extraInAPI.size() > 0 && extraInUI.size() > 0) {
            Assert.fail(extraInAPI.toString() + " this " + verificationTopic + " content from API not visible on " + PageName + " for " + language + " || " + extraInUI.toString() + " this " + verificationTopic + " content from UI not visible on " + PageName + " layout API for " + language + " ** JIRA ID " + JiraID);
        }
        if (extraInAPI.size() > 0) {
            Assert.fail(extraInAPI.toString() + " this " + verificationTopic + " content from API not visible on " + PageName + " for " + language + " ** JIRA ID " + JiraID);
        }
        if (extraInUI.size() > 0) {
            Assert.fail(extraInUI.toString() + " this " + verificationTopic + " content from UI not visible on " + PageName + " layout API for " + language + " ** JIRA ID " + JiraID);
        }
    }


    /**
     * @param setFromAPI        " fist set"
     * @param setFromUI         " second set"
     * @param verificationTopic " verification topic "
     * @param PageName          " page name "
     * @param language          " language "
     * @param JiraID            " jiraID "
     */
    public int aPIuIAssertionBetweenTwoSetWithSoftAssert(Set<String> setFromAPI, Set<String> setFromUI, String verificationTopic, String PageName, String language, String JiraID) {

        ArrayList<String> intersection = (ArrayList<String>) CollectionUtils.intersection(setFromAPI, setFromUI);
        ArrayList<String> extraInAPI = (ArrayList<String>) CollectionUtils.disjunction(setFromAPI, intersection);
        ArrayList<String> extraInUI = (ArrayList<String>) CollectionUtils.disjunction(setFromUI, intersection);

        if (extraInAPI.size() > 0 && extraInUI.size() > 0) {
            SoftAssert.fail(extraInAPI.toString() + " this " + verificationTopic + " content from API not visible on " + PageName + " for " + language + " || " + extraInUI.toString() + " this " + verificationTopic + " content from UI not visible on " + PageName + " layout API for " + language + " ** JIRA ID " + JiraID);
            return 1;
        }
        if (extraInAPI.size() > 0) {
            SoftAssert.fail(extraInAPI.toString() + " this " + verificationTopic + " content from API not visible on " + PageName + " for " + language + " ** JIRA ID " + JiraID);
            return 1;
        }
        if (extraInUI.size() > 0) {
            SoftAssert.fail(extraInUI.toString() + " this " + verificationTopic + " content from UI not visible on " + PageName + " layout API for " + language + " ** JIRA ID " + JiraID);
            return 1;
        }
        return 0;
    }








    public void openURL(String path) {
        launchUrl(sTestDetails.get().get("APP_URL") + path, true);
    }

    public static enum navigationOption {HOME,LOGIN, REGISTER}

    public void navigateToPage(navigationOption navigationOption) {

    switch (navigationOption) {
            case HOME:
                String x = sTestDetails.get().get("APP_URL");
                launchUrl(sTestDetails.get().get("APP_URL") , true);
                break;

            case REGISTER:
                launchUrl(sTestDetails.get().get("APP_URL")+"/register", true);
                break;

            case LOGIN:
                launchUrl(sTestDetails.get().get("APP_URL")+"/login" , true);
                break;

        }

    }

    public void navigateToPage(String verificationLink)
    {

        launchUrl(verificationLink , true);
    }

}
