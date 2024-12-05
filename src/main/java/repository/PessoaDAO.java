package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Pessoa;

public class PessoaDAO {
    private EntityManagerFactory emf;

    public PessoaDAO() {
        emf = Persistence.createEntityManagerFactory("testes");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void salvarPessoa(Pessoa pessoa) {
        if (pessoa == null) {
            throw new IllegalArgumentException("A pessoa não pode ser nula.");
        }

        // Verificar se já existe uma pessoa com o CPF informado
        EntityManager em = getEntityManager();
        try {
            // Verificando se já existe um CPF cadastrado no banco de dados
            Pessoa pessoaExistente = em.find(Pessoa.class, pessoa.getCpf());
            if (pessoaExistente != null) {
                throw new IllegalArgumentException("Este CPF já está cadastrado.");
            }

            // Se o CPF não existir, persiste a pessoa
            em.getTransaction().begin();
            em.persist(pessoa);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar a pessoa: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    // Outros métodos (findByCpf, findAll, updatePorCampo, delete) permanecem iguais.
}
