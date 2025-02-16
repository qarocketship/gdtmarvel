package factory;

import com.microsoft.playwright.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.PropertyReader;

import java.util.ArrayList;

public class PlaywrightFactory {

    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext browserContext;
    private static Page page;
    private static PropertyReader propertyReader;
    private static Logger log;


    public static void initialisePage() {
        log = LogManager.getLogger(PlaywrightFactory.class);
        //To start the browser in maximized state
        ArrayList<String> arguments = new ArrayList<>();
        arguments.add("--start-maximized");

        //read the config.properties file
        propertyReader = new PropertyReader();
        String browserType = propertyReader.getProperty("browser");
        Boolean headless = Boolean.parseBoolean(propertyReader.getProperty("headless"));

        //Create Playwright instance
        if (playwright == null) {
            playwright = Playwright.create();
            log.info("Playwright instance created");
        }

        //create browser instance based on the browser entered in the config.properties file
        switch (browserType.toLowerCase()) {
            case "chrome":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless).setChannel("chrome").setArgs(arguments));
                log.info("Chrome browser initialised");
                break;
            case "edge":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless).setChannel("msedge").setArgs(arguments));
                log.info("Edge browser initialised");
                break;
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless).setArgs(arguments));
                log.info("Firefox browser initialised");
                break;
            default:
                log.error("Browser type not supported");
                throw new IllegalArgumentException("Browser type not supported");
        }

        //create BrowserContext instance
        browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        log.info("BrowserContext initialised");

        //create Page instance
        page = browserContext.newPage();
        log.info("New page initialised");

    }


    public static Page getPage(){
        //Navigate to the url
        page.navigate(propertyReader.getProperty("url"));
        log.info("navigated to url");
        return page;
    }

    //close page, browserContext and browser
    public static void closePage() {
        page.close();
        log.info("page closed");

        browserContext.close();
        log.info("browserContext closed");

        browser.close();
        log.info("browser closed");
    }

    //close playwright
    public static void closePlaywright() {
        log.info("closing Playwright");
        playwright.close();


    }
}

