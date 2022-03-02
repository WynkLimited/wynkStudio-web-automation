package in.wynk.pages;

import in.wynk.API.API;
import in.wynk.common.DriverActionUtils;
import in.wynk.framework.Reporting;
import org.openqa.selenium.By;

import java.io.IOException;

public class FooterPage  extends DriverActionUtils {
    API api;
    HomePage homePage;

    public FooterPage(Reporting Reporter, in.wynk.framework.Assert Assert, in.wynk.framework.SoftAssert SoftAssert , HomePage homePage) {
        super(Reporter, Assert, SoftAssert);
        api = new API(Reporter, Assert, SoftAssert, sTestDetails);
        this.homePage=homePage;
    }

    public void verifyNoBrokenLinks() throws IOException {
       Assert.assertTrue(!homePage.verifyNoBrokenLinksInFooter() , "there is a Broken Link");
    }
}
