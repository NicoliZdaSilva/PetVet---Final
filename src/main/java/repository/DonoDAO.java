package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Dono;

import java.util.List;

public class DonoDAO {
    private EntityManagerFactory emf;

    public DonoDAO() {
        emf = Persistence.createEntityManagerFactory("Testes");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void salvarDono(Dono dono) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(dono);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Dono findByCpf(String cpf) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Dono.class, cpf);
        } finally {
            em.close();
        }
    }

    public List<Dono> findAll() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT d FROM Dono d", Dono.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void updatePorCampo(String cpf, String novoNome, Integer novaIdade, String novoEstado, String novaCidade, String novoTelefone) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Dono dono = em.find(Dono.class, cpf);

            if (dono == null) {
                throw new IllegalArgumentException("Dono com CPF " + cpf + " não encontrado.");
            }

            if (novoNome != null && !novoNome.isEmpty()) {
                dono.setNome(novoNome);
            }
            if (novaIdade != null && (novaIdade > 0 && novaIdade < 120)) {
                dono.setIdade(novaIdade);
            }
            if (novoEstado != null && !novoEstado.isEmpty()) {
                dono.setEstado(novoEstado);
            }
            if (novaCidade != null && !novaCidade.isEmpty()) {
                dono.setCidade(novaCidade);
            }
            if (novoTelefone != null && !novoTelefone.isEmpty() && novoTelefone.length() == 11) {
                dono.setTelefone(novoTelefone);
            }

            em.merge(dono);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void delete(String cpf) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Dono dono = em.find(Dono.class, cpf);
            if (dono != null) {
                em.remove(dono);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
