package in.wynk.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import in.wynk.pages.CommonPage;
import in.wynk.pages.SearchPage;

import java.util.List;

public class SearchStep {

    SearchPage searchPage;
    CommonPage commonPage;

    public SearchStep(SearchPage searchPage, CommonPage commonPage) {
        this.searchPage = searchPage;
        this.commonPage = commonPage;
    }

    @Given("^User search for content (.+)$")
    public void user_search_for_content(String contentName) throws Exception {
        contentName = (String) searchPage.getGDValue(contentName);
        commonPage.navigateToPage(CommonPage.navigationOption.SEARCH);
        searchPage.typeString(contentName);
//    searchPage.clickSearchButton();
        searchPage.clickSearchedContent(contentName);
    }

    @Given("^click on Recent search clear button$")
    public void clickOnRecentSearchClearButton() throws Exception {
        searchPage.clickOnClearButton();
    }


    @Then("click on search button")
    public void click_on_search_button() {
        searchPage.clickSearchButton();
    }

    @When("search contentName(.+)")
    public void search_contentName(String contentName) throws Exception {
        searchPage.searchContent(contentName);
    }

    @Then("type contentName (.+) on searchbar")
    public void type_contentName_on_searchbar(String contentName) throws Exception {
        searchPage.typeContentNameOnSearchBar(contentName);
    }

    @Then("Verfity no result found text for (.+)")
    public void verfity_no_result_found_text_for(String contentName) {
        searchPage.verifyNoResultFoundText(contentName);
    }


    @Then("click on search cross button")
    public void click_on_search_cross_button() {
        searchPage.clickOnCrossButton();
    }

    @Then("verify the search bar empty")
    public void verify_the_search_bar_empty() {
        searchPage.verifySearchbarIsEmpty();
    }

    @Then("verify the placeholder text")
    public void verify_the_placeholder_text() {
        searchPage.verifyTheSearchbarPlaceholderText();
    }


    @Then("Verfity the search behaviour for(.+)")
    public void verfity_the_search_behaviour_for(String contentName) {
        searchPage.VerifyTheSearchBehaviour(contentName);
    }


    @Then("click on searchbar")
    public void click_on_searchbar() {
        searchPage.clickOnSearchbar();

    }

    @Then("select the content from the dropdown")
    public void select_the_content_from_the_dropdown() {
        searchPage.selectContentFromAutosuggestDropdown();
    }


    @Then("select content(.+)")
    public void select_content(String contentType) {
        searchPage.selectContent(contentType);
    }

    @Then("verify the searched (.+) under the recent search")
    public void verify_the_searched_content_under_the_recent_search(String contentName) {
        searchPage.verifySearchedContentUnderTheRecentSearched(contentName);
    }

    @Then("Verify Auto suggestion contents list should match with API for mobile number (.+) and contentName (.+)")
    public void verify_Auto_suggestion_contents_list_should_match_with_API_for_mobile_number_and_contentName_puth(String mobileNumber, String query) throws Exception {
        List<String> autosuggestListUI = searchPage.getAllAutosuggestListFromUI();
        List<String> autosuggestListAPI = searchPage.getAllAutosuggestListFromApi(mobileNumber, query);
        searchPage.AssertionBetweenTwoList(autosuggestListUI, autosuggestListAPI, query);

    }

    @Then("Verify no suggestion for (.+) until Search Text length is greater than or equal to three")
    public void verify_no_suggestion_for_pu_until_Search_Text_length_is_greater_than_or_equal_to_three(String contentName) {
        searchPage.VerifyNoAutoSuggestForContentTextLengthLessThanThree(contentName);
    }

    @Then("Verify Autosuggestion for (.+) when Search Text length is greater than or equal to three")
    public void verify_Autosuggestion_for_pu_when_Search_Text_length_is_greater_than_or_equal_to_three(String contentName) {
        searchPage.VerifyAutoSuggestForContentTextLengthGreaterThanTwo(contentName);

    }



    @Then("Verify total content count in serach page from API and content count in rail title for contentName (.+) number (.+)")
    public void verify_total_content_count_in_serach_page_from_API_and_content_count_in_rail_title_for_contentName_roja_number(String contentName, String mobileNumber) throws Exception {
        int contentCountFromAPI = searchPage.getAllContentCountFromApi(mobileNumber, contentName);
        int contentCountFromtUI = searchPage.getAllContentCountFromUI();
        searchPage.VerifyContentCountIsSameForUIandApi(contentCountFromtUI, contentCountFromAPI);

    }

    @Then("Verify Search Page")
    public void iVerifySearchPage() throws Exception {
        searchPage.verifySearchPage();
    }

    @When("search multiple content (.+)")
    public void search_multiple_content(String contents) throws Exception {
        searchPage.searchMultipleContent(contents);
    }

    @Then("Verify searched content (.+) under recent searches")
    public void verify_searched_content_under_recent_searches(String contents) {
        searchPage.VerifySearchedContentUnderRecentSearches(contents);
    }

    @Then("Verify Trending and Recent verification on Search pages")
    public void verify_Trending_and_Recent_verification_on_Search_pages() {
        searchPage.VerifyTrendingAndRecentsearches();
    }

    @When("search by (.+)")
    public void search_by(String searchText) throws Exception {
        searchPage.searchContent(searchText);

    }

    @Then("Verify search is related to (.+) language")
    public void verify_search_is_related_to_language(String language) {
        searchPage.VerifySearchRelatedToLanguage(language);
    }

    @Then("Verify search is related to (.+) artist")
    public void verify_search_is_related_to_artist(String artistName) {
        searchPage.VerifySearchRelatedToArtist(artistName);

    }
    @Then("Verify Trending content list should be consistent for UserA (.+) and UserB (.+) (.+)")
    public void verify_Trending_content_list_should_be_consistent_for_UserA_and_UserB(String userAmobileNumber, String userBmobileNumber ,String otp) throws Exception {
            searchPage.VerifyTrendingListConsistentforAllUser((String)searchPage.getGDValue(userAmobileNumber),  (String)searchPage.getGDValue(userBmobileNumber),(String)searchPage.getGDValue(otp));
    }


}
