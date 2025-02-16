package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//Parent class containing all reusable methods
public class BasePage {
    protected Page page;
    protected Logger log;

    //constructor
    public BasePage(Page page) {
        this.page=page;
        log = LogManager.getLogger(this.getClass());
    }



    //get page locator
    protected Locator getLocator(String locator){
        log.info("Getting locator of "+locator);
        return page.locator(locator);
    }

    //wait until the locator is visible on the page
    protected void waitUntilVisible(String locatorString){
        log.info("waiting until "+locatorString+" is visible");
        page.locator(locatorString).nth(1).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }


}
