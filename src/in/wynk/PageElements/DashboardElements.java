package in.wynk.PageElements;

import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class DashboardElements {

    private By artistDashBoardHeading = By.xpath("//h3[contains(text(),'Artist Dashboard')]");
    private By artistDashBoardHeading_2 = By.xpath("//h3[contains(text(),'Artist Dashboard')]");
    private By nameOfArtistDashboard = By.xpath("//h4[contains(@class,'PodcastNameContainer')]");
    private By musicRoleSDashBoard = By.xpath("//p[contains(@class,'MusicRolesContainer')]");
    private By viewOnWynkButton =By.xpath("//button/span[contains(text(),'View on Wynk')]");
    private By last7DaysDropButton = By.xpath("//button/strong[contains(text(),'Last 7 days')]");
    private By lastXDaysDropButton = By.xpath("//button/strong[contains(text(),'Last ')]");
    private By Last7DaysDatesTextDropDown = By.xpath("//button/strong[contains(text(),'Last 7 days')]/following-sibling::p");
    private By last7DayFilterInsideDropDown = By.xpath("//p[contains(text(),'Last 7 days')]");
    private By last30DayFilterInsideDropDown = By.xpath("//p[contains(text(),'Last 30 days')]");
    private By last90DayFilterInsideDropDown = By.xpath("//p[contains(text(),'Last 90 days')]");
    private By loader = By.xpath("//div[contains(@class,'PodcastStatsWrapper')]");
    private By downloadButton  = By.xpath("//button[contains(@class,'download')]");
    private By allTheTimeDownloadRadioButton = By.xpath("//span[contains(text(),'All the time')]");
    private By pickADateDownloadRadioButton = By.xpath("//span[contains(text(),'Pick a date')]");
    private By doneButton = By.xpath("//button/span[contains(text(),'Done')]");

    //This xpath has to be parameterized by month
    // //div[@class='rdrMonthName'][contains(text(),'Mar')]/following-sibling::div/button[@class='rdrDay']//span[@class='rdrDayNumber']
    private By dateSelectedList = By.xpath("//div[@class='rdrDateDisplay']//span/input");
    private By crossDownloadButton=By.xpath("//span[contains(@class,'StyledCrossIcon')]//*[name()='svg']");
    private By notificationMsgForSuccessfulDownload = By.xpath("//div[@class='ant-notification-notice-message'][contains(text(),'Your report is being downloaded')]");
    private By likesAndHTActivatesTextList = By.xpath("//div[contains(@class,'CompactContainer')]//span[not(contains(@class,'icon'))]") ;
    private By followerButton = By.xpath("//div[contains(@class,'PodcastStatsWrapper')]//p[contains(text(),'FOLLOWERS')]");
    private By uniqueListenersButton = By.xpath("//div[contains(@class,'PodcastStatsWrapper')]//p[contains(text(),'UNIQUE LISTENERS')]");
    private By totalStreamsButton  =By.xpath("//div[contains(@class,'PodcastStatsWrapper')]//p[contains(text(),'TOTAL STREAMS')]");
    private By totalNumberFollowerText = By.xpath("//div[contains(@class,'PodcastStatsWrapper')]//p[contains(text(),'FOLLOWERS')]/following-sibling::div//h3");
    private By totalNumberUniqueListenersText = By.xpath("//div[contains(@class,'PodcastStatsWrapper')]//p[contains(text(),'UNIQUE LISTENERS')]/following-sibling::div//h3");
    private By totalNumberTotalStreamsText = By.xpath("//div[contains(@class,'PodcastStatsWrapper')]//p[contains(text(),'TOTAL STREAMS')]/following-sibling::div//h3");
    private By singlesWithNumberText = By.xpath("//div[contains(@class,'ant-tabs-tab-btn') and contains(text(),'Singles')]");
    private By totalSongsCountPageWise = By.xpath("//tbody[@class='ant-table-tbody']//tr");
    private By totalHeader = By.xpath("//thead[@class='ant-table-thead']//tr/th");









}
