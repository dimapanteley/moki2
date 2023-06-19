package com.example.moki3;


import com.example.moki3.exceptions.FullMapException;
import com.example.moki3.exceptions.WrongDeptException;
import com.example.moki3.services.DepartmentService;
import com.example.moki3.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.moki3.ConstantsSet.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    private EmployeeService emplServ;
    private DepartmentService deptServ;

    @BeforeEach
    public void BeforeEach() {
        deptServ = new DepartmentService(emplServ);
    }

    @Test
    public void getEmployeeByDeptTest() {
        /**делаю заглушку для метода*/
        Mockito.when(emplServ.getEmployees()).thenReturn(EMPLOYEE_MAP_MOCK_TEST);

        int deptNum = 1; //номер отдела

        /**верхний цикл while перебирает номера отделов*/
        while (deptNum <= 5) {
            int employeesCounter = 0; //счетчик суммирует сотрудников в листе
            /**список куда попадают сотрудники сортированные по отделу*/
            List<Employee> employeeListTest = new ArrayList<>(deptServ.getEmployeesByDept(deptNum));

            /**цикл for проходит по сотрудникам в списке...*/
            for (int i = 0; i < employeeListTest.size(); i++) {
                /**...получает номер отдела каждого сотрудника*/
                int actual = employeeListTest.get(0).getDept();
                /**...и сравнивает номер отдела deptNum(expected) с полученным actual для каждого сотрудника*/
                assertEquals(deptNum, actual);
                /**увеличиваем счетчик в каждой итерации*/
                employeesCounter++;
            }
            /**проверяем что в списке столько же сотрудников сколько мы проверили во внутреннем цикле
             * и ни кого не забыли проверить*/
            assertEquals(employeesCounter, employeeListTest.size());
            /**номер отдела увеличиваем на 1, чтобы в следующей итерации проверить следующий отдел*/
            deptNum++;
        }
    }

    @Test
    public void getSalarySumByDeptTest() {
        /**делаю заглушку для метода*/
        Mockito.when(emplServ.getEmployees()).thenReturn(EMPLOYEE_MAP_MOCK_TEST);

        /**список ожидаемых значений сумм зарплат по отделам*/
        List<Integer> expected = new ArrayList<>(List.of(137_000, 186_000, 165_000, 130_000, 161_000));
        int deptNum = 1; //номер отдела

        /**циклом проходим по списку с суммами зарплат*/
        for (int i = 0; i < 5; i++) {
            /**получаем сумму зарплат из метода и кладем ее в actual*/
            int actual = deptServ.getSalarySumByDept(deptNum);
            /**сравниваем ожидаемое i-ое значение с фактическим, полученным из метода*/
            assertEquals(expected.get(i), actual);
            deptNum++;
        }
    }

    @Test
    public void getMaxSalaryByDeptTest() {
        /**делаю заглушку для метода*/
        Mockito.when(emplServ.getEmployees()).thenReturn(EMPLOYEE_MAP_MOCK_TEST);

        /**список ожидаемых значений сумм зарплат по отделам*/
        List<Integer> expected = new ArrayList<>(List.of(72_000, 99_000, 90_000, 67_000, 85_000));
        int deptNum = 1; //номер отдела

        /**циклом проходим по списку с суммами зарплат*/
        for (int i = 0; i < 5; i++) {
            /**получаем сумму зарплат из метода и кладем ее в actual*/
            int actual = deptServ.getMaxSalaryByDept(deptNum);
            /**сравниваем ожидаемое i-ое значение с фактическим, полученным из метода*/
            assertEquals(expected.get(i), actual);
            deptNum++;
        }
    }

    @Test
    public void getMinSalaryByDeptTest() {
        /**делаю заглушку для метода*/
        Mockito.when(emplServ.getEmployees()).thenReturn(EMPLOYEE_MAP_MOCK_TEST);

        /**список ожидаемых значений сумм зарплат по отделам*/
        List<Integer> expected = new ArrayList<>(List.of(65_000, 87_000, 75_000, 63_000, 76_000));
        int deptNum = 1; //номер отдела

        /**циклом проходим по списку с суммами зарплат*/
        for (int i = 0; i < 5; i++) {
            /**получаем сумму зарплат из метода и кладем ее в actual*/
            int actual = deptServ.getMinSalaryByDept(deptNum);
            /**сравниваем ожидаемое i-ое значение с фактическим, полученным из метода*/
            assertEquals(expected.get(i), actual);
            deptNum++;
        }
    }

    @Test
    public void getAllEmployeesTest() {
        /**делаю заглушку для метода*/
        Mockito.when(emplServ.getEmployees()).thenReturn(EMPLOYEE_MAP_MOCK_TEST);
        /**создаю ожидаемую мапу*/
        Map<Integer, List<Employee>> expected = new HashMap<>(Map.of(
                1, new ArrayList<>(deptServ.getEmployeesByDept(1)),
                2, new ArrayList<>(deptServ.getEmployeesByDept(2)),
                3, new ArrayList<>(deptServ.getEmployeesByDept(3)),
                4, new ArrayList<>(deptServ.getEmployeesByDept(4)),
                5, new ArrayList<>(deptServ.getEmployeesByDept(5))
        ));
        /**кладу в actual мапу из тестируемого метода*/
        Map<Integer, List<Employee>> actual = deptServ.getAllEmployees();
        /**сравниваю результаты*/
        assertEquals(expected, actual);
    }

    @Test
    public void getEmployeeByDeptExceptionTest1() {
        /**Проверка на выброс исключения при неверном вводе номера отдела*/
        assertThrows(WrongDeptException.class, () -> deptServ.getEmployeesByDept(0));
        assertThrows(WrongDeptException.class, () -> deptServ.getEmployeesByDept(6));

        assertThrows(WrongDeptException.class, () -> deptServ.getSalarySumByDept(0));
        assertThrows(WrongDeptException.class, () -> deptServ.getSalarySumByDept(6));

        assertThrows(WrongDeptException.class, () -> deptServ.getMaxSalaryByDept(0));
        assertThrows(WrongDeptException.class, () -> deptServ.getMaxSalaryByDept(6));

        assertThrows(WrongDeptException.class, () -> deptServ.getMinSalaryByDept(0));
        assertThrows(WrongDeptException.class, () -> deptServ.getMinSalaryByDept(6));
    }

    @Test
    public void getEmployeeByDeptExceptionTest2() {
        /**Проверка на выброс исключения */
        /**создаю пустую мапу*/
        Map<String, Employee> testMap = new HashMap<>();
        Mockito.when(emplServ.getEmployees()).thenReturn(testMap);

        /**подставляем номера отделав*/
        for (int i = 1; i <= 5; i++) {
            int finalI = i;
            assertThrows(NullPointerException.class, () -> deptServ.getEmployeesByDept(finalI));
            assertThrows(NullPointerException.class, () -> deptServ.getSalarySumByDept(finalI));
            assertThrows(NullPointerException.class, () -> deptServ.getMaxSalaryByDept(finalI));
            assertThrows(NullPointerException.class, () -> deptServ.getMinSalaryByDept(finalI));
        }
        assertThrows(NullPointerException.class, () -> deptServ.getAllEmployees());
    }

}