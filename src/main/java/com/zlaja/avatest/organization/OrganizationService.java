package com.zlaja.avatest.organization;

import com.zlaja.avatest.employee.Employee;
import com.zlaja.avatest.employee.EmployeeRepository;
import com.zlaja.avatest.security.ExternalAccessRule;
import com.zlaja.avatest.security.ExternalAccessRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrganizationService {

    private final EmployeeRepository employeeRepository;
    private final ExternalAccessRuleRepository externalAccessRuleRepository;

    @Autowired
    public OrganizationService(EmployeeRepository employeeRepository, ExternalAccessRuleRepository externalAccessRuleRepository) {
        this.employeeRepository = employeeRepository;
        this.externalAccessRuleRepository = externalAccessRuleRepository;
    }

    /**
     * This method returns all organizations to which he is connected via {@link com.zlaja.avatest.security.ExternalAccessRule}.
     *
     * @return returns all organizations to which he is connected via {@link com.zlaja.avatest.security.ExternalAccessRule}.
     */
    public Set<Organization> getOrganizationsForCurrentUser() {
        Employee employee = employeeRepository.findByUsername(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        List<ExternalAccessRule> rules = externalAccessRuleRepository.findByToOrganizationAndIsApprovedByMainOrganization(employee.getOrganization(), true);

        Set<Organization> organizations = rules.stream().
                map(ExternalAccessRule::getFromOrganization)
                .collect(Collectors.toSet());
        organizations.add(employee.getOrganization());

        return organizations;
    }
}
