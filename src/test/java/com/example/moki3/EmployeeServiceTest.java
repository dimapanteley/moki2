package com.example.moki3;

import com.example.moki3.exceptions.FullMapException;
import com.example.moki3.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static com.example.moki3.ConstantsSet.*;
import static org.junit.jupiter.api.Assertions.*;


public class EmployeeServiceTest {
    private final EmployeeService employeeService = new EmployeeService();

    @BeforeEach
    public void setUp() {
        /**создаем сотрудника */
        employeeService.addEmployee(FULL_NAME_1, SALARY_1, DEPARTMENT_1);
    }

    @Test
    public void getEmployees() {
        assertNotNull(employeeService.getEmployees());
        assertEquals(FULL_NAME_1, employeeService.getEmployees().get(FULL_NAME_1).getFullName());
        assertEquals(SALARY_1, employeeService.getEmployees().get(FULL_NAME_1).getSalary());
        assertEquals(DEPARTMENT_1, employeeService.getEmployees().get(FULL_NAME_1).getDept());
    }

    @Test
    public void addEmployeeTest() {
        /**сотрудник добавлен через beforeEach */
        /**получаем сотрудников и проверяем
        /**сравниваем сотрудников*/
        assertEquals(EMPLOYEE1, employeeService.getEmployees().get(FULL_NAME_1));
        assertEquals(FULL_NAME_1, employeeService.getEmployees().get(FULL_NAME_1).getFullName());
        assertEquals(SALARY_1, employeeService.getEmployees().get(FULL_NAME_1).getSalary());
        assertEquals(DEPARTMENT_1, employeeService.getEmployees().get(FULL_NAME_1).getDept());
    }

    @Test
    public void addEmployeeTestException() {
        int keyCounter = 0;
        String keyFullName = "Имя";
        for (int i = 0; i < 20; i++) {
            keyCounter++;
            keyFullName += keyCounter;
            employeeService.getEmployees().put(keyFullName, new Employee(keyFullName, SALARY_1, DEPARTMENT_1));
        }
        assertThrows(FullMapException.class, () -> employeeService.addEmployee(FULL_NAME_2, SALARY_2, DEPARTMENT_2));
    }

    @Test
    public void removeEmployee() {
        /**сотрудник добавлен через beforeEach */
        assertTrue(employeeService.getEmployees().containsKey(FULL_NAME_1));
        assertFalse(employeeService.getEmployees().containsKey(FULL_NAME_1));

    }

    @Test
    public void changeEmployee() {
        /**сотрудник добавлен через beforeEach */
        /**проверяем кол-во
        /**проверяю что в мапу добавился именно тот сотрудник из BeforeEach с FULL_NAME1*/
        assertTrue(employeeService.getEmployees().containsKey(FULL_NAME_1));
        /**использую метод changeEmployee, чтобы изменить сотрудника в мапе*/
        employeeService.changeEmployee(FULL_NAME_1, FULL_NAME_2, SALARY_2, DEPARTMENT_2);
        /**получаю FALSE если добавленного ранее сотрудника нет в мапе, то есть сотрудник удален*/
        assertFalse(employeeService.getEmployees().containsKey(FULL_NAME_1));
        /**проверяю что в мапу добавился именно сотрудник с FULL_NAME2*/
        assertTrue(employeeService.getEmployees().containsKey(FULL_NAME_2));
        assertEquals(11, employeeService.getEmployees().size());
    }

    @Test
    public void printEmployeeTest() {
        String expected = "- Карчемный Владимир Георгиевич, зарплата: 72000, отдел: 1\n";
        String expected1 = "- Петрова Елена Павловна, зарплата: 87000, отдел: 2\n";
        String expected2 = "- Скворцов Сергей Денисович, зарплата: 63000, отдел: 4\n";
        String expected3 = "- Александров Михаил Богданович, зарплата: 99000, отдел: 2\n";
        String expected4 = "- Юницин Сергей Михайлович, зарплата: 76000, отдел: 5\n";
        String expected5 = "- Васильев Денис Андреевич, зарплата: 65000, отдел: 1\n";
        String expected6 = "- Лянге Александр Григорьевич, зарплата: 90000, отдел: 3\n";
        String expected7 = "- Попова Варвара Богдановна, зарплата: 85000, отдел: 5\n";
        String expected8 = "- Кузнцов Александр Семенович, зарплата: 67000, отдел: 4\n";
        String expected9 = "- Рыбкин Данил Амвросиевич, зарплата: 75000, отдел: 3\n";
        String expected10 = "- Иванов Иван Иванович, зарплата: 200000, отдел: 1\n";

        StringBuilder actual = employeeService.printEmployees();

        assertTrue(actual.toString().contains(expected));
        assertTrue(actual.toString().contains(expected1));
        assertTrue(actual.toString().contains(expected2));
        assertTrue(actual.toString().contains(expected3));
        assertTrue(actual.toString().contains(expected4));
        assertTrue(actual.toString().contains(expected5));
        assertTrue(actual.toString().contains(expected6));
        assertTrue(actual.toString().contains(expected7));
        assertTrue(actual.toString().contains(expected8));
        assertTrue(actual.toString().contains(expected9));
        assertTrue(actual.toString().contains(expected10));
    }
}