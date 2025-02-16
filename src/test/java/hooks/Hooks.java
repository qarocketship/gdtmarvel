package hooks;

import factory.PlaywrightFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;


public class Hooks {

    @Before
    public static void setup(){
        PlaywrightFactory.initialisePage();
    }


    @After
    public void pageClose(){
        PlaywrightFactory.closePage();

    }

    @AfterAll
    public static void tearDown(){
        PlaywrightFactory.closePlaywright();
    }

}
