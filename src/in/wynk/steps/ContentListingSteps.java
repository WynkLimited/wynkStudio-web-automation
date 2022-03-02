package in.wynk.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import in.wynk.framework.SoftAssert;
import in.wynk.pages.CommonPage;
import in.wynk.pages.ContentListingPage;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ContentListingSteps {

    in.wynk.framework.Assert Assert;
    SoftAssert softAssert;
    ContentListingPage contentListingPage;
    CommonPage commonPage;

            public ContentListingSteps(SoftAssert softAssert, ContentListingPage contentListingPage, CommonPage commonPage, in.wynk.framework.Assert Assert) {
        this.Assert = Assert;
        this.contentListingPage = contentListingPage;
        this.commonPage = commonPage;
        this.softAssert = softAssert;
    }

    @When("^verify (.+) Rails Layout From API content(.+) JiraID (.+)$")
    public void verify_Rails_Form_LayoutAPI(String PageName, String Number,String JiraID) throws Exception {
        String mobileNumber =  (String) contentListingPage.getGDValue(Number);
        Set<String> railNameFromAPI = new HashSet<>();
        Set<String> railNameFromUI = new HashSet<>();
        switch (PageName) {
            case "HOME PAGE":
                railNameFromUI.addAll(contentListingPage.getRailsNameList(300000, 1).stream().collect(Collectors.toSet()));
                railNameFromAPI.addAll(contentListingPage.getRailsNameFromLayoutAPI(mobileNumber, commonPage.getPageID(CommonPage.PageID.HOME), "en,hi").stream().collect(Collectors.toSet()));
                railNameFromUI.remove("My Watchlist");
                railNameFromUI.remove("Continue Watching");
                railNameFromAPI.remove("CONTINUE_WATCHING");
                railNameFromAPI.remove("WATCHLIST");

                // these rails not contain any content, these are remove because in front contains check if these rails comes from backend  so it should not reflect on UI.
//                railNameFromAPI.remove("Download Now");
//                railNameFromAPI.remove("Watch till it lasts");
//                railNameFromAPI.remove("Hollywood Horror Movies");
                break;
            case "TVSHOWS PAGE":
                railNameFromAPI.addAll(contentListingPage.getRailsNameFromLayoutAPI(mobileNumber, commonPage.getPageID(CommonPage.PageID.TVSHOWS), "en,hi").stream().collect(Collectors.toSet()));
                railNameFromUI.addAll(contentListingPage.getRailsNameList(300000, 15).stream().collect(Collectors.toSet()));
//                railNameFromAPI.remove("Action, Drama, Thrill");
//                railNameFromAPI.remove("Popular In Drama");
//                railNameFromAPI.remove("Popular Malayalam Web Series");
//                railNameFromAPI.remove("Popular Kannada Web Series");
//                railNameFromAPI.remove("English TV Shows");

                break;
            case "MOVIES PAGE":
                railNameFromAPI.addAll(contentListingPage.getRailsNameFromLayoutAPI(mobileNumber, commonPage.getPageID(CommonPage.PageID.MOVIES), "en,hi").stream().collect(Collectors.toSet()));
                railNameFromUI.addAll(contentListingPage.getRailsNameList(300000, 15).stream().collect(Collectors.toSet()));
//                railNameFromAPI.remove("Hollywood Comedy");
//                railNameFromAPI.remove("Bollywood Evergreen Classics");
//                railNameFromAPI.remove("Hollywood Horror");
//                railNameFromAPI.remove("#SquadGoals");
                break;
        }
        railNameFromAPI.remove("BANNER");
        System.out.println(railNameFromAPI);
        System.out.println(railNameFromUI);
        commonPage.aPIuIAssertionBetweenTwoSet(railNameFromAPI, railNameFromUI, "Rails", PageName, "Hindi English", JiraID);

    }

    @Then("^verify You may also like content for (.+) language contentType (.+) apiLang (.+) and user (.+) JiraID (.+)$")
    public void verifyYouMayAlsoLikeContent(String language, String pageName, String apiLang, String number , String jiraID) throws Exception {
        String mobileNumber = (String) commonPage.getGDValue(number);
        List<String> railNameWithId = new ArrayList<>();
        String randomSelectRail = "";
        String contentURL = "";
        Set<String> recommendedContentFormAPI = new HashSet<>();
        Set<String> recommendedContentFromUI =  new HashSet<>();

        switch (pageName) {
            case "Movies":
                railNameWithId = commonPage.getNonLiveRailsNameAndPackIDLayoutAPI(mobileNumber, commonPage.getPageID(CommonPage.PageID.MOVIES), apiLang.trim());
                break;
            case "TVShows":
                railNameWithId = commonPage.getNonLiveRailsNameAndPackIDLayoutAPI(mobileNumber, commonPage.getPageID(CommonPage.PageID.TVSHOWS), apiLang.trim());
                break;
            case "LiveTV":
                railNameWithId = commonPage.getNonLiveRailsNameAndPackIDLayoutAPI(mobileNumber, commonPage.getPageID(CommonPage.PageID.LiveTV), apiLang.trim());
                break;
        }


        for (int i = 0; i <= railNameWithId.size() - 1; i++) {
            randomSelectRail = commonPage.getRandomRailSelection(language, railNameWithId);
            String name = StringUtils.substringBefore(randomSelectRail.trim(), "|").trim();
            String id = StringUtils.substringAfter(randomSelectRail.trim(), "|").trim();
            contentURL = contentListingPage.getRandomContentURLFromAPI(mobileNumber, id);
            if (contentURL.contains("404")) {
                System.out.println(name + "|" + id + " not playable ");
                railNameWithId.remove(name + "|" + id);
            } else {
                break;
            }
        }
        System.out.println("content  ULR " + contentURL);
        commonPage.openURL(contentURL);
       // commonPage.launchUrl("https://www.airtelxstream.in/tv-shows/kamini-hindi/HOICHOI_TVSHOW_2244", false);
        commonPage.sleep(1000);
        recommendedContentFormAPI.addAll(contentListingPage.getRecommendedContentFromRelatedAPI(mobileNumber, StringUtils.substringAfterLast(contentURL.trim(), "/").trim(), "title").stream().collect(Collectors.toSet()));
      //  recommendedContentFormAPI.addAll(contentListingPage.getRecommendedContentFromRelatedAPI(mobileNumber, "HOICHOI_TVSHOW_2244", "title").stream().collect(Collectors.toSet()));
        commonPage.scrollToRail("You may also like", 5000);
        if (commonPage.isRailVisibleOnScreen("You may also like")) {
            recommendedContentFromUI.addAll(contentListingPage.getRecommendedContentFromUI());
            commonPage.aPIuIAssertionBetweenTwoSet(recommendedContentFormAPI, recommendedContentFromUI, "*You may also like*", pageName, language, jiraID);

        }
        else{
            Assert.fail(contentURL +" This content not contain any recommended content");
        }

    }
}
