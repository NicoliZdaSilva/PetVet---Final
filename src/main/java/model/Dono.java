package model;

import repository.EspecialidadeDAO;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import repository.DonoDAO;
import model.Pessoa;
import model.Pet;

@Entity
@Table(name = "dono")
public class Dono extends Pessoa{

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "pet_dono",
            joinColumns = @JoinColumn (name = "dono_cpf"),
            inverseJoinColumns = @JoinColumn (name = "pet_id")
    )
    @Column (nullable = false)
    private String estado;

    @Column (nullable = false)
    private String cidade;

    @Column (nullable = false)
    private String telefone;

    public Dono(String nome, int idade, String cpf, String estado, String cidade, String telefone){
        super(nome, idade, cpf);
        this.estado = estado;
        this.cidade = cidade;
        this.telefone= telefone;
    }
    public Dono(){

    }
    public void setEstado(String estado){
        if (estado == null || nome.isEmpty()){
            throw new IllegalArgumentException("É necessário informar o estado");
        }
        this.estado = estado;
    }
    public String getEstado(){
        return this.estado;
    }
    public void setCidade(String cidade){
        if (cidade == null || cidade.isEmpty()){
            throw new IllegalArgumentException("É necessário informar a cidade");
        }
        this.estado = estado;
    }
    public String getCidade(){
        return this.cidade;
    }
    public void setTelefone(String telefone){
        if (telefone == null || telefone.isEmpty()){
            throw new IllegalArgumentException("É necessário informar o telefone");
        }
        if (telefone.length() != 11){
            throw new IllegalArgumentException("O telefone informado precisa ter 11 dígitos incluindo DDD9");
        }
        this.telefone = telefone;
    }
    public String telefone(){
        return this.telefone;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nEstado : " +this.estado +
                "\nCidade : " +this.cidade +
                "\nTelefone : " + this.telefone;
    }
}


