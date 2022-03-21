package in.wynk.PageElements;

import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class ProfessionPageElements {


    private By iAmAMusicArtistButton = By.xpath("//div[@class='rowstyled__Row-sc-1ws8a7a-0 style__StyledCol-sc-1p8xmcv-0 itJqLv fuVwEl']//button[1]");
    private By iAmPodcastCreatorButton = By.xpath("//div[@class='rowstyled__Row-sc-1ws8a7a-0 style__StyledCol-sc-1p8xmcv-0 itJqLv fuVwEl']//button[2]");
    private By iAmLabelAndAgencyButton = By.xpath("//div[@class='rowstyled__Row-sc-1ws8a7a-0 style__StyledCol-sc-1p8xmcv-0 itJqLv fuVwEl']//button[3]");
    private By wynkStudioBanner = By.xpath("//img[@alt='Wynk Studio Banner']");
    private By emailIdProfileDropdown = By.xpath("//header[@class='style__StyledMenuContainer-sc-1y5vx6r-1 fmHXUA']//div[1]");
    private By logoutProfileDropDown = By.xpath("//header[@class='style__StyledMenuContainer-sc-1y5vx6r-1 fmHXUA']//div[2]");
   private By profileButton =By.xpath("//div[@class='style__StyledInitials-sc-1y5vx6r-4 fzeafF ant-dropdown-trigger']");
}
