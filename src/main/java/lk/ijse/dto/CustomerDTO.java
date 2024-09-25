package lk.ijse.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data

public class CustomerDTO {
    private int id;
    private String name;
    private String phoneNumber;
    private String address;
    private int age;

    public CustomerDTO(int id, String address, int age, String name, String phoneNumber) {
        this.id = id;
        this.address = address;
        this.age = age;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
