package com.hp.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class MySessionFactory
{
  private static SessionFactory sessionFactory;
  
  public static SessionFactory getSessionFactory()
  {
    if (sessionFactory == null)
    {
      Configuration config = new Configuration().configure();
      ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
      sessionFactory = config.buildSessionFactory(serviceRegistry);
      
      return sessionFactory;
    }
    return sessionFactory;
  }
}
