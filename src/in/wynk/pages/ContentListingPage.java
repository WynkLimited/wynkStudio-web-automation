package in.wynk.pages;

import in.wynk.API.API;
import in.wynk.API.APICommon;
import in.wynk.common.DriverActionUtils;
import in.wynk.framework.Driver;
import in.wynk.framework.Reporting;
import io.restassured.response.Response;
import org.openqa.selenium.By;

import java.util.*;

public class ContentListingPage extends DriverActionUtils {
    CommonPage commonPage;
    in.wynk.framework.SoftAssert softAssert;
    API api;
    APICommon apiCommon;

    // private By railsName = By.xpath(".//div[@class='title title-with-cta d-flex justify-content-between align-items-center']//a//h2");
    private By railsName = By.xpath("//div[contains(@class , 'carousel-left-margin carousel-right-margin')]");
    private By railsName2 = By.xpath(".//div[@class='title title-with-cta d-flex justify-content-between align-items-center']//a//h2[@class='mb-0']");
    private By moviesRails = By.xpath(".//div[@class=\"carousel-left-margin carousel-right-margin component-top-padding potrait-rails\"]//div[@class='title title-with-cta d-flex justify-content-between align-items-center']//a//h2");
    private By showsRails = By.xpath(".//div[@class=\"carousel-left-margin carousel-right-margin component-top-padding\"]//div[@class='title title-with-cta d-flex justify-content-between align-items-center']//a//h2");
    private By channelsRails = By.xpath(".//div[@class=\"channelRails container-fluid component-top-padding\"]//div[@class='title title-with-cta d-flex justify-content-between align-items-center']//a//h2");
    private By youMayLikeContent =  By.xpath(".//h1[text()=\"You may also like\"]/../..//div//a");

    public ContentListingPage(Reporting Reporter, CommonPage commonPage, in.wynk.framework.Assert Assert, in.wynk.framework.SoftAssert SoftAssert, ThreadLocal<Driver.HashMapNew> sTestDetails) {
        super(Reporter, Assert, SoftAssert, sTestDetails);
        this.softAssert = SoftAssert;
        this.commonPage = commonPage;
        api = new API(Reporter, Assert, SoftAssert, sTestDetails);
    }

    public Set<String> getRailsNameList(int totalPageScrollTime, int noOfTimeClickDown) {
        Set<String> railSet = new HashSet<>();
        railSet.addAll(commonPage.getAllElementFromPageWithKeyDown(totalPageScrollTime, railsName, noOfTimeClickDown, "text"));
        railSet.addAll(commonPage.getElementFromPage(railsName2, "text"));
        railSet.addAll(commonPage.getElementFromPage(moviesRails, "text"));
        railSet.addAll(commonPage.getElementFromPage(showsRails, "text"));
        railSet.addAll(commonPage.getElementFromPage(channelsRails, "text"));
        return railSet;
    }

    Response userProfileResponse = null;
    Map<String, String> userConfigParams = null;

    public List<String> getRailsNameFromLayoutAPI(String mobileNumber, String PageId, String lang) throws Exception {
        if (userProfileResponse == null) {
            userProfileResponse = api.hitUserProfileConfigAPI(mobileNumber);
            userConfigParams = APICommon.getUserContentProperties(userProfileResponse);
        }
        Response layoutResponse = api.hitPageLayoutAPI(mobileNumber, userConfigParams, PageId, lang);
        return APICommon.getRailsNameFromLayoutAPI(mobileNumber, layoutResponse);
    }

    public String getRandomContentURLFromAPI(String mobileNumber, String railWithPackId) throws Exception {
        Response packagesContent = api.hitPackagesContentAPI(mobileNumber, railWithPackId, null);
        if (packagesContent.statusCode() == 404) {
            return packagesContent.statusCode() + "   " + packagesContent.getBody().asString();
        }
        String randomContentURL = APICommon.getRandomContentURL(packagesContent);
        return randomContentURL;
    }

    public List<String> getRecommendedContentFromRelatedAPI(String mobileNumber, String playableId, String contentKey) {
        Response response = api.hitRelatedContentAPI(mobileNumber, playableId, null);
        return APICommon.getContentFromRelatedResponse(response, contentKey);
    }

    public Set<String> getRecommendedContentFromUI() {
        return commonPage.getAllElementFromPageWithKeyDown(30000,youMayLikeContent, 8, "title");
    }


}