package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Pessoa;

import java.util.List;

public class PessoaDAO {
    private EntityManagerFactory emf;

    public PessoaDAO() {
        emf = Persistence.createEntityManagerFactory("Testes");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void salvarPessoa(Pessoa pessoa) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(pessoa);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Pessoa findByCpf(String cpf) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pessoa.class, cpf);
        } finally {
            em.close();
        }
    }

    public List<Pessoa> findAll() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Pessoa p", Pessoa.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void updatePorCampo(String cpf, String novoNome, Integer novaIdade) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Pessoa pessoa = em.find(Pessoa.class, cpf);

            if (pessoa == null) {
                throw new IllegalArgumentException("Pessoa com CPF " + cpf + " não encontrada.");
            }

            if (novoNome != null && !novoNome.isEmpty()) {
                pessoa.setNome(novoNome);
            }
            if (novaIdade != null && (novaIdade > 0 && novaIdade < 120)) {
                pessoa.setIdade(novaIdade);
            }

            em.merge(pessoa);
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
            Pessoa pessoa = em.find(Pessoa.class, cpf);
            if (pessoa != null) {
                em.remove(pessoa);
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

