import Data.EmployeeList;
import entity.Employee;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamCollectorsDemo {

    public static void main(String[] args) {

//        EmployeeList employeeList = new EmployeeList();
        List<Employee> listofemployees = EmployeeList.getEmployeeList();

//        listofemployees.stream().forEach(System.out::println);

        String empFirstnameJoined = listofemployees.stream().
                map(employee -> employee.getFirstName()).collect(Collectors.joining(","));
//        System.out.println(empFirstnameJoined);

//        public static <T>
//                Collector<T, ?,Map<Boolean, List<T>>> partitioningBy(Predicate<? super T> predicate) {
        Map<Boolean, List<Employee>> employeeSalarypartition = listofemployees.stream().
                collect(Collectors.partitioningBy(employee -> employee.getSalary() >= 100005));

//        System.out.println(employeeSalarypartition);

        //set example
        //  public static <T, D, A>
        //    Collector<T, ?, Map<Boolean, D>> partitioningBy(Predicate<? super T> predicate,
        //                                                    Collector<? super T, A, D> downstream)
        Map<Boolean, Set<Employee>> employeeSalarypartitionSet = listofemployees.stream().
                collect(Collectors.partitioningBy(employee -> employee.getSalary() >= 100005, Collectors.toSet()));

//        System.out.println(employeeSalarypartitionSet);

        // count example
        //  public static <T, D, A>
        //    Collector<T, ?, Map<Boolean, D>> partitioningBy(Predicate<? super T> predicate,
        //                                                    Collector<? super T, A, D> downstream)
        Map<Boolean, Long> employeeSalarypartitionCount = listofemployees.stream().
                collect(Collectors.partitioningBy(employee -> employee.getSalary() >= 100005, Collectors.counting()));

//        System.out.println(employeeSalarypartitionCount);


//        public static <T, K> Collector<T, ?, Map<K, List<T>>>
//        groupingBy(Function<? super T, ? extends K> classifier)
        Map<Object, List<Employee>> employeeByDept = listofemployees.stream().
                collect(Collectors.groupingBy(employee -> employee.getDept()));

//        System.out.println(employeeByDept);

//        public static <T, K, A, D>
//                Collector<T, ?, Map<K, D>> groupingBy(Function<? super T, ? extends K> classifier,
//                Collector<? super T, A, D> downstream)
        Map<Object, Long> employeeByDeptCounting = listofemployees.stream().
                collect(Collectors.groupingBy(employee -> employee.getDept(),Collectors.counting()));

//        System.out.println(employeeByDeptCounting);


//        public static <T, K, A, D>
//                Collector<T, ?, Map<K, D>> groupingBy(Function<? super T, ? extends K> classifier,
//                Collector<? super T, A, D> downstream)
//        public static <T, U, A, R>
//                Collector<T, ?, R> mapping(Function<? super T, ? extends U> mapper,
//                Collector<? super U, A, R> downstream) {

        Map<String, List<String>> empNamesByDept = listofemployees.stream().
                collect(
                        Collectors.groupingBy
                                (employee -> employee.getDept(),Collectors.mapping(employee -> employee.getFirstName(),Collectors.toList())));

//        System.out.println(empNamesByDept);


//        public static <T> Collector<T, ?, Optional<T>>
//        reducing(BinaryOperator<T> op)
        Map<String, Optional<Double>> sumofSalaryByDept = listofemployees.stream().
                collect(
                        Collectors.groupingBy
                                (employee -> employee.getDept(), Collectors.mapping(employee -> employee.getSalary(), Collectors.reducing((a, b) -> a + b))));

//        System.out.println(sumofSalaryByDept);

        Map<String, Double> sumofSalaryByDep = listofemployees.stream().
                collect(
                        Collectors.groupingBy
                                (employee -> employee.getDept(), Collectors.summingDouble(employee -> employee.getSalary())));

//        System.out.println(sumofSalaryByDep);


//        Collector<T, ?, DoubleSummaryStatistics> summarizingDouble(ToDoubleFunction<? super T> mapper)
        Map<String, DoubleSummaryStatistics> empSummaryStatistics = listofemployees.stream().
                collect(
                        Collectors.groupingBy
                                (employee -> employee.getDept(), Collectors.summarizingDouble(employee -> employee.getSalary())));

//        System.out.println(empSummaryStatistics);

//        Collector<T, ?, R> filtering(Predicate<? super T> predicate,
//                Collector<? super T, A, R> downstream)

        DoubleSummaryStatistics hrEmpSalaryStatistics = listofemployees.stream().collect(Collectors.filtering(employee -> employee.getDept().equals("HR"), Collectors.summarizingDouble(value -> value.getSalary())));
//        System.out.println(hrEmpSalaryStatistics);


//        employee with highest age
//        public static <T> Collector<T, ?, Optional<T>>
////        maxBy(Comparator<? super T> comparator)
        Optional<Employee> collect = listofemployees.stream().collect(Collectors.maxBy((o1, o2) -> (int) (o1.getSalary() - o2.getSalary())));


//        System.out.println(collect.get());

//        using comparter now
        Optional<Employee> collect1 = listofemployees.stream().collect(Collectors.maxBy(Comparator.comparing(employee -> employee.getSalary())));

//        System.out.println(collect1.get());

//        public static<T,A,R,RR> Collector<T,A,RR> collectingAndThen(Collector<T,A,R> downstream,
//                Function<R,RR> finisher)

//        String empNamewithHighestSalarywithMetref = listofemployees.stream().
//                collect(Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Employee::getSalary)),
//                        empOptional -> empOptional.map(employee -> employee.getFirstName()).orElse("Name not found")
//                ));
//        System.out.println(empNamewithHighestSalarywithMetref);

//        String empNamewithHighestSalarywithmetods = listofemployees.stream().
//                collect(Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(o -> o.getSalary())),
//                        empOptional -> empOptional.map(employee -> employee.getFirstName()).orElse("Name not found")
//                ));
//        System.out.println(empNamewithHighestSalarywithmetods);




    }
}
