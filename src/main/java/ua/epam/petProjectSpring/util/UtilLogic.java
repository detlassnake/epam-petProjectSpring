package ua.epam.petProjectSpring.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class UtilLogic {
    private static final Logger logger = LoggerFactory.getLogger(UtilLogic.class);

    public static ArrayList<String> getProperties () {
        logger.debug("UtilLogic->Get properties");
        ArrayList<String> applicationPropertiesArrayList = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream("src/main/resources/liquibase/liquibase.properties")) {
            Properties property = new Properties();
            property.load(fis);
            applicationPropertiesArrayList.add(property.getProperty("url"));
            applicationPropertiesArrayList.add(property.getProperty("username"));
            applicationPropertiesArrayList.add(property.getProperty("password"));
        } catch (IOException e) {
            logger.error("UtilLogic getProperties->IOException");
            e.printStackTrace();
        }
        return applicationPropertiesArrayList;
    }
}
