package uz.pdp.jpaapprelationships.payload;

import lombok.Data;

@Data
public class StudentDto {
    private String firstName;
    private String lastName;
    private Integer groupId;
    private String city;
    private String district;
    private String street;


}
