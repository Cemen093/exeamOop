package org.itstep.command.organization;

import org.itstep.*;
import org.itstep.exeption.ExceptionDepartmentNotFound;

import java.util.InputMismatchException;
import java.util.Scanner;

import static org.itstep.Position.*;

public class AddNewEmployee implements CommandOrganization {
    //принимать на работу новых сотрудников
    private static final int numArg = 9;//єнам создавать не буду.

    @Override
    public void execute(Organization organization, String... arg) {
        //создадим сотрудника
        Scanner scanner = new Scanner(System.in);
        String name;
        String dateOfBirth;
        String contactNumber;
        int[] hiringDate;
        int salary;
        Sex sex = null;//жалуется на возможную не инициализацию
        Position position = null;//жалуется на возможную не инициализацию
        Department department = null;//жалуется на возможную не инициализацию

        System.out.print("Добавление нового сотрудника");
        System.out.print("Введите имя сотрудника >> ");
        name = scanner.nextLine();

        System.out.print("Введите дату рождения сотрудника >> ");
        dateOfBirth = scanner.nextLine();

        System.out.print("Введите номер телефона сотрудника >> ");
        contactNumber = scanner.nextLine();

        hiringDate = new int[3];
        do {
            System.out.println("Введите дату найма сотрудника (день, месяц, год) череза пробел >>");
            Scanner scanner1 = new Scanner(System.in);
            try {
                for (int i = 0; i < 3; i++) {
                    hiringDate[i] = scanner1.nextInt();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Не корректный ввод");
            }
        } while (true);


        do {
            System.out.print("Введите зарплату сотрудника >> ");
            try {
                salary = Integer.parseInt(scanner.nextLine());
                break;
            } catch (InputMismatchException e) {
                System.out.println("Не корректный ввод");
            }
        } while (true);

        boolean checkInput = false;
        do {
            checkInput = false;
            String[] choice = {"мужской", "мужик", "парень", "человек", "женский", "женщина", "девочка", "не человек"};//8)
            System.out.print("Введите пол сотрудника >> ");
            String tmp = scanner.nextLine().toLowerCase();
            if (tmp.equals(choice[0]) || tmp.equals(choice[1]) || tmp.equals(choice[2]) || tmp.equals(choice[3])) {
                sex = Sex.MEN;
            } else if (tmp.equals(choice[4]) || tmp.equals(choice[5]) || tmp.equals(choice[6]) || tmp.equals(choice[7])) {
                sex = Sex.WOMEN;
            } else {
                System.out.println("Нам очень жаль, но мы не поняли ваш запрос");
                System.out.print("Доступные варианты: ");
                for (String s : choice) {
                    System.out.print(s + ", ");
                }
                System.out.println();
                checkInput = true;
            }
        } while (checkInput);


        do {
            checkInput = false;
            System.out.println("В данный момент в компании доступны следующие должности: ");
            for (Position p :
                    Position.values()) {
                System.out.print(p.getPosition() + ", ");
            }
            System.out.print("Введите должность нового сотрудника >> ");
            String positionNew = scanner.nextLine();
            if (CHEF.getPosition().equals(positionNew)) {// FIXME: 30.12.2020 Что если шеф уже существует?
                position = CHEF;
            } else if (PROGRAMMER.getPosition().equals(positionNew)) {
                position = PROGRAMMER;
            } else if (TESTER.getPosition().equals(positionNew)) {
                position = TESTER;
            } else if (CLEANER.getPosition().equals(positionNew)) {
                position = Position.CLEANER;
            } else {
                checkInput = true;
                System.out.println("Введенные данные не корректны");
            }
        } while (checkInput);

        do {
            checkInput = false;
            System.out.print("Введите название департамента для нового сотрудника >>");
            String nameDepartment = scanner.nextLine();
            try {
                department = organization.getDepartmentForName(nameDepartment);
            } catch (ExceptionDepartmentNotFound exceptionDepartmentNotFound) {
                System.out.println("Такой департамент не найден");
                System.out.println("Существующие департаменты: " + organization.getDepartments());
                checkInput = true;
            }

        } while (checkInput);

        department.addEmployee(new Employee(name, dateOfBirth, contactNumber, hiringDate, salary, sex, position, department));
    }
}
