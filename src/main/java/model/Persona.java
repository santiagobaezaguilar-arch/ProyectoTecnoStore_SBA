package model;

public class Persona {

    private int id;
    private String nombre;
    private String identificacion;
    private String correo;
    private String contraseña;
    private String telefono;

    public Persona() {
    }

    public Persona(String nombre, String identificacion, String correo, String contraseña, String telefono) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.correo = correo;
        this.contraseña = contraseña;
        this.telefono = telefono;
    }

    public Persona(int id, String nombre, String identificacion, String correo, String contraseña, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.correo = correo;
        this.contraseña = contraseña;
        this.telefono = telefono;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getIdentificacion() { return identificacion; }
    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " | Identificación: " + identificacion 
                + " | Correo: " + correo + " | Teléfono: " + telefono;
    }
}