package unitarios;

import model.Dono;
import model.Pet;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import repository.DonoDAO;
import repository.PetDAO;

public class TestAdicionarPetAoDono {

    @Test // CT01 - Adicionar um novo pet válido ao dono
    public void testAdicionarPetValidoAoDono() {
        // Criar um dono
        Dono dono = new Dono("Maria Silva", 35, "123.456.789-00", "SP", "São Paulo", "11987654321");

        // Criar um novo pet
        Pet novoPet = new Pet(LocalDate.of(2022, 5, 10), "Cachorro", "Rex", dono);

        // Verificar se o dono foi associado corretamente ao pet
        assertEquals(dono, novoPet.getDono());
        assertEquals("Rex", novoPet.getNome());
        assertEquals("Cachorro", novoPet.getTipo());
        assertEquals(LocalDate.of(2022, 5, 10), novoPet.getDataNascimento());

        // Mensagem para conferência no terminal
        System.out.println("CT01 - Pet adicionado com sucesso: " + novoPet);
    }

    @Test // CT02 - Adicionar pet com dono nulo (espera exceção)
    public void testAdicionarPetComDonoNulo() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Pet(LocalDate.of(2022, 5, 10), "Gato", "Mimi", null)
        );

        assertEquals("O dono do pet não pode ser nulo.", exception.getMessage());

        // Mensagem para conferência no terminal
        System.out.println("CT02 - Exceção capturada com sucesso: " + exception.getMessage());
    }

    @Test // CT03 - Adicionar pet com nome vazio (espera exceção)
    public void testAdicionarPetComNomeVazio() {
        Dono dono = new Dono("Carlos Souza", 40, "987.654.321-00", "RJ", "Rio de Janeiro", "21987654321");

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Pet(LocalDate.of(2020, 1, 15), "Gato", "", dono)
        );

        assertEquals("O nome do pet não pode ser nulo ou vazio.", exception.getMessage());

        // Mensagem para conferência no terminal
        System.out.println("CT03 - Exceção capturada com sucesso: " + exception.getMessage());
    }

    @Test // CT04 - Adicionar pet com data de nascimento nula (espera exceção)
    public void testAdicionarPetComDataNascimentoNula() {
        Dono dono = new Dono("Ana Paula", 28, "456.789.123-00", "MG", "Belo Horizonte", "31987654321");

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Pet(null, "Cachorro", "Toby", dono)
        );

        //assertEquals("A data de nascimento não pode ser nula.", exception.getMessage());

        // Mensagem para conferência no terminal
        System.out.println("CT04 - Exceção capturada com sucesso: " + exception.getMessage());
    }

    @Test // CT05 - Adicionar mais de um pet ao dono
    public void testAdicionarMaisDeUmPetAoDono() {
        // Criar um dono
        Dono dono = new Dono("Lucas Oliveira", 45, "321.654.987-00", "RS", "Porto Alegre", "51987654321");

        // Criar os pets
        Pet pet1 = new Pet(LocalDate.of(2021, 6, 20), "Cachorro", "Buddy", dono);
        Pet pet2 = new Pet(LocalDate.of(2020, 3, 15), "Gato", "Miau", dono);

        // Verificar se o dono está corretamente associado a ambos os pets
        assertEquals(dono, pet1.getDono());
        assertEquals(dono, pet2.getDono());
        assertEquals("Buddy", pet1.getNome());
        assertEquals("Miau", pet2.getNome());

        // Mensagem para conferência no terminal
        System.out.println("CT05 - Mais de um pet adicionado ao dono com sucesso: " + pet1 + ", " + pet2);
    }

    @Test // CT06 - Atualizar nome do pet
    public void testAtualizarNomePet() {
        // Criar um dono
        Dono dono = new Dono("João Pereira", 30, "111.222.333-44", "SP", "São Paulo", "11987654321");

        // Criar um pet
        Pet pet = new Pet(LocalDate.of(2021, 7, 10), "Cachorro", "Rex", dono);

        // Verificar se o nome original do pet está correto
        assertEquals("Rex", pet.getNome());

        // Atualizar o nome do pet
        pet.setNome("Max");

        // Verificar se o nome foi atualizado corretamente
        assertEquals("Max", pet.getNome());

        // Mensagem para conferência no terminal
        System.out.println("CT06 - Nome do pet atualizado com sucesso para: " + pet.getNome());
    }

    @Test // CT10 - Trocar o tipo do pet
    public void testTrocarTipoPet() {
        // Criar um dono
        Dono dono = new Dono("Paulo Oliveira", 40, "123.456.789-10", "SP", "São Paulo", "11987654321");

        // Criar um pet
        Pet pet = new Pet(LocalDate.of(2018, 3, 5), "Cachorro", "Bolt", dono);

        // Verificar o tipo original do pet
        String tipoOriginal = pet.getTipo();
        System.out.println("CT10 - Tipo original do pet: " + tipoOriginal);

        // Atualizar o tipo do pet
        pet.setTipo("Gato");

        // Verificar o tipo após a atualização
        String tipoAtualizado = pet.getTipo();
        System.out.println("     - Tipo atualizado do pet: " + tipoAtualizado);

        // Verificar se o tipo foi atualizado corretamente
        assertEquals("Gato", pet.getTipo());

        // Mensagem para conferência no terminal
        System.out.println("     - O tipo do pet foi atualizado de '" + tipoOriginal + "' para '" + tipoAtualizado + "'.");
    }
    @Test
    public void adicionarMesmoPet() {
        Dono dono = new Dono("Nicoli da Silva", 20, "111.222.333-58", "SC", "Ituporanga", "47988556644");

        Pet pet1 = new Pet(LocalDate.of(2018, 8, 5), "Cachorro", "Dandara", dono);
        Pet pet2 = new Pet(LocalDate.of(2018, 8, 5), "Cachorro", "Dandara", dono);

        PetDAO petDAO = new PetDAO();
        petDAO.salvarPet(pet1);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            petDAO.salvarPet(pet2);
        });

        String expectedMessage = "Já existe um pet com o nome " + pet2.getNome() + " para o mesmo dono.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
