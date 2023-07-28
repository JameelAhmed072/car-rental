package org.carrental.domain;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Vehicle {
    private Long id;
    private String name;
    private Long model;
    private String brand;
    private String color;
    private String vstatus;
    private Long ownerId;
    private String ownerName;
    private Integer commission;
}
