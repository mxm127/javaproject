package com.mxm.ui;

import javax.swing.*;

public class RegisterJFrame extends JFrame {
    public RegisterJFrame() {
//        设置界面宽高
        this.setSize(488,500);
//        设置界面标题
        this.setTitle("拼图注册界面");
//        设置界面置顶
        this.setAlwaysOnTop(true);
//        设置界面居中
        this.setLocationRelativeTo(null);
//        设置界面关闭模式
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//        让界面显示出来
        this.setVisible(true);

    }
}
