package in.wynk.pages;

import in.wynk.API.API;
import in.wynk.API.APICommon;
import in.wynk.common.DriverActionUtils;
import in.wynk.framework.Driver;
import in.wynk.framework.Reporting;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.*;

public class LiveTvPage extends DriverActionUtils {


    API api;
    in.wynk.framework.SoftAssert softAssert;
    CommonPage commonPage;

    private By morePageContent = By.xpath(".//h6[@class='card-title']");

    private By railsName = By.xpath(".//h2[@class='mb-0']");


    private By ChannelNames = By.xpath(".//div[@class='cards cards-channel-landscape cards-channel-landscape--grid']//a");

    private By sortingButton = By.xpath(".//div[@class='dropdown']//button//span");

    private By sortingOptions = By.xpath(".//div[@class = 'dropdown-menu dropdown-custom dropdown-menu-right show']//button");

    private By liveTVRailsName = By.xpath(".//div[@class='title title-with-cta d-flex justify-content-between align-items-center']//h2");

    // channel player
    private By channelLogo = By.xpath(".//div[@class='w-100 d-inline-block']");
    private By channelName = By.xpath(".//h2[@class='current-vdo__heading mt-3']");
    private By channelProgram = By.xpath(".//div[@class='current-vdo__title mt-3']//h1");
    private By channelProgramTimeLang = By.xpath(".//ul[@class='current-vdo__details year-language mt-1']//li");
    private By channelGenre = By.xpath(".//div[@class='mid-section']//span");


    public boolean verifyChannelLogoIsVisible() {
        return getDriver().findElement(channelLogo).isDisplayed();
    }

    public String getChannelName() {
        return getText(channelName);
    }

    public String getChannelProgramName() {
        return getText(channelProgram);
    }

    public String getChannelProgramTimeAndLang() {
        return getText(channelProgramTimeLang);
    }

    public String getChannelProgramGenre() {
        return getText(channelGenre);
    }

    public LiveTvPage(Reporting Reporter, CommonPage commonPage, in.wynk.framework.Assert Assert, in.wynk.framework.SoftAssert SoftAssert, ThreadLocal<Driver.HashMapNew> sTestDetails) {
        super(Reporter, Assert, SoftAssert, sTestDetails);
        api = new API(Reporter, Assert, SoftAssert, sTestDetails);
        this.softAssert = SoftAssert;
        this.commonPage = commonPage;

    }

    public Set<String> getAllChannelsNameFromGenresPage(int maxFindingTime) {
      return   commonPage.getAllElementFromPageWithKeyDown(maxFindingTime,ChannelNames,2,"title");
    }

    public void clickOnRail(String railName) {
        click(By.xpath(".//h2[(text()='" + railName + "')]"), railName);
    }

    public void clickOnSortingButton() {
        click(sortingButton, "SortingButton");
    }

    public void clickOnRailPrevButton(String railName) {
        click(By.xpath("//h2[text()='" + railName + "']//parent::a/following-sibling::div//i[@class='icon20 icon-carousel-prev-arrow']"), "Rail Previous Button");
    }

    public void clickOnRailNextButton(String railName) {
        click(By.xpath("//h2[contains(text(),'" + railName + "')]//parent::a/following-sibling::div//i[@class='icon20 icon-carousel-arrow icon-carousel-next-arrow']"), "Rail next Button");
    }

    public void clickOnRailMoreButton(String railName) {
        click(By.xpath("//h2[contains(text(),'" + railName + "')]//parent::a/following-sibling::div//a[text()='More']"), "Rail next Button", 2);
    }

    public String getSortingOptions() {
        String sortingTypes = "";
        List<WebElement> webElements = getDriver().findElements(sortingOptions);
        for (WebElement webElement : webElements) {
            sortingTypes = sortingTypes + "," + webElement.getText().trim();
        }
        return sortingTypes;
    }

    public boolean verifyNextBtnFunctionality(String railName) {
        List<String> channels = getRailChannelsName(railName);
        clickOnRailNextButton(railName);
        clickOnRailNextButton(railName);
        sleep(500);
        List<String> channels1 = getRailChannelsName(railName);
        System.out.println(channels);
        System.out.println(channels1);
        return channels.size()!=channels1.size();
    }

