package org.itstep.command.organization;

import org.itstep.Organization;
import org.itstep.exeption.ExceptionDepartmentNotFound;

import java.util.Scanner;

public class AddNewDepartment implements CommandOrganization {
    @Override
    public void execute(Organization organization, String... str) {
        Scanner scanner = new Scanner(System.in);
        boolean isDepartmentExist = false;
        do {
            isDepartmentExist = false;
            System.out.print("Введите имя департамента который вы хотите добавить\n >>");
            String name = scanner.nextLine();

            String[] names = organization.getNamesDepartments();
            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(name)){
                    System.out.println("Извините, но департамент с таким именем уже существует");
                    isDepartmentExist = true;
                    break;
                }
            }
            if (!isDepartmentExist){
                organization.addNewDepartment(name);
            }

        } while (isDepartmentExist);
    }
}
