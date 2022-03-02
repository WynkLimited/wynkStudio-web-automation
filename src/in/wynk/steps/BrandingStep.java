package in.wynk.steps;

import cucumber.api.java.en.Then;
import in.wynk.pages.BrandingPage;
import in.wynk.pages.HomePage;
import junit.framework.Assert;

public class BrandingStep {
    BrandingPage brandingPage;
    HomePage homePage;

    public BrandingStep(BrandingPage brandingPage, HomePage homePage) {
        this.brandingPage = brandingPage;
        this.homePage = homePage;
    }
    @Then("Verify version number (.+)")
    public void verify_version_number(String versionNumber) {
       String buildVersionNumber  = (String) homePage.getGDValue(versionNumber);
        Assert.assertTrue( "Version is not same" ,brandingPage.verifyBuildVersion().trim().equals(buildVersionNumber) );
    }
}
