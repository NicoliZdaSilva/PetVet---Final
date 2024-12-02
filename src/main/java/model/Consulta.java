package model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "consultas")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(nullable = false)
    private String motivo;

    @ManyToOne
    @JoinColumn(name = "veterinario_cpf", nullable = false)
    private Veterinario veterinario;

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    @Column(nullable = false)
    private double valor;

    @Column(nullable = false)
    private String status;

    @Column
    private String comentario;

    public Consulta() {}

    public Consulta(LocalDateTime dataHora, String motivo, Veterinario veterinario, Pet pet, double valor, String status) {
        if (dataHora == null) {
            throw new IllegalArgumentException("A data e hora da consulta são obrigatórias.");
        }
        if (motivo == null || motivo.trim().isEmpty()) {
            throw new IllegalArgumentException("O motivo da consulta não pode ser vazio.");
        }
        if (veterinario == null) {
            throw new IllegalArgumentException("É necessário informar o veterinário responsável.");
        }
        if (pet == null) {
            throw new IllegalArgumentException("É necessário informar o pet para a consulta.");
        }
        if (valor < 0) {
            throw new IllegalArgumentException("O valor da consulta não pode ser negativo.");
        }
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("O status da consulta é obrigatório.");
        }

        this.dataHora = dataHora;
        this.motivo = motivo;
        this.veterinario = veterinario;
        this.pet = pet;
        this.valor = valor;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        if (dataHora == null) {
            throw new IllegalArgumentException("A data e hora da consulta são obrigatórias.");
        }
        this.dataHora = dataHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        if (motivo == null || motivo.trim().isEmpty()) {
            throw new IllegalArgumentException("O motivo da consulta não pode ser vazio.");
        }
        this.motivo = motivo;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        if (veterinario == null) {
            throw new IllegalArgumentException("É necessário informar o veterinário responsável.");
        }
        this.veterinario = veterinario;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        if (pet == null) {
            throw new IllegalArgumentException("É necessário informar o pet para a consulta.");
        }
        this.pet = pet;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("O valor da consulta não pode ser negativo.");
        }
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("O status não pode ser vazio.");
        }
        this.status = status;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "id=" + id +
                ", dataHora=" + dataHora +
                ", motivo='" + motivo + '\'' +
                ", veterinario=" + (veterinario != null ? veterinario.getNome() : "null") +
                ", pet=" + (pet != null ? pet.getNome() : "null") +
                ", valor=" + valor +
                ", status='" + status + '\'' +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
