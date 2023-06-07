import entity.Car;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class JavaStreamsAPI {


    public static void main(String[] args) {

        List<Car> cars = List.of(new Car("sedan","BMW","538",1998),
                new Car("sedan","Audi","A5",1998),
                new Car("sedan","Mercedes","E-class",2500),
                new Car("sedan","Toyota","E-class",1600),
                new Car("hashback","Toyota","E-class",400),
                new Car("hashback","Honda","R",2500)

        );

        List<Car> sedanList = cars.stream().filter(car -> car.type.equals("sedan")).collect(Collectors.toList());
        List<String> carmakeList = cars.stream().map(car -> car.make).collect(Collectors.toList());

       List<List<String>> carmakemodelList = cars.stream().map(car ->List.of(car.make,car.model)).collect(Collectors.toList());

/*       or better to use flatMap instead of complicate structure like above which provides the option to use
Stream and makes String directly and then use list operation */

        List<String> carmakemodelListflatMap = cars.stream().flatMap(car -> Stream.of(car.make,car.model)).collect(Collectors.toList());

        Stream<Integer> IntegerStream =Stream.of(10,12,13,14,16,17);

//        Debug using peek
        Stream FilterIntegerStream = IntegerStream.filter(integer ->  integer % 2 == 0 ).peek(integer ->
                System.out.println("Integer " +integer));

        System.out.println("Count = " + FilterIntegerStream.count());

      //True or false partition cars
        Map<Boolean,List<Car>> partitionedCars =
                cars.stream().collect(partitioningBy(car -> car.type.equals("sedan")));

        System.out.println("Partition cars by type " +partitionedCars);

//        group by type,(make,electricity)
        Map<String, Map<String, Integer>> groupedCars = cars.stream().collect(groupingBy(

                car -> car.type, toMap(Car::getMake, Car::getEngineCapacity
                )

                //sedan    ->Audi(A5),1998
                //sedan    ->Toyota,1600
               //Hashback  ->toyota,1500

        ));

        System.out.println("Grouped Cars " +groupedCars);

       cars.stream().forEach(car ->
       {
           System.out.println(car.make);
       });

        cars.parallelStream().forEach(car ->
        {
            System.out.println("adassa");
        });


    }
}
