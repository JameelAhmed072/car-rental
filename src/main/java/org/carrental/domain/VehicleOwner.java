package org.carrental.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class VehicleOwner {
    private Long id;
    private String name;
    private String cnic;
    private String phonenumber;
    private String address;
    private Double commission;
}
