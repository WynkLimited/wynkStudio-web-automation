package in.wynk.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import in.wynk.framework.Assert;
import in.wynk.pages.CommonPage;
import in.wynk.pages.HomePage;
import in.wynk.pages.PlayerPage;
import org.apache.commons.lang.StringUtils;

import java.util.Locale;

public class BreadcrumbsStep {

    in.wynk.framework.Assert Assert;
    CommonPage commonPage;
    PlayerPage playerPage;
    HomePage homePage;

    public BreadcrumbsStep(HomePage homePage , Assert Assert, CommonPage commonPage, PlayerPage playerPage) {
        this.Assert = Assert;
        this.homePage = homePage;
        this.commonPage = commonPage;
        this.playerPage = playerPage;
    }

    @Then("^Verify BreadCrumb for(.+)$")
    public void verify_BreadCrumbs_for(String breadCrumb) throws Exception {
       // commonPage.scrollToEnd();
        commonPage.swipeTillDown(20);
        System.out.println(commonPage.getBreadCrumbs().trim().toLowerCase());
        System.out.println(breadCrumb.toLowerCase().trim());
        Assert.assertTrue(commonPage.getBreadCrumbs().toLowerCase().trim().equals(breadCrumb.toLowerCase().trim()), breadCrumb + " this bread crumbs not match with this " + commonPage.getDriver().getCurrentUrl() + " BreadCrumbs");

    }

    @Then("^Verify (.+) BreadCrumb for Shows Content (.+)$")
    public void verify_BreadCrumb_For_Shows_Content(String breadCrumb, String path) throws Exception {
        commonPage.openURL(path.trim());
        commonPage.swipeTillDown(20);
        String contentName = playerPage.getCurrentContentTitleFromIndex();
        String breadcrumbString = "Home";
        String indexing = "";

        switch (breadCrumb) {
            case "regular shows":
                indexing = contentName;
                break;
            case "season page": {
                String name = StringUtils.substringBefore(contentName, "- Season");
                String season = StringUtils.substringAfter(contentName, name + "-");
                indexing = name.trim() + " > " + season.trim();
            }
            break;
            case "episode page":
                String name = StringUtils.substringBefore(contentName, "- Season");
                String season = StringUtils.substringBetween(contentName, name + "-", "Episode");
                String episode = StringUtils.substringAfterLast(contentName, season);
                indexing = name.trim() + " > " + season.trim() + " > " + episode.trim() + " - " + playerPage.getCurrentContentEpisodeNameFromIndex();
                break;
        }

        breadcrumbString = breadcrumbString + " > TV Shows > " + playerPage.getCurrentContentLanguageFromIndex().trim() + " TV Shows > " + indexing;
        System.out.println("* " + commonPage.getBreadCrumbs());
        System.out.println("- " + breadcrumbString);
        Assert.assertTrue(breadcrumbString.toLowerCase().contains(commonPage.getBreadCrumbs().toLowerCase()));

    }


    @Then("^Verify BreadCrumb for Movie detail page (.+)$")
    public void verify_BreadCrumb_Movies_Details_Page(String path) throws Exception {
        commonPage.openURL(path.trim());
        commonPage.swipeTillDown(20);
        String contentName = playerPage.getCurrentContentTitleFromIndex();
        String breadcrumbString = "Home";
        breadcrumbString = breadcrumbString + " > Movies > " + playerPage.getCurrentContentLanguageFromIndex().trim() + " Movies > " + contentName;
        System.out.println("* " + commonPage.getBreadCrumbs());
        System.out.println("- " + breadcrumbString);
        Assert.assertTrue(breadcrumbString.toLowerCase().contains(commonPage.getBreadCrumbs().toLowerCase()));

    }

