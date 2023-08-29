package dao;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.Driver;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DriverDAOImpl implements IDriverDAO {

    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    @Override
    public String saveDriver(String name, String surname, BigDecimal salary) {
        String id;
       try(EntityManager em = emf.createEntityManager()) {
           Driver driver = new Driver(name, surname, salary);
           em.getTransaction().begin();
           em.persist(driver);
           em.getTransaction().commit();
           id = driver.generateId();
       }
       return id;
    }

    @Override
    public Driver getDriverById(String id) {
        Driver driver;
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            driver = em.find(Driver.class, id);
        }
        return driver;
    }

    @Override
    public Driver updateDriver(Driver driver) {
        Driver updatedDriver;
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            updatedDriver = em.merge(driver);
            em.getTransaction().commit();
        }
        return updatedDriver;
    }

    @Override
    public void deleteDriver(String id) {
        Driver driver = getDriverById(id);
        if (driver != null) {
            try (EntityManager em = emf.createEntityManager()) {
                em.getTransaction().begin();
                em.remove(driver);
                em.getTransaction().commit();
            }
        }
    }

    @Override
    public List<Driver> findAllDriversEmployedAtTheSameYear(String year) {
        List<Driver> drivers;
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            String query = "SELECT d FROM Driver d WHERE YEAR(d.employmentDate) = :year";
            TypedQuery<Driver> typedQuery = em.createQuery(query, Driver.class);
            typedQuery.setParameter("year", year);
            drivers = typedQuery.getResultList();
        }
        return drivers;
    }

    @Override
    public List<Driver> fetchAllDriversWithSalaryGreaterThan10000() {
        List<Driver> drivers;
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            String query = "SELECT d FROM Driver d WHERE d.salary > 10000";
            TypedQuery<Driver> typedQuery = em.createQuery(query, Driver.class);
            drivers = typedQuery.getResultList();
        }
        return drivers;
    }

    @Override
    public double fetchHighestSalary() {
        BigDecimal highestSalaryBigDecimal;
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            String query = "SELECT MAX(d.salary) FROM Driver d";
            TypedQuery<BigDecimal> typedQuery = em.createQuery(query, BigDecimal.class);
            highestSalaryBigDecimal = typedQuery.getSingleResult();
        }
        return highestSalaryBigDecimal.doubleValue();
    }

    @Override
    public List<String> fetchFirstNameOfAllDrivers() {
        List<String> firstNames;
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            String query = "SELECT d.name FROM Driver d";
            TypedQuery<String> typedQuery = em.createQuery(query, String.class);
            firstNames = typedQuery.getResultList();
        }
        return firstNames;
    }

    @Override
    public long calculateNumberOfDrivers() {
        Long amount;
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            String query = "SELECT COUNT(d) FROM Driver d";
            TypedQuery<Long> typedQuery = em.createQuery(query, Long.class);
            amount = typedQuery.getSingleResult();
        }
        return amount;
    }

    @Override
    public Driver fetchDriverWithHighestSalary() {
        Driver driver;
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            String query = "SELECT d FROM Driver d ORDER BY salary DESC LIMIT 1";
            TypedQuery<Driver> typedQuery = em.createQuery(query, Driver.class);
            driver = typedQuery.getSingleResult();
        }
        return driver;
    }
}
