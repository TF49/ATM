package edu.cdtu.commons;

import javax.swing.*;
import java.awt.*;

public class ComponentStyle
{
    /**
     *页面窗体样式
     * @param sysTittle 系统名称
     * @param width    窗体宽度
     * @param height   窗体高度
     * @param imgUrl   Logo图片地址
     * @param jFrame   窗体对象
     */
    public static void setFrame(String sysTittle,int width,int height,String imgUrl, JFrame jFrame)
    {
        jFrame.setTitle(sysTittle);//设置窗体标题
        jFrame.setSize(width, height);//设置窗体大小
        jFrame.setLocationRelativeTo(null);//设置窗体在电脑屏幕中间显示
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置关闭属性
        jFrame.setResizable(false);//设置窗体不可通过拖动改变大小

        //窗体左上角logo图片
        ImageIcon icon = new ImageIcon(imgUrl);
        jFrame.setIconImage(icon.getImage());
    }

    /**
     * 面板背景图片
     * @param imgUrl  背景图片地址
     * @param x       背景图片x轴位置
     * @param y       背景图片y轴位置
     * @param width   背景图片宽度
     * @param height  背景图片高度
     * @param panel   将背景图片添加到哪个面板中
     */
    public static void setPanelBackgroundImg(String imgUrl,int x,int y,int width, int height,JPanel panel)
    {
        JLabel imgLable = new JLabel(new ImageIcon(imgUrl));//背景图片
        imgLable.setBounds(x, y, width, height);//位置宽高
        panel.add(imgLable);//添加到面板中
    }

    /**
     * 设置表单组件样式
     * @param component 组件名称
     * @param size 字体大小
     * @param x 组件x轴坐标位置
     * @param y 组件y轴坐标位置
     * @param width 组件宽度
     * @param height 组件高度
     * @param panel 将组件加入哪个面板中
     * @param fontColor 文字颜色
     */
    public static void setFormStyle(JComponent component, int size, Color fontColor, int x, int y, int width, int height, JPanel panel)
    {
        component.setFont(new Font("楷体", Font.BOLD, size));//文字
        component.setForeground(fontColor);//字体颜色
        component.setBounds(x, y, width, height);//位置与宽高
        panel.add(component);// 加入面板中
    }

    /**
     * 按钮样式
     * @param jButton 按钮名称
     * @param btnColor 按钮背景颜色
     * @param x 按钮х轴位置
     * @param y 按钮y轴位置
     * @param width 按钮宽度
     * @param height 按钮高度
     * @param panel 将按钮添加到哪个面板
     */
    public static void setButtonStyle(JButton jButton,Color btnColor,int x,int y,int width,int height,JPanel panel)
    {
            jButton.setBackground(btnColor);//背景色
            jButton.setFont(new Font("楷体",Font.BOLD,25));
            jButton.setBounds(x,y,width,height);
            panel.add(jButton);
    }

    /**
     * 菜单按钮样式
     * @param jButton 按钮对象
     * @param btnColor 菜单按钮背景颜色
     * @param x 菜单按钮х轴位置
     * @param y 菜单按钮y轴位置
     * @param width 菜单按钮宽度
     * @param height 菜单按钮高度
     * @param jPanel 将菜单按钮添加到哪个面板
     */
    public static void setMenuButtonStyle(JButton jButton,Color btnColor, int x,int y,int width,int height,JPanel jPanel)
    {
        jButton.setBackground(btnColor);
        jButton.setBounds(x, y, width, height);
        jButton.setFont(new Font("楷体", Font.BOLD, 20));

        //字体颜色
        jButton.setForeground(Color.white);

        //文字水平居中，在图片下方
        jButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        jButton.setHorizontalTextPosition(SwingConstants.CENTER);

        //不要边框
        jButton.setBorderPainted(false);
        jPanel.add(jButton);
    }
}
