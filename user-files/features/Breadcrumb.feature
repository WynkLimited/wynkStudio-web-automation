@Breadcrumb
Feature: Breadcrumbs

  @sanity
  Scenario Outline:  Verify Root of Page for BreadCrumb
    When Navigate To Home Page
    Then Verify Root of Page for BreadCrumb<contentPath>

    Examples:
      | contentPath                                                                         |
      | /livetv-channels/raj-musix-telugu/RAJTV_LIVE_LIVETVCHANNEL_61b09b94c29a19d454322f06 |
      | /movies/style/RAJTV_MOVIE_61af0b351507c370d5879c61                                  |


  @sanity
  Scenario Outline:  Verify BreadCrumb for <PageName>
    When Navigate To Home Page
    When Navigate To Page<PageName>
    Then Verify BreadCrumb for<BreadCrumb>

    Examples:
      | PageName     | BreadCrumb      |
      | HOME PAGE    | Home            |
      | TVSHOWS PAGE | Home > TV Shows |
      | MOVIES PAGE  | Home > Movies   |
      | NEWS PAGE    | Home > News     |
      | LIVETV PAGE  | Home > Live TV  |


  @P0
  Scenario Outline:  Verify <BreadCrumb> BreadCrumb for TV Shows
    When Navigate To Home Page
    Then Verify <BreadCrumb> BreadCrumb for Shows Content <contentPath>

    Examples:
      | BreadCrumb    | contentPath                                                                                        |
   #   | season page   | /tv-shows/super-samayal/super-samayal/RAJTV_TVSHOW_62035850846ff44be4d97777_SEASON_1               |
      | regular shows | /tv-shows/amudhe-thamizhe/RAJTV_TVSHOW_62036f605498374937e2fd9a                                    |
      | episode page  | /tv-shows/amudhe-thamizhe/amudhe-thamizhe/episode-1-bini-mov/RAJTV_EPISODE_62037b735498374937e2fd9e|


  @regression
  Scenario Outline:  Verify <BreadCrumb> BreadCrumb for Movie detail page
    When Navigate To Home Page
    Then Verify BreadCrumb for Movie detail page <contentPath>

    Examples:
      | contentPath                                        |
      | /movies/style/RAJTV_MOVIE_61af0b351507c370d5879c61 |

  @regression
  Scenario Outline:  Verify <BreadCrumb> BreadCrumb for LIVE TV content detail page
    When Navigate To Home Page
    Then Verify BreadCrumb for livetv detail page <contentPath>

    Examples:
      | contentPath                                                                         |
      | /livetv-channels/raj-musix-telugu/RAJTV_LIVE_LIVETVCHANNEL_61b09b94c29a19d454322f06 |

  @regression
  Scenario Outline:  Verify that the BreadCrumbs should be clickable for Movies content detail page
    When Navigate To Home Page
    Then Verify BreadCrumb should be clickable for Movies content detail page <contentPath>

    Examples:
      | contentPath                                                       |
      | /movies/chidambara-ragasiyam/RAJTV_MOVIE_61d3cdf76d249e22d8c1d7be |

  @regression
  Scenario Outline:  Verify that the BreadCrumbs should be clickable for Livetv content detail page
    When Navigate To Home Page
    Then Verify BreadCrumb should be clickable for Livetv content detail page <contentPath>

    Examples:
      | contentPath                                                                        |
      | /livetv-channels/raj-news-telugu/RAJTV_LIVE_LIVETVCHANNEL_61b09b4cc29a19d454322b8a |

  @regression
  Scenario Outline:  Verify respected tabs should be selected when played any content
    When Navigate To Home Page
    Then Verify respected tabs should be selected when played any content <contentPath>

    Examples:
      | contentPath                                                                        |
      | /livetv-channels/raj-news-telugu/RAJTV_LIVE_LIVETVCHANNEL_61b09b4cc29a19d454322b8a |
      | /movies/chidambara-ragasiyam/RAJTV_MOVIE_61d3cdf76d249e22d8c1d7be                  |

#  @P0
#  Scenario Outline:  Verify <BreadCrumb> BreadCrumb for Rail List movies Page
#    When Navigate To Home Page
#    Then Verify BreadCrumb for LIVE TV content detail page <BreadCrumb> path <contentPath>
#
#    Examples:
#      | BreadCrumb   | contentPath                                                       |
#      | Movies_list  | /movies/latest-movies/list/5cde9bc2e4b008cb38f1157f               |
#      | LiveTV_list  | /livetv-channels/live-news/list/5cc2ac04e4b077ca0de47363          |
#      | TVShows_list | /tv-shows/popular-telugu-web-series/list/5ce3c667e4b0192564812ea7 |


  #failing
  @P0 @smoke
  Scenario Outline:  Verify <BreadCrumb> BreadCrumb for Rail List from Home Page
    When Navigate To Home Page
    Then Verify BreadCrumb for Rail List Page from Home <contentPath>

    Examples:
      | contentPath                                        |
      | /home/popular-movies/list/617afa9deb3b870dc3c06963 |



#//bug
#  @P0
#  Scenario Outline:  Verify <BreadCrumb> BreadCrumb for catchup content
#    When Navigate To More Page
#    Then Verify BreadCrumb for Catch up <contentPath>
#
#    Examples:
#      | contentPath                                      |
#        | /livetv-channels/zee-punjab-haryana-himachal/hindi-news-bulletin/MWTV_LIVETVSHOW_429_30000000550270408 |
