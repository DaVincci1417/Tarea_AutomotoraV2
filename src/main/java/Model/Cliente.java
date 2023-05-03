package Model;

public class Cliente {
    private String nombre;
    private String rut;
    private String direccion;
    private String numeroTelefonico;
    private String correoElectronico;

    public Cliente(String nombre, String rut, String direccion,
                   String numeroTelefonico, String correoElectronico){
        setNombre(nombre);
        setRut(rut);
        setDireccion(direccion);
        setNumeroTelefonico(numeroTelefonico);
        setCorreoElectronico(correoElectronico);
    }

    //Getters and Setters
    public String getNombre() {
        return nombre;
    }
    private void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getRut() {
        return rut;
    }
    private void setRut(String rut) {
        this.rut = rut;
    }
    public String getDireccion() {
        return direccion;
    }
    private void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }
    private void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }
    public String getCorreoElectronico() {
        return correoElectronico;
    }
    private void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

}