    @Then("Verify BreadCrumb for livetv detail page (.+)")
    public void verify_BreadCrumb_for_livetv_detail_page(String path) throws Exception {
        commonPage.openURL(path.trim());
        commonPage.swipeTillDown(20);
        String channelType = playerPage.getLivetvGenreType();
        String channelName = playerPage.getLiveTVChannelName();
        String breadcrumbString = "Home";
        breadcrumbString = breadcrumbString + " > Live TV > " + channelType.trim() + " Channels > " + channelName;
        Assert.assertTrue(breadcrumbString.toLowerCase().contains(commonPage.getBreadCrumbs().toLowerCase()));

    }
    @Then("Verify BreadCrumb should be clickable for Movies content detail page (.+)")
    public void verify_BreadCrumb_should_be_clickable(String path) throws Exception {
        commonPage.openURL(path.trim());
        commonPage.swipeTillDown(20);
        playerPage.clickOnHomeBreadcrumb();
        Assert.assertTrue(commonPage.getActiveTab().toLowerCase().equals("Home".toLowerCase()));
        commonPage.openURL(path.trim());
        commonPage.swipeTillDown(20);
        playerPage.clickOnContentTypeBreadcrumb();
        Assert.assertTrue(commonPage.getActiveTab().toLowerCase().equals("Movies".toLowerCase()));
        commonPage.openURL(path.trim());
        commonPage.swipeTillDown(20);
        String currentContentLang = playerPage.getCurrentContentLanguageFromIndex().trim();
        playerPage.clickOnlangContentTypeBreadcrumb();
        Assert.assertTrue(commonPage.getActiveTab().toLowerCase().equals("Movies".toLowerCase()));
        Assert.assertTrue(commonPage.getRailTitle().toLowerCase().equals(currentContentLang.toLowerCase()+ " Movies".toLowerCase()));


    }

    @Then("Verify BreadCrumb should be clickable for Livetv content detail page (.+)")
    public void verify_BreadCrumb_should_be_clickable_for_Livetv(String path) throws Exception {
        commonPage.openURL(path.trim());
        commonPage.swipeTillDown(20);
        playerPage.clickOnHomeBreadcrumb();
        Assert.assertTrue(commonPage.getActiveTab().toLowerCase().equals("Home".toLowerCase()));
        commonPage.openURL(path.trim());
        commonPage.swipeTillDown(20);
        playerPage.clickOnContentTypeBreadcrumb();
        Assert.assertTrue(commonPage.getActiveTab().toLowerCase().equals("Live tv".toLowerCase()));
        commonPage.openURL(path.trim());
        commonPage.swipeTillDown(20);
        String channelType = playerPage.getLivetvGenreType();
        playerPage.clickOnlangContentTypeBreadcrumb();
        Assert.assertTrue(commonPage.getActiveTab().toLowerCase().equals("Live tv".toLowerCase()));
        Assert.assertTrue(commonPage.getRailTitle().toLowerCase().equals(channelType.toLowerCase()+ " Channels".toLowerCase()));


    }



    @Then("^Verify BreadCrumb for  Rail List movies Page (.+)$")
    public void verify_BreadCrumb_For_LiveTV_content_details_Page(String path) throws Exception {
        commonPage.openURL(path.trim());
        commonPage.swipeTillDown(20);
        String channelName = playerPage.getLiveTVChannelName();
        String breadcrumbString = "Home";
        breadcrumbString = breadcrumbString + " > Live TV > " + playerPage.getPlayerContentGenre().trim() + " Channels > " + channelName;
        System.out.println("* " + commonPage.getBreadCrumbs());
        System.out.println("- " + breadcrumbString);
        Assert.assertTrue(breadcrumbString.toLowerCase().contains(commonPage.getBreadCrumbs().toLowerCase()));

    }


