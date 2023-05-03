package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Automotora {
    private ObservableList<Vehiculo> vehiculos;
    private ObservableList<Cliente> clientes;

    public Automotora(){
        vehiculos = FXCollections.observableArrayList();
        clientes = FXCollections.observableArrayList();
    }

    //Getters
    public ObservableList<Vehiculo> getVehiculos() {
        return vehiculos;
    }
    public ObservableList<Cliente> getClientes() {
        return clientes;
    }

    //Metodos que nos permitiran cumplir con las funciones exigidas
    public void agregarCliente(Cliente cliente){
        clientes.add(cliente);
    }
    public void agregarVehiculo(Vehiculo vehiculo){
        vehiculos.add(vehiculo);
    }
    public ObservableList<Vehiculo> buscarVehiculos(String marca, String modelo){
        ObservableList<Vehiculo> vehiculosEncontrados = FXCollections.observableArrayList();

        for(int i = 0; i < vehiculos.size(); i++){
            if(vehiculos.get(i).getMarca().equalsIgnoreCase(marca)){
                if(vehiculos.get(i).getModelo().equalsIgnoreCase(modelo)){
                    vehiculosEncontrados.add(vehiculos.get(i));
                }
            }
        }

        return vehiculosEncontrados;
    }
}
