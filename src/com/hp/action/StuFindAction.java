package com.hp.action;

import com.hp.bean.CourseView;
import com.hp.bean.Questions;
import com.hp.bean.StuTestSimpleView;
import com.hp.bean.StuTestView;
import com.hp.bean.StudentView;
import com.hp.bean.TestView;
import com.hp.dao.StudentDao;
import com.hp.dao.impl.StudentDaoImpl;
import com.hp.util.Page;
import com.hp.util.Pageutil;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StuFindAction
  extends SuperAction
{
  private static final long serialVersionUID = 1L;
  StudentDao stuDao = new StudentDaoImpl();
  
  public String pastExam()
    throws Exception
  {
    String currentPage = this.request.getParameter("currentPage");
    if (currentPage == null) {
      currentPage = "0";
    }
    StudentView stu = (StudentView)this.session.getAttribute("student");
    Page page = new Page();
    page = Pageutil.createPage(10, this.stuDao.findAllTestCount(stu.getStu_id()), Integer.valueOf(currentPage).intValue());
    List<StuTestSimpleView> stuv = this.stuDao.findAllTest(page, stu.getStu_id());
    List<CourseView> ccc = this.stuDao.findCouByDepId(stu.getDep_id());
    this.session.setAttribute("cousss", ccc);
    this.session.setAttribute("stuTestSimpleView", stuv);
    this.session.setAttribute("page", page);
    return "pastExam_success";
  }
  
  public String findPastExam()
  {
    String cou_id = this.request.getParameter("select");
    
    String currentPage = this.request.getParameter("currentPage");
    StudentView student = (StudentView)this.session.getAttribute("student");
    Page page = new Page();
    page = Pageutil.createPage(10, this.stuDao.findTestCountByCourse(Integer.valueOf(cou_id).intValue()), Integer.valueOf(currentPage).intValue());
    List<StuTestSimpleView> st = this.stuDao.findTestByCourse(page, Integer.valueOf(cou_id).intValue(), student.getStu_id());
    
    this.session.setAttribute("select", cou_id);
    this.session.setAttribute("page", page);
    this.session.setAttribute("stuTestSimpleView", st);
    return "findPastExam_success";
  }
  
  public String detailedTest()
    throws Exception
  {
    StudentDao stuDao = new StudentDaoImpl();
    
    String stu_test_id = this.request.getParameter("stu_test_id");
    StuTestView stuTest = stuDao.findDetailedTest(Integer.valueOf(stu_test_id).intValue());
    
    String[][] test_q = toArray(stuTest.getTest_ques());
    
    int[] test_sin_que = new int[test_q[0].length];
    Questions[] sin_ques = new Questions[test_q[0].length];
    for (int i = 0; i < test_q[0].length; i++)
    {
      test_sin_que[i] = Integer.valueOf(test_q[0][i]).intValue();
      sin_ques[i] = stuDao.findQuesInfo(test_sin_que[i]);
    }
    int[] test_mult_que = new int[test_q[1].length];
    Questions[] mult_ques = new Questions[test_q[1].length];
    for (int i = 0; i < test_q[1].length; i++)
    {
      test_mult_que[i] = Integer.valueOf(test_q[1][i]).intValue();
      mult_ques[i] = stuDao.findQuesInfo(test_mult_que[i]);
    }
    int[] test_judg_que = new int[test_q[2].length];
    Questions[] judg_ques = new Questions[test_q[2].length];
    for (int i = 0; i < test_q[2].length; i++)
    {
      test_judg_que[i] = Integer.valueOf(test_q[2][i]).intValue();
      judg_ques[i] = stuDao.findQuesInfo(test_judg_que[i]);
    }
    String[] stu_wrong = stuTest.getStu_wrong_ques().split(",");
    System.out.println("stu_wrong=" + stu_wrong.length);
    int[] stu_wrong_ques = new int[stu_wrong.length];
    if (stu_wrong_ques.length == 0)
    {
      this.session.setAttribute("stu_wrong_ques", stu_wrong_ques);
    }
    else
    {
      for (int i = 0; i < stu_wrong.length; i++) {
        stu_wrong_ques[i] = Integer.valueOf(stu_wrong[i]).intValue();
      }
      this.session.setAttribute("stu_wrong_ques", stu_wrong_ques);
    }
    if (Integer.valueOf(stu_wrong_ques[0]).intValue() != 0)
    {
      String[] stu_wrong_ans = stuTest.getStu_wrong_ans().split(",");
      
      this.session.setAttribute("stu_wrong_ans", stu_wrong_ans);
    }
    this.session.setAttribute("stuDetailedTest", stuTest);
    this.session.setAttribute("sin_ques", sin_ques);
    this.session.setAttribute("mult_ques", mult_ques);
    this.session.setAttribute("judg_ques", judg_ques);
    
    return "detailedTest_success";
  }
  
  public String personalInfo()
    throws Exception
  {
    StudentDao stuDao = new StudentDaoImpl();
    StudentView stu = stuDao.findStuInfo(Integer.valueOf(this.request.getParameter("stu_id")).intValue());
    this.session.setAttribute("student", stu);
    return "personalInfo_success";
  }
  
  public void sendMessage(String content)
    throws IOException
  {
    this.response.setCharacterEncoding("UTF-8");
    this.response.getWriter().write(content);
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
  
  public String exam()
  {
    int test_id = Integer.valueOf(this.request.getParameter("test_id")).intValue();
    StudentDao stuDao = new StudentDaoImpl();
    TestView test = stuDao.stuExam(test_id);
    
    String[][] test_q = toArray(test.getTest_ques());
    
    int[] test_sin_que = new int[test_q[0].length];
    Questions[] sin_ques = new Questions[test_q[0].length];
    for (int i = 0; i < test_q[0].length; i++)
    {
      test_sin_que[i] = Integer.valueOf(test_q[0][i]).intValue();
      sin_ques[i] = stuDao.findQuesInfo(test_sin_que[i]);
      System.out.println("test_sin_que[i]=" + test_sin_que[i]);
    }
    int[] test_mult_que = new int[test_q[1].length];
    Questions[] mult_ques = new Questions[test_q[1].length];
    for (int i = 0; i < test_q[1].length; i++)
    {
      test_mult_que[i] = Integer.valueOf(test_q[1][i]).intValue();
      mult_ques[i] = stuDao.findQuesInfo(test_mult_que[i]);
      System.out.println("test_sin_que[i]=" + test_mult_que[i]);
    }
    int[] test_judg_que = new int[test_q[2].length];
    Questions[] judg_ques = new Questions[test_q[2].length];
    for (int i = 0; i < test_q[2].length; i++)
    {
      test_judg_que[i] = Integer.valueOf(test_q[2][i]).intValue();
      judg_ques[i] = stuDao.findQuesInfo(test_judg_que[i]);
      System.out.println("test_sin_que[i]=" + test_judg_que[i]);
    }
    this.session.setAttribute("test", test);
    this.session.setAttribute("t_sin", sin_ques);
    this.session.setAttribute("t_mult", mult_ques);
    this.session.setAttribute("t_judg", judg_ques);
    return "exam_success";
  }
  
  public String main()
  {
    int usn = Integer.valueOf(this.request.getParameter("stu_id")).intValue();
    Date date = new Date();
    StudentView student = this.stuDao.findStuInfo(usn);
    List<TestView> list = this.stuDao.findNearTest(student, date);
    this.session.setAttribute("student", student);
    this.session.setAttribute("near_list", list);
    return "main_success";
  }
}
