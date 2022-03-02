@Footer
Feature: Footer

  @smoke
  Scenario Outline: Verify user can see top shows list
    When Navigate To Home Page
    Then Verify topshows  list <TopShowList>

    Examples:
      | TopShowList                                                  |
      | Tamil Shows , Telugu Shows , Kannada Shows , Malayalam Shows |

  @smoke
  Scenario Outline: Verify user can see trending movies list
    When Navigate To Home Page
    Then Verify trending Movies list <trendingMoviesList>

    Examples:
      | trendingMoviesList                                                  |
      | Tamil Movies , Telugu Movies , Kannada Movies , Malayalam Movies |

  @smoke
  Scenario Outline: Verify user can see top shows list
    When Navigate To Home Page
    Then Verify just Arrived list <justArrivedList>

    Examples:
      | justArrivedList                                                  |
      | Latest Tamil , Latest Telugu , Latest Kannada ,Latest Malayalam  |

  @sanity
  Scenario Outline:  verify footer list type
    When Navigate To Home Page
    Then Verify verify footer list type <ListType>

    Examples:
      | ListType                                   |
      | Top shows , Trending Movies , Just Arrived  |

  @sanity
  Scenario Outline:  verify that no links are broken
    When Navigate To Home Page
    Then verify that no links are broken

    Examples:
      | ListType                                   |
      | Top shows , Trending Movies , Just Arrived  |