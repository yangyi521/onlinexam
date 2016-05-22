package com.hp.dao.impl;

import com.hp.bean.Classes;
import com.hp.bean.ClassesView;
import com.hp.bean.Course;
import com.hp.bean.CourseView;
import com.hp.bean.Department;
import com.hp.bean.Manager;
import com.hp.bean.Student;
import com.hp.bean.StudentView;
import com.hp.bean.TeaCouView;
import com.hp.bean.TeachCourse;
import com.hp.bean.Teacher;
import com.hp.bean.TeacherView;
import com.hp.util.Page;
import java.util.List;

public abstract interface ManagerDao
{
  public abstract boolean sysLogin(String paramString1, String paramString2);
  
  public abstract Manager findManagerInfo(String paramString);
  
  public abstract int findAllCourseCount();
  
  public abstract List<CourseView> findAllCourse(Page paramPage);
  
  public abstract int findCourseCountByDep(String paramString);
  
  public abstract List<CourseView> findCourseByDep(Page paramPage, String paramString);
  
  public abstract int findCourseCountByCouName(String paramString);
  
  public abstract List<CourseView> findCourseByCouName(Page paramPage, String paramString);
  
  public abstract Course findCourseByName(String paramString);
  
  public abstract CourseView findCouByCouId(int paramInt);
  
  public abstract int findAllTeaCount();
  
  public abstract List<TeacherView> findAllTea(Page paramPage);
  
  public abstract int findTeaCountByCouName(String paramString);
  
  public abstract List<TeaCouView> findTeaByCouName(Page paramPage, String paramString);
  
  public abstract int findTeaCountByTeaName(String paramString);
  
  public abstract List<TeacherView> findTeaByTeaName(Page paramPage, String paramString);
  
  public abstract int findTeaCountByTeaId(int paramInt);
  
  public abstract TeacherView findTeaByTeaId(Page paramPage, int paramInt);
  
  public abstract int findAllStuCount();
  
  public abstract List<StudentView> findAllStu(Page paramPage);
  
  public abstract int findStuCountByClaName(String paramString);
  
  public abstract List<StudentView> findStuByClaName(Page paramPage, String paramString);
  
  public abstract int findStuCountByStuName(String paramString);
  
  public abstract List<StudentView> findStuByStuName(Page paramPage, String paramString);
  
  public abstract int findStuCountByStuId(int paramInt);
  
  public abstract StudentView findStuByStuId(Page paramPage, int paramInt);
  
  public abstract Course findSimpleCouByCouId(int paramInt);
  
  public abstract boolean deleteTeacher(int paramInt);
  
  public abstract boolean deleteStudent(int paramInt);
  
  public abstract boolean deleteCourse(int paramInt);
  
  public abstract boolean deleteCla(int paramInt);
  
  public abstract ClassesView findClaByClaId(int paramInt);
  
  public abstract List<Classes> findSimpleCla();
  
  public abstract int findAllClaCount();
  
  public abstract List<ClassesView> findAllCla(Page paramPage);
  
  public abstract int findfindAllClaCountByDep(int paramInt);
  
  public abstract List<ClassesView> findAllClaByDep(Page paramPage, int paramInt);
  
  public abstract List<Department> findSimpleDep();
  
  public abstract List<Teacher> findSimpleTea();
  
  public abstract TeachCourse findTeaCou(int paramInt1, int paramInt2);
  
  public abstract boolean addCourse(Course paramCourse);
  
  public abstract boolean addTeaCou(TeachCourse paramTeachCourse);
  
  public abstract boolean addTeacher(Teacher paramTeacher);
  
  public abstract boolean addStudent(Student paramStudent);
  
  public abstract boolean addCla(Classes paramClasses);
  
  public abstract boolean updateCla(Classes paramClasses);
  
  public abstract boolean updateTeacher(Teacher paramTeacher);
  
  public abstract boolean updateStudent(Student paramStudent);
  
  public abstract boolean updateManagerInfo(Manager paramManager);
  
  public abstract boolean updateCourse(Course paramCourse);
  
  public abstract boolean updateCouView(CourseView paramCourseView);
  
  public abstract boolean updateTeachCou(TeachCourse paramTeachCourse);
}
