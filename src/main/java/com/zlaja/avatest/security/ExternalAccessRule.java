package com.zlaja.avatest.security;

import com.zlaja.avatest.organization.Organization;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.Set;

public class ExternalAccessRule implements ProtectedResource {

    @Id
    private String id;
    private Organization fromOrganization;
    private Organization toOrganization;
    private Rule rule;
    private Set<AccessRight> grantedRights;
    private boolean isApprovedByMainOrganization;

    @Transient
    private Set<AccessRight> rights;

    public ExternalAccessRule() {
    }

    public ExternalAccessRule(Organization fromOrganization, Organization toOrganization, Rule rule, Set<AccessRight> grantedRights, boolean isApprovedByMainOrganization) {
        this.fromOrganization = fromOrganization;
        this.toOrganization = toOrganization;
        this.rule = rule;
        this.grantedRights = grantedRights;
        this.isApprovedByMainOrganization = isApprovedByMainOrganization;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Organization getFromOrganization() {
        return fromOrganization;
    }

    public void setFromOrganization(Organization fromOrganization) {
        this.fromOrganization = fromOrganization;
    }

    public Organization getToOrganization() {
        return toOrganization;
    }

    public void setToOrganization(Organization toOrganization) {
        this.toOrganization = toOrganization;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public Set<AccessRight> getGrantedRights() {
        return grantedRights;
    }

    public void setGrantedRights(Set<AccessRight> grantedRights) {
        this.grantedRights = grantedRights;
    }

    public boolean isApprovedByMainOrganization() {
        return isApprovedByMainOrganization;
    }

    public void setApprovedByMainOrganization(boolean approvedByMainOrganization) {
        isApprovedByMainOrganization = approvedByMainOrganization;
    }

    @Override
    public Organization getOrganization() {
        return fromOrganization;
    }

    @Override
    public Class<? extends Rule> applicableRule() {
        return null;
    }

    @Override
    public void setRights(Set<AccessRight> rights) {
        this.rights = rights;
    }

    public Set<AccessRight> getRights() {
        return rights;
    }
}
