package org.itstep;

import org.itstep.enam.Command;
import org.itstep.enam.Position;
import org.itstep.enam.Sex;
import java.util.*;
import static org.itstep.enam.Position.*;
import static org.itstep.enam.Sex.*;
import static org.itstep.enam.Command.*;

public class Main {
    //Основная задача проекта: хранить информацию о сотрудниках организации и структуре организации.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Organization organization = Organization.getWorkOrganization();

        HashMap<String, CommandOrganization> commandOrganization = new HashMap<>() {
            @Override
            public CommandOrganization get(Object key) {
                CommandOrganization tmp = super.get(key);
                return tmp == null ? new CommandNotFound() : tmp;
            }
        };
        commandOrganization.put(ADD_NEW_EMPLOYEE.getName(), new AddNewEmployee());//принимать на работу новых сотрудников
        commandOrganization.put(TO_FIRE_EMPLOYEE.getName(), new ToFireEmployee());//увольнять сотрудников
        commandOrganization.put(CHANGE_INFORMATION_EMPLOYEE.getName(), new ChangeInformationEmployee());//изменять информацию о сотрудниках
        commandOrganization.put(FIND_EMPLOYEES.getName(), new FindEmployee());//поиску сотрудников внутри организации по таким параметрам
        commandOrganization.put(PRINT_REPORT.getName(), new PrintReport());//создавать следующие отчеты
        commandOrganization.put(EXIT.getName(), new Exit());//Exit
        commandOrganization.put(ADD_NEW_DEPARTMENT.getName(), new AddNewDepartment());

