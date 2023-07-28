package org.carrental.dao;

public class SqlQueryConstant {
    public final static String GET_ALL_CUSTOMER = "select * from customer";
    public final static String GET_CUSTOMER_BY_ID = "select * from customer where id = ?";
    public final static String GET_Complete_CUSTOMER_BY_ID = "select * from customer where id = ?";
    public final static String GET_CUSTOMER_BY_NAME = "select * from customer where c_name like '%?%' ";
    public final static String UPDATE_CUSTOMER_BY_ID = "update customer set c_name = ?,c_number = ?,c_cnic = ?,c_address = ?,ref_phone_number = ? where id = ?;";
    public final static String DELETE_CUSTOMER_BY_ID = "delete from customer where id = ?";
    public final static String INSERT_INTO_CUSTOMER =
            "insert into customer(c_name,c_number,c_cnic,c_address,ref_phone_number) " +
            "values (?,?,?,?,?)";


    //  --------   vehicle owner table   --------------//

    public final static String GET_ALL_OWNERS = "select * from vehicle_owner";
    public final static String GetAllVehiclesOwners = "select id,o_name,o_cnic,o_phone_number,o_address,commission\n" +
            "             from vehicle_owner\n";
    public final static String GET_VEHICLE_OWNER_BY_ID = "select * from vehicle_owner where id = ?";
    public final static String UPDATE_VEHICLE_OWNER_BY_ID = "update vehicle_owner set o_name = ? where id = ?";
    public final static String DELETE_VEHICLE_OWNER_BY_ID = "delete from vehicle_owner where id = ?";
    public final static String INSERT_INTO_VEHICLE_OWNER =
            "insert into vehicle_owner(o_name,o_cnic,o_phone_number,o_address,commission) " +
                    "values (?,?,?,?,?)";

    //  user  table
    public final static String GET_USER_BY_USERNAME_AND_PASSWORD = "select * from user where username = ? AND pass = ?";

    //    Booking Table

    public final static String GET_ALL_BOOKING = "select * from booking";
    public final static String GET_BOOKING_BY_ID = "select * from booking where id = ?";
    public final static String GET_BOOKINGS_FOR_UPDATING = "select b.id,b.v_id,b.c_id,v.v_name,c.c_name,b.booking_date,b.price,vo.commission,vo.o_name from booking b\n" +
            "inner join vehicle v on v.id = b.v_id\n" +
            "inner join vehicle_owner vo on v.owner_id = vo.id\n" +
            "inner join customer c on b.c_id = c.id \n" +
            " where b.id = ?;";
    public final static String GET_BOOKING_BY_DATE = "select * from booking where booking_date like '%?%' ";
    public final static String UPDATE_BOOKING_BY_ID = "update booking set booking_date = ? where id = ?";

    //   ----- this is query for the montyly report for the report class  ------------------------------------
    public final static String MONTHLY_REPORT_QUERY = "\n" +
            "select b.id,b.v_id,b.c_id,v.v_name,c.c_name,b.booking_date,b.complete_date,b.price,b.booking_status,vo.commission,vo.o_name from booking b \n" +
            "                   inner join vehicle v on b.v_id = v.id\n" +
            "                   inner join vehicle_owner vo on v.owner_id = vo.id\n" +
            "                 inner join customer c on b.c_id = c.id\n" +
            "                    where booking_date  between ? AND ?;";

    public final static String COMMISSION_REPORT_QUERY = "SELECT b.id,b.v_id,b.c_id,v.v_name,c.c_name,b.booking_date,b.complete_date,b.price,b.booking_status,vo.commission,vo.o_name,\n" +
            "                   SUM(vo.commission) AS total_commission\n" +
            "            FROM vehicle_owner vo\n" +
            "            INNER JOIN vehicle v ON vo.id = v.owner_id\n" +
            "            INNER JOIN booking b ON v.id = b.v_id\n" +
            "            INNER JOIN customer c ON b.c_id = c.id\n" +
            "            WHERE booking_date BETWEEN ? AND ?\n" +
            "              AND b.booking_status = 'Completed'\n" +
            "            GROUP BY b.id,b.v_id,b.c_id,v.v_name,c.c_name,b.booking_date,b.complete_date,b.price,b.booking_status,vo.commission,vo.o_name;";
// -----------------------------------------------------------------------------------------------------------
    public final static String DELETE_BOOKING_BY_ID = "delete from booking where id = ?";
    public final static String INSERT_INTO_BOOKING =
            "insert into booking(v_id,c_id,booking_date,price,booking_status) " +
                    "values (?,?,?,?,?)";
    public final static String UPDATE_INTO_BOOKING = "update booking\n" +
            "set v_id = ?,c_id = ?,booking_date = ?,price = ?,booking_status = ?\n" +
            "where id = ?;";

    public final static String GetAllIDAndCustomer = "select * from customer";
    public final static String GetAllIDAndVehicle = "select * from vehicle v\n" +
            "inner join vehicle_owner vo on v.owner_id = vo.id;\n";

    public final static String GetAllIBookingDetails = "select b.id,b.v_id,c_id,v.v_name,c.c_name,b.price,b.booking_date,vo.commission,vo.o_name from booking b\n" +
            "            inner join customer c on b.c_id = c.id\n" +
            "            inner join vehicle v on b.v_id = v.id\n" +
            "            inner join vehicle_owner vo on vo.id = v.owner_id;";

    public final static String GET_ALL = "select b.id, b.customer_id, b.vehicle_id, b.booking_date, b.complete_date, b.amount, b.status, c.customer_name, v.vehicle_name from booking b inner join customer c on b.customer_id = c.id inner join vehicle v on b.vehicle_id = v.id where b.status in ('Open','Completed')";
    public final static String GetAllAvailableCarsDetails = "select v.id,v.v_name,vo.o_name,vo.commission,v.v_model,v.v_brand,v.v_color,v.owner_id,v.vstatus from vehicle v\n" +
            "            inner join vehicle_owner vo on v.owner_id = vo.id\n" +
            "            where v.vstatus != 'Booked';";
    //   -------------------------------------------------------------------------------   //
//   vehicle
    public final static String GET_ALL_VEHICLE = "select v.id,v.v_name,v.v_model,v.v_brand,v.v_color,v.owner_id,vo.o_name from vehicle v\n" +
            "inner join vehicle_owner vo on v.owner_id = vo.id;";
    public final static String GET_VEHICLE_BY_ID = "select * from vehicle where id = ?";
    public final static String GET_VEHICLE_BY_NAME = "select * from vehicle where c_name like '%?%' ";
    public final static String UPDATE_VEHICLE_BY_ID = "update vehicle set v_name = ?,v_model = ?, v_brand = ?, v_color = ?,owner_id = ?  where id = ?";
    public final static String DELETE_VEHICLE_BY_ID = "delete from vehicle where id = ?";

//    public final static String DELETE_VEHICLE_WHEN_IS_FOREIGN_KEY = "delete from booking where v_id = ?" for this we have to use 2 queries ask Sir
    public final static String INSERT_INTO_VEHICLE =
            "insert into vehicle(v_name,v_model,v_brand,v_color,owner_id) " +
                    "values (?,?,?,?,?)";

}
