package org.carrental;

import org.carrental.dao.CustomerDAO;
import org.carrental.dao.VehicleOwnerDAO;
//import org.carrental.domain.Booking;
import org.carrental.domain.Customer;
import org.carrental.domain.VehicleOwner;
import org.carrental.mapper.CustomerMapper;
import org.carrental.ui.HomeUI;
import org.carrental.ui.LoginUI;

public class Main {
    public static void main(String[] args) {
        //  3tyre architecture ==> this is


        // ui -> service -> dao -> mapper -> domain
        //  DAO(Repository) data access object package ==> yaha sub kaam jo database see related o
        //  Domain package ==>sub concept ki classes yaha ayeenge  e.g booking,customer,vehicle etc
        //  Servie package ==> yaha kohi b business logic ogi too woo yaha oga, actual logic if, for loop etc
        //  mapper package ==>  iss meee classes onnge, jab app iss me ResultSet pass karooge too apko object deenge aur apko uss object see jooo chaeeyee woo app waha baana kar use kar sakte ha





                CustomerDAO customerDAO = new CustomerDAO();


        //   ----------    getAll()   method Start ---------------------------------------//

//                         customerDAO.getAll().forEach(System.out::println);

        //   ----------    getAll()   method End ---------------------------------------//



        //   ----------    insert()   method Start ---------------------------------------//

//        Customer newCus = new Customer(); //   or use builder Pattern
//        Customer newCus = Customer.builder()
//                .name("Aziz")
//                .cnic("000045456")
//                .address("Quetta")
//                .phoneNumber("1111111")
//                .build();
//
//        customerDAO.insert(newCus);
//        customerDAO.getAll().forEach(System.out::println);

        //   ----------    insert()   method End ---------------------------------------//



        //   ----------    getById()   method Start ---------------------------------------//

//        System.out.println(customerDAO.getById(3L));

        //   ----------    getById()   method End ---------------------------------------//


        //   ----------    update()   method Start ---------------------------------------//


//        Customer customer = customerDAO.getById(3L);
//        customer.setName("Khan");
//        customerDAO.update(customer,3L);
//        System.out.println(customerDAO.getById(3L));

        //   ----------    update()   method End ---------------------------------------//


        //   ----------    deleteById()   method Start ---------------------------------------//

//        customerDAO.deleteById(3L);
//        customerDAO.getAll().forEach(System.out::println);

        //   ----------    deleteById()   method End ---------------------------------------//

//  ---------------------------------------------------------------------------------------------------------------//

//    =======>     Vehicle Owner table  <=======

//        VehicleOwnerDAO vehicleOwnerDAO = new VehicleOwnerDAO();

//        vehicleOwnerDAO.getAll().forEach(System.out::println);




//        VehicleOwner newOwner = VehicleOwner.builder()
//                .name("Aziz")
//                .cnic("000045456")
//                .phonenumber("11111110")
//                .address("Quetta")
//                .commission(122D)
//                .build();
//
//        vehicleOwnerDAO.insert(newOwner);
//        vehicleOwnerDAO.getAll().forEach(System.out::println);


//        System.out.println(vehicleOwnerDAO.getById(3L));




        //   -------------------------------------------------------------------//
        //  Swing
//        LoginUI login = new LoginUI();  //  or
//
//        new LoginUI();  // ananomyus object


        new HomeUI();

    }
}