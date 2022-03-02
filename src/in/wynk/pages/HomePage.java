package in.wynk.pages;

import in.wynk.API.API;
import in.wynk.API.APICommon;
import in.wynk.common.DriverActionUtils;
import in.wynk.framework.Driver;
import in.wynk.framework.Reporting;
import in.wynk.framework.SoftAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class HomePage extends DriverActionUtils {

    CommonPage commonPage;
    SoftAssert softAssert;
    API api;
    APICommon apiCommon;
    private By homePagRailsName = By.xpath(".//div[@class='title title-with-cta d-flex justify-content-between align-items-center']//h2");
    private By bannerName = By.xpath(".//div[@class='banner_container']//a");
    private By addToWatchList = By.id("add-watchlist");
    private By loginBtn = By.id("nav-SignIn");
    private By tvShow = By.id("nav-TV Shows");
    private By movies = By.id("nav-Movies");
    private By kids = By.id("nav-Kids");
    private By logOutBtn = By.xpath("//p[text()='Logout']");
    private By termsOfUses = By.xpath("//li[@class = \"active nav-item top-nav--mobile__list-item\" ]//a[@title=\"Terms of Uses\"]");
    private By privacyPolicy = By.xpath("//li[@class = \"active nav-item top-nav--mobile__list-item\" ]//a[@title=\"Privacy Policy\"]");
    private By profile = By.xpath("//a[contains (@class, 'nav-profile-item nav-header-item')]");
    private By language = By.id("nav-Language");
    private By ContinueWatchingList = By.xpath("//div[contains(@class, 'carousel-left-margin')][1]//h6");
    private By Watchlist = By.xpath("//div[contains(@class, 'carousel-left-margin')][1]//h6");
    private By railNameList = By.xpath("//h2");
    private By crossButton = By.xpath("//div[@class='episodeCards']//button");
    private By watchListButton = By.id("nav-Watchlist");
    private By menuItems = By.xpath("//li[@class='profile-card-items']");
    private By CWonWatchlistPage = By.xpath("//div[contains(@class, 'carousel-left-margin')][1]//h6");
    private By watchlistText = By.xpath("//h2[text()='My Watchlist']");
    private By contentCard = By.xpath("//div[contains (@class,'episodeWrapper')]");
    private By continueWatchingText = By.xpath("//h2[text()='Continue Watching']");
    private By topShowList = By.xpath("//div[@class='footer-col'][1]//span//a");
    private By trendingMoviesList = By.xpath("//div[@class='footer-col'][2]//span//a");
    private By justArrivedList = By.xpath("//div[@class='footer-col'][3]//span//a");
    private By footerListType = By.xpath("//button[@class='accordian ']");
    private By footerAncorTags = By.xpath("//div[contains(@class ,'footer-grid')]//a");
    private By copyRightText = By.xpath("//div[@class='footer-copyright']//p");
    private By watchlistIcon = By.xpath("//div[contains(@class,'watchlist-icon')]");
    private By railContentcard = By.xpath("//div[contains (@class,'overlay')]");


    public HomePage(Reporting Reporter, CommonPage commonPage, in.wynk.framework.Assert Assert, in.wynk.framework.SoftAssert SoftAssert, ThreadLocal<Driver.HashMapNew> sTestDetails) {
        super(Reporter, Assert, SoftAssert, sTestDetails);
        this.softAssert = SoftAssert;
        this.commonPage = commonPage;
        api = new API(Reporter, Assert, SoftAssert, sTestDetails);

    }

    public void VerifyCrossIconOnWatchlist() {
        if (checkIfElementPresent(watchlistText, 1000)) {
            commonPage.mouse_Hover(contentCard, true);
            checkIfElementPresent(crossButton, 0);
        }
    }

    public void deleteFirstContent(String listType) {
        sleep(2000);
        if (listType.equalsIgnoreCase("watchlist"))
            commonPage.mouse_Hover(contentCard, true);
        else
            commonPage.mouse_Hover(contentCard, true);

        click(crossButton, "cross button");
    }

    public List<String> getAllContinueWatchingList() {

        List<WebElement> elementsList = getWebElementsList(ContinueWatchingList);
        List<String> continueWatchingList = new ArrayList<String>();
        for (WebElement element : elementsList) {
            continueWatchingList.add(element.getText().trim().toLowerCase());
        }
        return continueWatchingList;
    }

    public List<String> getRailsName() {
        List<WebElement> autoSuggestElementList = getWebElementsList(railNameList);
        List<String> contentAutoSuggestList = new ArrayList<String>();
        for (WebElement element : autoSuggestElementList) {
            contentAutoSuggestList.add(element.getText().trim().toLowerCase());
        }
        return contentAutoSuggestList;
    }

    public List<String> getContinueWatchingListfromWatchlistPahge() {
        commonPage.mouse_Hover(profile, true);
        sleep(1000);
        click(watchListButton, " watchlistIcon");

        List<WebElement> ElementList = getWebElementsList(CWonWatchlistPage);
        List<String> continueWatchingList = new ArrayList<String>();
        for (WebElement element : ElementList) {
            continueWatchingList.add(element.getText().trim().toLowerCase());
        }
        return continueWatchingList;

    }

    public void clickonProfileButton() {
        click(profile, "probileButton");
    }

    public List<String> getMenuOption() {
        List<WebElement> ElementList = getWebElementsList(menuItems);
        List<String> menuItems = new ArrayList<String>();
        for (WebElement element : ElementList) {
            menuItems.add(element.getText().toLowerCase());
        }
        return menuItems;
    }

    public boolean watchlistPresent() {
        return checkIfElementPresent(watchlistText, 0);
    }

    public boolean continueWatchingPresent() {
        return checkIfElementPresent(continueWatchingText, 0);
    }


    public boolean verifyIfContentIsAddedToWatchlist(String contentName) {
        if (!watchlistPresent()) {
            return false;
        } else {
            List<String> watchlist = getAllWatchlist();
            return watchlist.contains(contentName.toLowerCase().trim());
        }
    }

    private List<String> getAllWatchlist() {
        List<WebElement> elementsList;
        if (continueWatchingPresent()) {
            elementsList = getWebElementsList(By.xpath("//div[contains(@class, 'carousel-left-margin')][2]//h6"));
        } else
            elementsList = getWebElementsList(Watchlist);
        List<String> watchingList = new ArrayList<String>();
        for (WebElement element : elementsList) {
            watchingList.add(element.getText().trim().toLowerCase());
        }
        return watchingList;
    }

    public boolean ContinueWatchlistPresent() {
        return checkIfElementPresent(ContinueWatchingList, 1000);
    }

    public void deleteContentFromWatchlist() {
        if (checkIfElementPresent(watchlistText, 1000)) {
            deleteFirstContent("watchlist");
        }
    }

    public List<String> getListFromUI(String listName) {

        List<WebElement> elementsList;
        if (listName.equalsIgnoreCase("Top Show")) {
            elementsList = getWebElementsList(topShowList);
        } else if (listName.equalsIgnoreCase("Trending Movies")) {
            elementsList = getWebElementsList(trendingMoviesList);
        } else
            elementsList = getWebElementsList(justArrivedList);

        List<String> showList = new ArrayList<String>();
        for (WebElement element : elementsList) {
            if (!element.getText().isEmpty())
                showList.add(element.getAttribute("title").trim().toLowerCase());
        }
        return showList;
    }

    public List<String> getFooterListTypeFromUI() {
        List<WebElement> elementsList = getWebElementsList(footerListType);
        List<String> showList = new ArrayList<String>();
        for (WebElement element : elementsList) {
            if (!element.getText().isEmpty())
                showList.add(element.getText().trim().toLowerCase());
        }
        return showList;
    }

    public boolean verifyNoBrokenLinksInFooter() throws IOException {
        List<WebElement> allURLs = getWebElementsList(footerAncorTags);

        for (WebElement element : allURLs) {
            if (verifyLinks(element.getAttribute("href"))) {
                return false;
            }
        }
        return true;
    }

    private boolean verifyLinks(String linkUrl) throws IOException {
        URL url = new URL(linkUrl);
        HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();
        httpURLConnect.setConnectTimeout(5000);
        httpURLConnect.connect();
        if (httpURLConnect.getResponseCode() >= 400) {
            System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + "is a broken link");
            return true;
        }
        return false;
    }

    public String getBuildVersionNumber() {
        return getText(copyRightText);
    }

    public boolean VerifyWatchlistIconOnRails() {
        commonPage.mouse_Hover(railContentcard, true);
      return   checkIfElementPresent(watchlistIcon, 0);
    }

    public void clickOnLogOutButton() {
        click(logOutBtn, "login");
    }

    public boolean isLoginButtonPresent() {
      return   checkIfElementPresent(logOutBtn,1000);
    }

    public enum HomePageOption {LOGIN, MOVIES, TV_SHOWS, KIDS, LANGUAGE}

    public void selectOption(HomePageOption homePageOption) {
        switch (homePageOption) {
            case LOGIN:
                click(loginBtn, "login");
                break;
            case TV_SHOWS:
                click(tvShow, "TV shows");
                break;
            case MOVIES:
                click(movies, "Movies");
                break;
            case KIDS:
                click(kids, "kids");
                break;
            case LANGUAGE:
                click(language, "language");
                break;
        }
    }


    public Set<String> getHomeRailsNames(int scrollingTime, int scrollGap) {
        Set<String> rail_Set = new HashSet<String>();
        int sameCount = 0;
        long current_Time = System.currentTimeMillis() + scrollingTime;
        List<WebElement> webElements = getDriver().findElements(homePagRailsName);
        int j = webElements.size();
        String lastRailsName = "";
        while (current_Time > System.currentTimeMillis()) {
            for (int i = 0; i <= webElements.size() - 1; i++) {
                //sleep(200);
                rail_Set.add(webElements.get(i).getText());
            }

            if (sameCount % 9 == 0) {
                System.out.println("yoyo" + lastRailsName + "     akdm " + webElements.get(webElements.size() - 1).getText().trim());
                if (lastRailsName.trim().equals(webElements.get(webElements.size() - 1).getText().trim())) {
                    break;
                }
                lastRailsName = webElements.get(webElements.size() - 1).getText().trim();
            }

            //    System.out.println(rail_Set);
            scrollingByCoordinatesofAPage(0, scrollGap);
            //   sleep(100);
            webElements = getDriver().findElements(homePagRailsName);
            sameCount++;
        }
        return rail_Set;
    }


    public Set<String> getAllHomeChannelsWithScroll(int totalScrollTime) {
        return commonPage.getAllElementFromPageWithKeyDown(totalScrollTime, homePagRailsName, "text");
    }

    public List<String> getBannerContentFromAPIForHome(String mobileNumber, String PageId, String contentKey, String lang) throws Exception {
        return commonPage.getBannerContentFromAPI(mobileNumber, PageId, contentKey, lang);
    }

    public void addContentInWatchList(int count) {
        List<WebElement> content = getDriver().findElements(addToWatchList);
        for (int i = 0; i < count; i++) {
            new Actions(getDriver()).moveToElement(content.get(i)).click().perform();
        }
    }


    public void clickOnSignInButton() throws InterruptedException {
        sleep(3000);
        getElementWhenVisible(loginBtn, 3000).click();
    }

    public void validateAllHomePages(String pages, boolean b) {

    }

    public boolean verifyLoginSuccessfully() {
        try {
            getDriver().findElement(profile).isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTermsOfUsesSelected() {
        return isElementDisplayed(termsOfUses, " terms of uses ", false);
    }

    public boolean isPrivacyPolicySelected() {
        return isElementDisplayed(privacyPolicy, "Privacy Policy", false);
    }


//    public HashSet<String> getAllTopPages(boolean fromLeftToRight) throws Exception {
//        elementPath = "CommonPage/TopTile_PageOptions";
//        String parentView = "CommonPage/TopTile_PageParentView";
//        HashSet<String> texts = new HashSet<String>();
//        if (fromLeftToRight) {
//            swipeLeftTillEnd(parentView, "", 3, elementPath);
//            //    texts = getAllElementTextInScreenTillHorizontalRight(parentView, "", elementPath);
//        } else {
//            //  texts = getAllElementTextInScreenTillHorizontalRight(parentView, "", elementPath);
//        }
//        return texts;
//    }

}