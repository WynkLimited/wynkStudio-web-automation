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
    private By fullNameTextBox = By.id("name");
    private By NationalityDropDown = By.id("rc_select_1");
    private By LanguageDropdown = By.id("rc_select_2");
    private By RolesDropdown = By.id("rc_select_3");
    private By EraDropdown = By.id("rc_select_4");
    private By GenreDropdown = By.id("rc_select_5");
    private By bioTextBox = By.id("Bio");
    private By iprsYes = By.xpath("//input[@class='ant-radio-input'][@value='1']");
    private By iprsNo = By.xpath("//input[@class='ant-radio-input'][@value='0']");
    private By instagramLink = By.id("INSTAGRAM");
    private By faceBookLink = By.id("FACEBOOK");
    private By continueButton = By.xpath("//button/span[contains(text(),'Continue')]");
    private By addPhotoPlusSign= By.xpath("//span[@aria-label='plus']");
    private By uploadImage1 = By.cssSelector(".style__AddPhoto-sc-9ylmvx-4");
    //private By uploadImage2 =By.cssSelector(".Styled__FlexContainer-sc-243yjz-7 > input");
    private By uploadImage2 =By.xpath("//input[@type='file']");
    private By imageUploadedOnPopUp =By.xpath("//div[@class='style__ImageContainer-sc-9ylmvx-2 cjjnno']/img[1]");
    private By imageUploadedOnAddArtistPage = By.xpath("//div[@class='Styled__FlexContainer-sc-243yjz-7 style__ProfileFlexContainer-sc-1fov93c-16 kfgLme oprMB']/img[1]");
    private By crossButtonOnUpdatePhotoTab = By.xpath("//img[@alt='close']");
    private By doneButton = By.xpath("//span[contains(text(),'Done')]");
    private By backButton = By.cssSelector(".style__FlexContainer-sc-k44phs-0 svg");
    private By headingCreateClaimArtistPage = By.xpath("//div[contains(text(),'Create or claim an artist profile')]");
    private By artistNameAlert = By.xpath("//div[contains(text(),'Artist name is required!')]");
    private By langAlert = By.xpath("//div[contains(text(),'Language is required!')]");
    private By artistBioAlert = By.xpath("//div[contains(text(),'Min character limit is 100')]");
    private By roleAlert = By.xpath("//div[contains(text(),'Role is required!')]");
    private By iprsAlert =By.xpath("//div[contains(text(),'This field is required!')]");












}
