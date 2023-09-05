package model;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class EventTest {
    EntityManagerFactory emf;
    Event event;
    Address a;

    @BeforeEach
    void setUp() {
        a = new Address();
        emf = HibernateConfig.getEntityManagerFactoryConfig();
        event = Event.builder()
                .name("Roskilde festival")
                .address(a)
                .price(2250f)
                .build();
        event.setAddress(a);

        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(a);
            event.setHobby(em.find(Hobby.class, 99));
            em.persist(event);
            em.getTransaction().commit();
        }
    }

    @AfterEach
    void tearDown() {
        emf = null;
        event = null;
    }

    @Test
    void addUser() {
        int originalUserSetSize = event.getUsers().size();

        event.addUser(null);

        assertEquals(originalUserSetSize, event.getUsers().size());

        User u = User.builder()
                .name("Test")
                .surname("Testersen")
                .email("test@test.com")
                .phoneNumber("+4512345678")
                .build();
        u.setAddress(a);

        event.addUser(u);

        assertEquals(originalUserSetSize + 1, event.getUsers().size());

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(u);
            em.merge(event);
            em.getTransaction().commit();
        }
    }
}