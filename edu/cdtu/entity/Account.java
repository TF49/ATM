package edu.cdtu.entity;

public class Account
{
    private int cid;//卡号id
    private String accountNumber;//卡号
    private String password;//卡密码
    private Double money;//卡内余额
    private int status;//卡的激活状态

    //无参构造
    public Account()
    {

    }

    //有参构造
    public Account(int cid, int status, Double money, String password, String accountNumber)
    {
        this.cid = cid;
        this.status = status;
        this.money = money;
        this.password = password;
        this.accountNumber = accountNumber;
    }

    public int getCid()
    {
        return cid;
    }

    public void setCid(int cid)
    {
        this.cid = cid;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public Double getMoney()
    {
        return money;
    }

    public void setMoney(Double money)
    {
        this.money = money;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString()
    {
        return "Account{" +
                "cid=" + cid +
                ", accountNumber='" + accountNumber + '\'' +
                ", password='" + password + '\'' +
                ", money=" + money +
                ", status=" + status +
                '}';
    }
}
