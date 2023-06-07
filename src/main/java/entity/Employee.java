package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    int id;
    String firstName;
    String lastName;
    String dept;
    String position;
    Double Salary;


}
