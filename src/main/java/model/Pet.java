package model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    private LocalDate dataNascimento;
    private String tipo;
    private String nome;
    
    public long getId(){
        return 0;
    }

    public Pet() {

    }
}

