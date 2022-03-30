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
    private By uploadAudioButton = By.xpath("//p[@id='releaseAudio']/following-sibling::div//p[contains(text(),'Select audio file')]");
    private By textOfUploadedFileList = By.xpath("//p[@id='releaseAudio']/following-sibling::div//p");
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
    private By artworkHref = By.xpath("//div[contains(@class,'ImageContainer')]/img");
    private By smallerResolutionErrorMsgArtwork = By.xpath("//span[contains(text(),'Minimum resolution is 800 x 800')]");
    private By addYourReleaseDetailsHeader = By.xpath("//h4[contains(text(),'Add your release details')]");
    private By songProgressBar = By.xpath("//div[@class='ant-progress-bg']");
    private By playButton = By.xpath("//div[@class='ant-form-item-control-input-content']//button");
    private By crossButton =By.xpath("//span[contains(@class,'crossIcon')] ");
    private By yesForUPC = By.xpath("//span[contains(text(),'Yes, I have UPC')]");
    private By yesForISRC =By.xpath("//span[contains(text(),'Yes, I have ISRC')]");

    //###################### Second Page################################//

    private By primaryArtistRoleDropdown = By.xpath("//div[@id='primaryArtist']//div[contains(@class,'dropdown')]/div");
    private By musicProducerRole = By.xpath("//div[@class='ant-select-item-option-content']/div[contains(text(),'Music Producer')]");
    private By primaryArtistName = By.xpath("//input[@class='ant-input ant-input-disabled']");
    private By addSupportingArtistButton = By.xpath("//span[contains(text(),'Add supporting artist')]");
    private By supportingActorRoleDropDown = By.xpath("//div[@class='mt_2']//div[contains(@class,'dropdown')]/div");
    private By secondaryArtistSingerRoleList = By.xpath("//div[@class='ant-select-item-option-content']/div[contains(text(),'Singer')]");
    private By secondaryArtistName = By.xpath("//div[@class='mt_2']//div[contains(@class,'InputContainer')]//input");
    private By secondaryArtistCrossIcon = By.xpath("//div[@class='mt_2']//span[contains(@class,'crossIcon')]");
    private By languageDropDown = By.xpath("//div[@id='language']/div");
    private By hindiLanguage = By.xpath("//div[@class='ant-select-item-option-content'][contains(text(),'Hindi')]");
    private By genreDropDown = By.  xpath("//p[@id='genre']/following-sibling::div/div");
    private By genreSearchTextField = By.xpath("//p[@id='genre']/following-sibling::div//input");
    private By bachataGenre = By.xpath("//div[contains(text(),'Bachata')][@class='ant-select-item-option-content']");
    private By NativeAmericanGenre = By.xpath("//div[contains(text(),'Native american')][@class='ant-select-item-option-content']");
    private By genreCloseButtonList = By.xpath("//p[@id='genre']/following-sibling::div//div[@class='ant-select-selection-overflow-item']" +
            "//span[@aria-label='close']");
    private By uploadSongLyricsRadioButton = By.xpath("//p[contains(text(),'Upload sing along lyrics')]");
    private By uploadLyricsInLRC = By.xpath("//p[contains(text(),'Upload lyrics in .LRC format')]");
    private By uploadLyricTextRadioButton = By.xpath("//span[contains(text(),'Add lyrics text only')]");
    private By lyricsTextArea = By.xpath("//textarea[@placeholder='Paste the lyrics here']");
    private By removeLyrics = By.xpath("//span[normalize-space()='Remove']");
    private By isLrcFileSuccfullyUploaded = By.xpath("//span[contains(@class,'style__UploadedText')]" +
            "[contains(text(),'Successfully uploaded')]");
    private By explicitYesRadioButton = By.xpath("//p[@id='explicitContent']/following-sibling::div//span[contains(text(),'Yes')]");
    private By explicitNoRadioButton = By.xpath("//p[@id='explicitContent']/following-sibling::div//span[contains(text(),'No')]");
    private By previouslyReleasedYesRadioButton = By.xpath("//p[@id='previouslyReleased']/following-sibling::div//span[contains(text(),'Yes')]");
    private By previouslyReleasedNoRadioButton = By.xpath("//p[@id='previouslyReleased']/following-sibling::div//span[contains(text(),'No')]");
    private By textBoxWherePreviouslyReleaseList = By.xpath("//p[@id='previouslyReleased']/following-sibling::div/p/following-sibling::div//input");
    private By backArrowSecondPage = By.xpath("//div[@class='arrow-link']");
    private By addLRCFileViaBannerButton = By.xpath("//button[contains(@class,'ant-btn ant-btn-primary ant-btn-sm')]");
    private By invalidURLErrorPreviouslyRelease  = By.xpath("//div[@role='alert'][contains(text(),'valid URL')]");


    //###################### common page ##########################
    private By backButton = By.xpath("//p[contains(text(),'Back')]");
    private By continueButton = By.xpath("//p[contains(text(),'Continue')]");

    //###################### Third Page################################//

    private By htClipNameTextBox_1 = By.xpath("//div[@id='ht_0']//input");
    private By addMoreClip = By.xpath("//p[contains(text(),'Add more clips')]");
    private By htClipNameTextBox_2 = By.xpath("//div[@id='ht_1']//input");
    private By duplicateClipNameErrorMsg = By.xpath("//div[contains(text(),'Duplicate clipnames are not allowed')]");
    private By htHelpPopUp = By.xpath("//div[@id='modal-root']//h5");
    private By hereLink = By.xpath("//span[@class='modalText']");
    private By htBannerImage = By.xpath("//img[@alt='helloTunes']");
    private By htBannerText = By.xpath("//p[contains(text(),'*Wynk strongly believe to connect millions of list')]");
    private By crossIcononBanner = By.xpath("//span[contains(@class, 'cursor_pointer')]//*[name()='svg']");

    //################### Schedule your release #####################

    private By asSoonAsPossible = By.xpath("//p[contains(text(),'As soon as possible')]");

    //############################# Review Page##############################
    private By confirmAndSubmit = By.xpath("//button//p[contains(text(),'Confirm & Submit')]");
    private By releaseTitleList = By.xpath("//span[contains(text(),'Release title')]/following-sibling::h5/span");
    private By coverImage = By.xpath("//span[contains(text(),'Cover Image')]/following-sibling::div//img");
    private By audioFileName = By.xpath("//span[contains(text(),'Uploaded audio')]/following-sibling::h5/span");
    private By namePrimaryArtist = By.xpath("//span[contains(text(),'Primary artist')]/following-sibling::h5");
    private By primaryLanguage = By.xpath("//span[contains(text(),'Language')]/following-sibling::h5");
    private By primaryGenre = By.xpath("//span[contains(text(),'Genre')]/following-sibling::h5");
    private By lyrics = By.xpath("//span[contains(text(),'Lyrics')]/following-sibling::h5/span");
    private By explicitContent =By.xpath("//span[contains(text(),'Explicit Content')]/following-sibling::h5");
    private By htName1 = By.xpath("//span[contains(text(),'Clip 1')]/following-sibling::h5");
    private By htName2 = By.xpath("//span[normalize-space()='Clip 2']/following-sibling::h5");
    private By releaseUploadedEditButton = By.xpath("//h5[contains(text(),'Release uploaded')]/following-sibling::div/p");

















}
