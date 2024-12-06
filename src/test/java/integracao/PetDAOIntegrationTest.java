import model.Dono;
import model.Pet;
import org.junit.jupiter.api.*;
import repository.DonoDAO;
import repository.PetDAO;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PetDAOIntegrationTest {

    private PetDAO petDAO;
    private DonoDAO donoDAO;

    @BeforeAll
    void setup() {
        petDAO = new PetDAO();
        donoDAO = new DonoDAO();
    }

    @Test
    @Order(1)
    void testSalvarPetComTodosDados_CT44() {
        Dono dono = new Dono();
        dono.setCpf("12345678901");
        dono.setNome("Carlos Silva");
        dono.setTelefone("11999999999");
        dono.setIdade(35);
        dono.setCidade("São Paulo");
        dono.setEstado("SP");

        donoDAO.salvarDono(dono);

        Pet pet = new Pet();
        pet.setNome("Rex");
        pet.setTipo("Cachorro");
        pet.setDono(dono);

        petDAO.salvarPet(pet);

        Pet petSalvo = petDAO.findByName("Rex");
        assertNotNull(petSalvo);
        assertEquals("Rex", petSalvo.getNome());
        assertEquals("Cachorro", petSalvo.getTipo());
        assertEquals(dono.getCpf(), petSalvo.getDono().getCpf());
    }
}
