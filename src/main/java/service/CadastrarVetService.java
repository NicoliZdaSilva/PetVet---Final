package service;

import model.Veterinario;
import repository.VeterinarioDAO;

public class CadastrarVetService {

    VeterinarioDAO dao = new VeterinarioDAO();

    public void validarVeterinario(Veterinario veterinario) throws IllegalArgumentException {
        if (veterinario == null) {
            throw new IllegalArgumentException("O veterinário não pode ser nulo.");
        }

        if (veterinario.getCpf() == null || veterinario.getCpf().isBlank()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio.");
        }

        if (dao.readByCPF(veterinario.getCpf()) != null) {
            throw new IllegalArgumentException("CPF já cadastrado no sistema.");
        }

        if (veterinario.getNome() == null || veterinario.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do veterinário não pode ser nulo ou vazio.");
        }

        if (veterinario.getDataAdmissao() == null) {
            throw new IllegalArgumentException("A data de admissão é obrigatória.");
        }

        if (veterinario.getEspecialidades() == null || veterinario.getEspecialidades().isEmpty()) {
            throw new IllegalArgumentException("O veterinário deve ter ao menos uma especialidade.");
        }
    }


}
