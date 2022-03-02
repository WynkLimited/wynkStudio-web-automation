package in.wynk.pages;

import in.wynk.common.DriverActionUtils;
import in.wynk.framework.Assert;
import in.wynk.framework.DriverFactory;
import in.wynk.framework.Reporting;
import in.wynk.framework.SoftAssert;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.text.ParseException;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class PlayerPage extends DriverActionUtils {

    CommonPage commonPage;

    public PlayerPage(Reporting Reporter, Assert Assert, SoftAssert SoftAssert, CommonPage commonPage) {
        super(Reporter, Assert, SoftAssert);
        this.commonPage = commonPage;
    }
    private By volume_Btn = By.id("volume-bar");
    private By PIP_btn = By.xpath("//button[@class='pip-button']");
    private By volume_Btn_state = By.id("volume-controller");
    private By toggle_full_screen_btn = By.xpath("//button[contains(@class,'btm-control-fullscreen')]");
    public By seekbar_current_time_label = By.xpath("//div[contains(@class, 'text-left')]//span");
    private By seekbar_end_time_label = By.xpath("//div[contains(@class, 'text-right')]//span");
    private By backward_btn = By.xpath("//div[@class=' btm-control-space-manager'][1]");
    private By forward_btn = By.xpath("//div[@class=' btm-control-space-manager'][2]");
    private By play_pause_btn = By.xpath("//button[contains (@class , 'button button-icon button-icon-pp')]");
    private By play_pause_bottom_controls = By.id("player-play-btn");
    private By seek_Bar = By.xpath("//div[@id='seekbar']");
    private By seek_Bar_hover_time = By.xpath("//div[@class='hover-time']");
    private By player_content_Label = By.xpath("//div[@class='current-vdo__title']//h1");
    private By seekbar_Thumb = By.xpath("//div[@class='thumb']");
    private By seekbarMainLine = By.xpath("//div[@class='main']");
    private By seekbar_Buffer = By.xpath("//div[@class='buffered']");
    private By setting_btn = By.xpath("//button[contains(@class,'btm-control-setting')]");
    private By setting_Quality_option = By.id("player-quality-settings");

    private By quality_Resolution_Options = By.id("player-quality-resolution-options");
    private By scheduleRailContentName = By.xpath("//div[@class ='channelSchedule_holder']//div//p[@class ='schedule_episod_name']");
    private By scheduleRailContentTime = By.xpath("//div[@class ='channelSchedule_holder']//div//p[@class ='schedule_episod_name']/following-sibling::p");
    //Live Tv Player
    private By liveTVChannelGenre = By.xpath("//li[@class='lang-list']");
    private By live_TV_Label = By.xpath(".//span[@class='text text-12']");
    private By currentContentNameFromContentInfoIndex = By.xpath("//div[@class='current-vdo__title']//h1");
    private By contentLanguageFromContentInfoIndex = By.xpath("//ul[@class=\"current-vdo__details year-language mt-1\"]//a");
    private By episodeNameFromContentInfoIndex = By.xpath("//ul[@class=\"current-vdo__details mt-1\"]//li");
    private By liveTVChannelName  =  By.xpath("//h2[@class=\"current-vdo__heading mt-3\"]");
    private By playerContentGenre =By.xpath("//ul[@class=\"current-vdo__details\"]//a[@class=\"link-theme-secondary\"]");
    //BreadCrumbs
    private By homeBreadcrumb = By.xpath("//li[@id='home-clickable-bradcrumbs'][1]//a");
    private By contentTypeBreadcrumb = By.xpath("//li[@id='home-clickable-bradcrumbs'][2]//a");
    private By langContentTypeBreadcrumb = By.xpath("//li[@id='home-clickable-bradcrumbs'][3]//a");




    public String get_LiveTV_Button_Text() {
        return getText(live_TV_Label);
    }

    public void launchURI(String contentType, String contentName, String contentID, String flag) {
        launchUrl(DriverFactory.getTestDetails().get("APP_URL").trim() + "/" + contentType + "/" + contentName.trim() + "/" + contentID.trim(), Boolean.valueOf(flag));
    }

    public boolean is_Volume_Btn_Visible() {
        commonPage.mouse_Hover(volume_Btn, true);
        return isElementDisplayed(volume_Btn, "volumeButton_ON_FullScreen", true);
    }

    public void click_On_volume_button() {
        commonPage.mouse_Hover(volume_Btn, true);
        click(volume_Btn, "Volume Button");
    }

    public String verify_volume_state() {
        commonPage.mouse_Hover(volume_Btn, true);
        if (getAttribute(volume_Btn_state, "aria-valuenow").trim().equals("0")) {
            return "mute";
        } else {
            return "voluble";
        }
    }

    public boolean is_Full_Screen_Visible() {
        commonPage.mouse_Hover(toggle_full_screen_btn, true);
        return isElementDisplayed(toggle_full_screen_btn, "full_screen_button", true);
    }


    public boolean is_Current_Time_Label_Visible() {
        commonPage.mouse_Hover(seekbar_current_time_label, true);
        return isElementDisplayed(seekbar_current_time_label, "current_time_label", true);
    }

    public boolean is_End_Time_Label_Visible() {
        commonPage.mouse_Hover(seekbar_end_time_label, true);
        return isElementDisplayed(seekbar_end_time_label, "end_time_label", true);
    }

    public boolean is_Setting_Btn_Visible() {
        commonPage.mouse_Hover(setting_btn, true);
        return isElementDisplayed(setting_btn, "setting button", true);
    }

    public boolean is_Qality_Option_Visible_In_Setting() {
        commonPage.mouse_Hover(setting_Quality_option, true);
        return isElementDisplayed(setting_Quality_option, "setting Quality Option", true);
    }

    public void select_Quality_Option_In_Setting() {
        commonPage.mouse_Hover(setting_Quality_option, true);
        click(setting_Quality_option, "Quality Option");
    }

    public String get_Quality_Option() {
        String resolution = getDriver().findElements(quality_Resolution_Options).get(0).getText();
        int size = getDriver().findElements(quality_Resolution_Options).size();
        for (int i = 1; i <= size - 1; i++) {
            resolution = resolution + "," + getDriver().findElements(quality_Resolution_Options).get(i).getText();
        }
        return resolution;
    }


    public void click_on_Setting_Btn() {
        commonPage.mouse_Hover(setting_btn, true);
        click(setting_btn, "setting button");
    }

    public boolean is_Backward_Btn_Visible() {
        commonPage.mouse_Hover(backward_btn, true);
        return isElementDisplayed(backward_btn, "backward_button", true);
    }

    public boolean is_Forward_Btn_Visible() {
        commonPage.mouse_Hover(forward_btn, true);
        return isElementDisplayed(forward_btn, "forward_button", true);
    }

    public boolean is_SeekBar_Visible() {
        commonPage.mouse_Hover(seek_Bar, true);
        return isElementDisplayed(seek_Bar, "setting button", true);
    }

    public boolean is_SeekBar_Hover_Time_Visible() {
        commonPage.mouse_Hover(seek_Bar_hover_time, true);
        return isElementDisplayed(seek_Bar_hover_time, "seek_Bar_hover_time", true);
    }

    // this label visible when play in full screen
    public boolean is_Player_Content_Label_Visible() {
        commonPage.mouse_Hover(player_content_Label, true);
        return isElementDisplayed(player_content_Label, "player_content_Label", true);
    }

    public void click_on_play_pause_button() {
        sleep(1000);
        commonPage.mouse_Hover(play_pause_btn, true);
        click(play_pause_btn, "play_pause_button");
    }


    public void click_on_play_pause_bottom_control_button() {
        commonPage.mouse_Hover(play_pause_bottom_controls, true);
        click(play_pause_bottom_controls, "play_pause_button");
    }


    public boolean verify_streaming_is_working() {
        String currentTime = get_player_Thumb_Current_Time();
        System.out.println("get time" + currentTime);
        sleep(2000);
        String TimeafterTwoSec = get_player_Thumb_Current_Time();
        if (currentTime.equals(TimeafterTwoSec)) {
            System.out.println("return false");
            return false;
        } else {
            System.out.println("return true");
            return true;
        }
    }


    public void click_On_BackWard_Button() {
//        commonPage.mouse_Hover(backward_btn,true);
//        click(backward_btn, "backwordButton");
        commonPage.mouse_Hover_click(backward_btn);
    }

    public void click_On_Forward_Button() {
        commonPage.mouse_Hover(forward_btn, true);
        click(forward_btn, "forward button");
    }


    public void click_On_full_screen_Button() {
        new Actions(getDriver()).moveToElement(getDriver().findElement(toggle_full_screen_btn)).click().perform();
    }

    public String get_player_Thumb_Current_Time() {
        commonPage.mouse_Hover(seekbar_current_time_label, true);
        return getText(seekbar_current_time_label);
    }

    public String get_player_Thumb_End_Time() {
        commonPage.mouse_Hover(seekbar_end_time_label, true);
        return getText(seekbar_end_time_label);
    }

    public void set_SeekBar_Thumb_In_Start() {
        sleep(2000);

        while (commonPage.time_to_milliSec(get_player_Thumb_Current_Time()) > (commonPage.time_to_milliSec(get_player_Thumb_End_Time()) * 96) / 100) {
            click_ON_LeftArrow_respect_of_seekBar();
        }

        while (commonPage.time_to_milliSec(get_player_Thumb_Current_Time()) < (commonPage.time_to_milliSec(get_player_Thumb_End_Time()) * 4) / 100) {
            click_ON_RightArrow_respect_of_seekBar();
        }

        new Actions(getDriver())
                .clickAndHold(getDriver().findElement(seekbar_Thumb))
                .moveToElement(getDriver().findElement(seekbar_current_time_label))
                .release().perform();

    }

    public void set_SeekBar_Thumb_In_Start_For_LiveTV() {
        sleep(2000);
        click_ON_LeftArrow_respect_of_seekBar();
        click_ON_LeftArrow_respect_of_seekBar();
        new Actions(getDriver())
                .clickAndHold(getDriver().findElement(seekbar_Thumb))
                .moveToElement(getDriver().findElement(seekbar_current_time_label))
                .release().perform();

    }


    public void click_ON_LeftArrow_respect_of_seekBar() {
        commonPage.mouse_Hover(seek_Bar, false);
        getDriver().findElement(seek_Bar).sendKeys(Keys.ARROW_LEFT);
    }

    public void click_ON_RightArrow_respect_of_seekBar() {
        commonPage.mouse_Hover(seek_Bar, false);
        getDriver().findElement(seek_Bar).sendKeys(Keys.ARROW_RIGHT);

    }

    public void set_SeekBar_Thumb_In_End() throws ParseException {
        sleep(2000);

        while (commonPage.time_to_milliSec(get_player_Thumb_Current_Time()) > (commonPage.time_to_milliSec(get_player_Thumb_End_Time()) * 96) / 100) {
            click_ON_LeftArrow_respect_of_seekBar();
        }

        while (commonPage.time_to_milliSec(get_player_Thumb_Current_Time()) < (commonPage.time_to_milliSec(get_player_Thumb_End_Time()) * 4) / 100) {
            click_ON_RightArrow_respect_of_seekBar();
        }

        new Actions(getDriver())
                .moveToElement(getDriver().findElement(seekbar_Thumb))
                .pause(Duration.ofSeconds(1))
                .clickAndHold(getDriver().findElement(seekbar_Thumb))
                .pause(Duration.ofSeconds(1))
                .moveByOffset(1, 0)
                .moveToElement(getDriver().findElement(seekbar_end_time_label))
                .moveByOffset(1, 0)
                .pause(Duration.ofSeconds(1))
                .release().perform();

    }

    public void set_SeekBar_Thumb_In_End_for_TV() {
        sleep(2000);
        new Actions(getDriver())
                .moveToElement(getDriver().findElement(seekbar_Thumb))
                .pause(Duration.ofSeconds(1))
                .clickAndHold(getDriver().findElement(seekbar_Thumb))
                .pause(Duration.ofSeconds(1))
                .moveByOffset(1, 0)
                .moveToElement(getDriver().findElement(live_TV_Label))
                .moveByOffset(1, 0)
                .pause(Duration.ofSeconds(1))
                .release().perform();

    }

    public int verify_forward_button_functionality() throws ParseException {
        commonPage.mouse_Hover(seekbar_current_time_label, false);
        String currentTime = getText(seekbar_current_time_label);
        System.out.println(currentTime);
        click_On_Forward_Button();

        commonPage.mouse_Hover(seekbar_current_time_label, false);
        String currentTime2 = getText(seekbar_current_time_label);
        System.out.println(currentTime2);
        return commonPage.get_Time_diff_in_Sec(currentTime, currentTime2);
    }

    public String verify_Connect_Buffering(int waitingTime, int apiCounter, int HitTimeGap) {
        String bufferSize = StringUtils.substringBetween(getAttribute(seekbar_Buffer, "style"), "X(", ");");
        long current_Time = System.currentTimeMillis();
        long endtime = current_Time + waitingTime;
        int count = 0;

        while (System.currentTimeMillis() < endtime && count != apiCounter) {
            sleep(HitTimeGap);
            commonPage.mouse_Hover(seekbar_Buffer, false);
            bufferSize = bufferSize + "," + Double.parseDouble(StringUtils.substringBetween(getAttribute(seekbar_Buffer, "style"), "X(", ");"));
            count++;
        }
        System.out.println("bufferSize  " + bufferSize);
        return bufferSize;
    }

    public String verify_Connect_Buffering_pause(int waitingTime, int apiCounter) {
        String bufferSize = StringUtils.substringBetween(getAttribute(seekbar_Buffer, "style"), "X(", ");");
        long endtime = System.currentTimeMillis() + waitingTime;
        int count = 0;

        while (System.currentTimeMillis() < endtime && count != apiCounter) {
            click_ON_RightArrow_respect_of_seekBar();
            sleep(500);
            commonPage.mouse_Hover(seekbar_Buffer, false);
            bufferSize = bufferSize + "," + Double.parseDouble(StringUtils.substringBetween(getAttribute(seekbar_Buffer, "style"), "X(", ");"));
            count++;
        }
        System.out.println("bufferSize  " + bufferSize);
        return bufferSize;
    }


    public int verify_backward_button_functionality() throws ParseException {
        //  String currentTime = get_player_Thumb_Current_Time();
        commonPage.mouse_Hover(seekbar_current_time_label, false);
        String currentTime = getText(seekbar_current_time_label);
        click_On_BackWard_Button();
        //    String currentTime2 = get_player_Thumb_Current_Time();
        commonPage.mouse_Hover(seekbar_current_time_label, false);
        String currentTime2 = getText(seekbar_current_time_label);
        return commonPage.get_Time_diff_in_Sec(currentTime, currentTime2);
    }

    /**
     * This Method Get Content From "Player Page  Live TV Channel schedule rail cards"
     *
     * @return set of Content Name And Time Separated With pipe |
     */
    public Set<String> getLiveTVChannelScheduleRailContentNameAndTime() {
        Set<String> contentNameAndTime = new HashSet<>();
        int count = 1 ;
        List<WebElement> scheduleRailCardName = getDriver().findElements(scheduleRailContentName);
        List<WebElement> scheduleRailCardTime = getDriver().findElements(scheduleRailContentTime);
        sleep(2000);
        int size =scheduleRailCardName.size()-1;
        for (int i = 0 ; i<=size;i++) {
            String contentName = scheduleRailCardName.get(i).getText();
            String contentTime = scheduleRailCardTime.get(i).getText();
            contentNameAndTime.add(contentName + " - " + contentTime);
            System.out.println(contentName + " - " + contentTime);
        }
        return contentNameAndTime;
    }

    public WebElement getLiveChannelCardRedBorderLine(String cardContentName) {
        return   getDriver().findElement(By.xpath(".//p[contains(text(),\""+cardContentName+"\")]/ancestor::div[@class=\"channelSchedule_wrapper schedule_episod_live\"]"));
    }

    public WebElement getLiveChannelCardRedIndicator(String cardContentName) {
        return   getDriver().findElement(By.xpath(".//p[contains(text(),\""+cardContentName+"\")]//preceding::p//picture"));
    }

    public String getCurrentContentTitleFromIndex()
    {
        return getText(currentContentNameFromContentInfoIndex);
    }

    public String getLivetvGenreType() {
        String[] genre = getText(liveTVChannelGenre).split(" ");
        return genre[2];
    }

    public String getCurrentContentLanguageFromIndex() {
        return getText(contentLanguageFromContentInfoIndex);
    }
    public String getCurrentContentEpisodeNameFromIndex() {
        return getText(episodeNameFromContentInfoIndex);
    }

    public String getLiveTVChannelName()
    {
        return getText(liveTVChannelName);
    }
    public String getPlayerContentGenre()
    {
        return getText(playerContentGenre);
    }

    public void clickOnPIPButton() {
        commonPage.mouse_Hover(PIP_btn, true);
        click(PIP_btn, "pip Button");
    }

    public void clickOnPlayPausePlayerControlBtn() {
        commonPage.mouse_Hover(play_pause_bottom_controls, true);
        click(play_pause_bottom_controls, "play-pause-player-controller-btn");
    }


    public void clickOnHomeBreadcrumb() {
        click(homeBreadcrumb, "home  BreadCrumb");
    }
        public void clickOnContentTypeBreadcrumb() {
        click(contentTypeBreadcrumb, "content type  BreadCrumb");
    }
    public void clickOnlangContentTypeBreadcrumb() {
        click(langContentTypeBreadcrumb, "lang type  BreadCrumb");
    }

    public String getContentTypeFromBreadCrumbs() {
        return getText(contentTypeBreadcrumb, 1000);
    }
}
