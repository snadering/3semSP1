package dao;

import config.HibernateConfig;
import dto.HobbyAndInterest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.Event;
import model.Hobby;
import model.User;

import java.util.List;

public class HobbyDAO {
    private final EntityManagerFactory emf;
    private static HobbyDAO instance;

    private HobbyDAO() {
        emf = HibernateConfig.getEntityManagerFactoryConfig();
    }

    public static HobbyDAO getInstance() {
        if (instance == null) {
            instance = new HobbyDAO();
        }

        return instance;
    }

    public Hobby readHobbyById(int id){
        Hobby hobby;
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            hobby = em.find(Hobby.class, id);
            em.getTransaction().commit();
        }
        return hobby;
    }

    public List<User> allUsersWithGivenHobby(int id){
        List<User> usersWithGivenHobby;
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            TypedQuery<User> typedQuery = em.createQuery("select h.users from Hobby h where h.id = :id", User.class);
            typedQuery.setParameter("id", id);
            usersWithGivenHobby = typedQuery.getResultList();
        }
        return usersWithGivenHobby;
    }

    public int numberOfUsersWithGivenHobby(int id){
        int amountOfUsersWithGivenHobby;
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            TypedQuery<Integer> typedQuery = em.createQuery("select size(h.users) from Hobby h where h.id = :id", Integer.class);
            typedQuery.setParameter("id", id);
            amountOfUsersWithGivenHobby = typedQuery.getSingleResult();
        }
        return amountOfUsersWithGivenHobby;
    }

    public List<HobbyAndInterest> getAllHobbiesAndAmountOfInterested(){
        List<HobbyAndInterest> hobbiesAndInterested;
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            String jpql = "SELECT new dto.HobbyAndInterest (h.name, COUNT(u)) " + "FROM User u " + "JOIN u.hobbies h " + "GROUP BY h.name";

            TypedQuery<HobbyAndInterest> typedQuery = em.createQuery(jpql, HobbyAndInterest.class);
            hobbiesAndInterested = typedQuery.getResultList();
        }
        return hobbiesAndInterested;
    }
}
