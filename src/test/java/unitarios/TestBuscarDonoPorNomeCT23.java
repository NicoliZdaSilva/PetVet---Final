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
        assertNotNull(donoEncontrado);
        assertEquals("João Silva", donoEncontrado.getNome());
        assertEquals(30, donoEncontrado.getIdade());
        assertEquals("12345678900", donoEncontrado.getCpf());
        assertEquals("São Paulo", donoEncontrado.getEstado());
        assertEquals("São Paulo", donoEncontrado.getCidade());
        assertEquals("11987654321", donoEncontrado.telefone());
    }
}
