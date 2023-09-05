package DAO;

import config.HibernateConfig;
import jakarta.persistence.EntityManagerFactory;

public class AdressDAO {
    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
}
