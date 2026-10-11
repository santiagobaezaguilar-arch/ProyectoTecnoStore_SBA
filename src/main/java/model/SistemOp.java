package model;

public class SistemOp {

    private int id;
    private String nombre;

    public SistemOp() {
    }

    public SistemOp(String nombre) {
        this.nombre = nombre;
    }

    public SistemOp(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Sistema Operativo: " + nombre;
    }
}
