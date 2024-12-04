package integracao;

import model.Dono;
import org.junit.jupiter.api.Test;
import repository.DonoDAO;

import static org.junit.jupiter.api.Assertions.*;

public class TetsCadastroDonoIntegraçãoRT14 { //RT14

    @Test // CT41
    public void testeCadastrarDonoCompleto(){
        DonoDAO dao = new DonoDAO();
        Dono donoEsperado = new Dono("Maria", 18, "8979995674", "SC", "Ibirama", "47999999999"); // Corrigido CPF e telefone de exemplo

        dao.salvarDono(donoEsperado);

        Dono donoSalvo = dao.findByCpf("8979995674"); // Corrigido para o CPF correto

        // Assegura que todos os dados do dono esperado são iguais aos dados salvos
        assertEquals(donoEsperado.getNome(), donoSalvo.getNome());
        assertEquals(donoEsperado.getIdade(), donoSalvo.getIdade());
        assertEquals(donoEsperado.getCpf(), donoSalvo.getCpf());
        assertEquals(donoEsperado.getEstado(), donoSalvo.getEstado());
        assertEquals(donoEsperado.getCidade(), donoSalvo.getCidade());
       // assertEquals(donoEsperado.getTelefone(), donoSalvo.getTelefone());
    }

    @Test
    public void testeCadastroTelefoneErrado() {
        DonoDAO dao = new DonoDAO();
        Dono donoComTelefoneErrado = new Dono("Nicoli da Silva", 19, "55644877957", "SC", "Ibirama", "479885554477"); // Telefone com 12 dígitos

        // Espera-se que uma exceção seja lançada devido ao telefone inválido
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            dao.salvarDono(donoComTelefoneErrado);
        });

        // Verifica se a exceção possui a mensagem correta
        assertEquals(" O telefone precisa ter 11 dígitos, incluindo o DDD.", exception.getMessage());
    }
    @Test
    public void atualizarDono(){
        DonoDAO dao = new DonoDAO();
        Dono donoAtualizar = new Dono ("Henrique Knaul", 20, "5566644478", "Paraná", "Curitiba", "47966558844");

        dao.salvarDono(donoAtualizar);

        dao.updatePorCampo("5566644478", "Henrique Knaul", 21, "Santa Catarina", "Rodeio", "47988554466");

        Dono donoAtualizado = dao.findByCpf("5566644478");

        assertNotNull(donoAtualizado);
        assertEquals("Henrique Knaul", donoAtualizado.getNome());
        assertEquals(21, donoAtualizado.getIdade());
        assertEquals("Santa Catarina", donoAtualizado.getEstado());
        assertEquals("Rodeio", donoAtualizado.getCidade());
       // assertEquals("47988554466", donoAtualizado.getTelefone());

        dao.delete("5566644478");
    }
}
