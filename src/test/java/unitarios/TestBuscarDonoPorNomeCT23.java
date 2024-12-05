package unitarios;

import model.Dono;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.DonoDAO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestBuscarDonoPorNomeCT23 {

    private DonoDAO donoDAO;

    @BeforeEach
    public void setUp() {
        donoDAO = new DonoDAO(); // Inicializa o DonoDAO
    }

    @Test
    public void testBuscarDonoPorNome() {
        // Criando e salvando um dono para o teste
        Dono dono = new Dono("João Silva", 30, "12345678900", "São Paulo", "São Paulo", "11987654321");
        donoDAO.salvarDono(dono);

        // Buscando todos os donos
        Dono donoEncontrado = null;
        for (Dono d : donoDAO.findAll()) {
            if (d.getNome().equals("João Silva")) {
                donoEncontrado = d;
                break;
            }
        }

        // Verificando se o dono foi encontrado
        assertNotNull(donoEncontrado, "O dono deve ser encontrado pelo nome.");
        assertEquals(dono.getNome(), donoEncontrado.getNome(), "O nome do dono deve ser igual ao salvo.");
        assertEquals(dono.getEstado(), donoEncontrado.getEstado(), "O estado do dono deve ser igual ao salvo.");
        assertEquals(dono.getCidade(), donoEncontrado.getCidade(), "A cidade do dono deve ser igual ao salvo.");
        assertEquals(dono.getTelefone(), donoEncontrado.getTelefone(), "O telefone do dono deve ser igual ao salvo.");
    }
}
