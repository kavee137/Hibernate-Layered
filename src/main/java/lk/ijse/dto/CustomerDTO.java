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
}
