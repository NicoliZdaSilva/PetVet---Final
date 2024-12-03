package app;

import model.Especialidade;
import model.Veterinario;
import repository.VeterinarioDAO;

public class iniciarProjeto {
    public static void main(String[] args) {
        Veterinario vet = new Veterinario("Lucas", 25, "14569875725","17/04/2022", new Especialidade("felinos"));
        VeterinarioDAO dao = new VeterinarioDAO();
        dao.save(vet);


    }
}
