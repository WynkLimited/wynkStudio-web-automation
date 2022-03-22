package in.wynk.pages;

import in.wynk.PageElements.ProfessionPageElements;
import in.wynk.common.DriverActionUtils;
import in.wynk.framework.Assert;
import in.wynk.framework.Reporting;
import in.wynk.framework.SoftAssert;



public class ProfessionPage extends DriverActionUtils {

    ProfessionPageElements professionPageElements;

    public ProfessionPage(ProfessionPageElements professionPageElements, Reporting Reporter, in.wynk.framework.Assert Assert, in.wynk.framework.SoftAssert SoftAssert) {
        super(Reporter, Assert, SoftAssert);
        this.professionPageElements =professionPageElements;

    }

    public boolean isAmAMusicArtistButtonPresent()
    {
        return isElementDisplayed( professionPageElements.getIAmAMusicArtistButton(),
                "I am a Music Artist Button",true);
    }

    public void clickIAmAMusicArtistButton()
    {
        click(professionPageElements.getIAmAMusicArtistButton(), "I am a Music Artist Button");
    }


    public boolean isAmAPodcastCreatorButtonPresent()
    {
        return isElementDisplayed( professionPageElements.getIAmPodcastCreatorButton(),
                "I am a Podcast Creator Button",true);
    }


    public boolean isAgencyAndLabelButtonPresent()
    {
        return isElementDisplayed( professionPageElements.getIAmLabelAndAgencyButton(),
                "I am a Label and Agency Button",true);
    }

    public boolean isProfilelButtonPresent()
    {
        return isElementDisplayed( professionPageElements.getProfileButton(),
                "I am a Label and Agency Button",true);
    }



    public boolean isWynkStudioBannerPresent()
    {
        return isElementDisplayed( professionPageElements.getWynkStudioBanner(),
                "Wynk Banner",true);
    }

    public String getEmailIdFromProfileDropDown()
    {
       return getElementWhenVisible(professionPageElements.getEmailIdProfileDropdown()).getAttribute("value");
    }

    public void clickLogoutButton()
    {
        click(professionPageElements.getLogoutProfileDropDown(), "Logout Button from Profile Drop down");
    }

    public void clickProfileButton()
    {
        click(professionPageElements.getProfileButton(), "Profile Button");
    }






}
