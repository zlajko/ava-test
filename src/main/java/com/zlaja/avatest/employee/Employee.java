package com.zlaja.avatest.employee;

import com.zlaja.avatest.organization.Organization;
import com.zlaja.avatest.security.AccessRight;
import com.zlaja.avatest.security.EmployeeRule;
import com.zlaja.avatest.security.ProtectedResource;
import com.zlaja.avatest.security.Rule;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.Set;

public class Employee implements ProtectedResource {

    @Id
    private String id;
    private String name;
    private String username;
    private String password;
    private Organization organization;
    private Set<AccessRight> internalRights;

    @Transient
    private Set<AccessRight> rights;

    public Employee() {
    }

    public Employee(String name, String username, String password, Organization organization, Set<AccessRight> internalRights) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.organization = organization;
        this.internalRights = internalRights;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Set<AccessRight> getInternalRights() {
        return internalRights;
    }

    public void setInternalRights(Set<AccessRight> internalRights) {
        this.internalRights = internalRights;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Class<? extends Rule> applicableRule() {
        return EmployeeRule.class;
    }

    public Set<AccessRight> getRights() {
        return rights;
    }

    public void setRights(Set<AccessRight> rights) {
        this.rights = rights;
    }
}
