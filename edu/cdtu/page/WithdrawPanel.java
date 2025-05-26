package edu.cdtu.page;

import edu.cdtu.commons.ComponentStyle;
import edu.cdtu.dao.AccountDao;
import edu.cdtu.entity.Account;
import edu.cdtu.utils.ColorUtils;
import edu.cdtu.utils.DbUtils;
import edu.cdtu.utils.UserSaveTool;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WithdrawPanel extends JPanel implements ActionListener
{
    //文字：用户名、卡号、卡内余额、取出金额
    private JLabel currentUser,currentcid,currentMoney,withdrawLable;
    //输入框：用户名、卡号、卡内余额、取出金额
    private JTextField currentUserField,currentCidField,currentMoneyField,withdrawField;
    //按钮：取消、确认
    private JButton cancelButton,confirmButton;

    public WithdrawPanel()
    {
        //1.设置当前取款面板
        setSize(600,700);
        this.setBackground(ColorUtils.PANEL_BACKGROUND_COLOR);
        setLayout(null);

        //2.用户名
        currentUser = new JLabel("用  户  名:",JLabel.CENTER);
        ComponentStyle.setFormStyle(currentUser,25,Color.black,50,70,200,50,this);

        currentUserField = new JTextField(UserSaveTool.getCurrentLoginUsername());
        currentUserField.setEditable(false);//设置不可编辑
        ComponentStyle.setFormStyle(currentUserField,25,Color.black,250,70,280,50,this);

        //3.卡号
        currentcid = new JLabel("卡      号:",JLabel.CENTER);
        ComponentStyle.setFormStyle(currentcid,25,Color.black,50,170,200,50,this);

        currentCidField= new JTextField(UserSaveTool.getCurrentLoginAccountNumber());
        currentCidField.setEditable(false);//议置不可编辑
        ComponentStyle.setFormStyle(currentCidField,25,Color.black,250,170,280,50,this);

        //4.卡内余额
        currentMoney = new JLabel("卡内余额￥:",JLabel.CENTER);
        ComponentStyle.setFormStyle(currentMoney,25,Color.black,50,270,200,50,this);

        currentMoneyField = new JTextField(UserSaveTool.getCurrentLoginMoney());
        currentMoneyField.setEditable(false);//设置不可编辑
        ComponentStyle.setFormStyle(currentMoneyField,25,Color.black,250,270,280,50,this);

        //5.取出金额
        withdrawLable = new JLabel(" 取出金额￥：",JLabel.CENTER);
        ComponentStyle.setFormStyle(withdrawLable,25,Color.black,50,370,200,50,this);

        withdrawField = new JTextField();
        ComponentStyle.setFormStyle(withdrawField,25,Color.black,250,370,280,50,this);

        //6.按钮：取消+确认
        cancelButton = new JButton("取消");
        ComponentStyle.setButtonStyle(cancelButton,Color.orange,70,490,220,50,this);

        confirmButton = new JButton("确认");
        ComponentStyle.setButtonStyle(confirmButton,Color.green,310,490,220,50,this);

        //为按钮添加事件监听
        cancelButton.addActionListener(this);
        confirmButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == cancelButton)
        {
            //清空
            withdrawField.setText("");
        }
        else if (e.getSource() == confirmButton)
        {
            //确认
            //1.获取输入
            String money = withdrawField.getText().trim();

            //2.验证输入
            if (validateWithdraw(money))
            {
                //3.执行取款
                if (AccountDao.withdraw(Double.parseDouble(money)))
                {
                    //更新取款页面中的卡内余额，并清空取款金额
                    currentMoneyField.setText(UserSaveTool.getCurrentLoginMoney());
                    withdrawField.setText("");
                    this.updateUI();
                }
            }
        }

    }

    /**
     * 验证输入的取款金额是否合法
     * @param money 取款金额
     * @return
     */
    private boolean validateWithdraw(String money)
    {
        String depositRegx = "^[1-9]\\d*$";

        double withDrawMoney = Double.parseDouble(money);
        double currentMoney = Double.parseDouble(UserSaveTool.getCurrentLoginMoney());

        if (!money.matches(depositRegx))
        {
            JOptionPane.showMessageDialog(null,"存款金额为大于0的数字！");
            return false;
        }

        if (withDrawMoney > currentMoney)
        {
            JOptionPane.showMessageDialog(null,"余额不足！");
            return false;
        }
        return true;
    }
}
