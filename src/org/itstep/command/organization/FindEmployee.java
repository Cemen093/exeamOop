package org.itstep.command.organization;

import org.itstep.Organization;
import org.itstep.Position;

import java.util.Scanner;


public class FindEmployee implements CommandOrganization {
    //Приложение должно предоставить функциональность по поиску сотрудников внутри организации
    // по таким параметрам: ФИО, должности, названию отдела, ФИО начальника.

    @Override
    public void execute(Organization organization, String... str) {
        //Приложение должно предоставить функциональность по поиску сотрудников внутри организации по таким параметрам:
        // ФИО, должности, названию отдела, ФИО начальника

        Scanner scanner = new Scanner(System.in);
        System.out.println("Варианты: ФИО, должности, названию отдела, ФИО начальника, выход");
        System.out.println("По какому параметру искать сотрудников >> ");

        boolean checkInput = true;
        do {
            switch (scanner.nextLine().toLowerCase()) {
                case "фио" -> organization.printEmployeesForName(getStr("ФИО"));
                case "должности" -> organization.printEmployeesForPosition(getPosition());
                case "названию отдела" -> organization.printEmployeesForNameDepartment(getStr("название отдела"));
                case "фио начальника" -> organization.printEmployeesForNameChief(getStr("ФИО начальника"));
                case "выход" -> checkInput = false;
                default -> System.out.println("Не корректная команда");
            }


        }while (checkInput);

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
