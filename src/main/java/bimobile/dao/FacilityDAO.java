package bimobile.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import bimobile.model.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class FacilityDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("carrentalPU");
    private EntityManager em = emf.createEntityManager();

    public void addFacility(Facility facility) {
        em.getTransaction().begin();
        em.persist(facility); // Save to database
        em.getTransaction().commit();
    }

    public List<Facility> getAllFacilities() {
        return em.createQuery("SELECT c FROM Facility c", Facility.class).getResultList();
    }

    public Facility getFacilityById(Long id) {
        try {
            return em.createQuery("SELECT f FROM Facility f WHERE f.id = :id", Facility.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void close() {
        em.close();
        emf.close();
    }

    public void updateFacility(Facility facility) {
        em.getTransaction().begin();
        em.merge(facility); // merges changes into database
        em.getTransaction().commit();
    }
}
