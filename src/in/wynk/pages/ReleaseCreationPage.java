package in.wynk.pages;

import in.wynk.PageElements.ReleaseCreationElements;
import in.wynk.common.DriverActionUtils;
import in.wynk.framework.Reporting;
import in.wynk.framework.SoftAssert;
import org.openqa.selenium.By;
import junit.framework.Assert;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ReleaseCreationPage  extends DriverActionUtils {

ReleaseCreationElements releaseCreationElements;
    HashMap<String,String> releaseValidator;

    public ReleaseCreationPage(ReleaseCreationElements releaseCreationElements,
                               HashMap<String,String> releaseValidator,Reporting Reporter,
                               in.wynk.framework.Assert Assert, in.wynk.framework.SoftAssert SoftAssert) {

        super(Reporter, Assert, SoftAssert);
        this.releaseCreationElements = releaseCreationElements;
        this.releaseValidator = new HashMap<String,String>();
    }

    public void clickPlayButton()
    {
        getWebElementsList(releaseCreationElements.getPlayButton()).get(0).click();
    }

    public void clickYesUPCButton()
    {

        click(releaseCreationElements.getYesForUPC(),"Yes For UPC button" , 5);
    }

    public void clickYesISRCButton()
    {
        click(releaseCreationElements.getYesForISRC(),"Yes For ISRC" , 5);
    }

    public void clickCrossButton()
    {
       click(releaseCreationElements.getCrossButton(),"cross button" , 5);
    }

    public boolean isAreYouAddingFreshRelLabelPresent()
    {
        return isElementDisplayed(releaseCreationElements.getAreYouAddingFreshRel(), "Are you adding fresh release",true);
    }

    public boolean isYesRadioButtonPresent()
    {
        return isElementDisplayed(releaseCreationElements.getYesRadioButton(), "Yes Radio Button ",true);
    }

    public boolean isSmallerResolutionErrorMsgPresent()
    {
        return isElementDisplayed(releaseCreationElements.getSmallerResolutionErrorMsgArtwork(), "800 * 800 error msg",true);
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

    public void clickNoRadioButtonOnPopUp()
    {
        click(releaseCreationElements.getNoRadioButton(), "cross Button on pop up",true);
    }

    public void clickYesButtonOnPopUp()
    {
        click(releaseCreationElements.getYesRadioButton(), "cross Button on pop up",true);
    }

    public void clickContinueButtonPopUp()
    {
        click(releaseCreationElements.getContinueButtonTellUsAboutYourRelease(), "continue Button on pop up",true, 5);
    }

    public void clickAddAudioButton()
    {
        //click(releaseCreationElements.getUploadAudioButton(), "upload audio Button on pop up",true, 5);
        getElementWhenPresent(releaseCreationElements.getUploadAudioButton()).click();

    }



    public boolean isAddAudioButtonVisible()
    {
        return  isElementDisplayed(releaseCreationElements.getUploadAudioButton(),"upload audio Button on pop up",true);
    }

    public void clickBackArrowAddReleaseDetailPage()
    {
        click(releaseCreationElements.getBackArrowAddReleaseDetailPage(), "Back Arrow Add Release Detail Page",true);
    }

    public void clickContinueUploadReleasePage()
    {
        click(releaseCreationElements.getContinueUploadReleasePage(), "Continue Upload Release Page",true);
    }

    public boolean isProgressBarPresent()
    {
        try {
           return isElementDisplayed(releaseCreationElements.getSongProgressBar(),"progress Bar", true);
        }catch (Exception ex)
        {
            return false;
        }

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


    public void uploadAudio() throws IOException, InterruptedException {
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

        Thread.sleep(8000);

        releaseValidator.put("audio",
                getWebElementsList(releaseCreationElements.getTextOfUploadedFileList()).get(0).getText());
    }

    public void uploadWAVAudio() throws IOException {
        String filePath ="/Users/b0218201/Documents/Automation Repo/wynkStudio-web-automation/user-files/resources/";

        filePath = filePath+"wavAudio.wav";

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

    public void uploadSmallerResolutionArtwwork() throws IOException {
        String filePath ="/Users/b0218201/Documents/Automation Repo/wynkStudio-web-automation/user-files/resources/";

        filePath = filePath+"smallerResolution.jpeg";

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




    public void uploadArtwork() throws IOException, InterruptedException {
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
        Thread.sleep(20000);

        releaseValidator.put("artworkHref",
                getElementWhenPresent(releaseCreationElements.getArtworkHref(), 5).
                        getAttribute("src"));


    }

    public void typeReleaseTitleTextBox() throws Exception {
        click(releaseCreationElements.getReleaseTitleTextBox(), "Release Text box",true);
        type(releaseCreationElements.getReleaseTitleTextBox(),"Release Text box","Karishma Automation", 5 );
        releaseValidator.put("releaseTitle","Karishma Automation");
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

    public boolean checkIfSongIsPlaying() throws InterruptedException {
        Thread.sleep(2000);
        String s =  getElementWhenPresent(releaseCreationElements.getSongProgressBar()).getAttribute("style");
        s  =  s.split(";")[0].split(":")[1].replaceAll("\\s", "");
        while(!s.equalsIgnoreCase("100%"))
        {
           s = getElementWhenPresent(releaseCreationElements.getSongProgressBar()).getAttribute("style");
           s  =  s.split(";")[0].split(":")[1].replaceAll("\\s", "");
        }
        if(s.equalsIgnoreCase("100%"))
            return true;
      return false;
    }

    public void selectPrimaryArtistRole() throws InterruptedException
    {
        getElementWhenPresent(releaseCreationElements.getPrimaryArtistRoleDropdown()).click();
        Thread.sleep(1000);
        getElementWhenPresent(releaseCreationElements.getMusicProducerRole()).click();
        Thread.sleep(2000);
        releaseValidator.put("primaryArtistRole","Music Producer");
    }

    public void selectSupportingArtistRoleAndEnterName() throws Exception
    {
        getElementWhenPresent(releaseCreationElements.getAddSupportingArtistButton()).click();
        Thread.sleep(1000);
        getElementWhenPresent(releaseCreationElements.getSupportingActorRoleDropDown()).click();
        Thread.sleep(1000);
        getWebElementsList(releaseCreationElements.getSecondaryArtistSingerRoleList()).get(1).click();
        Thread.sleep(2000);
        type(releaseCreationElements.getSecondaryArtistName(),"secondary artist name","Rahul",5);

    }

    public void clickCrossButtonSecondaryArtist() throws Exception {

        click(releaseCreationElements.getSecondaryArtistCrossIcon(),"Secondary Artist Cross Icon", 5);
    }

    public boolean isAddSupportingArtistButtonPresent()
    {
      return  isElementDisplayed(releaseCreationElements.getAddSupportingArtistButton(), "Add supporting artist", true);
    }

    public String readPrimaryArtistName()
    {
        releaseValidator.put("primaryArtistName",getElementWhenPresent(
                releaseCreationElements.getPrimaryArtistName(),5).getAttribute("value"));
        return getElementWhenPresent(releaseCreationElements.getPrimaryArtistName(),5).getAttribute("value");

    }

    public void selectPrimaryLanguage() throws InterruptedException
    {
        getElementWhenPresent(releaseCreationElements.getLanguageDropDown()).click();
        Thread.sleep(1000);
        getElementWhenPresent(releaseCreationElements.getHindiLanguage()).click();
        Thread.sleep(2000);
        releaseValidator.put("PrimaryLanguage","Hindi");
    }

    public void selectPrimaryGenre() throws Exception
    {
        getElementWhenPresent(releaseCreationElements.getGenreDropDown()).click();
        Thread.sleep(2000);
        type(releaseCreationElements.getGenreSearchTextField(),"Genre","B", 5 );
        Thread.sleep(2000);
        getElementWhenPresent(releaseCreationElements.getBachataGenre()).click();
        Thread.sleep(2000);
        releaseValidator.put("PrimaryGenre","Bachata");
    }

    public void selectMultipleGenre() throws Exception
    {
        getElementWhenPresent(releaseCreationElements.getGenreDropDown()).click();
        Thread.sleep(2000);
        type(releaseCreationElements.getGenreSearchTextField(),"Genre","B", 5 );
        Thread.sleep(2000);
        getElementWhenPresent(releaseCreationElements.getBachataGenre()).click();
        Thread.sleep(2000);

        getElementWhenPresent(releaseCreationElements.getGenreDropDown()).click();
        Thread.sleep(3000);
        getElementWhenPresent(releaseCreationElements.getGenreSearchTextField(),5).sendKeys("N");
        Thread.sleep(2000);
        getElementWhenPresent(releaseCreationElements.getNativeAmericanGenre()).click();
        Thread.sleep(2000);
    }

    public void deleteAllGenre()
    {
        for(int i=0;i<countOfGenreSlected();i++)
        {
            getWebElementsList(releaseCreationElements.getGenreCloseButtonList()).get(0).click();
        }
    }

    public boolean isHTHelpModalPresent()
    {
       return isElementDisplayed(releaseCreationElements.getHtHelpPopUp(),"HTHelpModal",true);
    }

    public void clickHereLinkOnAddHTPage()
    {
        click(releaseCreationElements.getHereLink(),"Here Link On Add HT Page", true, 5);
    }

    public boolean isHTHelpBannerIntact()
    {
        boolean f1 = isElementDisplayed(releaseCreationElements.getHtHelpPopUp(),"HTHelpModal",true);
        boolean f2 = isElementDisplayed(releaseCreationElements.getHtBannerImage(),"HTHelpModal banner image",true);
        boolean f3 =  isElementDisplayed(releaseCreationElements.getHtBannerText(),"HTHelpModal banner text",true);

        if (f1 && f2 && f3)
          return  true;
       return false;
    }

    public void clickCrossIconOnHTHelpBanner()
    {
        click(releaseCreationElements.getCrossIcononBanner(),"CrossIconOnHTHelpBanner", true, 5);
    }
    public int countOfGenreSlected()
    {
        return getWebElementsList(releaseCreationElements.getGenreCloseButtonList()).size();
    }
    public void clickUploadLyricsAlongSongRadioButton()
    {
     click(releaseCreationElements.getUploadSongLyricsRadioButton(),"upload song lyrics radio button", true, 5);
    }

    public void clickUploadLrcButtonOnBanner() throws InterruptedException
    {
        Thread.sleep(3000);
        click(releaseCreationElements.getAddLRCFileViaBannerButton(),"Upload Lrc Button On Banner",
                true, 5);
    }

    public void clickYesPreviouslyUploadedRadioButton()
    {
        click(releaseCreationElements.getPreviouslyReleasedYesRadioButton(),"Yes button Previous upload", 5);
    }

    public void clickEditButtonForReleaseUploadedButton()
    {
        click(releaseCreationElements.getReleaseUploadedEditButton(),"Edit Button For Release Uploaded Button", 5);
    }

    public boolean isWrongURLAlertPresentPreviouslyReleased()
    {
        try {
            return isElementDisplayed(releaseCreationElements.getInvalidURLErrorPreviouslyRelease(),
                    "WrongURLAlert PreviouslyRelease", true);
        } catch (Exception e)
        {
            return false;
        }
    }

    public void enterTextInReleaseLinkTextBox(String text)
    {
        getWebElementsList(releaseCreationElements.getTextBoxWherePreviouslyReleaseList()).get(0).sendKeys(text);
    }

    public void uploadLyricFile() throws IOException, InterruptedException {
        String filePath ="/Users/b0218201/Documents/Automation Repo/wynkStudio-web-automation/user-files/resources/";

        filePath = filePath+"TAEYEON-lyrics.lrc";

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
        Thread.sleep(8000);
    }

    public void clickRemoveLRCfile()
    {
        click(releaseCreationElements.getRemoveLyrics(),"Remove Lyrics", 9);

    }

    public boolean isLrcFileSuccessfullyUploaded()
    {
        try {
            boolean flag = isElementDisplayed(releaseCreationElements.getIsLrcFileSuccfullyUploaded(),
                    "success msg of lrc file upload", true);
            if(flag)
            {
                if(releaseValidator.containsKey("lyrics"))
                {
                    releaseValidator.replace("Lyrics","TAEYEON-lyrics.lrc");
                }
                else {
                    releaseValidator.put("Lyrics","TAEYEON-lyrics.lrc");

                }
                return true;
            }
            return false;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public void clickAddLyricRadioButton()
    {
        click(releaseCreationElements.getUploadLyricTextRadioButton(),"Add song lyrics Text radio button", true, 5);
    }

    public void clickUploadFileLRCFormatButton() throws InterruptedException {
        click(releaseCreationElements.getUploadLyricsInLRC(), "Upload File LRC Format Button", true);
        Thread.sleep(1000);
    }


    public void typeLyricsManually() throws Exception
    {
        type(releaseCreationElements.getLyricsTextArea(), "Lyrics Text box", "Lyrics Lyrics Lyrics Lyrics", 5);
        if(!releaseValidator.containsKey("Lyrics"))
        {
            releaseValidator.replace("Lyrics","Lyrics Lyrics Lyrics Lyrics");
        }
        else {
            releaseValidator.put("Lyrics","Lyrics Lyrics Lyrics Lyrics");

        }

    }

    public void clickNoExplicitContentRadioButton()
    {
       click(releaseCreationElements.getExplicitNoRadioButton(),"Explicit Content Radio Button", true);
       releaseValidator.put("ExplicitContent","No");
    }

    public void clickNoPreviouslyReleaseRadioButton()
    {
        click(releaseCreationElements.getPreviouslyReleasedNoRadioButton(),
                "Previously Released No Radio Button", true);
        releaseValidator.put("PreviouslyReleased","No");
    }

    public void clickAndEnterFirstHT(String clipName) throws Exception {
        type(releaseCreationElements.getHtClipNameTextBox_1(),"HT ClipName 1", clipName);
        releaseValidator.put("HTClip1",clipName);
    }

    public void clickAndEnterSecondHT(String clipName) throws Exception {

        type(releaseCreationElements.getHtClipNameTextBox_2(),"HT ClipName 2", clipName);
        releaseValidator.put("HTClip2",clipName);
    }

    public void clickAddMoreHTCLips()  {
        click(releaseCreationElements.getAddMoreClip(),"Add more HT clips", true);

    }



    public boolean isHTDuplicateErrorMsgExist()
    {
        try {
            return isElementDisplayed(releaseCreationElements.getDuplicateClipNameErrorMsg(), "DuplicateHT error msg", true);
        }
        catch (Exception e)
        {
            return false;
        }
    }

   public void clickAsSoonAsPosibleRadioButton()
   {
      click( releaseCreationElements.getAsSoonAsPossible(),"as soon as possible radio button", true);
   }

   public void matchTheDetails()
   {
       String releaseTitle = getWebElementsList(releaseCreationElements.getReleaseTitleList()).get(0).getText();
       String primaryArtistName = getElementWhenPresent(releaseCreationElements.getNamePrimaryArtist(),5).getText();
       String primaryLanguage  = getElementWhenPresent(releaseCreationElements.getPrimaryLanguage(),5).getText();
       String primaryGenre  = getElementWhenPresent(releaseCreationElements.getPrimaryGenre(),5).getText();
       String lyrics = getElementWhenPresent(releaseCreationElements.getLyrics(),5).getText();
       String explicitContent = getElementWhenPresent(releaseCreationElements.getExplicitContent(),5).getText();
       String htname1 = getElementWhenPresent(releaseCreationElements.getHtName1(),5).getText();
       String htname2 = getElementWhenPresent(releaseCreationElements.getHtName2(),5).getText();
       String coverImageHref = getElementWhenPresent(releaseCreationElements.getCoverImage(),5).getAttribute("src");
       //String audioFileName = getElementWhenPresent(releaseCreationElements.getAudioFileName(),5).getText();


       Assert.assertTrue(releaseValidator.get("artworkHref").equalsIgnoreCase(coverImageHref));
       Assert.assertTrue(releaseValidator.get("primaryArtistName").equalsIgnoreCase(primaryArtistName));
       Assert.assertTrue(releaseValidator.get("PrimaryGenre").equalsIgnoreCase(primaryGenre));
       Assert.assertTrue(releaseValidator.get("ExplicitContent").equalsIgnoreCase(explicitContent));
       Assert.assertTrue(releaseValidator.get("Lyrics").equalsIgnoreCase(lyrics));
       //Assert.assertTrue(releaseValidator.get("audio").equalsIgnoreCase(audioFileName));
       Assert.assertTrue(releaseValidator.get("releaseTitle").equalsIgnoreCase(releaseTitle));
       Assert.assertTrue(releaseValidator.get("HTClip2").equalsIgnoreCase(htname2));
       Assert.assertTrue(releaseValidator.get("HTClip1").equalsIgnoreCase(htname1));
       Assert.assertTrue(releaseValidator.get("PrimaryLanguage").equalsIgnoreCase(primaryLanguage));
   }

   public void clickConfirmAndSubmitButton()
   {
      click( releaseCreationElements.getConfirmAndSubmit(),"confirm and submit",5);
   }


}
