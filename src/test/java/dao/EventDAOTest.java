package dao;

import config.HibernateConfig;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventDAOTest {
    EntityManagerFactory emf;
    EventDAO dao;
    AddressDAO aDao;
    HobbyDAO hDao;
    Event e;

    @BeforeEach
    void setUp() {
        dao = EventDAO.getInstance();
        aDao = AddressDAO.getInstance();
        hDao = HobbyDAO.getInstance();

        Address a = new Address();
        a.setStreet("Campusvej");
        a.setZip(new ZipCode(3400,"Hillerod","123","123"));
        a.setNumber("2");

        aDao.createAddress(a);
        Hobby h = hDao.readHobbyById(99);
        e = Event.builder()
                .name("Roskilde festival")
                .address(a)
                .price(2250f)
                .startDate(LocalDate.of(2023, 6, 25))
                .endDate(LocalDate.of(2023, 7, 2))
                .build();
        e.setHobby(h);
        dao.createEvent(e);
    }

    @AfterEach
    void tearDown() {
        try (var em = HibernateConfig.getEntityManagerFactoryConfig().createEntityManager()) {
            em.getTransaction().begin();
            e = em.find(Event.class, e.getId());
            if (e != null) {
                em.remove(e);
            }
            em.getTransaction().commit();
        }

        dao = null;
        aDao = null;
        hDao = null;
    }

    @Test
    void getInstance() {
        assertNotNull(dao);

        EventDAO newDao = EventDAO.getInstance();

        assertEquals(dao, newDao);
    }

    @Test
    void createEvent() {
        Address a = new Address();
        a.setStreet("Campusvej");
        a.setZip(new ZipCode(3400,"Hillerod","123","123"));
        a.setNumber("2");
        aDao.createAddress(a);
        Hobby h = hDao.readHobbyById(99);
        Event e = Event.builder()
                .name("Roskilde festival")
                .address(a)
                .price(2250f)
                .startDate(LocalDate.of(2023, 6, 25))
                .endDate(LocalDate.of(2023, 7, 2))
                .build();
        e.setHobby(h);
        dao.createEvent(e);

        try (EntityManager em = HibernateConfig.getEntityManagerFactoryConfig().createEntityManager()) {
            Event event = em.find(Event.class, e.getId());
            assertNotNull(event);

            assertEquals(e.getName(), event.getName());
            assertEquals(e.getPrice(), event.getPrice());
            assertEquals(e.getAddress().getId(), event.getAddress().getId());
            assertEquals(e.getStartDate(), event.getStartDate());
            assertEquals(e.getEndDate(), event.getEndDate());
        }
    }

    @Test
    void readEvent() {
        Event event = dao.readEvent(e.getId());

        assertEquals(e.getName(), event.getName());
        assertEquals(e.getPrice(), event.getPrice());
        assertEquals(e.getAddress().getId(), event.getAddress().getId());
        assertEquals(e.getStartDate(), event.getStartDate());
        assertEquals(e.getEndDate(), event.getEndDate());
    }

    @Test
    void updateEvent() {
        User u = User.builder()
                .name("Test")
                .surname("Testersen")
                .email("Test@test.com")
                .phoneNumber("+455463281")
                .build();
        UserDAO.getInstance().createUser(u);

        int originalUserListSize = e.getUsers().size();
        e.addUser(u);
        int userListSize = e.getUsers().size();

        dao.updateEvent(e);

        assertEquals(originalUserListSize + 1, e.getUsers().size());
        assertEquals(userListSize, e.getUsers().size());
    }

    @Test
    void deleteEvent() {
        assertDoesNotThrow(() -> dao.deleteEvent(-10));

        dao.deleteEvent(e.getId());
        assertNull(dao.readEvent(e.getId()));

        assertDoesNotThrow(() -> dao.deleteEvent(e.getId()));
    }

    @Test
    void getEventsInDateRange() {
        Event e = Event.builder()
                .name("SP1")
                .address(new Address())
                .startDate(LocalDate.of(2023, 9, 4))
                .endDate(LocalDate.of(2023, 9, 7))
                .build();
        e.setHobby(HobbyDAO.getInstance().readHobbyById(32));

        List<Event> events = dao.getEventsInDateRange(LocalDate.of(2023, 6, 30), LocalDate.of(2023, 7, 1));

        System.out.println(events);
    }

    @Test
    void getAllEventsForSpecificHobby() {

        Hobby hobby = hDao.readHobbyById(99);

        Event event1 = createEventWithHobby("Event 1", hobby);
        Event event2 = createEventWithHobby("Event 2", hobby);
        Event event3 = createEventWithHobby("Event 3", hobby);

        List<Event> eventsForHobby = dao.getAllEventsForSpecificHobby(hobby.getId());

        assertTrue(!eventsForHobby.isEmpty());
    }

    private Event createEventWithHobby(String name, Hobby hobby) {
        emf = HibernateConfig.getEntityManagerFactoryConfig();
        Address a = new Address();
        a.setStreet("Campusvej");
        a.setZip(new ZipCode(3400,"Hillerod","123","123"));
        a.setNumber("2");

        aDao.createAddress(a);


        Event event = Event.builder()
                .name(name)
                .address(a)
                .price(2250f)
                .startDate(LocalDate.of(2023, 6, 25))
                .endDate(LocalDate.of(2023, 7, 2))
                .difficulty(EventDifficulty.ADVANCED)
                .build();
        event.setHobby(hobby);

        dao.createEvent(event);
        return event;
    }

}