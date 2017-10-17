package com.zlaja.avatest.employee;

import com.zlaja.avatest.organization.OrganizationService;
import com.zlaja.avatest.product.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final OrganizationService organizationService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, OrganizationService organizationService) {
        this.employeeRepository = employeeRepository;
        this.organizationService = organizationService;
    }

    @PostFilter("hasPermission(filterObject, 'READ')")
    public List<Employee> findAll() {
        return employeeRepository.findByOrganizationIn(organizationService.getOrganizationsForCurrentUser());
    }

    @PostAuthorize("hasPermission(returnObject, 'READ')")
    public Employee findById(String id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        }
        throw new ProductNotFoundException();
    }

    @PreAuthorize("hasPermission(#employee, 'CREATE')")
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @PreAuthorize("hasPermission(#employee, 'UPDATE')")
    public Employee update(Employee employee) {
        return employeeRepository.save(employee);
    }

    @PreAuthorize("hasPermission(#employee, 'DELETE')")
    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }
}
