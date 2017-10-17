package com.zlaja.avatest.security;

import com.zlaja.avatest.organization.Organization;

import java.util.Set;

public interface ProtectedResource {

    Organization getOrganization();

    Class<? extends Rule> applicableRule();

    void setRights(Set<AccessRight> rights);
}
