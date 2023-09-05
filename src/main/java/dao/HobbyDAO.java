package dao;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Hobby;

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

}
