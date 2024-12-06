package integracao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import model.Consulta;
import repository.ConsultaDAO;

public class TestSalvarConsultaComFormatoInvalido_CT50 {

    private ConsultaDAO consultaDAO;

    @BeforeEach
    public void setUp() {
        consultaDAO = new ConsultaDAO(); // Certifique-se de que o DAO está sendo instanciado corretamente
    }

    @Test
    @Order(2)
    void testSalvarConsultaComFormatoInvalido_CT50() {
        // Criando uma consulta com status válido
        Consulta consulta = new Consulta();
        consulta.setStatus("Pendente");

        // Passando uma data inválida (em formato String)
        String dataInvalida = "2024-13-40 10:10";  // Exemplo de data com formato inválido

        Exception exception = assertThrows(DateTimeParseException.class, () -> {
            // Tentando setar a data inválida
            consulta.setDataHora(LocalDateTime.parse(dataInvalida));  // Tentativa de conversão para LocalDateTime
            consultaDAO.salvarConsulta(consulta); // Tentativa de salvar a consulta
        });

        // Verificando se a exceção foi corretamente lançada
        assertTrue(exception.getMessage().contains("Text '2024-13-40 10:10' could not be parsed"));
    }
}
