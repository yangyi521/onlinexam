package com.hp.action;

import com.hp.bean.ClaResultView;
import com.hp.bean.Department;
import com.hp.bean.Point;
import com.hp.bean.QuesView;
import com.hp.bean.Questions;
import com.hp.bean.StuSTestView;
import com.hp.bean.StuTestView;
import com.hp.bean.TeaCouView;
import com.hp.bean.TeaTestView;
import com.hp.bean.TeacherView;
import com.hp.dao.ManagerDao;
import com.hp.dao.TeacherDao;
import com.hp.dao.impl.ManagerDaoImpl;
import com.hp.dao.impl.TeacherDaoImpl;
import com.hp.util.Page;
import com.hp.util.Pageutil;
import com.hp.util.PoiTitleCount;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class TeaFindAction
  extends SuperAction
{
  private static final long serialVersionUID = 1L;
  TeacherDao tead = new TeacherDaoImpl();
  
  public List<QuesView> likeFindQueByTitle(String q_title, Page page)
  {
    page = Pageutil.createPage(page.getEveryPage(), this.tead.findAllQueCountByTitle(q_title), page.getCurrentPage());
    
    List<QuesView> list = this.tead.likeFindQueByTitle(q_title, page);
    
    return list;
  }
  
  public String quesBank()
  {
    Page page = new Page();
    page.setEveryPage(10);
    
    page.setCurrentPage(Integer.valueOf(this.request.getParameter("currentPage")).intValue());
    String q_title = this.request.getParameter("q_title");
    
    page = Pageutil.createPage(page.getEveryPage(), this.tead.findAllQuesCount(), page.getCurrentPage());
    List<QuesView> list = this.tead.findAllQues(page);
    this.session.setAttribute("page", page);
    this.session.setAttribute("queList", list);
    return "quesBank_success";
  }
  
  public String queryResult()
  {
    String tea_id = this.request.getParameter("tea_id");
    
    Page page = new Page();
    page.setEveryPage(10);
    page.setCurrentPage(Integer.valueOf(this.request.getParameter("currentPage")).intValue());
    
    page = Pageutil.createPage(10, this.tead.findAllStuTestCountByTea(Integer.valueOf(tea_id).intValue()), page.getCurrentPage());
    List<StuSTestView> list = this.tead.findAllStuTestByTea(page, Integer.valueOf(tea_id).intValue());
    this.session.setAttribute("stuTestList", list);
    this.session.setAttribute("page", page);
    return "queryResult_success";
  }
  
  public String findQueryResult()
  {
    String type = this.request.getParameter("findType");
    String name = this.request.getParameter("name");
    if (type == null) {
      type = (String)this.session.getAttribute("selectk");
    }
    if (name == null) {
      name = (String)this.session.getAttribute("quesname");
    }
    String teaid = this.request.getParameter("tea_id");
    int tea_id = Integer.valueOf(teaid).intValue();
    
    Page page = new Page();
    page.setEveryPage(10);
    page.setCurrentPage(Integer.valueOf(this.request.getParameter("currentPage")).intValue());
    if (type.equals("stuName"))
    {
      page = Pageutil.createPage(10, this.tead.findCountByStuName(name), page.getCurrentPage());
      List<StuSTestView> list = this.tead.findByStuName(page, name, tea_id);
      
      this.session.setAttribute("selectk", type);
      this.session.setAttribute("quesname", name);
      this.session.setAttribute("page", page);
      this.session.setAttribute("stuTestList", list);
      return "findQueryResult_success";
    }
    if (type.equals("claName"))
    {
      page = Pageutil.createPage(10, this.tead.findCountByClaName(name), page.getCurrentPage());
      List<StuSTestView> list = this.tead.findByClaName(page, name, tea_id);
      
      this.session.setAttribute("selectk", type);
      this.session.setAttribute("quesname", name);
      this.session.setAttribute("page", page);
      this.session.setAttribute("stuTestList", list);
      return "findQueryResult_success";
    }
    if (type.equals("couName"))
    {
      page = Pageutil.createPage(page.getEveryPage(), this.tead.findCountByCouName(name), page.getCurrentPage());
      List<StuSTestView> list = this.tead.findByCouName(page, name, tea_id);
      
      this.session.setAttribute("selectk", type);
      this.session.setAttribute("quesname", name);
      this.session.setAttribute("stuTestList", list);
      this.session.setAttribute("page", page);
      return "findQueryResult_success";
    }
    return "error";
  }
  
  public String detailedTest()
  {
    int stu_id = Integer.valueOf(this.request.getParameter("stu_id")).intValue();
    List<StuTestView> stut = this.tead.findByStuId(stu_id);
    this.session.setAttribute("stuTestById", stut);
    return "detailedTest_success";
  }
  
  public String takeTestQue()
  {
    int tea_id = Integer.valueOf(this.request.getParameter("tea_id")).intValue();
    List<TeaCouView> teav = this.tead.findAllTeach(tea_id);
    ManagerDao sysd = new ManagerDaoImpl();
    List<Department> ndep = sysd.findSimpleDep();
    List<TeaCouView> tcv = this.tead.findAllTeaCla(tea_id);
    this.session.setAttribute("teaAllCla", tcv);
    this.session.setAttribute("ndep", ndep);
    this.session.setAttribute("teaAllTeach", teav);
    return "takeTestQue_success";
  }
  
  public String tpersonalInfo()
  {
    TeacherDao teaDao = new TeacherDaoImpl();
    List<TeaCouView> teaTeach = teaDao.findAllTeach2(Integer.valueOf(this.request.getParameter("tea_id")).intValue());
    TeacherView tv = teaDao.findTeaInfo(Integer.valueOf(this.request.getParameter("tea_id")).intValue());
    int age = getAge(tv.getTea_born());
    this.session.setAttribute("tage", Integer.valueOf(age));
    if (teaTeach.size() != 0) {
      this.session.setAttribute("teaTeach", teaTeach);
    }
    return "tpersonalInfo_success";
  }
  
  public String tmain()
  {
    int tea_id = Integer.valueOf(this.request.getParameter("tea_id")).intValue();
    TeacherView t = this.tead.findTeaInfo(tea_id);
    List<TeaTestView> list = this.tead.findNearTest(tea_id);
    this.session.setAttribute("teacher", t);
    this.session.setAttribute("near_list", list);
    return "tmain_success";
  }
  
  public String findQuesBank()
  {
    String selectk = this.request.getParameter("selectk");
    if (selectk == null) {
      selectk = (String)this.session.getAttribute("selectk");
    }
    String name = this.request.getParameter("quesname");
    if (name == null) {
      name = (String)this.session.getAttribute("quesname");
    }
    if (selectk.equals("queTitle"))
    {
      Page page = new Page();
      page.setEveryPage(10);
      page.setCurrentPage(Integer.valueOf(this.request.getParameter("currentPage")).intValue());
      page = Pageutil.createPage(10, this.tead.findAllQueCountByTitle(name), page.getCurrentPage());
      List<QuesView> list = this.tead.likeFindQueByTitle(name, page);
      
      this.session.setAttribute("quesname", name);
      this.session.setAttribute("selectk", selectk);
      this.session.setAttribute("page", page);
      this.session.setAttribute("queList", list);
      return "findQuesBank_success";
    }
    if (selectk.equals("couName"))
    {
      Page page = new Page();
      page.setEveryPage(10);
      page.setCurrentPage(Integer.valueOf(this.request.getParameter("currentPage")).intValue());
      page = Pageutil.createPage(10, this.tead.findQueCountByCouName(name), page.getCurrentPage());
      
      List<QuesView> list = this.tead.findQueByCouName(name, page);
      
      this.session.setAttribute("quesname", name);
      this.session.setAttribute("selectk", selectk);
      this.session.setAttribute("page", page);
      this.session.setAttribute("queList", list);
      return "findQuesBank_success";
    }
    return "error";
  }
  
  public String claResult()
  {
    int tea_id = Integer.valueOf(this.request.getParameter("tea_id")).intValue();
    
    Page page = new Page();
    page.setEveryPage(10);
    page.setCurrentPage(Integer.valueOf(this.request.getParameter("currentPage")).intValue());
    
    StringBuffer sb = new StringBuffer();
    sb.append("");
    
    List<ClaResultView> tcv = this.tead.findAllCla(tea_id);
    for (ClaResultView tv : tcv)
    {
      List<String> array = new ArrayList();
      sb.setLength(0);
      
      page = Pageutil.createPage(10, this.tead.findAllClaResultCount(tea_id, tv.getCla_id(), tv.getCou_id(), tv.getTest_id()), page.getCurrentPage());
      List<ClaResultView> crv = this.tead.findAllClaResult(page, tea_id, tv.getCla_id(), tv.getCou_id(), tv.getTest_id());
      
      System.out.println("crv的大小：" + crv.size());
      for (int j = 0; j < crv.size(); j++) {
        if (((ClaResultView)crv.get(j)).getStu_wrong_ques().equals("0"))
        {
          crv.remove(j);
          j--;
        }
        else
        {
          sb.append(((ClaResultView)crv.get(j)).getStu_wrong_ques() + ",");
        }
      }
      System.out.println("sb去掉逗号之前" + sb.toString());
      String str = sb.toString();
      if ((str.equals("")) || (str == null))
      {
        tv.setStu_test_evaluate("知识点掌握的很好");
      }
      else
      {
        str = str.substring(0, str.length() - 1);
        
        System.out.println("str去掉逗号之后" + str.toString());
        String[] split = str.split(",");
        for (int i = 0; i < split.length; i++)
        {
          Questions q = this.tead.findQueByID(Integer.valueOf(split[i]).intValue());
          
          Point pp = this.tead.findPoiById(q.getPoi_id());
          array.add(pp.getPoi_title());
        }
        Iterator<Map.Entry> it = PoiTitleCount.CountPointTitle(array);
        String result = "";
        System.out.println("result置空后" + result);
        while (it.hasNext())
        {
          Map.Entry<String, Integer> entry = (Map.Entry)it.next();
          result = result + "对于知识点 " + (String)entry.getKey() + "错误次数达到 " + entry.getValue() + " ��; <br />";
        }
        tv.setStu_test_evaluate(result);
        System.out.println("result此刻为" + result);
      }
    }
    this.session.setAttribute("allClaResult", tcv);
    this.session.setAttribute("page", page);
    
    return "claResult_success";
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

