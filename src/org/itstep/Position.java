package org.itstep;

public enum Position {
    CHEF("шеф"), PROGRAMMER("программист"), TESTER("тестер"), CLEANER("уборщик");
    private final String position;

    Position(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }
}
