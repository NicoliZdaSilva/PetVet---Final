package unitarios;

import model.Dono;
import model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTrocarTipoAnimalCT12 {

    private Pet pet;
    private Dono dono;

    @BeforeEach
    void setUp() {
        // Criando um dono válido
        dono = new Dono("Maria Oliveira", 40, "98765432100", "Rio de Janeiro", "Rio de Janeiro", "21988888888");

        // Criando um pet válido associado ao dono
        pet = new Pet(LocalDate.of(2019, 6, 15), "Gato", "Mimi", dono);
    }

    @Test
    void trocarTipoDoAnimal() {
        String tipoOriginal = pet.getTipo();
        String novoTipo = "Coelho";

        // Alterando o tipo do pet
        pet.setTipo(novoTipo);

        // Verificando que o tipo foi atualizado
        assertEquals(novoTipo, pet.getTipo(), "O tipo do pet deve ser atualizado corretamente.");

        // Verificando que o nome do pet permanece inalterado
        assertEquals("Mimi", pet.getNome(), "O nome do pet não deve ser alterado.");

        // Verificando que o tipo original não é mais o mesmo
        assertEquals("Gato", tipoOriginal, "O tipo original deve ser preservado para comparação.");
    }
}
