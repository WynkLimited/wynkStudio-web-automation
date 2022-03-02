package in.wynk.pages;

import in.wynk.API.API;
import in.wynk.common.DriverActionUtils;
import in.wynk.framework.Reporting;
import org.openqa.selenium.By;

public class WatchlistPage extends DriverActionUtils {
    API api;
    HomePage homePage;
    ContentDetailPage contentDetailPage;


    public WatchlistPage(HomePage homePage,ContentDetailPage contentDetailPage, API api, Reporting Reporter, in.wynk.framework.Assert Assert, in.wynk.framework.SoftAssert SoftAssert) {
        super(Reporter, Assert, SoftAssert);
        this.api = api;
        this.homePage = homePage;
        this.contentDetailPage=contentDetailPage;
    }

    public boolean VerifyIfWatchListIsPresent() {
        return homePage.watchlistPresent();
    }

    public void clickOnAddToWatchlistButton() {
      contentDetailPage.clickOnWatchlistButton() ;

    }

    public void verifyRemoveFromWatchlistIcon() {
        Assert.assertTrue(contentDetailPage.isRemoveFromWatchlistIcon());
    }
}
