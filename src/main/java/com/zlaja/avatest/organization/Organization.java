package com.zlaja.avatest.organization;

import org.springframework.data.annotation.Id;

public class Organization {

    @Id
    private String id;
    private String name;
    private boolean isMainOrganization;

    public Organization() {
    }

    public Organization(String name, boolean isMainOrganization) {
        this.name = name;
        this.isMainOrganization = isMainOrganization;
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

    public boolean isMainOrganization() {
        return isMainOrganization;
    }

    public void setMainOrganization(boolean mainOrganization) {
        isMainOrganization = mainOrganization;
    }
}
