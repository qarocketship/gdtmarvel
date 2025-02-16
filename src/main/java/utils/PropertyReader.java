package utils;

import factory.PlaywrightFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//class to read the config.properties file
public class PropertyReader {

    private Properties properties;
    private Logger log;

    //constructor to load the properties file
    public PropertyReader() {
        log = LogManager.getLogger(this.getClass());
        File src = new File(Constants.PRO_FILE_PATH);
        try (FileInputStream fis = new FileInputStream(src)) {
            properties = new Properties();
            properties.load(fis);
            log.info("properties file loaded");

        } catch (IOException e) {
            log.error("Failed to load properties file "+e.getMessage());
            throw new RuntimeException("Failed to load properties file: " + src, e);
        }
    }


    //get the value of the provided key
    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            log.error("Property not found: "+ key);
            throw new RuntimeException("Property not found: " + key);
        }
        return value;
    }
}
