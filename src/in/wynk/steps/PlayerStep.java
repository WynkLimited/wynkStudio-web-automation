package in.wynk.steps;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import in.wynk.framework.Assert;
import in.wynk.framework.SoftAssert;
import in.wynk.pages.CommonPage;
import in.wynk.pages.PlayerPage;

import java.text.ParseException;


public class PlayerStep {

    Assert Assert;
    PlayerPage playerPage;
    SoftAssert softAssert;
    CommonPage commonPage;

    public PlayerStep(Assert Assert, PlayerPage playerPage, SoftAssert softAssert, CommonPage commonPage) {
        this.Assert = Assert;
        this.playerPage = playerPage;
        this.softAssert = softAssert;
        this.commonPage = commonPage;
    }


    @When("^Open playable content with contentType(.+) ,contentName(.+),contentID(.+),flag(.+)$")
    public void open_playable_content(String contentType, String contentName, String contentID, String flag) {

        switch (contentType) {
            case "MOVIES":
                playerPage.launchURI("movies", contentName, contentID, flag);
                break;
            case "LIVE_TV":
                playerPage.launchURI("livetv-channels", contentName, contentID, flag);
                break;
            case "TV_SHOWS":
                playerPage.launchURI("tv-shows", contentName, contentID, flag);
                break;
        }
    }

    @Then("^click on play pause button$")
    public void click_on_play_pause() {
        playerPage.click_on_play_pause_button();
    }
    @Then("click on play pause player controller button after {int}")
    public void click_on_play_pause_player_controller_button_after(Integer int1) {
        playerPage.sleep(int1);
     System.out.println( playerPage.get_player_Thumb_Current_Time());
        playerPage.click_on_play_pause_bottom_control_button();
    }


    @Then("^click on play pause bottom navigation button$")
    public void click_on_play_pause_navigation_button() {
        playerPage.click_on_play_pause_bottom_control_button();
    }

    @And("^verify player Controls(.+)$")
    public void verify_player_Controls(String contentType) throws ParseException {
        playerPage.sleep(2000);
        set_seekBar_Thumb_in_start();
        playerPage.sleep(2000);
        Assert.assertTrue(playerPage.is_Volume_Btn_Visible(), "volume button is not visible in player ");
        Assert.assertTrue(playerPage.is_Setting_Btn_Visible(), "setting button is  not visible in player ");
        Assert.assertTrue(playerPage.is_SeekBar_Visible(), "seek bar is not visible in player");
//        Assert.assertTrue(playerPage.is_SeekBar_Hover_Time_Visible(), "seek bar hover is not visible in player");
        Assert.assertTrue(playerPage.is_Current_Time_Label_Visible(), "seek bar content running  current time label is not visible");
        Assert.assertTrue(playerPage.is_End_Time_Label_Visible(), " seek bar content end time label is not visible");
        Assert.assertTrue(playerPage.is_Backward_Btn_Visible(), " player backward button is not visible in player  ");
        Assert.assertTrue(playerPage.is_Full_Screen_Visible(), "player full screen is not visible in player");
        Assert.assertTrue(playerPage.is_Forward_Btn_Visible(), "player forward button is not visible in player");
        playerPage.click_On_full_screen_Button();
        playerPage.sleep(2000);
        Assert.assertTrue(playerPage.is_Player_Content_Label_Visible(), "player content label is not visible");

    }

    @And("^verify a player Controls for LiveTV$")
    public void verify_player_Controls_for_LiveTV() throws ParseException {
        playerPage.sleep(2000);
        playerPage.set_SeekBar_Thumb_In_Start_For_LiveTV();
        playerPage.sleep(2000);
        Assert.assertTrue(playerPage.is_Volume_Btn_Visible(), "volume button is not visible in player ");
        Assert.assertTrue(playerPage.is_Setting_Btn_Visible(), "setting button is  not visible in player ");
        Assert.assertTrue(playerPage.is_SeekBar_Visible(), "seek bar is not visible in player");
//        Assert.assertTrue(playerPage.is_SeekBar_Hover_Time_Visible(), "seek bar hover is not visible in player");
        Assert.assertTrue(playerPage.is_Current_Time_Label_Visible(), "seek bar content running  current time label is not visible");
        Assert.assertEquals(playerPage.get_LiveTV_Button_Text().trim(), "GO LIVE", "Go Live button not visible in Live TV Player");
        Assert.assertTrue(playerPage.is_Backward_Btn_Visible(), " player backward button is not visible in player  ");
        Assert.assertTrue(playerPage.is_Full_Screen_Visible(), "player full screen is not visible in player");
        Assert.assertTrue(playerPage.is_Forward_Btn_Visible(), "player forward button is not visible in player");
        playerPage.click_On_full_screen_Button();
        playerPage.sleep(2000);
        Assert.assertTrue(playerPage.is_Player_Content_Label_Visible(), "player content label is not visible");

    }


