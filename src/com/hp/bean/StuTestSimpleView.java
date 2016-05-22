package com.hp.bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="v_stu_simple_test_view")
public class StuTestSimpleView
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private int stu_test_id;
  private int test_id;
  private String test_name;
  private String stu_test_time;
  private double stu_test_score;
  private int stu_id;
  private String cou_name;
  private int cou_id;
  
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
  
  @Id
  public int getStu_test_id()
  {
    return this.stu_test_id;
  }
  
  public void setStu_test_id(int stu_test_id)
  {
    this.stu_test_id = stu_test_id;
  }
  
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
  
  public String getStu_test_time()
  {
    return this.stu_test_time;
  }
  
  public void setStu_test_time(String stu_test_time)
  {
    this.stu_test_time = stu_test_time;
  }
  
  public double getStu_test_score()
  {
    return this.stu_test_score;
  }
  
  public void setStu_test_score(double stu_test_score)
  {
    this.stu_test_score = stu_test_score;
  }
  
  public int getStu_id()
  {
    return this.stu_id;
  }
  
  public void setStu_id(int stu_id)
  {
    this.stu_id = stu_id;
  }
  
  public String toString()
  {
    return 
    
      "StuTestSimpleView [stu_test_id=" + this.stu_test_id + ", test_id=" + this.test_id + ", test_name=" + this.test_name + ", stu_test_time=" + this.stu_test_time + ", stu_test_score=" + this.stu_test_score + ", stu_id=" + this.stu_id + ", cou_name=" + this.cou_name + ", cou_id=" + this.cou_id + "]";
  }
}
