package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Employee;
import model.Facility;

import java.util.List;

public class FacilityDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("carrentalPU");
    private EntityManager em = emf.createEntityManager();

    public void addFacility(Facility facility) {
        em.getTransaction().begin();
        em.persist(facility); // Save to database
        em.getTransaction().commit();
    }

    public List<Employee> getAllFacilities() {
        return em.createQuery("SELECT c FROM Facility c", Facility.class).getResultList();
    }

    public void close() {
        em.close();
        emf.close();
    }
}
