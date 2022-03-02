package in.wynk.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import in.wynk.API.API;
import in.wynk.framework.Assert;
import in.wynk.framework.DriverFactory;
import in.wynk.framework.Language;
import in.wynk.framework.XStreamLanguageMapping;
import in.wynk.pages.CommonPage;
import in.wynk.pages.LoginPage;
import in.wynk.pages.MorePage;
import in.wynk.pages.SearchPage;
import io.restassured.response.Response;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.html5.RemoteWebStorage;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class LocalStorageCookiePage {

    LoginPage loginPage;
    Assert Assert;
    CommonPage commonPage;
    API api;
    SearchPage searchPage;
    MorePage morePage;


    public LocalStorageCookiePage(MorePage morePage, SearchPage searchPage, API api, CommonPage commonPage, LoginPage loginPage, Assert Assert) {
        this.loginPage = loginPage;
        this.Assert = Assert;
        this.commonPage = commonPage;
        this.api = api;
        this.searchPage = searchPage;
        this.morePage = morePage;

    }

    @And("Verify Local Storage data from user/login API MobileNumber (.+) - OTP (.+) - localStorageItem (.+) - LoginAPIKeys (.+)")
    public void verifyLocalStorageDataWithLoginAPI(String mobileNumber, String Otp, String localStorageItem, String loginAPIKeys) throws Exception {
        Response response = api.hitLoginAPI((String) commonPage.getGDValue(mobileNumber), (String) commonPage.getGDValue(Otp));
        String[] localItem = localStorageItem.split("\\s*,\\s*");
        String[] apiKeys = loginAPIKeys.split("\\s*,\\s*");
        Set<String> localStorageValuesFromAPI = new HashSet<>();
        Set<String> localStorageValuesFromUI = new HashSet<>();
        LocalStorage localStorage;
        if (DriverFactory.getTestDetails().get("grid").equalsIgnoreCase("true")) {
            RemoteExecuteMethod executeMethod = new RemoteExecuteMethod((RemoteWebDriver) loginPage.getDriver());
            RemoteWebStorage webStorage = new RemoteWebStorage(executeMethod);
            localStorage = webStorage.getLocalStorage();
        } else {
            localStorage = ((WebStorage) loginPage.getDriver()).getLocalStorage();
        }
        for (String s : localItem) {
            localStorageValuesFromUI.add(localStorage.getItem(s).replace("\"", ""));
        }
        for (String keys : apiKeys) {
            try {
                localStorageValuesFromAPI.add(response.jsonPath().get(keys).toString().trim());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        commonPage.twoSetWithSoftAssert(localStorageValuesFromAPI, localStorageValuesFromUI, "component's  are not visible in the localStorage", "localStorage Component's not visible in the login API", "No");
    }


    @And("Verify Selected language should be reflect in LocalStorage - Language (.+) - JiraID (.+)")
    public void verifySelectedLanguageShouldBeReflectInLocalStorage(String language, String JiraID) {
        LocalStorage localStorage;
        if (DriverFactory.getTestDetails().get("grid").equalsIgnoreCase("true")) {
            RemoteExecuteMethod executeMethod = new RemoteExecuteMethod((RemoteWebDriver) loginPage.getDriver());
            RemoteWebStorage webStorage = new RemoteWebStorage(executeMethod);
            localStorage = webStorage.getLocalStorage();
        } else {
            localStorage = ((WebStorage) loginPage.getDriver()).getLocalStorage();
        }
        String[] localStorageLang = localStorage.getItem("user_lang").replace("[\"", "").replace("\"]", "").replace("\"", " ").split(",");
       List<String> localStorageLanglist = new ArrayList<>();
        for(int i=0; i< localStorageLang.length ;i++){
            localStorageLanglist.add(String.valueOf(Language.getLanguage(XStreamLanguageMapping.getShotToLongTermLanguage(localStorageLang[i]))));
        }
        Assert.assertTrue(localStorageLanglist.contains(commonPage.getGDValue(language)) , " local language is match with selected language please check local storage functionalit");

        //  String localStorageLang = XStreamLanguageMapping.getShotToLongTermLanguage(localStorage.getItem("user_lang").replace("[\"", "").replace("\"]", "").trim());
        //  System.out.println(language + " ----- " + localStorage.getItem("user_lang").replace("[\"", "").replace("\"]", "").trim());
      //  Assert.assertTrue(localStorage.getItem("user_lang").contains(stortTermLanguage), " local language is match with selected language please check local storage functionality");
    }


    @And("verify resent search Query from local storage - Query (.+)")
    public void verifyResentSearchQueryFromLocalStorage(String querys) throws Exception {
        LocalStorage localStorage;
        if (DriverFactory.getTestDetails().get("grid").equalsIgnoreCase("true")) {
            RemoteExecuteMethod executeMethod = new RemoteExecuteMethod((RemoteWebDriver) loginPage.getDriver());
            RemoteWebStorage webStorage = new RemoteWebStorage(executeMethod);
            localStorage = webStorage.getLocalStorage();
        } else {
            localStorage = ((WebStorage) loginPage.getDriver()).getLocalStorage();
        }
        String[] queryList = querys.split("\\s*,\\s*");
        for (String query : queryList) {
            searchPage.typeString(query);
            searchPage.clickSearchButton();
            searchPage.clearSearchTextBox();
        }

        searchPage.sleep(1000);
        String localStorageSEARCH_QUERIES = localStorage.getItem("SEARCH_QUERIES");
        System.out.println(localStorageSEARCH_QUERIES);
        for (String query : queryList) {
            System.out.print(query + ", ");
            Assert.assertTrue(localStorageSEARCH_QUERIES.contains(query), query + " not visible in localStorage SEARCH_QUERIES values ");
        }
    }


    @Then("verify continue watch list and my watchlist rail content should be visible in local Storage")
    public void verifyContinueWatchListAndMyWatchlistRailContent() {
        LocalStorage localStorage;
        if (DriverFactory.getTestDetails().get("grid").equalsIgnoreCase("true")) {
            RemoteExecuteMethod executeMethod = new RemoteExecuteMethod((RemoteWebDriver) loginPage.getDriver());
            RemoteWebStorage webStorage = new RemoteWebStorage(executeMethod);
            localStorage = webStorage.getLocalStorage();
        } else {
            localStorage = ((WebStorage) loginPage.getDriver()).getLocalStorage();
        }
        String user_data = localStorage.getItem("user_data" + localStorage.getItem("uid").replace("\"", ""));
        String localStorageWatchlistData = StringUtils.substringBetween(user_data, "\"watchList\":", "\"addToWatchList\":");
        String localStorageContinueWatchlistData = StringUtils.substringBetween(user_data, "\"continueWatchingData\":", "\"continueWatchingRail\":");
        for (String watchlistContent : morePage.getWatchListContent()) {
            Assert.assertTrue(localStorageWatchlistData.contains(watchlistContent), watchlistContent + "  this watchlist content not visible local storage user_data ");
        }
        for (String continueWatchlistContent : morePage.getContinueWatchListContent()) {
            Assert.assertTrue(localStorageContinueWatchlistData.contains(continueWatchlistContent), continueWatchlistContent + "  this continue watchlist content not visible local storage user_data");
        }
    }
}
