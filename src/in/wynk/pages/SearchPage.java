package in.wynk.pages;

import in.wynk.API.APICommon;
import io.restassured.response.Response;
import org.apache.commons.collections.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import in.wynk.common.DriverActionUtils;
import in.wynk.framework.Reporting;
import org.openqa.selenium.WebElement;
import in.wynk.API.API;

import java.util.*;

public class SearchPage extends DriverActionUtils {
    API api;
    ContentDetailPage contentDetailPage;
    LoginPage loginpage;

    public SearchPage(ContentDetailPage contentDetailPage, LoginPage loginpage, Reporting Reporter, in.wynk.framework.Assert Assert, in.wynk.framework.SoftAssert SoftAssert) {
        super(Reporter, Assert, SoftAssert);
        api = new API(Reporter, Assert, SoftAssert, sTestDetails);
        this.contentDetailPage = contentDetailPage;
        this.loginpage = loginpage;
    }

    private By searchInput = By.id("search-bar");
    private By searchButton = By.id("nav-Search");
    private By recentSearchClearButton = By.id("remove-recent-search");
    private By searchedContent = By.xpath("//*[@id=\"nav-Search\"]/div[1]/div/div[3]/ul/li[1]/a");
    private By NoResultFountText = By.xpath("//div[@class='no-results']//span");
    private By searchResultFound = By.xpath("//div[@class='search-result-found']//span");

    private By crossButton = By.xpath("//div[@class='inline-search-input-cancel']");
    private By firstAutoSuggest = By.xpath("//li[@class=' suggest-item-with-title '][1]");
    private By AutoSuggestList = By.xpath("//li[@class=' suggest-item-with-title ']");
    private By recentSearches = By.xpath("//ul[@class='desktop-recent-search-suggestions']//li//a");
    private By selectedMoviesContent = By.xpath("//div[contains(@class , 'cards-portrait')][1]");
    private By selectedTVshowContent = By.xpath("//div[contains(@class , 'cards-landscape')][1]");
    private By contentTypeTitle = By.xpath("//h2");
    private By recentSearchesText = By.xpath("//h2[text()='Recent Searches']");
    private By trendingText = By.xpath("//h2[text()='Trending']");
    private By trendingshowList = By.xpath("//div[contains(@class , 'trending-search-cards contain-layout')]");
    private By showAllButton = By.xpath("//a[text()='Show All']");
    private By trendingList = By.xpath("//div[contains(@class , 'card-bottom-left-details')]//h2");

    public void typeString(String text) throws Exception {
        if (getDriverType().trim().toUpperCase().contains("CHROME"))
            sendKeys(searchInput, text, Keys.ENTER);
        else
            sendKeys(searchInput, text, Keys.ENTER);
    }

    public void clearSearchTextBox() {
        getDriver().findElement(searchInput).clear();
    }

    public void clickSearchButton() {
        getElementWhenClickable(searchButton, 3000).click();
    }

    public void clickSearchedContent(String contentName) {
        By searchedContent = By.xpath("//div[contains(@class,'cards cards-portrait  contain-layout' )][1]");
        getElementWhenClickable(searchedContent, 3000).click();
//    click(searchedContent, "Content Card");
    }

    public void clickOnClearButton() {
        if (getDriver().findElements(recentSearchClearButton).size() > 0) {
            click(recentSearchClearButton, "recentSearchClearButton", 2);
        }
    }


    public void searchContent(String contentName) throws Exception {
        typeString(contentName);
    }

    public void verifyNoResultFoundText(String contentName) {
        Assert.assertTrue(getText(NoResultFountText).toLowerCase().trim().contains("no results found for " + (contentName.toLowerCase()).toLowerCase()));
    }

    public void clickOnCrossButton() {
        getElementWhenClickable(crossButton, 2000).click();

    }

    public void verifySearchbarIsEmpty() {
        Assert.assertTrue(getText(searchInput).toLowerCase().trim().isEmpty());

    }

    public void verifyTheSearchbarPlaceholderText() {
        Assert.assertTrue(getAttribute(searchInput, "placeholder").toLowerCase().trim().contains("Find something to watch...".toLowerCase()));

    }

