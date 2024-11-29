package unitarios;

import model.Especialidade;
import model.Veterinario;
import org.junit.jupiter.api.Test;
import service.ExibirVeterinariosService;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestVisualizacaoVetUnitarioRT13 {

    @Test // CT39
    public void testExibirListaCompletaDeVeterinarios() {
        Set<Veterinario> quadroDeVeterinarios = new HashSet<>();

        quadroDeVeterinarios.add(new Veterinario("João Parro", 35, "111.111.111-11", "29/11/2023", new Especialidade("Oncologia")));
        quadroDeVeterinarios.add(new Veterinario("Nicoli Zimmermann da Silva", 28, "222.222.222-22", "15/06/2022", new Especialidade("Animais de grande porte")));
        quadroDeVeterinarios.add(new Veterinario("Thaiane de Almeida", 24, "333.333.333-33", "01/02/2024", new Especialidade("Anestesia")));
        quadroDeVeterinarios.add(new Veterinario("Henrique Knaul", 29, "444.444.444-44", "25/08/2021", new Especialidade("Animais de pequeno porte")));

        ExibirVeterinariosService service = new ExibirVeterinariosService();
        String resultado = service.exibirVeterinarios(quadroDeVeterinarios);

        String esperado = """
                        Name: Henrique Knaul
                        Especialidade: Animais de pequeno porte
                        Name: João Parro
                        Especialidade: Oncologia
                        Name: Nicoli Zimmermann da Silva
                        Especialidade: Animais de grande porte
                        Name: Thaiane de Almeida
                        Especialidade: Anestesia
                        """.trim();

        assertEquals(esperado, resultado);
    }

    @Test // CT40
    public void testNenhumVeterinarioCadastrado() {
        Set<Veterinario> quadroDeVeterinarios = new HashSet<>();

        ExibirVeterinariosService service = new ExibirVeterinariosService();

        String resultado = service.exibirVeterinarios(quadroDeVeterinarios);
        assertEquals("Nenhum veterinário cadastrado até o momento", resultado);
    }

}
