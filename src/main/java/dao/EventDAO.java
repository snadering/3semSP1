package dao;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Event;

public class EventDAO {
    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    public void createEvent(Event event) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(event);
            em.getTransaction().commit();
        }
    }

    public Event readEvent(int id) {

        Event event;
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            event = em.find(Event.class, id);
            em.getTransaction().commit();
            }
        return event;
    }

    public void updateEvent(Event event) {
        try (EntityManager em = emf.createEntityManager()) {
            if(event.getId() != null) {
                em.getTransaction().begin();
                em.merge(event);
                em.getTransaction().commit();
            }
        }
    }

    public void deleteEvent(int id) {

        Event event = readEvent(id);
        if(event.getId() != null) {
            try (EntityManager em = emf.createEntityManager()) {
                em.getTransaction().begin();
                em.remove(event);
                em.getTransaction().commit();
            }
        }
    }
}

