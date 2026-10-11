package model;

public class Celular {

    private int id;
    private String modelo;
    private int idMarca;
    private int idSistemaOperativo;
    private int idGama;
    private double precio;
    private int stock;

    public Celular() {
    }

    public Celular(String modelo, int idMarca, int idSistemaOperativo, int idGama, double precio, int stock) {
        this.modelo = modelo;
        this.idMarca = idMarca;
        this.idSistemaOperativo = idSistemaOperativo;
        this.idGama = idGama;
        this.precio = precio;
        this.stock = stock;
    }

    public Celular(int id, String modelo, int idMarca, int idSistemaOperativo, int idGama, double precio, int stock) {
        this.id = id;
        this.modelo = modelo;
        this.idMarca = idMarca;
        this.idSistemaOperativo = idSistemaOperativo;
        this.idGama = idGama;
        this.precio = precio;
        this.stock = stock;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public int getIdMarca() { return idMarca; }
    public void setIdMarca(int idMarca) { this.idMarca = idMarca; }

    public int getIdSistemaOperativo() { return idSistemaOperativo; }
    public void setIdSistemaOperativo(int idSistemaOperativo) { this.idSistemaOperativo = idSistemaOperativo; }

    public int getIdGama() { return idGama; }
    public void setIdGama(int idGama) { this.idGama = idGama; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return "ID: " + id + " | Modelo: " + modelo + " | Precio: $" + precio + " | Stock: " + stock 
                + " [Marca ID: " + idMarca + ", SO ID: " + idSistemaOperativo + ", Gama ID: " + idGama + "]";
    }
}
