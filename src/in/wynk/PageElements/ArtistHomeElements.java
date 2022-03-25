package in.wynk.PageElements;

import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class ArtistHomeElements {

    private By newReleaseButtonArtistPage =By.xpath("//button[@class='ant-btn addReleaseBtn']") ;
    private By headingOnArtistPage = By.xpath("//div[@class='style__Info-sc-5fcp8g-2 gpSbwf']");
    private By homeButtonArtistPage = By.xpath("//a[@class='Logo__LogoCustom-sc-285esy-0 jIiSUX logo']");
    private By studioButtonArtistPage = By.xpath("//main[@id='app']//a[@href='/home']");
    private By dashboardButtonArtistPage = By.xpath("//main[@id='app']//a[@href='/home/dashboard/all']");
    private By royalties = By.xpath("/main[@id='app']//a[@href='/royalties']");
    private By dashboardButtonFromProfileDropDown = By.xpath("//div[@class='style__StyledMenuItemNonClickable-sc-1y5vx6r-2 kfbabJ']");
    private By logoutButtonFromProfileDropDown = By.xpath("//div[@class='style__StyledMenuItemClickable-sc-1y5vx6r-3 iVHunq']");
    private By profilePhotoOnWelcomeAboardBanner = By.xpath("//div[@id='modal-root']//img");
    private By goTOStudioHomeButton = By.xpath("//div[@id='modal-root']//span[contains(text(),'Go to Studio Home')]");
    private By nameOfArtistDashboard = By.xpath("//main[@id='app']//h4");
    private By roleOfArtistDashBoard = By.xpath("//main[@id='app']//p/span");

}
