package edu.cdtu.dao;

import edu.cdtu.utils.DbUtils;
import edu.cdtu.utils.UserSaveTool;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDao
{
    /**
     * 存款功能
     * @param depositMoney 存入金额
     * @return
     */
    public static boolean deposit(Double depositMoney)
    {
        //1.获取连接
        Connection conn = DbUtils.getConn();
        PreparedStatement ps = null;

        try
        {
            //2.编写SQL语句
            ps = conn.prepareStatement("update tb_account SET money = money + ? WHERE accountNumber = ?");

            //3.处理SQL语句的参数
            ps.setString(1,depositMoney.toString());//存入的金额
            ps.setString(2, UserSaveTool.getCurrentLoginAccountNumber());//登录用户的银行卡号码

            //4.执行SQL
            int i = ps.executeUpdate();

            //5.判断结果
            if (i > 0)
            {
                //将登陆缓存的银行卡余额进行更新：原有的+存入的
                double newMoney = Double.parseDouble(UserSaveTool.getCurrentLoginMoney())+depositMoney;
                UserSaveTool.setCurrentLoginMoney(String.valueOf(newMoney));

                JOptionPane.showMessageDialog(null,"存入成功！");
                return true;
            }
            else
            {
                JOptionPane.showMessageDialog(null,"存入失败！");
                return false;
            }
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null,"数据库出错。");
            e.printStackTrace();
            return false;
        }
        finally
        {
            try
            {
                //6.关流
                if (ps != null)
                {
                    ps.close();
                }
                if (conn != null)
                {
                    conn.close();
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * 取款功能
     * @param withdrawMoney 取款金额
     * @return
     */
    public static boolean withdraw(Double withdrawMoney)
    {
        //1.获取连接
        Connection conn = DbUtils.getConn();
        PreparedStatement ps = null;

        try
        {
            //编写SQL语句
            ps = conn.prepareStatement("update tb_account SET money = money - ? WHERE accountNumber = ?");

            //3.处理SQL语句的参数
            ps.setString(1,withdrawMoney.toString());//取款金额
            ps.setString(2,UserSaveTool.getCurrentLoginAccountNumber());//登录用户的银行卡号码

            //4.执行SQL
            int i = ps.executeUpdate();

            //判断结果
            if (i > 0)
            {
                //将登录缓存的银行卡余额进行更新：原有的-取出的
                double preMoney = Double.parseDouble(UserSaveTool.getCurrentLoginMoney())-withdrawMoney;
                UserSaveTool.setCurrentLoginMoney(String.valueOf(preMoney));

                JOptionPane.showMessageDialog(null,"取款成功！");
                return true;
            }
            else
            {
                JOptionPane.showMessageDialog(null,"取款失败！");
                return false;
            }
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null,"数据库出错");
            e.printStackTrace();
            return false;
        }
        finally
        {
            try
            {
                //6.关流
                if (ps !=null)
                {
                    ps.close();
                }
                if (conn !=null)
                {
                    conn.close();
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    //生成新卡
    public static String addAccount()
    {
        //随机生成卡号
        int resRandom =(int)(Math.random()*9000)+1000;//后四位取值范围[1000,9999]
        String accountNumber = "32170038000"+String.valueOf(resRandom);

        //1.获取连接
        Connection conn = DbUtils.getConn();
        PreparedStatement ps = null;
        try
        {
            //2.编写SQL语句
            ps = conn.prepareStatement("insert into tb_account(accountNumber) VALUES(?)");

            //处理SQL语句的参数
            ps.setString(1,accountNumber);//为sql中的参数赋值

            //4.执行SQL
            int i = ps.executeUpdate();

            //5.判断结果
            if (i > 0)
            {
                return accountNumber;
            }
            else
            {
                JOptionPane.showMessageDialog(null,"开卡失败！");
                return "";
            }
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null,"数据库出错。");
            e.printStackTrace();
            return "";
        }
        finally
        {
            try
            {
                //6.关流
                if (ps != null)
                {
                    ps.close();
                }
                if (conn != null)
                {
                    conn.close();
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * 查询新卡id
     * @param accountNumber 新卡卡号
     * @return
     */
    public static String selectAccountByAccountNumber(String accountNumber)
    {
        //1.获取连接
        Connection conn = DbUtils.getConn();
        PreparedStatement ps = null;
        try
        {
            //2.编写SQL语句
            ps = conn.prepareStatement("SELECT cid FROM tb_account WHERE accountNumber = ?");

            //3.处理SQL语句的参数
            ps.setString(1,accountNumber);

            //4.执行SQl
            ResultSet rs = ps.executeQuery();

            //5.判断结果
            if (rs.next() && rs.getRow() > 0)
            {
                String cid = rs.getString(1);//查询后的账号
                return cid;
            }
            else
            {
                JOptionPane.showMessageDialog(null,"开卡失败");
                return "";
            }
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null,"数据库出错。");
            e.printStackTrace();
            return "";
        }
        finally
        {
            try
            {
                //6.关流
                if (ps !=null)
                {
                    ps.close();
                }
                if (conn !=null)
                {
                    conn.close();
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}
