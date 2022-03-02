package in.wynk.pages;

import in.wynk.common.DriverActionUtils;

import in.wynk.framework.Driver;
import in.wynk.framework.Reporting;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ContinueWatchingPage extends DriverActionUtils {

    public ContinueWatchingPage(Reporting Reporter, in.wynk.framework.Assert Assert, in.wynk.framework.SoftAssert SoftAssert) {
        super(Reporter, Assert, SoftAssert);
        
    }


    public void verifyContentIsAddedToList(List<String> continueWatchingList, String contentName) {
        Assert.assertTrue(continueWatchingList.contains(contentName), "content not added to continue watching list");

    }

    public void verifyContentIsNotAddedToList(List<String> continueWatchingList, String contentName) {
        Assert.assertTrue(!continueWatchingList.contains(contentName), "content  added to continue watching list");

    }

}
