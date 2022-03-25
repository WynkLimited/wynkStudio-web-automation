package in.wynk.PageElements;

import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class CreateArtistElements {

    private By typeYourNameTextBox = By.xpath("//input[@class='ant-input']");
    private By profileButton =By.xpath("//div[@class='style__StyledInitials-sc-1y5vx6r-4 fzeafF ant-dropdown-trigger ant-dropdown-open']");
    private By createNewProfileButton = By.xpath("//div[@class='style__DefaultProfilePic-sc-9itsm9-9 dCugyL']");
    private By listOfArtist = By.xpath("//div[@class='style__CardContainer-sc-9itsm9-5 fVvZfb']");
    private By noArtistFoundMsg = By.xpath("//p[@class='b1styled__B1-sc-msfk5a-0 kigPag mb_1 ml_3 mt_4']");
    private By uploadPhotoButton = By.xpath("//span[contains(text(),'Upload Photo')]");
    private By updatePhotoButton = By.xpath("//span[contains(text(),'Update Photo')]");
    private By fullNameTextBox = By.id("name");
    private By NationalityDropDown = By.id("rc_select_8");
    private By LanguageDropdown = By.xpath("//input[@class='ant-select-selection-search-input']");
    //private By LanguageDropdown = By.xpath("//div[@id='Languages']/div/div/div");
  // private By LanguageDropdown = By.xpath("//div[@id='Languages']//div[@class='ant-select-selection-overflow']");
   private By LanguageDropdown2  = By.xpath("//*[@id='Languages']/div/div[2]/div/div");
   private By hindiLang = By.xpath("//div[contains(text(),'Hindi')][@class='ant-select-item-option-content']");
   private By instrumentalist = By.xpath("//div[contains(text(),'Instrumentalist')][@class='ant-select-item-option-content']");
  //  private By hindiLang = By.cssSelector(".ant-select-item-option-active > .ant-select-item-option-content");
    private By RolesDropdown = By.id("rc_select_10");
    private By EraDropdown = By.id("rc_select_11");
    private By GenreDropdown = By.id("rc_select_12");
    private By bioTextBox = By.xpath("//div[@id='Bio']//textarea");
    private By iprsYes = By.xpath("//label[@class='ant-radio-wrapper']//span[contains(text(),'Yes')]");
    private By iprsNo = By.xpath("//label[@class='ant-radio-wrapper']//span[contains(text(),'No')]");
    private By instagramLink = By.id("INSTAGRAM");
    private By faceBookLink = By.id("FACEBOOK");
    private By continueButton = By.xpath("//button/span[contains(text(),'Continue')]");
    private By addPhotoPlusSign= By.xpath("//span[@aria-label='plus']");
    private By uploadImage1 = By.cssSelector(".style__AddPhoto-sc-9ylmvx-4");
    //private By uploadImage2 =By.cssSelector(".Styled__FlexContainer-sc-243yjz-7 > input");
    private By uploadImage2 =By.xpath("//input[@type='file']");
    private By imageUploadedOnPopUp =By.xpath("//div[@class='style__ImageContainer-sc-9ylmvx-2 cjjnno']/img[1]");
    private By imageUploadedOnAddArtistPage = By.xpath("//div[@class='Styled__FlexContainer-sc-243yjz-7 style__ProfileFlexContainer-sc-1fov93c-16 kfgLme oprMB']/img[1]");
    private By crossButtonOnUpdatePhotoTab = By.xpath("//div[@id='modal-container']//img[@alt='close']");
    private By doneButton = By.xpath("//span[contains(text(),'Done')]");
    private By backButton = By.cssSelector(".style__FlexContainer-sc-k44phs-0 svg");
    private By headingCreateClaimArtistPage = By.xpath("//div[contains(text(),'Create or claim an artist profile')]");
    private By artistNameAlert = By.xpath("//div[contains(text(),'Artist name is required!')]");
    private By langAlert = By.xpath("//div[contains(text(),'Language is required!')]");
    private By artistBioAlert = By.xpath("//div[contains(text(),'Min character limit is 100')]");
    private By roleAlert = By.xpath("//div[contains(text(),'Role is required!')]");
    private By iprsAlert =By.xpath("//div[contains(text(),'This field is required!')]");












}
