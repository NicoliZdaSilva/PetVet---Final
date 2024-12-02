package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Dono;
import model.Pet;

import java.util.List;

public class PetDAO {

    private EntityManagerFactory emf;

    public PetDAO() {
        emf = Persistence.createEntityManagerFactory("Testes");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void salvarPet(Pet pet) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(pet);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Pet findById(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pet.class, id);
        } finally {
            em.close();
        }
    }

    public List<Pet> findAll() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Pet p", Pet.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void updatePet(Long id, String novoNome, String novoTipo, Long novoDonoId) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Pet pet = em.find(Pet.class, id);

            if (pet == null) {
                throw new IllegalArgumentException("Pet com ID " + id + " não encontrado.");
            }

            if (novoNome != null && !novoNome.trim().isEmpty()) {
                pet.setNome(novoNome);
            }
            if (novoTipo != null && !novoTipo.trim().isEmpty()) {
                pet.setTipo(novoTipo);
            }
            if (novoDonoId != null) {
                pet.setDono(em.getReference(Dono.class, novoDonoId));
            }

            em.merge(pet);
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
            Pet pet = em.find(Pet.class, id);
            if (pet != null) {
                em.remove(pet);
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