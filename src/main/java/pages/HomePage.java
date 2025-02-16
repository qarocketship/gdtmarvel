package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import java.util.ArrayList;

//This class contains element locators of Homepage and methods to perform actions on those elements
public class HomePage extends BasePage {
    private final String thumbnail = "img";
    private final String allRows =  "tbody>tr";
    private final String characterName = "tr[class^='border-b']>td:nth-child(2)";
    private final String noOfComics = "tr[class^='border-b']>td:nth-child(5)";
    private final String datePublished = "tr[class^='border-b']>td:nth-child(5)";
    private final String searchBox = "input#keyword-input";
    private final String searhResult = "tbody>tr>td:nth-child(2)";
    private final String totalList = "div.text-xs.text-muted-foreground>strong";


    //constructor
    public HomePage(Page page){
        //initializing the constructor of parent class
        super(page);
    }


    public void dashboard_loads_successfully() {
        //wait until the thumbnail loads
        waitUntilVisible(thumbnail);
    }

    public int returnTotalRows(){
        //get total number of rows of the Marvel character list
        return getLocator(allRows).count();
    }


    public String returnThumbnailImage(int rowNumber) {
        //get the image url of thumbnail
        return getLocator(thumbnail).nth(rowNumber).getAttribute("src");
    }

    public String returnCharacterName(int rowNumber) {
        //get the character name
        return getLocator(characterName).nth(rowNumber).innerText();
    }

    public String returnNoOfComics(int rowNumber) {
        //get the noOfComics
        return getLocator(noOfComics).nth(rowNumber).innerText();
    }

    public String returnDatePublished(int rowNumber) {
        //get the datePublished
        return getLocator(datePublished).nth(rowNumber).innerText();
    }

    public void scrollTillEndOfPage() {

        //get the current height of the page
        int lastHeight = (int) page.evaluate("document.body.scrollHeight");

        //loop to keep scrolling
        while (true) {
            //scroll down
            page.evaluate("window.scrollTo(0, document.body.scrollHeight)");

            //wait for sometime to let new elements load
            page.waitForTimeout(8000);

            //get the new height of the page
            int newHeight = (int) page.evaluate("document.body.scrollHeight");

            //break if the height hasn't changed (no new content loaded)
            if (newHeight == lastHeight) {
                page.evaluate("window.scrollTo(0, document.body.scrollHeight)");
                break;
            }

            //update lastHeight for the next iteration
            lastHeight = newHeight;
        }

    }

    public void enterSearchTerm(String searchTerm) {
        //enter search term in the search box
        getLocator(searchBox).fill(searchTerm);

        //wait for sometime to let search result
        page.waitForTimeout(5000);
    }


    //get all character names in the search result
    public ArrayList<String> searchResult() {
        //initialize empty ArrayList to store the character names in the search result
        ArrayList<String> searchResult = new ArrayList<>();

        //condition to check return the Character names from the search result
        if(returnTotalRows()==0){
            return searchResult;
        }else{
            for(int i=0;i<getLocator(searhResult).count();i++){
                searchResult.add(getLocator(searhResult).nth(i).innerText());
            }
            return searchResult;
        }


    }


    //method to click the character based on the row number provided
    public String clickRow(String row) {
        //convert string to integer
        int rowNum = Integer.parseInt(row);

        //extract the total number of rows displayed on the page
        int totalCharactersDisplayed = Integer.parseInt(getLocator(totalList).innerText().substring(2));

        //loop to scroll down till the row number provided as parameter loads
        while (rowNum>totalCharactersDisplayed) {
            //scroll down
            page.evaluate("window.scrollTo(0, document.body.scrollHeight)");

            //wait for some time to let new elements load
            page.waitForTimeout(8000);
            totalCharactersDisplayed = Integer.parseInt(getLocator(totalList).innerText().substring(2));

        }

        //get the character name on the row number provided as parameter
        Locator characterToClick = page.locator("tbody>tr:nth-child("+rowNum+")>td:nth-child(2)");
        String characterName = characterToClick.innerText();

        //click on the character name on the row number provided as parameter
        characterToClick.click();
        page.waitForTimeout(5000);
        return characterName;

    }
}
