package com.github;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepsTest {

    private static final String REPOSITORY = "jkammi/aliexpress_automated_tests";
    private static final String ISSUENAME = "TestIssue2";

    @Test
    public void testLambdaStep() {

        step("Opening the mainpage", () -> {
            open("https://github.com");
        });
        step("Looking for repository " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Clicking on the link of repository " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Opening tab Issues", () -> {
            $("#issues-tab").click();
        });
        step("Checking if the issue with this name exists: " + ISSUENAME, () -> {
            $(withText(ISSUENAME)).should(Condition.exist);
        });
    }

    @Test
    public void testAnnotatedStep() {
        WebSteps steps = new WebSteps();
        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithName(ISSUENAME);
    }
}