package model;

import jakarta.persistence.*;

@Entity
public class Dono extends Pessoa {
    @Column(nullable = false)
    private String estado;
    @Column(nullable = false)
    private String cidade;
    @Column(nullable = false)
    private String telefone;

    public Dono(String nome, int idade, String cpf, String estado, String cidade, String telefone) {
        super(nome, idade, cpf);

        // Validação do telefone
        if (telefone == null || telefone.isEmpty()) {
            throw new IllegalArgumentException("O número de telefone não pode estar vazio.");
        }
        if (telefone.length() != 11) {
            throw new IllegalArgumentException("O telefone precisa ter 11 dígitos, incluindo o DDD.");
        }
        this.telefone = telefone;

        // Validação do estado
        if (estado == null || estado.isEmpty()) {
            throw new IllegalArgumentException("O estado deve ser informado.");
        }
        this.estado = estado;

        // Validação da cidade
        if (cidade == null || cidade.isEmpty()) {
            throw new IllegalArgumentException("A cidade deve ser informada.");
        }
        this.cidade = cidade;
    }

    public Dono (){

    }
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (estado == null || estado.isEmpty()) {
            throw new IllegalArgumentException("É necessário informar o estado.");
        }
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        if (cidade == null || cidade.isEmpty()) {
            throw new IllegalArgumentException("É necessário informar a cidade.");
        }
        this.cidade = cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if (telefone == null || telefone.isEmpty()) {
            throw new IllegalArgumentException("O número de telefone não pode estar vazio.");
        }
        if (telefone.length() != 11) {
            throw new IllegalArgumentException("O telefone precisa ter 11 dígitos, incluindo o DDD.");
        }
        this.telefone = telefone;
    }
}
