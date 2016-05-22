package com.hp.util;

import java.io.PrintStream;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory
{
  private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";
  private static final ThreadLocal<Session> threadLocal = new ThreadLocal();
  private static Configuration configuration = new Configuration();
  private static SessionFactory sessionFactory;
  private static String configFile = CONFIG_FILE_LOCATION;
  
  static
  {
    try
    {
      configuration.configure(configFile);
      sessionFactory = configuration.buildSessionFactory();
    }
    catch (Exception ex)
    {
      System.err.println("%%%Error Creating SessionFactory%%%");
      ex.printStackTrace();
    }
  }
  
  public static Session getSession()
    throws HibernateException
  {
    Session session = (Session)threadLocal.get();
    if ((session == null) || (!session.isOpen()))
    {
      if (sessionFactory == null) {
        rebuildSessionFactory();
      }
      session = sessionFactory != null ? sessionFactory.openSession() : null;
      threadLocal.set(session);
    }
    return session;
  }
  
  public static void rebuildSessionFactory()
  {
    try
    {
      configuration.configure(configFile);
      sessionFactory = configuration.buildSessionFactory();
    }
    catch (Exception ex)
    {
      System.err.println("%%%Error Creating SessionFactory%%%");
      ex.printStackTrace();
    }
  }
  
  public static void closeSession()
    throws HibernateException
  {
    Session session = (Session)threadLocal.get();
    threadLocal.set(null);
    if (session != null) {
      session.close();
    }
  }
  
  public static SessionFactory getSessionFctory()
  {
    return sessionFactory;
  }
  
  public static void setConfigFile(String configFile)
  {
    configFile = configFile;
    sessionFactory = null;
  }
  
  public static Configuration getConfiguration()
  {
    return configuration;
  }
}
