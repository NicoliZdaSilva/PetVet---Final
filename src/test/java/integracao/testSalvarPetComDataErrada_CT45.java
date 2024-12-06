package integracao;  // Ajuste o pacote conforme sua estrutura de diretórios

import model.Dono;
import model.Pet;
import org.junit.jupiter.api.*;
import repository.PetDAO;
import repository.DonoDAO;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class testSalvarPetComDataErrada_CT45 {

    private PetDAO petDAO;
    private DonoDAO donoDAO;

    @BeforeAll
    void setup() {
        // Inicializando os DAOs para uso nos testes
        petDAO = new PetDAO();
        donoDAO = new DonoDAO();
    }

    @Test
    @Order(2)
    void testSalvarPetComDataErrada_CT45() {
        // Buscando um dono no banco
        Dono dono = donoDAO.findByCpf("12345678901");
        assertNotNull(dono);

        // Criando um pet com dados errados
        Pet pet = new Pet();
        pet.setNome("Tigrão");
        pet.setTipo("Gato");
        pet.setDono(dono);

        // Simulando a validação de dados no método salvarPet
        Exception exception = assertThrows(RuntimeException.class, () -> {
            petDAO.salvarPet(pet); // Ajustar para adicionar um atributo inválido
        });

        // Verificando se a exceção contém a mensagem esperada
        assertTrue(exception.getMessage().contains("Data inválida"));
    }
}
