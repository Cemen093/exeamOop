package org.itstep.command.organization;

import org.itstep.Organization;

public class Exit implements CommandOrganization{
    @Override
    public void execute(Organization organization, String... str) {
        System.exit(0);
    }
}
