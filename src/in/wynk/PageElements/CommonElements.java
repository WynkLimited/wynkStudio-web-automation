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
    private By inReviewReleasesummary = By.xpath("//p[contains(text(),'In-review')]");
    private By inDraft = By.xpath("//div[contains(@aria-controls,'IN_DRAFT')]");
    private By titleList = By.xpath("//h3[contains(@class,'Title')]");
    private By nameOfReleaseOnReleaseSummary = By.xpath("//span[@class='pl_1']");

}

