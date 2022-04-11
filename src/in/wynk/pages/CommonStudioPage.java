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

    public int getTotalSongs()
    {
        return getWebElementsList(commonElements.getAllSongPodcastListHomePage()).size();
    }

    public boolean isSearchBoxPresent()
    {
        return isElementDisplayed( commonElements.getSearchBoxStudioPage(), "All Song Tab",true);
    }


    public String getCountOfAllSongs()
    {
        scrollingToBottomofAPage();
        try {
            return Integer.toString(getWebElementsList(commonElements.getCountSongsAllTab()).size());
        }
        catch (Exception e)
        {
            return null;
        }
    }


    public String getCountOfAllSongFromTab()
    {
        scrollingToBottomofAPage();
     return getElementWhenPresent(commonElements.getAllTabText()).getText().split(" ")[1];
    }

    public void clickAllSongTab()
    {
        click(commonElements.getAllTabText(), "All song tab",5);
    }

    public String getCountOfDraftSongFromTab()
    {
        scrollingToBottomofAPage();
        return getElementWhenPresent(commonElements.getDraftTabText()).getText().split(" ")[1];
    }
    public boolean clickDraftSongTab()
    {
        if(!getCountOfDraftSongFromTab().contains("(0")) {
            click(commonElements.getDraftTabText(), "Draft song tab", 5);
            return true;
        }
        return false;
    }

    public String getCountOfinReviewSongFromTab()
    { scrollingToBottomofAPage();
        return getElementWhenPresent(commonElements.getInReviewTabText()).getText().split(" ")[1];
    }

    public boolean clickinReviewSongTab()
    {
        if(!getCountOfinReviewSongFromTab().contains("(0")) {
            click(commonElements.getInReviewTabText(), "Live song tab", 5);
            return true;
        }
        return false;
    }

    public String getCountOfRejectedSongFromTab()
    { scrollingToBottomofAPage();
        return getElementWhenPresent(commonElements.getRejectedTabText()).getText().split(" ")[1];
    }


    public boolean clickRejectedSongTab()
    {
        if(!getCountOfRejectedSongFromTab().contains("(0")) {
            click(commonElements.getRejectedTabText(), "Rejected song tab", 5);
            return true;
        }
        return false;
    }

    public String getCountOfLiveSongFromTab()
    { scrollingToBottomofAPage();
        return getElementWhenPresent(commonElements.getLiveTabText()).getText().split(" ")[1];
    }
    public boolean clickLiveSongTab()
    {
       if(!getCountOfLiveSongFromTab().contains("(0")) {
           click(commonElements.getLiveTabText(), "Live song tab", 5);
           return true;
       }
        return false;
    }

    public static enum tabOption
    {
        ALL, REJECTED, LIVE, INREVIEW, DRAFT;
    }


    public boolean verifySongCountIsCorrect(tabOption option) {

        switch (option) {
            case ALL:
                if (!getCountOfAllSongFromTab().contains("(0"))
                {
                    clickAllSongTab();
                    return getCountOfAllSongFromTab().contains(getCountOfAllSongs());
                }
                else if (getCountOfAllSongFromTab().contains("(0")){
                    return true;
                }
            case LIVE:
                if (!getCountOfLiveSongFromTab().contains("(0")) {
                    clickDraftSongTab();
                    return getCountOfLiveSongFromTab().contains(getCountOfAllSongs());
                }
                else if (getCountOfLiveSongFromTab().contains("(0")){
                    return true;
                }
            case INREVIEW:
                if (!getCountOfinReviewSongFromTab().contains("(0")) {
                    clickinReviewSongTab();
                    return getCountOfinReviewSongFromTab().contains(getCountOfAllSongs());
                }
                else if (getCountOfinReviewSongFromTab().contains("(0")){
                    return true;
                }
            case REJECTED:
                if (!getCountOfRejectedSongFromTab().contains("(0")) {
                    clickRejectedSongTab();
                    return getCountOfRejectedSongFromTab().contains(getCountOfAllSongs());
                }
                else if (getCountOfRejectedSongFromTab().contains("(0")){
                    return true;
                }

            case DRAFT:
                if (!getCountOfDraftSongFromTab().contains("(0")) {
                    clickRejectedSongTab();
                    return getCountOfDraftSongFromTab().contains(getCountOfAllSongs());
                }
                else if (getCountOfDraftSongFromTab().contains("(0")){
                    return true;
                }
        }
        return false;
    }

    /*
    returns Name of song of first tile
     */
    public String clickFirstSongReleaseSummary()
    {
        String a=getWebElementsList(commonElements.getSongNameOnTilesTextList()).get(0).getText();
        if(getTotalSongs()>=1)
        getWebElementsList(commonElements.getCountSongsAllTab()).get(0).click();

        return a;
    }


}
