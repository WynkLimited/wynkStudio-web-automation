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

    public void clickNewReleaseButton()
    {
         click(artistHomeElements.getNewReleaseButtonArtistPage(), "New release button",true, 10);
    }

    public boolean isHeadingOnStudioPage()
    {
        return isElementDisplayed(artistHomeElements.getHeadingOnArtistPage(), "Heading on artist home page",true);
    }

    public boolean isHomeButtonArtistHomePagePresent()
    {
        return isElementDisplayed(artistHomeElements.getHomeButtonArtistPage(), "Home Button on ArtistHome ",true);
    }

    public void clickHomeButtonArtistHomePage()
    {
         click(artistHomeElements.getHomeButtonArtistPage(), "Home Button on ArtistHome ",true);
    }

    public boolean isStudioButtonArtistHomePagePresent()
    {
        return isElementDisplayed(artistHomeElements.getStudioButtonArtistPage(), "Home Button on ArtistHome ",true);
    }

    public void clickStudioButtonArtistHomePage()
    {
        click(artistHomeElements.getStudioButtonArtistPage(), "Home Button on ArtistHome ",true);
    }

    public boolean isDashboardButtonArtistHomePagePresent()
    {
        return isElementDisplayed(artistHomeElements.getDashboardButtonArtistPage(), "Home Button on ArtistHome ",true);
    }

    public void clickDashboardButtonArtistHomePage()
    {
        click(artistHomeElements.getDashboardButtonArtistPage(), "Home Button on ArtistHome ",true);
    }

    public void clickGoToStudioHomeButtonOnWelcomeBanner()
    {
        click(artistHomeElements.getGoTOStudioHomeButton(), "Go To Studio Home Button On Welcome Banner",true);
    }

    public boolean isGoToStudioHomeButtonOnWelcomePresent()
    {
      return  isElementDisplayed(artistHomeElements.getGoTOStudioHomeButton(), "Go To Studio Home Button On Welcome Banner",true);
    }

    public String getUrlProfilePicOnWelcomeBanner()
    {
      return  getDriver().findElement(artistHomeElements.getProfilePhotoOnWelcomeAboardBanner()).getAttribute("src");
    }


    public String getNameOfArtistDashBoard()
    {
        System.out.println(getDriver().findElement(artistHomeElements.getNameOfArtistDashboard()).getText());
        return getDriver().findElement(artistHomeElements.getNameOfArtistDashboard()).getText();
    }

    public String getRoleOfArtistDashBoard()
    {
        System.out.println(getDriver().findElement(artistHomeElements.getRoleOfArtistDashBoard()).getText());
        return getDriver().findElement(artistHomeElements.getRoleOfArtistDashBoard()).getText();
    }


}
