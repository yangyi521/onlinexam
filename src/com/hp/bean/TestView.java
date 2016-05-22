package com.hp.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="v_test")
public class TestView
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private int test_id;
  private String test_name;
  private String cou_name;
  private int dep_id;
  private String dep_name;
  private Date test_time;
  private String test_ques;
  private int cou_id;
  private String test_clas;
  
  public String getTest_clas()
  {
    return this.test_clas;
  }
  
  public void setTest_clas(String test_clas)
  {
    this.test_clas = test_clas;
  }
  
  public int getCou_id()
  {
    return this.cou_id;
  }
  
  public void setCou_id(int cou_id)
  {
    this.cou_id = cou_id;
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
  public int getTest_id()
  {
    return this.test_id;
  }
  
  public void setTest_id(int test_id)
  {
    this.test_id = test_id;
  }
  
  public String getTest_name()
  {
    return this.test_name;
  }
  
  public void setTest_name(String test_name)
  {
    this.test_name = test_name;
  }
  
  public String getCou_name()
  {
    return this.cou_name;
  }
  
  public void setCou_name(String cou_name)
  {
    this.cou_name = cou_name;
  }
  
  public String getDep_name()
  {
    return this.dep_name;
  }
  
  public void setDep_name(String dep_name)
  {
    this.dep_name = dep_name;
  }
  
  public Date getTest_time()
  {
    return this.test_time;
  }
  
  public void setTest_time(Date test_time)
  {
    this.test_time = test_time;
  }
  
  public String getTest_ques()
  {
    return this.test_ques;
  }
  
  public void setTest_ques(String test_ques)
  {
    this.test_ques = test_ques;
  }
  
  public String toString()
  {
    return 
    
      "TestView [test_id=" + this.test_id + ", test_name=" + this.test_name + ", cou_name=" + this.cou_name + ", dep_name=" + this.dep_name + ", test_time=" + this.test_time + ", test_ques=" + this.test_ques + "]";
  }
}
