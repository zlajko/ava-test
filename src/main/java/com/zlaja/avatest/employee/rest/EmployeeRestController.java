package com.zlaja.avatest.employee.rest;

import com.zlaja.avatest.employee.Employee;
import com.zlaja.avatest.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(path = "/employees", produces = "application/json")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(method = GET)
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @RequestMapping(path = "/{id}", method = GET)
    public Employee findById(@PathVariable("id") String id) {
        return employeeService.findById(id);
    }

    @RequestMapping(method = POST)
    public Employee create(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @RequestMapping(path = "/{id}", method = PUT)
    public Employee update(@PathVariable("id") String id, @RequestBody Employee employee) {
        return employeeService.update(employee);
    }

    @RequestMapping(path = "/{id}", method = DELETE)
    public void delete(@PathVariable("id") String id) {
        employeeService.delete(employeeService.findById(id));
    }
}
