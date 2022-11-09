package com.example.lab8gtics.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "reserva", schema = "mydb", catalog = "")
public class Reserva {


    @Column(name = "pelicula_id")
    private int peliculaId;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "inicio")
    private Time horaInicio;

    @Column(name = "fin")
    private Time horaFin;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "numero")
    private int numero;

    public int getPeliculaId() {
        return peliculaId;
    }

    public void setPeliculaId(int peliculaId) {
        this.peliculaId = peliculaId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getInicio() {
        return horaInicio;
    }

    public void setInicio(Time inicio) {
        this.horaInicio = inicio;
    }

    public Time getFin() {
        return horaFin;
    }

    public void setFin(Time fin) {
        this.horaFin = fin;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

}
