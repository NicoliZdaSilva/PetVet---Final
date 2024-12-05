package unitarios;

import model.Dono;
import model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAtualizarPetCT11 {

    private Pet pet;
    private Dono dono;

    @BeforeEach
    void setUp() {
        // Criando um dono válido
        dono = new Dono("João Silva", 35, "12345678901", "São Paulo", "São Paulo", "11999999999");

        // Criando um pet válido associado ao dono
        pet = new Pet(LocalDate.of(2020, 1, 1), "Cachorro", "Rex", dono);
    }

    @Test
    void atualizarNomeDoPet() {
        String novoNome = "Max";

        // Atualizando o nome do pet
        pet.setNome(novoNome);

        // Verificando que o nome foi atualizado
        assertEquals(novoNome, pet.getNome(), "O nome do pet deve ser atualizado corretamente.");

        // Verificando que o tipo do pet não foi alterado
        assertEquals("Cachorro", pet.getTipo(), "O tipo do pet não deve ser alterado.");
    }
}
