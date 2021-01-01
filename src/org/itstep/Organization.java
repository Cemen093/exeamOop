package org.itstep;

import org.itstep.enam.Position;
import org.itstep.enam.Sex;

import java.util.*;

public class Organization {
    private String nameOrganization;
    private String siteOrganization;
    private ArrayList<Department> departments;
    private static final int[] CURRENT_DATA = {31, 12, 2020};
    List<String> rows = new ArrayList<>();
    public static final String dividingRow = "-----------------------------------------------------------------------------------------" +
            "--------------------------------------------------";

    public Organization(String nameOrganization, String siteOrganization) {
        this.nameOrganization = nameOrganization;
        this.siteOrganization = siteOrganization;
        departments = new ArrayList<>();
    }

    public void addNewEmployee(Employee employee) {
        //принимать на работу новых сотрудников
        getDepartmentForName(employee.getDepartment().getNameDepartment()).addEmployee(employee);
    }

    public Department getDepartmentForName(String nameDepartment) {
        for (int i = 0; i < departments.size(); i++) {
            if (departments.get(i).getNameDepartment().equals(nameDepartment)) {
                return departments.get(i);
            }
        }
        throw null;
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

    @Override
    public String toString() {
        return "Organization{" +
                "nameOrganization= " + nameOrganization + '\n' +
                ", siteOrganization= " + siteOrganization + '\n' +
                "departments= " + Arrays.toString(getNamesDepartments()) + '\n' +
                departments;
    }

    public boolean toFireEmployee(Employee e) {
        List<Employee> employees;
        for (Department d :
                departments) {
            if (d.toFireEmployee(e)) {
                return true;
            }
        }
        return false;
    }

    public Employee getEmployee(Employee e) {
        Employee employee;
        for (Department d :
                departments) {
            if (d.isEmployeeExist(e)) {
                employee = d.getEmployee(e);
                if (employee != null) {
                    return employee;
                }
            }
        }
        return null;
    }

    public void changeDepartmentEmployee(Employee employee, String nameNewDepartment) {
        employee.getDepartment().toFireEmployee(employee);
        Department d = getDepartmentForName(nameNewDepartment);
        if (d != null) {
            d.addEmployee(employee);
        }
    }

    public int getAverageSalary() {
        int averageSalary = 0;
        for (int num :
                getAverageSalaryDepartments()) {
            averageSalary += num;
        }
        averageSalary = departments.size() != 0 ? averageSalary / departments.size() : 0;
        return averageSalary;
    }

    public int[] getAverageSalaryDepartments() {
        int[] averageSalaryDepartments = new int[departments.size()];
        for (int i = 0; i < departments.size(); i++) {
            averageSalaryDepartments[i] = departments.get(i).getAverageSalary();
        }
        return averageSalaryDepartments;
    }

    public List<Employee> getAllEmployee() {
        List<Employee> employees = new ArrayList<>();
        for (Department d :
                departments) {
            employees.addAll(d.getAllEmployee());
        }
        return employees;
    }

    public void printEmployeesForName(String name) {
        List<Employee> employees = new ArrayList<>();
        for (Department department : departments) {
            employees.addAll(department.getEmployeesForName(name));
        }

        printReport(employees, "Сотрудники в компании с именем" + name);
    }

    public void printEmployeesForPosition(Position position) {
        List<Employee> employees = new ArrayList<>();
        for (Department department : departments) {
            employees.addAll(department.getEmployeeForPosition(position));
        }

        printReport(employees, "Сотрудники в компании на позиции " + position.getPosition());
    }

    public void printEmployeesForNameDepartment(String nameDepartment) {
        List<Employee> employees = new ArrayList<>();
        for (Department d :
                departments) {
            if (d.getNameDepartment().equals(nameDepartment)) {
                employees = d.getAllEmployee();
                break;
            }
        }

        printReport(employees, "Сотрудники в департаменте " + nameDepartment);
    }

    public void printEmployeesForNameChief(String nameChief) {
        List<Employee> employees = new ArrayList<>();
        for (Department department : departments) {
            if (department.getChief().getName().equals(nameChief)) {
                employees = department.getEmployees();//у первого шефа с именем?
                return;
            }
        }

        printReport(employees, "Сотрудники в компании в подчинении " + nameChief);
    }

    public void printStructOrganization() {

        printReport("Name Organization: " + nameOrganization,
                "Site Organization: " + siteOrganization);
        System.out.println();

        for (Department d :
                departments) {
            System.out.println();
            printReport(d.getChief() != null ? List.of(d.getChief()) : new ArrayList<>(), d.getNameDepartment(), "Шеф");
            printReport(d.getEmployees(), "Сотрудники");
        }
    }

    public void printTenMostExpensiveEmployeesBySalary() {
        List<Employee> employees = getAllEmployee();
        employees.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                if (o2.getSalary() > o1.getSalary()) {
                    return 1;
                }
                if (o2.getSalary() < o1.getSalary()) {
                    return -1;
                }
                return 0;
            }
        });
        int newLength = employees.size();
        if (newLength > 10) {
            newLength = 10;
        }

        employees = employees.subList(0, newLength);
        printReport(employees, "Топ 10 сотрудников по заработной плате");
    }

    public void printTopTenEmployeesByYearsInTheOrganization() {
        List<Employee> employees = getAllEmployee();
        employees.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {// FIXME: 31.12.2020 не верное сравнение дат
                if (CURRENT_DATA[2] - o2.getHiringDate()[2] > (CURRENT_DATA[2] - o1.getHiringDate()[2])) {
                    return 1;
                }
                if (CURRENT_DATA[2] - o2.getHiringDate()[2] < (CURRENT_DATA[2] - o1.getHiringDate()[2])) {
                    return -1;
                }
                return 0;
            }
        });
        int newLength = employees.size();
        if (newLength > 10) {
            newLength = 10;
        }

        employees = employees.subList(0, newLength);
        printReport(employees, "Топ 10 сотрудников по выслуге лет");
    }

    private void printReport(List<Employee> employees, String... headings) {
        for (String heading :
                headings) {

            System.out.println(dividingRow);
            System.out.format("| %-135s |\n", heading);
        }
        System.out.println(dividingRow);
        if (employees.isEmpty()) {
            System.out.format("| %-135s |\n", "Сотрудники не найдены");
        } else {
            for (Employee e :
                    employees) {
                System.out.print(e.getString());
                System.out.println(dividingRow);
            }
        }

    }

    private void printReport(String... headings) {
        for (String heading :
                headings) {

            System.out.println(dividingRow);
            System.out.format("| %-135s |\n", heading);
        }
        System.out.println(dividingRow);
    }

    public static Organization getWorkOrganization() {
        final Random random = new Random();
        Organization org = new Organization("Кукушкино", "https://qna.habr.com/");
        String[] depName = {"Департамент_1", "Департамент_2", "Департамент_3", "Департамент_4"};
        String[] empName = {"Джон", "Майк", "Антон", "Данил", "Вован", "Жека", "Максим", "Леха", "Юрий", "Иван"};
        org.addNewDepartment(depName[0]);
        org.addNewDepartment(depName[1]);
        org.addNewDepartment(depName[2]);
        org.addNewDepartment(depName[3]);

        for (int i = 0; i < 50; i++) {
            int pos = random.nextInt(Position.values().length);
            org.addNewEmployee(new Employee(empName[random.nextInt(depName.length)],
                    random.nextInt(29) + "." + random.nextInt(13) + "." + (random.nextInt(30) + 1980),
                    "+380" + random.nextInt(1000) + random.nextInt(1000) + random.nextInt(1000)
                    , new int[]{random.nextInt(29), random.nextInt(13), random.nextInt(10) + 2010},
                    (random.nextInt(7) + 3) * 10000, random.nextInt(2) == 1 ? Sex.MEN : Sex.WOMEN,
                    pos == 0 ? Position.CHEF : pos == 1 ? Position.PROGRAMMER : pos == 2 ? Position.TESTER : Position.CLEANER,
                    org.getDepartmentForName(depName[random.nextInt(depName.length)])));
        }
        return org;
    }
}
