package com.example.celsiuscloud.Clases;


public class Perfil {
    private String nombre;
    private String apellidos;
    private String fecha;
    private String sexo;
    private int estatura;
    private int peso;

    public Perfil() {
    }

    public Perfil(String nombre, String apellidos, String fecha, String sexo, int estatura, int peso) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fecha = fecha;
        this.sexo = sexo;
        this.estatura = estatura;
        this.peso = peso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getEstatura() {
        return estatura;
    }

    public void setEstatura(int estatura) {
        this.estatura = estatura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

}
