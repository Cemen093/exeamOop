package org.itstep;

import org.itstep.exeption.ExceptionEmployeeNotFound;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.itstep.Organization.dividingRow;

public class Department {
    private String nameDepartment;
    private Employee chief;
    private ArrayList<Employee> employees;

    public Department(String nameDepartment) {
        this.nameDepartment = nameDepartment;
        this.chief = null;
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee){
        if (employee.getPosition() == Position.CHEF){
            chief = employee;// FIXME: 01.01.2021 Шефа увольняем?
        }else {
            employees.add(employee);
        }
        employee.setDepartment(this);
    }
    
    public boolean isEmployeeExist(Employee emp){
        if (chief.equals(emp)){
            return true;
        }
        for (Employee e: employees) {
            if (e.equals(emp)){
                return true;
            }
        }
        return false;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public Employee getChief() {
        return chief;
    }

    public void setChief(Employee chief) {
        this.chief = chief;
    }

    public List<Employee> getEmployeesForName(String name){
        List<Employee> list = new ArrayList<>();
        if (chief != null || chief.getName().equals(name)){
            list.add(chief);
        }
        for (Employee employee : employees) {
            if (employee.getName().equals(name)) {
                list.add(employee);
            }
        }
        return list;
    }

    public List<Employee> getEmployeeForPosition(Position position){
        List<Employee> list = new ArrayList<>();
        if (chief != null || chief.getPosition().equals(position)){
            list.add(chief);
        }
        for (Employee employee : employees) {
            if (employee.getPosition().equals(position)) {
                list.add(employee);
            }
        }
        return list;
    }

    public List<Employee> getAllEmployee(){
        List<Employee> list = new ArrayList<>();
        if (chief != null){
            list.add(chief);
        }
        list.addAll(employees);
        return list;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    @Override
    public String toString() {
        return "\nDepartment{" +
                "\nnameDepartment= " + nameDepartment +
                "\nchief= " + chief +
                "\nemployees= " + employees +
                '}';
    }

    public boolean toFireEmployee(Employee e) {
        if (chief.equals(e)){
            chief = null;// FIXME: 31.12.2020
            return true;
        }
        for (Employee emp :
                employees) {
            if (emp.equals(e)){
                employees.remove(emp);
                return true;
            }
        }
        return false;
    }

    public Employee getEmployee(Employee e) {
        if (chief.equals(e)){
            return chief;
        }
        for (Employee emp :
                employees) {
            if (emp.equals(e)){
                return emp;
            }
        }
        return null;
    }

    public int getAverageSalary() {
        int num = employees.size();
        int averageSalary = 0;
        if (getChief()!= null){
            averageSalary += getChief().getSalary();
            num++;
        }
        for (Employee e :
                employees) {
            averageSalary += e.getSalary();
        }
        return num != 0 ? averageSalary / num: 0;
    }

}
