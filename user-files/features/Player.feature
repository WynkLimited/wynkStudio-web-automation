@player
Feature: Player

  @P0 @sanity
  Scenario Outline: verify Player for <contentType>
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then click on play pause button
    When Login with number<mobilenumber> and OTP<OTP>
    And verify player Controls<contentType>
    Examples:
      | contentType | contentName        | contentID                            | flag  | mobilenumber                    | OTP                    |
      | MOVIES      | roja               | RAJTV_MOVIE_61a49bcc1507c370d5879c22 | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} |
  #    | TV_SHOWS    | falling-for-angels | JUNGO_TVSHOW_2000002029                   | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} |



  @P0 @sanity
  Scenario Outline: verify play pause functionality for <contentType>
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then click on play pause button
    When Login with number<mobilenumber> and OTP<OTP>
    When wait for 2000 milli second
    Then set seekBar Thumb in start
    Then click KeyBoard Right Arrow button on 5 time
    When wait for 3000 milli second
    Then click on play pause player controller button
    Then verify play pause functionality<PauseFlag>
    When wait for 2000 milli second
    Then click on play pause player controller button
    When wait for 2000 milli second
    Then verify play pause functionality<playflag>
    Examples:
      | contentType | contentName        | contentID                                 | flag  | mobilenumber                    | OTP                    | PauseFlag | playflag |
      | MOVIES      | roja               | RAJTV_MOVIE_61a49bcc1507c370d5879c22      | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} |  false     | true     |
   #   | TV_SHOWS    | falling-for-angels | JUNGO_TVSHOW_2000002029                   | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | false     | true     |


  @P0
  Scenario Outline: verify seekBar pointer dragging functionality for <contentType>
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then click on play pause button
    When Login with number<mobilenumber> and OTP<OTP>
    When wait for 2000 milli second
    Then seek bar dragging start to end : end to start

    Examples:
      | contentType | contentName        | contentID                                   | flag  | mobilenumber                    | OTP                    |
      | MOVIES      | roja               | RAJTV_MOVIE_61a49bcc1507c370d5879c22        | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} |
    #  | TV_SHOWS    | falling-for-angels | JUNGO_TVSHOW_2000002029                   | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} |


  @P0
  Scenario Outline: verify player in full screen for <contentType>
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then click on play pause button
    When Login with number<mobilenumber> and OTP<OTP>
    When wait for 2000 milli second
    Then verify player element and functionality in full screen

    Examples:
      | contentType | contentName        | contentID                                 | flag  | mobilenumber                    | OTP                    |
      | MOVIES      | roja               | RAJTV_MOVIE_61a49bcc1507c370d5879c22      | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} |
   #   | TV_SHOWS    | falling-for-angels | JUNGO_TVSHOW_2000002029                   | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} |

  @P0 @sanity
  Scenario Outline: verify forward or backward functionality while streaming for <contentType>
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then click on play pause button
    When Login with number<mobilenumber> and OTP<OTP>
    Then wait for 2000 milli second
    Then verify player forward functionality in streaming
    Then wait for 2000 milli second
    Then verify player backward functionality in streaming
    Examples:
      | contentType | contentName        | contentID                                 | flag  | mobilenumber                    | OTP                    |
      | MOVIES      | roja               | RAJTV_MOVIE_61a49bcc1507c370d5879c22      | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} |
     # | TV_SHOWS    | falling-for-angels | JUNGO_TVSHOW_2000002029                   | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} |




  @P0 @sanity
  Scenario Outline: verify content buffering in default size screen for <contentType>
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then click on play pause button
    When Login with number<mobilenumber> and OTP<OTP>
    Then wait for 1000 milli second
    Then set seekBar Thumb in start
    Then verify Buffering waitingTime<waitingTime>,apiCounter<apiCounter>,hitTimeGap<hitTimeGap>

    Examples:
      | contentType | contentName     | contentID                                 | flag  | mobilenumber                    | OTP                    | waitingTime | apiCounter | hitTimeGap |
      | MOVIES      | roja               | RAJTV_MOVIE_61a49bcc1507c370d5879c22   | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | 6000        | 5          | 14000      |


  @P0 @sanity
  Scenario Outline: verify content buffering in full screen size for <contentType>
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then click on play pause button
    When Login with number<mobilenumber> and OTP<OTP>
    Then wait for 1000 milli second
    Then click On full screen Button in player
    Then set seekBar Thumb in start
    Then verify Buffering waitingTime<waitingTime>,apiCounter<apiCounter>,hitTimeGap<hitTimeGap>

    Examples:
      | contentType | contentName        | contentID                             | flag  | mobilenumber                    | OTP                    | waitingTime | apiCounter | hitTimeGap |
      | MOVIES      | Uthama Puthiran    | RAJTV_MOVIE_61b81bde1507c370d5879c7b  | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} |6000         | 5          | 14000      |


  @P0
  Scenario Outline: verify content buffering in default size screen when player in pause state for <contentType>
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then click on play pause button
    When Login with number<mobilenumber> and OTP<OTP>
    Then wait for 1000 milli second
    Then set seekBar Thumb in start
    Then wait for 1000 milli second
    And click KeyBoard Right Arrow button on 5 time
    Then click on play pause button
    Then wait for 1000 milli second
    And click KeyBoard Right Arrow button on 10 time
    Then verify Buffering in pause waitingTime<waitingTime>,apiCounter<apiCounter>,hitTimeGap<hitTimeGap>

    Examples:
      | contentType | contentName                 | contentID                              | flag  | mobilenumber                    | OTP                    | waitingTime | apiCounter | hitTimeGap |
      | MOVIES      | Adutha Varisu               | RAJTV_MOVIE_61ab0ebe1507c370d5879c47   | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | 10000       | 10         | 300        |

  @P0 @sanity
  Scenario Outline: verify content buffering in full screen size when player in pause state for <contentType>
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then click on play pause button
    When Login with number<mobilenumber> and OTP<OTP>
    Then wait for 1000 milli second
    Then set seekBar Thumb in start
    Then click On full screen Button in player
    Then click on play pause bottom navigation button
    Then verify Buffering in pause waitingTime<waitingTime>,apiCounter<apiCounter>,hitTimeGap<hitTimeGap>

    Examples:
      | contentType | contentName            | contentID                                 | flag  | mobilenumber                    | OTP                    | waitingTime | apiCounter | hitTimeGap |
      | MOVIES      | Apoorva Sahodarigal    | RAJTV_MOVIE_61c1597761a483687598da91   | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | 10000       | 10         | 300        |

