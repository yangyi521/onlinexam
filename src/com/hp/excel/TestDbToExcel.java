package com.hp.excel;

import com.hp.bean.Student;
import com.hp.bean.Teacher;
import com.hp.dao.StudentDao;
import com.hp.dao.TeacherDao;
import com.hp.dao.impl.StudentDaoImpl;
import com.hp.dao.impl.TeacherDaoImpl;
import java.io.File;
import java.io.PrintStream;
import java.util.Date;
import java.util.List;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class TestDbToExcel
{
  public static void main(String[] args)
  {
    TestDbToExcel aa = new TestDbToExcel();
    try
    {
      aa.StudentTestDbToExcel();
      System.out.println("StudentTestDbToExcel()执行成功");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public void StudentTestDbToExcel()
    throws Exception
  {
    StudentDao studentDao = new StudentDaoImpl();
    WritableWorkbook wwb = null;
    
    String fileName = "D://onlinexam/StudentTestDbToExcel.xls";
    File file = new File(fileName);
    if (!file.exists()) {
      file.createNewFile();
    }
    wwb = Workbook.createWorkbook(file);
    
    WritableSheet ws = wwb.createSheet("Test Shee 1", 0);
    
    List<Student> list = studentDao.getStudentAllByDb();
    
    Label labelstu_id = new Label(0, 0, "学生编号(stu_id)");
    Label labelstu_name = new Label(1, 0, "学生姓名(stu_name)");
    Label labelstu_password = new Label(2, 0, "学生密码(stu_password)");
    Label labelstu_sex = new Label(3, 0, "学生性别(stu_sex)");
    Label labelstu_born = new Label(4, 0, "学生出生日期(stu_born)");
    Label labelcla_id = new Label(5, 0, "学生所在班级(cla_id)");
    Label labeldep_id = new Label(6, 0, "学生所在系别(dep_id)");
    
    ws.addCell(labelstu_id);
    ws.addCell(labelstu_name);
    ws.addCell(labelstu_password);
    ws.addCell(labelstu_sex);
    ws.addCell(labelstu_born);
    ws.addCell(labelcla_id);
    ws.addCell(labeldep_id);
    for (int i = 0; i < list.size(); i++)
    {
      Label labelId_i = new Label(0, i + 1, String.valueOf(((Student)list.get(i)).getStu_id()));
      Label labelName_i = new Label(1, i + 1, ((Student)list.get(i)).getStu_name());
      Label labelPassword_i = new Label(2, i + 1, ((Student)list.get(i))
        .getStu_password());
      Label labelSex_i = new Label(3, i + 1, ((Student)list.get(i)).getStu_sex());
      Label labelBorn_i = new Label(4, i + 1, ((Student)list.get(i)).getStu_born()
        .toString());
      Label labelClaId_i = new Label(5, i + 1, String.valueOf(((Student)list.get(i)).getCla_id()));
      
      Label labelDepId_i = new Label(6, i + 1, String.valueOf(((Student)list.get(i)).getDep_id()));
      
      ws.addCell(labelId_i);
      ws.addCell(labelName_i);
      ws.addCell(labelPassword_i);
      ws.addCell(labelSex_i);
      ws.addCell(labelBorn_i);
      ws.addCell(labelClaId_i);
      ws.addCell(labelDepId_i);
    }
    wwb.write();
    
    wwb.close();
  }
  
  public void TeacherTestDbToExcel()
    throws Exception
  {
    TeacherDao teacherDao = new TeacherDaoImpl();
    WritableWorkbook wwb = null;
    
    String fileName = "D://onlinexam/TeacherTestDbToExcel.xls";
    File file = new File(fileName);
    if (!file.exists()) {
      file.createNewFile();
    }
    wwb = Workbook.createWorkbook(file);
    
    WritableSheet ws = wwb.createSheet("Test Shee 1", 0);
    
    List<Teacher> list = teacherDao.getTeacherAllByDb();
    
    Label labeltea_id = new Label(0, 0, "教师编号(tea_id)");
    Label labeltea_name = new Label(1, 0, "教师姓名(tea_name)");
    Label labeltea_password = new Label(2, 0, "教师密码(tea_password)");
    Label labeltea_sex = new Label(3, 0, "教师性别(tea_sex)");
    Label labeltea_born = new Label(4, 0, "教师出生日期(tea_born)");
    Label labeldep_id = new Label(6, 0, "教师所在系别(dep_id)");
    
    ws.addCell(labeltea_id);
    ws.addCell(labeltea_name);
    ws.addCell(labeltea_password);
    ws.addCell(labeltea_sex);
    ws.addCell(labeltea_born);
    ws.addCell(labeldep_id);
    for (int i = 0; i < list.size(); i++)
    {
    	//改动过
      Label labelId_i = new Label(0, i + 1, String.valueOf(((Teacher)list.get(i)).getTea_id()));
      String.valueOf(((Teacher)list.get(i)).getTea_id());
      
      Label labelName_i = new Label(1, i + 1, ((Teacher)list.get(i)).getTea_name());
      Label labelPassword_i = new Label(2, i + 1, ((Teacher)list.get(i))
        .getTea_password());
      Label labelSex_i = new Label(3, i + 1, ((Teacher)list.get(i)).getTea_sex());
      Label labelBorn_i = new Label(4, i + 1, ((Teacher)list.get(i)).getTea_born()
        .toString());
      //改动过
      Label labelDepId_i = new Label(6, i + 1, String.valueOf(((Teacher)list.get(i)).getDep_id()));
      
      ws.addCell(labelId_i);
      ws.addCell(labelName_i);
      ws.addCell(labelPassword_i);
      ws.addCell(labelSex_i);
      ws.addCell(labelBorn_i);
      ws.addCell(labelDepId_i);
    }
    wwb.write();
    
    wwb.close();
  }
}
