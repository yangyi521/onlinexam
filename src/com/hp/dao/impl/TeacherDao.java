package com.hp.dao.impl;

import com.hp.bean.ClaResultView;
import com.hp.bean.Course;
import com.hp.bean.Point;
import com.hp.bean.QuesView;
import com.hp.bean.Questions;
import com.hp.bean.StuSTestView;
import com.hp.bean.StuTestView;
import com.hp.bean.TeaCouView;
import com.hp.bean.TeaTestView;
import com.hp.bean.Teacher;
import com.hp.bean.TeacherView;
import com.hp.bean.Test;
import com.hp.util.Page;
import java.util.List;

public abstract interface TeacherDao
{
  public abstract boolean teaLogin(int paramInt, String paramString);
  
  public abstract Questions findQueByTitle(String paramString);
  
  public abstract boolean addQuestions(Questions paramQuestions);
  
  public abstract Questions findQueByID(int paramInt);
  
  public abstract Point findPoiById(int paramInt);
  
  public abstract boolean updateQue(Questions paramQuestions);
  
  public abstract boolean deleteQue(int paramInt);
  
  public abstract int findAllQueCountByTitle(String paramString);
  
  public abstract int findAllQuesCount();
  
  public abstract List<QuesView> likeFindQueByTitle(String paramString, Page paramPage);
  
  public abstract int findQueCountByCouName(String paramString);
  
  public abstract List<QuesView> findQueByCouName(String paramString, Page paramPage);
  
  public abstract List<QuesView> findAllQues(Page paramPage);
  
  public abstract int findCountByStuName(String paramString);
  
  public abstract List<StuSTestView> findByStuName(Page paramPage, String paramString, int paramInt);
  
  public abstract int findCountByClaName(String paramString);
  
  public abstract List<StuSTestView> findByClaName(Page paramPage, String paramString, int paramInt);
  
  public abstract int findCountByCouName(String paramString);
  
  public abstract List<StuSTestView> findByCouName(Page paramPage, String paramString, int paramInt);
  
  public abstract int findAllStuTestCount();
  
  public abstract int findAllStuTestCountByTea(int paramInt);
  
  public abstract List<StuSTestView> findAllStuTestByTea(Page paramPage, int paramInt);
  
  public abstract List<StuTestView> findAllStuTest(Page paramPage);
  
  public abstract TeacherView findTeaInfo(int paramInt);
  
  public abstract List<TeaCouView> findAllTeach(int paramInt);
  
  public abstract List<TeaCouView> findAllTeach2(int paramInt);
  
  public abstract List<TeaTestView> findNearTest(int paramInt);
  
  public abstract List<StuTestView> findByStuId(int paramInt);
  
  public abstract boolean updateTeaInfo(Teacher paramTeacher);
  
  public abstract List<Course> findAllCou(int paramInt);
  
  public abstract List<TeaCouView> findAllTeaCla(int paramInt);
  
  public abstract List<Point> findAllPoi(int paramInt);
  
  public abstract boolean addTest(Test paramTest);
  
  public abstract List<Teacher> getTeacherAllByDb();
  
  public abstract List<Teacher> getTeacherAllByExcel(String paramString);
  
  public abstract boolean isTeacherExist(int paramInt);
  
  public abstract int findAllClaResultCount(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public abstract List<ClaResultView> findAllClaResult(Page paramPage, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public abstract List<ClaResultView> findAllCla(int paramInt);
}
