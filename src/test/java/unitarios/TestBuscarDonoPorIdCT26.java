package unitarios;

import model.Dono;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.DonoDAO;

import static org.junit.jupiter.api.Assertions.*;

public class TestBuscarDonoPorIdCT26 {

    private DonoDAO donoDAO;
    private Dono dono;

    @BeforeEach
    public void setup() {
        // Configuração inicial
        donoDAO = new DonoDAO();

        // Criando um dono para o teste
        dono = new Dono("Ana Souza", 35, "98765432100", "RJ", "Rio de Janeiro", "21987654321");

        // Salvando o dono no banco de dados
        donoDAO.salvarDono(dono);
    }

    @Test
    public void buscarDonoPorCpf() {
        // Buscando o dono pelo CPF
        Dono donoEncontrado = donoDAO.findByCpf("98765432100");

        // Verificando se o dono foi encontrado corretamente
        assertNotNull(donoEncontrado, "O dono deve ser encontrado pelo CPF.");
        assertEquals(dono.getNome(), donoEncontrado.getNome(), "O nome do dono deve ser igual ao salvo.");
        assertEquals(dono.getEstado(), donoEncontrado.getEstado(), "O estado do dono deve ser igual ao salvo.");
        assertEquals(dono.getCidade(), donoEncontrado.getCidade(), "A cidade do dono deve ser igual ao salvo.");
       // assertEquals(dono.getTelefone(), donoEncontrado.getTelefone(), "O telefone do dono deve ser igual ao salvo.");
    }

    @Test
    public void buscarDonoInexistentePorCpf() {
        // Tentando buscar um CPF que não existe
        Dono donoNaoEncontrado = donoDAO.findByCpf("11122233344");

        // Verificando se nenhum dono foi encontrado
        assertNull(donoNaoEncontrado, "Nenhum dono deve ser encontrado para um CPF inexistente.");
    }
}
