package in.wynk.PageElements;

import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class ProfessionPageElements {


    private By iAmAMusicArtistButton = By.xpath("//button//h4[contains(text(),'music')]");
    private By iAmPodcastCreatorButton = By.xpath("//button//h4[contains(text(),'podcast')]");
    private By iAmLabelAndAgencyButton = By.xpath("//button//h4[contains(text(),'Label')]");
    private By wynkStudioBanner = By.xpath("//img[@alt='Wynk Studio Banner']");
    private By emailIdProfileDropdown = By.xpath("//header[contains(@class,'StyledMenuContainer')]/div[contains(@class,'MenuItemNonClickable')]");
    private By logoutProfileDropDown = By.xpath("//header[contains(@class,'StyledMenuContainer')]/div[contains(@class,'MenuItemClickable')]//span[contains(text(),'Log Out')]");
   private By profileButton =By.xpath("//header//div[contains(text(),*)][contains(@class,'ant-dropdown-trigger')]");
}
