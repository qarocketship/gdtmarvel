package stepDefinitions;


import com.microsoft.playwright.Page;
import factory.PlaywrightFactory;
import io.cucumber.java.en.*;
import org.testng.asserts.SoftAssert;
import java.util.ArrayList;
import pages.CharacterPage;
import pages.HomePage;

public class DashboardSteps {

    public Page page;
    SoftAssert softAssert = new SoftAssert();
    String searchTerm;
    String characterName;
    int totalRows;

    private HomePage homePage;
    private CharacterPage characterPage;


    @Given("User navigates to dashboard")
    public void user_navigates_to_dashboard() {
        page = PlaywrightFactory.getPage();
        homePage = new HomePage(page);

    }

    @When("dashboard loads successfully")
    public void dashboard_loads_successfully() {
        homePage.dashboard_loads_successfully();
    }



    @Then("initial list of Marvel characters is displayed")
    public void initial_list_of_marvel_characters_is_displayed() {

        int totalRows = homePage.returnTotalRows();

        for(int i=0;i<totalRows;i++){
            softAssert.assertTrue(!((homePage.returnThumbnailImage(i)).isEmpty()),"Image of "+i+" Character is missing");
            softAssert.assertTrue(!((homePage.returnCharacterName(i)).isEmpty()),"Name of "+i+" Character is missing");
            softAssert.assertTrue(!((homePage.returnNoOfComics(i)).isEmpty()),"No of comics of "+i+" Character is missing");
            softAssert.assertTrue(!((homePage.returnDatePublished(i)).isEmpty()),"Date published of "+i+" Character is missing");
        }
        softAssert.assertAll();

    }

    @When("user scrolls down the list")
    public void user_scrolls_down_the_list() {
        totalRows= homePage.returnTotalRows();
        homePage.scrollTillEndOfPage();
    }


    @Then("Additional characters are loaded dynamically")
    public void additional_characters_are_loaded_dynamically() {
        int newTotalRows = homePage.returnTotalRows();
        //softAssert.assertTrue(newTotalRows>totalRows);
        softAssert.assertEquals(newTotalRows,1564);
        softAssert.assertAll();
    }

    @When("user enters {string} in search bar")
    public void user_enters_in_search_bar(String searchTerm) {
        this.searchTerm = searchTerm;
        homePage.enterSearchTerm(searchTerm);
    }

    @Then("list updates to display matching characters")
    public void list_updates_to_display_matching_characters() {
        ArrayList<String> searchResult = homePage.searchResult();
        if(searchResult.size()==0){
            softAssert.assertTrue(page.locator("body").innerHTML().contains("No characters found."));
        }else{
            for(int i=0;i<searchResult.size();i++){
                String name = searchResult.get(i);
                softAssert.assertTrue(name.startsWith(searchTerm));
            }
        }
       softAssert.assertAll();

    }

    @When("user clicks {string} character")
    public void user_clicks_character(String row) {
        characterName = homePage.clickRow(row);
    }

    @Then("user is navigated to character page")
    public void user_is_navigated_to_character_page() {
        characterPage = new CharacterPage(page);
        softAssert.assertTrue(characterPage.getPageUrl().contains("characters/"));
    }

    @Then("detailed information of the character is shown")
    public void detailed_information_of_the_character_is_shown() {
        String characterHeading = characterPage.getCharacterName();
        softAssert.assertEquals(characterName,characterHeading,"The Character name is not matching");
        softAssert.assertAll();
    }
}
