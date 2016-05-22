package com.hp.action;

import com.hp.bean.Course;
import com.hp.bean.Point;
import com.hp.bean.Questions;
import com.hp.bean.Test;
import com.hp.dao.TeacherDao;
import com.hp.dao.impl.TeacherDaoImpl;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class TeaAddAction
  extends SuperAction
{
  private static final long serialVersionUID = 1L;
  TeacherDao tead = new TeacherDaoImpl();
  
  public String quesAdd()
  {
    String dep_id = this.request.getParameter("dep_id");
    System.out.println("zheshi" + dep_id + "lele");
    List<Course> cou = this.tead.findAllCou(Integer.valueOf(dep_id).intValue());
    List<Point> poi = this.tead.findAllPoi(Integer.valueOf(dep_id).intValue());
    this.session.setAttribute("courses", cou);
    this.session.setAttribute("points", poi);
    return "quesAdd_success";
  }
  
  public String quesBank()
  {
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
    boolean n = this.tead.addQuestions(nq);
    if (n) {
      return "quesSuccess";
    }
    return "error";
  }
  
  public String test()
    throws ParseException
  {
    String method = this.request.getParameter("method");
    String cous = this.request.getParameter("cous");
    
    String[] aaa = this.request.getParameterValues("teachCla");
    String test_clas = aaa[0];
    for (int i = 1; i < aaa.length; i++) {
      test_clas = test_clas + "," + aaa[i];
    }
    String sincho = this.request.getParameter("sincho");
    System.out.println(sincho);
    int sin_cho = Integer.valueOf(sincho).intValue();
    
    String multcho = this.request.getParameter("multcho");
    System.out.println(multcho);
    int mult_cho = Integer.valueOf(multcho).intValue();
    
    String judg = this.request.getParameter("judg");
    System.out.println(judg);
    int jud = Integer.valueOf(judg).intValue();
    
    int count = this.tead.findAllQuesCount();
    System.out.println("count" + count);
    int[] testsin = new int[sin_cho];
    int[] testmult = new int[mult_cho];
    int[] testjudg = new int[jud];
    if (method.equals("randomTest"))
    {
      for (int i = 0; i < sin_cho;)
      {
        int testCount = (int)(1.0D + Math.random() * count);
        System.out.println("单选随机数" + testCount);
        Questions que = this.tead.findQueByID(testCount);
        int u = 0;
        for (int t = 0; t < testsin.length; t++) {
          if (testsin[t] == testCount) {
            u++;
          }
        }
        System.out.println(Integer.valueOf(cous));
        if ((que != null) && (que.getCou_id() == Integer.valueOf(cous).intValue()) && (que.getQ_type() == 1) && (u == 0) && (que.getQ_exist() == 1))
        {
          testsin[i] = testCount;
          i++;
        }
      }
      for (int j = 0; j < mult_cho;)
      {
        int testCount1 = (int)(1.0D + Math.random() * count);
        System.out.println("多选随机数为：" + testCount1);
        Questions que = this.tead.findQueByID(testCount1);
        int u = 0;
        for (int t = 0; t < testmult.length; t++) {
          if (testmult[t] == testCount1) {
            u++;
          }
        }
        if ((que != null) && (que.getCou_id() == Integer.valueOf(cous).intValue()) && (que.getQ_type() == 2) && (u == 0) && (que.getQ_exist() == 1))
        {
          testmult[j] = testCount1;
          j++;
        }
      }
      for (int k = 0; k < jud;)
      {
        int testCount2 = (int)(1.0D + Math.random() * count);
        System.out.println("判断随机数为：" + testCount2);
        Questions que = this.tead.findQueByID(testCount2);
        int u = 0;
        for (int t = 0; t < testjudg.length; t++) {
          if (testjudg[t] == testCount2) {
            u++;
          }
        }
        if ((que != null) && (que.getCou_id() == Integer.valueOf(cous).intValue()) && (que.getQ_type() == 3) && (u == 0) && (que.getQ_exist() == 1))
        {
          testjudg[k] = testCount2;
          k++;
        }
      }
      String test_que = "";
      
      test_que = test_que + String.valueOf(testsin[0]);
      for (int q = 1; q < sin_cho; q++) {
        test_que = test_que + "," + testsin[q];
      }
      test_que = test_que + ";";
      
      test_que = test_que + String.valueOf(testmult[0]);
      for (int q = 1; q < mult_cho; q++) {
        test_que = test_que + "," + testmult[q];
      }
      test_que = test_que + ";";
      
      test_que = test_que + String.valueOf(testjudg[0]);
      for (int q = 1; q < jud; q++) {
        test_que = test_que + "," + testjudg[q];
      }
      String deps = this.request.getParameter("deps");
      String testname = this.request.getParameter("test_name");
      String born = this.request.getParameter("birthday");
      
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      Date date = formatter.parse(born);
      String tea_id = this.request.getParameter("tea_id");
      Test newT = new Test();
      newT.setCou_id(Integer.valueOf(cous).intValue());
      newT.setDep_id(Integer.valueOf(deps).intValue());
      newT.setTea_id(Integer.valueOf(tea_id).intValue());
      newT.setTest_name(testname);
      newT.setTest_ques(test_que);
      newT.setTest_time(date);
      newT.setTest_clas(test_clas);
      
      this.session.setAttribute("newTest", newT);
      Questions[] sin_q = new Questions[testsin.length];
      for (int i = 0; i < testsin.length; i++) {
        sin_q[i] = this.tead.findQueByID(testsin[i]);
      }
      Questions[] mult_q = new Questions[testmult.length];
      for (int i = 0; i < testmult.length; i++) {
        mult_q[i] = this.tead.findQueByID(testmult[i]);
      }
      Questions[] judg_q = new Questions[testjudg.length];
      for (int i = 0; i < testjudg.length; i++) {
        judg_q[i] = this.tead.findQueByID(testjudg[i]);
      }
      this.session.setAttribute("testsin", sin_q);
      this.session.setAttribute("testmult", mult_q);
      this.session.setAttribute("testjudg", judg_q);
      
      return "test_success";
    }
    if (method.equals("byTea")) {
      return "test_success";
    }
    return "error";
  }
  
  public static String[][] toArray(String str)
  {
    String[] split = str.split(";");
    String[][] array = new String[split.length][];
    for (int i = 0; i < split.length; i++) {
      array[i] = split[i].split(",");
    }
    return array;
  }
}
