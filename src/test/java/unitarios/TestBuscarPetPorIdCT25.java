package unitarios;

import model.Dono;
import model.Pet;
import org.junit.jupiter.api.*;
import repository.PetDAO;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestBuscarPetPorIdCT25 {

    private PetDAO petDAO;
    private Pet petCadastrado;

    @BeforeAll
    void setup() {
        // Criação do mock do PetDAO
        petDAO = mock(PetDAO.class);

        // Criar um dono com os atributos necessários
        Dono dono = new Dono("João Silva", 35, "12345678900", "São Paulo", "São Paulo", "11987654321");

        // Criar e salvar um pet associado ao dono
        petCadastrado = new Pet(LocalDate.of(2020, 5, 10), "Cachorro", "Rex", dono);

        // Simula o comportamento do método findById do PetDAO
        when(petDAO.findById(petCadastrado.getId())).thenReturn(petCadastrado);
    }

    @Test
    @DisplayName("CT25 - Buscar pelo ID do pet")
    void testBuscarPetPorId() {
        // Buscar o pet pelo ID usando o mock
        Pet petEncontrado = petDAO.findById(petCadastrado.getId());

        // Verificar se o pet foi encontrado
        assertNotNull(petEncontrado, "O pet deveria ter sido encontrado.");
    }

    @AfterAll
    void tearDown() {
        // Aqui você poderia limpar recursos se necessário
    }
}
