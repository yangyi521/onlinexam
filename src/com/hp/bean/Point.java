package com.hp.bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_point")
public class Point
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private int poi_id;
  private String poi_title;
  private String poi_level;
  private int cou_id;
  
  @Id
  public int getPoi_id()
  {
    return this.poi_id;
  }
  
  public void setPoi_id(int poi_id)
  {
    this.poi_id = poi_id;
  }
  
  public String getPoi_title()
  {
    return this.poi_title;
  }
  
  public void setPoi_title(String poi_title)
  {
    this.poi_title = poi_title;
  }
  
  public String getPoi_level()
  {
    return this.poi_level;
  }
  
  public void setPoi_level(String poi_level)
  {
    this.poi_level = poi_level;
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