    public void VerifyTheSearchBehaviour(String contentName) {
        Assert.assertTrue(getText(searchResultFound).toLowerCase().trim().contains("search results found for \"" + (contentName.toLowerCase()) + "\""));
        System.out.println(getText(searchResultFound).toLowerCase().trim());
        System.out.println("search results found for \"" + (contentName.toLowerCase()) + "\"");
    }

    public void clickOnSearchbar() {
        getElementWhenClickable(searchInput, 2000).click();

    }

    public void selectContentFromAutosuggestDropdown() {
        getElementWhenClickable(firstAutoSuggest, 2000).click();
    }

    public void verifySearchedContentUnderTheRecentSearched(String contentName) {
        List<WebElement> RecentSearchedElementList = getWebElementsList(recentSearches);
        List<String> recentSearchList = new ArrayList<String>();
        for (WebElement element : RecentSearchedElementList) {
            recentSearchList.add(element.getText().toLowerCase());
        }
        Assert.assertTrue(recentSearchList.contains(contentName.toLowerCase()));
    }

    public void typeContentNameOnSearchBar(String contentName) throws Exception {
        if (getDriverType().trim().toUpperCase().contains("CHROME"))
            sendKeys(searchInput, contentName);
        else
            sendKeys(searchInput, contentName);
    }

    public List<String> getAllAutosuggestListFromApi(String mobileNumber, String query) throws Exception {
        Response userProfileResponse = api.hitUserProfileConfigAPI(mobileNumber);
        Map<String, String> userConfigParams = APICommon.getUserContentProperties(userProfileResponse);
        Response autoSuggestResponse = api.hitAutoSuggestAPI(mobileNumber, userConfigParams, query);
        List<String> autosuggestTitles = APICommon.getAutosuggestionTitles(autoSuggestResponse);
        return autosuggestTitles;
    }

    public List<String> getAllAutosuggestListFromUI() {
        List<WebElement> autoSuggestElementList = getWebElementsList(AutoSuggestList);
        List<String> contentAutoSuggestList = new ArrayList<String>();
        for (WebElement element : autoSuggestElementList) {
            contentAutoSuggestList.add(element.getText().trim());
        }
        return contentAutoSuggestList;
    }

    public int getAllContentCountFromApi(String mobileNumber, String query) throws Exception {
        Response userProfileResponse = api.hitUserProfileConfigAPI(mobileNumber);
        Map<String, String> userConfigParams = APICommon.getUserContentProperties(userProfileResponse);
        Response searchApiResponse = api.hitSearchAPI(mobileNumber, userConfigParams, query);
        int contentCount = APICommon.getContentCount(searchApiResponse);
        return contentCount;
    }


    public void AssertionBetweenTwoList(List<String> listFromAPI, List<String> listFromUI, String query) {

        ArrayList<String> intersection = (ArrayList<String>) CollectionUtils.intersection(listFromAPI, listFromUI);
        ArrayList<String> extraInAPI = (ArrayList<String>) CollectionUtils.disjunction(listFromAPI, intersection);
        ArrayList<String> extraInUI = (ArrayList<String>) CollectionUtils.disjunction(listFromUI, intersection);

        if (extraInAPI.size() > 0 && extraInUI.size() > 0) {
            Assert.fail("AutoSuggestiondo not matcxh with API response");
        }
        if (extraInAPI.size() > 0) {
            Assert.fail("AutoSuggestiondo not matcxh with API response");
        }
        if (extraInUI.size() > 0) {
            Assert.fail("AutoSuggestiondo not matcxh with API response");
        }
    }

    public void VerifyNoAutoSuggestForContentTextLengthLessThanThree(String contentName) {
        Assert.assertTrue(!checkIfElementPresent(AutoSuggestList, 0));
    }

    public void VerifyAutoSuggestForContentTextLengthGreaterThanTwo(String contentName) {
        Assert.assertTrue(checkIfElementPresent(AutoSuggestList, 1000));
    }

    public void selectContent(String contentType) {
        if(contentType.equalsIgnoreCase("movies"))
        getElementWhenClickable(selectedMoviesContent, 1000).click();
        else
            getElementWhenClickable(selectedTVshowContent, 2000).click();
    }

