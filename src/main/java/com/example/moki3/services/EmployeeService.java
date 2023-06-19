package com.example.moki3.services;

import com.example.moki3.Employee;
import com.example.moki3.exceptions.FullMapException;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class EmployeeService {

    private Map<String, Employee> employees = new LinkedHashMap<>(Map.of(
            "Попова Варвара Богдановна",
            new Employee("Попова Варвара Богдановна", 85_000, 5),
            "Петрова Елена Павловна",
            new Employee("Петрова Елена Павловна", 87_000, 2),
            "Васильев Денис Андреевич",
            new Employee("Васильев Денис Андреевич", 65_000, 1),
            "Лянге Александр Григорьевич",
            new Employee("Лянге Александр Григорьевич", 90_000, 3),
            "Кузнецов Александр Семенович",
            new Employee("Кузнцов Александр Семенович", 67_000, 4),
            "Скворцов Сергей Денисович",
            new Employee("Скворцов Сергей Денисович", 63_000, 4),
            "Александров Михаил Богданович",
            new Employee("Александров Михаил Богданович", 99_000, 2),
            "Карчемный Владимир Георгиевич",
            new Employee("Карчемный Владимир Георгиевич", 72_000, 1),
            "Юницин Сергей Михайлович",
            new Employee("Юницин Сергей Михайлович", 76_000, 5),
            "Рыбкин Данил Амвросиевич",
            new Employee("Рыбкин Данил Амвросиевич", 75_000, 3)
    ));

    public StringBuilder printEmployees() {
        StringBuilder rezString = new StringBuilder("");
        if (employees.isEmpty()) {
            return rezString.append("интерфейс Map не содержит ни одного сотрудника");
        }
        employees.values().stream()
                .forEach(employee -> rezString.append("- " + employee.getFullName() +
                        ", зарплата: " + employee.getSalary() +
                        ", отдел: " + employee.getDept() + "\n"));
        return rezString;
    }


    public Employee addEmployee(String fullName, int salary, int dept) {
        Employee employee = new Employee(fullName, salary, dept);
        if (employees.size() > 20) {
            throw new FullMapException();
        }
        employees.put(fullName, employee);
        return employee;
    }

    public void removeEmployee(String fullName) {
        if (employees.containsKey(fullName)) {
            employees.remove(fullName);
        } else {
            throw new RuntimeException();
        }
    }

    public Map<String, Employee> getEmployees() {
        return employees;
    }

    public void changeEmployee(String fullNameDeletingEmployee,
                               String fullNameNewEmployee,
                               Integer newSalary,
                               Integer newDept) {
        if (employees.containsKey(fullNameDeletingEmployee)) {
            removeEmployee(fullNameDeletingEmployee);
            addEmployee(fullNameNewEmployee, newSalary, newDept);
        } else {
            throw new NullPointerException();
        }
    }
}