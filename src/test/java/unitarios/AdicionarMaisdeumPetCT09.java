package unitarios;

import model.Dono;
import model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AdicionarMaisdeumPetCT09 {

    private Dono dono;

    @BeforeEach
    public void setup() {
        dono = new Dono("Carlos Silva", 30, "12345678900", "SP", "São Paulo", "11987654321");
    }

    @Test
    public void adicionarMaisDe1PetAoMesmoDono() {
        Pet pet1 = new Pet(LocalDate.of(2022, 5, 10), "Cachorro", "Rex", dono);
        Pet pet2 = new Pet(LocalDate.of(2023, 6, 15), "Gato", "Mia", dono);

        assertNotNull(pet1.getDono(), "O pet 1 deve ter um dono associado.");
        assertNotNull(pet2.getDono(), "O pet 2 deve ter um dono associado.");
        assertEquals(dono, pet1.getDono(), "O dono do pet 1 deve ser o mesmo.");
        assertEquals(dono, pet2.getDono(), "O dono do pet 2 deve ser o mesmo.");

    }
}