    public int getAllContentCountFromUI() {
        int count = 0;
        List<WebElement> contentTypeList = getWebElementsList(contentTypeTitle);
        List<String> contentAutoSuggestList = new ArrayList<String>();
        for (WebElement element : contentTypeList) {
            String str[]= element.getText().split(" ");
            count = count + Integer.parseInt(String.valueOf(str[str.length-1].substring(1,str[str.length-1].length()-1)));
        }
        return count;
    }

    public void VerifyContentCountIsSameForUIandApi(int contentCountFromAPI, int contentCountFromtUI) {
        Assert.assertTrue(contentCountFromAPI == contentCountFromtUI);
    }

    public void verifySearchPage() {
        Assert.assertTrue(checkIfElementPresent(searchButton, 1000));
        getElementWhenClickable(searchButton, 1000).click();
        Assert.assertTrue(getAttribute(searchInput, "placeholder").toLowerCase().trim().contains("Find something to watch...".toLowerCase()));

        Assert.assertTrue(checkIfElementPresent(trendingText, 0));
        if (checkIfElementPresent(recentSearches, 0)) {
            Assert.assertTrue(checkIfElementPresent(recentSearchesText, 0));
            Assert.assertTrue(checkIfElementPresent(recentSearchClearButton, 0));
        }
    }

    public void searchMultipleContent(String contents) throws Exception {
        String[] queryList = contents.split("\\s*,\\s*");
        for (String query : queryList) {
            typeString(query);
            clickOnSearchbar();
            clickOnCrossButton();
        }
    }

    public void VerifySearchedContentUnderRecentSearches(String contents) {
        List<String> contentsList = Arrays.asList(contents.split("\\s*,\\s*"));
        List<String> UIcontentList = new ArrayList<>();

        List<WebElement> UIcontentsListElemeents = getWebElementsList(recentSearches);
        for (WebElement element : UIcontentsListElemeents) {
            UIcontentList.add(element.getText().trim());
        }
        AssertionBetweenTwoList(contentsList, UIcontentList, contents);

    }

    public void VerifyTrendingAndRecentsearches() {
        clickOnSearchbar();
        if (checkIfElementPresent(recentSearches, 0)) {
            Assert.assertTrue(checkIfElementPresent(recentSearchesText, 0));
            Assert.assertTrue(checkIfElementPresent(recentSearchClearButton, 0));
            Assert.assertTrue(checkIfElementPresent(recentSearches, 0));
        }
        Assert.assertTrue(checkIfElementPresent(trendingText, 0));
        Assert.assertTrue(checkIfElementPresent(trendingshowList, 0));
        Assert.assertTrue(checkIfElementPresent(showAllButton, 0));

    }

    public void VerifySearchRelatedToLanguage(String language) {
        getElementWhenClickable(selectedMoviesContent, 1000).click();
        Assert.assertTrue(language.trim().toLowerCase().equals(contentDetailPage.getLanguage().trim().toLowerCase()));
    }

    public void VerifySearchRelatedToArtist(String artistName) {
        getElementWhenClickable(selectedMoviesContent, 1000).click();
        List<String> artistListFromUI = contentDetailPage.getArtistListFromUI();
        Assert.assertTrue(artistListFromUI.contains(artistName.trim()));
    }


    public void VerifyTrendingListConsistentforAllUser(String userAmobileNumber, String userBmobileNumber, String otp) throws Exception {
        List<String> trendingListForUserA = getTrendingList(userAmobileNumber, otp);
        List<String> trendingListForUserB = getTrendingList(userBmobileNumber, otp);
        System.out.println(trendingListForUserA.size() + "******"+trendingListForUserB.size());
        Assert.assertTrue(trendingListForUserA.size()==trendingListForUserB.size());
    }

    private List<String> getTrendingList(String userAmobileNumber, String otp) throws Exception {
        loginpage.loginWithoutGUI(userAmobileNumber, otp);
        clickSearchButton();
        sleep(2000);
        getElementWhenClickable(trendingText, 1000).click();

        List<WebElement> elements = getWebElementsList(trendingList);
        List<String> trendingShowList = new ArrayList<String>();
        for (WebElement element : elements) {
            trendingShowList.add(element.getText().trim().toLowerCase());
        }
        return trendingShowList;
    }

}
