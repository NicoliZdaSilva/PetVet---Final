package integracao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.ConsultaDAO;
import model.Consulta;
import javax.persistence.EntityManager;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestCancelarConsulta_CT51 {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private ConsultaDAO consultaDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCancelarConsulta() {
        // Mocking a consulta para ser retornada
        Long consultaId = 1L;
        Consulta consultaMock = mock(Consulta.class);
        when(entityManager.find(Consulta.class, consultaId)).thenReturn(consultaMock);

        // Verifique o comportamento aqui usando o método correto
        Consulta consulta = consultaDAO.findById(consultaId); // ou use o método correto
        assertNotNull(consulta);  // Exemplo de verificação
    }
}
