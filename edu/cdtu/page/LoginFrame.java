package edu.cdtu.page;

import edu.cdtu.commons.ComponentStyle;
import edu.cdtu.dao.UserDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener
{
    //登录面板
    private JPanel loginPanel;
    //标题文字，用户名文字，密码文字
    private JLabel tittleLabel,accountNumberLable,passwordLabel;
    //用户名输入框
    private JTextField accountNumberField;
    //密码输入框
    private JPasswordField passwordField;
    //开户按钮登录按钮
    private JButton registerButton,loginButton;
    //1. 构造方法--创建登录窗体
    public LoginFrame()
    {
        //调用窗体样式方法设置登录窗体
        ComponentStyle.setFrame("ATM自动存取款系统", 1200, 800, "Second/ATM/src/images/logo.png", this);

        //3.添加面板
        loginPanel = new JPanel();
        loginPanel.setLayout(null);
        this.add(loginPanel);

        //5. 添加标题
        tittleLabel = new JLabel("ATM白动存取款系统",JLabel.CENTER);ComponentStyle.setFormStyle(tittleLabel,32, Color.white,550,230,550,50,loginPanel);

        //6.卡号:文字+输入框
        accountNumberLable = new JLabel("卡  号",JLabel.CENTER);
        ComponentStyle.setFormStyle(accountNumberLable,25,Color.white,530,320,200,50,loginPanel);

        accountNumberField = new JTextField();
        ComponentStyle.setFormStyle(accountNumberField,25,Color.black,700,320,280,50,loginPanel);

        //7.密码：文字+输入框
        passwordLabel = new JLabel("密  码:",JLabel.CENTER);
        ComponentStyle.setFormStyle(passwordLabel,25,Color.white,530,410,200,50,loginPanel);

        passwordField = new JPasswordField();
        passwordField.setEchoChar('*');
        ComponentStyle.setFormStyle(passwordField,25,Color.black,700,410,280,50,loginPanel);

        //8. 按钮：开户+登录
        registerButton = new JButton("开户");
        ComponentStyle.setButtonStyle(registerButton,Color.orange,700,490,120,50,loginPanel);

        loginButton = new JButton("登录");
        ComponentStyle.setButtonStyle(loginButton,Color.green,860,490,120,50,loginPanel);

        //为按钮添加点击事件
        registerButton.addActionListener(this);
        loginButton.addActionListener(this);

        //4.背景图片
        ComponentStyle.setPanelBackgroundImg("Second/ATM/src/images/login.png",0,0,1200,800,loginPanel);

        //设置窗体可见
        setVisible(true);
    }
    //2.主方法实例化登录窗体
    public static void main(String[] args)
    {
        new LoginFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == registerButton)
        {
            //开户
            new RegisterFrame();
        }
        else if (e.getSource() == loginButton)
        {
            //登陆
            //1.获取用户输入
            String accountNumber = accountNumberField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            //2.验证输入法是否合法
            if (validateLogin(accountNumber,password))
            {
                //3.执行登陆
                if (UserDao.uesrLogin(accountNumber,password))
                {
                    //当面窗口不可见，创建主页窗口
                    this.setVisible(false);
                    new MainFrame();
                }
            }
        }
    }

    /**
     * 验证登录页面输入法是否合法，当返回值为true，证明合法，执行登录；为false提示错误消息
     * @param accountNumber 卡号
     * @param password 密码
     * @return
     */
    private boolean validateLogin(String accountNumber,String password)
    {
        //卡号是15位数字
        String accountRegex ="^[0-9]{15}$";

        if (accountNumber == null || accountNumber.equals(""))
        {
            JOptionPane.showMessageDialog(null,"卡号不能为空！");
            return false;
        }
        if (!accountNumber.matches(accountRegex))
        {
            JOptionPane.showMessageDialog(null,"卡号必须是15位数字！");
            return false;
        }
        if (password == null || password.equals(""))
        {
            JOptionPane.showMessageDialog(null,"密码不能为空！");
            return false;
        }
        return true;
    }
}
