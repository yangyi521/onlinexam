package com.hp.action;

import com.hp.bean.Manager;
import com.hp.bean.StudentView;
import com.hp.bean.TeaTestView;
import com.hp.bean.TeacherView;
import com.hp.bean.TestView;
import com.hp.dao.ManagerDao;
import com.hp.dao.StudentDao;
import com.hp.dao.TeacherDao;
import com.hp.dao.impl.ManagerDaoImpl;
import com.hp.dao.impl.StudentDaoImpl;
import com.hp.dao.impl.TeacherDaoImpl;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
public class LoginAction
  extends SuperAction
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String username;
  private String password;
  private String choice;
  
  public String getUsername()
  {
    return this.username;
  }
  
  public void setUsername(String username)
  {
    this.username = username;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  public String getChoice()
  {
    return this.choice;
  }
  
  public void setChoice(String choice)
  {
    this.choice = choice;
  }
  
  public void validate()
  {
    if ((this.username == null) || (this.username.replaceAll("", " ").equals(" "))) {
      addFieldError(this.username, "用户名不能为空");
    }
    if ((this.password == null) || (this.password.replaceAll("", " ").equals(" "))) {
      addFieldError(this.password, "密码不能为空");
    }
  }
  
  public String execute()
    throws Exception
  {
    String method = this.request.getParameter("method");
    System.out.println(method);
    if (method.equals("logout")) {
      logout();
    }
    if (method.equals("returnIndex")) {
      returnIndex();
    }
    if (this.choice.equals("student"))
    {
      StudentDao stuDao = new StudentDaoImpl();
      Date date = new Date();
      try
      {
        int usn = Integer.valueOf(this.username).intValue();
        boolean stu = stuDao.stuLogin(usn, this.password);
        if (stu)
        {
          StudentView student = stuDao.findStuInfo(usn);
          List<TestView> list = stuDao.findNearTest(student, date);
          this.session.setAttribute("student", student);
          this.session.setAttribute("near_list", list);
          return "stu_success";
        }
        this.request.setAttribute("msg", "<script>alert('用户名或密码错误');</script>");
        return "error";
      }
      catch (Exception ex)
      {
        this.request.setAttribute("msg", "<script>alert('用户名或密码错误');</script>");
        return "error";
      }
    }
    if (this.choice.equals("teacher"))
    {
      TeacherDao teaDao = new TeacherDaoImpl();
      try
      {
        int usn = Integer.valueOf(this.username).intValue();
        boolean tea = teaDao.teaLogin(usn, this.password);
        if (tea)
        {
          TeacherView t = teaDao.findTeaInfo(Integer.valueOf(this.username).intValue());
          List<TeaTestView> list = teaDao.findNearTest(Integer.valueOf(this.username).intValue());
          this.session.setAttribute("teacher", t);
          
          this.session.setAttribute("near_list", list);
          return "tea_success";
        }
        this.request.setAttribute("msg", "<script>alert('用户名或密码错误');</script>");
        return "error";
      }
      catch (Exception ex)
      {
        this.request.setAttribute("msg", "<script>alert('用户名或密码错误');</script>");
        return "error";
      }
    }
    ManagerDao sysDao = new ManagerDaoImpl();
    boolean sys = sysDao.sysLogin(this.username, this.password);
    if (sys)
    {
      ManagerDao sysd = new ManagerDaoImpl();
      Manager syst = sysd.findManagerInfo(this.username);
      this.session.setAttribute("manager", syst);
      return "adm_success";
    }
    this.request.setAttribute("msg", "<script>alert('用户名或密码错误');</script>");
    return "error";
  }
  
  private String returnIndex()
  {
    return "return_success";
  }
  
  public String logout()
    throws Exception
  {
    if (this.session.getAttribute("student") != null) {
      this.session.removeAttribute("student");
    }
    if (this.session.getAttribute("teacher") != null) {
      this.session.removeAttribute("teacher");
    }
    if (this.session.getAttribute("manager") != null) {
      this.session.removeAttribute("manager");
    }
    return "logout";
  }
}
