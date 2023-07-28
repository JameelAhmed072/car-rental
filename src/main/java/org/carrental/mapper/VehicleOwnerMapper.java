package org.carrental.mapper;

import org.carrental.domain.VehicleOwner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleOwnerMapper implements IMapper<VehicleOwner>{
    private final static String ID = "id";
    private final static String O_NAME = "o_name";
    private final static String O_CNIC = "o_cnic";
    private final static String O_NUMBER = "o_phone_number";
    private final static String O_ADDRESS = "o_address";
    private final static String COMMISSION = "commission";

    @Override
    public List<VehicleOwner> resultSetToList(ResultSet rs) throws SQLException {
        List<VehicleOwner> vehicleOwnersList = new ArrayList<>();

        while(rs.next()){
            VehicleOwner vehicleOwner = VehicleOwner.builder()   //   this is just like constructor of Constructor, but using builder pattern
                    .id(rs.getLong(ID))
                    .name(rs.getString(O_NAME))
                    .phonenumber(rs.getString(O_CNIC))
                    .cnic(rs.getString(O_NUMBER))
                    .address(rs.getString(O_ADDRESS))
                    .commission(rs.getDouble(COMMISSION))
                    .build();
            vehicleOwnersList.add(vehicleOwner);
        }
        return vehicleOwnersList;
    }

    @Override
    public VehicleOwner resultToObject(ResultSet rs) throws SQLException {
        if(rs.next()){
            return  VehicleOwner.builder()
                    .id((long) rs.getInt(ID))
                    .name(rs.getString(O_NAME))
                    .phonenumber(rs.getString(O_CNIC))
                    .cnic(rs.getString(O_NUMBER))
                    .address(rs.getString(O_ADDRESS))
                    .commission(rs.getDouble(COMMISSION))
                    .build();
        }
        return null;
    }
}
