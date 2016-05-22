package com.hp.bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_teach_course")
public class TeachCourse
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private int tea_cou_id;
  private int cou_id;
  private int tea_id;
  private int cla_id;
  
  @Id
  public int getTea_cou_id()
  {
    return this.tea_cou_id;
  }
  
  public void setTea_cou_id(int tea_cou_id)
  {
    this.tea_cou_id = tea_cou_id;
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
  
  public int getCla_id()
  {
    return this.cla_id;
  }
  
  public void setCla_id(int cla_id)
  {
    this.cla_id = cla_id;
  }
}
