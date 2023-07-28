package org.carrental.service;

import org.carrental.dao.CustomerDAO;
import org.carrental.domain.Customer;

import javax.swing.*;
import java.util.List;

public class CustomerService {

    CustomerDAO dao = new CustomerDAO();


    public String[][] searchByName(String name){  //   we will put this is textfield of left panel of customer
        List<Customer> customerList = dao.getByName(name);

        return transformToJTable(customerList,6);
    }



    public String[][] getAllCustomerForJTabel(){ //  bcz UI need data in 2D String, that's why return type is

        List<Customer> customerList = dao.getAll();   //  jooo b getAll() see data ayeega usko customerList me daaala
        return transformToJTable(customerList,6);
    }
    private static String[][] transformToJTable(List<Customer> customerList,int columnSize){
        //  UI ko data 2D String mee chaeye too hum data ko ussko convert karenge.
        String[][] data = new String[customerList.size()][columnSize];
        for (int i = 0; i < customerList.size(); i++) {
            data[i][0] = String.valueOf(customerList.get(i).getId());
            data[i][1] = customerList.get(i).getName();
            data[i][2] = customerList.get(i).getPhoneNumber();
            data[i][3] = customerList.get(i).getCnic();
            data[i][4] = customerList.get(i).getAddress();
            data[i][5] = customerList.get(i).getReferencePhoneNumber();
        }
        return data;
    }
    public void save(String name, String phone, String cnic, String address, String refPhoneNumber) {
        Customer customer = Customer.builder()
                .phoneNumber(phone)
                .cnic(cnic)
                .name(name)
                .address(address)
                .referencePhoneNumber(refPhoneNumber)
                .build();

        dao.insert(customer);
    }
    public void editRow(String id,String name,String phoneNumber,String cnic,String address,String refPhoneNUmber) {
        Customer customer = dao.getById(Long.valueOf(id));
        customer.setName(name);
        customer.setPhoneNumber(phoneNumber);
        customer.setCnic(cnic);
        customer.setAddress(address);
        customer.setReferencePhoneNumber(refPhoneNUmber);
        dao.update(customer, Long.valueOf(id));
    }
    public Customer getById(Integer id) {
        return dao.getById(Long.valueOf(id));
    }
    public void delete(Long id){
        dao.deleteById(id);
    }
    public String[][] getAllCustomers() {

        List<Customer> bookings = dao.getBookingsDetails();
        String[][] data = new String[bookings.size()][2];
        for (int i = 0; i < bookings.size(); i++) {
            data[i][0] = String.valueOf(bookings.get(i).getId());
            data[i][1] = String.valueOf(bookings.get(i).getName());
        }
        return data;
    }
}
