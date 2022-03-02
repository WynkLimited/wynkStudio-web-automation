package in.wynk.pages;

import in.wynk.common.DriverActionUtils;
import in.wynk.framework.Reporting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class MorePage extends DriverActionUtils {

    CommonPage commonPage;
    PlayerPage playerPage;

    public MorePage(PlayerPage playerPage, CommonPage commonPage, Reporting Reporter, in.wynk.framework.Assert Assert, in.wynk.framework.SoftAssert SoftAssert) {
        super(Reporter, Assert, SoftAssert);
        this.commonPage = commonPage;
        this.playerPage = playerPage;
    }

    public enum morePageOption {WATCH_LIST, HELP_AND_SUPPORT, LOGIN, DOWNLOAD_APP, FEEDBACK, LOGOUT}

    private By watchListBtn = By.xpath("//ul[@class='more-sidenav__list']//span[text()='Watchlist']");
    private By helpAndSupportBtn = By.xpath("//ul[@class='more-sidenav__list']//span[text()='Help And Support']");
    private By loginBtn = By.id("nav-SignIn");
    private By logOutBtn = By.xpath("//ul[@class='more-sidenav__list']//span[text()='Logout']");
    private By DownloadAppBtn = By.xpath("//ul[@class='more-sidenav__list']//span[text()='Download App']");
    private By feedbackBtn = By.xpath("//ul[@class='more-sidenav__list']//span[text()='Feedback']");
    private By redLoginButton = By.xpath(".//button[text()='Login']");
    private By watchlistMsg = By.xpath(".//div[@class='watchlist-empty-state']//h2");
    private By watchlistContentClose = By.xpath(".//button[@class='close-btn']");
    private By play = By.xpath(".//section//div[@class='card-center-play-icon']");
    private By watchlistContent = By.xpath(".//section[@class='container-fluid component-top-padding']//div[@class='episodeWrapper']//a");
    private By ContinueWatchlistContent = By.xpath(".//div[@class='carousel-left-margin carousel-right-margin component-top-padding']//div[@class='episodeWrapper']//a");
    private By  termsOfUses =By.xpath("//li[@class = \"active nav-item top-nav--mobile__list-item\" ]//a[@title=\"Terms of Uses\"]");
    private By  privacyPolicy = By.xpath("//li[@class = \"active nav-item top-nav--mobile__list-item\" ]//a[@title=\"Privacy Policy\"]");

    public void selectOption(morePageOption morePageOption) {
        switch (morePageOption) {
            case WATCH_LIST:
                click(watchListBtn, "WatchList");
                break;
            case HELP_AND_SUPPORT:
                click(helpAndSupportBtn, "Help And Support");
                break;
            case LOGIN:
                click(loginBtn, "Login");
                break;
            case LOGOUT:
                click(logOutBtn, "Logout");
                break;
            case DOWNLOAD_APP:
                click(DownloadAppBtn, "Download");
                break;
            case FEEDBACK:
                click(feedbackBtn, "Feedback");
                break;
        }
    }

    public boolean verifyLoginSuccessfully() {
        try {
            getDriver().findElement(logOutBtn).isDisplayed();
            return true;
        } catch (Exception e){return false;}
    }

    public void clickOnRedLoginButton() {
        click(redLoginButton, "Red Login Button");
    }

    public boolean verifyMorePageIsvisibleOrNot() {
        return checkIfElementPresent(redLoginButton);

    }

    public boolean isWatchlistAvailable() {
        return checkIfElementPresent(watchlistMsg, 5);
    }

    /**
     * remove no if content from watch list
     */
    public void removeAllContentFromWatchlist() {
        try {
            List<WebElement> watchlistContent = getDriver().findElements(watchlistContentClose);
            while (watchlistContent.size() > 0) {
                commonPage.mouse_Hover(By.xpath("//div[@class='card-center-play-icon']"), false);
                click(watchlistContentClose, "close button");
                watchlistContent = getDriver().findElements(watchlistContentClose);
            }
        } catch (Exception e) {

        }

    }

    public void addContentInContinueWatchlist() {
        List<WebElement> content = getDriver().findElements(play);
        int size = content.size();
        int count = 0;
        for (int i = 0; i <= size - 1; i++) {
            new Actions(getDriver()).moveToElement(content.get(i)).click().perform();
            while (count <= 80) {
                playerPage.click_ON_RightArrow_respect_of_seekBar();
                count++;
            }
            sleep(3000);
            getDriver().navigate().back();
            sleep(500);
            content = getDriver().findElements(play);
            count = 0;
        }
    }

    public List<String> getWatchListContent() {
        List<String> channelsName = new ArrayList<>();
        List<WebElement> webElements = getDriver().findElements(watchlistContent);
        for (int i = 0; i <= webElements.size() - 1; i++) {
            channelsName.add(webElements.get(i).getAttribute("title"));
        }
        return channelsName;
    }

    public List<String> getContinueWatchListContent() {
        List<String> channelsName = new ArrayList<>();
        List<WebElement> webElements = getDriver().findElements(ContinueWatchlistContent);
        for (int i = 0; i <= webElements.size() - 1; i++) {
            channelsName.add(webElements.get(i).getAttribute("title"));
        }
        return channelsName;
    }


}
