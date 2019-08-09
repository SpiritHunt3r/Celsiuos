package com.example.celsiuscloud.Clases;

public class Actividad {
    private String titulo;
    private String tipo;
    private String descripcion;
    private String fecha;
    private String hora;
    private String foto;
    private String sintoma;

    public Actividad() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getSintoma() {
        return sintoma;
    }

    public void setSintoma(String sintoma) {
        this.sintoma = sintoma;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        if (tipo.equals("Nota")){
            return "Nota: " +
                    titulo + '\n' +
                    "Descripción= " + descripcion + '\n' +
                    "Fecha= " + fecha + '\n' +
                    "Hora= " + hora + '\n'+'\n';
        }
        else if (tipo.equals("Sintoma")){
            return "Sintoma: " +
                    titulo + '\n' +
                    "Descripción= " + descripcion + '\n' +
                    "Fecha= " + fecha + '\n' +
                    "Hora= " + hora + '\n' +'\n' ;
        }
        else if (tipo.equals("Foto")){
            return "Foto: " +
                    titulo + '\n' +
                    "Link= " + foto + '\n' +
                    "Fecha= " + fecha + '\n' +
                    "Hora= " + hora + '\n' +'\n';
        }
        else if (tipo.equals("Temperatura")){
            return "Temperatura: " +
                    titulo +" ℃ - " +  sintoma + '\n'+
                    "Descripción= " + descripcion + '\n' +
                    "Fecha= " + fecha + '\n' +
                    "Hora= " + hora + '\n' + '\n';
        }
        else{
            return "Actividad: " +
                    "Título= " + titulo + '\n' +
                    ", Descripción='" + descripcion + '\n' +
                    ", fecha='" + fecha + '\n' +
                    ", hora='" + hora + '\n' +
                    ", foto='" + foto + '\n' +
                    ", sintoma= " + sintoma + '\n' +
                    '}';
        }



    }
}
