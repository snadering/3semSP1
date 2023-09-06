package dao;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.Event;
import org.hibernate.Hibernate;

import java.time.LocalDate;
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
        if(event != null) {
            try (EntityManager em = emf.createEntityManager()) {
                em.getTransaction().begin();
                em.remove(event);
                em.getTransaction().commit();
            }
        }
    }

    public List<Event> getEventsInDateRange(LocalDate from, LocalDate to) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Event> query = em.createQuery("SELECT e FROM Event e WHERE (e.startDate >= :from OR e.endDate >= :from) AND (e.startDate <= :to OR e.endDate <= :to)", Event.class);
            query.setParameter("from", from);
            query.setParameter("to", to);
            List<Event> events = query.getResultList();
            Hibernate.initialize(events);
            return events;
        }
    }
}

