package in.wynk.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import in.wynk.pages.CommonPage;
import in.wynk.pages.SharePage;

public class ShareStep {
    SharePage sharePage;
    CommonPage commonPage;

    public ShareStep(SharePage sharePage, CommonPage commonPage) {
        this.sharePage = sharePage;
        this.commonPage = commonPage;
    }

    @Then("Verify share icon on every content detail page")
    public void verify_share_icon_on_every_content_detail_page() {
       // commonPage.openURL(path.trim());
        sharePage.VerifyShareButton();
    }

    @When("click on share icon")
    public void click_on_share_icon() {
     sharePage.clickOnShareIcon();
    }

    @Then("Verify share popup contains(.+)")
    public void verify_share_popup_contains(String shareOptions) {
      sharePage.verifySharePopup(shareOptions);
    }
}
