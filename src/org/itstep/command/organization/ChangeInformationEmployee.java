package org.itstep.command.organization;

import org.itstep.Employee;
import org.itstep.Organization;
import org.itstep.Position;
import org.itstep.Sex;

import java.util.*;

import static org.itstep.Sex.MEN;
import static org.itstep.Sex.WOMEN;

public class ChangeInformationEmployee implements CommandOrganization {
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
        }while (true);

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
