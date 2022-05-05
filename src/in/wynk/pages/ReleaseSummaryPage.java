package in.wynk.pages;

import in.wynk.PageElements.DashboardElements;
import in.wynk.PageElements.ReleaseCreationElements;
import in.wynk.PageElements.ReleaseSummaryElements;
import in.wynk.common.DriverActionUtils;
import in.wynk.framework.Assert;
import in.wynk.framework.Reporting;
import in.wynk.framework.SoftAssert;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ReleaseSummaryPage extends DriverActionUtils {

    ReleaseSummaryElements releaseSummaryElements;
    HashMap<String, String> songSpecificAnaLytic;
    DashboardElements dashboardElements;

    public ReleaseSummaryPage(ReleaseSummaryElements releaseSummaryElements, Reporting Reporter, in.wynk.framework.Assert Assert,
                              in.wynk.framework.SoftAssert SoftAssert, DashboardElements dashboardElements) {
        super(Reporter, Assert, SoftAssert);
        this.releaseSummaryElements = releaseSummaryElements;
        this.dashboardElements = dashboardElements;
    }


    public String getNameOfSong() {

        return getElementWhenPresent(releaseSummaryElements.getNameOfSong()).getText();
    }

    public boolean isLiveTextPresent() {

        return checkIfElementPresent(releaseSummaryElements.getLiveText(), 5);
    }

    public boolean isDraftTextPresent() {
        return checkIfElementPresent(releaseSummaryElements.getDraftText(), 5);
    }

    public boolean isInReviewTextPresent() {
        return checkIfElementPresent(releaseSummaryElements.getInReviewText(), 5);
    }

    public boolean isRejectedTextPresent() {
        return checkIfElementPresent(releaseSummaryElements.getRejectedText(), 5);
    }

    public String getUniqueListens() {
        return getElementWhenPresent(releaseSummaryElements.getUniquesListens()).getText();
    }

    public String getTotalStream() {
        return getElementWhenPresent(releaseSummaryElements.getTotalStreams()).getText();
    }

    public String getHelloTunesActivated() {
        return getElementWhenPresent(releaseSummaryElements.getHelloTunesActivated()).getText();
    }

    public String getLikes() {
        return getElementWhenPresent(releaseSummaryElements.getLikes()).getText();
    }

    public void clickBackArrow() {
        click(releaseSummaryElements.getBackArrow(), "back arrow", 5);
    }

    public boolean isLastUpdatedTextPresentLiveState() {
        return checkIfElementPresent(releaseSummaryElements.getLastUpdatedOn(), 5);
    }

    public boolean isListenOnWynkButtonPresent() {
        return checkIfElementPresent(releaseSummaryElements.getListenOnWynkButton(), 5);
    }


    public boolean checkIfAllElementsOnDraftReleaseSummaryPresent() {
        if (checkIfElementPresent(releaseSummaryElements.getContinueFromWhereYouLeft()) ||
                isDraftTextPresent() || checkIfElementPresent(releaseSummaryElements.getContinueButtonDraftState()) ||
                checkIfElementPresent(releaseSummaryElements.getStatusBarOfIncompleteRelease())) {
            return true;
        }

        return false;

    }

    public boolean checkIfAllElementsOnInReviewReleaseSummaryPresent() {

        if (checkIfElementPresent(releaseSummaryElements.getInReviewStateText()) ||
                checkIfElementPresent(releaseSummaryElements.getNewReleaseInReviewState()) ||
                checkIfElementPresent(releaseSummaryElements.getInReviewText())) {
            return true;
        }

        return false;
    }

    public boolean checkIfAllElementsOnRejectedReleaseSummaryPresent() {

        if (checkIfElementPresent(releaseSummaryElements.getRejectedText()) ||
                checkIfElementPresent(releaseSummaryElements.getSongRejectionText()) ||
                checkIfElementPresent(releaseSummaryElements.getInReviewText()) ||
                checkIfElementPresent(releaseSummaryElements.getNeedHelpButtonRejectionState())) {
            return true;
        }

        return false;
    }

    public String getDateFromFilterSelected() {
        return getText(releaseSummaryElements.getTextOnFilter(), 5);
    }


    public void clickUniqueListensBox() {
        checkIfElementPresent(releaseSummaryElements.getUniquesListens(),3000);
        click(releaseSummaryElements.getUniquesListens(), "unique listenes");

    }

    public String getUniqueListenReleasePage() {
        checkIfElementPresent(releaseSummaryElements.getUniquesListens(),3000);
        return getDriver().findElement(releaseSummaryElements.getUniquesListens()).getText();
    }

    public String getUniqueListenTrendGraph() {
        return getDriver().findElement(releaseSummaryElements.getUniqueListensTrendsGraph()).getText();
    }

    public void clickTotalStreamsBox() {
        checkIfElementPresent(releaseSummaryElements.getTotalStreams(),3000);
        click(releaseSummaryElements.getTotalStreams(), "total streams");
    }

    public String getTotalStreamsReleasePage() {
        checkIfElementPresent(releaseSummaryElements.getTotalStreams(),3000);
        return getDriver().findElement(releaseSummaryElements.getTotalStreams()).getText();
    }

    public String getTotalStreamsTrendGraph() {
        return getDriver().findElement(releaseSummaryElements.getTotalStreamsTrendsGraph()).getText();
    }

    public void clickHelloTunesActivatedBox() {
        checkIfElementPresent(releaseSummaryElements.getHelloTunesActivated(),3000);
        click(releaseSummaryElements.getHelloTunesActivated(), "Hello tunes");
    }

    public String getHTActivatedReleasePage() {
        checkIfElementPresent(releaseSummaryElements.getHelloTunesActivated(),3000);
        return getDriver().findElement(releaseSummaryElements.getHelloTunesActivated()).getText();
    }

    public String getHTActivatedTrendGraph() {
        return getDriver().findElement(releaseSummaryElements.getHtActivatedTrendsGraph()).getText();
    }

    public void clickLikesBox() {
        checkIfElementPresent(releaseSummaryElements.getLikes(),3000);
        click(releaseSummaryElements.getLikes(), "Likes");
    }


    public String getLikesReleasePage() {
        checkIfElementPresent(releaseSummaryElements.getLikes(),3000);
        return getDriver().findElement(releaseSummaryElements.getLikes()).getText();
    }

    public String getLikesTrendGraph() {
        return getDriver().findElement(releaseSummaryElements.getLikeTrendsGraph()).getText();
    }

    public boolean isGraphPresentOnTrendsPage()
    {
       return checkIfElementPresent(releaseSummaryElements.getGraphTrendPage(),5);
    }

    public void clickCrossIcon()
    {
        click(releaseSummaryElements.getCrossIconTrendPopUp(),"cross icon on trend pop up", 5);
    }

    public boolean ifGraphPresentRowWise()
    {

        checkIfElementPresent(getWebElementsList(releaseSummaryElements.getRowWiseGraphList()).get(0),5000);
        if (getWebElementsList(releaseSummaryElements.getRowWiseGraphList()).size() == 2)
            return true;
        return false;

    }

    public boolean clickFirstLiveSongInTable()
    {
        checkIfElementPresent(releaseSummaryElements.getUniquesListens(),5000);
        int size =getWebElementsList(releaseSummaryElements.getTotalSongsCountPageWise()).size();

        if(size>=1)
        {
            getDriver().findElement(By.xpath("//tbody[@class='ant-table-tbody']//tr[1]//td[2]")).click();
            return true;
        }

        return false;
    }

    public String clickPlayButton()
    {
        String originalWindowId=  getDriver().getWindowHandle();
        click(releaseSummaryElements.getPlayButton(),"play button",5);
        Set<String> allWindowHandle =  getDriver().getWindowHandles();
        String s =null;
        for(String a : allWindowHandle)
        {

            if(!a.equalsIgnoreCase(originalWindowId))
            {
                getDriver().switchTo().window(a);
                s = getWebElementsList(releaseSummaryElements.getSongTitleOnWynkList()).get(1).getText();

            }


        }
        getDriver().switchTo().window(originalWindowId);

       return s;
    }


}







