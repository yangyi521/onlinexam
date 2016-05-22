package com.hp.action;

import com.hp.bean.StudentView;
import com.hp.bean.TeaTestView;
import com.hp.bean.TeacherView;
import com.hp.bean.Test;
import com.hp.bean.TestView;
import com.hp.dao.StudentDao;
import com.hp.dao.TeacherDao;
import com.hp.dao.impl.StudentDaoImpl;
import com.hp.dao.impl.TeacherDaoImpl;
import java.io.PrintStream;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class backAction extends SuperAction
{
  private static final long serialVersionUID = 1L;
  
  public String execute()
  {
    String m = this.request.getParameter("method");
    if ((m == null) || ((!m.equals("tea")) && (!m.equals("sys"))))
    {
      StudentDao stuDao = new StudentDaoImpl();
      Date date = new Date();
      StudentView student = (StudentView)this.session.getAttribute("student");
      List<TestView> list = stuDao.findNearTest(student, date);
      this.session.setAttribute("near_list", list);
      return "stu_success";
    }
    if (m.equals("tea"))
    {
      TeacherView tea = (TeacherView)this.session.getAttribute("teacher");
      TeacherDao tead = new TeacherDaoImpl();
      Test test = (Test)this.session.getAttribute("newTest");
      boolean a = tead.addTest(test);
      System.out.println("增加试卷结果" + a);
      List<TeaTestView> list = tead.findNearTest(tea.getTea_id());
      this.session.setAttribute("near_list", list);
      return "tea_success";
    }
    return "sys_success";
  }
}
