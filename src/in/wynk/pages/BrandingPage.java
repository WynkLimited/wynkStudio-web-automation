package in.wynk.pages;

import in.wynk.API.API;
import in.wynk.common.DriverActionUtils;
import in.wynk.framework.Reporting;

public class BrandingPage extends DriverActionUtils {
    API api;
    HomePage homePage;

    public BrandingPage(Reporting Reporter, in.wynk.framework.Assert Assert, in.wynk.framework.SoftAssert SoftAssert, HomePage homePage) {
        super(Reporter, Assert, SoftAssert);
        api = new API(Reporter, Assert, SoftAssert, sTestDetails);
        this.homePage = homePage;
    }

    public String verifyBuildVersion() {
     String[] s1 =  homePage.getBuildVersionNumber().split("TV");
     return s1[1];
    }
}
