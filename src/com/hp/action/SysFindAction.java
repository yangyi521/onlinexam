package com.hp.action;

import com.hp.bean.Classes;
import com.hp.bean.ClassesView;
import com.hp.bean.CourseView;
import com.hp.bean.Department;
import com.hp.bean.StudentView;
import com.hp.bean.TeaCouView;
import com.hp.bean.Teacher;
import com.hp.bean.TeacherView;
import com.hp.dao.ManagerDao;
import com.hp.dao.impl.ManagerDaoImpl;
import com.hp.util.Page;
import com.hp.util.Pageutil;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SysFindAction
  extends SuperAction
{
  private static final long serialVersionUID = 1L;
  ManagerDao sysd = new ManagerDaoImpl();
  
  public String courseManagement()
  {
    Page page = new Page();
    String currentPage = this.request.getParameter("currentPage");
    if (currentPage == null) {
      currentPage = "0";
    }
    page = Pageutil.createPage(10, this.sysd.findAllCourseCount(), Integer.valueOf(currentPage).intValue());
    List<CourseView> cv = this.sysd.findAllCourse(page);
    this.session.setAttribute("allCourse", cv);
    this.session.setAttribute("page", page);
    return "courseManagement_success";
  }
  
  public String teaManagement()
  {
    Page page = new Page();
    String currentPage = this.request.getParameter("currentPage");
    if (currentPage == null) {
      currentPage = "0";
    }
    page = Pageutil.createPage(10, this.sysd.findAllTeaCount(), Integer.valueOf(currentPage).intValue());
    List<TeacherView> tv = this.sysd.findAllTea(page);
    this.session.setAttribute("allTeacher", tv);
    this.session.setAttribute("page", page);
    return "teaManagement_success";
  }
  
  public String stuManagement()
  {
    Page page = new Page();
    String currentPage = this.request.getParameter("currentPage");
    if (currentPage == null) {
      currentPage = "0";
    }
    page = Pageutil.createPage(10, this.sysd.findAllStuCount(), Integer.valueOf(currentPage).intValue());
    List<StudentView> tv = this.sysd.findAllStu(page);
    this.session.setAttribute("allStudent", tv);
    this.session.setAttribute("page", page);
    return "stuManagement_success";
  }
  
  public String classesManagement()
  {
    Page page = new Page();
    String currentPage = this.request.getParameter("currentPage");
    if (currentPage == null) {
      currentPage = "0";
    }
    page = Pageutil.createPage(10, this.sysd.findAllClaCount(), Integer.valueOf(currentPage).intValue());
    List<ClassesView> tv = this.sysd.findAllCla(page);
    this.session.setAttribute("allClasses", tv);
    this.session.setAttribute("page", page);
    return "classesManagement_success";
  }
  
  public String findClassesManagement()
  {
    String selectk = this.request.getParameter("selectk");
    if (selectk == null) {
      selectk = (String)this.session.getAttribute("selectk");
    }
    String currentPage = this.request.getParameter("currentPage");
    if (currentPage == null) {
      currentPage = "0";
    }
    if (selectk.equals("1"))
    {
      Page page = Pageutil.createPage(10, this.sysd.findfindAllClaCountByDep(1), Integer.valueOf(currentPage).intValue());
      List<ClassesView> cuv = this.sysd.findAllClaByDep(page, 1);
      this.session.setAttribute("allClasses", cuv);
      this.session.setAttribute("page", page);
      this.session.setAttribute("selectk", selectk);
      return "findClassesManagement_success";
    }
    if (selectk.equals("2"))
    {
      Page page = Pageutil.createPage(10, this.sysd.findfindAllClaCountByDep(2), Integer.valueOf(currentPage).intValue());
      List<ClassesView> cuv = this.sysd.findAllClaByDep(page, 2);
      this.session.setAttribute("allClasses", cuv);
      this.session.setAttribute("page", page);
      this.session.setAttribute("selectk", selectk);
      return "findClassesManagement_success";
    }
    return "error";
  }
  
  public String findCourseManagement()
  {
    String selectk = this.request.getParameter("selectk");
    String coursename = this.request.getParameter("coursename");
    if (selectk == null) {
      selectk = (String)this.session.getAttribute("selectk");
    }
    if (coursename == null) {
      coursename = (String)this.session.getAttribute("quesname");
    }
    String currentPage = this.request.getParameter("currentPage");
    if (currentPage == null) {
      currentPage = "0";
    }
    if (selectk.equals("courseName"))
    {
      Page page = Pageutil.createPage(10, this.sysd.findAllCourseCount(), Integer.valueOf(currentPage).intValue());
      List<CourseView> cuv = this.sysd.findCourseByCouName(page, coursename);
      this.session.setAttribute("allCourse", cuv);
      this.session.setAttribute("page", page);
      this.session.setAttribute("selectk", selectk);
      this.session.setAttribute("quesname", coursename);
      return "findCourseManagement_success";
    }
    if (selectk.equals("depName"))
    {
      Page page = Pageutil.createPage(10, this.sysd.findAllCourseCount(), Integer.valueOf(currentPage).intValue());
      List<CourseView> cuv = this.sysd.findCourseByDep(page, coursename);
      this.session.setAttribute("allCourse", cuv);
      this.session.setAttribute("page", page);
      this.session.setAttribute("selectk", selectk);
      this.session.setAttribute("quesname", coursename);
      return "findCourseManagement_success";
    }
    return "error";
  }
  
  public String findTeaManagement()
  {
    String selectk = this.request.getParameter("selectk");
    String name = this.request.getParameter("name");
    if (selectk == null) {
      selectk = (String)this.session.getAttribute("selectk");
    }
    if (name == null) {
      name = (String)this.session.getAttribute("quesname");
    }
    String currentPage = this.request.getParameter("currentPage");
    if (currentPage == null) {
      currentPage = "0";
    }
    if ((name == null) || (name.equals("")))
    {
      Page page = Pageutil.createPage(10, this.sysd.findAllTeaCount(), Integer.valueOf(currentPage).intValue());
      List<TeacherView> cuv = this.sysd.findAllTea(page);
      this.session.setAttribute("allTeacher", cuv);
      this.session.setAttribute("quesname", name);
      this.session.setAttribute("page", page);
      return "findTeaManagement_success";
    }
    if (selectk.equals("courseName"))
    {
      Page page = Pageutil.createPage(10, this.sysd.findAllTeaCount(), Integer.valueOf(currentPage).intValue());
      List<TeaCouView> cuv = this.sysd.findTeaByCouName(page, name);
      this.session.setAttribute("allTeacher", cuv);
      this.session.setAttribute("page", page);
      this.session.setAttribute("selectk", selectk);
      this.session.setAttribute("quesname", name);
      return "findTeaManagement_success";
    }
    if (selectk.equals("teaId"))
    {
      Page page = Pageutil.createPage(10, this.sysd.findAllTeaCount(), Integer.valueOf(currentPage).intValue());
      TeacherView cuv = this.sysd.findTeaByTeaId(page, Integer.valueOf(name).intValue());
      this.session.setAttribute("allTeacher", cuv);
      this.session.setAttribute("page", page);
      this.session.setAttribute("selectk", selectk);
      this.session.setAttribute("quesname", name);
      return "findTeaManagement_success";
    }
    if (selectk.equals("teaName"))
    {
      Page page = Pageutil.createPage(10, this.sysd.findAllTeaCount(), Integer.valueOf(currentPage).intValue());
      List<TeacherView> cuv = this.sysd.findTeaByTeaName(page, name);
      this.session.setAttribute("allTeacher", cuv);
      this.session.setAttribute("page", page);
      this.session.setAttribute("selectk", selectk);
      this.session.setAttribute("quesname", name);
      return "findTeaManagement_success";
    }
    return "error";
  }
  
  public String findStuManagement()
  {
    String selectk = this.request.getParameter("selectk");
    String name = this.request.getParameter("stuname");
    if (selectk == null) {
      selectk = (String)this.session.getAttribute("selectk");
    }
    if (name == null) {
      name = (String)this.session.getAttribute("quesname");
    }
    String currentPage = this.request.getParameter("currentPage");
    String method = this.request.getParameter("method");
    if (currentPage == null) {
      currentPage = "0";
    }
    if ((name == null) || (name.equals("")))
    {
      Page page = Pageutil.createPage(10, this.sysd.findAllStuCount(), Integer.valueOf(currentPage).intValue());
      List<StudentView> cuv = this.sysd.findAllStu(page);
      this.session.setAttribute("allStudent", cuv);
      this.session.setAttribute("quesname", name);
      this.session.setAttribute("page", page);
      return "findStuManagement_success";
    }
    if (selectk.equals("claName"))
    {
      Page page = Pageutil.createPage(10, this.sysd.findStuCountByClaName(name), Integer.valueOf(currentPage).intValue());
      List<StudentView> cuv = this.sysd.findStuByClaName(page, name);
      this.session.setAttribute("allStudent", cuv);
      this.session.setAttribute("page", page);
      this.session.setAttribute("selectk", selectk);
      this.session.setAttribute("quesname", name);
      return "findStuManagement_success";
    }
    if (selectk.equals("stuId"))
    {
      Page page = Pageutil.createPage(10, this.sysd.findStuCountByStuId(Integer.valueOf(name).intValue()), Integer.valueOf(currentPage).intValue());
      StudentView cuv = this.sysd.findStuByStuId(page, Integer.valueOf(name).intValue());
      this.session.setAttribute("allStudent", cuv);
      this.session.setAttribute("page", page);
      this.session.setAttribute("selectk", selectk);
      this.session.setAttribute("quesname", name);
      return "findStuManagement_success";
    }
    if (selectk.equals("stuName"))
    {
      Page page = Pageutil.createPage(10, this.sysd.findStuCountByStuName(name), Integer.valueOf(currentPage).intValue());
      List<StudentView> cuv = this.sysd.findStuByStuName(page, name);
      this.session.setAttribute("allStudent", cuv);
      this.session.setAttribute("page", page);
      this.session.setAttribute("selectk", selectk);
      this.session.setAttribute("quesname", name);
      return "findStuManagement_success";
    }
    return "error";
  }
  
  public String stuAdd()
  {
    List<Classes> cl = this.sysd.findSimpleCla();
    List<Department> de = this.sysd.findSimpleDep();
    this.session.setAttribute("deps", de);
    this.session.setAttribute("classes", cl);
    return "stuAdd_success";
  }
  
  public String teaAdd()
  {
    List<Department> dep = this.sysd.findSimpleDep();
    this.session.setAttribute("departs", dep);
    return "teaAdd_success";
  }
  
  public String courseAdd()
  {
    List<Teacher> tea = this.sysd.findSimpleTea();
    List<Classes> cla = this.sysd.findSimpleCla();
    this.session.setAttribute("Teachers", tea);
    this.session.setAttribute("Classes", cla);
    return "courseAdd_success";
  }
}
