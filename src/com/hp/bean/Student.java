package com.hp.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_student")
public class Student
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private int stu_id;
  private String stu_name;
  private String stu_password;
  private String stu_sex;
  private Date stu_born;
  private int cla_id;
  private int dep_id;
  
  public Date getStu_born()
  {
    return this.stu_born;
  }
  
  public void setStu_born(Date stu_born)
  {
    this.stu_born = stu_born;
  }
  
  public String getStu_sex()
  {
    return this.stu_sex;
  }
  
  public void setStu_sex(String stu_sex)
  {
    this.stu_sex = stu_sex;
  }
  
  public int getDep_id()
  {
    return this.dep_id;
  }
  
  public void setDep_id(int dep_id)
  {
    this.dep_id = dep_id;
  }
  
  @Id
  public int getStu_id()
  {
    return this.stu_id;
  }
  
  public void setStu_id(int stu_id)
  {
    this.stu_id = stu_id;
  }
  
  public String getStu_name()
  {
    return this.stu_name;
  }
  
  public void setStu_name(String stu_name)
  {
    this.stu_name = stu_name;
  }
  
  public String getStu_password()
  {
    return this.stu_password;
  }
  
  public void setStu_password(String stu_password)
  {
    this.stu_password = stu_password;
  }
  
  public int getCla_id()
  {
    return this.cla_id;
  }
  
  public void setCla_id(int cla_id)
  {
    this.cla_id = cla_id;
  }
  
  public String toString()
  {
    return 
    
      "Student [stu_id=" + this.stu_id + ", stu_name=" + this.stu_name + ", stu_password=" + this.stu_password + ", stu_sex=" + this.stu_sex + ", stu_born=" + this.stu_born + ", cla_id=" + this.cla_id + ", dep_id=" + this.dep_id + "]";
  }
  
  public Student() {}
  
  public Student(int stu_id, String stu_name, String stu_password, String stu_sex, Date stu_born, int cla_id, int dep_id)
  {
    this.stu_id = stu_id;
    this.stu_name = stu_name;
    this.stu_password = stu_password;
    this.stu_sex = stu_sex;
    this.stu_born = stu_born;
    this.cla_id = cla_id;
    this.dep_id = dep_id;
  }
}
