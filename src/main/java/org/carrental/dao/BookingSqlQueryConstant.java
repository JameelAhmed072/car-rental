package org.carrental.dao;


public class BookingSqlQueryConstant {
    public final static String POST = "insert into booking (c_id,v_id,booking_date,price) values(?,?,?,?)";
    //            " inner join vehicle v on b.vehicle_id = v.id inner join vehicle_owner o on v.owner_id = o.id " +
    public final static String UPDATE_VEHICLE_STATUS_AFTER_ADD = "UPDATE vehicle SET vstatus = 'Booked' WHERE id = ?";
    public final static String GET_ALL = "select b.id, b.c_id, b.v_id, b.booking_date,b.price, b.booking_status, c.c_name, v.v_name from booking b inner join customer c on b.c_id = c.id inner join vehicle v on b.v_id = v.id where b.booking_status in ('Open','Completed')";
    public final static String Bookings = "select b.id, b.c_id, b.v_id, b.booking_date, b.price, b.booking_status, c.c_name, v.v_name from booking b inner join customer c on b.c_id = c.id inner join vehicle v on b.v_id = v.id ";
    public final static String GetmonthlyBookings = "select b.id, b.c_id, b.v_id, b.booking_date, b.price, b.booking_status, c.c_name, v.v_name from booking b inner join customer c on b.c_id = c.id inner join vehicle v on b.v_id = v.id where booking_date ";
    public final static String GetAllVehicles = "select v.id,v.v_name,v.v_model,v.v_brand,v.v_color,\n" +
            "            v.vstatus,v.owner_id,vo.o_name from vehicle v\n" +
            "            inner join vehicle_owner vo on vo.id = v.owner_id\n";
    public final static String GET_BY_ID = "select * from booking where id = ?";
    public final static String COMPLETE_BOOKING = "UPDATE booking set booking_status = 'Completed' , complete_date = ? WHERE id = ?";
    public final static String UPDATE_VEHICLE_STATUS_AFTER_COMPLETE = "UPDATE vehicle SET vstatus = 'Active' WHERE id = ?";
    public final static String SOFT_DELETE = "UPDATE booking set booking_status = 'Inactive' WHERE id = ?";
    public final static String UPDATE = "update booking set c_id = ?, v_id = ?, booking_date=?, price=?, booking_status=? where id = ?";
    public final static String MONTHLY_REPORT = "select b.id, b.c_id, b.v_id, b.booking_date, " +
            " b.price * DATEDIFF(complete_date,booking_date) as total_amount, b.price, DATEDIFF(b.complete_date,b.booking_date) " +
            "as total_days, b.booking_status, c.c_name, v.v_name from booking b inner join customer c on b.c_id = c.id " +
            "inner join vehicle v on b.v_id = v.id where b.complete_date BETWEEN ? AND ? AND b.booking_status='Completed'";
    public final static String GET_COMMISSION = "select Sum(o.commission*(DATEDIFF(b.complete_date , b.booking_date)*b.price)/100)" +
            " as total_commission from booking b inner join vehicle v on v.id=b.v_id inner join " +
            "vehicle_owner o on o.id = v.owner_id where (b.complete_date Between ? And ?)";

}