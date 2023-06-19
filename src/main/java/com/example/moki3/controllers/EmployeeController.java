package com.example.moki3.controllers;

import com.example.moki3.services.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    EmployeeService es;
    public EmployeeController(EmployeeService es) {
        this.es = es;
    }
    @GetMapping("/")
    public String getEmployees() {
        return "<pre><h1><b>Список всех сотрудников:</b></h1>\n" + "" + es.printEmployees() + "<pre>";
    }
    @GetMapping("/add")
    public String add(@RequestParam("name") String name,
                      @RequestParam("salary") int salary,
                      @RequestParam("dept") int dept) {
//        es.addEmployee(name, salary, dept);
        return "<h1>Сотрудник " + es.addEmployee(name, salary, dept) + " добавлен</h1>";
    }

    @GetMapping("/change")
    public String change(@RequestParam("nameDeletingEmployee") String fullNameDeletingEmployee,
                         @RequestParam("nameNewEmployee") String fullNameNewEmployee,
                         @RequestParam("newSalary") int newSalary,
                         @RequestParam("newDept") int newDept) {
        es.changeEmployee(fullNameDeletingEmployee,
                fullNameNewEmployee,
                newSalary,
                newDept);
        return "<h1>Сотрудник: " + fullNameDeletingEmployee + " </h1>" +
                "<h2><b>заменен </b></h2>" +
                "<h1>сотрудником: " + fullNameNewEmployee + " </h1>" +
                "<h2> зарплата: " + newSalary + "</h2>" +
                "<h2> отдел: " +  newDept + "</h2>";
    }
    @GetMapping("/remove")
    public String removeEmployee(@RequestParam("fullName") String fullName) {
        try {
            es.removeEmployee(fullName);
        } catch (NullPointerException e) {
            throw new RuntimeException("\u001B[31m Такого сотрудника не существует \u001B[0m");
        }
        return "<h1>Сотрудник " + fullName + " удален</h1>";
    }
    @GetMapping("/find")
    public String findEmployee(@RequestParam("fullName") String fullName) {
        if (es.getEmployees().containsKey(fullName)) {
            return "<h1>Сотрудник: " + es.getEmployees().get(fullName) + "</h1>";
        } else {
            return "<h1>Сотрудник не найден</h1>";
        }
    }

}
