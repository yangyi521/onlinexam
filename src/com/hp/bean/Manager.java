package com.hp.bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_manager")
public class Manager
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private int sys_id;
  private String sys_account;
  private String sys_password;
  
  @Id
  public int getSys_id()
  {
    return this.sys_id;
  }
  
  public void setSys_id(int sys_id)
  {
    this.sys_id = sys_id;
  }
  
  public String getSys_account()
  {
    return this.sys_account;
  }
  
  public void setSys_account(String sys_account)
  {
    this.sys_account = sys_account;
  }
  
  public String getSys_password()
  {
    return this.sys_password;
  }
  
  public void setSys_password(String sys_password)
  {
    this.sys_password = sys_password;
  }
}

