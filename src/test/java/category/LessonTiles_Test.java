package category;

import data.LessonsCategoryData;
import org.openqa.selenium.WebElement;
import pages.BreedingCoursesPage;
import factory.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.DetailedCardCoursePage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LessonTiles_Test {
    private BreedingCoursesPage breedingCourses = null;

    private WebDriver driver;

    @BeforeEach
    public void init() {
        this.driver = new DriverFactory(driver).create();

        List<String> queryParams = new ArrayList<>();
        queryParams.add(String.format("categories=%s", LessonsCategoryData.TESTING.name().toLowerCase()));

        this.breedingCourses = new BreedingCoursesPage(driver);
        breedingCourses.open(queryParams);
    }

    @AfterEach
    public void stopDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void checkCountCourses() {
        breedingCourses.cardsCoursesCountShouldBeSameAs(10);
    }

    @Test
    public void checkDataCardCourse() {
        breedingCourses.clickRandomCardCourses();
        DetailedCardCoursePage detailedCardCoursePage = new DetailedCardCoursePage(driver, "");
        detailedCardCoursePage.checkTitleCourse();
        detailedCardCoursePage.checkDescriptionCourse();
        detailedCardCoursePage.checkCourseDuration();
        detailedCardCoursePage.checkCourseFormat("Онлайн");
        }
}

