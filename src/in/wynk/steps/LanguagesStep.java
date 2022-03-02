package in.wynk.steps;

import cucumber.api.java.en.Then;
import in.wynk.API.API;
import in.wynk.common.Utils;
import in.wynk.framework.Assert;
import in.wynk.framework.DriverFactory;
import in.wynk.framework.Language;
import in.wynk.framework.XStreamLanguageMapping;
import in.wynk.pages.*;
import io.restassured.response.Response;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LanguagesStep {

    Assert Assert;
    MorePage morePage;
    CommonPage commonPage;
    API api;
    LanguagePage languagePage;
    ContentListingPage contentListingPage;
    HomePage homePage;
    Utils utils;

    public LanguagesStep(Utils utils, HomePage homePage, ContentListingPage contentListingPage, LanguagePage languagePage, API api, CommonPage commonPage, Assert Assert, MorePage morePage) {
        this.Assert = Assert;
        this.morePage = morePage;
        this.commonPage = commonPage;
        this.api = api;
        this.languagePage = languagePage;
        this.contentListingPage = contentListingPage;
        this.homePage = homePage;
        this.utils = utils;
    }

    @Then("^verify Languages from Language Page(.+) jiraID (.+)$")
    public void verify_Languages_from_Language_Page(String languages, String jiraID) throws Exception {
        String listOfLanguages = (String) languagePage.getGDValue(languages);
        Assert.assertTrue(languagePage.getAllLanguagesFromUI().containsAll(Arrays.asList(listOfLanguages.split("\\s*,\\s*")).stream().collect(Collectors.toSet())), languages + " not contains in language page JiraID" + jiraID);
    }

    @Then("^verify selected Languages (.+) from  userConfig API for User(.+)$")
    public void verify_Languages_from_API(String lang, String mobileNumber) throws Exception {
        String language = (String) languagePage.getGDValue(lang);
        languagePage.selectLanguage((String) languagePage.getGDValue(lang));
        languagePage.clickOnSubmit();
        languagePage.sleep(3000);
        languagePage.getDriver().navigate().refresh();
        commonPage.navigateToPage(CommonPage.navigationOption.LANGUAGE);
        List<String> uiLang = languagePage.getSelectedLang();
        Response response = api.hitUserProfileConfigAPI((String) languagePage.getGDValue(mobileNumber));
        String langValueFromAPI = response.path("userInfo.lang").toString().replace("[", "").replace("]", "").trim();
        Assert.assertTrue(String.valueOf(Language.getLanguage(XStreamLanguageMapping.getShotToLongTermLanguage(langValueFromAPI))).equals(language), " sselected Languages are not samee on userConfig Api and Ui");
    }

    @Then("^select Language(.+)$")
    public void select_language(String langName) throws Exception {
        languagePage.selectLanguage((String) languagePage.getGDValue(langName));
        languagePage.clickOnSubmit();
    }

    @Then("select (.+) and click on submit button")
    public void select_and_click_on_submit_button(String language) {
        languagePage.selectLanguage((String) languagePage.getGDValue(language));
        languagePage.clickOnSubmit();
    }

    @Then("Verify that user languague screen UI and text (.+)")
    public void verify_that_user_languague_screen_UI_and_text(String langugaes) {
        languagePage.verifyLanguagePage((String) languagePage.getGDValue(langugaes));
    }


    @Then("Verify language (.+) is updated")
    public void verify_language_language_is_updated(String language) {
        languagePage.VerifySelectedLanguage((String) languagePage.getGDValue(language));
    }

    @Then("Verify User should be able to select minimum {int} language and maximum {int} languages")
    public void verify_User_should_be_able_to_select_minimum_language_and_maximum_languages(int min, Integer int2) {
        languagePage.VerifyMinimumLanguage(min);
    }

    @Then("Verify User should be able to select min (.+) languages")
    public void verify_User_should_be_able_to_select_min_languages(String minCount) {
        int minLangCount = Integer.parseInt(String.valueOf(languagePage.getGDValue(minCount)));
        languagePage.VerifyMinimumLanguage(minLangCount);
    }

    @Then("Verify User should be able to select max (.+) languages")
    public void verify_User_should_be_able_to_select_max_languages(String maxCount) {
        int maxLangCount = Integer.parseInt(String.valueOf(languagePage.getGDValue(maxCount)));
        languagePage.selectedMaximumLanguage(maxLangCount);
        Assert.assertTrue(languagePage.getSelectedLang().size() == maxLangCount);
        languagePage.clickOnSubmit();
        commonPage.navigateToPage(CommonPage.navigationOption.LANGUAGE);
        Assert.assertTrue(languagePage.getSelectedLang().size() == maxLangCount);

    }

    @Then("Verify default language (.+) should be selected")
    public void verify_default_language_should_be_selected(String defaultLanguage) {
        languagePage.VerifySelectedLanguage((String) languagePage.getGDValue(defaultLanguage));
    }

    @Then("Verify that user can see is only language (.+)")
    public void verify_that_user_can_see_is_only_language(String languages) {
        String langList = (String) languagePage.getGDValue(languages);
        Assert.assertTrue(languagePage.getAllLanguagesFromUI().containsAll(Arrays.asList(langList.split("\\s*,\\s*")).stream().collect(Collectors.toSet())), languages + " not contains in language page JiraID");

    }

    @Then("update language and sumbit")
    public void update_language_and_sumbit() {
        languagePage.updateLanguage();
    }

    @Then("verify language should not change")
    public void verify_language_should_not_change() {

    }

    @Then("select default language(.+)")
    public void select_default_langugae(String defaultLang) {
        languagePage.setDefaultLanguage((String) languagePage.getGDValue(defaultLang));
        languagePage.updateLanguage();
        commonPage.navigateToPage(CommonPage.navigationOption.LANGUAGE);
        Assert.assertTrue(languagePage.getSelectedLang().size() == 1 && languagePage.getSelectedLang().get(0).equals((String) languagePage.getGDValue(defaultLang)));
    }

    @Then("Verify that selected language for user A should be (.+)")
    public void verify_that_selected_language_for_user_A_should_be(String UserALanguage) {
        Assert.assertTrue(languagePage.getSelectedLang().contains(languagePage.getGDValue(UserALanguage)));
    }

    @Then("Verify User should be able to re-select the language (.+) again")
    public void verify_User_should_be_able_to_re_select_the_language_again(String language) {
        languagePage.selectLanguage((String) languagePage.getGDValue(language));
        Assert.assertTrue(languagePage.getSelectedLang().contains(languagePage.getGDValue(language)));
        languagePage.unSelectLanguage((String)languagePage.getGDValue(language));
        Assert.assertTrue(!languagePage.getSelectedLang().contains(languagePage.getGDValue(language)));
        languagePage.selectLanguage((String) languagePage.getGDValue(language));
        Assert.assertTrue(languagePage.getSelectedLang().contains(languagePage.getGDValue(language)));

    }
    @Then("Verify already selected lang (.+) should appear to user again")
    public void verify_already_selected_lang_should_appear_to_user_again(String language) {
        Assert.assertTrue(languagePage.getSelectedLang().contains(languagePage.getGDValue(language)));
    }


}

