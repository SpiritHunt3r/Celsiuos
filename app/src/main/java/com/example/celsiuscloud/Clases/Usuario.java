package com.example.celsiuscloud.Clases;

public class Usuario {
    private String Nombre;
    private String Apellidos;
    private int Avatar;

    public Usuario() {
    }

    public Usuario(String nombre, String apellidos) {
        Nombre = nombre;
        Apellidos = apellidos;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public int getAvatar() {
        return Avatar;
    }

    public void setAvatar(int avatar) {
        Avatar = avatar;
    }
}
