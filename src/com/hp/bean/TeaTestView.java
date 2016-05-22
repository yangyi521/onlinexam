package com.hp.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="v_tea_test")
public class TeaTestView
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private int test_id;
  private String test_name;
  private int cou_id;
  private int dep_id;
  private Date test_time;
  private String test_ques;
  private int tea_id;
  private String tea_name;
  private String tea_sex;
  private Date tea_born;
  private String tea_password;
  private String dep_name;
  private String cou_name;
  
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
  
  public String getTea_sex()
  {
    return this.tea_sex;
  }
  
  public void setTea_sex(String tea_sex)
  {
    this.tea_sex = tea_sex;
  }
  
  public Date getTea_born()
  {
    return this.tea_born;
  }
  
  public void setTea_born(Date tea_born)
  {
    this.tea_born = tea_born;
  }
  
  public String getTea_password()
  {
    return this.tea_password;
  }
  
  public void setTea_password(String tea_password)
  {
    this.tea_password = tea_password;
  }
  
  public String getDep_name()
  {
    return this.dep_name;
  }
  
  public void setDep_name(String dep_name)
  {
    this.dep_name = dep_name;
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
