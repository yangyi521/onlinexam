package com.hp.action;

import com.hp.bean.Classes;
import com.hp.bean.ClassesView;
import com.hp.bean.Course;
import com.hp.bean.CourseView;
import com.hp.bean.Department;
import com.hp.bean.Manager;
import com.hp.bean.Student;
import com.hp.bean.StudentView;
import com.hp.bean.TeachCourse;
import com.hp.bean.Teacher;
import com.hp.bean.TeacherView;
import com.hp.dao.ManagerDao;
import com.hp.dao.impl.ManagerDaoImpl;
import com.hp.util.Page;
import com.hp.util.Pageutil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SysUpdateAction
  extends SuperAction
{
  private static final long serialVersionUID = 1L;
  ManagerDao sysd = new ManagerDaoImpl();
  
  public String mmain()
  {
    String repassword = this.request.getParameter("repassword");
    String password = this.request.getParameter("password");
    Manager tu = new Manager();
    Manager mv = (Manager)this.session.getAttribute("manager");
    Manager chkStu = this.sysd.findManagerInfo(mv.getSys_account());
    if (chkStu.getSys_password().equals(repassword))
    {
      tu.setSys_account(mv.getSys_account());
      tu.setSys_password(password);
      boolean t = this.sysd.updateManagerInfo(tu);
      if (t) {
        return "mmain_success";
      }
      this.request.setAttribute("msg", "<script>alert('��������������������������');</script>");
      return "changePwd_error";
    }
    this.request.setAttribute("msg", "<script>alert('������������������������������');</script>");
    return "changePwd_error";
  }
  
  public String courseModify()
  {
    String cou_id = this.request.getParameter("cou_id");
    
    Course cvv = this.sysd.findSimpleCouByCouId(Integer.valueOf(cou_id).intValue());
    List<Teacher> tt = this.sysd.findSimpleTea();
    List<Department> dd = this.sysd.findSimpleDep();
    List<Classes> cc = this.sysd.findSimpleCla();
    this.session.setAttribute("clacla", cc);
    this.session.setAttribute("teatea", tt);
    this.session.setAttribute("depdep", dd);
    this.session.setAttribute("coss", cvv);
    return "courseModify_success";
  }
  
  public String teaModify()
  {
    String tea_id = this.request.getParameter("tea_id");
    Page page = Pageutil.createPage(10, 1, 1);
    TeacherView tev = this.sysd.findTeaByTeaId(page, Integer.valueOf(tea_id).intValue());
    Page newPage = Pageutil.createPage(this.sysd.findAllCourseCount(), this.sysd.findAllCourseCount(), 1);
    List<CourseView> cv = this.sysd.findCourseByDep(newPage, tev.getDep_name());
    List<Teacher> tcv = this.sysd.findSimpleTea();
    this.session.setAttribute("allTeach", tcv);
    this.session.setAttribute("coubydep", cv);
    this.session.setAttribute("tea", tev);
    return "teaModify_success";
  }
  
  public String stuModify()
  {
    String stu_id = this.request.getParameter("stu_id");
    Page page = Pageutil.createPage(10, 1, 1);
    StudentView stv = this.sysd.findStuByStuId(page, Integer.valueOf(stu_id).intValue());
    List<Classes> cl = this.sysd.findSimpleCla();
    List<Department> de = this.sysd.findSimpleDep();
    this.session.setAttribute("cls", cl);
    this.session.setAttribute("des", de);
    this.session.setAttribute("stu", stv);
    return "stuModify_success";
  }
  
  public String classesModify()
  {
    String cla_id = this.request.getParameter("cla_id");
    Page page = Pageutil.createPage(10, 1, 1);
    ClassesView stv = this.sysd.findClaByClaId(Integer.valueOf(cla_id).intValue());
    this.session.setAttribute("cla", stv);
    return "classesModify_success";
  }
  
  public String teaManagement()
    throws ParseException
  {
    TeacherView te = (TeacherView)this.session.getAttribute("tea");
    int tea_id = te.getTea_id();
    String tea_name = this.request.getParameter("username");
    String tea_password = this.request.getParameter("password");
    String tea_sex = this.request.getParameter("tea_sex");
    String dep_id = this.request.getParameter("dep");
    String born = this.request.getParameter("birthday");
    
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date date = formatter.parse(born);
    
    Teacher tea = new Teacher();
    tea.setTea_id(tea_id);
    tea.setTea_name(tea_name);
    tea.setTea_password(tea_password);
    tea.setTea_sex(tea_sex);
    tea.setTea_born(date);
    tea.setDep_id(Integer.valueOf(dep_id).intValue());
    boolean a = this.sysd.updateTeacher(tea);
    if (a) {
      return "teaManagement_delete";
    }
    return "error";
  }
  
  public String stuManagement()
    throws ParseException
  {
    StudentView st = (StudentView)this.session.getAttribute("stu");
    int stu_id = st.getStu_id();
    String stu_name = this.request.getParameter("username");
    String stu_password = this.request.getParameter("password");
    String cla_id = this.request.getParameter("classes");
    String dep_id = this.request.getParameter("dep");
    String stu_sex = this.request.getParameter("stu_sex");
    String born = this.request.getParameter("birthday");
    
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date date = formatter.parse(born);
    
    Student stu = new Student();
    stu.setStu_id(stu_id);
    stu.setStu_name(stu_name);
    stu.setStu_password(stu_password);
    stu.setStu_sex(stu_sex);
    stu.setStu_born(date);
    stu.setCla_id(Integer.valueOf(cla_id).intValue());
    stu.setDep_id(Integer.valueOf(dep_id).intValue());
    
    boolean a = this.sysd.updateStudent(stu);
    if (a) {
      return "stuManagement_delete";
    }
    return "error";
  }
  
  public String courseManagement()
  {
    String cou_id = this.request.getParameter("cou_id");
    String cou_name = this.request.getParameter("coursename");
    String cou_credit = this.request.getParameter("coursenum");
    Course cou = new Course();
    cou.setCou_id(Integer.valueOf(cou_id).intValue());
    cou.setCou_credit(Integer.valueOf(cou_credit).intValue());
    cou.setCou_name(cou_name);
    boolean a = this.sysd.updateCourse(cou);
    if (a)
    {
      String cla = this.request.getParameter("classes");
      String teacher = this.request.getParameter("teacher");
      String tea_cou_id = this.request.getParameter("tea_cou_id");
      TeachCourse tc = new TeachCourse();
      tc.setCla_id(Integer.valueOf(cla).intValue());
      tc.setCou_id(Integer.valueOf(cou_id).intValue());
      tc.setTea_id(Integer.valueOf(teacher).intValue());
      tc.setTea_cou_id(Integer.valueOf(tea_cou_id).intValue());
      boolean b = this.sysd.updateTeachCou(tc);
      if (b) {
        return "courseManagement_delete";
      }
      return "error";
    }
    return "error";
  }
  
  public String classesManagement()
  {
    ClassesView cv = (ClassesView)this.session.getAttribute("cla");
    int cla_id = cv.getCla_id();
    
    String cla_name = this.request.getParameter("claname");
    int dep_id = Integer.valueOf(this.request.getParameter("dep")).intValue();
    Classes c = new Classes();
    c.setCla_id(cla_id);
    c.setCla_name(cla_name);
    c.setDep_id(dep_id);
    boolean a = this.sysd.updateCla(c);
    if (a) {
      return "classesManagement_delete";
    }
    return "error";
  }
}
