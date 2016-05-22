package com.hp.excel;

import com.hp.bean.Student;
import com.hp.bean.Teacher;
import com.hp.dao.StudentDao;
import com.hp.dao.TeacherDao;
import com.hp.dao.impl.StudentDaoImpl;
import com.hp.dao.impl.TeacherDaoImpl;
import com.hp.util.DBhelper;
import java.io.PrintStream;
import java.util.List;

public class TestExcelToDb
{
  public static void main(String[] args)
    throws Exception
  {
    TestExcelToDb aa = new TestExcelToDb();
    aa.StudentTestExcelToDb();
    System.out.println("StudentTestExcelToDb()��������");
  }
  
  public void StudentTestExcelToDb()
  {
    StudentDao studentDao = new StudentDaoImpl();
    
    List<Student> listExcel = studentDao
      .getStudentAllByExcel("D://onlinexam/StudentTestDbToExcel.xls");
    
    DBhelper db = new DBhelper();
    for (Student student : listExcel)
    {
      int id = student.getStu_id();
      if (!studentDao.isStudentExist(id))
      {
        String sql = "insert into t_student (stu_id,stu_name,stu_password,stu_sex,stu_born,cla_id,dep_id) values(?,?,?,?,?,?,?)";
        
        Object[] str = { student.getStu_id(), 
          student.getStu_name(), student.getStu_password(), 
          student.getStu_sex(), student.getStu_born(), 
          student.getCla_id(), student.getDep_id() };
        db.AddU(sql, str);
        System.out.println("TestExcelToDb������StudentTestExcelToDb()����1");
      }
      else
      {
        String sql = "update t_student set stu_name=?,stu_password=?,stu_sex=?,stu_born=?,cla_id=?,dep_id=? where stu_id=?";
        
        Object[] str = { student.getStu_name(), 
          student.getStu_password(), student.getStu_sex(), 
          student.getStu_born(), 
          student.getCla_id(), student.getDep_id(), id };
        db.AddU(sql, str);
        System.out.println("TestExcelToDb������StudentTestExcelToDb()����2");
      }
    }
  }
  
  public void TeacherTestExcelToDb()
  {
    TeacherDao teacherDao = new TeacherDaoImpl();
    
    List<Teacher> listExcel = teacherDao.getTeacherAllByExcel("D://onlinexam/TeacherTestDbToExcel.xls");
    
    DBhelper db = new DBhelper();
    for (Teacher teacher : listExcel)
    {
      int id = teacher.getTea_id();
      if (!teacherDao.isTeacherExist(id))
      {
        String sql = "insert into t_teacher (tea_id,tea_name,tea_password,tea_sex,tea_born,dep_id) values(?,?,?,?,?,?)";
        
        Object[] str = { teacher.getTea_id(), 
          teacher.getTea_name(), teacher.getTea_password(), 
          teacher.getTea_sex(), teacher.getTea_born(), teacher.getDep_id() };
        db.AddU(sql, str);
        System.out.println("TestExcelToDb������TeacherTestExcelToDb()����1");
      }
      else
      {
        String sql = "update t_teacher set tea_name=?,tea_password=?,tea_sex=?,tea_born=?,dep_id=? where tea_id=?";
        
        Object[] str = { teacher.getTea_name(), teacher.getTea_password(), 
          teacher.getTea_sex(), teacher.getTea_born(), teacher.getDep_id(), id };
        db.AddU(sql, str);
        System.out.println("TestExcelToDb������TeacherTestExcelToDb()����2");
      }
    }
  }
}
