package com.hp.dao.impl;

import com.hp.bean.Classes;
import com.hp.bean.CourseView;
import com.hp.bean.Department;
import com.hp.bean.QuesView;
import com.hp.bean.Questions;
import com.hp.bean.StuTest;
import com.hp.bean.StuTestSimpleView;
import com.hp.bean.StuTestView;
import com.hp.bean.Student;
import com.hp.bean.StudentView;
import com.hp.bean.Test;
import com.hp.bean.TestView;
import com.hp.util.Page;
import java.util.Date;
import java.util.List;

public abstract interface StudentDao
{
  public abstract boolean stuLogin(int paramInt, String paramString);
  
  public abstract List<TestView> findNearTest(StudentView paramStudentView, Date paramDate);
  
  public abstract TestView stuExam(int paramInt);
  
  public abstract StuTest testResult(Test paramTest);
  
  public abstract List<StuTestSimpleView> findAllTest(Page paramPage, int paramInt);
  
  public abstract int findAllTestCount(int paramInt);
  
  public abstract int findTestCountByCourse(int paramInt);
  
  public abstract List<StuTestSimpleView> findTestByCourse(Page paramPage, int paramInt1, int paramInt2);
  
  public abstract int findCouCountByDepId(int paramInt);
  
  public abstract List<CourseView> findCouByDepId(int paramInt);
  
  public abstract StuTestView findDetailedTest(int paramInt);
  
  public abstract StudentView findStuInfo(int paramInt);
  
  public abstract boolean updateStuInfo(Student paramStudent);
  
  public abstract List<Department> findAllDep();
  
  public abstract List<Classes> findAllClass(int paramInt);
  
  public abstract Questions findQuesInfo(int paramInt);
  
  public abstract boolean addStuTest(StuTest paramStuTest);
  
  public abstract QuesView findQuesByQueId(int paramInt);
  
  public abstract List<Student> getStudentAllByDb();
  
  public abstract List<Student> getStudentAllByExcel(String paramString);
  
  public abstract boolean isStudentExist(int paramInt);
}
