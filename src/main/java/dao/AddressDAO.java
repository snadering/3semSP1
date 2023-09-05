package dao;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Address;
import model.ZipCode;

public class AddressDAO {
    private final EntityManagerFactory emf;
    private static AddressDAO instance;

    private AddressDAO() {
        emf = HibernateConfig.getEntityManagerFactoryConfig();
    }

    public static AddressDAO getInstance() {
        if (instance == null) {
            instance = new AddressDAO();
        }

        return instance;
    }

    public int createAddress(Address address){
        int id;
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(address);
            em.getTransaction().commit();
            id = address.getId();
        }
        return id;
    }

    public int createAddress(String street, String number, ZipCode zip){
        int id;
        Address address = new Address(street, number, zip);
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(address);
            em.getTransaction().commit();
            id = address.getId();
        }
        return id;
    }
    public Address readAddress(int id){
        Address address;
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            address = em.find(Address.class, id);
        }
        return address;
    }

    public void updateAddress(Address address){
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.merge(address);
            em.getTransaction().commit();
        }
    }

    public void deleteAddress(Address address){
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.remove(em.merge(address));
            em.getTransaction().commit();
        }
    }

    public ZipCode readZipCode(int zip){
        ZipCode zipCode;
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            zipCode = em.find(ZipCode.class, zip);
        }
        return zipCode;
    }





}



