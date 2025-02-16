package pages;

import com.microsoft.playwright.Page;

//This class contains element locators of CharacterPage and methods to perform actions on those elements
public class CharacterPage extends BasePage {

    private final String characterNameHeading = "div.mb-4:first-child>h1";

    //constructor
    public CharacterPage(Page page) {
        //initializing the constructor of parent class
        super(page);
    }

    //get url of the page
    public String getPageUrl(){
        return page.url();
    }

    //get the character heading on the character page
    public String getCharacterName() {
        page.waitForSelector(characterNameHeading);
        return getLocator(characterNameHeading).innerText();
    }
}
