package org.itstep.command.organization;

import org.itstep.Employee;
import org.itstep.Organization;

import java.util.Scanner;

public class ToFireEmployee implements CommandOrganization{
    @Override
    public void execute(Organization organization, String... str) {
        Scanner scanner = new Scanner(System.in);
        String[] question = {"Введите имя сотрудника", "Ведите дату рождения сотрудника", "Введите дату найма сотрудника"};
        String[] arg = new String[question.length - 1];
        int[] arg2 = new int[3];

        for (int i = 0; i < question.length - 1; i++) {
            System.out.println(question[i]);
            arg[i] = scanner.nextLine();
       }

        System.out.println("Введите дату найма сотрудника (день, месяц, год) череза пробкл >>");
        Scanner scanner1 = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            arg2[i] = scanner1.nextInt();// FIXME: 31.12.2020 Проверки
        }

        if (organization.toFireEmployee(new Employee(arg[0], arg[1], null, arg2, 0, null, null, null))){
            System.out.println("Сотрудник был уволен");
        }else {
            System.out.println("Очень жаль, но мы не смогли найти такого сотрудника");
        }
    }
}
