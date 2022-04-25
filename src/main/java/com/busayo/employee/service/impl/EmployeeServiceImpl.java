package com.busayo.employee.service.impl;

import com.busayo.employee.exception.ResourceNotFoundException;
import com.busayo.employee.model.Employee;
import com.busayo.employee.repository.EmployeeRepository;
import com.busayo.employee.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
//        Optional<Employee> employee = employeeRepository.findById(id);
//        if (employee.isPresent()) {
//            return employee.get();
//        } else {
//            throw new ResourceNotFoundException("Employee", "Id", id);
//        }
        return employeeRepository.findById(id).orElseThrow(() ->
        new ResourceNotFoundException("Employee", "Id",id));
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
//        we need to check if the employee with given id exists in the DB
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "Id", id));
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());

//        save existing employee to DB
        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }

    @Override
    public void deleteEmployee(long id) {
//        check if the employee exists in the DB
        employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
        employeeRepository.deleteById(id);
    }


}
