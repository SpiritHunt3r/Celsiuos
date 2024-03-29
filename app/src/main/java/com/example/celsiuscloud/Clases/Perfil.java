package com.example.celsiuscloud.Clases;


import java.util.UUID;

public class Perfil {
    private String id;
    private String nombre;
    private String apellidos;
    private String fecha;
    private String sexo;
    private int estatura;
    private int peso;
    private int avatar;

    public Perfil() {
    }

    public Perfil(String nombre, String apellidos, String fecha, String sexo, int estatura, int peso) {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        this.id = randomUUIDString;
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

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Perfil" + '\n' +
                "nombre = " + nombre + " " + apellidos + '\n' +
                "fecha de nacimiento= " + fecha + '\n' +
                "sexo= " + sexo + '\n' +
                "estatura= " + estatura + '\n' +
                "peso= " + peso + '\n' + '\n' +
                "****Actividades****\n\n\n";
    }
}
