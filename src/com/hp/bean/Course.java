package com.hp.bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_course")
public class Course
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private int cou_id;
  private String cou_name;
  private int cou_credit;
  
  public int getCou_credit()
  {
    return this.cou_credit;
  }
  
  public void setCou_credit(int cou_credit)
  {
    this.cou_credit = cou_credit;
  }
  
  @Id
  @GeneratedValue
  public int getCou_id()
  {
    return this.cou_id;
  }
  
  public void setCou_id(int cou_id)
  {
    this.cou_id = cou_id;
  }
  
  public String getCou_name()
  {
    return this.cou_name;
  }
  
  public void setCou_name(String cou_name)
  {
    this.cou_name = cou_name;
  }
}
