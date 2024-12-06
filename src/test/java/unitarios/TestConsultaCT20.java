package unitarios;

import model.Consulta;
import model.Pet;
import model.Veterinario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repository.ConsultaDAO;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestConsultaCT20 {

    private Veterinario mockVeterinario;
    private Pet mockPet;
    private Consulta consulta;

    @BeforeEach
    void setUp() {
        // Criar mocks para Veterinário e Pet
        mockVeterinario = Mockito.mock(Veterinario.class);
        Mockito.when(mockVeterinario.getNome()).thenReturn("Dr. João Veterinário");

        mockPet = Mockito.mock(Pet.class);
        Mockito.when(mockPet.getNome()).thenReturn("Rex");

        // Criar a consulta com os mocks
        consulta = new Consulta(
                LocalDateTime.of(2024, 12, 5, 10, 30),
                "Consulta de rotina",
                mockVeterinario,
                mockPet,
                150.0,
                "Confirmada"
        );
    }

    @Test
    void testVisualizarConsulta() {
        // Testa as propriedades
        assertNotNull(consulta.getDataHora(), "A data e hora estão incorretas.");
        assertEquals("Consulta de rotina", consulta.getMotivo(), "O motivo está incorreto.");
        assertEquals(mockVeterinario, consulta.getVeterinario(), "O veterinário não foi atribuído corretamente.");
        assertEquals("Dr. João Veterinário", consulta.getVeterinario().getNome(), "O nome do veterinário está incorreto.");
        assertEquals(mockPet, consulta.getPet(), "O pet não foi atribuído corretamente.");
        assertEquals("Rex", consulta.getPet().getNome(), "O nome do pet está incorreto.");
        assertEquals(150.0, consulta.getValor(), "O valor da consulta está incorreto.");
        assertEquals("Confirmada", consulta.getStatus(), "O status está incorreto.");

        // Removido o teste para o ID gerado pelo banco
        // Não é necessário verificar o ID se não houver persistência.
    }

    @Test
    void testToString() {
        // A representação esperada do toString, ajustando conforme o formato da sua classe
        String expected = "Consulta{id=null, dataHora=2024-12-05T10:30, motivo='Consulta de rotina', " +
                "veterinario=Dr. João Veterinário, pet=Rex, valor=150.0, status='Confirmada', comentario='null'}";
        assertEquals(expected, consulta.toString(), "A representação do toString está incorreta.");
    }
    @Test
    void testRemarcarConsulta() {
        LocalDateTime novaDataHora = LocalDateTime.of(2024, 12, 6, 14, 00);
        String novoStatus = "Remarcada";

        ConsultaDAO consultaDAO = new ConsultaDAO();

        consultaDAO.remarcarConsulta(consulta.getId(), novaDataHora, novoStatus);

        Consulta consultaRemarcada = consultaDAO.findById(consulta.getId());

        assertNotNull(consultaRemarcada.getDataHora());
        assertEquals(novaDataHora, consultaRemarcada.getDataHora(), "A data e hora não foram atualizadas.");

        assertEquals(novoStatus, consultaRemarcada.getStatus(), "O status não foi atualizado corretamente.");
    }

}

