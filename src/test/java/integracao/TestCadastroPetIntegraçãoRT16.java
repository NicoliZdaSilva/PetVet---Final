package integracao;

import model.Dono;
import model.Pet;
import org.junit.jupiter.api.Test;
import repository.DonoDAO;
import repository.PetDAO;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCadastroPetIntegraçãoRT16 {
    @Test
    public void cadastrarPet() {

        DonoDAO dao = new DonoDAO();
        Dono dono1 = new Dono("Luiza", 18, "25486301222", "SC", "Ibirama", "47999999999");

        dao.salvarDono(dono1);

        Pet pet1 = new Pet();
        pet1.setDono(dono1);
        pet1.setTipo("Cachorro");
        pet1.setNome("Nico");
        pet1.setDataNascimento(LocalDate.of(2020, 12, 25));

        dono1.addPet(pet1);

        Pet petSalvo = dono1.getPets().iterator().next();
        assertEquals("Cachorro", petSalvo.getTipo());
        assertEquals("Nico", petSalvo.getNome());
        assertEquals(LocalDate.of(2020, 12, 25), petSalvo.getDataNascimento());
    }
}



