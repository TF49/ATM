package edu.cdtu.dao;

import edu.cdtu.utils.DbUtils;
import edu.cdtu.utils.UserSaveTool;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao
{
    /**
     * 用户登录
     * @param accountNumber 卡号
     * @param password 密码
     * @return
     */
    public static boolean uesrLogin(String accountNumber,String password)
    {
        //1.获取数据库连接
        Connection conn = DbUtils.getConn();
        PreparedStatement ps = null;

        try
        {
            //2.数据库语句
            ps = conn.prepareStatement("SELECT a.accountNumber,a.`password`,a.money,u.username " + " FROM tb_user u,tb_account a " + " WHERE a.accountNumber = ? AND a.password = ? AND a.cid = u.fk_cid");

            //3.处理数据库语句的参数
            ps.setString(1,accountNumber);
            ps.setString(2,password);

            //3.执行SQL
            ResultSet rs =ps.executeQuery();

            //4.处理结果
            if (rs.next() && rs.getRow() > 0)
            {
                String resAccountNumber = rs.getString(1);//查询后的账号
                String resPassword = rs.getString(2);
                String money = rs.getString(3);//余额
                String username = rs.getString(4);//用户名

                //缓存登录成功后的用户信息
                UserSaveTool.setCurrentLoginAccountNumber(resAccountNumber);
                UserSaveTool.setCurrentLoginAccountPass(resPassword);
                UserSaveTool.setCurrentLoginMoney(money);
                UserSaveTool.setCurrentLoginUsername(username);

                return true;
            }
            else
            {
                JOptionPane.showMessageDialog(null,"账号密码错误");
                return false;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"数据库异常");
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
     * 开卡/注册
     * @param username 姓名
     * @param uid 身份证号码
     * @param gender 性别
     * @return
     */
    public static String addUser(String username,String uid,String gender)
    {
        //1.生成新卡，并获取到新卡卡号--操作卡号信息表
        String accountNumber = AccountDao.addAccount();

        //2.获取到新卡在数据库表中的主键id
        String cid = AccountDao.selectAccountByAccountNumber(accountNumber);

        Connection conn = DbUtils.getConn();//获取数据库连接
        PreparedStatement ps = null;

        try
        {
            //数据库语句--3.在用户表中建立新卡与该用户的关系--操作用户表
            ps = conn.prepareStatement("insert into tb_user(username,identityCard,gender,fk_cid) VALUES(?,?,?,?)");

            //数据库语句的参数
            ps.setString(1,username);
            ps.setString(2,uid);
            ps.setString(3,gender);
            ps.setString(4,cid);

            //执行SQL
            int i = ps.executeUpdate();

            //处理结果
            if (i > 0)
            {
                JOptionPane.showMessageDialog(null,"开卡成功");
                return accountNumber;
            }
            else
            {
                JOptionPane.showMessageDialog(null,"开卡失败");
                return "";
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"数据库异常");
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

}
