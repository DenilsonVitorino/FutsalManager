package br.com.futsalmanager.model;

public class Horario {
    private Long id;
    private String mes;
    private String diasemana;
    private String hora;

    public Horario() {
    }

    public Horario(String mes, String diasemana, String hora) {
        this.mes = mes;
        this.diasemana = diasemana;
        this.hora = hora;
    }

    public Horario(Long id, String mes, String diasemana, String hora) {
        this.id = id;
        this.mes = mes;
        this.diasemana = diasemana;
        this.hora = hora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getDiasemana() {
        return diasemana;
    }

    public void setDiasemana(String diasemana) {
        this.diasemana = diasemana;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
