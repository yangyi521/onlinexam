package com.hp.dao.impl;

import com.hp.bean.Classes;
import com.hp.bean.CourseView;
import com.hp.bean.Department;
import com.hp.bean.QuesView;
import com.hp.bean.Questions;
import com.hp.bean.StuTest;
import com.hp.bean.StuTestSimpleView;
import com.hp.bean.StuTestView;
import com.hp.bean.Student;
import com.hp.bean.StudentView;
import com.hp.bean.Test;
import com.hp.bean.TestView;
import com.hp.dao.StudentDao;
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
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class StudentDaoImpl
  implements StudentDao
{
  public boolean stuLogin(int stu_id, String stu_password)
  {
    Transaction tx = null;
    String hql = "";
    try
    {
      Session session = MySessionFactory.getSessionFactory().getCurrentSession();
      tx = session.beginTransaction();
      hql = "from Student where stu_id=? and stu_password=?";
      Query query = session.createQuery(hql);
      query.setParameter(0, Integer.valueOf(stu_id));
      query.setParameter(1, stu_password);
      Student list = (Student)query.uniqueResult();
      tx.commit();
      if (list != null) {
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
  
  public List<TestView> findNearTest(StudentView stu, Date nowDate)
  {
    Transaction tx = null;
    List<TestView> list = new ArrayList();
    String sql = "";
    try
    {
      Session session = MySessionFactory.getSessionFactory().getCurrentSession();
      tx = session.beginTransaction();
      sql = "SELECT * from v_test where test_time>date_add(now(), interval -1 day) and test_id NOT IN (SELECT test_id FROM t_stu_test WHERE stu_id=?)  and test_clas LIKE ?";
      Query query = session.createSQLQuery(sql);
      query.setParameter(0, Integer.valueOf(stu.getStu_id()));
      query.setParameter(1, "%" + stu.getCla_id() + "%");
      System.out.println("班级ID：" + stu.getCla_id());
      List<Object[]> array = query.list();
      for (int i = 0; i < array.size(); i++)
      {
        Object[] obs = (Object[])array.get(i);
        TestView tv = new TestView();
        tv.setTest_id(((Integer)obs[0]).intValue());
        tv.setTest_name((String)obs[1]);
        tv.setCou_name((String)obs[2]);
        tv.setDep_name((String)obs[3]);
        tv.setTest_time((Date)obs[4]);
        tv.setDep_id(((Integer)obs[5]).intValue());
        tv.setTest_ques((String)obs[6]);
        tv.setCou_id(((Integer)obs[7]).intValue());
        tv.setTest_clas((String)obs[8]);
        list.add(tv);
      }
      tx.commit();
      return list;
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      tx.commit();
      return null;
    }
    finally
    {
      if (tx != null) {
        tx = null;
      }
    }
  }
  
  public TestView stuExam(int test_id)
  {
    Transaction tx = null;
    String sql = "";
    TestView test = new TestView();
    try
    {
      Session session = MySessionFactory.getSessionFactory().getCurrentSession();
      tx = session.beginTransaction();
      sql = "SELECT * FROM v_test WHERE test_id=?";
      Query query = session.createSQLQuery(sql).setInteger(0, test_id);
      Object[] o = (Object[])query.uniqueResult();
      test.setTest_id(((Integer)o[0]).intValue());
      test.setTest_name((String)o[1]);
      test.setCou_name((String)o[2]);
      test.setDep_name((String)o[3]);
      test.setTest_time((Date)o[4]);
      test.setDep_id(((Integer)o[5]).intValue());
      test.setTest_ques((String)o[6]);
      test.setCou_id(((Integer)o[7]).intValue());
      tx.commit();
      return test;
    }
    catch (Exception ex)
    {
      TestView localTestView1;
      ex.printStackTrace();
      return test;
    }
    finally
    {
      if (tx != null) {
        tx = null;
      }
    }
  }
  
  public StuTest testResult(Test test)
  {
    return null;
  }
  
  public List<StuTestSimpleView> findAllTest(Page page, int stu_id)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from StuTestSimpleView where stu_id=? ";
    Query query = session.createQuery(hql);
    query.setInteger(0, stu_id);
    query.setMaxResults(page.getEveryPage());
    query.setFirstResult(page.getBeginIndex());
    List list = query.list();
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public List<StuTestSimpleView> findTestByCourse(Page page, int cou_id, int stu_id)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from StuTestSimpleView where cou_id=? and stu_id=?";
    Query query = session.createQuery(hql);
    query.setInteger(0, cou_id);
    query.setInteger(1, stu_id);
    query.setMaxResults(page.getEveryPage());
    query.setFirstResult(page.getBeginIndex());
    List list = query.list();
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public StuTestView findDetailedTest(int stu_test_id)
  {
    Transaction tx = null;
    String sql = "";
    StuTestView stuTest = new StuTestView();
    try
    {
      Session session = MySessionFactory.getSessionFactory().getCurrentSession();
      tx = session.beginTransaction();
      sql = "SELECT * FROM v_stu_test where stu_test_id=?";
      Query query = session.createSQLQuery(sql).setInteger(0, stu_test_id);
      
      Object[] o = (Object[])query.uniqueResult();
      
      stuTest.setStu_test_id(((Integer)o[0]).intValue());
      stuTest.setCou_name((String)o[1]);
      stuTest.setStu_test_evaluate((String)o[2]);
      stuTest.setTest_name((String)o[3]);
      stuTest.setTest_ques((String)o[4]);
      stuTest.setStu_name((String)o[5]);
      stuTest.setCla_name((String)o[6]);
      
      stuTest.setStu_test_score(((Double)o[7]).doubleValue());
      stuTest.setStu_wrong_ques((String)o[8]);
      stuTest.setStu_wrong_ans((String)o[9]);
      stuTest.setTest_id(((Integer)o[10]).intValue());
      stuTest.setCou_id(((Integer)o[11]).intValue());
      stuTest.setStu_id(((Integer)o[12]).intValue());
      stuTest.setStu_test_time((String)o[13]);
      tx.commit();
      return stuTest;
    }
    catch (Exception ex)
    {
      StuTestView localStuTestView1;
      ex.printStackTrace();
      return stuTest;
    }
    finally
    {
      if (tx != null) {
        tx = null;
      }
    }
  }
  
  public StudentView findStuInfo(int stu_id)
  {
    Transaction tx = null;
    String sql = "";
    StudentView student = new StudentView();
    try
    {
      Session session = MySessionFactory.getSessionFactory().getCurrentSession();
      tx = session.beginTransaction();
      sql = "SELECT * FROM v_student_view WHERE stu_id=?";
      Query query = session.createSQLQuery(sql).setInteger(0, stu_id);
      
      Object[] o = (Object[])query.uniqueResult();
      
      student.setStu_id(((Integer)o[0]).intValue());
      student.setStu_name((String)o[1]);
      student.setCla_name((String)o[2]);
      student.setDep_name((String)o[3]);
      student.setStu_password((String)o[4]);
      student.setCla_id(((Integer)o[5]).intValue());
      student.setDep_id(((Integer)o[6]).intValue());
      student.setStu_sex((String)o[7]);
      student.setStu_born((Date)o[8]);
      tx.commit();
      return student;
    }
    catch (Exception ex)
    {
      StudentView localStudentView1;
      ex.printStackTrace();
      return student;
    }
    finally
    {
      if (tx != null) {
        tx = null;
      }
    }
  }
  
  public boolean updateStuInfo(Student stu)
  {
    Transaction tx = null;
    try
    {
      Session session = MySessionFactory.getSessionFactory().getCurrentSession();
      tx = session.beginTransaction();
      session.update(stu);
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
  
  public List<Department> findAllDep()
  {
    Transaction tx = null;
    List<Department> list = new ArrayList();
    String sql = "";
    try
    {
      Session session = MySessionFactory.getSessionFactory().getCurrentSession();
      tx = session.beginTransaction();
      sql = "SELECT * FROM t_department";
      Query query = session.createSQLQuery(sql);
      List<Object[]> array = query.list();
      for (int i = 0; i < array.size(); i++)
      {
        Object[] obs = (Object[])array.get(i);
        Department dep = new Department();
        dep.setDep_id(((Integer)obs[0]).intValue());
        dep.setDep_name((String)obs[1]);
        list.add(dep);
      }
      tx.commit();
      return list;
    }
    catch (Exception ex)
    {
      List<Department> localList1;
      ex.printStackTrace();
      tx.commit();
      return list;
    }
    finally
    {
      if (tx != null) {
        tx = null;
      }
    }
  }
  
  public List<Classes> findAllClass(int dep_id)
  {
    Transaction tx = null;
    List<Classes> list = new ArrayList();
    String sql = "";
    try
    {
      Session session = MySessionFactory.getSessionFactory().getCurrentSession();
      tx = session.beginTransaction();
      sql = "SELECT * FROM t_class WHERE dep_id=?";
      Query query = session.createSQLQuery(sql);
      query.setParameter(0, Integer.valueOf(dep_id));
      List<Object[]> array = query.list();
      for (int i = 0; i < array.size(); i++)
      {
        Object[] obs = (Object[])array.get(i);
        Classes cla = new Classes();
        cla.setCla_id(((Integer)obs[0]).intValue());
        cla.setCla_name((String)obs[1]);
        cla.setDep_id(((Integer)obs[2]).intValue());
        list.add(cla);
      }
      tx.commit();
      return list;
    }
    catch (Exception ex)
    {
      List<Classes> localList1;
      ex.printStackTrace();
      tx.commit();
      return list;
    }
    finally
    {
      if (tx != null) {
        tx = null;
      }
    }
  }
  
  public Questions findQuesInfo(int q_id)
  {
    Transaction tx = null;
    Questions que = new Questions();
    String sql = "";
    try
    {
      Session session = MySessionFactory.getSessionFactory().getCurrentSession();
      tx = session.beginTransaction();
      sql = "SELECT * FROM t_questions WHERE q_id=?";
      Query query = session.createSQLQuery(sql);
      query.setParameter(0, Integer.valueOf(q_id));
      Object[] o = (Object[])query.uniqueResult();
      que.setQ_id(((Integer)o[0]).intValue());
      que.setQ_type(((Integer)o[1]).intValue());
      que.setQ_exist(((Byte)o[2]).byteValue());
      que.setQ_title((String)o[3]);
      que.setQ_itemA((String)o[4]);
      que.setQ_itemB((String)o[5]);
      que.setQ_itemC((String)o[6]);
      que.setQ_itemD((String)o[7]);
      que.setQ_ans((String)o[8]);
      que.setPoi_id(((Integer)o[9]).intValue());
      que.setCou_id(((Integer)o[10]).intValue());
      tx.commit();
      return que;
    }
    catch (Exception ex)
    {
      Questions localQuestions1;
      ex.printStackTrace();
      tx.commit();
      return que;
    }
    finally
    {
      if (tx != null) {
        tx = null;
      }
    }
  }
  
  public boolean addStuTest(StuTest stut)
  {
    Session session = HibernateSessionFactory.getSession();
    Transaction tx = null;
    try
    {
      tx = session.beginTransaction();
      session.save(stut);
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
  
  public QuesView findQuesByQueId(int q_id)
  {
    Transaction tx = null;
    String sql = "";
    try
    {
      Session session = MySessionFactory.getSessionFactory().getCurrentSession();
      tx = session.beginTransaction();
      sql = "from QuesView WHERE q_id=?";
      Query query = session.createQuery(sql);
      query.setParameter(0, Integer.valueOf(q_id));
      QuesView que = (QuesView)query.uniqueResult();
      tx.commit();
      return que;
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      tx.commit();
      return null;
    }
    finally
    {
      if (tx != null) {
        tx = null;
      }
    }
  }
  
  public int findAllTestCount(int stu_id)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from StuTestSimpleView where stu_id=?";
    Query query = session.createQuery(hql);
    query.setInteger(0, stu_id);
    List list = query.list();
    int count = list.size();
    HibernateSessionFactory.closeSession();
    return count;
  }
  
  public int findTestCountByCourse(int cou_id)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from StuTestSimpleView where cou_id=? ";
    Query query = session.createQuery(hql);
    query.setInteger(0, cou_id);
    List list = query.list();
    int count = list.size();
    HibernateSessionFactory.closeSession();
    return count;
  }
  
  public int findCouCountByDepId(int dep_id)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from CourseView where dep_id=? ";
    Query query = session.createQuery(hql);
    query.setInteger(0, dep_id);
    List list = query.list();
    int count = list.size();
    HibernateSessionFactory.closeSession();
    return count;
  }
  
  public List<CourseView> findCouByDepId(int dep_id)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from CourseView where dep_id=? GROUP BY cou_id";
    Query query = session.createQuery(hql);
    query.setInteger(0, dep_id);
    List list = query.list();
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public List<Student> getStudentAllByDb()
  {
    List<Student> list = new ArrayList();
    try
    {
      DBhelper db = new DBhelper();
      String sql = "select * from t_student";
      ResultSet rs = db.Search(sql, null);
      while (rs.next())
      {
        int stu_id = rs.getInt("stu_id");
        String stu_name = rs.getString("stu_name");
        String stu_password = rs.getString("stu_password");
        String stu_sex = rs.getString("stu_sex");
        Date stu_born = rs.getDate("stu_born");
        int cla_id = rs.getInt("cla_id");
        int dep_id = rs.getInt("dep_id");
        
        list.add(new Student(stu_id, stu_name, stu_password, stu_sex, 
          stu_born, cla_id, dep_id));
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      return null;
    }
    return list;
  }
  
  public List<Student> getStudentAllByExcel(String file)
  {
    List<Student> list_t_student = new ArrayList();
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
          String stu_id = rs.getCell(j++, i).getContents();
          
          String stu_name = rs.getCell(j++, i).getContents();
          String stu_password = rs.getCell(j++, i).getContents();
          String stu_sex = rs.getCell(j++, i).getContents();
          String stu_born = rs.getCell(j++, i).getContents();
          String cla_id = rs.getCell(j++, i).getContents();
          String dep_id = rs.getCell(j++, i).getContents();
          
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
          Date stu_born_d = new Date();
          try
          {
            stu_born_d = sdf.parse(stu_born);
          }
          catch (ParseException e)
          {
            e.printStackTrace();
          }
          System.out.println("Student [stu_id=" + stu_id + 
            ", stu_name=" + stu_name + ", stu_password=" + 
            stu_password + ", stu_sex=" + stu_sex + 
            ", stu_born=" + stu_born + ", cla_id=" + cla_id + 
            ", dep_id=" + dep_id + "]");
          list_t_student
            .add(new Student(Integer.parseInt(stu_id), 
            stu_name, stu_password, stu_sex, 
            stu_born_d, Integer.parseInt(cla_id), 
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
    return list_t_student;
  }
  
  public boolean isStudentExist(int stu_id)
  {
    try
    {
      DBhelper db = new DBhelper();
      ResultSet rs = db.Search("select * from t_student where stu_id=?", 
        new String[] { String.valueOf(stu_id)});
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
}

