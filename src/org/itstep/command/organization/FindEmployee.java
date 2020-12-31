package org.itstep.command.organization;

import org.itstep.Employee;
import org.itstep.Organization;
import org.itstep.Position;

import java.util.List;
import java.util.Scanner;


public class FindEmployee implements CommandOrganization {
    //Приложение должно предоставить функциональность по поиску сотрудников внутри организации
    // по таким параметрам: ФИО, должности, названию отдела, ФИО начальника.

    @Override
    public void execute(Organization organization, String... str) {
        //Приложение должно предоставить функциональность по поиску сотрудников внутри организации по таким параметрам:
        // ФИО, должности, названию отдела, ФИО начальника

        Scanner scanner = new Scanner(System.in);
        System.out.println("Варианты: ФИО, должности, названию отдела, ФИО начальника");
        System.out.println("По какому параметру искать сотрудников >> ");

        boolean checkInput = false;

        List<Employee> employees;

        switch (scanner.nextLine().toLowerCase()){
            case "ФИО":
                employees = organization.getEmployeesForName(getStr("ФИО"));
                break;
            case "должности":
                employees = organization.getEmployeesForPosition(getPosition());
                break;
            case "названию отдела":
                employees = organization.getEmployeesForNameDepartment(getStr("название отдела"));
                break;
            case "ФИО начальника":
                employees = organization.getEmployeesForNameChief(getStr("ФИО начальника"));
                break;
            default:
                break;
        }

    }
    private String getStr(String str){
        Scanner scanner = new Scanner(System.in);
        String str2 = "";
        do {
            System.out.println("Введите " + str + " сотрудника для поиска");
            str2 = scanner.nextLine();
        }while (str2.isEmpty());
        return str2;
    }

    private Position getPosition() {
        Scanner scanner = new Scanner(System.in);
        String str = "";
        do {
            System.out.println("Введите должность сотрудника для поиска");
            str = scanner.nextLine();
            for (Position p :
                    Position.values()) {
                if (p.getPosition().equals(str)) {
                    return p;
                }
            }
        } while (true);
    }
}
