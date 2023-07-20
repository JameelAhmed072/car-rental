package org.carrental.mapper;

import org.carrental.domain.Customer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class CustomerMapper implements IMapper<Customer>{

    private final static String ID = "id";
    private final static String C_NAME = "c_name";
    private final static String C_NUMBER = "c_number";
    private final static String C_CNIC = "c_cnic";
    private final static String C_ADDRESS = "c_address";
    private final static String REF_PHONE_NUMBER = "ref_phone_number";
    @Override
    public List<Customer> resultSetToList(ResultSet rs) throws SQLException {
        List<Customer> customerList = new ArrayList<>();

        while(rs.next()){
            Customer customer = Customer.builder()   //   this is just like constructor of Constructor, but using builder pattern
                    .id((long) rs.getInt(ID))
                    .name(rs.getString(C_NAME))
                    .phoneNumber(rs.getString(C_NUMBER))
                    .cnic(rs.getString(C_CNIC))
                    .address(rs.getString(C_ADDRESS))
                    .referencePhoneNumber(rs.getString(REF_PHONE_NUMBER))
                    .build();
            customerList.add(customer);
        }
        return customerList;
    }

    @Override
    public Customer resultToObject(ResultSet rs) throws SQLException {

        if(rs.next()){
            return  Customer.builder()
                    .id((long) rs.getInt(ID))
                    .name(rs.getString(C_NAME))
                    .phoneNumber(rs.getString(C_NUMBER))
                    .cnic(rs.getString(C_CNIC))
                    .address(rs.getString(C_ADDRESS))
                    .referencePhoneNumber(rs.getString(REF_PHONE_NUMBER))
                    .build();
        }
        return null;
    }
}