# need to be done
  @P0
  Scenario Outline: verify content resolution for <contentType>
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then click on play pause button
    When Login with number<mobilenumber> and OTP<OTP>
    Then verify content resolution Options<resolution>

    Examples:
      | contentType | contentName     | contentID                                 | flag  | mobilenumber                    | OTP                    | resolution           |
      | MOVIES      | roja            | RAJTV_MOVIE_61a49bcc1507c370d5879c22      | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Auto,High,Medium,Low |
#     | TV_SHOWS    | falling-for-angels | JUNGO_TVSHOW_2000002029                   | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | Auto,High,Medium,Low |


  @P0  @sanity
  Scenario Outline: verify volume and mute functionality for <contentType>
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then click on play pause button
    When Login with number<mobilenumber> and OTP<OTP>
    Then verify volume state<state>
    Then click On volume button
    Then verify volume state<muteState>
    Then click On volume button
    Then verify volume state<state>

    Examples:
      | contentType | contentName        | contentID                                 | flag  | mobilenumber                    | OTP                    | state   | muteState |
      | MOVIES      | Velaikkaran        | RAJTV_MOVIE_61a8913d1507c370d5879c3a     | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | voluble | mute      |
    #  | TV_SHOWS    | falling-for-angels | JUNGO_TVSHOW_2000002029                   | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} | voluble | mute      |


  @P0
  Scenario Outline: verify volume and mute functionality in full screen for <contentType>
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then click on play pause button
    When Login with number<mobilenumber> and OTP<OTP>
    Then click On full screen Button in player
    Then verify volume state<state>
    Then click On volume button
    Then verify volume state<muteState>
    Then click On volume button
    Then verify volume state<state>


    Examples:
      | contentType | contentName        | contentID                                 | flag  | mobilenumber                    | OTP                    | state  | muteState |
      | MOVIES      | roja               | RAJTV_MOVIE_61a49bcc1507c370d5879c22      | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} |voluble | mute      |
  #    | TV_SHOWS    | falling-for-angels | JUNGO_TVSHOW_2000002029                   | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} |voluble | mute      |


  @P0
  Scenario Outline: Verify the behaviour on reloading the page while streaming video playback for <contentType>
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then click on play pause button
    When Login with number<mobilenumber> and OTP<OTP>
    Then wait for 1000 milli second
    When set seekBar Thumb in start
    Then click KeyBoard Right Arrow button on 10 time
    Then wait for 2000 milli second
    Then verify content timer when page refresh

    Examples:
      | contentType | contentName        | contentID                                 | flag  | mobilenumber                    | OTP                    |
      | MOVIES      | roja               | RAJTV_MOVIE_61a49bcc1507c370d5879c22      | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} |
    # | TV_SHOWS    | falling-for-angels | JUNGO_TVSHOW_2000002029                   | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} |


  @P0
  Scenario Outline: Verify the behaviour on reloading the page while streaming video playback for <contentType> in full screen
    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
    Then click on play pause button
    When Login with number<mobilenumber> and OTP<OTP>
    Then click On full screen Button in player
    Then click KeyBoard Right Arrow button on 12 time
    Then verify content timer when page refresh


    Examples:
      | contentType | contentName        | contentID                                 | flag  | mobilenumber                    | OTP                    |
      | MOVIES      | Silk Silk Silk     | RAJTV_MOVIE_61a5bc7610d0b059dc13d582      | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} |
   #   | TV_SHOWS    | falling-for-angels | JUNGO_TVSHOW_2000002029                   | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} |


