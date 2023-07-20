package org.carrental.dao;

public class SqlQueryConstant {

    public final static String GET_ALL_CUSTOMER = "select * from customer";
    public final static String GET_CUSTOMER_BY_ID = "select * from customer where id = ?";
    public final static String UPDATE_CUSTOMER_BY_ID = "update customer set c_name = ? where id = ?";
    public final static String DELETE_CUSTOMER_BY_ID = "delete from customer where id = ?";
    public final static String INSERT_INTO_CUSTOMER =
            "insert into customer(c_name,c_number,c_cnic,c_address,ref_phone_number) " +
            "values (?,?,?,?,?)";


    //  --------   vehicle owner table   --------------//

    public final static String GET_ALL_OWNERS = "select * from vehicle_owner";
    public final static String GET_VEHICLE_OWNER_BY_ID = "select * from vehicle_owner where id = ?";
    public final static String UPDATE_VEHICLE_OWNER_BY_ID = "update vehicle_owner set o_name = ? where id = ?";
    public final static String DELETE_VEHICLE_OWNER_BY_ID = "delete from vehicle_owner where id = ?";
    public final static String INSERT_INTO_VEHICLE_OWNER =
            "insert into vehicle_owner(o_name,o_cnic,o_phone_number,o_address,commission) " +
                    "values (?,?,?,?,?)";

    //  user
    public final static String GET_USER_BY_USERNAME_AND_PASSWORD = "select * from user where username = ? AND pass = ?";
}
