package dao;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Driver;
import model.WasteTruck;

import java.util.List;

public class WasteTruckDAOImpl implements IWasteTruckDAO {
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    @Override
    public int saveWasteTruck(String brand, String registrationNumber, int capacity) {
        WasteTruck truck = new WasteTruck(brand, capacity, registrationNumber);
        int id;
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(truck);
            em.getTransaction().commit();
            id = truck.getId();
        }
        return id;
    }

    @Override
    public WasteTruck getWasteTruckById(int id) {
        WasteTruck truck;
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            truck = em.find(WasteTruck.class, id);
        }
        return truck;
    }

    @Override
    public void setWasteTruckAvailable(WasteTruck wasteTruck, boolean available) {
        wasteTruck.setAvailable(true);
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(wasteTruck);
            em.getTransaction().commit();
        }
    }

    @Override
    public void deleteWasteTruck(int id) {
       WasteTruck truck = getWasteTruckById(id);
       try(EntityManager em = emf.createEntityManager()){
           em.getTransaction().begin();
           em.remove(truck);
           em.getTransaction().commit();
       }
    }

    @Override
    public void addDriverToWasteTruck(WasteTruck wasteTruck, Driver driver) {

    }

    @Override
    public void removeDriverFromWasteTruck(WasteTruck wasteTruck, String id) {

    }

    @Override
    public List<WasteTruck> getAllAvailableTrucks() {
        return null;
    }
}
