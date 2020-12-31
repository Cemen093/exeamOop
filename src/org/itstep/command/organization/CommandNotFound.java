package org.itstep.command.organization;

import org.itstep.Organization;

public class CommandNotFound implements CommandOrganization{
    @Override
    public void execute(Organization organization, String... str) {
        System.out.println("CommandNotFound");
    }
}
