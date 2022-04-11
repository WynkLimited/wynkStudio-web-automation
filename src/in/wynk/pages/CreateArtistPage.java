package in.wynk.pages;

import in.wynk.PageElements.CreateArtistElements;
import in.wynk.common.DriverActionUtils;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import in.wynk.framework.Reporting;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.List;


public class CreateArtistPage  extends DriverActionUtils {

    CreateArtistElements createArtistElements;
    static int countOfFileUploaded = 0;
    static int counter =0;


    public CreateArtistPage( CreateArtistElements createArtistElements,
                             Reporting Reporter, in.wynk.framework.Assert Assert,
                             in.wynk.framework.SoftAssert SoftAssert)
    {
        super(Reporter, Assert, SoftAssert);
        this.createArtistElements= createArtistElements;
    }

    public int getSizeOfListOfArtistInDropDown()
    {
        return getWebElementsList(createArtistElements.getListOfArtist()).size();
    }
    public void clickCrossButtonOnProfilePic()
    {
        click(createArtistElements.getCrossButtonOnUpdatePhotoTab(), "Cross Button update photo tab",true);
    }

    public void clickFirstNonClaimedArtistFound()
    {

        int size = getSizeOfListOfArtistInDropDown();
        List<WebElement> artistList = getWebElementsList(createArtistElements.getListOfArtist());

        if(size>1)
        {
            for (int i = 2; i < size; i++) {
                try {
                    boolean flag = isElementDisplayed(By.
                            xpath("//div[@class='infinite-scroll-component ']/div["+i+"]//p[contains(text(),'Claimed')]"),
                            "claimed text", true);
                    if (flag)
                    {
                       continue;

                    }
                } catch (Exception ex) {
                    artistList.get(i).click();
                    break;
                }
            }
        }
        else {
            this.Reporter.log("Click on Artist found","Atleast 1 artist should be found","No Artist Found","No Artist Found");
        }
    }

    public boolean isNoArtistFoundMsgPresent()
    {
        return isElementDisplayed(createArtistElements.getNoArtistFoundMsg(), "No Artist Found msg",true);
    }

    public boolean isNameAlertPresent()
    {
        return isElementDisplayed(createArtistElements.getArtistNameAlert(), "Name Alert",true);
    }

    public boolean isLangSelectedAlertPresent()
    {
        return isElementDisplayed(createArtistElements.getLangAlert(), "No Lang Selected Alert",true);
    }

    public boolean isIprsSelectedAlertPresent()
    {
        return isElementDisplayed(createArtistElements.getIprsAlert(), "No IPRS Selected Alert",true);
    }

    public boolean isNoBioMentionedAlertPresent()
    {
        return isElementDisplayed(createArtistElements.getArtistBioAlert(), "No Bio Mentioned Alert",true);
    }

    public boolean isNoRoleAlertPresent()
    {
        return isElementDisplayed(createArtistElements.getRoleAlert(), "No Role Mentioned Alert",true);
    }

    public boolean isHeadingClaimCreateArtistPresent()
    {
        return isElementDisplayed(createArtistElements.getHeadingCreateClaimArtistPage(), "Heading Create Claim Artist Page",true);
    }


    public void clickFirstClaimedArtistFound()
    {

        int size = getWebElementsList(createArtistElements.getListOfArtist()).size();
        List<WebElement> artistList = getWebElementsList(createArtistElements.getListOfArtist());

        if(size>1)
        {
            for (int i = 2; i < size; i++) {
                try {
                    boolean flag = isElementDisplayed(By.
                                    xpath("//div[@class='infinite-scroll-component ']/div["+i+"]//p[contains(text(),'Claimed')]"),
                            "claimed text", true);
                    if (flag)
                    {
                        artistList.get(i).click();
                        break;

                    }
                } catch (Exception ex) {
                    continue;
                }
            }
            }

        else {
            this.Reporter.log("Click on Artist found","Atleast 1 artist should be found","No Artist Found","No Artist Found");
        }
    }

    public void clickCreateNewProfile()
    {
        click(createArtistElements.getCreateNewProfileButton(), "Create New Profile Button",true);
    }

    public void uploadFirstImage() throws Exception {
        uploadImage("auto1.jpg");
    }
    public void uploadSecondImage() throws Exception {
        uploadImage("auto2.jpg");
    }
    public void uploadThirdImage() throws Exception {
        uploadImage("auto3.jpg");
    }

    public void uploadInvalidImage() throws Exception {
        uploadImage("invalid.gif");
    }

    public void uploadImage(String imageName) throws Exception
    {
       String filePath ="/Users/b0218201/Documents/Automation Repo/wynkStudio-web-automation/user-files/resources/";

        filePath = filePath+imageName;

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
        sleep(5);
    }

    public void clickPhotoUploadAddArtistDetailsPage()
    {
        try {
            if(getDriver().findElement(createArtistElements.getUpdatePhotoButton()).isDisplayed()) {
               //do nothing
            }
        }catch (Exception ex)
        {
            click(createArtistElements.getUploadPhotoButton(), " Photo Upload Button on Add Artist Details Page ", true);
        }
    }

    public void clickAddPhotoPlusSignPopUp()
    {
        getElementWhenPresent(createArtistElements.getAddPhotoPlusSign(),25).click();
    }

