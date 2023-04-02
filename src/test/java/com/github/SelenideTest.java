package com.github;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideTest {

    private static final String REPOSITORY = "jkammi/aliexpress_automated_tests";
    private static final String ISSUENAME = "TestIssue2";

    @BeforeAll
    static void configure() {
        Configuration.browserSize = "1920x1080";
        Configuration.browserPosition = "0x0";
        WebDriverManager.chromedriver().setup();
    }

    @Test
    @DisplayName("Clear Selenide test (with Listener)")
    public void selenidetest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://github.com");
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(REPOSITORY);
        $(".header-search-input").submit();
        $(By.linkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $(withText(ISSUENAME)).should(Condition.exist);
    }
}
