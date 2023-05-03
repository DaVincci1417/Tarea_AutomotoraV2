package com.example.automotorav2;

import Model.Automotora;
import Model.Cliente;
import Model.Vehiculo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable{
    //Panel Pricipal
    @FXML
    AnchorPane panelPrincipal;
    @FXML
    Button botonBuscarVehiculos, botonAgregarVehiculos, botonAgregarClientes;

    //Panel Registro Cliente
    @FXML
    AnchorPane panelCliente;
    @FXML
    TextField txtNombre, txtRut, txtDireccion, txtCorreo, txtTelefono;
    @FXML
    Button volverCliente, limpiarCliente;

    //Panel Registro Vehiculo
    @FXML
    AnchorPane panelAuto;
    @FXML
    TextField txtMarca, txtModelo, txtKilometros, txtPrecio;
    @FXML
    ComboBox<String> comboBoxAño = new ComboBox<>();
    @FXML
    ComboBox<String> comboBoxColor = new ComboBox<>();
    @FXML
    Button volverAuto, limpiarVehiculo;

    //Panel Buscar Vehiculo
    @FXML
    AnchorPane panelBuscar;
    @FXML
    ComboBox<String> comboBoxMarca = new ComboBox<>();
    @FXML
    TextField txtModeloBuscar;
    @FXML
    Button volverBuscar, limpiarBuscar;

    //Panel Resultado Busqueda
    @FXML
    AnchorPane panelTabla;
    @FXML
    ListView<Vehiculo> listaAutos;
    @FXML
    Button volverTabla;

    Automotora automotora = new Automotora();
    ObservableList<Vehiculo> vehiculosEncontrados;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        vehiculosEncontrados = FXCollections.observableArrayList();

        comboBoxAño.setItems(agregarAñosComboBox());
        comboBoxColor.setItems(agregarColeresComboBox());

        listaAutos.setItems(vehiculosEncontrados);
    }

    //Agregar Clientes
    @FXML
    public void agregarCliente(){
        if(txtNombre.getText().isEmpty() || txtRut.getText().isEmpty() ||
                txtDireccion.getText().isEmpty() || txtCorreo.getText().isEmpty()
                || txtTelefono.getText().isEmpty()){
            mensajeErrorBotonAgregarCliente();
        }else{
            if(validarRut(txtRut.getText()) == 0){
                mensajeErrorBotonAgregarCliente();
            }else{
                if(validarCorreo(txtCorreo.getText()) == 0){
                    mensajeErrorBotonAgregarCliente();
                }else{
                    automotora.agregarCliente(new Cliente(txtNombre.getText(), txtRut.getText(), txtDireccion.getText(),
                            txtTelefono.getText(),txtCorreo.getText()));
                    mensajeConfirmacionAgregarClientes();
                }
            }
        }
    }
    private void mensajeConfirmacionAgregarClientes(){
        Alert mensaje = new Alert(Alert.AlertType.NONE);
        mensaje.setTitle("Agregar Clientes");
        mensaje.setHeaderText("Registro exitoso");
        mensaje.setContentText("Cliente registrado correctamente");
        ButtonType botonOk = new ButtonType("Ok");
        mensaje.getButtonTypes().setAll(botonOk);
        mensaje.show();
    }
    private void mensajeErrorBotonAgregarCliente(){
        if(txtNombre.getText().isEmpty() || txtRut.getText().isEmpty() ||
                txtDireccion.getText().isEmpty() || txtCorreo.getText().isEmpty()
                || txtTelefono.getText().isEmpty()){
            Alert mensaje = new Alert(Alert.AlertType.ERROR);
            mensaje.setTitle("Agregar Clientes");
            mensaje.setHeaderText("Error");
            mensaje.setContentText("No puede dejar campos vacios");
            ButtonType botonOk = new ButtonType("Ok");
            mensaje.getButtonTypes().setAll(botonOk);
            mensaje.show();
        }
        if(validarRut(txtRut.getText()) == 0){
            Alert mensaje = new Alert(Alert.AlertType.ERROR);
            mensaje.setTitle("Agregar Clientes");
            mensaje.setHeaderText("Error");
            mensaje.setContentText("Ha ingresado un RUT invalido");
            ButtonType botonOk = new ButtonType("Ok");
            mensaje.getButtonTypes().setAll(botonOk);
            mensaje.show();
        }
        if(validarCorreo(txtCorreo.getText()) == 0){
            Alert mensaje = new Alert(Alert.AlertType.ERROR);
            mensaje.setTitle("Agregar Clientes");
            mensaje.setHeaderText("Error");
            mensaje.setContentText("Ha ingresado un correo electronico invalido");
            ButtonType botonOk = new ButtonType("Ok");
            mensaje.getButtonTypes().setAll(botonOk);
            mensaje.show();
        }
    }
    private int validarRut(String rut){
        String rutSinPuntosNiGuion = quitarPuntosYGuion(rut);
        String mensajeError = "Ingrese un RUT valido.";

        if(rutSinPuntosNiGuion.length() > 9){
            return 0;
        }
        if(!rut.substring(8,9).equalsIgnoreCase("k")){
            try {
                Integer.parseInt(rut.substring(8,9));
            }catch (NumberFormatException e){
                return 0;
            }
        }
        try {
            Integer.parseInt(rut.substring(0,8));
        } catch (NumberFormatException e){
            return 0;
        }

        int validacion = 0;
        try {
            String rutNuevo =  rutSinPuntosNiGuion.toUpperCase();
            int rutAux = Integer.parseInt(rutNuevo.substring(0, rut.length() - 1));

            char dv = rutNuevo.charAt(rutNuevo.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = 1;
            }

        } catch (java.lang.NumberFormatException e){
            return 0;
        } catch (Exception e){
            return 0;
        }
        return 1;
    }
    private String quitarPuntosYGuion(String rut){
        String rutSinPuntosNiGuion;
        rutSinPuntosNiGuion = rut.replace(".", "");
        rutSinPuntosNiGuion = rut.replace("-", "");
        rutSinPuntosNiGuion = rut.replace(" ", "");
        return rutSinPuntosNiGuion;
    }
    private int validarCorreo(String correoElectronico){
        int valido = 0;
        for(int i = 0; i < correoElectronico.length(); i++){
            if(correoElectronico.substring(i).equalsIgnoreCase("@")){
                valido = valido + 1;
            }
        }
        if (valido == 1){
            return 1;
        }else{
            return 0;
        }
    }


    //Agregar Vehiculo
    @FXML
    public void agregarVehiculo(){
        if(txtMarca.getText().isEmpty() || txtModelo.getText().isEmpty() ||
                txtKilometros.getText().isEmpty() || txtPrecio.getText().isEmpty()
                || comboBoxAño.getSelectionModel().isEmpty() || comboBoxColor.getSelectionModel().isEmpty()){
            mensajeErrorBotonAgregarVehiculo();
        }else{
            automotora.agregarVehiculo(new Vehiculo(
                    txtMarca.getText(), txtModelo.getText(), comboBoxAño.getSelectionModel().getSelectedItem(),
                    comboBoxColor.getSelectionModel().getSelectedItem(), txtKilometros.getText(), txtPrecio.getText()));
            mensajeConfirmacionAgregarVehiculo();
        }
    }
    private void mensajeConfirmacionAgregarVehiculo(){
        Alert mensaje = new Alert(Alert.AlertType.NONE);
        mensaje.setTitle("Agregar Vehiculo");
        mensaje.setHeaderText("Registro exitoso");
        mensaje.setContentText("Vehiculo agregado correctamente");
        ButtonType botonOk = new ButtonType("Ok");
        mensaje.getButtonTypes().setAll(botonOk);
        mensaje.show();
    }
    private void mensajeErrorBotonAgregarVehiculo(){
        if(txtMarca.getText().isEmpty() || txtModelo.getText().isEmpty() ||
                txtKilometros.getText().isEmpty() || txtPrecio.getText().isEmpty()
                || comboBoxAño.getSelectionModel().isEmpty() || comboBoxColor.getSelectionModel().isEmpty()){
            Alert mensaje = new Alert(Alert.AlertType.ERROR);
            mensaje.setTitle("Agregar Vehiculo");
            mensaje.setHeaderText("Error");
            mensaje.setContentText("No puede dejar campos vacios");
            ButtonType botonOk = new ButtonType("Ok");
            mensaje.getButtonTypes().setAll(botonOk);
            mensaje.show();
        }
    }


    //Buscar Vehiculo
    @FXML
    public void buscarVehiculo(ActionEvent event) {
        if (txtModeloBuscar.getText().isEmpty() || comboBoxMarca.getSelectionModel().isEmpty()) {
            mensajeBotonBuscarVehiculo();
        } else {
            if (automotora.buscarVehiculos(comboBoxMarca.getSelectionModel().getSelectedItem(), txtModeloBuscar.getText()).size() == 0) {
                mensajeBotonBuscarVehiculo();
            } else {
                panelBuscar.setVisible(false);
                panelTabla.setVisible(true);
                vehiculosEncontrados.addAll(automotora.buscarVehiculos(comboBoxMarca.getSelectionModel().getSelectedItem(), txtModeloBuscar.getText()));
            }
        }
    }
    private void mensajeBotonBuscarVehiculo(){
        if(txtModeloBuscar.getText().isEmpty() || comboBoxMarca.getSelectionModel().isEmpty()){
            Alert mensaje = new Alert(Alert.AlertType.ERROR);
            mensaje.setTitle("Buscar Vehiculo");
            mensaje.setHeaderText("Error");
            mensaje.setContentText("No puede dejar campos vacios");
            ButtonType botonOk = new ButtonType("Ok");
            mensaje.getButtonTypes().setAll(botonOk);
            mensaje.show();
        }
        if(automotora.buscarVehiculos(comboBoxMarca.getSelectionModel().getSelectedItem(), txtModeloBuscar.getText()).size() == 0){
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("Buscar Vehiculo");
            mensaje.setHeaderText("Error");
            mensaje.setContentText("No se ha encontrado vehiculos");
            ButtonType botonOk = new ButtonType("Ok");
            mensaje.getButtonTypes().setAll(botonOk);
            mensaje.show();
        }
    }


    //Metodos que permitiran moverse por el software
    @FXML
    public void ingresarAgregarCliente(){
        panelPrincipal.setVisible(false);
        panelAuto.setVisible(false);
        panelBuscar.setVisible(false);
        panelTabla.setVisible(false);

        panelCliente.setVisible(true);
    }
    @FXML
    public void ingresarAgregarVehiculo(){
        panelPrincipal.setVisible(false);
        panelCliente.setVisible(false);
        panelBuscar.setVisible(false);
        panelTabla.setVisible(false);

        panelAuto.setVisible(true);
    }
    @FXML
    public void ingresarBuscarVehiculo(){
        if(automotora.getVehiculos().size() == 0){
            mensajeErrorBotonBuscar();
        }else{
            comboBoxMarca.setItems(agregarMarcasComboBox());
            panelPrincipal.setVisible(false);
            panelAuto.setVisible(false);
            panelCliente.setVisible(false);
            panelTabla.setVisible(false);

            panelBuscar.setVisible(true);
        }
    }
    private void mensajeErrorBotonBuscar(){
        if(automotora.getVehiculos().size() == 0){
            Alert mensaje = new Alert(Alert.AlertType.ERROR);
            mensaje.setTitle("Buscar Vehiculos");
            mensaje.setHeaderText("Error");
            mensaje.setContentText("No hay vehiculos en la Automotora");
            ButtonType botonOk = new ButtonType("Ok");
            mensaje.getButtonTypes().setAll(botonOk);
            mensaje.show();
        }
    }
    @FXML
    public void volverPrincipal(){
        panelCliente.setVisible(false);
        panelAuto.setVisible(false);
        panelBuscar.setVisible(false);
        panelTabla.setVisible(false);

        panelPrincipal.setVisible(true);
    }
    @FXML
    public void volverBuscar(){
        panelPrincipal.setVisible(false);
        panelCliente.setVisible(false);
        panelAuto.setVisible(false);
        panelTabla.setVisible(false);

        panelBuscar.setVisible(true);
        for(int i = 0; i < vehiculosEncontrados.size(); i++){
            vehiculosEncontrados.remove(i);
        }
        listaAutos.setItems(vehiculosEncontrados);
    }


    //Metodos para limpiar las casiilas de las respectivas pestañas
    @FXML
    public void limpiarAgregarCliente(){
        txtNombre.setText(null);
        txtRut.setText(null);
        txtDireccion.setText(null);
        txtCorreo.setText(null);
        txtTelefono.setText(null);
    }
    @FXML
    public void limpiarAgregarVehiculo(){
        txtMarca.setText(null);
        txtModelo.setText(null);
        txtKilometros.setText(null);
        txtPrecio.setText(null);
        comboBoxAño.getSelectionModel().select(null);
        comboBoxColor.getSelectionModel().select(null);
    }
    @FXML
    public void limiparBusquedaVehiculo(){
        txtModeloBuscar.setText(null);
        comboBoxMarca.getSelectionModel().select(null);
    }


    //Metodos usados para rellenar ComboBox
    private ObservableList<String> agregarMarcasComboBox(){
        ObservableList<String> marcas = FXCollections.observableArrayList();
        ObservableList<Vehiculo> vehiculos = FXCollections.observableArrayList();
        vehiculos = automotora.getVehiculos();

        for(int i = 0; i < vehiculos.size(); i++){
            if(i == 0){
                marcas.add(vehiculos.get(i).getMarca());
            }else{
                for (int j = 0; j < vehiculos.size(); j++){
                    if(!vehiculos.get(j).getMarca().equalsIgnoreCase(vehiculos.get(i).getMarca())){
                        marcas.add(vehiculos.get(i).getMarca());
                    }
                }
            }
        }

        return marcas;
    }
    private ObservableList<String> agregarAñosComboBox(){
        ObservableList<String> años = FXCollections.observableArrayList();
        int año = 2000;
        for(int i = 0; i < 24; i++){
            años.add(Integer.toString(año + i));
        }
        return años;
    }
    private ObservableList<String> agregarColeresComboBox(){
        ObservableList<String> colores = FXCollections.observableArrayList();
        colores.add("Rojo");
        colores.add("Silver");
        colores.add("Azul");
        colores.add("Blanco");
        colores.add("Negro");
        colores.add("Amarillo");
        colores.add("Gris");
        colores.add("Caoba");
        colores.add("Azul Medianoche");
        colores.add("Esmeralda");
        return colores;
    }

}