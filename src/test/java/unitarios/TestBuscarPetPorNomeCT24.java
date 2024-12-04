import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;  // Importação correta
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestBuscarPetPorNomeCT24 {

    private EntityManagerFactory emf;
    private EntityManager em;

    @BeforeEach
    public void setUp() {
        // Certifique-se de usar o nome correto do persistence-unit
        emf = Persistence.createEntityManagerFactory("h2"); // Corrigido para "h2"
        em = emf.createEntityManager();
    }

    @Test
    public void testBuscarPetPorNome() {
        // Teste que você deseja executar
        // Adicione a lógica do teste aqui
    }

    // Corrigido para @AfterEach para encerrar os recursos após cada teste
    @AfterEach
    public void tearDown() {
        if (em != null && em.isOpen()) {
            em.close();
        }
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
