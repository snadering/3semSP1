package dao;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.User;

import java.util.List;

public class UserDAO {

    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    public void createUser(User user){
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        }
    }

    public void createUser(String name, String surname, String phoneNumber, String email){
        User user = new User(name, surname, phoneNumber, email);
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        }
    }

    public User findUserById(int id){
        User user;
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            user = em.find(User.class,id);
        }
        return user;
    }

    public User updateUser (User user) {
        User updatedUser = null;
        if (user.getId() != null){
            try(EntityManager em = emf.createEntityManager()){
                em.getTransaction().begin();
                updatedUser = em.merge(user);
                em.getTransaction().commit();
            }
        }
        return updatedUser;
    }

    public void deleteUser(int id) {
        User user = findUserById(id);
        if (user.getId() != null) {
            try (EntityManager em = emf.createEntityManager()) {
                em.getTransaction().begin();
                em.remove(user);
                em.getTransaction().commit();
            }
        }
    }

    public List<User> getAllInformationOnUser(int id){
        List<User> userInformationList;
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            TypedQuery<User> typedQuery = em.createQuery("select u from User u where id(u.id) = :id", User.class);
            typedQuery.setParameter("id",id);
            userInformationList = typedQuery.getResultList();
        }
        return userInformationList;
    }

    public List<User> getAllPhoneNumbersFromUserById(int id){
        List<User> userPhoneNumberList;
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            TypedQuery<User> typedQuery = em.createQuery("select u.phoneNumber from User u where id(u.id) = :id", User.class);
            typedQuery.setParameter("id",id);
            userPhoneNumberList = typedQuery.getResultList();
        }
        return userPhoneNumberList;
    }

    public List<User> getAllInformationOnUserByPhoneNumber(String phoneNumber){
        List<User> userInformationList;
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            TypedQuery<User> typedQuery = em.createQuery("select u from User u where phoneNumber(u.phoneNumber) = :phoneNumber", User.class);
            typedQuery.setParameter("phoneNumber",phoneNumber);
            userInformationList = typedQuery.getResultList();
        }
        return userInformationList;
    }
}
