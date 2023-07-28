package org.carrental.dao;

import org.carrental.domain.Booking;
import org.carrental.domain.Customer;
import org.carrental.domain.Vehicle;
import org.carrental.domain.VehicleOwner;
import org.carrental.mapper.BookingMapper;
import org.carrental.mapper.CustomerMapper;
import org.carrental.mapper.VehicleMapper;
import org.carrental.mapper.VehicleOwnerMapper;

import java.sql.*;
import java.util.List;

import static org.carrental.dao.BookingSqlQueryConstant.*;
import static org.carrental.dao.SqlQueryConstant.*;

public class BookingDAO extends BaseDAO implements ICrud<Booking> {
    private final BookingMapper mapper = new BookingMapper();
    private final VehicleMapper vehicleMapper = new VehicleMapper();
    private final VehicleOwnerMapper vehicleOwnerMapper = new VehicleOwnerMapper();
    private final CustomerMapper customerMapper = new CustomerMapper();

    @Override
    public void insert(Booking obj) {
        try {
            PreparedStatement ps = conn.prepareStatement(POST);
            ps.setInt(1, obj.getC_id());
            ps.setInt(2, obj.getV_id());
            ps.setDate(3, obj.getBooking_date());
            ps.setInt(4, obj.getAmount());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateVehicleStatusAdd(Integer id) {
        try {
            PreparedStatement statement = conn.prepareStatement(UPDATE_VEHICLE_STATUS_AFTER_ADD);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Vehicle> getAllVehicles() {
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(GetAllVehicles);
            return vehicleMapper.resultSetToList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Customer> getAllCustomer() {
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from customer");
            return customerMapper.resultSetToList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<VehicleOwner> getOwners() {
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from vehicle_owner");
            return vehicleOwnerMapper.resultSetToList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Customer> getAllOwners() {
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from customer");
            return customerMapper.resultSetToList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Booking> getAll() {
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(Bookings);
            return mapper.resultToListBooking(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Booking> getMonthlyReport(Date startDate, Date endDate){
        try {
            PreparedStatement ps = conn.prepareStatement(MONTHLY_REPORT_QUERY);
            ps.setDate(1,startDate);
            ps.setDate(2,endDate);
            ResultSet rs = ps.executeQuery();
            return mapper.resultSetToList(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Booking> getCommissionReport(Date startDate, Date endDate){
        try {
            PreparedStatement ps = conn.prepareStatement(COMMISSION_REPORT_QUERY);
            ps.setDate(1,startDate);
            ps.setDate(2,endDate);
            ResultSet rs = ps.executeQuery();
            return mapper.resultSetToList(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void completeBooking(String id, java.util.Date date) {
        try {
            PreparedStatement statement = conn.prepareStatement(COMPLETE_BOOKING);
            statement.setDate(1, new java.sql.Date(date.getTime()));
            statement.setString(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateVehicleStatusComplete(Integer id) {
        try {
            PreparedStatement statement = conn.prepareStatement(UPDATE_VEHICLE_STATUS_AFTER_COMPLETE);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void softDelete(String id) {
        try {
            PreparedStatement statement = conn.prepareStatement(SOFT_DELETE);
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Booking> getByDate(String date) {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from booking where booking_date like '%" + date + "%' and status != 'Inactive'");
            ResultSet resultSet = ps.executeQuery();
            return mapper.resultSetToList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Booking getById(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(GET_BY_ID);
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            return mapper.resultToObject(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Booking obj, Long id) {
        try {
            PreparedStatement statement = conn.prepareStatement(UPDATE);
            statement.setInt(1, obj.getC_id());
            statement.setInt(2, obj.getV_id());
            statement.setDate(3, obj.getBooking_date());
            statement.setInt(4, obj.getAmount());
            statement.setString(5, obj.getBooking_status());
            statement.setLong(6, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Booking> monthlyReports(java.util.Date startDate, java.util.Date endDate) {
        try {
            PreparedStatement statement = conn.prepareStatement(MONTHLY_REPORT);
            statement.setDate(1, new java.sql.Date(startDate.getTime()));
            statement.setDate(2, new java.sql.Date(endDate.getTime()));
            ResultSet resultSet = statement.executeQuery();
            return mapper.resultSetToList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Booking> totalCommission(java.util.Date startDate, java.util.Date endDate) {
        try {
            PreparedStatement statement = conn.prepareStatement(GET_COMMISSION);
            statement.setDate(1, new java.sql.Date(startDate.getTime()));
            statement.setDate(2, new java.sql.Date(endDate.getTime()));
            ResultSet resultSet = statement.executeQuery();
            return mapper.ResultSetToListOfCommission(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Booking> getAvailableCars(){
        try {
            PreparedStatement ps = conn.prepareStatement(GetAllAvailableCarsDetails);
            ResultSet rs = ps.executeQuery();
            return mapper.resultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {

    }
}