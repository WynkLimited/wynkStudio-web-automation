package in.wynk.pages;

import in.wynk.API.API;
import in.wynk.common.DriverActionUtils;
import in.wynk.framework.Reporting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SharePage  extends DriverActionUtils {
    API api;
    private By shareText = By.xpath("//span[text()='SHARE']");
    private By sharePopup = By.xpath("//div[@class='dropdown-share']");
    private By shareOptions= By.xpath("//div[@class='dropdown-share']");
    private By shareIcon = By.xpath("//button[contains(@class, ' justify-content-start align-items-cent')]//img");



    public SharePage(Reporting Reporter, in.wynk.framework.Assert Assert, in.wynk.framework.SoftAssert SoftAssert) {
        super(Reporter, Assert, SoftAssert);
        api = new API(Reporter, Assert, SoftAssert, sTestDetails);

    }

    public void VerifyShareButton() {
        sleep(3000);
        Assert.assertTrue(checkIfElementPresent(shareText, 0));
    }

    public void clickOnShareIcon() {
        getElementWhenClickable(shareText, 1000).click();
    }

    public void verifySharePopup(String shareOptions) {
//        List<String> contentsList = Arrays.asList(shareOptions.split("\\s*,\\s*"));
//        List<String> UIcontentList = new ArrayList<>();
//
//        List<WebElement> UIcontentsListElemeents = getWebElementsList(recentSearches);
//        for(WebElement element : UIcontentsListElemeents){
//            UIcontentList.add(element.getText().trim());
//        }
//        AssertionBetweenTwoList(contentsList , UIcontentList , contents);
        Assert.assertTrue(checkIfElementPresent(sharePopup, 0));

    }
}
