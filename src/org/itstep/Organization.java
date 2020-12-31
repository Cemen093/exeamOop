package org.itstep;

import org.itstep.exeption.ExceptionDepartmentNotFound;
import org.itstep.exeption.ExceptionEmployeeNotFound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Organization {
    private String nameOrganization;
    private String siteOrganization;
    private ArrayList<Department> departments;
    private static final int[] CURRENT_DATA = {31, 12, 2020};

    public Organization(String nameOrganization, String siteOrganization) {
        this.nameOrganization = nameOrganization;
        this.siteOrganization = siteOrganization;
        departments = new ArrayList<>();
    }

    public void addNewEmployee(Employee employee) {
        //принимать на работу новых сотрудников
        addNewEmployee(employee);
    }

    public Department getDepartmentForName(String nameDepartment) throws ExceptionDepartmentNotFound {
        for (int i = 0; i < departments.size(); i++) {
            if (departments.get(i).getNameDepartment().equals(nameDepartment)) {
                return departments.get(i);
            }
        }
        throw new ExceptionDepartmentNotFound("Департамент не найден");
    }

    public int getNumDepartments() {
        return departments.size();
    }

    public void addNewDepartment(String name) {
        departments.add(new Department(name));
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public String[] getNamesDepartments() {
        int size = departments.size();
        String[] names = new String[size];
        for (int i = 0; i < size; i++) {
            names[i] = departments.get(i).getNameDepartment();
        }
        return names;
    }

    public void show() {
        System.out.println(this);

    }

    @Override
    public String toString() {
        return "Organization{" +
                "nameOrganization= " + nameOrganization + '\n' +
                ", siteOrganization= " + siteOrganization + '\n' +
                "departments= " + Arrays.toString(getNamesDepartments()) + '\n' +
                departments;
    }

    public List<Employee> getEmployeesForName(String name) {
        List<Employee> employees = new ArrayList<>();
        for (Department department : departments) {
            employees.addAll(department.getEmployeesForName(name));
        }
        return employees;
    }

    public List<Employee> getEmployeesForPosition(Position position) {
        List<Employee> employees = new ArrayList<>();
        for (Department department : departments) {
            employees.addAll(department.getEmployeeForPosition(position));
        }
        return employees;
    }

    public List<Employee> getEmployeesForNameDepartment(String nameDepartment) {
        List<Employee> employees = new ArrayList<>();
        for (Department d :
                departments) {
            if (d.getNameDepartment().equals(nameDepartment)) {
                return d.getAllEmployee();
            }
        }
        return employees;
    }

    public List<Employee> getEmployeesForNameChief(String nameChief) {
        List<Employee> employees = new ArrayList<>();
        for (Department department : departments) {
            if (department.getChief().getName().equals(nameChief)){
                return department.getEmployees();
            }
        }
        return employees;
    }

    public boolean toFireEmployee(Employee e){
        List<Employee> employees;
        for (Department d :
                departments) {
            if (d.isEmployeeExist(e)){
                d.toFireEmployee(e);
                return true;
            }
        }
        return false;
    }

    public Employee getEmployee(Employee e) {
        Employee employee;
        for (Department d :
                departments) {
            if (d.isEmployeeExist(e)){
                employee = d.getEmployee(e);
                if (employee != null){
                    return employee;
                }
            }
        }
        return null;
    }

    public void changeDepartmentEmployee(Employee employee, String nameNewDepartment) {
        employee.getDepartment().toFireEmployee(employee);
        try {
            getDepartmentForName(nameNewDepartment).addEmployee(employee);
        } catch (ExceptionDepartmentNotFound exceptionDepartmentNotFound) {
            System.out.println("Ой, департамент не найден");
        }
    }

    public int getAverageSalary() {
        int averageSalary = 0;
        for (int num :
                getAverageSalaryDepartments()) {
            averageSalary += num;
        }
        averageSalary = departments.size() != 0 ? averageSalary / departments.size(): 0;
        return averageSalary;
    }

    public int[] getAverageSalaryDepartments() {
        int[] averageSalaryDepartments = new int[departments.size()];
        for (int i = 0; i < departments.size(); i++) {
            averageSalaryDepartments[i] = departments.get(i).getAverageSalary();
        }
        return averageSalaryDepartments;
    }

    public List<Employee> getTenMostExpensiveEmployeesBySalary() {
        List<Employee> employees = getAllEmployee();
        employees.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                if (o2.getSalary() > o1.getSalary()){return -1;}// FIXME: 31.12.2020 Тут еще смотреть надо
                if (o2.getSalary() < o1.getSalary()){return 1;}
                return 0;
            }
        });
        int newLength = employees.size();
        if (newLength > 10){
            newLength = 10;
        }

        return employees.subList(0, newLength);
    }

    public List<Employee> getAllEmployee(){
        List<Employee> employees = new ArrayList<>();
        for (Department d :
                departments) {
            employees.addAll(d.getAllEmployee());
        }
        return employees;
    }

    public List<Employee> getTopTenEmployeesByYearsInTheOrganization(){
        List<Employee> employees = getAllEmployee();
        employees.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {// FIXME: 31.12.2020 не верное сравнение дат
                if (CURRENT_DATA[3] - o2.getHiringDate()[3] > (CURRENT_DATA[3] - o1.getHiringDate()[3])){return -1;}// FIXME: 31.12.2020 Тут еще смотреть надо
                if (CURRENT_DATA[3] - o2.getHiringDate()[3] < (CURRENT_DATA[3] - o1.getHiringDate()[3])){return 1;}
                return 0;
            }
        });
        int newLength = employees.size();
        if (newLength > 10){
            newLength = 10;
        }

        return employees.subList(0, newLength);
    }
}
