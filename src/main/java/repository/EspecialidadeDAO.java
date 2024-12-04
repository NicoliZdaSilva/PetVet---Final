package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Especialidade;

public class EspecialidadeDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("testes");
    private EntityManager em = emf.createEntityManager();

    public EspecialidadeDAO(){

    }

    public boolean save(Especialidade especialidade) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(especialidade);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao salvar especialidade: " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    public Especialidade getOrCreateEspecialidade(String nomeEspecialidade, EntityManager em) {
        Especialidade especialidade = em.createQuery(
                        "SELECT e FROM Especialidade e WHERE e.nome = :nome", Especialidade.class)
                .setParameter("nome", nomeEspecialidade)
                .getResultStream()
                .findFirst()
                .orElse(null);

        if (especialidade == null) {
            especialidade = new Especialidade();
            especialidade.setNome(nomeEspecialidade);
            em.persist(especialidade);
        }

        return especialidade;
    }
}




