package dao;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Driver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DriverDAOImplTest {


        Driver d1 = new Driver("Thresh", "Ulriksen", new BigDecimal("10500"));
        Driver d2 = new Driver("Annie", "Holm", new BigDecimal("5200"));
        Driver d3 = new Driver("Quinn", "Larsen", new BigDecimal("35250"));
        DriverDAOImpl dao = new DriverDAOImpl();
    @BeforeEach
    void setUp(){
    }

    @Test
    void saveDriver() {
       assertNull(d1.getId());
       d1.setId(dao.saveDriver(d1.getName(), d1.getSurname(), new BigDecimal("69420")));
       assertNotNull(d1.getId());
    }

    @Test
    void getDriverById() {
        Driver driver;
        d2.setId(dao.saveDriver(d1.getName(), d1.getSurname(), new BigDecimal("42069")));
        driver = dao.getDriverById(d2.getId());
        assertNotNull(driver.getId());
    }

    @Test
    void updateDriver() {

    }

    @Test
    void deleteDriver() {
       String id = dao.saveDriver(d1.getName(), d1.getSurname(), new BigDecimal("12345"));
       assertNotNull(dao.getDriverById(id));
       dao.deleteDriver(id);
       assertNull(dao.getDriverById(id));
    }

    @Test
    void findAllDriversEmployedAtTheSameYear() {

    }

    @Test
    void fetchAllDriversWithSalaryGreaterThan10000() {
    }

    @Test
    void fetchHighestSalary() {
    }

    @Test
    void fetchFirstNameOfAllDrivers() {
    }

    @Test
    void calculateNumberOfDrivers() {
    }

    @Test
    void fetchDriverWithHighestSalary() {
    }
}