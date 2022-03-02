package in.wynk.steps;

import cucumber.api.java.en.Then;
import in.wynk.pages.FooterPage;
import in.wynk.pages.HomePage;
import junit.framework.Assert;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FooterStep {
    FooterPage footerPage;
    HomePage homePage;

    public FooterStep(FooterPage footerPage, HomePage homePage) {
        this.footerPage = footerPage;
        this.homePage = homePage;
    }

    @Then("Verify topshows  list (.+)")
    public void verify_topshows_list(String list) {
        List<String> showlist = homePage.getListFromUI("Top Show");
        Assert.assertTrue("all shows  not present", showlist.containsAll(Arrays.asList(list.toLowerCase().split("\\s*,\\s*")).stream().collect(Collectors.toSet())));

    }
    @Then("Verify trending Movies list (.+)")
    public void verify_trending_Movies_list(String list) {
        List<String> showlist = homePage.getListFromUI("Trending Movies");
        Assert.assertTrue("all shows  not present", showlist.containsAll(Arrays.asList(list.toLowerCase().split("\\s*,\\s*")).stream().collect(Collectors.toSet())));
    }

    @Then("Verify just Arrived list (.+)")
    public void verify_just_Arrived_list(String list){
        List<String> showlist = homePage.getListFromUI("Just Arrived");
        Assert.assertTrue("all shows  not present", showlist.containsAll(Arrays.asList(list.toLowerCase().split("\\s*,\\s*")).stream().collect(Collectors.toSet())));
    }

    @Then("Verify verify footer list type (.+)")
    public void verify_verify_footer_list_type(String type) {
        List<String> footerTypeList = homePage.getFooterListTypeFromUI();
        Assert.assertTrue("all shows  not present", footerTypeList.containsAll(Arrays.asList(type.toLowerCase().split("\\s*,\\s*")).stream().collect(Collectors.toSet())));
    }

    @Then("verify that no links are broken")
    public void verify_that_no_links_are_broken() throws IOException {
        footerPage.verifyNoBrokenLinks();
    }
}
