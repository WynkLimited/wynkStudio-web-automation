package in.wynk.pages;

import in.wynk.PageElements.PodcastHomeElements;
import in.wynk.framework.Reporting;
import in.wynk.common.Utils;
import in.wynk.common.DriverActionUtils;


public class PodcastHomePage extends DriverActionUtils{

    CommonPage commonPage;
    in.wynk.framework.Assert anAssert;
    PodcastHomeElements elements;
    String password = "Abcd@12345";
    Utils utility;
    String email = "karishmakoul2@gmail.com";
    CommonStudioPage commonStudioPage ;


    public PodcastHomePage(CommonPage commonPage, Reporting Reporter, in.wynk.framework.Assert Assert,
                           in.wynk.framework.SoftAssert SoftAssert, PodcastHomeElements elements, Utils utility, CommonStudioPage commonStudioPage) {

        super(Reporter, Assert, SoftAssert);
        this.commonPage = commonPage;
        this.anAssert = Assert;
        this.elements = elements;
        this.utility = utility;
        this.commonStudioPage = commonStudioPage;

    }

    public boolean isAddPodcastButtonArtistHomePagePresent()
    {
        return isElementDisplayed(elements.getAddPodcastButton(), "Add podcast button ",true);
    }

    public void clickStudioButtonArtistHomePagePresent()
    {
        click(elements.getAddPodcastButton(), "Home Button on ArtistHome ",true);
    }

    public boolean isStudioLogoButtonPodcastPagePresent()
    {
        return isElementDisplayed(elements.getPodcastLogoButton(), "Studio Logo Button Podcast Page",true);
    }

    public void clickStudioLogoButtonPodcastPage()
    {
        click(elements.getPodcastLogoButton(), "Studio Logo Button Podcast Page",true);
    }


    public boolean isDashboardButtonPodcastPagePresent()
    {
        return isElementDisplayed(elements.getPodcastLogoButton(), "Studio Logo Button Podcast Page",true);
    }

    public void clickDashboardButtonPodcastPage()
    {
        click(elements.getPodcastLogoButton(), "Studio Logo Button Podcast Page",true);
    }




}
