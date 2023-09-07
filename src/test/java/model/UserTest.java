package model;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Executable;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    EntityManagerFactory emf;
    User u;
    Address a;

    @BeforeEach
    void setUp() {
        a = new Address();
        a.setStreet("Campusvej");
        a.setZip(new ZipCode(3400,"Hillerod","123","123"));
        a.setNumber("2");
        emf = HibernateConfig.getEntityManagerFactoryConfig();
        u = User.builder()
                .name("Test")
                .surname("Testersen")
                .email("test@test.com")
                .phoneNumber("+45 12345678")
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

    @Test
    public void preUpdateValid() {
        // Valid email
        u = new User();
        u.setEmail("valid.email@example.com");
        u.setPhoneNumber("+4512348765");
        assertDoesNotThrow(() -> u.preUpdate());

        u.setPhoneNumber("+45 12345678");
        assertDoesNotThrow(() -> u.preUpdate());
    }

    @Test
    public void preUpdateInvalid() {
        // Invalid email
        u = new User();
        u.setEmail("invalid_email");
        u.setPhoneNumber("+45 12345678");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> u.preUpdate());
        assertEquals("Invalid email address", exception.getMessage());

        u.setEmail("valid@email.com");
        u.setPhoneNumber("+85 12345678");
        exception = assertThrows(IllegalArgumentException.class, () -> u.preUpdate());
        assertEquals("Invalid phone number", exception.getMessage());
    }


}
