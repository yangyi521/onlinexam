package com.hp.bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_stu_test")
public class StuTest
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private int stu_test_id;
  private int test_id;
  private String stu_test_evaluate;
  private int cou_id;
  private String stu_test_time;
  private double stu_test_score;
  private int stu_id;
  private String stu_wrong_ques;
  private String stu_wrong_ans;
  
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
  
  public String getStu_wrong_ques()
  {
    return this.stu_wrong_ques;
  }
  
  public void setStu_wrong_ques(String stu_wrong_ques)
  {
    this.stu_wrong_ques = stu_wrong_ques;
  }
  
  public String getStu_wrong_ans()
  {
    return this.stu_wrong_ans;
  }
  
  public void setStu_wrong_ans(String stu_wrong_ans)
  {
    this.stu_wrong_ans = stu_wrong_ans;
  }
}
