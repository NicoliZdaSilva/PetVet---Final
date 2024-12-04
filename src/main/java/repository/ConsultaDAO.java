package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Consulta;

import java.time.LocalDateTime;
import java.util.List;

public class ConsultaDAO {
    private EntityManagerFactory emf;

    public ConsultaDAO() {
        emf = Persistence.createEntityManagerFactory("testes");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void salvarConsulta(Consulta consulta) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(consulta);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Consulta findById(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Consulta.class, id);
        } finally {
            em.close();
        }
    }

    public List<Consulta> findAll() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Consulta c", Consulta.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void updateConsulta(Long id, String novoStatus, LocalDateTime novoHorario, String novoComentario) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Consulta consulta = em.find(Consulta.class, id);

            if (consulta == null) {
                throw new IllegalArgumentException("Consulta com ID " + id + " não encontrada.");
            }

            if (novoStatus != null && !novoStatus.trim().isEmpty()) {
                consulta.setStatus(novoStatus);
            }
            if (novoHorario != null) {
                consulta.setDataHora(novoHorario);
            }
            if (novoComentario != null && !novoComentario.trim().isEmpty()) {
                consulta.setComentario(novoComentario);
            }

            em.merge(consulta);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Consulta consulta = em.find(Consulta.class, id);
            if (consulta != null) {
                em.remove(consulta);
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