#  no such feature named Replay




#  @P0
#  Scenario Outline: Verify that once the video is complete, user can replay the video using 'replay' icon for <contentType>
#    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
#    Then click on play pause button
#    When Login with number<mobilenumber> and OTP<OTP>
#    Then wait for 1000 milli second
#    When set seekBar Thumb in end
#    Then click KeyBoard Left Arrow button on 1 time
#    Then wait for 12000 milli second
#    Then click on play pause button
#    And verify current time is 00:00:00 when player start via replay button
#
#    Examples:
#      | contentType | contentName     | contentID                                 | flag  | mobilenumber                    | OTP                    |
#      | MOVIES      | tango-charlie   | SHEMAROOME_MOVIE_61002232a609d2281c0017e2 | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} |
#

#  @P0
#  Scenario Outline: verify Player for <contentType>
#    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
#    Then click on play pause button
#    When Login with number<mobilenumber> and OTP<OTP>
#    And  verify a player Controls for LiveTV
#    Examples:
#      | contentType | contentName | contentID              | flag  | mobilenumber                    | OTP                    |
#      | LIVE_TV     | zee-news    | MWTV_LIVETVCHANNEL_428 | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} |

#  @P0
#  Scenario Outline: verify backward functionality while streaming with default player and full screen size for <contentType>
#    When Open playable content with contentType<contentType> ,contentName<contentName>,contentID<contentID>,flag<flag>
#    Then click on play pause button
#    When Login with number<mobilenumber> and OTP<OTP>
#    Then verify player backward functionality in LiveTV streaming
#    Then click On full screen Button in player
#    Then wait for 1000 milli second
#    Then verify player backward functionality in LiveTV streaming
#
#    Examples:
#      | contentType | contentName     | contentID                                 | flag  | mobilenumber                    | OTP                    |
#      | MOVIES      | tango-charlie   | SHEMAROOME_MOVIE_61002232a609d2281c0017e2 | false | %{XSTREAM_PREMIUM_PHONE_NUMBER} | %{XSTREAM_PREMIUM_OTP} |

















