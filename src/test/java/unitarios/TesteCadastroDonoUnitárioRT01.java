//Nicoli
package unitarios;

import model.Especialidade;
import model.Pessoa;
import model.Dono;
import model.Veterinario;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TesteCadastroDonoUnitárioRT01 {
    @Test //CT01
    public void cadastrarDonoCompleto() {
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

        System.out.println(dono1.toString());
    }

    @Test //CT02
    public void CadastroDonoIncompleto() {
        String nome = "Nicoli Silva";
        int idade = 20;
        String cpf = "14455588878";
        String estado = "Santa Catarina";
        String cidade = "";  // Cidade vazia
        String telefone = "47988775544";
        {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                Dono dono2 = new Dono(nome, idade, cpf, estado, cidade, telefone);
            });

            assertEquals("A cidade deve ser informada.", exception.getMessage());

        }
    }
    @Test//CT03
    public void CadastroDonoTelefoneMaisOnze(){
        String nome = "Nicoli Silva";
        int idade = 20;
        String cpf = "12506895786";
        String estado = "SC";
        String cidade = "PG";
        String telefone = "478885554447777";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Dono dono3 = new Dono(nome, idade, cpf, estado, cidade, telefone);
        });
        assertEquals("O telefone precisa ter 11 dígitos, incluindo o DDD.", exception.getMessage());
    }
    @Test //CT04
    public void CadastroDonoTelefoneMenosOnze(){
        String nome = "Nicoli Silva";
        int idade = 20;
        String cpf = "12506895786";
        String estado = "SC";
        String cidade = "PG";
        String telefone = "4788855588";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Dono dono3 = new Dono(nome, idade, cpf, estado, cidade, telefone);
        });
        assertEquals("O telefone precisa ter 11 dígitos, incluindo o DDD.", exception.getMessage());
    }
}
