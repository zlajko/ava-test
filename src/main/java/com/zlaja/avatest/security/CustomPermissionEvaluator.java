package com.zlaja.avatest.security;

import com.zlaja.avatest.employee.Employee;
import com.zlaja.avatest.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    private final AccessDecisionControl accessDecisionControl;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public CustomPermissionEvaluator(AccessDecisionControl accessDecisionControl, EmployeeRepository employeeRepository) {
        this.accessDecisionControl = accessDecisionControl;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object o, Object o1) {
        Employee employee = employeeRepository.findByUsername(((User) authentication.getPrincipal()).getUsername());
        return hasPermission(employee, (ProtectedResource) o, AccessRight.valueOf((String) o1));
    }

    private boolean hasPermission(Employee employee, ProtectedResource protectedResource, AccessRight accessRight) {
        Set<AccessRight> accessRights = accessDecisionControl.getAccessRights(employee, protectedResource);
        setRightsOnProtectedResource(protectedResource, accessRights);
        return accessRights != null && accessRights.contains(accessRight);
    }

    private void setRightsOnProtectedResource(ProtectedResource protectedResource, Set<AccessRight> accessRights) {
        // change to TreeSet, so it's always sorted the same way
        Set<AccessRight> newRights = new TreeSet<>(accessRights);
        // this doesn't make sense to be written next to the created resource
        newRights.remove(AccessRight.CREATE);
        protectedResource.setRights(newRights);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
