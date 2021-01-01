package org.itstep.command.organization;

import org.itstep.Organization;

import java.util.*;

public class PrintReport implements CommandOrganization{
    //риложение должно иметь возможность создавать следующие отчеты: структура организации
    //(информация об отделах, ФИО начальников отделов), средняя зарплата по организации и по отделам,
    //топ–10 самых дорогих сотрудников по зарплате,
    //топ–10 самых преданных сотрудников по количеству лет работы в организации.

    @Override
    public void execute(Organization organization, String... arg) {
        Map<String, CommandPrintReport> map = new HashMap<>(){
            @Override
            public CommandPrintReport get(Object key) {
                CommandPrintReport com = super.get(key);
                return com == null ? new ComNotACommand(): com;
            }
        };
        map.put("структура компании" , new ComPrintStructureOrganization());
        map.put("средняя зарплата" , new ComPrintAverageSalary());
        map.put("топ 10 по зарплате" , new ComPrintTenMostExpensiveEmployeesBySalary());
        map.put("топ 10 по выслуге" , new ComPrintTopTenEmployeesByYearsInTheOrganization());

        Scanner scanner = new Scanner(System.in);
        String str = "";
        do {
            System.out.print("Доступные комманды: ");
            for (String s :
                    map.keySet()) {
                System.out.print(s + ", ");
            }
            System.out.print("выход\n >> ");

            str = scanner.nextLine().toLowerCase(Locale.ROOT);
            if (str.equals("выход")){
                break;
            }
            map.get(str).execute(organization);
        }while (true);
    }

    interface CommandPrintReport{
        void execute(Organization organization, String... arg);
    }

    static class ComPrintStructureOrganization implements CommandPrintReport{
        @Override
        public void execute(Organization organization, String... arg) {
            //структура организации
            organization.printStructOrganization();
        }
    }

    class ComPrintAverageSalary implements CommandPrintReport{
        @Override
        public void execute(Organization organization, String... arg) {
            //средняя зарплата по организации и по отделам
            System.out.print("Средняя зарплата в организации: ");
            System.out.println(organization.getAverageSalary());
            System.out.println("Средняя зарплата в организации: ");
            String[] name = organization.getNamesDepartments();
            int[] num = organization.getAverageSalaryDepartments();
            for (int i = 0; i < name.length; i++) {
                System.out.println("Департамент: " + name[i] + " средняя зарплата " + num[i]);
            }
            System.out.println();
        }
    }

    class ComPrintTenMostExpensiveEmployeesBySalary implements CommandPrintReport{
        @Override
        public void execute(Organization organization, String... arg) {
            organization.printTenMostExpensiveEmployeesBySalary();

        }
    }

    class ComPrintTopTenEmployeesByYearsInTheOrganization implements CommandPrintReport{
        @Override
        public void execute(Organization organization, String... str) {
            organization.printTopTenEmployeesByYearsInTheOrganization();
        }
    }

    class ComNotACommand implements CommandPrintReport{
        @Override
        public void execute(Organization organization, String... str) {
            System.out.println("Не комманда");
        }
    }
}
