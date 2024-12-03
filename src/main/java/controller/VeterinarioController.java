package controller;

import model.Veterinario;
import repository.VeterinarioDAO;
import service.CadastrarVetService;

public class VeterinarioController {

    CadastrarVetService service = new CadastrarVetService();
    VeterinarioDAO dao = new VeterinarioDAO();

    public Veterinario readByCPF(String cpf) {
        return dao.readByCPF(cpf);
    }

    public boolean cadastrarVeterinario(Veterinario veterinario) {
        try {
            service.validarVeterinario(veterinario);
            return dao.save(veterinario);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro na validação do veterinário: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar o veterinário: " + e.getMessage());
            return false;
        }
    }
}
