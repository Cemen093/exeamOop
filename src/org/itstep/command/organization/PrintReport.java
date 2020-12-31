package org.itstep.command.organization;

import org.itstep.Employee;
import org.itstep.Organization;

import java.util.Arrays;
import java.util.List;

public class PrintReport implements CommandOrganization{
    //риложение должно иметь возможность создавать следующие отчеты: структура организации
    //(информация об отделах, ФИО начальников отделов), средняя зарплата по организации и по отделам,
    //топ–10 самых дорогих сотрудников по зарплате,
    //топ–10 самых преданных сотрудников по количеству лет работы в организации.

    @Override
    public void execute(Organization organization, String... str) {
        System.out.println("Тут будут печататься различные отчеты");
        System.out.println(organization);

        System.out.println();
    }

    interface CommandPrintReport{
        void execute(Organization organization, String... arg);
    }

    class ComPrintStructureOrganization implements CommandPrintReport{

        @Override
        public void execute(Organization organization, String... arg) {
            //структура организации
            System.out.println(organization);
        }
    }

    class ComPrintAverageSalary implements CommandPrintReport{
        @Override
        public void execute(Organization organization, String... arg) {
            //средняя зарплата по организации и по отделам
            System.out.print("Средняя зарплата в организации: ");
            System.out.println(organization.getAverageSalary());
            System.out.print("Средняя зарплата в организации: ");
            String[] name = organization.getNamesDepartments();
            int[] num = organization.getAverageSalaryDepartments();
            for (int i = 0; i < name.length; i++) {
                System.out.println("Департамент: " + name[i] + "средняя зарплата" + num[i]);
            }
            System.out.println();
        }
    }

    class ComPrintTenMostExpensiveEmployeesBySalary implements CommandPrintReport{
        @Override
        public void execute(Organization organization, String... arg) {
            List<Employee> employees = organization.getTenMostExpensiveEmployeesBySalary();
            System.out.println(employees);
        }
    }

    class ComPrintTopTenEmployeesByYearsInTheOrganization implements CommandOrganization{
        @Override
        public void execute(Organization organization, String... str) {
            System.out.println(organization.getTopTenEmployeesByYearsInTheOrganization());
        }
    }
}
