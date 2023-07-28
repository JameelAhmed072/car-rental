package org.carrental.mapper;

import org.carrental.domain.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleMapper implements IMapper<Vehicle>{

    private final static String ID = "id";
    private final static String V_NAME = "v_name";
    private final static String V_MODEL = "v_model";
    private final static String V_BRAND = "v_brand";
    private final static String V_COLOR = "v_color";
    private final static String OWNERID = "owner_id";
    private final static String OWNERNAME = "o_name";
    private final static String COMMISSION = "commission";


    @Override
    public List<Vehicle> resultSetToList(ResultSet rs) throws SQLException {

        List<Vehicle> vehicleList = new ArrayList<>();

        while(rs.next()){
            Vehicle vehicle = Vehicle.builder()
                    .id((long) rs.getInt(ID))
                    .name(rs.getString(V_NAME))
                    .model(rs.getLong(V_MODEL))
                    .brand(rs.getString(V_BRAND))
                    .color(rs.getString(V_COLOR))
                    .ownerId(rs.getLong(OWNERID))
                    .ownerName(rs.getString(OWNERNAME))
//                    .commission(rs.getInt(COMMISSION))
                    .build();
            vehicleList.add(vehicle);
        }
        return vehicleList;
    }

    @Override
    public Vehicle resultToObject(ResultSet rs) throws SQLException {
        if(rs.next()){
            return Vehicle.builder()   //   this is just like constructor of Constructor, but using builder pattern
                    .id((long) rs.getInt(ID))
                    .name(rs.getString(V_NAME))
                    .model(rs.getLong(V_MODEL))
                    .brand(rs.getString(V_BRAND))
                    .color(rs.getString(V_COLOR))
                    .ownerId(rs.getLong(OWNERID))
                    .build();
        }
        return null;
    }
}
