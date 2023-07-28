package org.carrental.domain;


import lombok.*;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class Booking {

    private Integer id;
    private Integer c_id;
    private Integer v_id;
    private Date booking_date;
    private Date complete_date;
    private Integer amount;
    private String booking_status;
    private  Integer commission;
    private String customer_name;
    private String vehicle_name;
    private String owner_name;
    private Integer total_commission;
    private Integer total_days;
    private Integer total_amount;


}

//"select b.id, b.customer_id, b.vehicle_id, b.booking_date, b.complete_date," +
//        " b.amount * DATEDIFF(complete_date,booking_date) as total_amount, b.amount, DATEDIFF(b.complete_date,b.booking_date) " +
//        "as total_days, b.status, c.customer_name, v.vehicle_name from booking b inner join customer c on b.customer_id = c.id " +
//        "inner join vehicle v on b.vehicle_id = v.id where b.complete_date BETWEEN ? AND ? AND b.status='Completed'