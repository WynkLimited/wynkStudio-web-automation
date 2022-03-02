package in.wynk.pages;

import in.wynk.API.API;
import in.wynk.API.APICommon;
import in.wynk.common.DriverActionUtils;
import in.wynk.framework.Driver;
import in.wynk.framework.Reporting;
import in.wynk.framework.SoftAssert;
import org.openqa.selenium.By;

import java.util.List;

public class MoviesPage extends DriverActionUtils {

    CommonPage commonPage;
    SoftAssert softAssert;
    API api;
    APICommon apiCommon;
    private By homePagRailsName = By.xpath(".//div[@class='title title-with-cta d-flex justify-content-between align-items-center']//h2");



    public MoviesPage(Reporting Reporter, CommonPage commonPage, in.wynk.framework.Assert Assert, in.wynk.framework.SoftAssert SoftAssert, ThreadLocal<Driver.HashMapNew> sTestDetails) {
        super(Reporter, Assert, SoftAssert, sTestDetails);
        this.softAssert = SoftAssert;
        this.commonPage = commonPage;
        api = new API(Reporter, Assert, SoftAssert, sTestDetails);

    }

    public List<String> getBannerContentFromAPIForMovies(String mobileNumber, String PageId, String contentKey,String lang) throws Exception {
        return commonPage.getBannerContentFromAPI(mobileNumber, PageId, contentKey,lang);
    }
}
