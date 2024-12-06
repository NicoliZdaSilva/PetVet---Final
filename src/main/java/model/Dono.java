package model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dono")
public class Dono extends Pessoa {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "dono", orphanRemoval = true)
    private Set<Pet> pets = new HashSet<>();

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String telefone;

    // Construtor com parâmetros
    public Dono(String nome, int idade, String cpf, String estado, String cidade, String telefone) {
        super(nome, idade, cpf);

        if (estado == null || estado.isEmpty()) {
            throw new IllegalArgumentException("O estado deve ser informado.");
        }
        this.estado = estado;

        if (cidade == null || cidade.isEmpty()) {
            throw new IllegalArgumentException("A cidade deve ser informada.");
        }
        this.cidade = cidade;

        if (telefone == null || telefone.isEmpty()) {
            throw new IllegalArgumentException("O número de telefone não pode estar vazio.");
        }
        if (telefone.length() != 11) {
            throw new IllegalArgumentException("O telefone precisa ter 11 dígitos, incluindo o DDD.");
        }
        this.telefone = telefone;
    }

    // Construtor vazio necessário para JPA
    public Dono() {}

    // Getters e Setters
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
            throw new IllegalArgumentException("É necessário informar o telefone.");
        }
        if (telefone.length() != 11) {
            throw new IllegalArgumentException("O telefone informado precisa ter 11 dígitos, incluindo o DDD.");
        }
        this.telefone = telefone;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        if (pets == null) {
            throw new IllegalArgumentException("O conjunto de pets não pode ser nulo.");
        }
        this.pets = pets;

        for (Pet pet : pets) {
            pet.setDono(this);
        }
    }

    // Métodos para adicionar e remover Pets
    public void addPet(Pet pet) {
        if (pet == null) {
            throw new IllegalArgumentException("Pet não pode ser nulo.");
        }
        if (this.pets.add(pet)) { // Se for um pet novo, adiciona e ajusta o dono
            pet.setDono(this);
        } else {
            throw new IllegalArgumentException("O pet já está associado a este dono.");
        }
    }

    public boolean removePet(Pet pet) {
        if (pet == null) {
            throw new IllegalArgumentException("Pet não pode ser nulo.");
        }
        if (this.pets.remove(pet)) { // Se o pet estava associado, remove e ajusta o dono
            pet.setDono(null);
            return true;
        }
        return false; // Indica que o pet não estava associado
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nEstado: " + this.estado +
                "\nCidade: " + this.cidade +
                "\nTelefone: " + this.telefone +
                "\nPets: " + (pets.isEmpty() ? "Nenhum pet cadastrado." : pets);
    }
}
