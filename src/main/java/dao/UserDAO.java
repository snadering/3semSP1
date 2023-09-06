package dao;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.User;

import java.util.List;

public class UserDAO {
    private final EntityManagerFactory emf;
    private static UserDAO instance;

    private UserDAO() {
        emf = HibernateConfig.getEntityManagerFactoryConfig();
    }

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }

        return instance;
    }

    public int createUser(User user){
        int id;
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            id = user.getId();
        }
        return id;
    }

    public int createUser(String name, String surname, String phoneNumber, String email){
        int id;
        User user = new User(name, surname, phoneNumber, email);
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            id = user.getId();
        }
        return id;
    }

    public User readUserById(int id){
        User user;
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            user = em.find(User.class,id);
        }
        return user;
    }

    public void updateUser (User user) {
        if (user.getId() != null){
            try(EntityManager em = emf.createEntityManager()){
                em.getTransaction().begin();
                em.merge(user);
                em.getTransaction().commit();
            }
        }
    }

    public void deleteUser(int id) {
        User user = readUserById(id);
        if (user.getId() != null) {
            try (EntityManager em = emf.createEntityManager()) {
                em.getTransaction().begin();
                em.remove(user);
                em.getTransaction().commit();
            }
        }
    }

    public List<String> getAllPhoneNumbersFromUserById(int id){
        List<String> listOfPhoneNumbers;
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            TypedQuery<String> typedQuery = em.createQuery("select u.phoneNumber from User u where id(u.id) = :id", String.class);
            typedQuery.setParameter("id",id);
            listOfPhoneNumbers = typedQuery.getResultList();
        }
        return listOfPhoneNumbers;
    }

    public User getAllInformationOnUserByPhoneNumber(String phoneNumber){
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            TypedQuery<User> typedQuery = em.createQuery("select u from User u where u.phoneNumber = :phoneNumber", User.class);
            typedQuery.setParameter("phoneNumber",phoneNumber);
             return typedQuery.setMaxResults(1).getSingleResult();
        }
    }
}
