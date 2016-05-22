package com.hp.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="v_teacher")
public class TeacherView
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private int tea_id;
  private String tea_name;
  private String tea_password;
  private String tea_sex;
  private Date tea_born;
  private int dep_id;
  private String dep_name;
  
  public String getTea_sex()
  {
    return this.tea_sex;
  }
  
  public void setTea_sex(String tea_sex)
  {
    this.tea_sex = tea_sex;
  }
  
  @Id
  public int getTea_id()
  {
    return this.tea_id;
  }
  
  public void setTea_id(int tea_id)
  {
    this.tea_id = tea_id;
  }
  
  public String getTea_name()
  {
    return this.tea_name;
  }
  
  public void setTea_name(String tea_name)
  {
    this.tea_name = tea_name;
  }
  
  public String getTea_password()
  {
    return this.tea_password;
  }
  
  public void setTea_password(String tea_password)
  {
    this.tea_password = tea_password;
  }
  
  public Date getTea_born()
  {
    return this.tea_born;
  }
  
  public void setTea_born(Date tea_born)
  {
    this.tea_born = tea_born;
  }
  
  public int getDep_id()
  {
    return this.dep_id;
  }
  
  public void setDep_id(int dep_id)
  {
    this.dep_id = dep_id;
  }
  
  public String getDep_name()
  {
    return this.dep_name;
  }
  
  public void setDep_name(String dep_name)
  {
    this.dep_name = dep_name;
  }
  
  public String toString()
  {
    return 
    
      "TeacherView [tea_id=" + this.tea_id + ", tea_name=" + this.tea_name + ", tea_password=" + this.tea_password + ", tea_sex=" + this.tea_sex + ", tea_born=" + this.tea_born + ", dep_id=" + this.dep_id + ", dep_name=" + this.dep_name + "]";
  }
}