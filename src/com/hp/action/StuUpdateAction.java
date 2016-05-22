package com.hp.action;

import com.hp.bean.Classes;
import com.hp.bean.Department;
import com.hp.bean.QuesView;
import com.hp.bean.Questions;
import com.hp.bean.StuTest;
import com.hp.bean.Student;
import com.hp.bean.StudentView;
import com.hp.bean.TestView;
import com.hp.dao.StudentDao;
import com.hp.dao.impl.StudentDaoImpl;
import com.hp.util.PoiTitleCount;

import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class StuUpdateAction
  extends SuperAction
{
  StudentDao sdao = new StudentDaoImpl();
  private static final long serialVersionUID = 1L;
  
  public String modifyInformation()
    throws Exception
  {
    StudentView stu = (StudentView)this.session.getAttribute("student");
    StudentDao sdao = new StudentDaoImpl();
    List<Department> dep = sdao.findAllDep();
    List<Classes> cla = sdao.findAllClass(stu.getDep_id());
    this.session.setAttribute("department", dep);
    this.session.setAttribute("classes", cla);
    return "modifyInformation_success";
  }
  
  public String personalInfo()
    throws ParseException
  {
    String method = this.request.getParameter("method");
    if (method.equals("changePwd"))
    {
      String repassword = this.request.getParameter("repassword");
      String password = this.request.getParameter("password");
      Student stu = new Student();
      StudentView stuv = (StudentView)this.session.getAttribute("student");
      StudentDao sdao = new StudentDaoImpl();
      StudentView chkStu = sdao.findStuInfo(stuv.getStu_id());
      if (chkStu.getStu_password().equals(repassword))
      {
        stu.setCla_id(stuv.getCla_id());
        stu.setDep_id(stuv.getDep_id());
        stu.setStu_born(stuv.getStu_born());
        stu.setStu_id(stuv.getStu_id());
        stu.setStu_name(stuv.getStu_name());
        stu.setStu_password(password);
        stu.setStu_sex(stuv.getStu_sex());
        boolean s = sdao.updateStuInfo(stu);
        if (s) {
          return "personalInfo_success";
        }
        this.request.setAttribute("msg", "<script>alert('原密码不正确请重新输入');</script>");
        return "changePwd_error";
      }
      this.request.setAttribute("msg", "<script>alert('原密码不正确请重新输入');</script>");
      return "changePwd_error";
    }
    if (method.equals("modifyInformation"))
    {
      StudentView st = (StudentView)this.session.getAttribute("student");
      Student stu = new Student();
      stu.setStu_id(Integer.valueOf(this.request.getParameter("stu_id")).intValue());
      stu.setStu_name(this.request.getParameter("stu_name"));
      stu.setStu_sex(this.request.getParameter("stu_sex"));
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
      
      stu.setStu_born(date);
      int age = getAge(date);
      
      stu.setStu_password(st.getStu_password());
      stu.setCla_id(st.getCla_id());
      stu.setDep_id(st.getDep_id());
      StudentDao sdao = new StudentDaoImpl();
      boolean updateStu = sdao.updateStuInfo(stu);
      this.session.setAttribute("age", Integer.valueOf(age));
      System.out.println("updateStu" + updateStu);
      if (updateStu) {
        return "personalInfo_update";
      }
      return "error";
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
  
  public String recentExam()
  {
    StuTest stuTest = new StuTest();
    String stu_wrong_ques = "";
    String stu_wrong_ans = "";
    String time = this.request.getParameter("hidden1");
    stuTest.setStu_test_time(time);
    
    int count = 0;
    List<String> array = new ArrayList();
    Questions[] t_sin = (Questions[])this.session.getAttribute("t_sin");
    Questions[] t_mult = (Questions[])this.session.getAttribute("t_mult");
    Questions[] t_judg = (Questions[])this.session.getAttribute("t_judg");
    
    String[] stu_sin_ans = new String[t_sin.length];
    
    stu_sin_ans[0] = this.request.getParameter("choice_" + t_sin[0].getQ_id());
    if (stu_sin_ans[0] == null)
    {
      stu_wrong_ans = "null";
      stu_wrong_ques = String.valueOf(t_sin[0].getQ_id());
      QuesView que = this.sdao.findQuesByQueId(t_sin[0].getQ_id());
      count++;
      array.add(que.getPoi_title());
      array.add(que.getPoi_title());
    }
    else if (!stu_sin_ans[0].equals(t_sin[0].getQ_ans()))
    {
      QuesView que = this.sdao.findQuesByQueId(t_sin[0].getQ_id());
      array.add(que.getPoi_title());
      stu_wrong_ques = String.valueOf(t_sin[0].getQ_id());
      stu_wrong_ans = stu_sin_ans[0];
      count++;
    }
    for (int i = 1; i < t_sin.length; i++)
    {
      stu_sin_ans[i] = this.request.getParameter("choice_" + t_sin[i].getQ_id());
      if (stu_sin_ans[i] == null)
      {
        stu_wrong_ans = stu_wrong_ans + "," + "null";
        stu_wrong_ques = stu_wrong_ques + "," + t_sin[i].getQ_id();
        QuesView que = this.sdao.findQuesByQueId(t_sin[i].getQ_id());
        count++;
        array.add(que.getPoi_title());
        array.add(que.getPoi_title());
        System.out.println("未做的单选知识点+2：" + que.getPoi_title());
      }
      else if (!stu_sin_ans[i].equals(t_sin[i].getQ_ans()))
      {
        QuesView que = this.sdao.findQuesByQueId(t_sin[i].getQ_id());
        
        array.add(que.getPoi_title());
        stu_wrong_ques = stu_wrong_ques + "," + t_sin[i].getQ_id();
        stu_wrong_ans = stu_wrong_ans + "," + stu_sin_ans[i];
        count++;
        System.out.println("做错的单选知识点+1：" + que.getPoi_title());
      }
    }
    double sin_score = ((Double)this.session.getAttribute("sin_score")).doubleValue();
    
    sin_score = 30.0D - sin_score * count;
    
    int a = count;
    
    String stu_mult_anss = "";
    String zz = "";
    String[] stu_mult_ans = new String[4];
    
    stu_mult_ans = this.request.getParameterValues("checkbox_" + t_mult[0].getQ_id());
    if (stu_mult_ans == null)
    {
      zz = "null";
    }
    else
    {
      for (int i2 = 0; i2 < stu_mult_ans.length; i2++) {
        zz = zz + stu_mult_ans[i2];
      }
      System.out.println("多选第一题答案" + zz);
    }
    if (zz.equals("null"))
    {
      QuesView que = this.sdao.findQuesByQueId(t_mult[0].getQ_id());
      count++;
      System.out.println("count第一次++：" + count);
      stu_wrong_ques = stu_wrong_ques + "," + t_mult[0].getQ_id();
      stu_wrong_ans = stu_wrong_ans + "," + zz;
      array.add(que.getPoi_title());
      array.add(que.getPoi_title());
    }
    else if (!zz.equals(t_mult[0].getQ_ans()))
    {
      stu_mult_anss = zz;
      stu_wrong_ques = stu_wrong_ques + "," + t_mult[0].getQ_id();
      stu_wrong_ans = stu_wrong_ans + "," + stu_mult_anss;
      count++;
      System.out.println("count第二次++：" + count);
      QuesView que = this.sdao.findQuesByQueId(t_mult[0].getQ_id());
      array.add(que.getPoi_title());
    }
    for (int i = 1; i < t_mult.length; i++)
    {
      zz = "";
      
      stu_mult_ans = this.request.getParameterValues("checkbox_" + t_mult[i].getQ_id());
      if (stu_mult_ans == null)
      {
        zz = "null";
      }
      else
      {
        for (int i2 = 0; i2 < stu_mult_ans.length; i2++) {
          zz = zz + stu_mult_ans[i2];
        }
        System.out.println("多选题第二题答案：" + zz);
      }
      if (zz.equals("null"))
      {
        QuesView que = this.sdao.findQuesByQueId(t_mult[i].getQ_id());
        count++;
        System.out.println("count第三次++：" + count);
        stu_wrong_ques = stu_wrong_ques + "," + t_mult[i].getQ_id();
        stu_wrong_ans = stu_wrong_ans + "," + zz;
        array.add(que.getPoi_title());
        array.add(que.getPoi_title());
      }
      else if (!zz.equals(t_mult[i].getQ_ans()))
      {
        stu_mult_anss = zz;
        stu_wrong_ques = stu_wrong_ques + "," + t_mult[i].getQ_id();
        stu_wrong_ans = stu_wrong_ans + "," + stu_mult_anss;
        System.out.println("多选题第二题最终答案：" + stu_mult_anss);
        count++;
        System.out.println("count第四次++：" + count);
        QuesView que = this.sdao.findQuesByQueId(t_mult[i].getQ_id());
        array.add(que.getPoi_title());
      }
    }
    double mult_score = ((Double)this.session.getAttribute("mult_score")).doubleValue();
    
    mult_score = 40.0D - mult_score * (count - a);
    System.out.println("count计数器:" + count + ",a计数为：" + a);
    
    a = count;
    
    String[] stu_judg_ans = new String[t_judg.length];
    
    stu_judg_ans[0] = this.request.getParameter("judge_" + t_judg[0].getQ_id());
    if (stu_judg_ans[0] == null)
    {
      stu_wrong_ques = stu_wrong_ques + "," + t_judg[0].getQ_id();
      stu_wrong_ans = stu_wrong_ans + "," + "null";
      System.out.println("到判断第一题为止的答案集合：" + stu_wrong_ans);
      QuesView que = this.sdao.findQuesByQueId(t_judg[0].getQ_id());
      count++;
      array.add(que.getPoi_title());
      array.add(que.getPoi_title());
    }
    else if (!stu_judg_ans[0].equals(t_judg[0].getQ_ans()))
    {
      stu_wrong_ques = stu_wrong_ques + "," + t_judg[0].getQ_id();
      stu_wrong_ans = stu_wrong_ans + "," + stu_judg_ans[0];
      count++;
      QuesView que = this.sdao.findQuesByQueId(t_judg[0].getQ_id());
      array.add(que.getPoi_title());
    }
    for (int i = 1; i < t_judg.length; i++)
    {
      stu_judg_ans[i] = this.request.getParameter("judge_" + t_judg[i].getQ_id());
      if (stu_judg_ans[i] == null)
      {
        stu_wrong_ques = stu_wrong_ques + "," + t_judg[i].getQ_id();
        stu_wrong_ans = stu_wrong_ans + "," + "null";
        QuesView que = this.sdao.findQuesByQueId(t_judg[i].getQ_id());
        count++;
        array.add(que.getPoi_title());
        array.add(que.getPoi_title());
      }
      else if (!stu_judg_ans[i].equals(t_judg[i].getQ_ans()))
      {
        stu_wrong_ques = stu_wrong_ques + "," + t_judg[i].getQ_id();
        stu_wrong_ans = stu_wrong_ans + "," + stu_judg_ans[i];
        count++;
        QuesView que = this.sdao.findQuesByQueId(t_judg[i].getQ_id());
        array.add(que.getPoi_title());
      }
    }
    double judg_score = ((Double)this.session.getAttribute("judg_score")).doubleValue();
    
    judg_score = 30.0D - judg_score * (count - a);
    
    String[] eve = new String[3];
    if (count == 0)
    {
      stuTest.setStu_wrong_ques("0");
      stuTest.setStu_test_evaluate("做的很棒");
    }
    else
    {
      Iterator<Map.Entry> it = PoiTitleCount.CountPointTitle(array);
      while (it.hasNext())
      {
        Map.Entry<String, Integer> entry = (Map.Entry)it.next();
        if (((Integer)entry.getValue()).intValue() < 4) {
          eve[0] = ("对于  " + (String)entry.getKey() + "  掌握的不太牢;<br />");
        } else if (((Integer)entry.getValue()).intValue() < 8) {
          eve[1] = ("对于  " + (String)entry.getKey() + " 还是很迷茫;<br />");
        } else {
          eve[2] = ("对于  " + (String)entry.getKey() + "  一无所知;<br />");
        }
      }
      if (eve[0] == null) {
        eve[0] = "";
      }
      if (eve[1] == null) {
        eve[1] = "";
      }
      if (eve[2] == null) {
        eve[2] = "";
      }
      stuTest.setStu_test_evaluate("以下知识点掌握的不太好<br />" + eve[0] + eve[1] + eve[2]);
      if (stu_wrong_ques.substring(0, 1).equals(",")) {
        stu_wrong_ques = stu_wrong_ques.substring(1);
      }
      if (stu_wrong_ans.substring(0, 1).equals(",")) {
        stu_wrong_ans = stu_wrong_ans.substring(1);
      }
      stuTest.setStu_wrong_ques(stu_wrong_ques);
      stuTest.setStu_wrong_ans(stu_wrong_ans);
      System.out.println("打印stu_wrong_ques"+stu_wrong_ques);
      System.out.println("打印stu_wrong_ans"+stu_wrong_ans);
    }
    TestView test = (TestView)this.session.getAttribute("test");
    stuTest.setTest_id(Integer.valueOf(this.request.getParameter("test_id")).intValue());
    stuTest.setCou_id(test.getCou_id());
    stuTest.setStu_test_score(sin_score + mult_score + judg_score);
    StudentView stu = (StudentView)this.session.getAttribute("student");
    stuTest.setStu_id(stu.getStu_id());
    boolean a1 = this.sdao.addStuTest(stuTest);
    if (a1)
    {
      this.session.setAttribute("stuTest", stuTest);
      return "recentExam_success";
    }
    return "error";
  }
}
