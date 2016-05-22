package com.hp.action;

import com.hp.bean.Course;
import com.hp.bean.Point;
import com.hp.bean.Questions;
import com.hp.bean.TeaCouView;
import com.hp.bean.Teacher;
import com.hp.bean.TeacherView;
import com.hp.dao.TeacherDao;
import com.hp.dao.impl.TeacherDaoImpl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class TeaUpdateAction
  extends SuperAction
{
  private static final long serialVersionUID = 1L;
  TeacherDao tead = new TeacherDaoImpl();
  
  public String tmodifyInformation()
  {
    int tea_id = Integer.valueOf(this.request.getParameter("tea_id")).intValue();
    List<TeaCouView> teav = this.tead.findAllTeach(tea_id);
    this.session.setAttribute("teaAllTeach", teav);
    return "tmodifyInformation_success";
  }
  
  public String tpersonalInfo()
    throws ParseException
  {
    String method = this.request.getParameter("method");
    if (method.equals("changePwd"))
    {
      String repassword = this.request.getParameter("repassword");
      String password = this.request.getParameter("password");
      Teacher tu = new Teacher();
      TeacherView tuv = (TeacherView)this.session.getAttribute("teacher");
      TeacherView chkStu = this.tead.findTeaInfo(tuv.getTea_id());
      if (chkStu.getTea_password().equals(repassword))
      {
        tu.setDep_id(tuv.getDep_id());
        tu.setTea_born(tuv.getTea_born());
        tu.setTea_id(tuv.getTea_id());
        tu.setTea_name(tuv.getTea_name());
        tu.setTea_password(password);
        tu.setTea_sex(tuv.getTea_sex());
        boolean t = this.tead.updateTeaInfo(tu);
        if (t) {
          return "tpersonalInfo_success";
        }
        this.request.setAttribute("msg", "<script>alert('更改密码错误，请重新输入');</script>");
        return "changePwd_error";
      }
      this.request.setAttribute("msg", "<script>alert('原密码不正确，请重新输入');</script>");
      return "changePwd_error";
    }
    if (method.equals("modifyInformation"))
    {
      TeacherView tv = (TeacherView)this.session.getAttribute("teacher");
      Teacher teac = new Teacher();
      teac.setTea_id(Integer.valueOf(this.request.getParameter("tea_id")).intValue());
      teac.setTea_name(this.request.getParameter("tea_name"));
      teac.setTea_sex(this.request.getParameter("tea_sex"));
      String year = this.request.getParameter("year");
      String month = this.request.getParameter("month");
      String day = this.request.getParameter("day");
      
      int mon = Integer.valueOf(month).intValue();
      if (mon < 10) {
        month = "0" + month;
      }
      int days = Integer.valueOf(day).intValue();
      if (days < 10) {
        day = "0" + day;
      }
      String born = year + "-" + month + "-" + day;
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      Date date = formatter.parse(born);
      teac.setTea_born(date);
      int age = getAge(date);
      teac.setTea_password(tv.getTea_password());
      teac.setDep_id(tv.getDep_id());
      boolean updateTea = this.tead.updateTeaInfo(teac);
      this.session.setAttribute("age", Integer.valueOf(age));
      if (updateTea) {
        return "tpersonalInfo_update";
      }
      return "error";
    }
    return "error";
  }
  
  public String quesModify()
  {
    String q_id = this.request.getParameter("q_id");
    String dep_id = this.request.getParameter("dep_id");
    Questions q = this.tead.findQueByID(Integer.valueOf(q_id).intValue());
    
    List<Course> cou = this.tead.findAllCou(Integer.valueOf(dep_id).intValue());
    List<Point> poi = this.tead.findAllPoi(Integer.valueOf(dep_id).intValue());
    this.session.setAttribute("quess", q);
    this.session.setAttribute("courses", cou);
    this.session.setAttribute("points", poi);
    return "quesModify_success";
  }
  
  public String quesBank()
  {
    String q_id = this.request.getParameter("q_id");
    String cou = this.request.getParameter("object");
    String type = this.request.getParameter("type");
    String ques = this.request.getParameter("ques");
    String choice_A = this.request.getParameter("choice_A");
    String choice_B = this.request.getParameter("choice_B");
    String choice_C = this.request.getParameter("choice_C");
    String choice_D = this.request.getParameter("choice_D");
    String point = this.request.getParameter("point");
    String ans = this.request.getParameter("ans");
    Questions nq = new Questions();
    nq.setQ_id(Integer.valueOf(q_id).intValue());
    nq.setQ_exist((byte)1);
    nq.setQ_type(Integer.valueOf(type).intValue());
    nq.setQ_title(ques);
    nq.setQ_itemA(choice_A);
    nq.setQ_itemB(choice_B);
    nq.setQ_itemC(choice_C);
    nq.setQ_itemD(choice_D);
    nq.setQ_ans(ans);
    nq.setCou_id(Integer.valueOf(cou).intValue());
    nq.setPoi_id(Integer.valueOf(point).intValue());
    boolean n = this.tead.updateQue(nq);
    if (n) {
      return "quesSuccess";
    }
    return "error";
  }
  
  public static int getAge(Date birthDate)
  {
    if (birthDate == null) {
      throw new RuntimeException("出生日期不能为null");
    }
    int age = 0;
    
    Date now = new Date();
    
    SimpleDateFormat format_y = new SimpleDateFormat("yyyy");
    SimpleDateFormat format_M = new SimpleDateFormat("MM");
    
    String birth_year = format_y.format(birthDate);
    String this_year = format_y.format(now);
    
    String birth_month = format_M.format(birthDate);
    String this_month = format_M.format(now);
    
    age = Integer.parseInt(this_year) - Integer.parseInt(birth_year);
    if (this_month.compareTo(birth_month) < 0) {
      age--;
    }
    if (age < 0) {
      age = 0;
    }
    return age;
  }
}