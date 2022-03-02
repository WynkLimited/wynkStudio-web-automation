package in.wynk.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import in.wynk.framework.Assert;
import in.wynk.framework.DriverFactory;
import in.wynk.pages.CommonPage;
import in.wynk.common.Utils;

import java.util.concurrent.TimeUnit;


public class CommonStep {

    Assert Assert;
    CommonPage commonPage;
    Utils utils;

    public CommonStep(Utils utils, Assert Assert, CommonPage commonPage) {
        this.utils = utils;
        this.Assert = Assert;
        this.commonPage = commonPage;
    }

    @Then("^reload current page$")
    public void clickOnVolumeButton() {
        commonPage.reload_page();
    }

    @Then("^wait for (.+) milli second$")
    public void clickOnVolumeButton(String millisecond) {
        commonPage.sleep(Integer.parseInt(millisecond));
    }

    @When("^Navigate To Search Page$")
    public void NavigateToSearchPage() {
        commonPage.navigateToPage(CommonPage.navigationOption.SEARCH);
    }

//    @When("^Navigate To Home Page$")
//    public void NavigateToHomePage()  {
//        sideBarPage.navigateToPage(SideBarPage.sideBarOption.HOME);
//
//    }

    @When("^Navigate To LiveTV Page$")
    public void NavigateToLiveTVPage() {
        commonPage.navigateToPage(CommonPage.navigationOption.LIVETV);
    }

    @When("^Navigate To TV Shows Page$")
    public void NavigateToTVShowsPage() {
        commonPage.navigateToPage(CommonPage.navigationOption.TVSHOWS);
    }

    @When("^Navigate To Movies Page$")
    public void NavigateToMoviesPage() {
        commonPage.navigateToPage(CommonPage.navigationOption.MOVIES);
    }

    @When("^Navigate To Language Page$")
    public void NavigateToLanguagePage() {
        commonPage.navigateToPage(CommonPage.navigationOption.LANGUAGE);
    }

    @And("Disable wifi connection")
    public void disableWifiConnection() throws Exception {
            Runtime.getRuntime().exec("sudo ifconfig en0 down");
//         String deviceID = DriverFactory.getTestDetails().get("udid").trim();
//        Assert.assertTrue(utils.disableWifi(deviceID), "Verify wifi is off");
    }

    @Then("Enable wifi connection")
    public void enableWifiConnection() throws Exception {
        Runtime.getRuntime().exec("sudo ifconfig en0 up");

        String deviceID = DriverFactory.getTestDetails().get("udid").trim();
        utils.enableWifi(deviceID);
    }
}
