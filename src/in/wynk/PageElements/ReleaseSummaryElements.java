package in.wynk.PageElements;

import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class ReleaseSummaryElements {

    private By uniquesListens = By.xpath("//p[contains(text(),'UNIQUE LISTENERS')]/ancestor::div[contains(@class,'heading')]/following-sibling::div//h3");
    private By TotalStreams = By.xpath("//p[contains(text(),'TOTAL STREAMS')]/ancestor::div[contains(@class,'heading')]/following-sibling::div//h3");
    private By helloTunesActivated = By.xpath("//p[contains(text(),'HELLOTUNES ACTIVATED')]/ancestor::div[contains(@class,'heading')]/following-sibling::div//h3");
    private By Likes = By.xpath("//p[contains(text(),'LIKES')]/ancestor::div[contains(@class,'heading')]/following-sibling::div//h3");
    private By totalStreamGraphPerRowList = By.xpath("//p[contains(text(),'Total')]/following-sibling::canvas");
    private By uniqueListenersGraphPerRowList = By.xpath( "//p[contains(text(),'Unique')]/following-sibling::canvas");
    private By lastUpdatedOn = By.xpath("//div[@class='mt_3'][contains(text(),'Last updated ')]");
    private By liveText = By.xpath("//p[contains(text(),'Live')]");
    private By draftText = By.xpath("//p[contains(text(),'Draft')]");
    private By inReviewText = By.xpath("//p[contains(text(),'In-review')]");
    private By rejectedText = By.xpath("//p[contains(text(),'Rejected')]");
    private By nameOfSong = By.xpath("//span[@class='pl_1']");
    private By backArrow= By.xpath("//a[@class='mr_6']");
    private By listenOnWynkButton = By.xpath("//div[@class='mt_3'][contains(text(),'Last updated ')]/following-sibling::div/button");
    private By continueFromWhereYouLeft = By.xpath("//div[@class='draft-container']/span[contains(text(),'Continue where you left from..')]");
    private By statusBarOfIncompleteRelease = By.xpath("//div[@class='draft-container']//div[contains(@class,'MuiStepper-horizontal')]");
    private By continueButtonDraftState = By.xpath("//div[@class='draft-container']/span[contains(text(),'Continue where you left from..')]" +
            "/following-sibling::button/span[contains(text(), 'Continue')]");
    private By songRejectionText = By.xpath("//div[@class='mt_3'][contains(text(),'Your song has been rejected by Wynk moderation team.')]");
    private By needHelpButtonRejectionState = By.xpath("//button/span[contains(text(),'Need help?')]");
    private By newReleaseInReviewState = By.xpath("//button/p[contains(text(),'New release')]");
    private By inReviewStateText = By.xpath("//div[@class='mt_3']/div");
    private By textOnFilter = By.xpath("//button/strong[contains(text(),'Last ')]/following-sibling::p");
    private By uniqueListensTrendsGraph = By.xpath("//div[contains(@id,'Unique Listeners')]//h3");
    private By totalStreamsTrendsGraph = By.xpath("//div[contains(@id,'Total Streams')]//h3");
    private By likeTrendsGraph = By.xpath("//div[contains(@id,'Likes')]//h3");
    private By htActivatedTrendsGraph = By.xpath("//div[contains(@id,'Hellotunes Activated')]//h3");
    private By graphTrendPage = By.xpath("//canvas[@role='img']");
    private By crossIconTrendPopUp = By.xpath("//span[contains(@class,'Cross')]//*[name()='svg']");
    private By rowWiseGraphList = By.xpath("//div[contains(@class,'ChartContainer')]//canvas[1]");
    private By totalHeader = By.xpath("//thead[@class='ant-table-thead']//tr/th");
    private By totalSongsCountPageWise = By.xpath("//tbody[@class='ant-table-tbody']//tr");

    private By playButton = By.xpath("//button[contains(@class,'square-icon-btn')]");
    private By songTitleOnWynkList = By.xpath("//h1[@class='heading1']");



}
