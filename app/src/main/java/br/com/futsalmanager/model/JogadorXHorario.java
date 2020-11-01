package br.com.futsalmanager.model;

public class JogadorXHorario {
    private Long id;
    private Long idHorario;
    private Long idJogador;
    private String nomeJogador;
    private Integer camiseta;

    public JogadorXHorario() {
    }

    public JogadorXHorario(Long id, Long idHorario, Long idJogador, String nomeJogador, Integer camiseta) {
        this.id = id;
        this.idHorario = idHorario;
        this.idJogador = idJogador;
        this.nomeJogador = nomeJogador;
        this.camiseta = camiseta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Long idHorario) {
        this.idHorario = idHorario;
    }

    public Long getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(Long idJogador) {
        this.idJogador = idJogador;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public Integer getCamiseta() {
        return camiseta;
    }

    public void setCamiseta(Integer camiseta) {
        this.camiseta = camiseta;
    }
}
