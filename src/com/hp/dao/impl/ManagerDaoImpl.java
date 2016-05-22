package com.hp.dao.impl;

import com.hp.bean.Classes;
import com.hp.bean.ClassesView;
import com.hp.bean.Course;
import com.hp.bean.CourseView;
import com.hp.bean.Department;
import com.hp.bean.Manager;
import com.hp.bean.Student;
import com.hp.bean.StudentView;
import com.hp.bean.TeaCouView;
import com.hp.bean.TeachCourse;
import com.hp.bean.Teacher;
import com.hp.bean.TeacherView;
import com.hp.dao.ManagerDao;
import com.hp.util.HibernateSessionFactory;
import com.hp.util.MySessionFactory;
import com.hp.util.Page;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ManagerDaoImpl
  implements ManagerDao
{
  public boolean sysLogin(String sys_account, String sys_password)
  {
    Transaction tx = null;
    String hql = "";
    try
    {
      Session session = MySessionFactory.getSessionFactory().getCurrentSession();
      tx = session.beginTransaction();
      hql = "from Manager where sys_account=? and sys_password=?";
      Query query = session.createQuery(hql);
      query.setParameter(0, sys_account);
      query.setParameter(1, sys_password);
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
  
  public Manager findManagerInfo(String account)
  {
    Transaction tx = null;
    String sql = "";
    try
    {
      Session session = MySessionFactory.getSessionFactory().getCurrentSession();
      tx = session.beginTransaction();
      sql = "from Manager where sys_account=?";
      Query query = session.createQuery(sql).setString(0, account);
      
      Manager sys = (Manager)query.uniqueResult();
      tx.commit();
      return sys;
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
  
  public int findAllCourseCount()
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from CourseView ORDER BY cla_id ASC";
    Query query = session.createQuery(hql);
    List list = query.list();
    int count = list.size();
    HibernateSessionFactory.closeSession();
    return count;
  }
  
  public List<CourseView> findAllCourse(Page page)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from CourseView ORDER BY cla_id ASC";
    Query query = session.createQuery(hql);
    query.setMaxResults(page.getEveryPage());
    query.setFirstResult(page.getBeginIndex());
    List list = query.list();
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public int findCourseCountByDep(String dep_name)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from CourseView where dep_name like :title";
    Query query = session.createQuery(hql);
    query.setString("title", "%" + dep_name + "%");
    List list = query.list();
    int count = list.size();
    HibernateSessionFactory.closeSession();
    return count;
  }
  
  public List<CourseView> findCourseByDep(Page page, String dep_name)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from CourseView where dep_name like :title";
    Query query = session.createQuery(hql);
    query.setString("title", "%" + dep_name + "%");
    query.setMaxResults(page.getEveryPage());
    query.setFirstResult(page.getBeginIndex());
    List list = query.list();
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public int findCourseCountByCouName(String cou_name)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from CourseView where cou_name like :title";
    Query query = session.createQuery(hql);
    query.setString("title", "%" + cou_name + "%");
    List list = query.list();
    int count = list.size();
    HibernateSessionFactory.closeSession();
    return count;
  }
  
  public List<CourseView> findCourseByCouName(Page page, String cou_name)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from CourseView where cou_name like :title";
    Query query = session.createQuery(hql);
    query.setString("title", "%" + cou_name + "%");
    query.setMaxResults(page.getEveryPage());
    query.setFirstResult(page.getBeginIndex());
    List list = query.list();
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public boolean updateManagerInfo(Manager sys)
  {
    Transaction tx = null;
    try
    {
      Session session = MySessionFactory.getSessionFactory().getCurrentSession();
      tx = session.beginTransaction();
      session.update(sys);
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
  
  public int findAllTeaCount()
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from TeacherView";
    Query query = session.createQuery(hql);
    List list = query.list();
    int count = list.size();
    HibernateSessionFactory.closeSession();
    return count;
  }
  
  public List<TeacherView> findAllTea(Page page)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from TeacherView";
    Query query = session.createQuery(hql);
    query.setMaxResults(page.getEveryPage());
    query.setFirstResult(page.getBeginIndex());
    List list = query.list();
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public int findTeaCountByCouName(String cou_name)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from TeaCouView where cou_name like :title";
    Query query = session.createQuery(hql);
    query.setString("title", "%" + cou_name + "%");
    List list = query.list();
    int count = list.size();
    HibernateSessionFactory.closeSession();
    return count;
  }
  
  public List<TeaCouView> findTeaByCouName(Page page, String cou_name)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from TeaCouView where cou_name like :title GROUP BY tea_id";
    Query query = session.createQuery(hql);
    query.setString("title", "%" + cou_name + "%");
    query.setMaxResults(page.getEveryPage());
    query.setFirstResult(page.getBeginIndex());
    List list = query.list();
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public int findTeaCountByTeaName(String tea_name)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from TeacherView where tea_name like :title";
    Query query = session.createQuery(hql);
    query.setString("title", "%" + tea_name + "%");
    List list = query.list();
    int count = list.size();
    HibernateSessionFactory.closeSession();
    return count;
  }
  
  public List<TeacherView> findTeaByTeaName(Page page, String tea_name)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from TeacherView where tea_name like :title";
    Query query = session.createQuery(hql);
    query.setString("title", "%" + tea_name + "%");
    query.setMaxResults(page.getEveryPage());
    query.setFirstResult(page.getBeginIndex());
    List list = query.list();
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public int findTeaCountByTeaId(int tea_id)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from TeacherView where tea_id=?";
    Query query = session.createQuery(hql);
    query.setInteger(0, tea_id);
    List list = query.list();
    int count = list.size();
    HibernateSessionFactory.closeSession();
    return count;
  }
  
  public TeacherView findTeaByTeaId(Page page, int tea_id)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from TeacherView where tea_id=?";
    Query query = session.createQuery(hql);
    query.setInteger(0, tea_id);
    query.setMaxResults(page.getEveryPage());
    query.setFirstResult(page.getBeginIndex());
    TeacherView tea = (TeacherView)query.uniqueResult();
    HibernateSessionFactory.closeSession();
    return tea;
  }
  
  public boolean deleteTeacher(int tea_id)
  {
    Session session = HibernateSessionFactory.getSession();
    Teacher que = (Teacher)session.get(Teacher.class, Integer.valueOf(tea_id));
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
  
  public int findAllStuCount()
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from StudentView";
    Query query = session.createQuery(hql);
    List list = query.list();
    int count = list.size();
    HibernateSessionFactory.closeSession();
    return count;
  }
  
  public List<StudentView> findAllStu(Page page)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from StudentView";
    Query query = session.createQuery(hql);
    query.setMaxResults(page.getEveryPage());
    query.setFirstResult(page.getBeginIndex());
    List list = query.list();
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public int findStuCountByClaName(String cla_name)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from StudentView where cla_name like :title";
    Query query = session.createQuery(hql);
    query.setString("title", "%" + cla_name + "%");
    List list = query.list();
    int count = list.size();
    HibernateSessionFactory.closeSession();
    return count;
  }
  
  public List<StudentView> findStuByClaName(Page page, String cla_name)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from StudentView where cla_name like :title";
    Query query = session.createQuery(hql);
    query.setString("title", "%" + cla_name + "%");
    query.setMaxResults(page.getEveryPage());
    query.setFirstResult(page.getBeginIndex());
    List list = query.list();
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public int findStuCountByStuName(String stu_name)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from StudentView where stu_name like :title";
    Query query = session.createQuery(hql);
    query.setString("title", "%" + stu_name + "%");
    List list = query.list();
    int count = list.size();
    HibernateSessionFactory.closeSession();
    return count;
  }
  
  public List<StudentView> findStuByStuName(Page page, String stu_name)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from StudentView where stu_name like :title";
    Query query = session.createQuery(hql);
    query.setString("title", "%" + stu_name + "%");
    query.setMaxResults(page.getEveryPage());
    query.setFirstResult(page.getBeginIndex());
    List list = query.list();
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public int findStuCountByStuId(int stu_id)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from StudentView where stu_id=?";
    Query query = session.createQuery(hql);
    query.setInteger(0, stu_id);
    List list = query.list();
    int count = list.size();
    HibernateSessionFactory.closeSession();
    return count;
  }
  
  public StudentView findStuByStuId(Page page, int stu_id)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from StudentView where stu_id=?";
    Query query = session.createQuery(hql);
    query.setInteger(0, stu_id);
    query.setMaxResults(page.getEveryPage());
    query.setFirstResult(page.getBeginIndex());
    StudentView tea = (StudentView)query.uniqueResult();
    HibernateSessionFactory.closeSession();
    return tea;
  }
  
  public boolean deleteStudent(int stu_id)
  {
    Session session = HibernateSessionFactory.getSession();
    Student que = (Student)session.get(Student.class, Integer.valueOf(stu_id));
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
  
  public boolean deleteCourse(int cou_id)
  {
    Session session = HibernateSessionFactory.getSession();
    Course que = (Course)session.get(Course.class, Integer.valueOf(cou_id));
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
  
  public List<Classes> findSimpleCla()
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from Classes";
    Query query = session.createQuery(hql);
    List list = query.list();
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public List<Department> findSimpleDep()
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from Department";
    Query query = session.createQuery(hql);
    List list = query.list();
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public List<Teacher> findSimpleTea()
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from Teacher";
    Query query = session.createQuery(hql);
	List<Teacher> list = query.list();
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public boolean addCourse(Course cou)
  {
    Session session = HibernateSessionFactory.getSession();
    Transaction tx = null;
    try
    {
      tx = session.beginTransaction();
      session.save(cou);
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
  
  public boolean addTeaCou(TeachCourse tcv)
  {
    Session session = HibernateSessionFactory.getSession();
    Transaction tx = null;
    try
    {
      tx = session.beginTransaction();
      session.save(tcv);
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
  
  public Course findCourseByName(String cou_name)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from Course where cou_name like :title";
    Query query = session.createQuery(hql);
    query.setString("title", "%" + cou_name + "%");
    Course list = (Course)query.uniqueResult();
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public boolean addTeacher(Teacher tea)
  {
    Session session = HibernateSessionFactory.getSession();
    Transaction tx = null;
    try
    {
      tx = session.beginTransaction();
      session.save(tea);
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
  
  public boolean addStudent(Student stu)
  {
    Session session = HibernateSessionFactory.getSession();
    Transaction tx = null;
    try
    {
      tx = session.beginTransaction();
      session.save(stu);
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
  
  public boolean updateTeacher(Teacher tea)
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
  
  public boolean updateStudent(Student stu)
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
  
  public CourseView findCouByCouId(int cou_id)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from CourseView where cou_id=?";
    Query query = session.createQuery(hql);
    query.setInteger(0, cou_id);
    CourseView list = (CourseView)query.uniqueResult();
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public boolean updateCourse(Course cou)
  {
    Transaction tx = null;
    try
    {
      Session session = MySessionFactory.getSessionFactory().getCurrentSession();
      tx = session.beginTransaction();
      session.update(cou);
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
  
  public boolean updateCouView(CourseView couv)
  {
    Transaction tx = null;
    try
    {
      Session session = MySessionFactory.getSessionFactory().getCurrentSession();
      tx = session.beginTransaction();
      session.update(couv);
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
  
  public boolean updateTeachCou(TeachCourse tc)
  {
    Transaction tx = null;
    try
    {
      Session session = MySessionFactory.getSessionFactory().getCurrentSession();
      tx = session.beginTransaction();
      session.update(tc);
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
  
  public TeachCourse findTeaCou(int cou_id, int tea_id)
  {
    Transaction tx = null;
    String hql = "";
    try
    {
      Session session = MySessionFactory.getSessionFactory().getCurrentSession();
      tx = session.beginTransaction();
      hql = "from TeachCourse where cou_id=? and tea_id=?";
      Query query = session.createQuery(hql);
      query.setParameter(0, Integer.valueOf(cou_id));
      query.setParameter(1, Integer.valueOf(tea_id));
      TeachCourse list = (TeachCourse)query.uniqueResult();
      tx.commit();
      return list;
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
  
  public List<ClassesView> findAllCla(Page page)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from ClassesView";
    Query query = session.createQuery(hql);
    query.setMaxResults(page.getEveryPage());
    query.setFirstResult(page.getBeginIndex());
    List list = query.list();
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public List<ClassesView> findAllClaByDep(Page page, int dep_id)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from ClassesView where dep_id=?";
    Query query = session.createQuery(hql);
    query.setParameter(0, Integer.valueOf(dep_id));
    query.setMaxResults(page.getEveryPage());
    query.setFirstResult(page.getBeginIndex());
    List list = query.list();
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public int findAllClaCount()
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from ClassesView";
    Query query = session.createQuery(hql);
    List list = query.list();
    int count = list.size();
    HibernateSessionFactory.closeSession();
    return count;
  }
  
  public int findfindAllClaCountByDep(int dep_id)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from ClassesView where dep_id=?";
    Query query = session.createQuery(hql);
    query.setParameter(0, Integer.valueOf(dep_id));
    List list = query.list();
    int count = list.size();
    HibernateSessionFactory.closeSession();
    return count;
  }
  
  public boolean addCla(Classes cla)
  {
    Session session = HibernateSessionFactory.getSession();
    Transaction tx = null;
    try
    {
      tx = session.beginTransaction();
      session.save(cla);
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
  
  public boolean updateCla(Classes cla)
  {
    Transaction tx = null;
    try
    {
      Session session = MySessionFactory.getSessionFactory().getCurrentSession();
      tx = session.beginTransaction();
      session.update(cla);
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
  
  public boolean deleteCla(int cla_id)
  {
    Session session = HibernateSessionFactory.getSession();
    Classes que = (Classes)session.get(Classes.class, Integer.valueOf(cla_id));
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
  
  public ClassesView findClaByClaId(int cla_id)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from ClassesView where cla_id=?";
    Query query = session.createQuery(hql);
    query.setInteger(0, cla_id);
    ClassesView list = (ClassesView)query.uniqueResult();
    HibernateSessionFactory.closeSession();
    return list;
  }
  
  public Course findSimpleCouByCouId(int cou_id)
  {
    Session session = HibernateSessionFactory.getSession();
    String hql = "from Course where cou_id=?";
    Query query = session.createQuery(hql);
    query.setInteger(0, cou_id);
    Course tea = (Course)query.uniqueResult();
    HibernateSessionFactory.closeSession();
    return tea;
  }
}
