package org.itstep;

import java.util.Objects;

public class Employee {
    private String name;//ФИО сотрудника
    private final String dateOfBirth;//дату рождения
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

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", hiringDate='" + hiringDate + '\'' +
                ", salary=" + salary +
                ", sex=" + sex +
                ", position=" + position +
                ", department=" + department.getNameDepartment() +
                ", chief=" + (department.getChief() != null ? department.getChief().getName(): "шефа нет") +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) && Objects.equals(dateOfBirth, employee.dateOfBirth) && Objects.equals(hiringDate, employee.hiringDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateOfBirth, hiringDate);
    }
}
