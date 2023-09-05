package dao;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.Hobby;
import model.User;

import java.util.List;

public class HobbyDAO {
private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

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
            TypedQuery<User> typedQuery = em.createQuery("select h.users from Hobby h where id(h.id) = :id", User.class);
            usersWithGivenHobby = typedQuery.getResultList();
    }
       return usersWithGivenHobby;
}

    public int numberOfUsersWithGivenHobby(int id){
        int amountOfUsersWithGivenHobby;
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            TypedQuery<Integer> typedQuery = em.createQuery("select h.users from Hobby h where id(h.id) = :id", Integer.class);
            amountOfUsersWithGivenHobby = typedQuery.getSingleResult();
        }
        return amountOfUsersWithGivenHobby;
    }

}
