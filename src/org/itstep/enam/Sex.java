package org.itstep.enam;

public enum Sex {
    MEN("мужской"), WOMEN("женский");
    String sex;

    Sex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }
}
