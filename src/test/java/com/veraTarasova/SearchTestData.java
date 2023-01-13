package com.veraTarasova;

public enum SearchTestData {
    CYRILLIC("Дженна Ортега"),
    LATIN("Wednesday Addams");

    private String value;

    public String getValue() {
        return value;
    }

    SearchTestData(String value) {
        this.value = value;
    }

}
