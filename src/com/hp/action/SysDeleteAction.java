package com.hp.action;

import com.hp.dao.ManagerDao;
import com.hp.dao.impl.ManagerDaoImpl;
import javax.servlet.http.HttpServletRequest;

public class SysDeleteAction
  extends SuperAction
{
  private static final long serialVersionUID = 1L;
  ManagerDao sysd = new ManagerDaoImpl();
  
  public String teaManagement()
  {
    String tea_id = this.request.getParameter("tea_id");
    boolean q = this.sysd.deleteTeacher(Integer.valueOf(tea_id).intValue());
    if (q) {
      return "teaManagement_delete";
    }
    return "error";
  }
  
  public String stuManagement()
  {
    String stu_id = this.request.getParameter("stu_id");
    boolean q = this.sysd.deleteStudent(Integer.valueOf(stu_id).intValue());
    if (q) {
      return "stuManagement_delete";
    }
    return "error";
  }
  
  public String courseManagement()
  {
    String cou_id = this.request.getParameter("cou_id");
    boolean q = this.sysd.deleteCourse(Integer.valueOf(cou_id).intValue());
    if (q) {
      return "courseManagement_delete";
    }
    return "error";
  }
  
  public String classesManagement()
  {
    String cla_id = this.request.getParameter("cla_id");
    boolean q = this.sysd.deleteCla(Integer.valueOf(cla_id).intValue());
    if (q) {
      return "classesManagement_delete";
    }
    return "error";
  }
}
