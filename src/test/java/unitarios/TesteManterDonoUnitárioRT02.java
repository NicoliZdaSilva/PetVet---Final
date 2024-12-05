package unitarios;

import model.Dono;
import model.Pessoa;
import unitarios.TesteCadastroDonoUnitárioRT01;
import org.junit.jupiter.api.Test;
import repository.DonoDAO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TesteManterDonoUnitárioRT02 {
    @Test //CT05
    public void alterarUmDadoDono() {
        // Dados iniciais
        String nome = "Nicoli Silva";
        int idade = 20;
        String cpf = "14455588878";
        String estado = "Santa Catarina";
        String cidade = "Ibirama";
        String telefone = "47988775544";

        Dono dono1 = new Dono(nome, idade, cpf, estado, cidade, telefone);

        assertEquals(nome, dono1.getNome());
        assertEquals(idade, dono1.getIdade());
        assertEquals(cpf, dono1.getCpf());
        assertEquals(estado, dono1.getEstado());
        assertEquals(cidade, dono1.getCidade());
        //assertEquals(telefone, dono1.getTelefone());

        String novaCidade = "Rio do Sul";
        dono1.setCidade(novaCidade);
        assertEquals(novaCidade, dono1.getCidade());
    }
    @Test //CT06
    public void alterarTodosOsDados(){
        String nome = "Nicoli Silva";
        int idade = 20;
        String cpf = "14455588878";
        String estado = "Santa Catarina";
        String cidade = "Ibirama";
        String telefone = "47988775544";

        Dono dono2 = new Dono(nome, idade, cpf, estado, cidade, telefone);

        assertEquals(nome, dono2.getNome());
        assertEquals(idade, dono2.getIdade());
        assertEquals(cpf, dono2.getCpf());
        assertEquals(estado, dono2.getEstado());
        assertEquals(cidade, dono2.getCidade());
        //assertEquals(telefone, dono2.getTelefone());

        String novoNome = "Maria Antonia";
        dono2.setNome(novoNome);
        assertEquals(novoNome, dono2.getNome());

        int novaIdade = 22;
        dono2.setIdade(novaIdade);
        assertEquals(novaIdade, dono2.getIdade());

        String novoCpf = "12255588878";
        dono2.setCpf(novoCpf);
        assertEquals(novoCpf, dono2.getCpf());

        String novoEstado = "São Paulo";
        dono2.setEstado(novoEstado);
        assertEquals(novoEstado, dono2.getEstado());

        String novaCidade = "São Paulo";
        dono2.setCidade(novaCidade);
        assertEquals(novaCidade, dono2.getCidade());

        String novoTelefone = "47955998855";
        dono2.setTelefone(novoTelefone);
        //assertEquals(novoTelefone, dono2.getTelefone());
    }



        private DonoDAO donoDAO = new DonoDAO();

        @Test // CT07 - Teste de cadastrar dono com CPF já existente
        public void cadastrarMesmoDono() {
            // Dados do primeiro dono
            String nome = "Maria Antonia";
            int idade = 25;
            String cpf = "12256698874";
            String estado = "São Paulo";
            String cidade = "São Paulo";
            String telefone = "47958621665";

            Dono dono1 = new Dono(nome, idade, cpf, estado, cidade, telefone);
            donoDAO.salvarDono(dono1);  // Salvando o primeiro dono no banco

            // Tentando cadastrar um segundo dono com o mesmo CPF
            String nome2 = "Maria Antonia";
            int idade2 = 25;
            String cpf2 = "12256698874";  // O CPF é o mesmo
            String estado2 = "São Paulo";
            String cidade2 = "São Paulo";
            String telefone2 = "47958621665";

            // Espera-se que lance uma IllegalArgumentException devido ao CPF duplicado
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                Dono dono2 = new Dono(nome2, idade2, cpf2, estado2, cidade2, telefone2);
                donoDAO.salvarDono(dono2);  // Tentando salvar o dono com CPF duplicado
            });

            // Verificando se a exceção foi lançada com a mensagem correta
            assertEquals("Este CPF já está cadastrado.", exception.getMessage());
        }
    }

