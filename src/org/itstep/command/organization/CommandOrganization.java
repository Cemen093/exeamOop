package org.itstep.command.organization;

import org.itstep.Organization;

public interface CommandOrganization {
    void execute(Organization organization, String... str);
}
