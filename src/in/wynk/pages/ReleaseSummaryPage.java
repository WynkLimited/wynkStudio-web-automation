package in.wynk.pages;

import in.wynk.PageElements.ReleaseCreationElements;
import in.wynk.PageElements.ReleaseSummaryElements;
import in.wynk.common.DriverActionUtils;
import in.wynk.framework.Assert;
import in.wynk.framework.Reporting;
import in.wynk.framework.SoftAssert;

import java.util.HashMap;

public class ReleaseSummaryPage extends DriverActionUtils {

    ReleaseSummaryElements releaseSummaryElements;
    HashMap<String, String> songSpecificAnaLytic ;
    SongDashboardPage songDashboardPage;

    public ReleaseSummaryPage(ReleaseSummaryElements releaseSummaryElements,Reporting Reporter, in.wynk.framework.Assert Assert, in.wynk.framework.SoftAssert SoftAssert) {
        super(Reporter, Assert, SoftAssert);

        this.releaseSummaryElements = releaseSummaryElements;
        this.songSpecificAnaLytic = songSpecificAnaLytic;

    }


    public String getNameOfSong()
    {
      return getElementWhenPresent(releaseSummaryElements.getNameOfSong()).getText();
    }

    public boolean isLiveTextPresent()
    {
        return checkIfElementPresent(releaseSummaryElements.getLiveText(),5);
    }
    public boolean isDraftTextPresent()
    {
        return checkIfElementPresent(releaseSummaryElements.getDraftText(),5);
    }
    public boolean isInReviewTextPresent()
    {
        return checkIfElementPresent(releaseSummaryElements.getInReviewText(),5);
    }

    public boolean isRejectedTextPresent()
    {
        return checkIfElementPresent(releaseSummaryElements.getRejectedText(),5);
    }

    public String getUniqueListens()
    {
        return getElementWhenPresent(releaseSummaryElements.getUniquesListens()).getText();
    }

    public String getTotalStream()
    {
        return getElementWhenPresent(releaseSummaryElements.getTotalStreams()).getText();
    }

    public String getHelloTunesActivated()
    {
        return getElementWhenPresent(releaseSummaryElements.getHelloTunesActivated()).getText();
    }

    public String getLikes()
    {
        return getElementWhenPresent(releaseSummaryElements.getLikes()).getText();
    }

    public void clickBackArrow()
    {
        click(releaseSummaryElements.getBackArrow(),"back arrow", 5);
    }

    public boolean isLastUpdatedTextPresentLiveState()
    {
      return checkIfElementPresent(releaseSummaryElements.getLastUpdatedOn(),5);
    }

    public boolean isListenOnWynkButtonPresent()
    {
        return checkIfElementPresent(releaseSummaryElements.getListenOnWynkButton(),5);
    }


    public boolean checkIfAllElementsOnDraftReleaseSummaryPresent()
    {
        if(checkIfElementPresent(releaseSummaryElements.getContinueFromWhereYouLeft())||
                isDraftTextPresent() || checkIfElementPresent(releaseSummaryElements.getContinueButtonDraftState()) ||
                checkIfElementPresent(releaseSummaryElements.getStatusBarOfIncompleteRelease()))
        {
            return true;
        }

        return false;

    }

    public boolean checkIfAllElementsOnInReviewReleaseSummaryPresent() {

        if(checkIfElementPresent(releaseSummaryElements.getInReviewStateText()) ||
        checkIfElementPresent(releaseSummaryElements.getNewReleaseInReviewState())||
        checkIfElementPresent(releaseSummaryElements.getInReviewText()))
        {
            return true;
        }

        return false;
    }

    public boolean checkIfAllElementsOnRejectedReleaseSummaryPresent() {

        if(checkIfElementPresent(releaseSummaryElements.getRejectedText()) ||
                checkIfElementPresent(releaseSummaryElements.getSongRejectionText())||
                checkIfElementPresent(releaseSummaryElements.getInReviewText())||
        checkIfElementPresent(releaseSummaryElements.getNeedHelpButtonRejectionState()))
        {
            return true;
        }

        return false;
    }


    }










