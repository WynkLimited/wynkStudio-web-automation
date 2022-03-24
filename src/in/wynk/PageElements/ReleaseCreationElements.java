package in.wynk.PageElements;

import com.amazonaws.services.cloudfront.model.PriceClass;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class ReleaseCreationElements {

    private By areYouAddingFreshRel = By.xpath("//h5[contains(text(),'Are you adding a fresh release?')]");
    private By yesRadioButton= By.xpath("//span[contains(text(),'Yes')]");
    private By noRadioButton = By.xpath( "//span[contains(text(),'No')]");
    private By continueButtonTellUsAboutYourRelease = By.xpath( "//span[contains(text(),'Continue')]");
    private By uploadAudioButton = By.xpath("//p[contains(text(),'Select audio file')]");
    private By releaseTitleTextBox =By.xpath("//div/input[@class='ant-input']");
    private By crossButtonPopUp =By.xpath("//span[@class='svgicon__IconWrapper-sc-gxfrqa-0 dzUXSY icon cursor_pointer']//*[name()='svg']");
    private By backdropModal = By.id("backdrop-modal");
    private By contactUsPopUp = By.xpath("//button/span[contains(text(),'Contact us')]");
    private By addReleaseDetailHeadingOnPage =By.xpath("//h3[contains(text(),'Add Release Details')]");
    private By backArrowAddReleaseDetailPage = By.xpath("//div[@class='arrow-link']");
    private By continueUploadReleasePage = By.xpath("//button//p[contains(text(),'Continue')]");
    private By errorMsgsAlertList = By.xpath("//div[contains(text(),'Required')]");
    private By artworkMissingAlert = By.xpath("//span[contains(text(),'Artwork is required to add new release!')]");
    private By generateCodeForMeList = By.xpath("//span[contains(text(),'No, generate it for me')]");
    private By uploadArtworkButton = By.xpath("//span[contains(text(),'Upload artwork')]");
    private By addYourReleaseDetailsHeader = By.xpath("//h4[contains(text(),'Add your release details')]");




}
