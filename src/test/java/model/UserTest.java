package model;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    EntityManagerFactory emf;
    User u;
    Address a;

    @BeforeEach
    void setUp() {
        a = new Address();
        emf = HibernateConfig.getEntityManagerFactoryConfig();
        u = User.builder()
                .name("Test")
                .surname("Testersen")
                .email("test@test.com")
                .phoneNumber("+4512345678")
                .build();
        u.setAddress(a);
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(a);
            em.persist(u);
            em.getTransaction().commit();
        }
    }

    @AfterEach
    void tearDown() {
        emf = null;
        u = null;
    }

    @Test
    void addHobby() {
        int oldUserSize = u.getHobbies().size();

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Hobby hobby = em.find(Hobby.class, 1);
            int oldHobbySize = hobby.getUsers().size();

            u.addHobby(hobby);
            em.getTransaction().commit();
            assertEquals(oldUserSize + 1, u.getHobbies().size());
            assertEquals(oldHobbySize + 1, hobby.getUsers().size());
        }

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            User user = em.find(User.class, u.getId());
            Hobby hobby = em.find(Hobby.class, 1);

            assertTrue(user.getHobbies().contains(hobby));
            assertTrue(hobby.getUsers().contains(user));
        }
    }

    @Test
    void addEvent() {
        int oldUserSize = u.getEvents().size();
        int eventId;

        try (EntityManager em = emf.createEntityManager()) {
            Event event = Event.builder()
                    .name("Roskilde festival")
                    .address(a)
                    .price(2250f)
                    .startDate(LocalDate.of(2023, 6, 25))
                    .endDate(LocalDate.of(2023, 7, 2))
                    .build();

            em.getTransaction().begin();
            event.setHobby(em.find(Hobby.class, 99));
            em.persist(event);
            eventId = event.getId();
            int oldHobbySize = event.getUsers().size();

            u.addEvent(event);
            em.getTransaction().commit();
            assertEquals(oldUserSize + 1, u.getEvents().size());
            assertEquals(oldHobbySize + 1, event.getUsers().size());
        }

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            User user = em.find(User.class, u.getId());
            Event event = em.find(Event.class, eventId);

            assertTrue(user.getEvents().contains(event));
            assertTrue(event.getUsers().contains(user));
        }
    }
}