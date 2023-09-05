package model;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HobbyTest {
    EntityManagerFactory emf;
    User user;
    Hobby hobby;

    @BeforeEach
    void setUp() {
        emf = HibernateConfig.getEntityManagerFactoryConfig();

        Address a = new Address();
        user = User.builder()
                .name("Test")
                .surname("Testersen")
                .email("test@test.com")
                .phoneNumber("+4512345678")
                .build();
        user.setAddress(a);

        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            hobby = em.find(Hobby.class, 300);
            Hibernate.initialize(hobby.getUsers());
            em.persist(a);
            em.persist(user);
            em.getTransaction().commit();
        }
    }

    @AfterEach
    void tearDown() {
        emf = null;
    }

    @Test
    void addUser() {
        int originalUserSetSize = hobby.getUsers().size();

        hobby.addUser(null);

        assertEquals(originalUserSetSize, hobby.getUsers().size());

        hobby.addUser(user);

        assertEquals(originalUserSetSize + 1, hobby.getUsers().size());

        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(hobby);
            em.getTransaction().commit();
        }
    }
}