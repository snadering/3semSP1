package dao;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.Event;

import java.util.List;

public class EventDAO {
    private final EntityManagerFactory emf;
    private static EventDAO instance;

    private EventDAO() {
        emf = HibernateConfig.getEntityManagerFactoryConfig();
    }

    public static EventDAO getInstance() {
        if (instance == null) {
            instance = new EventDAO();
        }

        return instance;
    }

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

    public List<Event> getAllEventsForSpecificHobby(int id){
        List<Event> allEventsForSpecificHobby;
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            TypedQuery<Event> typedQuery = em.createQuery("SELECT e FROM Event e WHERE e.hobby.id = :id", Event.class);
            typedQuery.setParameter("id",id);
            allEventsForSpecificHobby = typedQuery.getResultList();
        }
        return allEventsForSpecificHobby;
    }
}

