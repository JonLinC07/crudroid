package com.example.ojmlc.crudroid;

public class Planeta {

    int id, diametro;
    String nombre, tipo, fecha;

    public Planeta() {

    }

    public Planeta(String nombre, String tipo, String fecha, int diametro) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.fecha = fecha;
        this.diametro = diametro;
    }

    public Planeta(int id, String nombre, String tipo, String fecha, int diametro) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.fecha = fecha;
        this.diametro = diametro;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDiametro(int diametro) {
        this.diametro = diametro;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public int getDiametro() {
        return diametro;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getFecha() {
        return fecha;
    }
}
