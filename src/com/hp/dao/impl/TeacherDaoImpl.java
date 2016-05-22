package com.hp.dao.impl;

import com.hp.bean.ClaResultView;
import com.hp.bean.Course;
import com.hp.bean.Point;
import com.hp.bean.QuesView;
import com.hp.bean.Questions;
import com.hp.bean.StuSTestView;
import com.hp.bean.StuTestView;
import com.hp.bean.TeaCouView;
import com.hp.bean.TeaTestView;
import com.hp.bean.Teacher;
import com.hp.bean.TeacherView;
import com.hp.bean.Test;
import com.hp.dao.TeacherDao;
import com.hp.util.DBhelper;
import com.hp.util.HibernateSessionFactory;
import com.hp.util.MySessionFactory;
import com.hp.util.Page;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class TeacherDaoImpl
  implements TeacherDao
{
  public boolean teaLogin(int tea_id, String tea_password)
  {
    Transaction tx = null;
    String hql = "";
    try
    {
      Session session = MySessionFactory.getSessionFactory().getCurrentSession();
      tx = session.beginTransaction();
      hql = "from Teacher where tea_id=? and tea_password=?";
      Query query = session.createQuery(hql);
      query.setParameter(0, Integer.valueOf(tea_id));
      query.setParameter(1, tea_password);
      List list = query.list();
      tx.commit();
      if (list.size() > 0) {
        return true;
      }
      return false;
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      return false;
    }
    finally
    {
      if (tx != null) {
        tx = null;
      }
    }
  }
  
  public Questions findQueByTitle(String queTitle)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from Questions where q_title=? and q_exist=1 ORDER BY q_id DESC";
    Query query = session.createQuery(hql);
    query.setString(0, queTitle);
    List list = query.list();
    HibernateSessionFactory.closeSession();
    if (list.size() == 0) {
      return null;
    }
    return (Questions)list.get(0);
  }
  
  public boolean addQuestions(Questions que)
  {
    Session session = HibernateSessionFactory.getSession();
    Transaction tx = null;
    try
    {
      tx = session.beginTransaction();
      session.save(que);
      tx.commit();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      tx.rollback();
      return false;
    }
    HibernateSessionFactory.closeSession();
    return true;
  }
  
  public Questions findQueByID(int q_id)
  {
    Session session = HibernateSessionFactory.getSession();
    Questions que = (Questions)session.get(Questions.class, Integer.valueOf(q_id));
    HibernateSessionFactory.closeSession();
    return que;
  }
  
  public boolean updateQue(Questions que)
  {
    Session session = HibernateSessionFactory.getSession();
    Transaction tx = null;
    try
    {
      tx = session.beginTransaction();
      session.update(que);
      tx.commit();
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      tx.rollback();
      return false;
    }
    HibernateSessionFactory.closeSession();
    return true;
  }
  
  public boolean deleteQue(int q_id)
  {
    Session session = HibernateSessionFactory.getSession();
    Questions que = (Questions)session.get(Questions.class, Integer.valueOf(q_id));
    Transaction tx = null;
    try
    {
      tx = session.beginTransaction();
      session.delete(que);
      tx.commit();
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      return false;
    }
    HibernateSessionFactory.closeSession();
    return true;
  }
  
  public int findAllQueCountByTitle(String title)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from QuesView where q_exist=1 and q_title like :title";
    Query query = session.createQuery(hql);
    query.setString("title", "%" + title + "%");
    List list = query.list();
    int count = list.size();
    HibernateSessionFactory.closeSession();
    return count;
  }
  
  public int findAllQuesCount()
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from QuesView where q_exist=1";
    Query query = session.createQuery(hql);
    List list = query.list();
    int count = list.size();
    HibernateSessionFactory.closeSession();
    return count;
  }
  
  public List<QuesView> findAllQues(Page page)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from QuesView where q_exist=1 ORDER BY q_id DESC";
    Query query = session.createQuery(hql);
    query.setMaxResults(page.getEveryPage());
    query.setFirstResult(page.getBeginIndex());
    List list = query.list();
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public List<QuesView> likeFindQueByTitle(String q_title, Page page)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from QuesView where q_exist=1 and q_title like :title ORDER BY q_id DESC";
    Query query = session.createQuery(hql);
    query.setString("title", "%" + q_title + "%");
    query.setMaxResults(page.getEveryPage());
    query.setFirstResult(page.getBeginIndex());
    List list = query.list();
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public List<StuSTestView> findByStuName(Page page, String stu_name, int tea_id)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from StuSTestView where tea_id=? and stu_name like :title GROUP BY stu_test_id";
    Query query = session.createQuery(hql);
    query.setParameter(0, Integer.valueOf(tea_id));
    query.setString("title", "%" + stu_name + "%");
    query.setMaxResults(page.getEveryPage());
    query.setFirstResult(page.getBeginIndex());
    List list = query.list();
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public int findAllStuTestCount()
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from StuTestView ";
    Query query = session.createQuery(hql);
    List list = query.list();
    int count = list.size();
    HibernateSessionFactory.closeSession();
    return count;
  }
  
  public List<StuTestView> findAllStuTest(Page page)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from StuTestView";
    Query query = session.createQuery(hql);
    query.setMaxResults(page.getEveryPage());
    query.setFirstResult(page.getBeginIndex());
    List list = query.list();
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public List<StuSTestView> findByClaName(Page page, String cla_name, int tea_id)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from StuSTestView where tea_id=? and cla_name like :title GROUP BY stu_test_id";
    Query query = session.createQuery(hql);
    query.setParameter(0, Integer.valueOf(tea_id));
    query.setString("title", "%" + cla_name + "%");
    query.setMaxResults(page.getEveryPage());
    query.setFirstResult(page.getBeginIndex());
    List list = query.list();
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public List<StuSTestView> findByCouName(Page page, String cou_name, int tea_id)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from StuSTestView where tea_id=? and cou_name like :title GROUP BY stu_test_id";
    Query query = session.createQuery(hql);
    query.setParameter(0, Integer.valueOf(tea_id));
    query.setString("title", "%" + cou_name + "%");
    query.setMaxResults(page.getEveryPage());
    query.setFirstResult(page.getBeginIndex());
    List list = query.list();
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public TeacherView findTeaInfo(int tea_id)
  {
    Transaction tx = null;
    String hql = "";
    TeacherView teacher = new TeacherView();
    try
    {
      Session session = MySessionFactory.getSessionFactory().getCurrentSession();
      tx = session.beginTransaction();
      hql = "from TeacherView WHERE tea_id=?";
      Query query = session.createQuery(hql).setInteger(0, tea_id);
      TeacherView tea = (TeacherView)query.uniqueResult();
      
      tx.commit();
      return tea;
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      return null;
    }
    finally
    {
      if (tx != null) {
        tx = null;
      }
    }
  }
  
  public List<TeaTestView> findNearTest(int tea_id)
  {
    Transaction tx = null;
    List<TeaTestView> list = new ArrayList();
    String sql = "SELECT * FROM v_tea_test where test_time>date_add(now(), interval -1 day) and tea_id=?";
    try
    {
      Session session = MySessionFactory.getSessionFactory().getCurrentSession();
      tx = session.beginTransaction();
      Query query = session.createSQLQuery(sql);
      query.setParameter(0, Integer.valueOf(tea_id));
      List<Object[]> array = query.list();
      for (int i = 0; i < array.size(); i++)
      {
        Object[] obs = (Object[])array.get(i);
        TeaTestView ttv = new TeaTestView();
        ttv.setTest_id(((Integer)obs[0]).intValue());
        ttv.setTest_name((String)obs[1]);
        ttv.setCou_id(((Integer)obs[2]).intValue());
        ttv.setDep_id(((Integer)obs[3]).intValue());
        ttv.setTest_time((Date)obs[4]);
        ttv.setTest_ques((String)obs[5]);
        ttv.setTea_id(((Integer)obs[6]).intValue());
        ttv.setTea_name((String)obs[7]);
        ttv.setTea_sex((String)obs[8]);
        ttv.setTea_born((Date)obs[9]);
        ttv.setTea_password((String)obs[10]);
        ttv.setDep_name((String)obs[11]);
        ttv.setCou_name((String)obs[12]);
        list.add(ttv);
      }
      tx.commit();
      return list;
    }
    catch (Exception ex)
    {
      List<TeaTestView> localList1;
      ex.printStackTrace();
      tx.rollback();
      return list;
    }
    finally
    {
      if (tx != null) {
        tx = null;
      }
    }
  }
  
  public List<StuTestView> findByStuId(int stu_id)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from StuTestView where stu_id=?";
    Query query = session.createQuery(hql);
    query.setInteger(0, stu_id);
    List list = query.list();
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public List<TeaCouView> findAllTeach(int tea_id)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from TeaCouView WHERE tea_id=? GROUP BY cou_id";
    Query query = session.createQuery(hql);
    query.setInteger(0, tea_id);
    List<TeaCouView> list = query.list();
    
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public List<TeaCouView> findAllTeach2(int tea_id)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from TeaCouView WHERE tea_id=?";
    Query query = session.createQuery(hql);
    query.setInteger(0, tea_id);
    List<TeaCouView> list = query.list();
    
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public boolean updateTeaInfo(Teacher tea)
  {
    Transaction tx = null;
    try
    {
      Session session = MySessionFactory.getSessionFactory().getCurrentSession();
      tx = session.beginTransaction();
      session.update(tea);
      tx.commit();
      return true;
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      return false;
    }
    finally
    {
      if (tx != null) {
        tx = null;
      }
    }
  }
  
  public int findQueCountByCouName(String cou_name)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from QuesView where q_exist=1 and cou_name like :title";
    Query query = session.createQuery(hql);
    query.setString("title", "%" + cou_name + "%");
    List list = query.list();
    int count = list.size();
    HibernateSessionFactory.closeSession();
    return count;
  }
  
  public List<QuesView> findQueByCouName(String cou_name, Page page)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from QuesView where q_exist=1 and cou_name like :title ORDER BY q_id DESC";
    Query query = session.createQuery(hql);
    query.setString("title", "%" + cou_name + "%");
    query.setMaxResults(page.getEveryPage());
    query.setFirstResult(page.getBeginIndex());
    List list = query.list();
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public int findCountByStuName(String stu_name)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from StuTestView where stu_name like :title";
    Query query = session.createQuery(hql);
    query.setString("title", "%" + stu_name + "%");
    List list = query.list();
    int count = list.size();
    HibernateSessionFactory.closeSession();
    return count;
  }
  
  public int findCountByClaName(String cla_name)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from StuTestView where cla_name like :title";
    Query query = session.createQuery(hql);
    query.setString("title", "%" + cla_name + "%");
    List list = query.list();
    int count = list.size();
    
    HibernateSessionFactory.closeSession();
    return count;
  }
  
  public int findCountByCouName(String cou_name)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from StuTestView where cou_name like :title";
    Query query = session.createQuery(hql);
    query.setString("title", "%" + cou_name + "%");
    List list = query.list();
    int count = list.size();
    HibernateSessionFactory.closeSession();
    return count;
  }
  
  public List<Course> findAllCou(int dep_id)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from Course";
    Query query = session.createQuery(hql);
    
    List list = query.list();
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public List<Point> findAllPoi(int dep_id)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from Point";
    Query query = session.createQuery(hql);
    
    List list = query.list();
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public boolean addTest(Test test)
  {
    Session session = HibernateSessionFactory.getSession();
    Transaction tx = null;
    try
    {
      tx = session.beginTransaction();
      session.save(test);
      tx.commit();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      tx.rollback();
      return false;
    }
    HibernateSessionFactory.closeSession();
    return true;
  }
  
  public int findAllStuTestCountByTea(int tea_id)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from StuSTestView where tea_id=? GROUP BY stu_test_id";
    Query query = session.createQuery(hql);
    query.setParameter(0, Integer.valueOf(tea_id));
    List list = query.list();
    int count = list.size();
    HibernateSessionFactory.closeSession();
    
    return count;
  }
  
  public List<StuSTestView> findAllStuTestByTea(Page page, int tea_id)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from StuSTestView where tea_id=? GROUP BY stu_test_id ORDER BY cla_name ASC";
    Query query = session.createQuery(hql);
    query.setParameter(0, Integer.valueOf(tea_id));
    query.setMaxResults(page.getEveryPage());
    query.setFirstResult(page.getBeginIndex());
    List list = query.list();
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public List<Teacher> getTeacherAllByDb()
  {
    List<Teacher> list = new ArrayList();
    try
    {
      DBhelper db = new DBhelper();
      String sql = "select * from t_teacher";
      ResultSet rs = db.Search(sql, null);
      while (rs.next())
      {
        int tea_id = rs.getInt("tea_id");
        String tea_name = rs.getString("tea_name");
        String tea_password = rs.getString("tea_password");
        String tea_sex = rs.getString("tea_sex");
        Date tea_born = rs.getDate("tea_born");
        int dep_id = rs.getInt("dep_id");
        
        list.add(new Teacher(tea_id, tea_name, tea_password, tea_sex, 
          tea_born, dep_id));
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      return null;
    }
    return list;
  }
  
  public List<Teacher> getTeacherAllByExcel(String file)
  {
    List<Teacher> list_t_teacher = new ArrayList();
    try
    {
      Workbook rwb = Workbook.getWorkbook(new File(file));
      Sheet rs = rwb.getSheet(0);
      int clos = rs.getColumns();
      int rows = rs.getRows();
      
      System.out.println("clos:" + clos + "\t" + " rows:" + rows);
      for (int i = 1; i < rows; i++) {
        for (int j = 0; j < clos; j++)
        {
          String tea_id = rs.getCell(j++, i).getContents();
          
          String tea_name = rs.getCell(j++, i).getContents();
          String tea_password = rs.getCell(j++, i).getContents();
          String tea_sex = rs.getCell(j++, i).getContents();
          String tea_born = rs.getCell(j++, i).getContents();
          String dep_id = rs.getCell(j++, i).getContents();
          
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
          Date tea_born_d = new Date();
          try
          {
            tea_born_d = sdf.parse(tea_born);
          }
          catch (ParseException e)
          {
            e.printStackTrace();
          }
          System.out.println("Teacher [tea_id=" + tea_id + 
            ", tea_name=" + tea_name + ", tea_password=" + 
            tea_password + ", tea_sex=" + tea_sex + 
            ", tea_born=" + tea_born + ", dep_id=" + dep_id + 
            "]");
          list_t_teacher.add(new Teacher(Integer.parseInt(tea_id), 
            tea_name, tea_password, tea_sex, tea_born_d, 
            Integer.parseInt(dep_id)));
        }
      }
    }
    catch (BiffException e)
    {
      e.printStackTrace();
      return null;
    }
    catch (IOException e)
    {
      e.printStackTrace();
      return null;
    }
    return list_t_teacher;
  }
  
  public boolean isTeacherExist(int tea_id)
  {
    try
    {
      DBhelper db = new DBhelper();
      ResultSet rs = db.Search("select * from t_teacher where tea_id=?", 
        new String[] { String.valueOf(tea_id )});
      if (rs.next()) {
        return true;
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      return false;
    }
    return false;
  }
  
  public List<TeaCouView> findAllTeaCla(int tea_id)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from TeaCouView where tea_id=? GROUP BY cla_name";
    Query query = session.createQuery(hql);
    query.setParameter(0, Integer.valueOf(tea_id));
    List list = query.list();
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public int findAllClaResultCount(int tea_id, int cla_id, int cou_id, int test_id)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from ClaResultView where tea_id=? and cla_id=? and cou_id=? AND test_id=? GROUP BY stu_test_id";
    Query query = session.createQuery(hql);
    query.setParameter(0, Integer.valueOf(tea_id));
    query.setParameter(1, Integer.valueOf(cla_id));
    query.setParameter(2, Integer.valueOf(cou_id));
    query.setParameter(3, Integer.valueOf(test_id));
    List list = query.list();
    int count = list.size();
    
    HibernateSessionFactory.closeSession();
    return count;
  }
  
  public List<ClaResultView> findAllClaResult(Page page, int tea_id, int cla_id, int cou_id, int test_id)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from ClaResultView where tea_id=? and cla_id=? and cou_id=? AND test_id=?  GROUP BY stu_test_id";
    Query query = session.createQuery(hql);
    query.setParameter(0, Integer.valueOf(tea_id));
    query.setParameter(1, Integer.valueOf(cla_id));
    query.setParameter(2, Integer.valueOf(cou_id));
    query.setParameter(3, Integer.valueOf(test_id));
    query.setMaxResults(page.getEveryPage());
    query.setFirstResult(page.getBeginIndex());
    List list = query.list();
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public List<ClaResultView> findAllCla(int tea_id)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from ClaResultView where tea_id=? GROUP BY test_id,cla_id";
    Query query = session.createQuery(hql);
    query.setParameter(0, Integer.valueOf(tea_id));
    List list = query.list();
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public Point findPoiById(int poi_id)
  {
    Session session = HibernateSessionFactory.getSession();
    Point que = (Point)session.get(Point.class, Integer.valueOf(poi_id));
    HibernateSessionFactory.closeSession();
    return que;
  }
}
