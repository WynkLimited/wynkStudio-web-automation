package in.wynk.PageElements;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class CommonElements {

    private By allDraftLiveRejectDraftTabStudioPageHomePage = By.xpath("//div[@class='ant-tabs-nav-list']");
    private By profileButton = By.xpath("//div[@class='style__StyledInitials-sc-1y5vx6r-4 fzeafF ant-dropdown-trigger']");
    private By allSongsPodcastdivHomePage = By.xpath("//div[@class='infinite-scroll-component infinite-scroll-container']");
    private By allSongPodcastListHomePage = By.xpath("//div[@class='infinite-scroll-component infinite-scroll-container']/div");
    private By searchBoxStudioPage = By.xpath("//span[@class='ant-input-affix-wrapper']");


}
