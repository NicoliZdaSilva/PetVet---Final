package unitarios;

import model.Dono;
import model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repository.PetDAO;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TestFiltroDataCT20 {
    private PetDAO petDAO; // Mock da classe PetDAO

    @BeforeEach
    public void setup() {
        // Cria um mock do PetDAO
        petDAO = Mockito.mock(PetDAO.class);
    }

    @Test
    public void testFiltrarPetsPorDataDeNascimento() {
        // Arrange: cria um Dono válido
        Dono dono1 = new Dono("João", 30, "12345678901", "São Paulo", "São Paulo", "11999999999");
        Dono dono2 = new Dono("Maria", 25, "10987654321", "Rio de Janeiro", "Rio de Janeiro", "21988888888");

        // Cria pets e configura o mock
        Pet pet1 = new Pet(LocalDate.of(2020, 1, 1), "Cachorro", "Rex", dono1);
        Pet pet2 = new Pet(LocalDate.of(2019, 6, 15), "Gato", "Mimi", dono2);
        Pet pet3 = new Pet(LocalDate.of(2021, 8, 23), "Pássaro", "Blu", dono1);

        when(petDAO.findAll()).thenReturn(Arrays.asList(pet1, pet2, pet3));

        // Data limite para o filtro
        LocalDate dataLimite = LocalDate.of(2020, 1, 1);

        // Act: realiza o filtro
        List<Pet> resultado = petDAO.findAll().stream()
                .filter(pet -> !pet.getDataNascimento().isBefore(dataLimite))
                .toList();

        // Assert: verifica os resultados
        assertEquals(2, resultado.size());
        assertEquals("Rex", resultado.get(0).getNome());
        assertEquals("Blu", resultado.get(1).getNome());
    }
}