    public boolean verifyPreviousBtnFunctionality(String railName) {
        List<String> channels = getRailChannelsName(railName);
        String railLastChannelName = channels.get(channels.size() - 1);
        clickOnRailPrevButton(railName);
        clickOnRailPrevButton(railName);
        List<String> channels1 = getRailChannelsName(railName);
        System.out.println(channels);
        System.out.println(channels1);
        return !railLastChannelName.equals(channels1.get(channels1.size() - 1));
    }

    public boolean verifyPrevButtonOnRail(String railName) {
        return getDriver().findElement(By.xpath("//h2[text()='" + railName + "']//parent::a/following-sibling::div//i[@class='icon20 icon-carousel-prev-arrow']")).isDisplayed();
    }

    public boolean verifyNextButtonOnRail(String railName) {
        return getDriver().findElement(By.xpath("//h2[text()='" + railName + "']//parent::a/following-sibling::div//i[@class='icon20 icon-carousel-arrow icon-carousel-next-arrow']")).isDisplayed();
    }

    public boolean verifyRailMoreButtonisVisible(String railName) {
        return getDriver().findElement(By.xpath("//h2[text()='" + railName + "']//parent::a/following-sibling::div//a[text()='More']")).isDisplayed();
    }

    public void verifyContentCardPlayButton(String railName, int cardNumber) {
        getDriver().findElements(By.xpath("//h2[text()='Live News']//parent::a/parent::div/parent::div//div[@class='card-center-play-icon']")).get(cardNumber).isDisplayed();
    }

    public void clickContentCardPlayButton(String railName, int cardNumber) {
        getDriver().findElements(By.xpath("//h2[text()='Live News']//parent::a/parent::div/parent::div//div[@class='card-center-play-icon']")).get(cardNumber).isDisplayed();
    }

    public List<String> getRailChannelsName(String railName) {
        List<String> railContent = new ArrayList<>();
        List<WebElement> webElement = getDriver().findElements(By.xpath("//h2[text()='" + railName + "']//parent::a/parent::div/parent::div//h6[@class='card-title channel-text']"));
        int contentSize = webElement.size();
        for (int i = 0; i <= contentSize - 1; i++) {
            railContent.add(webElement.get(i).getText());
        }
        return railContent;
    }

    public void getMorePageContentList() {
        List<String> contentList = new ArrayList<String>();
        int a = getDriver().findElements(morePageContent).size();
        for (int i = 0; i <= a - 1; i++) {
            contentList.add(getDriver().findElements(morePageContent).get(i).getText());
        }
    }

    public Set<String> getRailsNames(int scrollingTime) {
        Set<String> rail_Set = new HashSet<String>();
        int sameCount = 0;
        long current_Time = System.currentTimeMillis() + scrollingTime;

        List<WebElement> webElements = getDriver().findElements(railsName);
        int j = webElements.size();

        while (current_Time > System.currentTimeMillis()) {
            for (int i = 0; i <= webElements.size() - 1; i++) {
                sleep(200);
                rail_Set.add(webElements.get(i).getText());
            }

            System.out.println(rail_Set);
            scrollingByCoordinatesofAPage(0, 200);
            sleep(200);
            webElements = getDriver().findElements(railsName);


//            if(lastelement.equals(getDriver().findElements(railsName).get(j-1).getText())) {
//                sameCount++;
//                if(sameCount == 20)
//                {
//                    break;
//                }
//            }else {
//                lastelement  = getDriver().findElements(railsName).get(j-1).getText().trim();
//            }

        }
        return rail_Set;
    }


//    public Set<String> getAllRailsNameFromLiveTvPage(int totalPageScrollTime) {
//        TreeSet<String> rail_Set = new TreeSet<>();
//        return commonPage.getAllElementFromPageWithKeyDown(totalPageScrollTime, railsName);
//    }


    public int totalNumberofRails = 0;

    public int verifyAllRailsfromAPI(String mobileNumber) {
        int count = 0;
        // Hit User Profile to get User related properties
        Response userProfileResponse = api.hitUserProfileConfigAPI(mobileNumber);
        Map<String, String> userContentPropertiesParams = APICommon.getUserContentProperties(userProfileResponse);

        // Hit App Config To get Page Id of Various Pages
        Response channelsContentAPIResponse = api.hitChannelsContentAPI(mobileNumber, userContentPropertiesParams);
        List<String> all_genersList = APICommon.getAllGenres(channelsContentAPIResponse);
        Collections.sort(all_genersList);

        TreeSet<String> rail_Set = new TreeSet<>();
        rail_Set.addAll(getRailsNames(60000));
        totalNumberofRails = rail_Set.size() - 1;
        int size = all_genersList.size() - 1;


        for (int i = 0; i <= size; i++) {

            // Entertainmen
            String findValue = all_genersList.get(i);
            findValue = findValue.substring(0, findValue.length() - 1);

            Iterator value = rail_Set.iterator();
            while (value.hasNext()) {
                if (value.next().toString().trim().contains(findValue)) {
                    count++;
                }
            }
        }
        System.out.println(rail_Set);
        System.out.println(count);
        System.out.println(count);
        return count;
    }


