package in.wynk.pages;

import in.wynk.PageElements.ReleaseCreationElements;
import in.wynk.common.DriverActionUtils;
import in.wynk.framework.Assert;
import in.wynk.framework.Reporting;
import in.wynk.framework.SoftAssert;
import org.openqa.selenium.By;

import java.io.IOException;

public class ReleaseCreationPage  extends DriverActionUtils {

ReleaseCreationElements releaseCreationElements;

    public ReleaseCreationPage(ReleaseCreationElements releaseCreationElements,Reporting Reporter, in.wynk.framework.Assert Assert, in.wynk.framework.SoftAssert SoftAssert) {
        super(Reporter, Assert, SoftAssert);
        this.releaseCreationElements = releaseCreationElements;

    }

    public boolean isAreYouAddingFreshRelLabelPresent()
    {
        return isElementDisplayed(releaseCreationElements.getAreYouAddingFreshRel(), "Are you adding fresh release",true);
    }

    public boolean isYesRadioButtonPresent()
    {
        return isElementDisplayed(releaseCreationElements.getYesRadioButton(), "Yes Radio Button ",true);
    }

    public boolean isNoRadioButtonPresent()
    {
        return isElementDisplayed(releaseCreationElements.getNoRadioButton(), "No Radio Button",true);
    }

    public boolean isCrossButtonOnPopUpPresent()
    {
        return isElementDisplayed(releaseCreationElements.getCrossButtonPopUp(), "cross Button on pop up",true);
    }
    public void clickCrossButtonOnPopUp()
    {
        click(releaseCreationElements.getCrossButtonPopUp(), "cross Button on pop up",true);
    }

    public void clickNoButtonOnPopUp()
    {
        click(releaseCreationElements.getNoRadioButton(), "cross Button on pop up",true);
    }

    public void clickYesButtonOnPopUp()
    {
        click(releaseCreationElements.getYesRadioButton(), "cross Button on pop up",true);
    }

    public void clickContinueButtonPopUp()
    {
        click(releaseCreationElements.getContinueButtonTellUsAboutYourRelease(), "continue Button on pop up",true);
    }

    public void clickAddAudioButtonPopUp()
    {
        click(releaseCreationElements.getUploadAudioButton(), "upload audio Button on pop up",true);
    }


    public void clickBackArrowAddReleaseDetailPage()
    {
        click(releaseCreationElements.getBackArrowAddReleaseDetailPage(), "Back Arrow Add Release Detail Page",true);
    }

    public void clickContinueUploadReleasePage()
    {
        //scrollingToBottomofAPage();
        click(releaseCreationElements.getContinueUploadReleasePage(), "Continue Upload Release Page",true);
    }

    public boolean isContactUsButtonPresent()
    {
        return isElementDisplayed(releaseCreationElements.getContactUsPopUp(), "cross Button on pop up",true);
    }

    public boolean isAddReleaseDetailHeaderPresent()
    {
        return isElementDisplayed(releaseCreationElements.getAddReleaseDetailHeadingOnPage(), "Add Release Detail Header",true);
    }

    public boolean isArtworkMissingAlertPresent()
    {
        return isElementDisplayed(releaseCreationElements.getArtworkMissingAlert(), "Artwork missing alert",true);
    }

    public boolean isAddYourReleaseDetailsHeading()
    {
        sleep(10);
        return isElementDisplayed(releaseCreationElements.getAddYourReleaseDetailsHeader(), "Page 2 Header",true);
    }

    public boolean isErrorMsgsForMissingFieldUploadReleasePage()
    {
       int size = getWebElementsList(releaseCreationElements.getAddReleaseDetailHeadingOnPage()).size();
       if(size<=4 || size>1)
       {
           return true;
       }
        return false;
    }

    public boolean isBackDropModalPresent()
    {
        try {
            return isElementDisplayed(releaseCreationElements.getBackdropModal(), "Back Drop modal", true);
        }
        catch (Exception e)
        {
            return false;
        }
    }


    public void uploadAudio() throws IOException {
        String filePath ="/Users/b0218201/Documents/Automation Repo/wynkStudio-web-automation/user-files/resources/";

        filePath = filePath+"audiofile.mp3";

        String script ="tell application \"System Events\"\n" +
                "\t--one second delay\n" +
                "\tdelay 2\n" +
                "\tkeystroke \"G\" using {command down, shift down}\n" +
                "\t\n" +
                "\tdelay 2\n" +
                "\tkeystroke \"" +filePath +"\"\n"+
                "\tdelay 2\n" +
                "\t\n" +
                "\tkeystroke return\n" +
                "\tdelay 2\n" +
                "\tkeystroke return\n" +
                "\t\n" +
                "end tell\n";

        Runtime runtime = Runtime.getRuntime();
        String[] args = { "osascript", "-e", script };
        Process process = runtime.exec(args);
        while(process.isAlive())
        { //do nothing
        }

        process.destroy();
        sleep(30);
    }

    public void uploadArtwork() throws IOException {
        String filePath ="/Users/b0218201/Documents/Automation Repo/wynkStudio-web-automation/user-files/resources/";

        filePath = filePath+"artwork.png";

        String script ="tell application \"System Events\"\n" +
                "\t--one second delay\n" +
                "\tdelay 2\n" +
                "\tkeystroke \"G\" using {command down, shift down}\n" +
                "\t\n" +
                "\tdelay 2\n" +
                "\tkeystroke \"" +filePath +"\"\n"+
                "\tdelay 2\n" +
                "\t\n" +
                "\tkeystroke return\n" +
                "\tdelay 2\n" +
                "\tkeystroke return\n" +
                "\t\n" +
                "end tell\n";

        Runtime runtime = Runtime.getRuntime();
        String[] args = { "osascript", "-e", script };
        Process process = runtime.exec(args);
        while(process.isAlive())
        { //do nothing
        }
        process.destroy();
        int x =2;
    }

    public void typeReleaseTitleTextBox() throws Exception {
        click(releaseCreationElements.getReleaseTitleTextBox(), "Release Text box",true);
        type(releaseCreationElements.getReleaseTitleTextBox(),"Release Text box","Karishma Automation", 5 );
    }


    public void clickGenerateISRCForMe() throws Exception {

        getWebElementsList(releaseCreationElements.getGenerateCodeForMeList()).get(0).click();

    }

    public void clickGenerateUPCForMe() throws Exception {

        getWebElementsList(releaseCreationElements.getGenerateCodeForMeList()).get(1).click();

    }

    public void clickUploadArtwork() throws Exception {
        click(releaseCreationElements.getUploadArtworkButton(), "upload artwork button",true);
    }







}
