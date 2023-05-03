package Model;

public class Vehiculo{
    private String marca;
    private String modelo;
    private String año;
    private String color;
    private String kilometrosRecorridos;
    private String precio;

    public Vehiculo(String marca, String modelo, String año, String color,
                    String kilometrosRecorridos, String precio) {
        setMarca(marca);
        setModelo(modelo);
        setAño(año);
        setColor(color);
        setKilometrosRecorridos(kilometrosRecorridos);
        setPrecio(precio);
    }

    public String getMarca() {
        return marca;
    }
    private void setMarca(String marca) {
        this.marca = marca;
    }
    public String getModelo() {
        return modelo;
    }
    private void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getAño() {
        return año;
    }
    private void setAño(String año) {
        this.año = año;
    }
    public String getColor() {
        return color;
    }
    private void setColor(String color) {
        this.color = color;
    }
    public String getKilometrosRecorridos() {
        return kilometrosRecorridos;
    }
    private void setKilometrosRecorridos(String kilometrosRecorridos) {
        this.kilometrosRecorridos = kilometrosRecorridos;
    }
    public String getPrecio() {
        return precio;
    }
    private void setPrecio(String precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return  "Marca: " + marca + "\n" +
                "Modelo: " +modelo + "\n" +
                "Año: " + año + "\n" +
                "Color: " + color + "\n" +
                "Km: " + kilometrosRecorridos + "\n" +
                "Precio: " +precio + "\n";
    }
}
