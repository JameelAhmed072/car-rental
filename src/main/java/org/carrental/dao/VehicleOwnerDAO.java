package org.carrental.dao;

//import org.carrental.domain.Booking;
import org.carrental.domain.Vehicle;
import org.carrental.domain.VehicleOwner;
import org.carrental.mapper.CustomerMapper;
import org.carrental.mapper.VehicleOwnerMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.carrental.dao.BookingSqlQueryConstant.GetAllVehicles;
import static org.carrental.dao.SqlQueryConstant.GetAllVehiclesOwners;
import static org.carrental.dao.SqlQueryConstant.INSERT_INTO_VEHICLE_OWNER;

public class VehicleOwnerDAO extends BaseDAO implements ICrud<VehicleOwner>{
    private final VehicleOwnerMapper vehicleOwnerMapper = new VehicleOwnerMapper(); // object of customer mapper;

    @Override
    public void insert(VehicleOwner obj) {
        try {
            PreparedStatement ps = conn.prepareStatement(INSERT_INTO_VEHICLE_OWNER);
            ps.setString(1,obj.getName());
            ps.setString(2,obj.getCnic());
            ps.setString(3,obj.getPhonenumber());
            ps.setString(4,obj.getAddress());
            ps.setDouble(5,obj.getCommission());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<VehicleOwner> getAllVehiclesOwners() {
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from vehicle_owner");
            return vehicleOwnerMapper.resultSetToList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<VehicleOwner> getAll() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SqlQueryConstant.GET_ALL_OWNERS);
            return vehicleOwnerMapper.resultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<VehicleOwner> getByName(String name) {
        try {
            PreparedStatement ps = conn.prepareStatement("select id,o_name,o_cnic,o_phone_number,o_address,commission from vehicle_owner where o_name like '%"+name+"%';\n");
            ResultSet rs = ps.executeQuery();
            return vehicleOwnerMapper.resultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public VehicleOwner getById(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.GET_VEHICLE_OWNER_BY_ID);
            ps.setInt(1,id.intValue());
            ResultSet rs = ps.executeQuery();
            return vehicleOwnerMapper.resultToObject(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(VehicleOwner obj, Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.UPDATE_VEHICLE_OWNER_BY_ID);
            ps.setString(1,obj.getName());
            ps.setInt(2,id.intValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void deleteById(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.DELETE_VEHICLE_OWNER_BY_ID);
            ps.setInt(1,id.intValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
