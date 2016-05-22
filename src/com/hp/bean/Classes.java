package com.hp.bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_class")
public class Classes
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private int cla_id;
  private String cla_name;
  private int dep_id;
  
  @Id
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
  
  public int getDep_id()
  {
    return this.dep_id;
  }
  
  public void setDep_id(int dep_id)
  {
    this.dep_id = dep_id;
  }
  
  public String toString()
  {
    return 
      "Classes [cla_id=" + this.cla_id + ", cla_name=" + this.cla_name + ", dep_id=" + this.dep_id + "]";
  }
}