    @And("^verify play pause functionality(.+)$")
    public void verify_play_pause(String pFlag) {
        playerPage.sleep(3000);
        Assert.assertEquals(String.valueOf(playerPage.verify_streaming_is_working()), pFlag);
    }

    @Then("^seek bar dragging start to end : end to start$")
    public void verify_seekBar_Dragging() throws InterruptedException, ParseException {
        playerPage.sleep(2000);
        playerPage.set_SeekBar_Thumb_In_End();
        Assert.assertEquals(playerPage.get_player_Thumb_End_Time().trim(), playerPage.get_player_Thumb_Current_Time().trim(), "verify thumb current time with end time");
        click_On_Keyboard_LeftArrow_Button("2");
        playerPage.set_SeekBar_Thumb_In_Start();
        Assert.assertEquals(playerPage.get_player_Thumb_Current_Time().trim(), "0:00:00", "verify thumb current time with start time");
    }


    @Then("^verify player element and functionality in full screen$")
    public void verify_player_in_full_screen() throws ParseException {
        set_seekBar_Thumb_in_start();
        playerPage.sleep(2000);
        playerPage.click_On_full_screen_Button();
        playerPage.sleep(2000);
        softAssert.assertTrue(playerPage.is_Volume_Btn_Visible(), "volume button is not visible in player ");
        //    softAssert.assertTrue(playerPage.is_Setting_Btn_Visible(), "setting button is  not visible in player ");
        softAssert.assertTrue(playerPage.is_SeekBar_Visible(), "seek bar is not visible in player");
        softAssert.assertTrue(playerPage.is_Current_Time_Label_Visible(), "seek bar content running  current time label is not visible");
        softAssert.assertTrue(playerPage.is_End_Time_Label_Visible(), " seek bar content end time label is not visible");
        softAssert.assertTrue(playerPage.is_Backward_Btn_Visible(), " player backward button is not visible in player  ");
        softAssert.assertTrue(playerPage.is_Forward_Btn_Visible(), "player forward button is not visible in player");
        softAssert.assertTrue(playerPage.is_Full_Screen_Visible(), "player full screen is not visible in player");
        softAssert.assertTrue(playerPage.is_Player_Content_Label_Visible(), "player content label is not visible");
        playerPage.set_SeekBar_Thumb_In_End();
        softAssert.assertEquals(playerPage.get_player_Thumb_End_Time().trim(), playerPage.get_player_Thumb_Current_Time().trim(), "verify thumb current time with end time");
        click_On_Keyboard_LeftArrow_Button("2");
        playerPage.set_SeekBar_Thumb_In_Start();
        softAssert.assertEquals(playerPage.get_player_Thumb_Current_Time().trim(), "0:00:00", "verify thumb current time with start time");
    }


