package com.mxm.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import com.mxm.method.finduser;
import com.mxm.method.codeUtil;

public class LoginJFrame extends JFrame implements MouseListener {
    static ArrayList<User> arr = new ArrayList<>();
    JButton login = new JButton();
    JButton register = new JButton();
    JTextField username = new JTextField();
    JPasswordField password = new JPasswordField();
    JTextField code = new JTextField();
    JLabel rightCode = new JLabel(codeUtil.getcode());

    static {
        arr.add(new User("mxm", "142536789"));
        arr.add(new User("lzq", "142536789"));

    }

    public LoginJFrame() {
        initlogin();

        initImage();

        //        让界面显示出来
        this.setVisible(true);

    }

    private void initImage() {
//        添加用户名文字
        JLabel usernameText = new JLabel(new ImageIcon("puzzlegame\\image\\login\\用户名.png"));
        usernameText.setBounds(116, 135, 47, 17);
        this.getContentPane().add(usernameText);

//        添加用户名输入框

        username.setBounds(195, 134, 200, 30);
        this.getContentPane().add(username);

//        添加密码文字
        JLabel passwordText = new JLabel(new ImageIcon("puzzlegame\\image\\login\\密码.png"));
        passwordText.setBounds(130, 195, 32, 16);
        this.getContentPane().add(passwordText);

//        添加密文文本输入框

        password.setBounds(195, 195, 200, 30);
        this.getContentPane().add(password);


//        添加验证码文字
        JLabel codeText = new JLabel(new ImageIcon("puzzlegame\\image\\login\\验证码.png"));
        codeText.setBounds(133, 256, 50, 30);
        this.getContentPane().add(codeText);

//        添加验证码文本输入框

        code.setBounds(195, 256, 100, 30);
        this.getContentPane().add(code);


        rightCode.setBounds(300, 256, 50, 30);
        rightCode.addMouseListener(this);
        this.getContentPane().add(rightCode);

//        添加登录按钮

        login.setBounds(123, 310, 128, 47);
        login.setIcon(new ImageIcon("puzzlegame\\image\\login\\登录按钮.png"));

//        去除按钮的边框
        login.setBorderPainted(false);
//        去除按钮背景
        login.setContentAreaFilled(false);
        login.addMouseListener(this);

        this.getContentPane().add(login);

//        添加注册按钮
        register.setBounds(256, 310, 128, 47);
        register.setIcon(new ImageIcon("puzzlegame\\image\\login\\注册按钮.png"));
        register.setBorderPainted(false);
        register.setContentAreaFilled(false);
        register.addMouseListener(this);
        this.getContentPane().add(register);

//        添加背景
        JLabel backGround = new JLabel(new ImageIcon("puzzlegame\\image\\login\\background.png"));
        backGround.setBounds(0, 0, 470, 390);
        this.getContentPane().add(backGround);
//

    }

    public void showJDialog(String content) {
        JDialog jDialog = new JDialog();
        jDialog.setSize(200, 150);
        jDialog.setAlwaysOnTop(true);
        jDialog.setLocationRelativeTo(null);
        jDialog.setModal(true);

        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);
        jDialog.setVisible(true);


    }

    private void initlogin() {
        //设置界面宽高
        this.setSize(488, 430);
        //设置界面标题
        this.setTitle("拼图登录界面");
//        设置界面置顶
        this.setAlwaysOnTop(true);
//        设置界面居中
        this.setLocationRelativeTo(null);
//        设置界面关闭模式
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    }





    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == login) {
            String n = username.getText();
            String p = password.getText();
            String c = code.getText();
            int index = finduser.findu(arr, n);
            if (index >= 0) {
                User u = arr.get(index);
                if (u.getPassword().equals(p)) {
                    if (rightCode.getText().equalsIgnoreCase(c)){
                        new GameJFrame();
                    }else {
                        showJDialog("验证码错误");
                    }
                } else {
                    showJDialog("密码错误");
                }
            } else {
                showJDialog("用户名错误");
            }
            this.setVisible(false);
        } else if (e.getSource() == register) {
            new RegisterJFrame();
        } else if (e.getSource() == rightCode) {
            rightCode.setText(codeUtil.getcode());

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == login) {
            login.setIcon(new ImageIcon("puzzlegame\\image\\login\\登录按下.png"));
        } else if (e.getSource() == register) {
            register.setIcon(new ImageIcon("puzzlegame\\image\\login\\注册按下.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getSource() == login){
            login.setIcon(new ImageIcon("puzzlegame\\image\\login\\登录按钮.png"));
        } else if (e.getSource() == register) {
            register.setIcon(new ImageIcon("puzzlegame\\image\\login\\注册按钮.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
