package lk.ijse.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
public class Customer {
    @Id
    private int id;
    private String name;
    private String address;
    private int age;
    private String phoneNumber;
}
