package controller;

import jakarta.persistence.EntityManager;
import model.Especialidade;
import repository.EspecialidadeDAO;

public class EspecialidadeController {
    EspecialidadeDAO dao = new EspecialidadeDAO();
    public EspecialidadeController(){
    }

    public Especialidade registrarEspecialidade(String nomeEsp, EntityManager em) {
        if (nomeEsp == null || nomeEsp.isEmpty()) {
            throw new IllegalArgumentException("O nome da especialidade não pode ser nulo ou vazio.");
        }
        return dao.getOrCreateEspecialidade(nomeEsp, em);
    }
}
