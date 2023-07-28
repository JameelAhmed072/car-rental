package org.carrental.service;

import org.carrental.dao.VehicleOwnerDAO;
//import org.carrental.domain.Booking;
import org.carrental.domain.Vehicle;
import org.carrental.domain.VehicleOwner;

import java.util.List;

public class VehicleOwnerService {

    VehicleOwnerDAO dao = new VehicleOwnerDAO();

    public String[][] getAllCustomerForJTabel(){ //  bcz UI need data in 2D String, that's why return type is

        List<VehicleOwner> vehicleOwnerList = dao.getAll();   //  jooo b getAll() see data ayeega usko customerList me daaala
        return transformToJTable(vehicleOwnerList,6);
    }

    private static String[][] transformToJTable(List<VehicleOwner> vehicleOwnerList,int columnSize){
        //  UI ko data 2D String mee chaeye too hum data ko ussko convert karenge.
        String[][] data = new String[vehicleOwnerList.size()][columnSize];
        for (int i = 0; i < vehicleOwnerList.size(); i++) {
            data[i][0] = String.valueOf(vehicleOwnerList.get(i).getId());
            data[i][1] = vehicleOwnerList.get(i).getName();
            data[i][2] = vehicleOwnerList.get(i).getCnic();
            data[i][3] = vehicleOwnerList.get(i).getPhonenumber();
            data[i][4] = vehicleOwnerList.get(i).getAddress();
            data[i][5] = String.valueOf(vehicleOwnerList.get(i).getCommission());

        }
        return data;
    }
    public String[] getAllVehicleOwners() {
        List<VehicleOwner> vehicleOwnerList = dao.getAllVehiclesOwners();
        String[] data = new String[vehicleOwnerList.size()];
        for (int i = 0; i < vehicleOwnerList.size(); i++) {
            data[i] = String.valueOf(vehicleOwnerList.get(i).getId());
            data[i] += "," + vehicleOwnerList.get(i).getName();
        }
        return data;
    }

    public void save(String name, String cnic, String phoneNumber, String address, Double commission) {
        VehicleOwner vehicleOwner = VehicleOwner.builder()
                .name(name)
                .cnic(cnic)
                .phonenumber(phoneNumber)
                .address(address)
                .commission(commission)
                .build();

        dao.insert(vehicleOwner);
    }
    public void editRow(String id,String name) {
        VehicleOwner vehicleOwner = dao.getById(Long.valueOf(id));
        vehicleOwner.setName(name);
        dao.update(vehicleOwner, Long.valueOf(id));

    }
    public VehicleOwner getById(Integer id) {
        return dao.getById(Long.valueOf(id));
    }
    public void delete(Long id){
        dao.deleteById(id);
    }

    public String[][] searchByName(String name){  //   we will put this is textfield of left panel of customer
        List<VehicleOwner> vehicleOwnerList = dao.getByName(name);

        return transformToJTable(vehicleOwnerList,6);
    }
}
