package com.zlaja.avatest;

import com.zlaja.avatest.employee.Employee;
import com.zlaja.avatest.employee.EmployeeRepository;
import com.zlaja.avatest.organization.Organization;
import com.zlaja.avatest.organization.OrganizationRepository;
import com.zlaja.avatest.product.Product;
import com.zlaja.avatest.product.ProductRepository;
import com.zlaja.avatest.security.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Component
class DataInitializr {

    private final ProductRepository productRepository;
    private final OrganizationRepository organizationRepository;
    private final EmployeeRepository employeeRepository;
    private final ExternalAccessRuleRepository externalAccessRuleRepository;

    DataInitializr(ProductRepository productRepository, OrganizationRepository organizationRepository, EmployeeRepository employeeRepository, ExternalAccessRuleRepository externalAccessRuleRepository) {
        this.productRepository = productRepository;
        this.organizationRepository = organizationRepository;
        this.employeeRepository = employeeRepository;
        this.externalAccessRuleRepository = externalAccessRuleRepository;
    }

    void initData(boolean approvedRules) {
        Organization organizationA = organizationRepository.save(new Organization("A", true));
        employeeRepository.save(new Employee("Admin", "admin", "admin", organizationA, new HashSet<>(Arrays.asList(AccessRight.values()))));

        Organization organizationB = organizationRepository.save(new Organization("B", false));
        employeeRepository.save(new Employee("Pera Peric", "pera", "pera", organizationB, new HashSet<>(Arrays.asList(AccessRight.values()))));
        employeeRepository.save(new Employee("Mika Mikic", "mika", "mika", organizationB, Collections.singleton(AccessRight.READ)));
        employeeRepository.save(new Employee("Zora Zoric", "zora", "zora", organizationB, Collections.singleton(AccessRight.READ)));

        productRepository.save(new Product("Elektromotor", 550, 5, organizationB));
        productRepository.save(new Product("Energetski kabel", 100, 8, organizationB));
        productRepository.save(new Product("Osigurac", 20, 120, organizationB));
        productRepository.save(new Product("Kuciste", 200, 20, organizationB));

        Organization organizationC = organizationRepository.save(new Organization("C", false));
        employeeRepository.save(new Employee("Sloba Stankovic", "sloba", "sloba", organizationC, new HashSet<>(Arrays.asList(AccessRight.values()))));
        employeeRepository.save(new Employee("Jovana Jovanovic", "jovana", "jovana", organizationC, new HashSet<>(Arrays.asList(AccessRight.READ, AccessRight.UPDATE, AccessRight.CREATE))));
        employeeRepository.save(new Employee("Nemanja Nemanjic", "nemanja", "nemanja", organizationC, Collections.singleton(AccessRight.READ)));

        productRepository.save(new Product("Ves Masina", 200, 5, organizationC));
        productRepository.save(new Product("Bojler", 500, 4, organizationC));
        productRepository.save(new Product("Sporet", 1200, 2, organizationC));
        productRepository.save(new Product("Sudo masina", 800, 7, organizationC));

        ProductRule rule1 = new ProductRule(ProductRule.Field.STOCK, ProductRule.Operation.LR, 10);
        ExternalAccessRule externalAccessRule1 = new ExternalAccessRule(organizationB, organizationC, rule1, Collections.singleton(AccessRight.READ), approvedRules);
        externalAccessRuleRepository.save(externalAccessRule1);

        ProductRule rule2 = new ProductRule(ProductRule.Field.STOCK, ProductRule.Operation.GR, 10);
        ExternalAccessRule externalAccessRule2 = new ExternalAccessRule(organizationB, organizationC, rule2, new HashSet<>(Arrays.asList(AccessRight.READ, AccessRight.UPDATE, AccessRight.DELETE)), approvedRules);
        externalAccessRuleRepository.save(externalAccessRule2);

        EmployeeRule rule3 = new EmployeeRule();
        ExternalAccessRule externalAccessRule3 = new ExternalAccessRule(organizationB, organizationC, rule3, new HashSet<>(Arrays.asList(AccessRight.READ, AccessRight.UPDATE, AccessRight.DELETE)), approvedRules);
        externalAccessRuleRepository.save(externalAccessRule3);
    }
}
