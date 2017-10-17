package com.zlaja.avatest.security;

import com.zlaja.avatest.employee.Employee;
import com.zlaja.avatest.organization.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class AccessDecisionControl {

    private final ExternalAccessRuleRepository externalAccessRuleRepository;

    @Autowired
    public AccessDecisionControl(ExternalAccessRuleRepository externalAccessRuleRepository) {
        this.externalAccessRuleRepository = externalAccessRuleRepository;
    }

    public Set<AccessRight> getAccessRights(Employee employee, ProtectedResource protectedResource) {
        if (organizationsAreEqual(protectedResource.getOrganization(), employee.getOrganization()) || employee.getOrganization().isMainOrganization()) {
            return employee.getInternalRights();
        }
        Set<AccessRight> accessRightsFromExternalRules = getAccessRightsFromExternalRules(employee, protectedResource);
        accessRightsFromExternalRules.retainAll(employee.getInternalRights());
        return accessRightsFromExternalRules;
    }

    private Set<AccessRight> getAccessRightsFromExternalRules(Employee employee, ProtectedResource protectedResource) {
        List<ExternalAccessRule> rules = externalAccessRuleRepository.findByToOrganizationAndFromOrganizationAndIsApprovedByMainOrganization(employee.getOrganization(), protectedResource.getOrganization(), true);
        return rules.stream()
                .filter(externalAccessRule -> ruleSatisfied(externalAccessRule.getRule(), protectedResource))
                .map(ExternalAccessRule::getGrantedRights)
                .reduce(null, (a, b) -> {
                    if (a == null) {
                        a = new HashSet<>(b);
                    } else {
                        a.retainAll(b);
                    }
                    return a;
                });
    }

    private boolean ruleSatisfied(Rule rule, ProtectedResource protectedResource) {
        return ruleApplicableToDomainModel(rule, protectedResource) && rule.enforceRule(protectedResource);
    }

    private boolean ruleApplicableToDomainModel(Rule rule, ProtectedResource protectedResource) {
        return rule.getClass().equals(protectedResource.applicableRule());
    }

    private boolean organizationsAreEqual(Organization o1, Organization o2) {
        return o1.getId().equals(o2.getId());
    }
}
