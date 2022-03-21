package in.wynk.pages;

import in.wynk.PageElements.CommonElements;
import in.wynk.PageElements.ArtistHomeElements;
import in.wynk.common.DriverActionUtils;
import in.wynk.framework.Driver;
import in.wynk.framework.Reporting;

public class ArtistHomePage extends DriverActionUtils {

    ArtistHomeElements artistHomeElements;
    CommonElements commonElements;

    public ArtistHomePage(Reporting Reporter, in.wynk.framework.Assert Assert,
                          in.wynk.framework.SoftAssert SoftAssert, ThreadLocal<Driver.HashMapNew> sTestDetails, ArtistHomeElements artistHomeElements,
                          CommonElements commonElements )
    {
        super(Reporter, Assert, SoftAssert, sTestDetails);
        this.artistHomeElements = artistHomeElements;
        this.commonElements = commonElements;
    }



    public boolean isNewReleaseButtonPresent()
    {
        return isElementDisplayed(artistHomeElements.getNewReleaseButtonArtistPage(), "New release button",true);
    }

    public boolean isHeadingOnStudioPage()
    {
        return isElementDisplayed(artistHomeElements.getHeadingOnArtistPage(), "Heading on artist home page",true);
    }

    public boolean isHomeButtonArtistHomePagePresent()
    {
        return isElementDisplayed(artistHomeElements.getHomeButtonArtistPage(), "Home Button on ArtistHome ",true);
    }

    public void clickHomeButtonArtistHomePagePresent()
    {
         click(artistHomeElements.getHomeButtonArtistPage(), "Home Button on ArtistHome ",true);
    }

    public boolean isStudioButtonArtistHomePagePresent()
    {
        return isElementDisplayed(artistHomeElements.getStudioButtonArtistPage(), "Home Button on ArtistHome ",true);
    }

    public void clickStudioButtonArtistHomePagePresent()
    {
        click(artistHomeElements.getStudioButtonArtistPage(), "Home Button on ArtistHome ",true);
    }

    public boolean isDashboardButtonArtistHomePagePresent()
    {
        return isElementDisplayed(artistHomeElements.getDashboardButtonArtistPage(), "Home Button on ArtistHome ",true);
    }

    public void clickDashboardButtonArtistHomePagePresent()
    {
        click(artistHomeElements.getDashboardButtonArtistPage(), "Home Button on ArtistHome ",true);
    }



}
