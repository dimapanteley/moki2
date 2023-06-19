package com.example.moki3.controllers;

import com.example.moki3.services.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("{id}/employees")
    public String getEmployeesByDept(@PathVariable("id") int id){
        return "<h1>Сотрудники " + id + " отдела: </h1>" + departmentService.getEmployeesByDept(id);
    }

    @GetMapping("{id}/salary/sum")
    public String getSalarySumByDept(@PathVariable("id") int id){
        return "<h1>Сумма зарплат сотрудников " + id + "-го отдела: </h1>" +
                new DecimalFormat("###,###.##").format(departmentService.getSalarySumByDept(id)) + " р.";
    }

    @GetMapping("{id}/salary/max")
    public String getMaxSalaryByDept(@PathVariable("id") int id){
        return "<h1>Максимальная зарплата " + id + "-го отдела: </h1>" +
                new DecimalFormat("###,###.##").format(departmentService.getMaxSalaryByDept(id)) + " р.";
    }

    @GetMapping("{id}/salary/min")
    public String getMinSalaryByDept(@PathVariable("id") int id){
        return "<h1>Минимальная зарплата " + id + "-го отдела: </h1>" +
                new DecimalFormat("###,###.##").format(departmentService.getMinSalaryByDept(id)) + " р.";
    }

    @GetMapping("/employees")
    public String getEmployeesByDept(){
        return "<h1>Все сотрудники: </h1>" + departmentService.getAllEmployees();
    }
}