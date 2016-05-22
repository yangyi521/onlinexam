package com.hp.action;

import com.hp.bean.Questions;
import com.hp.dao.TeacherDao;
import com.hp.dao.impl.TeacherDaoImpl;
import javax.servlet.http.HttpServletRequest;

public class TeaDeleteAction
  extends SuperAction
{
  private static final long serialVersionUID = 1L;
  TeacherDao tead = new TeacherDaoImpl();
  
  public String quesBank()
  {
    String q_id = this.request.getParameter("q_id");
    Questions q2 = this.tead.findQueByID(Integer.valueOf(q_id).intValue());
    q2.setQ_exist((byte)0);
    
    boolean q = this.tead.updateQue(q2);
    if (q) {
      return "quesSuccess";
    }
    return "error";
  }
  
  public String test()
  {
    return "";
  }
}