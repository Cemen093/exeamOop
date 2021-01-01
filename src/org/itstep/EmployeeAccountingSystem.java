package org.itstep;

import org.itstep.command.organization.*;

import java.util.HashMap;
import java.util.Scanner;
import static org.itstep.EmployeeAccountingSystem.COMMAND.*;

public class EmployeeAccountingSystem {
    //Основная задача проекта: хранить информацию о сотрудниках организации и структуре организации.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Organization organization = Organization.getWorkOrganization();

        HashMap<String, CommandOrganization> commandOrganization = new HashMap<>(){
            @Override
            public CommandOrganization get(Object key) {
                CommandOrganization tmp = super.get(key);
                return tmp == null ? new CommandNotFound(): tmp;
            }
        };
        commandOrganization.put(ADD_NEW_EMPLOYEE.getName(), new AddNewEmployee());//принимать на работу новых сотрудников
        commandOrganization.put(TO_FIRE_EMPLOYEE.getName(), new ToFireEmployee());//увольнять сотрудников
        commandOrganization.put(CHANGE_INFORMATION_EMPLOYEE.getName(), new ChangeInformationEmployee());//изменять информацию о сотрудниках
        commandOrganization.put(FIND_EMPLOYEES.getName(), new FindEmployee());//поиску сотрудников внутри организации по таким параметрам
        commandOrganization.put(PRINT_REPORT.getName(), new PrintReport());//создавать следующие отчеты
        commandOrganization.put(EXIT.getName(), new Exit());//Exit
        commandOrganization.put(ADD_NEW_DEPARTMENT.getName(), new AddNewDepartment());

        while (true){
            System.out.println("\nInput one of command");
            for (COMMAND name_command : COMMAND.values()) {
                System.out.println(name_command.getName());
            }
            System.out.print(">>  ");
            commandOrganization.get(scanner.nextLine()).execute(organization);
        }


    }

    enum COMMAND {
        ADD_NEW_EMPLOYEE("добавить нового сотрудника"), TO_FIRE_EMPLOYEE("уволить сотрудника"),
        CHANGE_INFORMATION_EMPLOYEE("изменить информацию о сотруднике"), FIND_EMPLOYEES("найти сотрудников"),
        PRINT_REPORT("распечатать отчет"), EXIT("выход"), ADD_NEW_DEPARTMENT("добавить новый департамент") ,COMMAND_NOT_FOUND("");

        String name;

        COMMAND(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
