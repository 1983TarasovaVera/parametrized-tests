package com.veraTarasova;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SearchTests {
    @Test
    void successfulSearchTest() {
        open("https://www.google.com/");
        $("[name=q]").setValue("selenide").pressEnter();
        $("[id=search]").shouldHave(text("https://ru.selenide.org"));
    }

    @ValueSource (strings = {
            "Дженна", "Wednesday"
    })
    @ParameterizedTest
    void googleSearchTestWithValueSource(String searchText) {
        open("https://www.google.com/");
        $("[name=q]").setValue(searchText).pressEnter();
        $("#result-stats").shouldHave(text("Результатов: примерно"));
    }

    @CsvSource(value = {
            "Test with Cyrillic, Дженна, Ортега",
            "Test with Latin script, Wednesday, Addams"
    })
    @ParameterizedTest(name = "{1}")
    void googleSearchTestWithCsvSource(String name, String firstName, String secondName) {
        open("https://www.google.com/");
        $("[name=q]").setValue(firstName + " " + secondName).pressEnter();
        $("#result-stats").shouldHave(text("Результатов: примерно"));
    }

    @EnumSource(SearchTestData.class)
    @ParameterizedTest()
    void googleSearchTestWithEnumSource(SearchTestData searchTestData) {
        open("https://www.google.com/");
        $("[name=q]").setValue(searchTestData.getValue()).pressEnter();
        $("#result-stats").shouldHave(text("Результатов: примерно"));
    }

    static Stream<Arguments> googleSearchTestWithMethodSource() {
        return Stream.of(
                Arguments.of(
                        "Дженна Ортега"
                ),
                Arguments.of(
                        "Wednesday Addams"
                )

        );
    }

    @MethodSource("googleSearchTestWithMethodSource")
    @ParameterizedTest()
    void googleSearchTestWithMethodSource(String argument) {
        open("https://www.google.com/");
        $("[name=q]").setValue(argument).pressEnter();
        $("#result-stats").shouldHave(text("Результатов: примерно"));
    }

}
