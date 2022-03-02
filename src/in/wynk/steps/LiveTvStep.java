package in.wynk.steps;

import cucumber.api.java.en.Then;

import cucumber.api.java.en.When;
import in.wynk.API.API;
import in.wynk.API.APICommon;
import in.wynk.framework.Assert;
import in.wynk.framework.SoftAssert;
import in.wynk.pages.*;
import io.restassured.response.Response;
import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class LiveTvStep {
    LiveTvPage liveTvPage;
    in.wynk.framework.Assert Assert;
    HomePage homePage;
    CommonPage commonPage;
    ContentListingPage contentListingPage;
    API api;
    SoftAssert softAssert;
    PlayerPage playerPage;

    public LiveTvStep(PlayerPage playerPage, SoftAssert softAssert, API api, ContentListingPage contentListingPage, LiveTvPage liveTvPage, HomePage homePage, CommonPage commonPage, Assert Assert) {
        this.liveTvPage = liveTvPage;
        this.Assert = Assert;
        this.homePage = homePage;
        this.commonPage = commonPage;
        this.contentListingPage = contentListingPage;
        this.api = api;
        this.softAssert = softAssert;
        this.playerPage = playerPage;


    }

    @When("^verify (.+) Live Tv rails layout from layout API for (.+) and API lng(.+) jiraID (.+)$")
    public void verifyLiveTvRailsFromLayoutAPI(String languages, String number, String apiLang, String jiraID) throws Exception {
        String mobileNumber = (String) liveTvPage.getGDValue(number);
        Set<String> railNameFromAPI = new HashSet<>();
        Set<String> railNameFromUI = new HashSet<>();
        railNameFromAPI.addAll(contentListingPage.getRailsNameFromLayoutAPI(mobileNumber, commonPage.getPageID(CommonPage.PageID.LiveTV), apiLang.trim()).stream().collect(Collectors.toSet()));
        railNameFromUI.addAll(liveTvPage.getLiveTVRailsNameListFromUI(30000, 15));
        railNameFromAPI.remove("BANNER");
        commonPage.aPIuIAssertionBetweenTwoSet(railNameFromAPI, railNameFromUI, "*Rails*", "Live TV", languages, jiraID);
    }


    @When("^scrolling To rail and verify more and arrow button jiraID (.+)$")
    public void scroll_to_rail_and_verify_elements(String jiraID) throws Exception {
        liveTvPage.scrollToLiveTvRail("Music", 30000);
        Assert.assertTrue(liveTvPage.verifyPrevButtonOnRail("Music"), "previous content button not visible on Music rails **jiraID " + jiraID);
        Assert.assertTrue(liveTvPage.verifyNextButtonOnRail("Music"), "next  content button not visible on Music rails **jiraID " + jiraID);
    }


    @When("^scrolling To rail and verify previous and next arrow button Functionality jiraID (.+)$")
    public void verify_Previous_and_Next_Button_Functionality_for_Rails(String jiraID) throws Exception {
        liveTvPage.scrollToLiveTvRail("Live News", 30000);
        Assert.assertTrue(liveTvPage.verifyNextBtnFunctionality("Live News"), "rail arrow next button not working **jiraID " + jiraID);
//        Assert.assertTrue(liveTvPage.verifyPreviousBtnFunctionality("Regional Live News"),"rail arrow previous button not working");
    }

    @Then("^scroll To rail(.+)$")
    public void scroll_To_Rail(String railName) throws Exception {
        liveTvPage.scrollToLiveTvRail(railName.trim(), 30000);
    }

    @Then("^click On rail(.+)$")
    public void click_On_Rail(String railName) throws Exception {
        liveTvPage.clickOnRail(railName);
    }


    @When("^verify (.+) All Rails Channels from API when API lang is (.+) for MobileNumber(.+) jiraID (.+)$")
    public void verify_AllRails_Channels_from_API(String languages, String apiLang, String number, String jiraID) throws Exception {
        String mobileNumber = (String) liveTvPage.getGDValue(number);
        int count = 0;
        Set<String> railNameFromAPI = new HashSet<>();
        Set<String> channelsNameFromUI = new HashSet<>();
        Set<String> liveChannelContentBasedOnGenreFromAPI = new HashSet<>();
        railNameFromAPI.addAll(liveTvPage.getRailsNameAndPackIDFromLiveTVLayoutAPI(mobileNumber, commonPage.getPageID(CommonPage.PageID.LiveTV), apiLang.trim()));

        for (String railWithPackid : railNameFromAPI) {
            String railPackageID = StringUtils.substringBetween(railWithPackid.trim(), "|,", "||").trim();
            Response packagesContent = api.hitPackagesContentAPI(mobileNumber, railPackageID, null);
            if (packagesContent.statusCode() == 200) {
                liveChannelContentBasedOnGenreFromAPI.addAll(APICommon.getContentByPackageId(packagesContent, railPackageID, "title").stream().collect(Collectors.toSet()));
             //   commonPage.launchUrl("https://www.airtelxstream.in/livetv-channels/" + StringUtils.substringBefore(railWithPackid.trim(), "|") + "/list/" + StringUtils.substringAfterLast(railWithPackid.trim(), "||"), false);
                commonPage.openURL("/livetv-channels/" + StringUtils.substringBefore(railWithPackid.trim(), "|") + "/list/" + StringUtils.substringAfterLast(railWithPackid.trim(), "||"));
                commonPage.sleep(2000);
                channelsNameFromUI.addAll(liveTvPage.getAllChannelsNameFromGenresPage(30000));
                commonPage.pageUP();
                count = count + commonPage.aPIuIAssertionBetweenTwoSetWithSoftAssert(liveChannelContentBasedOnGenreFromAPI, channelsNameFromUI, railWithPackid, "LiveTV", languages, jiraID);
                channelsNameFromUI.clear();
                liveChannelContentBasedOnGenreFromAPI.clear();

            } else {
                System.out.println(railWithPackid + " this content not valid for " + apiLang + " language");
                softAssert.fail(railWithPackid + " this content not valid for " + apiLang + " language");
                count++;
            }

        }
        Assert.assertTrue(count < 1, " some soft assert fail please check inside");

    }


    @Then("^click On Sorting Button$")
    public void click_ON_Sorting_Button() throws Exception {
        liveTvPage.clickOnSortingButton();
    }


    @Then("^verify Sorting options(.+) for (.+) and (.+) jiraID (.+)$")
    public void verify_Sorting_Options(String sortingType, String number, String apiLang, String jiraID) throws Exception {
        String mobileNumber = (String) liveTvPage.getGDValue(number);
        String[] sortingOptions = sortingType.split(",");
        Set<String> railNameFromAPI = new HashSet<>();
        int count = 0;
        Set<String> liveChannelContentBasedOnGenre = new HashSet<>();
        railNameFromAPI.addAll(liveTvPage.getRailsNameAndPackIDFromLiveTVLayoutAPI(mobileNumber, commonPage.getPageID(CommonPage.PageID.LiveTV), apiLang.trim()));

        for (String railWithPackid : railNameFromAPI) {

            String railPackageID = StringUtils.substringBetween(railWithPackid.trim(), "|,", "||").trim();

            Response packagesContent = api.hitPackagesContentAPI(mobileNumber, railPackageID, null);

            if (packagesContent.statusCode() == 200) {

                liveChannelContentBasedOnGenre.addAll(APICommon.getContentByPackageId(packagesContent, railPackageID, "title").stream().collect(Collectors.toSet()));

              //  commonPage.launchUrl("https://www.airtelxstream.in/livetv-channels/" + StringUtils.substringBefore(railWithPackid.trim(), "|") + "/list/" + StringUtils.substringAfterLast(railWithPackid.trim(), "||"), false);
                commonPage.openURL("/livetv-channels/" + StringUtils.substringBefore(railWithPackid.trim(), "|") + "/list/" + StringUtils.substringAfterLast(railWithPackid.trim(), "||"));
                liveTvPage.clickOnSortingButton();
                for (String options : sortingOptions) {
                    Assert.assertTrue(liveTvPage.getSortingOptions().contains(options), options + " not available in rails channels options **jiraID " + jiraID);
                }
            } else {
                System.out.println(railWithPackid + " this content not valid for " + apiLang + " language");
                softAssert.fail(railWithPackid + " this content not valid for " + apiLang + " language");
                count++;
            }
            Assert.assertTrue(count < 1, " some soft assert fail please check inside");
        }


    }


    @Then("^Verify that Live TV page is visible on the home page(.+)$")
    public void verify_LiveTV_On_HomePage(String totalScrollTime) {
        Set<String> homePageRails = homePage.getAllHomeChannelsWithScroll(Integer.parseInt(totalScrollTime));
        Assert.assertTrue(homePageRails.toString().trim().contains("Live"), " Live news Rail Not in Home Page");
        Assert.assertTrue(homePageRails.toString().trim().contains("Channels"), " Live news Rail Not in Home Page");
    }


    @Then("^Verify Channels Content Details Like Time / Show Name / From EPG and Content API(.+)$")
    public void verify_Channels_Content_details(String mobileNumber) {
        List<String> mixChannelList = new ArrayList<>();
        List<String> channelsNameFromAPI = new ArrayList<>();

        List<String> liveTvRailsNameFromAPI = liveTvPage.getAllRailsNameFromAPI(mobileNumber.trim());
        Response response = liveTvPage.getChannelContentAPIResponse(mobileNumber, liveTvPage.getUserContentPropertiesParams(mobileNumber));
        Response response1 = liveTvPage.getEPGContentAPIResponse(mobileNumber, liveTvPage.getUserContentPropertiesParams(mobileNumber));

        for (String RailsName : liveTvRailsNameFromAPI) {
            channelsNameFromAPI = liveTvPage.getAllChannelsTitleAndIDFromAPIByGenres(response, RailsName);
            String s = channelsNameFromAPI.get(new Random().nextInt((channelsNameFromAPI.size() - 1) - 0));
            mixChannelList.add(s + " || channel info " + liveTvPage.getChannelsProgramInfo(response1, StringUtils.substringBetween(s, "id:-  ", " & title:-")));
        }

        for (String RailsName : mixChannelList) {
            System.out.println(RailsName);
            String channelID = StringUtils.substringBetween(RailsName, "id:-  ", " & title:-");
            String channelName = StringUtils.substringBetween(RailsName, "& title:- ", " && lang:-");
           // liveTvPage.launchUrl("https://www.airtelxstream.in/livetv channels/" + channelName.trim() + "/" + channelID.trim() + "", false);
            commonPage.openURL("/livetv channels/" + channelName.trim() + "/" + channelID.trim() + "");
            liveTvPage.sleep(1000);
            Assert.assertTrue(liveTvPage.verifyChannelLogoIsVisible());
            Assert.assertTrue(RailsName.toLowerCase().contains(liveTvPage.getChannelName().toLowerCase()));
            Assert.assertTrue(RailsName.toLowerCase().contains(liveTvPage.getChannelProgramName().toLowerCase()));
            Assert.assertTrue(RailsName.toLowerCase().contains(liveTvPage.getChannelProgramGenre().toLowerCase()));

        }
    }


    @Then("^Verify channel schedule rail MobileNumber (.+) - API Lang (.+)$")
    public void verify_channel_schedule_rail(String number, String apiLang) throws Exception {
        String mobileNumber = (String) liveTvPage.getGDValue(number);
        Set<String> railNameFromAPI = new HashSet<>();
        railNameFromAPI.addAll(liveTvPage.getRailsNameAndPackIDFromLiveTVLayoutAPI(mobileNumber, commonPage.getPageID(CommonPage.PageID.LiveTV), apiLang.trim()));
        for (String railWithPackId : railNameFromAPI) {
            System.out.println(railWithPackId);
            String railPackageID = StringUtils.substringBetween(railWithPackId.trim(), "|,", "||").trim();
            Response packagesContent = api.hitPackagesContentAPI(mobileNumber, railPackageID, null);
            String randomContentURL = APICommon.getRandomContentURL(packagesContent);
           // sideBarPage.openURL(randomContentURL);
          //  liveTvPage.launchUrl("https://www.airtelxstream.in/LIVETVCHANNEL/9XM/MWTV_LIVETVCHANNEL_552", false);
            commonPage.openURL("/LIVETVCHANNEL/9XM/MWTV_LIVETVCHANNEL_552");
            Long currentTime = System.currentTimeMillis();
            //Response scheduleResponse = api.hitEPGContentAPI(mobileNumber, "channelId="+StringUtils.substringAfterLast(randomContentURL, "/"), String.valueOf(currentTime - 14400000), String.valueOf(currentTime + 14400000));
            //           Set<String> contentFromAPI = APICommon.getEPGResponseKeyValuesFor(scheduleResponse,StringUtils.substringAfterLast(randomContentURL, "/"));

            Response scheduleResponse = api.hitEPGContentAPI(mobileNumber, "channelId=MWTV_LIVETVCHANNEL_552", String.valueOf(currentTime - 18000000), String.valueOf(currentTime + 14400000));
            Set<String> contentFromAPI = APICommon.getScheduleRailContentNameWithTime(scheduleResponse, "MWTV_LIVETVCHANNEL_552");
            System.out.println(scheduleResponse.getBody().asString());
            liveTvPage.sleep(1000);
            Assert.assertEquals(commonPage.aPIuIAssertionBetweenTwoSetWithSoftAssert(contentFromAPI, playerPage.getLiveTVChannelScheduleRailContentNameAndTime(), "Channel Schedule Rail", "Player Page", apiLang, "")
                    , 0, "Some of schedule rail Content not Visible API for this " + randomContentURL + " please check manually");
            break;
        }
    }




    @Then("^Verify schedule rail live content card red indicator  MobileNumber (.+) - API Lang (.+)$")
    public void verify_schedule_rail_live_content_indicator(String number, String apiLang) throws Exception {
        String mobileNumber = (String) liveTvPage.getGDValue(number);
        Set<String> railNameFromAPI = new HashSet<>();
        railNameFromAPI.addAll(liveTvPage.getRailsNameAndPackIDFromLiveTVLayoutAPI(mobileNumber, commonPage.getPageID(CommonPage.PageID.LiveTV), apiLang.trim()));
        for (String railWithPackId : railNameFromAPI) {
            System.out.println(railWithPackId);
            String railPackageID = StringUtils.substringBetween(railWithPackId.trim(), "|,", "||").trim();
            Response packagesContent = api.hitPackagesContentAPI(mobileNumber, railPackageID, null);
            String randomContentURL = APICommon.getRandomContentURL(packagesContent);
         //   sideBarPage.openURL(randomContentURL);
           // liveTvPage.launchUrl("https://www.airtelxstream.in/LIVETVCHANNEL/9XM/MWTV_LIVETVCHANNEL_552", false);
            commonPage.openURL("/LIVETVCHANNEL/9XM/MWTV_LIVETVCHANNEL_552");
            String title =playerPage.getCurrentContentTitleFromIndex().replace("'S","'s");

            System.out.println( playerPage.getLiveChannelCardRedIndicator(title.trim()).isDisplayed());
            System.out.println( playerPage.getLiveChannelCardRedBorderLine(title.trim()).isDisplayed());

            Assert.assertTrue( playerPage.getLiveChannelCardRedIndicator(title.trim()).isDisplayed(),"live content card red live Logo not visible");
            Assert.assertTrue( playerPage.getLiveChannelCardRedBorderLine(title.trim()).isDisplayed(),"live content card red line border indicator not visible");
            break;
        }
    }

}