        while (true) {
            System.out.println("\nInput one of command");
            for (Command name_command : Command.values()) {
                System.out.println(name_command.getName());
            }
            System.out.print(">>  ");
            commandOrganization.get(scanner.nextLine()).execute(organization);
        }


    }

    interface CommandOrganization {
        void execute(Organization organization, String... str);
    }

    private static class AddNewDepartment implements CommandOrganization {
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
                    if (names[i].equals(name)) {
                        System.out.println("Извините, но департамент с таким именем уже существует");
                        isDepartmentExist = true;
                        break;
                    }
                }
                if (!isDepartmentExist) {
                    organization.addNewDepartment(name);
                }

            } while (isDepartmentExist);
        }
    }

    private static class AddNewEmployee implements CommandOrganization {
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
                department = organization.getDepartmentForName(nameDepartment);
                if (department == null) {
                    System.out.println("Такой департамент не найден");
                    System.out.println("Существующие департаменты: " + organization.getDepartments());
                    checkInput = true;
                }

            } while (checkInput);

            department.addEmployee(new Employee(name, dateOfBirth, contactNumber, hiringDate, salary, sex, position, department));
        }
    }

    private static class ChangeInformationEmployee implements CommandOrganization {
        @Override
        public void execute(Organization organization, String... str) {
            //изменять информацию о сотрудниках
            System.out.println("Информация о сотруднике должна быть изменена");
            Scanner scanner = new Scanner(System.in);
            String[] question = {"Введите имя сотрудника", "Ведите дату рождения сотрудника", "Введите дату найма сотрудника"};
            String[] arg = new String[question.length - 1];
            int[] arg2 = new int[3];

            for (int i = 0; i < question.length - 1; i++) {
                System.out.println(question[i]);
                arg[i] = scanner.nextLine();
            }

            do {
                System.out.println("Введите дату найма сотрудника (день, месяц, год) череза пробел >>");
                Scanner scanner1 = new Scanner(System.in);
                try {
                    for (int i = 0; i < 3; i++) {
                        arg2[i] = scanner1.nextInt();
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Не корректный ввод");
                }
            } while (true);

            Employee employee = organization.getEmployee(
                    new Employee(arg[0], arg[1], null, arg2, 0, null, null, null));

            if (employee == null) {
                System.out.println("Сотрудник не найден");
            } else {

                Map<String, CommandChangeInf> command = new HashMap<>() {
                    @Override
                    public CommandChangeInf get(Object key) {
                        CommandChangeInf com = super.get(key);
                        return com != null ? com : new ComNotACommand();
                    }
                };
                command.put("имя", new ComChangeName());
                command.put("номер телефона", new ComChangeContactNumber());
                command.put("зарплату", new ComChangeSalary());
                command.put("пол", new ComChangeSex());
                command.put("должность", new ComChangePosition());
                command.put("департамент", new ComChangeDepartment());

                do {
                    System.out.println(employee + "\n");
                    System.out.print("Доступные варианты: ");
                    for (String s :
                            command.keySet()) {
                        System.out.print(s + ", ");
                    }
                    System.out.println("выход");
                    System.out.print("Какую информацию вы хотите изменить о сотруднике?\n>> ");
                    String line = scanner.nextLine();
                    if (line.equals("выход")) {
                        break;//не уверен как правильно выйти из комманды если только не передавать в нее объект с bool
                    }
                    command.get(line).execute(employee, organization);
                } while (true);

            }
        }

        private interface CommandChangeInf {
            void execute(Employee employee, Organization organization, String... str);
        }

        private class ComChangeName implements CommandChangeInf {
            @Override
            public void execute(Employee employee, Organization organization, String... str) {
                Scanner scanner = new Scanner(System.in);
                String name = "";
                do {
                    System.out.print("Введите новое имя сотрудника >> ");
                    name = scanner.nextLine();
                } while (name.isEmpty());
                employee.setName(name);
            }
        }

        private class ComChangeContactNumber implements CommandChangeInf {
            @Override
            public void execute(Employee employee, Organization organization, String... str) {
                Scanner scanner = new Scanner(System.in);
                String contactNumber = "";
                do {
                    System.out.print("Введите новый номер телефона сотрудника >> ");
                    contactNumber = scanner.nextLine();
                } while (contactNumber.isEmpty());
                employee.setContactNumber(contactNumber);
            }
        }

        private class ComChangeSalary implements CommandChangeInf {
            @Override
            public void execute(Employee employee, Organization organization, String... str) {
                Scanner scanner = new Scanner(System.in);
                int salary;
                System.out.print("Введите новую зарплату сотрудника >> ");
                salary = scanner.nextInt();
                employee.setSalary(salary);
            }
        }

        private class ComChangeSex implements CommandChangeInf {
            @Override
            public void execute(Employee employee, Organization organization, String... str) {
                Scanner scanner = new Scanner(System.in);
                do {
                    System.out.print("Доступные варианты: ");
                    for (Sex s :
                            Sex.values()) {
                        System.out.print(s.getSex() + ", ");
                    }

                    System.out.print("Введите новый пол сотрудника >> ");
                    String string = scanner.nextLine();

                    if (string.equals(MEN.getSex())) {
                        employee.setSex(MEN);
                        return;
                    }
                    if (string.equals(WOMEN.getSex())) {
                        employee.setSex(WOMEN);
                        return;
                    }
                } while (true);
            }
        }

        private class ComChangePosition implements CommandChangeInf {
            @Override
            public void execute(Employee employee, Organization organization, String... str) {
                Scanner scanner = new Scanner(System.in);

                do {
                    System.out.print("Доступные варианты: ");
                    for (Position p :
                            Position.values()) {
                        System.out.print(p.getPosition() + ", ");
                    }

                    System.out.print("Введите новую должность сотрудника сотрудника >> ");
                    String string = scanner.nextLine();

                    for (Position p :
                            Position.values()) {
                        if (p.getPosition().equals(string)) {
                            employee.setPosition(p);
                            return;
                        }
                    }
                    System.out.println("Такая позиция не найдена");
                } while (true);
            }
        }

        private class ComChangeDepartment implements CommandChangeInf {
            @Override
            public void execute(Employee employee, Organization organization, String... str) {
                Scanner scanner = new Scanner(System.in);

                do {
                    String[] departments = organization.getNamesDepartments();
                    System.out.print("Доступные варианты: ");
                    for (String s :
                            departments) {
                        System.out.print(s + ", ");
                    }

                    System.out.print("Введите новый департамент сотрудника сотрудника >> ");
                    String nameNewDepartment = scanner.nextLine();

                    for (String s :
                            departments) {
                        if (s.equals(nameNewDepartment)) {
                            organization.changeDepartmentEmployee(employee, nameNewDepartment);
                            return;
                        }
                    }

                    System.out.println("Такая депортамент не найден не найдена");
                } while (true);
            }
        }

        private class ComNotACommand implements CommandChangeInf {
            @Override
            public void execute(Employee employee, Organization organization, String... str) {
                System.out.println("Не является коммандой");
            }
        }
    }

    private static class CommandNotFound implements CommandOrganization {
        @Override
        public void execute(Organization organization, String... str) {
            System.out.println("CommandNotFound");
        }
    }

    private static class Exit implements CommandOrganization {
        @Override
        public void execute(Organization organization, String... str) {
            System.exit(0);
        }
    }

    private static class FindEmployee implements CommandOrganization {
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


            } while (checkInput);

        }

        private String getStr(String str) {
            Scanner scanner = new Scanner(System.in);
            String str2 = "";
            do {
                System.out.println("Введите " + str + " сотрудника для поиска");
                str2 = scanner.nextLine();
            } while (str2.isEmpty());
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

    private static class PrintReport implements CommandOrganization {
        //риложение должно иметь возможность создавать следующие отчеты: структура организации
        //(информация об отделах, ФИО начальников отделов), средняя зарплата по организации и по отделам,
        //топ–10 самых дорогих сотрудников по зарплате,
        //топ–10 самых преданных сотрудников по количеству лет работы в организации.

        @Override
        public void execute(Organization organization, String... arg) {
            Map<String, CommandPrintReport> map = new HashMap<>() {
                @Override
                public CommandPrintReport get(Object key) {
                    CommandPrintReport com = super.get(key);
                    return com == null ? new ComNotACommand() : com;
                }
            };
            map.put("структура компании", new ComPrintStructureOrganization());
            map.put("средняя зарплата", new ComPrintAverageSalary());
            map.put("топ 10 по зарплате", new ComPrintTenMostExpensiveEmployeesBySalary());
            map.put("топ 10 по выслуге", new ComPrintTopTenEmployeesByYearsInTheOrganization());

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
                if (str.equals("выход")) {
                    break;
                }
                map.get(str).execute(organization);
            } while (true);
        }

        interface CommandPrintReport {
            void execute(Organization organization, String... arg);
        }

        static class ComPrintStructureOrganization implements CommandPrintReport {
            @Override
            public void execute(Organization organization, String... arg) {
                //структура организации
                organization.printStructOrganization();
            }
        }

        class ComPrintAverageSalary implements CommandPrintReport {
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

        class ComPrintTenMostExpensiveEmployeesBySalary implements CommandPrintReport {
            @Override
            public void execute(Organization organization, String... arg) {
                organization.printTenMostExpensiveEmployeesBySalary();

            }
        }

        class ComPrintTopTenEmployeesByYearsInTheOrganization implements CommandPrintReport {
            @Override
            public void execute(Organization organization, String... str) {
                organization.printTopTenEmployeesByYearsInTheOrganization();
            }
        }

        class ComNotACommand implements CommandPrintReport {
            @Override
            public void execute(Organization organization, String... str) {
                System.out.println("Не комманда");
            }
        }
    }

    private static class ToFireEmployee implements CommandOrganization {
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

            System.out.println("Введите дату найма сотрудника (день, месяц, год) череза пробел >>");
            for (int i = 0; i < 3; i++) {
                arg2[i] = Integer.parseInt(scanner.next());// FIXME: 31.12.2020 Проверки
            }

            if (organization.toFireEmployee(new Employee(arg[0], arg[1], null, arg2, 0, null, null, null))) {
                System.out.println("Сотрудник был уволен");
            } else {
                System.out.println("Очень жаль, но мы не смогли найти такого сотрудника");
            }
        }
    }
}