    @Then("^verify player forward functionality in streaming$")
    public void verify_player_forward_func_in_Streaming() throws ParseException {
        //   playerPage.set_SeekBar_Thumb_In_Start();
        click_On_Keyboard_RightArrow_Button("3");
        playerPage.sleep(8000);
        int timeGap = playerPage.verify_forward_button_functionality();
        System.out.println("verify player forward functionality in streaming " + timeGap);
        if (timeGap == 10 || timeGap == 11 || timeGap == 12) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false, " after click on forward button time gab is not 10, 11, 12  sec");
        }
    }

    @Then("^verify player backward functionality in streaming$")
    public void verify_player_backward_func_in_Streaming() throws ParseException {
        playerPage.set_SeekBar_Thumb_In_End();
        click_On_Keyboard_LeftArrow_Button("2");
        playerPage.sleep(8000);
        int backwardTime=0;
        backwardTime = playerPage.verify_backward_button_functionality();
        System.out.println("verify player backward functionality in streaming " + backwardTime);

        if (backwardTime == 10 || backwardTime == 9 || backwardTime == 8 || backwardTime == 7) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false, " after click on backward button time gab is not 10 ,9 ,8 ,7 sec");
        }

    }
    @Then("^verify player backward functionality in LiveTV streaming$")
    public void verify_player_backward_func_in_LiveTV_Streaming() throws ParseException {
        click_On_Keyboard_LeftArrow_Button("5");
        playerPage.sleep(1000);
        int backwardTime = playerPage.verify_backward_button_functionality();
        System.out.println("player backward for live tv" + backwardTime);
        if (backwardTime == 13 || backwardTime == 11 || backwardTime == 12 || backwardTime == 10 || backwardTime == 9 || backwardTime == 8 || backwardTime == 7) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false, " after click on backward button time gab is not 10 sec");
        }

    }

    @Then("^click On full screen Button in player$")
    public void click_On_full_screen_Button() {
        playerPage.click_On_full_screen_Button();
    }

    @When("^set seekBar Thumb in start$")
    public void set_seekBar_Thumb_in_start() {
        playerPage.set_SeekBar_Thumb_In_Start();
    }

    @When("^set seekBar Thumb in end$")
    public void set_seekBar_Thumb_in_end() throws ParseException {
        playerPage.set_SeekBar_Thumb_In_End();
    }


    @Then("^verify Buffering waitingTime(.+),apiCounter(.+),hitTimeGap(.+)$")
    public void verifyBuffering(String waitingTime, String apiCounter, String hitTimeGap) {
        String[] bufferArr = playerPage.verify_Connect_Buffering(Integer.parseInt(waitingTime), Integer.parseInt(apiCounter), Integer.parseInt(hitTimeGap)).split(",");
        int count = 0;
        for (int i = 1; i <= bufferArr.length - 1; i++) {
            if (Double.parseDouble(bufferArr[i - 1]) < Double.parseDouble((bufferArr[i]))) {
                count++;
            }
        }
        if (count <= bufferArr.length - 1 && count >= bufferArr.length - 3) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false, "buffering not working");
        }

    }

    @Then("click on play pause player controller button")
    public void click_on_play_pause_player_controller_button() {
      playerPage.clickOnPlayPausePlayerControlBtn();
    }


    @Then("^verify Buffering in pause waitingTime(.+),apiCounter(.+),hitTimeGap(.+)$")
    public void verifyBufferingInPause(String waitingTime, String apiCounter, String hitTimeGap) {
        String[] bufferArr = playerPage.verify_Connect_Buffering_pause(Integer.parseInt(waitingTime), Integer.parseInt(apiCounter)).split(",");
        int count = 0;

        for (int i = 1; i <= bufferArr.length - 1; i++) {
            if (Double.parseDouble(bufferArr[i - 1]) < Double.parseDouble((bufferArr[i]))) {
                count++;
            }
        }
        System.out.println("count" + count);
        if (count <= bufferArr.length - 1 && count >= bufferArr.length - bufferArr.length / 3) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false, "buffering not working");
        }

    }

    @And("click KeyBoard Right Arrow button on (.+) time$")
    public void click_On_Keyboard_RightArrow_Button(String noOfTime) {
        for (int i = 1; i <= Integer.parseInt(noOfTime); i++) {
            playerPage.click_ON_RightArrow_respect_of_seekBar();
        }
    }

    @And("click KeyBoard Left Arrow button on (.+) time$")
    public void click_On_Keyboard_LeftArrow_Button(String noOfTime) throws ParseException {
        for (int i = 1; i <= Integer.parseInt(noOfTime); i++) {
            playerPage.click_ON_LeftArrow_respect_of_seekBar();
        }
    }

    @Then("^verify content resolution Options(.+)$")
    public void clickOnSettingButton(String resolution) {
        playerPage.click_on_Setting_Btn();
        playerPage.select_Quality_Option_In_Setting();
        Assert.assertEquals(playerPage.get_Quality_Option().trim(), resolution, "content resolution miss match or not visible");
    }


    @Then("^verify volume state(.+)$")
    public void verifyVolumeState(String state) {
        System.out.println(state);
        playerPage.verify_volume_state();
        Assert.assertEquals(playerPage.verify_volume_state(), state, "volume button is not in this " + state);
    }

    @Then("^click On volume button$")
    public void clickOnVolumeButton() {
        playerPage.click_On_volume_button();
    }

    @Then("^verify content timer when page refresh$")
    public void verifyContentTimerwhenPageRefresh() throws ParseException {
        String beforeTime = playerPage.get_player_Thumb_Current_Time();
        commonPage.reload_page();
        String afterTime = playerPage.get_player_Thumb_Current_Time();
        int timeGap = Math.abs(commonPage.get_Time_diff_in_Sec(beforeTime, afterTime));
        System.out.println("time gap" + timeGap);
        if (timeGap == 0 || timeGap == 1 || timeGap == 2) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false, "after refresh player page time difference is more the 2 sec ");

        }

    }

    @And("^verify current time is 00:00:00 when player start via replay button$")
    public void verifyCurrentTimeWhenPlayerStart() {
        long current_Time = System.currentTimeMillis();
        boolean flag = false;
        while ((current_Time + 20000) > System.currentTimeMillis()) {
            if (playerPage.getText(playerPage.seekbar_current_time_label) == "0:00:00") {
                flag = true;
                break;
            }
        }
        Assert.assertTrue(flag,"after replay same content start time should be 00:00:00" );
    }
    @Then("Verify content is playing")
    public void verify_content_is_playing() {
        Assert.assertEquals(playerPage.verify_streaming_is_working(), true);

    }

    @When("click on PIP button")
    public void click_on_PIP_button() {
        playerPage.clickOnPIPButton();
    }
}
