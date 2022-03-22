package in.wynk.pages;

import in.wynk.PageElements.ClaimArtistPagesElements;
import in.wynk.common.DriverActionUtils;
import in.wynk.framework.Assert;
import in.wynk.framework.Reporting;
import in.wynk.framework.SoftAssert;

public class ClaimArtistPage extends DriverActionUtils {

    ClaimArtistPagesElements claimArtistPagesElements;

    public ClaimArtistPage(Reporting Reporter, in.wynk.framework.Assert Assert,
                           in.wynk.framework.SoftAssert SoftAssert, ClaimArtistPagesElements claimArtistPagesElements) {
        super(Reporter, Assert, SoftAssert);

        this.claimArtistPagesElements= claimArtistPagesElements;

    }

    public boolean isClaimArtistButtonPresent()
    {
       return isElementDisplayed(claimArtistPagesElements.getClaimThisPrfileButton(),"claim this profile button",true);
    }

    public boolean isCreateNewProfileButtonPresent()
    {
        return isElementDisplayed(claimArtistPagesElements.getCreateNewProfileButton(),"create new profile button",true);
    }

    public boolean isViewProfileOnWynkPresent()
    {
        return isElementDisplayed(claimArtistPagesElements.getViewProfileOnWynkButton(),"view profile on wynk button",true);
    }


}
