package org.carrental.service;

import org.carrental.dao.BookingDAO;
import org.carrental.domain.Booking;
import org.carrental.domain.Customer;
import org.carrental.domain.Vehicle;
import org.carrental.domain.VehicleOwner;

import java.util.Date;
import java.util.List;

public class BookingService {
    private final BookingDAO dao = new BookingDAO();

    public void add(Integer customerId, Integer vehicleId, Date bookingDate, Integer amount) {
        Booking booking = Booking.builder()
                .c_id(customerId)
                .v_id(vehicleId)
                .booking_date(new java.sql.Date(bookingDate.getTime()))
                .amount(amount)
                .build();
        dao.insert(booking);
        System.out.println(vehicleId);
        dao.updateVehicleStatusAdd(vehicleId);
    }


    public String[] getVehicle() {
        List<Vehicle> vehicleList = dao.getAllVehicles();
        String[] data = new String[vehicleList.size()];
        for (int i = 0; i < vehicleList.size(); i++) {
            data[i] = String.valueOf(vehicleList.get(i).getId());
            data[i] += "," + vehicleList.get(i).getName();
        }
        return data;
    }

    public String[] getCustomer() {
        List<Customer> customerList = dao.getAllCustomer();
        String[] data = new String[customerList.size()];
        for (int i = 0; i < customerList.size(); i++) {
            data[i] = String.valueOf(customerList.get(i).getId());
            data[i] += "," + customerList.get(i).getName();
        }
        return data;
    }
    public String[] getAllOwners() {
        List<VehicleOwner> customerList = dao.getOwners();
        String[] data = new String[customerList.size()];
        for (int i = 0; i < customerList.size(); i++) {
            data[i] = String.valueOf(customerList.get(i).getId());
            data[i] += "," + customerList.get(i).getName();
        }
        return data;
    }
    public String[][] getAllMonthlyForJTabel(Date startDate, Date endDate){ //  bcz UI need data in 2D String, that's why return type is

        List<Booking> bookingList = dao.getMonthlyReport((java.sql.Date) startDate, (java.sql.Date) endDate);   //  jooo b getAll() see data ayeega usko customerList me daaala
        return transformToJTable(bookingList,6);
    }
    public String[][] getCommissionForJTabel(Date startDate, Date endDate){ //  bcz UI need data in 2D String, that's why return type is


        List<Booking> CommissionList = dao.totalCommission((java.sql.Date) startDate, (java.sql.Date) endDate);   //  jooo b getAll() see data ayeega usko customerList me daaala
        return transformToCommissionJTable(CommissionList,2);
    }
    private static String[][] transformToCommissionJTable(List<Booking> CommissionList,int columnSize){
        String[][] data = new String[CommissionList.size()][columnSize];
        for (int i = 0; i < CommissionList.size(); i++) {
            data[i][0] = CommissionList.get(i).getOwner_name();
            data[i][1] = String.valueOf(CommissionList.get(i).getCommission());
        }
        return data;
    }
    public String[][] getAllCARAVAILABILITYForJTabel(){
        List<Booking> carList = dao.getAvailableCars();
        return transformToJTable(carList,5);

    }
    private static String[][] transformToJTable(List<Booking> customerList,int columnSize){
        String[][] data = new String[customerList.size()][columnSize];
        for (int i = 0; i < customerList.size(); i++) {
            data[i][0] = String.valueOf(customerList.get(i).getV_id());
            data[i][1] = customerList.get(i).getVehicle_name();
            data[i][2] = String.valueOf(customerList.get(i).getOwner_name());
            data[i][3] = String.valueOf(customerList.get(i).getCommission());
            data[i][4] = String.valueOf(customerList.get(i).getBooking_status());
        }
        return data;
    }

    public String[][] getAllBookings() {
        List<Booking> bookingList = dao.getAll();
        return convertListTo2DArray(bookingList, 7);
    }

    public String[][] searchByDate(String date) {
        List<Booking> bookingList = dao.getByDate(date);
        return convertListTo2DArray(bookingList, 7);
    }

    public void completeBooking(String id, Date completeDate, Integer vehicleId) {
        dao.completeBooking(id, completeDate);
        dao.updateVehicleStatusComplete(vehicleId);
    }


    private String[][] convertListTo2DArray(List<Booking> bookingList, Integer columnSize) {
        String[][] data = new String[bookingList.size()][columnSize];
        for (int i = 0; i < bookingList.size(); i++) {
            data[i][0] = String.valueOf(bookingList.get(i).getId());
            data[i][1] = String.valueOf(bookingList.get(i).getC_id());
            data[i][1] += ", " + bookingList.get(i).getCustomer_name();
            data[i][2] = String.valueOf(bookingList.get(i).getV_id());
            data[i][2] += ", " + bookingList.get(i).getVehicle_name();
            data[i][3] = String.valueOf(bookingList.get(i).getBooking_date());
            data[i][4] = String.valueOf(bookingList.get(i).getAmount());
            data[i][5] = bookingList.get(i).getBooking_status();
        }
        return data;
    }
    public void updateBooking(String id, Integer cusId, Integer vehId, java.util.Date bookingDate, String amount, String status) {
        Booking booking = Booking.builder()
                .c_id(cusId)
                .v_id(vehId)
                .booking_date(new java.sql.Date(bookingDate.getTime()))
                .amount(Integer.valueOf(amount))
                .booking_status(status)
                .build();
        dao.update(booking, Long.valueOf(id));
    }
}