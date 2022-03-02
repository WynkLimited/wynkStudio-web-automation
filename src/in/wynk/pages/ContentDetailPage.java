package in.wynk.pages;

import in.wynk.API.API;
import in.wynk.common.DriverActionUtils;
import in.wynk.framework.Driver;
import in.wynk.framework.Reporting;
import in.wynk.framework.XStreamLanguageMapping;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContentDetailPage extends DriverActionUtils {
    in.wynk.framework.Assert Assert;
    API api;
    CommonPage commonPage;

    private By watchlistIcon = By.xpath("//button[@class='video-watchlist-icon']//img");
    private By watchlistText = By.xpath("//span[text()='SHARE']");
    private By shareText = By.xpath("//span[text()='SHARE']");
    private By tittle = By.xpath("//h1[@class='description-box-title']");
    private By ageRating = By.xpath("//span[@class='age-rating-tag']");
    private By director = By.xpath("//p[text()='Director']//a");
    private By shortDescription = By.xpath("//div[contains(@class,'video-description')]//p");
    private By moreButton = By.xpath("//button[text()='More']");
    private By player = By.xpath("//div[@class='vdo-player']");
    private By watchlistButton = By.xpath("//img[@title='Add to Watchlist']");
    private By removeFromWatchlst = By.xpath("//img[@title='Remove From Watchlist']");
    private By language = By.xpath("//li[@class='lang-list']//a[1]");
    private By genre = By.xpath("//li[@class='lang-list']//a[2]");
    private By artistList = By.xpath("//div[@class='figure-caption']//p");


    public ContentDetailPage(CommonPage commonPage, Reporting Reporter, in.wynk.framework.Assert Assert, in.wynk.framework.SoftAssert SoftAssert, ThreadLocal<Driver.HashMapNew> sTestDetails) {
        super(Reporter, Assert, SoftAssert);
        api = new API(Reporter, Assert, SoftAssert, sTestDetails);
        this.Assert = Assert;
        this.commonPage = commonPage;
    }

    public void addContentToWatchlist() {
        click(watchlistIcon, "watchlist");
    }

    public void verifyIfContentAddedToWatchList() {
        Assert.assertEquals(!getDriver().findElement(watchlistIcon).getAttribute("alt").contains("remove"), true);

    }

    public Response getContentDetailFromApi(String contentId, String phoneNo) throws Exception {
        Response contentDetailResponse = api.hitContentAPI(contentId, phoneNo);
        return contentDetailResponse;
    }

    public void VerifyContentDetailFromUIandApi(Response contentDetailFromApi) {
        Assert.assertTrue(getText(tittle).toLowerCase().trim().equalsIgnoreCase(contentDetailFromApi.jsonPath().get("title")));
        //     Assert.assertTrue(getText(director).toLowerCase().trim().equalsIgnoreCase(contentDetailFromApi.jsonPath().get("director")));
        Assert.assertTrue(getText(ageRating).toLowerCase().trim().equalsIgnoreCase(contentDetailFromApi.jsonPath().get("ageRating")));
        getElementWhenClickable(moreButton, 2000).click();
        //     Assert.assertTrue(getText(shortDescription).equals((String )contentDetailFromApi.jsonPath().get("shortDescription")));
        Assert.assertTrue(checkIfElementPresent(watchlistIcon, 0));
        Assert.assertTrue(checkIfElementPresent(shareText, 0));
    }

    public void VerifyContentDetailPage() {
        Assert.assertTrue(checkIfElementPresent(watchlistIcon, 2000));
        Assert.assertTrue(checkIfElementPresent(shareText, 0));
        Assert.assertTrue(checkIfElementPresent(tittle, 0));
        Assert.assertTrue(checkIfElementPresent(player, 0));
    }

    public void clickOnWatchlistButton() {
        click(watchlistIcon, "watchlist button");
    }

    public boolean isRemoveFromWatchlistIcon() {
        return checkIfElementPresent(removeFromWatchlst, 1000);
    }

    public String getLanguage() {
        return getText(language, 1000);
    }

    public List<String> getArtistListFromUI() {
        List<WebElement> autoSuggestElementList = getWebElementsList(artistList);
        List<String> contentAutoSuggestList = new ArrayList<String>();
        for (WebElement element : autoSuggestElementList) {
            contentAutoSuggestList.add(element.getText().trim());
        }
        return contentAutoSuggestList;
    }

    public void VerifyGenereFromUIandApi(Response contentDetailFromApi) {
        Assert.assertTrue(getText(genre).trim().equalsIgnoreCase(contentDetailFromApi.jsonPath().get("genre")));
    }

    public void VerifyLanguageFromUIandApi(Response contentDetailFromApi) {
        List<String> artistListApi = contentDetailFromApi.jsonPath().get("languages");
        String lang = artistListApi.get(0);
        Assert.assertTrue(getText(language).toLowerCase().trim().equalsIgnoreCase(XStreamLanguageMapping.getShotToLongTermLanguage(lang)));

    }

    public boolean VerifyCreditsFromUIandApi(Response contentDetailFromApi) {
        List<String> artistListUI = getArtistListFromUI();
        List<String> artistListApi = contentDetailFromApi.jsonPath().get("credits.displayTitle");

        for (int i = 0; i < artistListUI.size(); i++) {
            if (!artistListApi.contains(artistListUI.get(i)))
                return false;
        }
        return true;
    }

    public void VerifyDescriptionFromUIandApi(Response contentDetailFromApi) {
        getElementWhenClickable(moreButton, 2000).click();
        String descrptionFromUI = getText(shortDescription);
        String descrptionFromAPI = contentDetailFromApi.jsonPath().get("description").toString();
        Assert.assertTrue(descrptionFromUI.equalsIgnoreCase(descrptionFromAPI.trim()));

    }

    public void verifyWatchlistIconIsPesent() {
        Assert.assertTrue(checkIfElementPresent(watchlistIcon, 2000));
    }
}
