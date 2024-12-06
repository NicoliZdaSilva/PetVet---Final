import model.Consulta;
import org.junit.jupiter.api.*;
import repository.ConsultaDAO;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ConsultaDAOIntegrationTest {

    private ConsultaDAO consultaDAO;

    @BeforeAll
    void setup() {
        consultaDAO = new ConsultaDAO();
    }

    @Test
    @Order(1)
    void testCriarConsultaSemDados_CT49() {
        Consulta consulta = new Consulta();
        consulta.setStatus(null); // Status obrigatório não preenchido
        consulta.setDataHora(LocalDateTime.now());

        Exception exception = assertThrows(RuntimeException.class, () -> consultaDAO.salvarConsulta(consulta));
        assertTrue(exception.getMessage().contains("não pode ser nulo"));
    }
}
