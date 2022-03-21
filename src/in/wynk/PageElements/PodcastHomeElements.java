package in.wynk.PageElements;
import lombok.Getter;
import org.openqa.selenium.By;




@Getter
public class PodcastHomeElements {

    private By addPodcastButton = By.xpath("//button[@class='ant-btn secondary add']");
    private By podcastLogoButton = By.xpath("//a[@class='Logo__LogoCustom-sc-285esy-0 btIYsX logo']");
    private By podcastHomeButton = By.xpath("//a[@class='style__Item-sc-a0bkzg-1 dJpzky active-menu']");
    private By podcastDashboard = By.xpath("//a[@class='style__Item-sc-a0bkzg-1 dJpzky']");
    private By dashboardbutfromPodcastProfiledropdown = By.xpath("//div[@class='style__StyledMenuItemNonClickable-sc-1y5vx6r-2 jshEop']");
    private By logoutButtonFromPodcastProfileDropDown = By.xpath("//div[@class='style__StyledMenuItemClickable-sc-1y5vx6r-3 chMCTJ']");


}
