package com.example.moki3.services;


import com.example.moki3.Employee;
import com.example.moki3.exceptions.WrongDeptException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private EmployeeService es;

    public DepartmentService(EmployeeService es) {
        this.es = es;
    }

    public List<Employee> getEmployeesByDept(int id) {
        if (id < 1 || id > 5) {
            throw new WrongDeptException();
        }
        if (es.getEmployees().isEmpty()) {
            throw new NullPointerException();
        }
        List<Employee> employeeInDepts = es.getEmployees().values().stream()
                .filter(employee -> employee.getDept() == id)
                .collect(Collectors.toList());
        return employeeInDepts;
    }

    public int getSalarySumByDept(int id) {
        if (id < 1 || id > 5) {
            throw new WrongDeptException();
        }
        if (es.getEmployees().isEmpty()) {
            throw new NullPointerException();
        }
        return es.getEmployees().values().stream()
                .filter(employee -> employee.getDept() == id)
                .mapToInt(el -> el.getSalary())
                .sum();
    }

    public int getMaxSalaryByDept(int id) {
        if (id < 1 || id > 5) {
            throw new WrongDeptException();
        }
        if (es.getEmployees().isEmpty()) {
            throw new NullPointerException();
        }
        Optional<Integer> maxSalary = es.getEmployees().values().stream()
                .filter(employee -> employee.getDept() == id)
                .map(Employee::getSalary)
                .max(Integer::compareTo);
        return maxSalary.orElse(0);
    }

    public int getMinSalaryByDept(int id) {
        if (id < 1 || id > 5) {
            throw new WrongDeptException();
        }
        if (es.getEmployees().isEmpty()) {
            throw new NullPointerException();
        }
        Optional<Integer> minSalary = es.getEmployees().values().stream()
                .filter(employee -> employee.getDept() == id)
                .map(Employee::getSalary)
                .min(Integer::compareTo);
        return minSalary.orElse(0);
    }

    public Map<Integer, List<Employee>> getAllEmployees() {
        if (es.getEmployees().isEmpty()) {
            throw new NullPointerException();
        }
        Map<Integer, List<Employee>> employeeInDepts = es.getEmployees().values().stream()
                .collect(Collectors.groupingBy(Employee::getDept));
        return employeeInDepts;
    }
}