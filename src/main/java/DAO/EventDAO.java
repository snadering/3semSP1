package DAO;

import config.HibernateConfig;
import jakarta.persistence.EntityManagerFactory;

public class EventDAO {
    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
}
