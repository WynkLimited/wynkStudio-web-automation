package in.wynk.PageElements;


import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class ClaimArtistPagesElements {

    By claimThisPrfileButton = By.xpath("//span[contains(text(), 'Claim this profile')]");
    By createNewProfileButton = By.xpath("//span[contains(text(), 'Create New Profile')]");
    By viewProfileOnWynkButton = By.xpath("//span[contains(text(), 'View profile on Wynk')]");
    By backArrow = By.xpath("//span[@class='svgicon__IconWrapper-sc-gxfrqa-0 hzltSc icon arrowIcon']");
}
