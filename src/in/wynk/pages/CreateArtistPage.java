package in.wynk.pages;

import in.wynk.PageElements.CreateArtistElements;
import in.wynk.common.DriverActionUtils;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import in.wynk.framework.Reporting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.List;


public class CreateArtistPage  extends DriverActionUtils {

    CreateArtistElements createArtistElements;

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

    public void uploadImage() throws Exception
    {
        String script ="tell application \"System Events\"\n" +
                "\t--one second delay\n" +
                "\tdelay 2\n" +
                "\tkeystroke \"G\" using {command down, shift down}\n" +
                "\t\n" +
                "\tdelay 2\n" +
                "\tkeystroke \"/Users/b0218201/Documents/Automation Repo/wynkStudio-web-automation/user-files/resources/auto.jpg\"\n" +
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
        {
         System.out.println(process.toString());
        }

        process.destroy();
    }

    public void clickPhotoUploadAddArtistDetailsPage()
    {
        click(createArtistElements.getUploadPhotoButton(), " Photo Upload Button on Add Artist Details Page ",true);
    }

    public void clickAddPhotoPlusSignPopUp()
    {
        click(createArtistElements.getAddPhotoPlusSign(), " Photo Upload + sign Button on pop up on Add Artist Details Page ",true);
    }

    public void clickDoneButtonOnPopUp()
    {
        click(createArtistElements.getDoneButton(), " Done Button on pop up on Add Artist Details Page ",true);
    }


    public String getLinkOfImageUploadedOnPopUp()
    {
        sleep(10);
       return getElementWhenClickable(createArtistElements.getImageUploadedOnPopUp(),6).getAttribute("src");
    }

    public String getLinkOfImageUploadedOnAddArtistDetails()
    {
        sleep(6);
        return getElementWhenClickable(createArtistElements.getImageUploadedOnAddArtistPage(),6).getAttribute("src");
    }

    public boolean ifImageUploadedIsCorrect()
    {
        if( getLinkOfImageUploadedOnPopUp().equalsIgnoreCase(getLinkOfImageUploadedOnAddArtistDetails()))
        {
            return true;
        }
        return false;
    }


    public void typeArtistNameInTextBox(String name) throws Exception {
        click(createArtistElements.getTypeYourNameTextBox(), "Email Text Box on Forgot Password Page" );
        type( createArtistElements.getTypeYourNameTextBox(), "Artist Name Text Box on Create or claim Artist Page", name, 5);
    }

    private void selectValueFromDropDown(By locator, String value)
    {
        Select select = new Select(getElementWhenClickable(locator, 3));
        select.selectByValue(value);

    }

    public static enum dropDownToSelect {NATIONALITY,LANGUAGES, ERA, GENRE, ROLE}

    public void clickDropDownAndSelectValue(dropDownToSelect dropdownName, String value)
    {
        switch (dropdownName) {
            case NATIONALITY:
                selectValueFromDropDown(createArtistElements.getNationalityDropDown(), value);
                break;

            case LANGUAGES:
                selectValueFromDropDown(createArtistElements.getLanguageDropdown(), value);
                break;

            case GENRE:
                selectValueFromDropDown(createArtistElements.getGenreDropdown(), value);
                break;

            case ERA:
                selectValueFromDropDown(createArtistElements.getEraDropdown(), value);
                break;

            case ROLE:
                selectValueFromDropDown(createArtistElements.getRolesDropdown(), value);
                break;

        }
    }

    public void clickBackArrowButton()
    {
        click(createArtistElements.getBackButton(),"Back Arrow Button");
    }

    public void clickContinueButton()
    {
        click(createArtistElements.getContinueButton(),"Continue Button on Add Artist Details Page");
    }
}
