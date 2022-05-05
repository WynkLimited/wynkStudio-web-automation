package in.wynk.PageElements;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class CommonElements {

    private By allDraftLiveRejectDraftTabStudioPageHomePage = By.xpath("//div[@class='ant-tabs-nav-list']");
    private By profileButton = By.xpath("//div[contains(@class,'Initials')]");
    private By allSongsPodcastdivHomePage = By.xpath("//div[@class='infinite-scroll-component infinite-scroll-container']");
    private By allSongPodcastListHomePage = By.xpath("//div[@class='infinite-scroll-component infinite-scroll-container']/div");
    private By searchBoxStudioPage = By.xpath("//span[contains(@class,'input')]/following::input");
    private By inReviewReleasesummary = By.xpath("//p[contains(text(),'In-review')]");
    private By inDraft = By.xpath("//div[contains(@aria-controls,'IN_DRAFT')]");
    private By titleList = By.xpath("//h3[contains(@class,'Title')]");
    private By nameOfReleaseOnReleaseSummary = By.xpath("//span[@class='pl_1']");
    private By countSongsAllTab = By.xpath("//span[contains(text(),'song')]");
    private By statusOfSongDraft = By.xpath("//span[contains(@class,'status') and contains(text(),'Draft')]");
    private By statusOfSongRejected = By.xpath("//span[contains(@class,'status') and contains(text(),'Rejected')]");
    private By statusOfSongLive = By.xpath("//span[contains(@class,'status') and contains(text(),'Live')]");
    private By statusOfSongInReview = By.xpath("//span[contains(@class,'status') and contains(text(),'In Review')]");
    private By allTabText = By.xpath("//div[contains(@class,'ant-tabs-tab')]/div[contains(text(),'All')]");
    private By liveTabText = By.xpath("//div[contains(@class,'ant-tabs-tab')]/div[contains(text(),'Live')]");
    private By draftTabText = By.xpath("//div[contains(@class,'ant-tabs-tab')]/div[contains(text(),'Draft')]");
    private By inReviewTabText = By.xpath("//div[contains(@class,'ant-tabs-tab')]/div[contains(text(),'In-review')]");
    private By rejectedTabText = By.xpath("//div[contains(@class,'ant-tabs-tab')]/div[contains(text(),'Rejected')]");
    private By verificationPendingText = By.xpath("//div[contains(@class,'ant-tabs-tab')]/div[contains(text(),'Verification Pending')]");
    private By songNameOnTilesTextList = By.xpath("//div[@id='releases-container']//div/h3[not(contains(text(),'Studio Home'))]");
    private By crossSign = By.xpath("//span[contains(@class,' cursor_pointer')]//*[name()='svg']");
    private By noSearchResult = By.xpath("//div[contains(@class,'input-filled')]/div[@class=' search-suggestions-container']//p[contains(text(),'No Search Results')]");

}

