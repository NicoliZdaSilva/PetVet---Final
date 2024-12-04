package model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "nome")
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dono_cpf", nullable = false)
    private Dono dono;

    // Construtor padrão necessário para JPA
    public Pet() {}

    public Pet(LocalDate dataNascimento, String tipo, String nome, Dono dono) {
        if (dataNascimento == null) {
            throw new IllegalArgumentException("A data de nascimento do pet não pode ser nula.");
        }
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("O tipo do pet não pode ser nulo ou vazio.");
        }
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do pet não pode ser nulo ou vazio.");
        }
        if (dono == null) {
            throw new IllegalArgumentException("O dono do pet não pode ser nulo.");
        }
        this.dataNascimento = dataNascimento;
        this.tipo = tipo.trim();
        this.nome = nome.trim();
        this.dono = dono;
    }

    public long getId() {
        return id;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        if (dataNascimento == null) {
            throw new IllegalArgumentException("A data de nascimento não pode ser nula.");
        }
        this.dataNascimento = dataNascimento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("O tipo do pet não pode ser nulo ou vazio.");
        }
        this.tipo = tipo.trim();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do pet não pode ser nulo ou vazio.");
        }
        this.nome = nome.trim();
    }

    public Dono getDono() {
        return dono;
    }

    public void setDono(Dono dono) {
        if (dono == null) {
            throw new IllegalArgumentException("O dono do pet não pode ser nulo.");
        }
        this.dono = dono;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", dataNascimento=" + dataNascimento +
                ", tipo='" + tipo + '\'' +
                ", nome='" + nome + '\'' +
                ", dono=" + (dono != null ? dono.getNome() : "Sem dono") +
                '}';
    }
}
