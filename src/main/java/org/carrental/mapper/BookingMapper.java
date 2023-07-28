package org.carrental.mapper;


import org.carrental.domain.Booking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingMapper implements IMapper<Booking> {

    private static final String ID = "id";
    private static final String CUSTOMER_ID = "c_id";
    private static final String VEHICLE_ID = "v_id";
    private static final String BOOKING_DATE = "booking_date";
    private static final String COMPLETE_DATE = "complete_date";
    private static final String AMOUNT = "price";
    private static final String BSTATUS = "booking_status";
    private static final String COMMISSION = "commission";
    private static final String CUSTOMER_NAME = "c_name";
    private static final String VEHICLE_NAME = "v_name";
    private static final String TOTAL_AMOUNT = "total_amount";
    private static final String TOTAL_DAYS = "total_days";

    @Override
    public List<Booking> resultSetToList(ResultSet resultSet) throws SQLException {
        List<Booking> bookingList = new ArrayList<>();
        while (resultSet.next()) {
            Booking booking = Booking.builder()
                    .id(resultSet.getInt("id"))
                    .c_id(resultSet.getInt("c_id"))
                    .v_id(resultSet.getInt("v_id"))
                    .booking_date(resultSet.getDate("booking_date"))
                    .complete_date(resultSet.getDate("complete_date"))
                    .amount(resultSet.getInt("price"))
                    .booking_status(resultSet.getString("booking_status"))
                    .customer_name(resultSet.getString("c_name"))
                    .vehicle_name(resultSet.getString("v_name"))
                    .commission(resultSet.getInt("commission"))
                    .owner_name(resultSet.getString("o_name"))
//                    .total_commission(resultSet.getInt("total_commission"))
//                    .total_amount(resultSet.getInt(TOTAL_AMOUNT))
//                    .total_days(resultSet.getInt(TOTAL_DAYS))
                    .build();
            bookingList.add(booking);
        }
        return bookingList;
    }

    public List<Booking> resultToListBooking(ResultSet resultSet) throws SQLException {
        List<Booking> bookingList = new ArrayList<>();
        while (resultSet.next()) {
            Booking booking = Booking.builder()
                    .id(resultSet.getInt(ID))
                    .c_id(resultSet.getInt(CUSTOMER_ID))
                    .v_id(resultSet.getInt(VEHICLE_ID))
                    .booking_date(resultSet.getDate(BOOKING_DATE))
                    .amount(resultSet.getInt(AMOUNT))
                    .booking_status(resultSet.getString(BSTATUS))
                    .customer_name(resultSet.getString(CUSTOMER_NAME))
                    .vehicle_name(resultSet.getString(VEHICLE_NAME))
                    .build();
            bookingList.add(booking);
        }
        return bookingList;
    }

    public List<Booking> ResultSetToListOfCommission(ResultSet rs) throws SQLException {
        List<Booking> commissionList = new ArrayList<>();
        while (rs.next()) {
            Booking booking = Booking.builder()
                    .commission(rs.getInt(COMMISSION))
                    .build();
            commissionList.add(booking);
        }
        return commissionList;
    }

    @Override
    public Booking resultToObject(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            Booking booking = Booking.builder()
                    .id(resultSet.getInt(ID))
                    .c_id(resultSet.getInt(CUSTOMER_ID))
                    .v_id(resultSet.getInt(VEHICLE_ID))
                    .booking_date(resultSet.getDate(BOOKING_DATE))
                    .complete_date(resultSet.getDate(COMPLETE_DATE))
                    .amount(resultSet.getInt(AMOUNT))
                    .booking_status(resultSet.getString(BSTATUS))
                    .customer_name(resultSet.getString(CUSTOMER_NAME))
                    .vehicle_name(resultSet.getString(VEHICLE_NAME))
                    .build();
            return booking;
        }
        return null;
    }
}