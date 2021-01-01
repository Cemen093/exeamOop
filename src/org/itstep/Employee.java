package org.itstep;

import org.itstep.enam.Position;
import org.itstep.enam.Sex;

import java.util.Arrays;
import java.util.Formatter;
import java.util.Objects;

public class Employee {
    private String name;//ФИО сотрудника
    private final String dateOfBirth;//дату рождения // FIXME: 01.01.2021 Не удобно
    private String contactNumber;//контактный телефон
    private final int[] hiringDate;//дату приема на работу
    private int salary;//зарплату
    private Sex sex;//пол
    private Position position;//должность
    private Department department;//название отдела в котором работает сотрудник
    //return department.getChief(); в методах

    public Employee(String name, String dateOfBirth, String contactNumber, int[] hiringDate, int salary, Sex sex, Position position, Department department) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.contactNumber = contactNumber;
        this.hiringDate = hiringDate;
        this.salary = salary;
        this.sex = sex;
        this.position = position;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public int[] getHiringDate() {
        return hiringDate;
    }

    public int getSalary() {
        return salary;
    }

    public Sex getSex() {
        return sex;
    }

    public Position getPosition() {
        return position;
    }

    public Department getDepartment() {
        return department;
    }

    public Employee getChief() {
        return department.getChief();//шефа или имя шефа возвращать
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getString(){
        Formatter f = new Formatter();
        f.format("| %-17s | %-20s | %-20s | %-15s | %-15s | %-15s | %-15s |\n",
                name, dateOfBirth, contactNumber, "" + hiringDate[0] + '.' + hiringDate[1] + '.' + hiringDate[2],
                "" + salary, sex.getSex(), position.getPosition());
        return f.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) && Objects.equals(dateOfBirth, employee.dateOfBirth) && Objects.equals(hiringDate[0], employee.hiringDate[0]) && Objects.equals(hiringDate[1], employee.hiringDate[1]) && Objects.equals(hiringDate[2], employee.hiringDate[2]);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateOfBirth, hiringDate);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", hiringDate=" + Arrays.toString(hiringDate) +
                ", salary=" + salary +
                ", sex=" + sex.getSex() +
                ", position=" + position.getPosition() +
                ", chief=" + getChief().getName() +
                ", department=" + department.getNameDepartment() +
                '}';
    }
}
