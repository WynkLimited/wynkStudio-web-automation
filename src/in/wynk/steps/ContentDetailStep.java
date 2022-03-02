package in.wynk.steps;

import cucumber.api.java.en.Then;
import in.wynk.framework.Assert;
import in.wynk.pages.ContentDetailPage;
import io.restassured.response.Response;

public class ContentDetailStep {
    in.wynk.framework.Assert Assert ;
    ContentDetailPage contentDetailPage;


    public ContentDetailStep(Assert Assert, ContentDetailPage contentDetailPage) {
        this.Assert = Assert;
        this.contentDetailPage = contentDetailPage;
    }

    @Then("Add content to watchlist")
    public void add_content_to_watchlist() {
        contentDetailPage.addContentToWatchlist();
    }

    @Then("verify content is added in watchlist")
    public void verify_content_is_added_in_watchlist() {
        contentDetailPage.verifyIfContentAddedToWatchList();
    }

//    @Then("verify Verify the content details from API AND UI for (.+)(.+)")
//    public void verify_Verify_the_content_details_from_API_AND_UI_for_RAJTV_MOVIE__a_bcc_c_d_c(String contentid ,String phoneNo) throws Exception {
//       contentDetailPage.getContentDetailFromApi(contentid , phoneNo);
//    }


    @Then("Verify the content details from API AND UI for content (.+) , mobileNumber (.+)")
    public void verify_the_content_details_from_API_AND_UI_for_content_RAJTV_MOVIE__a_bcc_c_d_c_mobileNumber(String contentid ,String phoneNo) throws Exception {
     Response contentDetailFromApi =   contentDetailPage.getContentDetailFromApi(contentid , phoneNo);
       contentDetailPage.VerifyContentDetailFromUIandApi(contentDetailFromApi);
    }
    @Then("Verify the content details page")
    public void verify_the_content_details_page() {
     contentDetailPage.VerifyContentDetailPage();
    }

    @Then("Verify that the user can see tagged genere to the content for (.+) and (.+)")
    public void verify_that_the_user_can_see_tagged_genere_to_the_content_for_mobileNumber_and_content(String mobileNumber , String contentid) throws Exception {
        Response contentDetailFromApi =   contentDetailPage.getContentDetailFromApi(contentid , mobileNumber);
        contentDetailPage.VerifyGenereFromUIandApi(contentDetailFromApi);
    }

    @Then("Verify that the user can see tagged Language to the content for (.+) and (.+)")
    public void verify_that_the_user_can_see_tagged_Language_to_the_content_for_mobileNumber_and_content(String mobileNumber , String contentid) throws Exception {
        Response contentDetailFromApi =   contentDetailPage.getContentDetailFromApi(contentid , mobileNumber);
        contentDetailPage.VerifyLanguageFromUIandApi(contentDetailFromApi);
    }

    @Then("Verify that the user can see credits of the content for (.+) and (.+)")
    public void verify_that_the_user_can_see_credits_of_the_content_for_mobileNumber_and_content(String mobileNumber , String contentid) throws Exception {
        Response contentDetailFromApi =   contentDetailPage.getContentDetailFromApi(contentid , mobileNumber);
        Assert.assertTrue( contentDetailPage.VerifyCreditsFromUIandApi(contentDetailFromApi) , "all artist are not present");
    }

    @Then("Verify that description on content detail page  for (.+) and (.+)")
    public void verify_that_description_on_content_detail_page_for_mobileNumber_and_content(String mobileNumber , String contentid) throws Exception {
        Response contentDetailFromApi =   contentDetailPage.getContentDetailFromApi(contentid , mobileNumber);
        contentDetailPage.VerifyDescriptionFromUIandApi(contentDetailFromApi);

    }
    @Then("Verify  Watchlist icon is present for registered user")
    public void verify_Watchlist_icon_is_present_for_registered_user() {
       contentDetailPage.verifyWatchlistIconIsPesent();
    }


}