    public int scrollToEveryRail(List<String> all_GenersList) throws InterruptedException {

        int size = all_GenersList.size() - 1;
        for (int i = 0; i <= size; i++) {
            long current_Time = System.currentTimeMillis() + 60000;

            scrollingToTopofAPage();
            while (current_Time > System.currentTimeMillis()) {
                System.out.println(current_Time + "     " + System.currentTimeMillis());
                String railName = all_GenersList.get(i).trim();
                railName = railName.substring(0, railName.length() - 1);
                sleep(200);
                if (getDriver().findElements(By.xpath(".//h2[contains(text(),'" + railName + "')]")).size() == 1) {
                    System.out.println(railName + " is display on pages");
                    break;
                } else {
                    scrollingByCoordinatesofAPage(0, 200);
                    System.out.println(railName + " not display on pages  scroll down");

                }
            }
        }
        return 0;
    }

    public void scrollToLiveTvRail(String railName, int railFindingTime) throws InterruptedException {
        commonPage.scrollToRail(railName,railFindingTime);
    }


    // Api work only for Live TV

    public List<String> getAllChannelsFromAPI(String mobileNumber, String generName) {
        Response userProfileResponse = api.hitUserProfileConfigAPI(mobileNumber);
        Map<String, String> userContentPropertiesParams = APICommon.getUserContentProperties(userProfileResponse);
        // Hit App Config To get Page Id of Various Pages
        Response channelsContentAPIResponse = api.hitChannelsContentAPI(mobileNumber, userContentPropertiesParams);
        return APICommon.getAllChannelNameByGenres(channelsContentAPIResponse, generName);

    }

    /**
     * Get All Data From Content API
     *
     * @param genresName
     * @return
     */
    public List<String> getAllChannelsTitleAndIDFromAPIByGenres(Response channelsContentAPIResponse, String genresName) {
        return APICommon.getAllChannelTitleAndIdByGenres(channelsContentAPIResponse, genresName);
    }

    public String getChannelsProgramInfo(Response cPPAPIResponse, String packageIds) {
        return APICommon.getChannelInformation(cPPAPIResponse, packageIds);
    }

    public Map<String, String> getUserContentPropertiesParams(String mobileNumber) {
        return APICommon.getUserContentProperties(api.hitUserProfileConfigAPI(mobileNumber));
    }

    public Response getChannelContentAPIResponse(String mobileNumber, Map<String, String> userContentPropertiesParams) {
        return api.hitChannelsContentAPI(mobileNumber, userContentPropertiesParams);
    }

    public Response getEPGContentAPIResponse(String mobileNumber, Map<String, String> userContentPropertiesParams) {
        return api.hitEPGContentAPI(mobileNumber, userContentPropertiesParams);
    }


    public List<String> getAllRailsNameFromAPI(String mobileNumber) {
        Response userProfileResponse = api.hitUserProfileConfigAPI(mobileNumber);
        Map<String, String> userContentPropertiesParams = APICommon.getUserContentProperties(userProfileResponse);
        // Hit App Config To get Page Id of Various Pages
        Response channelsContentAPIResponse = api.hitChannelsContentAPI(mobileNumber, userContentPropertiesParams);
        return APICommon.getAllGenres(channelsContentAPIResponse);
    }


    public Set<String> getLiveTVRailsNameListFromUI(int totalPageScrollTime, int noOfTimeClickDown) {
     return    commonPage.getAllElementFromPageWithKeyDown(totalPageScrollTime,liveTVRailsName,noOfTimeClickDown,"text");
    }


    public List<String> getRailsNameAndPackIDFromLiveTVLayoutAPI(String mobileNumber, String PageId, String lang) {
        return commonPage.getName_PackID_ID_ValuesFromLayoutAPI(mobileNumber, PageId, lang);

    }


}



