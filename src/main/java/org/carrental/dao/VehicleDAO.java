package org.carrental.dao;

//import org.carrental.domain.Booking;
import org.carrental.domain.Booking;
import org.carrental.domain.Vehicle;
import org.carrental.mapper.VehicleMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.carrental.dao.SqlQueryConstant.GetAllAvailableCarsDetails;
import static org.carrental.dao.SqlQueryConstant.INSERT_INTO_VEHICLE;

public class VehicleDAO extends BaseDAO implements ICrud<Vehicle>{

    final private VehicleMapper vehicleMapper = new VehicleMapper();
    @Override
    public void insert(Vehicle obj) {
        try {
            PreparedStatement ps = conn.prepareStatement(INSERT_INTO_VEHICLE);
            ps.setString(1,obj.getName());
            ps.setLong(2,obj.getModel());
            ps.setString(3,obj.getBrand());
            ps.setString(4,obj.getColor());
            ps.setLong(5,obj.getOwnerId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Vehicle> getAll() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SqlQueryConstant.GET_ALL_VEHICLE);
            return vehicleMapper.resultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Vehicle> getAvailableCars(){
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(GetAllAvailableCarsDetails);
            return vehicleMapper.resultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Vehicle getById(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.GET_VEHICLE_BY_ID);
            ps.setInt(1,id.intValue());
            ResultSet rs = ps.executeQuery();
            return vehicleMapper.resultToObject(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Vehicle> getByName(String name) {
        try {
            PreparedStatement ps = conn.prepareStatement("select v.id,v.v_name,v.v_model,v.v_brand,v.v_color,v.owner_id,vo.o_name from vehicle v\n" +
                    "inner join vehicle_owner vo on v.owner_id = vo.id\n" +
                    " where v.v_name like '%"+name+"%'");
            ResultSet rs = ps.executeQuery();
            return vehicleMapper.resultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void update(Vehicle obj, Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.UPDATE_VEHICLE_BY_ID);
            ps.setString(1,obj.getName());
            ps.setLong(2,obj.getModel());
            ps.setString(3,obj.getBrand());
            ps.setString(4,obj.getColor());
            ps.setLong(5,obj.getOwnerId());
            ps.setInt(6,id.intValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void deleteById(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.DELETE_VEHICLE_BY_ID);
            ps.setInt(1,id.intValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Vehicle> getBookingsDetails(){
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SqlQueryConstant.GetAllIDAndVehicle);
            return vehicleMapper.resultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
