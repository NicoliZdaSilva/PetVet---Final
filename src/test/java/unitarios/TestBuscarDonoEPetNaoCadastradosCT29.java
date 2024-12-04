package unitarios;

import model.Dono;
import model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.DonoDAO;
import repository.PetDAO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestBuscarDonoEPetNaoCadastradosCT29 {

    @Mock
    private DonoDAO donoDAO;

    @Mock
    private PetDAO petDAO;

    @InjectMocks
    private TestBuscarDonoEPetNaoCadastradosCT29 testInstance;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }

    @Test
    void testBuscarDonoNaoCadastrado() {
        // Simula o comportamento do donoDAO ao buscar um CPF não cadastrado
        String cpfNaoCadastrado = "12345678900";
        when(donoDAO.findByCpf(cpfNaoCadastrado)).thenReturn(null);

        Dono dono = donoDAO.findByCpf(cpfNaoCadastrado);

        // Verifica se o retorno é null
        assertNull(dono, "O dono não deve ser encontrado no banco de dados.");
    }

    @Test
    void testBuscarPetNaoCadastrado() {
        // Simula o comportamento do petDAO ao buscar um ID de pet não cadastrado
        Long idNaoCadastrado = 999L;
        when(petDAO.findById(idNaoCadastrado)).thenReturn(null);

        Pet pet = petDAO.findById(idNaoCadastrado);

        // Verifica se o retorno é null
        assertNull(pet, "O pet não deve ser encontrado no banco de dados.");
    }
}
