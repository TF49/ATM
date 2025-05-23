package edu.cdtu.entity;

import java.util.List;

public class User
{
    private int id;//用户id
    private String uesrname;//用户名
    private String indetityCard;//身份证号码
    private String gender;//性别
    private List<Account> accountList;//外键，银行卡id

    public User()
    {

    }

    public User(List<Account> accountList, String gender, String indetityCard, String uesrname, int id)
    {
        this.accountList = accountList;
        this.gender = gender;
        this.indetityCard = indetityCard;
        this.uesrname = uesrname;
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getUesrname()
    {
        return uesrname;
    }

    public void setUesrname(String uesrname)
    {
        this.uesrname = uesrname;
    }

    public String getIndetityCard()
    {
        return indetityCard;
    }

    public void setIndetityCard(String indetityCard)
    {
        this.indetityCard = indetityCard;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public List<Account> getAccountList()
    {
        return accountList;
    }

    public void setAccountList(List<Account> accountList)
    {
        this.accountList = accountList;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "id=" + id +
                ", uesrname='" + uesrname + '\'' +
                ", indetityCard='" + indetityCard + '\'' +
                ", gender='" + gender + '\'' +
                ", accountList=" + accountList +
                '}';
    }
}
