package com.example.moki3;


import java.util.*;

public class ConstantsSet {
    public final static String FULL_NAME_1 = "Иванов Иван Иванович";
    public final static String FULL_NAME_2 = "Петров Петр Петрович";
    public final static Integer SALARY_1 = 200_000;
    public final static Integer SALARY_2 = 190_000;
    public final static Integer DEPARTMENT_1 = 1;
    public final static Integer DEPARTMENT_2 = 2;
    public final static Employee EMPLOYEE1 = new Employee(FULL_NAME_1, SALARY_1, DEPARTMENT_1);
    public final static Employee EMPLOYEE2 = new Employee(FULL_NAME_2, SALARY_2, DEPARTMENT_2);

    public final static List<Employee> LIST1_EXP = new ArrayList<>(List.of(
            new Employee("Васильев Денис Андреевич", 65_000, 1),
            new Employee("Карчемный Владимир Георгиевич", 72_000, 1)
    ));
    public final static List<Employee> LIST2_EXP = new ArrayList<>(List.of(
            new Employee("Александров Михаил Богданович", 99_000, 2),
            new Employee("Петрова Елена Павловна", 87_000, 2)
    ));

    public final static List<Employee> LIST3_EXP = new ArrayList<>(List.of(
            new Employee("Лянге Александр Григорьевич", 90_000, 3),
            new Employee("Рыбкин Данил Амвросиевич", 75_000, 3)
    ));

    public final static List<Employee> LIST4_EXP = new ArrayList<>(List.of(
            new Employee("Скворцов Сергей Денисович", 63_000, 4),
            new Employee("Кузнцов Александр Семенович", 67_000, 4)
    ));

    public final static List<Employee> LIST5_EXP = new ArrayList<>(List.of(
            new Employee("Попова Варвара Богдановна", 85_000, 5),
            new Employee("Юницин Сергей Михайлович", 76_000, 5)
    ));
    public final static Map<String, Employee> EMPLOYEE_MAP_MOCK_TEST = new LinkedHashMap<>(Map.of(
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

}