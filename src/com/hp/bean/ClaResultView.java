package com.hp.bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="v_cla_result")
public class ClaResultView
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private int stu_id;
  private String stu_name;
  private int cla_id;
  private int dep_id;
  private String cla_name;
  private String dep_name;
  private int stu_test_id;
  private int test_id;
  private String stu_test_evaluate;
  private int cou_id;
  private String stu_wrong_ques;
  private int tea_id;
  private String tea_name;
  private String cou_name;
  private int cou_credit;
  private String test_name;
  
  public String getTest_name()
  {
    return this.test_name;
  }
  
  public void setTest_name(String test_name)
  {
    this.test_name = test_name;
  }
  
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
  
  public String getStu_test_evaluate()
  {
    return this.stu_test_evaluate;
  }
  
  public void setStu_test_evaluate(String stu_test_evaluate)
  {
    this.stu_test_evaluate = stu_test_evaluate;
  }
  
  public int getCou_id()
  {
    return this.cou_id;
  }
  
  public void setCou_id(int cou_id)
  {
    this.cou_id = cou_id;
  }
  
  public String getStu_wrong_ques()
  {
    return this.stu_wrong_ques;
  }
  
  public void setStu_wrong_ques(String stu_wrong_ques)
  {
    this.stu_wrong_ques = stu_wrong_ques;
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
  
  public String toString()
  {
    return 
    
      "ClaResultView [stu_id=" + this.stu_id + ", stu_name=" + this.stu_name + ", cla_id=" + this.cla_id + ", dep_id=" + this.dep_id + ", cla_name=" + this.cla_name + ", dep_name=" + this.dep_name + ", stu_test_id=" + this.stu_test_id + ", test_id=" + this.test_id + ", stu_test_evaluate=" + this.stu_test_evaluate + ", cou_id=" + this.cou_id + ", stu_wrong_ques=" + this.stu_wrong_ques + ", tea_id=" + this.tea_id + ", tea_name=" + this.tea_name + ", cou_name=" + this.cou_name + ", cou_credit=" + this.cou_credit + ", test_name=" + this.test_name + "]";
  }
}
