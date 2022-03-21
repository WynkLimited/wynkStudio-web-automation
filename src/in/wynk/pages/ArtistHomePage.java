package in.wynk.pages;

import in.wynk.PageElements.CommonElements;
import in.wynk.PageElements.ArtistHomeElements;
import in.wynk.common.DriverActionUtils;
import in.wynk.framework.Driver;
import in.wynk.framework.Reporting;

public class StudioHomePage extends DriverActionUtils {

    ArtistHomeElements studioHomeElements;
    CommonElements commonElements;

    public StudioHomePage(Reporting Reporter, in.wynk.framework.Assert Assert,
                          in.wynk.framework.SoftAssert SoftAssert, ThreadLocal<Driver.HashMapNew> sTestDetails, ArtistHomeElements studioHomeElements,
                          CommonElements commonElements )
    {
        super(Reporter, Assert, SoftAssert, sTestDetails);
        this.studioHomeElements = studioHomeElements;
        this.commonElements = commonElements;
    }

    public boolean isAllSong_RejectedTabPresent()
    {
        return isElementDisplayed( commonElements.getAllDraftLiveRejectDraftTabStudioPageHomePage(), "All Song..Rejected Tab",true);
    }

    public boolean isAllSongsStudioPagePresent()
    {
        return isElementDisplayed( commonElements.getAllSongsPodcastdivHomePage(), "All Song Tab",true);
    }

    public int getTotalSongsInStudioHome()
    {
       return getWebElementsList(commonElements.getAllSongPodcastListHomePage()).size();
    }

    public boolean isNewReleaseButtonPresent()
    {
        return isElementDisplayed(studioHomeElements.getNewReleaseButtonStudioPage(), "New release button",true);
    }

    public boolean isProfileButtonPresent()
    {
        return isElementDisplayed( commonElements.getProfileButton(), "profile button",true);
    }

}
