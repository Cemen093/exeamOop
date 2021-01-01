package org.itstep.enam;

public enum Command {
    ADD_NEW_EMPLOYEE("добавить нового сотрудника"), TO_FIRE_EMPLOYEE("уволить сотрудника"),
    CHANGE_INFORMATION_EMPLOYEE("изменить информацию о сотруднике"), FIND_EMPLOYEES("найти сотрудников"),
    PRINT_REPORT("распечатать отчет"), EXIT("выход"), ADD_NEW_DEPARTMENT("добавить новый департамент"), COMMAND_NOT_FOUND("");

    String name;

    Command(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}