package service;

import model.Veterinario;

import java.util.Set;
import java.util.stream.Collectors;

public class ExibirVeterinariosService {
    public String exibirVeterinarios(Set<Veterinario> quadroDeVeterinarios) {
        if (quadroDeVeterinarios == null || quadroDeVeterinarios.isEmpty()) {
            return "Nenhum veterinário cadastrado até o momento";
        }

        return quadroDeVeterinarios.stream()
                .sorted((v1, v2) -> v1.getNome().compareToIgnoreCase(v2.getNome()))
                .map(vet -> {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Name: ").append(vet.getNome()).append("\n");
                    vet.getEspecialidades().forEach(esp ->
                            sb.append("Especialidade: ").append(esp.getNome()).append("\n"));
                    return sb.toString().trim();
                })
                .collect(Collectors.joining("\n"));
    }


}
