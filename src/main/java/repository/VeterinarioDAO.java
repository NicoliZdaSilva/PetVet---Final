package repository;

import jakarta.persistence.*;
import jakarta.persistence.Persistence;
import model.Veterinario;

public class VeterinarioDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("testes");
    private EntityManager em = emf.createEntityManager();

    public boolean save(Veterinario veterinario) {
        try {
            em.getTransaction().begin();
            em.persist(veterinario);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao salvar veterinário: " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    public Veterinario readByCPF(String cpf) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT v FROM Veterinario v WHERE v.cpf = :cpf", Veterinario.class)
                    .setParameter("cpf", cpf)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.err.println("Veterinário não encontrado para o CPF: " + cpf);
            return null;
        } finally {
            em.close();
        }
    }
}


