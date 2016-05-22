package com.hp.bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_questions")
public class Questions
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private int q_id;
  private int q_type;
  private byte q_exist;
  private String q_title;
  private String q_itemA;
  private String q_itemB;
  private String q_itemC;
  private String q_itemD;
  private String q_ans;
  private int poi_id;
  private int cou_id;
  
  public String getQ_itemA()
  {
    return this.q_itemA;
  }
  
  public void setQ_itemA(String q_itemA)
  {
    this.q_itemA = q_itemA;
  }
  
  public String getQ_itemB()
  {
    return this.q_itemB;
  }
  
  public void setQ_itemB(String q_itemB)
  {
    this.q_itemB = q_itemB;
  }
  
  public String getQ_itemC()
  {
    return this.q_itemC;
  }
  
  public void setQ_itemC(String q_itemC)
  {
    this.q_itemC = q_itemC;
  }
  
  public String getQ_itemD()
  {
    return this.q_itemD;
  }
  
  public void setQ_itemD(String q_itemD)
  {
    this.q_itemD = q_itemD;
  }
  
  @Id
  public int getQ_id()
  {
    return this.q_id;
  }
  
  public void setQ_id(int q_id)
  {
    this.q_id = q_id;
  }
  
  public int getQ_type()
  {
    return this.q_type;
  }
  
  public void setQ_type(int q_type)
  {
    this.q_type = q_type;
  }
  
  public byte getQ_exist()
  {
    return this.q_exist;
  }
  
  public void setQ_exist(byte q_exist)
  {
    this.q_exist = q_exist;
  }
  
  public String getQ_title()
  {
    return this.q_title;
  }
  
  public void setQ_title(String q_title)
  {
    this.q_title = q_title;
  }
  
  public String getQ_ans()
  {
    return this.q_ans;
  }
  
  public void setQ_ans(String q_ans)
  {
    this.q_ans = q_ans;
  }
  
  public int getPoi_id()
  {
    return this.poi_id;
  }
  
  public void setPoi_id(int poi_id)
  {
    this.poi_id = poi_id;
  }
  
  public int getCou_id()
  {
    return this.cou_id;
  }
  
  public void setCou_id(int cou_id)
  {
    this.cou_id = cou_id;
  }
}

