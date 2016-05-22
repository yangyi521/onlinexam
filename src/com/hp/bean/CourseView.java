package com.hp.bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="v_course")
public class CourseView
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private int cou_id;
  private int tea_id;
  private int tea_cou_id;
  private String cou_name;
  private int cou_credit;
  private String tea_name;
  private int dep_id;
  private String dep_name;
  private int cla_id;
  private String cla_name;
  
  public int getCla_id()
  {
    return this.cla_id;
  }
  
  public void setCla_id(int cla_id)
  {
    this.cla_id = cla_id;
  }
  
  public String getCla_name()
  {
    return this.cla_name;
  }
  
  public void setCla_name(String cla_name)
  {
    this.cla_name = cla_name;
  }
  
  public int getCou_id()
  {
    return this.cou_id;
  }
  
  public void setCou_id(int cou_id)
  {
    this.cou_id = cou_id;
  }
  
  public int getTea_id()
  {
    return this.tea_id;
  }
  
  public void setTea_id(int tea_id)
  {
    this.tea_id = tea_id;
  }
  
  @Id
  public int getTea_cou_id()
  {
    return this.tea_cou_id;
  }
  
  public void setTea_cou_id(int tea_cou_id)
  {
    this.tea_cou_id = tea_cou_id;
  }
  
  public String getCou_name()
  {
    return this.cou_name;
  }
  
  public void setCou_name(String cou_name)
  {
    this.cou_name = cou_name;
  }
  
  public int getCou_credit()
  {
    return this.cou_credit;
  }
  
  public void setCou_credit(int cou_credit)
  {
    this.cou_credit = cou_credit;
  }
  
  public String getTea_name()
  {
    return this.tea_name;
  }
  
  public void setTea_name(String tea_name)
  {
    this.tea_name = tea_name;
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
}
