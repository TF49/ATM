package edu.cdtu.page;

import edu.cdtu.commons.ComponentStyle;
import edu.cdtu.utils.ColorUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepositWithdrawFrame extends JFrame implements ActionListener
{
    //顶部面板、底部面板
    private JPanel topPanel,bottomPanel;

    //存取按钮、取款按钮
    private JButton depositButton,withdrawButton;

    public DepositWithdrawFrame()
        {
            //1. 窗体设置
            ComponentStyle.setFrame("ATM自动存取款系统",600,800, "Second/ATM/src/images/logo.png",this);
            this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);//点击关闭时隐藏窗口，不退出程序
            this.setLayout(null);//更改布局方式

            //2. 顶部面板
            topPanel=new JPanel();
            topPanel.setBackground(ColorUtils.PANEL_BACKGROUND_COLOR);
            topPanel.setLayout(null);
            topPanel.setBounds(0,0,600,100);
            this.add(topPanel);

            //3.顶部面板存取款按钮
            //默认显示存款页面，因此存款按钮默认激活，颜色未激活色
            depositButton = new JButton("存款");
            ComponentStyle.setButtonStyle(depositButton,ColorUtils.BUTTON_ACTIVE_COLOR, 70, 30, 220, 50, topPanel);

            withdrawButton = new JButton("取款");
            ComponentStyle.setButtonStyle(withdrawButton,ColorUtils.BUTTON_INVALID_COLOR, 310, 30, 220, 50, topPanel);

            //4.为按钮添加点击事件
            depositButton.addActionListener(this);
            withdrawButton.addActionListener(this);

            //5.底部面板
            bottomPanel = new JPanel();
            bottomPanel.setLayout(null);
            bottomPanel.setBounds(0,100,600,700);
            this.add(bottomPanel);

            //6.默认显示存款面板
            DepositPanel depositPanel = new DepositPanel();
            bottomPanel.add(depositPanel);

            this.setVisible(true);
        }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //清除颜色
        clearButtonColor();

        if (e.getSource() == depositButton)
        {
            //存款
            depositButton.setBackground(ColorUtils.BUTTON_ACTIVE_COLOR);//按钮背景颜色
            bottomPanel.removeAll();//底部面板全部清除

            DepositPanel depositPanel = new DepositPanel();//新面板
            bottomPanel.add(depositPanel);//在底部面板中添加新面板
            bottomPanel.updateUI();//更新底部面板显示的内容
        }
        else if (e.getSource() == withdrawButton)
        {
            //取款
            withdrawButton.setBackground(ColorUtils.BUTTON_ACTIVE_COLOR);//按钮背景颜色
            bottomPanel.removeAll();//底部面板全部清除

            WithdrawPanel withdrawPanel = new WithdrawPanel();//新面板
            bottomPanel.add(withdrawPanel);//在底部面板中添加新面板
            bottomPanel.updateUI();//更新底部面板显示的内容
        }
    }
    //清除按钮激活色
    private void clearButtonColor()
    {
        withdrawButton.setBackground(ColorUtils.BUTTON_ACTIVE_COLOR);
        depositButton.setBackground(ColorUtils.BUTTON_INVALID_COLOR);
    }
}