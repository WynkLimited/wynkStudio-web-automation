package in.wynk.pages;

import in.wynk.common.DriverActionUtils;
import in.wynk.framework.Driver;
import in.wynk.framework.Language;
import in.wynk.framework.Reporting;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class LanguagePage extends DriverActionUtils {

    CommonPage commonPage;
    private By languages = By.id("languages");
    private By submitButton = By.id("languages-submit-btn");
    private By preferredLanguageText = By.xpath("//h1");
    private By preferredLanguageDesc = By.xpath("//h2");
    private By languagesList = By.xpath("//span[@class='title']");
    private By selectedLang = By.xpath("//button[contains(@class,'active')]");

    public LanguagePage(CommonPage commonPage, Reporting Reporter, in.wynk.framework.Assert Assert, in.wynk.framework.SoftAssert SoftAssert) {
        super(Reporter, Assert, SoftAssert);
        this.commonPage = commonPage;

    }

    public List<String> getAllLanguagesFromUI() {
        List<String> listIfLang = new ArrayList<>();
        List<WebElement> webElements = getDriver().findElements(languages);
        for (WebElement webElement : webElements) {
            listIfLang.add(webElement.getText().trim());
        }
        return listIfLang;
    }


    public void clickOnSubmit() {
        click(submitButton, "Submit button");
    }

    public void verifyLanguagePage(String languages) {
        Assert.assertTrue(getText(preferredLanguageText).toLowerCase().trim().equals("Preferred Languages".toLowerCase().trim()));
        Assert.assertTrue(getText(preferredLanguageDesc).toLowerCase().trim().equals("Only content with the selected languages will be shown".toLowerCase().trim()));
        Assert.assertTrue(checkIfElementPresent(submitButton, 0));
        List<WebElement> ElementList = getWebElementsList(languagesList);
        List<String> languageList = new ArrayList<String>();
        for (WebElement element : ElementList) {
            languageList.add(element.getText());
        }
        Assert.assertTrue(getAllLanguagesFromUI().containsAll(Arrays.asList(languages.split("\\s*,\\s*")).stream().collect(Collectors.toSet())), languages + " not contains in language page JiraID");

    }

    public void selectLanguage(String Selectedlanguage) {

        List<WebElement> webElements = getDriver().findElements(selectedLang);
        if (webElements.size() == 1) {
            if (!Language.getLanguage(webElements.get(0).getAttribute("language")).getValue().equals(Selectedlanguage))
                click(By.xpath(".//span[text()='" + Selectedlanguage.trim() + "']"), Selectedlanguage);

        } else {
            for (int i = 0; i < webElements.size(); i++) {
                if (i == webElements.size() - 1) {
                    click(By.xpath(".//span[text()='" + Selectedlanguage.trim() + "']"), Selectedlanguage);
                }
                webElements.get(i).click();
            }
        }
    }

    public void selectedMaximumLanguage(int maxLangCount) {

        List<String> activeLang = getSelectedLang();
        int selectedLanguageCount = activeLang.size();
        List<WebElement> languageList = getDriver().findElements(languagesList);

        for (int i = 0; i < languageList.size(); i++) {
            if (!activeLang.contains(languageList.get(i).getText()) && selectedLanguageCount < maxLangCount) {
                languageList.get(i).click();
                selectedLanguageCount++;
            }

        }
    }


    public void VerifyMinimumLanguage(int min) {
        List<WebElement> webElements = getDriver().findElements(selectedLang);
        for (int i = 1; i <= webElements.size(); i++) {
            if (webElements.size() - i == 0) {
                click(webElements.get(i - 1), "");
                checkAlert("accept");
            } else {
                click(webElements.get(i - 1), "");
            }
        }
    }

    public void checkAlert(String sAction) {
        WebDriverWait wait = new WebDriverWait(this.getDriver(), 1L);
        long implicitWait = Long.valueOf(((Driver.HashMapNew) this.sTestDetails.get()).get("implicitWait")) / 1000L;

        try {
            this.getDriver().manage().timeouts().implicitlyWait(1L, TimeUnit.SECONDS);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = this.getDriver().switchTo().alert();
            Assert.assertTrue(alert.getText().toLowerCase().equals("Please select atleast 1 language to confirm".toLowerCase()));
            System.out.println("*******" + alert.getText());
            if (sAction.equalsIgnoreCase("accept")) {
                alert.accept();
            } else if (sAction.equalsIgnoreCase("decline")) {
                alert.dismiss();
            }
        } catch (Exception var9) {
            //  log.info("Threw a Exception in BaseUtil::checkAlert, full stack trace follows:", var9);
        } finally {
            this.getDriver().manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
        }

    }

    public void VerifySelectedLanguage(String language) {
        List<WebElement> webElements = getDriver().findElements(selectedLang);
        List<String> selectedLang = new ArrayList<>();
        for (WebElement element : webElements) {
            selectedLang.add(Language.getLanguage(element.getAttribute("language")).getValue());
        }
        Assert.assertTrue(selectedLang.contains(language));

    }


    public List<String> getSelectedLang() {
        List<WebElement> webElements = getDriver().findElements(selectedLang);
        List<String> selectedLang = new ArrayList<>();
        for (WebElement element : webElements) {
            selectedLang.add(Language.getLanguage(element.getAttribute("language")).getValue());
        }
        return selectedLang;
    }

    public void updateLanguage() {

        click(submitButton, "submit  btn");
    }

    public void setDefaultLanguage(String defaultLanguage) {
        List<WebElement> webElements = getDriver().findElements(selectedLang);
        if (webElements.size() == 1) {
            List<WebElement> langList = getDriver().findElements(languagesList);
            selectedMaximumLanguage(langList.size());
        }
        webElements = getDriver().findElements(selectedLang);
        for (int i = 0; i < webElements.size(); i++) {

            if (!Language.getLanguage(webElements.get(i).getAttribute("language")).getValue().equals(defaultLanguage)) {
                webElements.get(i).click();
            }
        }

    }

    public void unSelectLanguage(String lang) {
        click(By.xpath(".//span[text()='" + lang.trim() + "']"), lang);
    }
}
