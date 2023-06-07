package Data;

import entity.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public  class EmployeeList {


    public static List<Employee> getEmployeeList()
    {
        return new ArrayList<Employee>(Arrays.asList
                (new Employee(1,"likitha","villu","HR","Senior HR",100000.00),
                new Employee(2,"suma","chinnu","IT","sw",100005.00),
                new Employee(3,"sudhir","nemu","CSE","Senior CSE",100010.00)));

    }
}
