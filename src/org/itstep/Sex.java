package org.itstep;

public enum Sex {
    MEN("муржской"), WOMEN("женский");
    String sex;

    Sex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }
}
