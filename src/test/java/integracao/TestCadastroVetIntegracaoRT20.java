package integracao; //Thaiane

import controller.EspecialidadeController;
import controller.VeterinarioController;
import model.Especialidade;
import model.Veterinario;
import org.junit.jupiter.api.Test;
import repository.VeterinarioDAO;
import service.CadastrarVetService;

import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class TestCadastroVetIntegracaoRT20 {

    @Test // CT52
    public void testCadastroVeterinarioPersistencia() {
        VeterinarioController controller = new VeterinarioController();
        String nome = "Nicoli Zimmermann da Silva";
        int idade = 30;
        String cpf = "987.654.321-30";
        String dataAdmissao = "22/04/2020";

        Especialidade especialidade = new Especialidade("Animais de grande porte");
        Veterinario veterinario = new Veterinario(nome, idade, cpf, dataAdmissao, especialidade);


        boolean cadastroEfetuado = controller.cadastrarVeterinario(veterinario);

        assertTrue(cadastroEfetuado, "O veterinário deveria ser cadastrado com sucesso.");

        Veterinario veterinarioSalvo = controller.readByCPF(cpf);
        assertNotNull(veterinarioSalvo, "O veterinário deveria estar persistido no banco.");
        assertEquals(nome, veterinarioSalvo.getNome(), "O nome do veterinário não corresponde.");
        assertEquals(idade, veterinarioSalvo.getIdade(), "A idade do veterinário não corresponde.");
        assertEquals(cpf, veterinarioSalvo.getCpf(), "O CPF do veterinário não corresponde.");
        assertEquals(dataAdmissao, veterinarioSalvo.getDataAdmissao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                "A data de admissão não corresponde.");

        Set<String> especialidadesCadastradas = veterinarioSalvo.getEspecialidades()
                .stream()
                .map(Especialidade::getNome)
                .collect(Collectors.toSet());

        assertTrue(especialidadesCadastradas.contains("Animais de grande porte"),
                "A especialidade do veterinário não corresponde.");
    }

    @Test // CT53
    public void testCadastroVeterinarioComDadosIncompletos() {
        VeterinarioController service = new VeterinarioController();
        VeterinarioDAO dao = new VeterinarioDAO();

        String nome = "Thaiane Bento";
        String dataAdmissao = "";
        String cpf = "123.456.789-00";
        Especialidade especialidade = new Especialidade("Animais de pequeno porte");


        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Veterinario(nome, 30, cpf, dataAdmissao, especialidade);
        });

        assertEquals("É necessário informar a data de admissão.", exception.getMessage());

        Veterinario veterinarioCadastrado = dao.readByCPF(cpf);
        assertNull(veterinarioCadastrado);
    }

    @Test // CT54
    public void testCadastroVeterinarioComDataInvalida() {
        VeterinarioController service = new VeterinarioController();
        VeterinarioDAO dao = new VeterinarioDAO();

        String nome = "Thaiane Bento";
        String dataAdmissao = "08-20-2025";
        String cpf = "12345678900";
        Especialidade especialidade = new Especialidade("Animais de pequeno porte");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Veterinario(nome, 30, cpf, dataAdmissao, especialidade);
        });

        assertEquals("Formato de data inválido.", exception.getMessage());

        Veterinario veterinarioCadastrado = dao.readByCPF(cpf);
        assertNull(veterinarioCadastrado);

    }

    @Test // CT55
    public void testCadastroVeterinarioComDadosDuplicados() {
        VeterinarioController service = new VeterinarioController();
        VeterinarioDAO dao = new VeterinarioDAO();

        String nome = "Nicoli Zimmermann da Silva";
        String dataAdmissao = "22/04/2020";
        String cpf = "987.654.321-00";
        Especialidade especialidade = new Especialidade("Animais de grande porte");

        Veterinario veterinario1 = new Veterinario(nome, 30, cpf, dataAdmissao, especialidade);
        boolean resultado1 = service.cadastrarVeterinario(veterinario1);
        assertTrue(resultado1, "O primeiro cadastro deveria ser bem-sucedido.");

        Veterinario veterinario2 = new Veterinario(nome, 30, cpf, dataAdmissao, especialidade);
        boolean resultado2 = service.cadastrarVeterinario(veterinario2);
        assertFalse(resultado2, "O sistema deveria impedir o cadastro de veterinários com dados duplicados.");

        Veterinario veterinarioCadastrado = dao.readByCPF(cpf);
        assertNotNull(veterinarioCadastrado);
        assertEquals(nome, veterinarioCadastrado.getNome(), "O nome do veterinário no banco deveria ser o mesmo do primeiro cadastro.");
    }

}
