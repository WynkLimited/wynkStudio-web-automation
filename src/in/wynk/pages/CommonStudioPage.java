package in.wynk.pages;

import in.wynk.PageElements.CommonElements;
import in.wynk.common.DriverActionUtils;
import in.wynk.framework.Reporting;
import in.wynk.common.Utils;

public class CommonStudioPage extends DriverActionUtils {

    CommonElements commonElements;
    Utils utility;

    public CommonStudioPage( Reporting Reporter, in.wynk.framework.Assert Assert,
                            in.wynk.framework.SoftAssert SoftAssert, CommonElements commonElements, Utils utility)
    {

        super(Reporter, Assert, SoftAssert);
        this.commonElements = commonElements;
        this.utility = utility;
    }

    public boolean isAllSong_RejectedTabPresent()
    {
        return isElementDisplayed( commonElements.getAllDraftLiveRejectDraftTabStudioPageHomePage(), "All Song..Rejected Tab",true);

    }

    public void clickProfileButton()
    {
        click(commonElements.getProfileButton(), "profile button");
    }

    public boolean isProfileButtonPresent()
    {
        return isElementDisplayed(commonElements.getProfileButton(), "profile button" , true);
    }

    public boolean isAllSongsStudioPagePresent()
    {
        return isElementDisplayed( commonElements.getAllSongsPodcastdivHomePage(), "All Song Tab",true);
    }

    public int getTotalSongsInStudioHome()
    {
        return getWebElementsList(commonElements.getAllSongPodcastListHomePage()).size();
    }

    public boolean isSearchBoxPresent()
    {
        return isElementDisplayed( commonElements.getSearchBoxStudioPage(), "All Song Tab",true);
    }


}
