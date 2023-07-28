package org.carrental.service;

import org.carrental.dao.VehicleDAO;
import org.carrental.domain.Booking;
import org.carrental.domain.Vehicle;

import java.util.List;

public class VehicleService {

        VehicleDAO dao = new VehicleDAO();

    public String[][] searchByName(String name){  //   we will put this is textfield of left panel of customer
        List<Vehicle> vehicleList = dao.getByName(name);

        return transformToJTable(vehicleList,6);
    }
    public String[][] getAllVehicleForJTabel(){ //  bcz UI need data in 2D String, that's why return type is

        List<Vehicle> vehicleList = dao.getAll();   //  jooo b getAll() see data ayeega usko customerList me daaala
        return transformToJTable(vehicleList,6);
    }
    public String[][] getAllCARAVAILABILITYForJTabel(){
        List<Vehicle> carList = dao.getAvailableCars();
        return transformToAvailableJTable(carList,5);

    }
    private static String[][] transformToJTable(List<Vehicle> vehicleList,int columnSize){

        String[][] data = new String[vehicleList.size()][columnSize];
        for (int i = 0; i < vehicleList.size(); i++) {
            data[i][0] = String.valueOf(vehicleList.get(i).getId());
            data[i][1] = vehicleList.get(i).getName();
            data[i][2] = String.valueOf(vehicleList.get(i).getModel());
            data[i][3] = vehicleList.get(i).getBrand();
            data[i][4] = vehicleList.get(i).getColor();
            data[i][5] = String.valueOf(vehicleList.get(i).getOwnerName());
        }
        return data;
    }
    private static String[][] transformToAvailableJTable(List<Vehicle> vehicleList,int columnSize){

        String[][] data = new String[vehicleList.size()][columnSize];
        for (int i = 0; i < vehicleList.size(); i++) {
            data[i][0] = String.valueOf(vehicleList.get(i).getId());
            data[i][1] = vehicleList.get(i).getName();
            data[i][2] = String.valueOf(vehicleList.get(i).getOwnerName());
            data[i][3] = vehicleList.get(i).getBrand();
            data[i][4] = vehicleList.get(i).getColor();

        }
        return data;
    }

    public void save(String name, String model, String brand, String color, Long ownerId) {
        Vehicle vehicle = Vehicle.builder()
                .name(name)
                .model(Long.valueOf(model))
                .brand(brand)
                .color(color)
                .ownerId(ownerId)
                .build();
        dao.insert(vehicle);
    }
    public void editRow(String id,String name,String model,String brand, String color, Long ownerId ) {
        Vehicle vehicle = dao.getById(Long.valueOf(id));
        vehicle.setName(name);
        vehicle.setModel(Long.valueOf(model));
        vehicle.setBrand(brand);
        vehicle.setColor(color);
        vehicle.setOwnerId(ownerId);
        dao.update(vehicle, Long.valueOf(id));

    }
    public Vehicle getById(Integer id) {
        return dao.getById(Long.valueOf(id));
    }
    public void delete(Long id){
        dao.deleteById(id);
    }
    public String[][] getAllVehicles() {

        List<Vehicle> vehicle = dao.getBookingsDetails();
        String[][] data = new String[vehicle.size()][2];
        for (int i = 0; i < vehicle.size(); i++) {
            data[i][0] = String.valueOf(vehicle.get(i).getId());
            data[i][1] = String.valueOf(vehicle.get(i).getName());
        }
        return data;
    }
}