    @Then("^Verify BreadCrumb for LIVE TV content detail page (.+) path (.+)$")
    public void verify_BreadCrumb_For_Rails_List_Movies_Page(String page, String path) {
        commonPage.openURL(path.trim());
        commonPage.scrollToEnd();
        String channelName = commonPage.getContentListName();
        String breadcrumbString = "Home";

        switch (page) {
            case "Movies_list":
                breadcrumbString = breadcrumbString + " > Movies > " + channelName;
                break;
            case "LiveTV_list":
                breadcrumbString = breadcrumbString + " > Live TV > " + channelName;
                break;
            case "TVShows_list":
                breadcrumbString = breadcrumbString + " > TV Shows > " + channelName;
                break;
        }

        System.out.println("* " + commonPage.getBreadCrumbs());
        System.out.println("- " + breadcrumbString);
        Assert.assertTrue(breadcrumbString.toLowerCase().contains(commonPage.getBreadCrumbs().toLowerCase()));

    }


    @Then("^Verify BreadCrumb for Rail List Page from Home (.+)$")
    public void verify_BreadCrumb_RailList_Page_From_Home(String path) {
        commonPage.openURL(path.trim());
        commonPage.scrollToEnd();
        String channelName = commonPage.getContentListName();
        String breadcrumbString = "Home";
        breadcrumbString = breadcrumbString + " > " + channelName;
        System.out.println("* " + commonPage.getBreadCrumbs());
        System.out.println("- " + breadcrumbString);
        Assert.assertTrue(breadcrumbString.toLowerCase().contains(commonPage.getBreadCrumbs().toLowerCase()));

    }



    @Then("^Verify BreadCrumb for Catch up(.+)$")
    public void verify_BreadCrumb_for_catch_up(String path) {
        commonPage.openURL(path.trim());
        commonPage.scrollToEnd();

        String breadcrumbString = "Home";

        breadcrumbString = breadcrumbString +" > Live TV > " +playerPage.getPlayerContentGenre().trim() + " Channels > " +playerPage.getLiveTVChannelName()+" > " + playerPage.getCurrentContentTitleFromIndex().trim();

        System.out.println("* " + commonPage.getBreadCrumbs());
        System.out.println("- " + breadcrumbString);
        Assert.assertTrue(breadcrumbString.toLowerCase().contains(commonPage.getBreadCrumbs().toLowerCase()));

    }

    @When("^Navigate To More Page$")
    public void NavigateToMorePage() throws InterruptedException {
        commonPage.navigateToPage(CommonPage.navigationOption.MORE);    }

    @Then("Verify respected tabs should be selected when played any content(.+)$")
    public void verify_respected_tabs_should_be_selected_when_played_any_content(String path) {
        commonPage.openURL(path.trim());
        commonPage.scrollToEnd();
        Assert.assertTrue(playerPage.getContentTypeFromBreadCrumbs().toLowerCase().equals(commonPage.getActiveTab().toLowerCase()));
    }

    @When("^Navigate To Page(.+)$")
    public void NavigateToPage(String PageName) throws InterruptedException {
        switch (PageName) {
            case "HOME PAGE":
                commonPage.navigateToPage(CommonPage.navigationOption.HOME);
                break;
            case "TVSHOWS PAGE":
                commonPage.navigateToPage(CommonPage.navigationOption.TVSHOWS);
                break;
            case "MOVIES PAGE":
                commonPage.navigateToPage(CommonPage.navigationOption.MOVIES);
                break;
            case "LIVETV PAGE":
                commonPage.getDriver().navigate().refresh();
                commonPage.navigateToPage(CommonPage.navigationOption.LIVETV);
                break;
            case "NEWS PAGE":
                commonPage.getDriver().navigate().refresh();
                commonPage.navigateToPage(CommonPage.navigationOption.NEWS);
                break;

        }

    }
    @Then("Verify Root of Page for BreadCrumb(.+)")
    public void verify_Root_of_Page_for_BreadCrumb(String path) throws Exception {
        commonPage.openURL(path.trim());
        commonPage.swipeTillDown(20);
        Assert.assertTrue(commonPage.isBreadcrumbsVisible());
    }
}