    public boolean isAddPhotoPlusSignPopUp()
    {
     return   isElementDisplayed(createArtistElements.getAddPhotoPlusSign(),"+ sign", true);
    }

    public void clickDoneButtonOnPopUp()
    {
        click(createArtistElements.getDoneButton(), " Done Button on pop up on Add Artist Details Page ",true);
    }


    public String getLinkOfFirstImageUploadedOnPopUp() throws InterruptedException {
        Thread.sleep(10000);
        checkIfElementPresent(getWebElementsList(createArtistElements.getImageUploadedOnPopUp()).get(0),40);
        return getWebElementsList(createArtistElements.getImageUploadedOnPopUp()).get(0).getAttribute("src");
    }

    public String getLinkOfSecondImageUploadedOnPopUp() throws InterruptedException
    {
        Thread.sleep(8000);
        checkIfElementPresent(getWebElementsList(createArtistElements.getImageUploadedOnPopUp()).get(1),40);
        return getWebElementsList(createArtistElements.getImageUploadedOnPopUp()).get(1).getAttribute("src");
    }


    public String getLinkOfThirdImageUploadedOnPopUp() throws InterruptedException {
        Thread.sleep(10000);
        checkIfElementPresent(getWebElementsList(createArtistElements.getImageUploadedOnPopUp()).get(1),40);
        return getWebElementsList(createArtistElements.getImageUploadedOnPopUp()).get(1).getAttribute("src");
    }



    public String getLinkOfImageUploadedOnAddArtistDetails()
    {
        return getElementWhenClickable(createArtistElements.getImageUploadedOnAddArtistPage(),6).getAttribute("src");
    }

    public boolean ifImageUploadedIsCorrect() throws InterruptedException {
        if( getLinkOfFirstImageUploadedOnPopUp().equalsIgnoreCase(getLinkOfImageUploadedOnAddArtistDetails()))
        {
            return true;
        }
        return false;
    }


    public void typeArtistNameInTextBox(String name) throws Exception
    {
        click(createArtistElements.getTypeYourNameTextBox(), "Email Text Box on Forgot Password Page" );
        Thread.sleep(2000);
        type( createArtistElements.getTypeYourNameTextBox(), "Artist Name Text Box on Create or claim Artist Page", name, 5);
    }

    public String readArtistNameFromArtistNameTextBox()
    {
        return getElementWhenVisible(createArtistElements.getTypeYourNameTextBox(),5).getAttribute("value");
    }

    private void selectValueFromDropDown(By locator, String value) throws Exception {
        getWebElementsList(createArtistElements.getLanguageDropdown()).get(2).click();
         sleep(3);
         click(createArtistElements.getHindiLang(), "Hindi Language drop down" , true);
        Thread.sleep(20000);

    }

    public static enum dropDownToSelect {NATIONALITY,LANGUAGES, ERA, GENRE, ROLE}

    public void clickDropDownAndSelectValue(dropDownToSelect dropdownName, String value) throws Exception {
        switch (dropdownName) {
            case NATIONALITY:
                selectValueFromDropDown(createArtistElements.getNationalityDropDown(), value);
                break;

            case LANGUAGES:
                getWebElementsList(createArtistElements.getLanguageDropdown()).get(2).click();
                sleep(2);
                click(createArtistElements.getHindiLang(), "Hindi Language drop down" , true);
                Thread.sleep(4000);
                break;

            case GENRE:
                selectValueFromDropDown(createArtistElements.getGenreDropdown(), value);
                break;

            case ERA:
                selectValueFromDropDown(createArtistElements.getEraDropdown(), value);
                break;

            case ROLE:
                getWebElementsList(createArtistElements.getLanguageDropdown()).get(3).click();
                sleep(2);
                click(createArtistElements.getInstrumentalist(), "Instrumentalist" , true);
                Thread.sleep(4000);
                break;

        }
    }

    public void clickBackArrowButton()
    {
        click(createArtistElements.getBackButton(),"Back Arrow Button");
    }

    public void clickContinueButton()
    {
        scrollingToElementofAPage(createArtistElements.getContinueButton());
        click(createArtistElements.getContinueButton(),"Continue Button on Add Artist Details Page");
    }

    public void clickYesIprsRadioButton() throws InterruptedException {
        Thread.sleep(3000);
        scrollingToElementofAPage(createArtistElements.getIprsYes());
        click(createArtistElements.getIprsYes(),"Yes IPRS");
    }
    public void clickNoIprsRadioButton()
    {
        click(createArtistElements.getIprsNo(),"No IPRS");
    }

    public void typeArtistBio() throws Exception {
        String bio = "Given User open Wynk studio Register page\n" +
                "Then Enter EmailId on Register Page\n" +
                "And  Enter Password\n" +
                "And  Enter Full Name\n" +
                "Then Click on Create Account\n" +
                "And Read verification Link using Mailinator API\n" +
                "Then Hit verification link sent over mail\n" +
                "Then Assert User should be on profession page\n" +
                "Then click on i am a Music Artist\n";
        type( createArtistElements.getBioTextBox(), "Artist Name Text Box on Create or claim Artist Page", bio, 5);
        Thread.sleep(3000);
    }

}
