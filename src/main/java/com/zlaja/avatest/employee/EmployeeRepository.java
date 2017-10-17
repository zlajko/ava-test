package com.zlaja.avatest.employee;

import com.zlaja.avatest.organization.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    Optional<Employee> findById(String id);

    Employee findByUsername(String username);

    List<Employee> findByOrganizationIn(Set<Organization> organizations);

}
