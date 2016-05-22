package com.hp.action;

import com.hp.bean.Classes;
import com.hp.bean.Course;
import com.hp.bean.Student;
import com.hp.bean.TeachCourse;
import com.hp.bean.Teacher;
import com.hp.dao.ManagerDao;
import com.hp.dao.StudentDao;
import com.hp.dao.TeacherDao;
import com.hp.dao.impl.ManagerDaoImpl;
import com.hp.dao.impl.StudentDaoImpl;
import com.hp.dao.impl.TeacherDaoImpl;
import com.hp.util.DBhelper;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class SysAddAction
  extends SuperAction
{
  private static final long serialVersionUID = 1L;
  ManagerDao sysd = new ManagerDaoImpl();
  
  public String courseManagement()
  {
    String couname = this.request.getParameter("couname");
    String credit = this.request.getParameter("credit");
    String tea_id = this.request.getParameter("teacher");
    String cla_id = this.request.getParameter("classes");
    
    Course ca = new Course();
    ca.setCou_name(couname);
    ca.setCou_credit(Integer.valueOf(credit).intValue());
    boolean a = this.sysd.addCourse(ca);
    if (a)
    {
      Course cc = this.sysd.findCourseByName(couname);
      TeachCourse cb = new TeachCourse();
      cb.setCou_id(cc.getCou_id());
      cb.setCla_id(Integer.valueOf(cla_id).intValue());
      cb.setTea_id(Integer.valueOf(tea_id).intValue());
      boolean b = this.sysd.addTeaCou(cb);
      if (b) {
        return "courseManagement_delete";
      }
      return "error";
    }
    return "error";
  }
  
  public String teaManagement()
    throws ParseException
  {
    String tea_id = this.request.getParameter("num");
    String tea_name = this.request.getParameter("username");
    String tea_password = this.request.getParameter("password");
    String tea_sex = this.request.getParameter("sex");
    String born = this.request.getParameter("birthday");
    
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date date = formatter.parse(born);
    String dep_id = this.request.getParameter("dep");
    
    Teacher tea = new Teacher();
    tea.setTea_id(Integer.valueOf(tea_id).intValue());
    tea.setTea_name(tea_name);
    tea.setTea_password(tea_password);
    tea.setTea_sex(tea_sex);
    tea.setTea_born(date);
    tea.setDep_id(Integer.valueOf(dep_id).intValue());
    boolean a = this.sysd.addTeacher(tea);
    if (a) {
      return "teaManagement_delete";
    }
    return "error";
  }
  
  public String stuManagement()
    throws ParseException
  {
    String stu_id = this.request.getParameter("num");
    String stu_name = this.request.getParameter("username");
    String stu_password = this.request.getParameter("password");
    String cla_id = this.request.getParameter("classes");
    String dep_id = this.request.getParameter("dep");
    String stu_sex = this.request.getParameter("sex");
    String born = this.request.getParameter("birthday");
    
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date date = formatter.parse(born);
    
    Student stu = new Student();
    stu.setStu_id(Integer.valueOf(stu_id).intValue());
    stu.setStu_name(stu_name);
    stu.setStu_password(stu_password);
    stu.setStu_sex(stu_sex);
    stu.setStu_born(date);
    stu.setCla_id(Integer.valueOf(cla_id).intValue());
    stu.setDep_id(Integer.valueOf(dep_id).intValue());
    
    boolean a = this.sysd.addStudent(stu);
    if (a) {
      return "stuManagement_delete";
    }
    return "error";
  }
  
  public String classesManagement()
  {
    int cla_id = Integer.valueOf(this.request.getParameter("clanum")).intValue();
    String cla_name = this.request.getParameter("claname");
    int dep_id = Integer.valueOf(this.request.getParameter("dep")).intValue();
    Classes cla = new Classes();
    cla.setCla_id(cla_id);
    cla.setCla_name(cla_name);
    cla.setDep_id(dep_id);
    boolean a = this.sysd.addCla(cla);
    if (a) {
      return "classesManagement_delete";
    }
    return "error";
  }
  
  public String bulkStuAdd()
  {
    StudentDao studentDao = new StudentDaoImpl();
    
    List<Student> listExcel = studentDao.getStudentAllByExcel("D://onlinexam/Student.xls");
    if (listExcel == null) {
      return "error";
    }
    DBhelper db = new DBhelper();
    for (Student student : listExcel)
    {
      int id = student.getStu_id();
      if (!studentDao.isStudentExist(id))
      {
        String sql = "insert into t_student (stu_id,stu_name,stu_password,stu_sex,stu_born,cla_id,dep_id) values(?,?,?,?,?,?,?)";
        
        Object[] str = { student.getStu_id(), 
          student.getStu_name(), student.getStu_password(), 
          student.getStu_sex(), student.getStu_born(), 
          student.getCla_id(), student.getDep_id() };
        db.AddU(sql, str);
        System.out.println("TestExcelToDb������StudentTestExcelToDb()--1");
      }
      else
      {
        String sql = "update t_student set stu_name=?,stu_password=?,stu_sex=?,stu_born=?,cla_id=?,dep_id=? where stu_id=?";
        
        Object[] str = { student.getStu_name(), 
          student.getStu_password(), student.getStu_sex(), 
          student.getStu_born(), 
          student.getCla_id(), student.getDep_id(), id };
        db.AddU(sql, str);
        System.out.println("TestExcelToDb������StudentTestExcelToDb()--2");
      }
    }
    this.request.setAttribute("msg", "<script>alert('批量增加学生');</script>");
    return "bulkStuAdd_success";
  }
  
  public String bulkTeaAdd()
  {
    TeacherDao teacherDao = new TeacherDaoImpl();
    
    List<Teacher> listExcel = teacherDao.getTeacherAllByExcel("D://onlinexam/Teacher.xls");
    if (listExcel == null) {
      return "error";
    }
    DBhelper db = new DBhelper();
    for (Teacher teacher : listExcel)
    {
      int id = teacher.getTea_id();
      if (!teacherDao.isTeacherExist(id))
      {
        String sql = "insert into t_teacher (tea_id,tea_name,tea_password,tea_sex,tea_born,dep_id) values(?,?,?,?,?,?)";
        
        Object[] str = { Integer.valueOf(teacher.getTea_id()), 
          teacher.getTea_name(), teacher.getTea_password(), 
          teacher.getTea_sex(), teacher.getTea_born(), Integer.valueOf(teacher.getDep_id()) };
        db.AddU(sql, str);
        System.out.println("TestExcelToDb类TeacherTestExcelToDb()--1");
      }
      else
      {
        String sql = "update t_teacher set tea_name=?,tea_password=?,tea_sex=?,tea_born=?,dep_id=? where tea_id=?";
        
        Object[] str = { teacher.getTea_name(), teacher.getTea_password(), 
          teacher.getTea_sex(), teacher.getTea_born(), teacher.getDep_id(), id };
        db.AddU(sql, str);
        System.out.println("TestExcelToDb类TeacherTestExcelToDb()--2");
      }
    }
    this.request.setAttribute("msg", "<script>alert('批量增加教师成功');</script>");
    return "bulkTeaAdd_success";
  }
}
