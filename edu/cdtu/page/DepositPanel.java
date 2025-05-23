package edu.cdtu.page;

import edu.cdtu.commons.ComponentStyle;
import edu.cdtu.dao.AccountDao;
import edu.cdtu.utils.ColorUtils;
import edu.cdtu.utils.UserSaveTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepositPanel extends JPanel implements ActionListener
{
    //文字：用户名、卡号、卡内余额、存入金额
    private JLabel currentUser, currentCid, currentMoney, depositLable;

    //输入框：用户名、卡号、卡内余额、存入金额
    private JTextField currentUserField, currentCidField, currentMoneyField, depositField;

    //按钮：取消、确认
    private JButton cancelButton, confirmButton;

    public DepositPanel() {
        //1.设置当前存款面板
        this.setSize(600, 700);
        this.setBackground(ColorUtils.PANEL_BACKGROUND_COLOR);
        setLayout(null);

        //2.用户名
        currentUser = new JLabel("用  户  名:", JLabel.CENTER);
        ComponentStyle.setFormStyle(currentUser, 25, Color.black, 50, 70, 200, 50, this);

        currentUserField = new JTextField(UserSaveTool.getCurrentLoginUsername());
        currentUserField.setEditable(false);//议置不可编辑
        ComponentStyle.setFormStyle(currentUserField, 25, Color.black, 250, 70, 280, 50, this);

        //3.卡号
        currentCid = new JLabel("卡      号:", JLabel.CENTER);
        ComponentStyle.setFormStyle(currentCid, 25, Color.black, 50, 170, 200, 50, this);

        currentCidField = new JTextField(UserSaveTool.getCurrentLoginAccountNumber());
        currentCidField.setEditable(false);//议置不可编辑
        ComponentStyle.setFormStyle(currentCidField, 25, Color.black, 250, 170, 280, 50, this);

        //4.卡内余额
        currentMoney = new JLabel("卡内余额￥:", JLabel.CENTER);
        ComponentStyle.setFormStyle(currentMoney, 25, Color.black, 50, 270, 200, 50, this);

        currentMoneyField = new JTextField(UserSaveTool.getCurrentLoginMoney());
        currentMoneyField.setEditable(false);//设置不可编辑
        ComponentStyle.setFormStyle(currentMoneyField, 25, Color.black, 250, 270, 280, 50, this);

        //5.存入金额
        depositLable = new JLabel("存入余额￥:", JLabel.CENTER);
        ComponentStyle.setFormStyle(depositLable, 25, Color.black, 50, 370, 200, 50, this);

        depositField = new JTextField();
        ComponentStyle.setFormStyle(depositField, 25, Color.black, 250, 370, 280, 50, this);

        //6. 按钮：取消+确认
        cancelButton = new JButton("取消");
        ComponentStyle.setButtonStyle(cancelButton, Color.orange, 70, 490, 220, 50, this);
        confirmButton = new JButton("确认");
        ComponentStyle.setButtonStyle(confirmButton, Color.green, 310, 490, 220, 50, this);

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
            depositField.setText("");
        }
        else if (e.getSource() == confirmButton)
        {
            //确认
            //1.获取输入
            String money = depositField.getText().trim();

            //2.验证输入
            if (validateDeposit(money))
            {
                //3.执行存款
                if (AccountDao.deposit(Double.parseDouble(money)))
                {
                    //更新存款页面中的卡内余额，并清空存入金额
                    currentMoneyField.setText(UserSaveTool.getCurrentLoginMoney());
                    depositField.setText("");
                    this.updateUI();
                }
            }
        }
    }

    /**
     * 验证输入的存款金额是否合法
     * @param money 存款金额
     * @return
     */
    private boolean validateDeposit(String money)
    {
        String depositRegx = "^[1-9]\\d*$";

        if (!money.matches(depositRegx))
        {
            JOptionPane.showMessageDialog(null,"存款金额为大于0的数字！");
            return false;
        }
        return true;
    }
}
