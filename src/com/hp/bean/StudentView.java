package com.hp.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="v_student_view")
public class StudentView
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private int stu_id;
  private String stu_name;
  private String cla_name;
  private String dep_name;
  private String stu_password;
  private int cla_id;
  private int dep_id;
  private String stu_sex;
  private Date stu_born;
  
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
  
  public String getCla_name()
  {
    return this.cla_name;
  }
  
  public void setCla_name(String cla_name)
  {
    this.cla_name = cla_name;
  }
  
  public String getDep_name()
  {
    return this.dep_name;
  }
  
  public void setDep_name(String dep_name)
  {
    this.dep_name = dep_name;
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
  
  public int getDep_id()
  {
    return this.dep_id;
  }
  
  public void setDep_id(int dep_id)
  {
    this.dep_id = dep_id;
  }
}
