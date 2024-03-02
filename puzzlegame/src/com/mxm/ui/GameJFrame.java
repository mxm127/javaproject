package com.mxm.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.*;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {

    private int x;
    private int y;
    private int count = 0;
    String path = "puzzlegame\\image\\animal\\animal2\\";
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenu replaceImage = new JMenu("更换图片");
    JMenuItem girl = new JMenuItem("美女");
    JMenuItem animals = new JMenuItem("动物");
    JMenuItem sports = new JMenuItem("运动");

    JMenuItem accountItem = new JMenuItem("公众号");
    int[][] darr = new int[4][4];
    int[][] win = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}

    };

    public GameJFrame() {
        //设置界面宽高
        initJFrame();

//        初始化菜单
        initJMenuBar();

//        初始化随机数组
        initData();

//        初始化图片
        initImage();

//        让界面显示出来
        this.setVisible(true);

    }

    private void initData() {
        int[] arr = new int[16];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        Random rad = new Random();
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            int index = rad.nextInt(16);
            arr[i] = arr[index];
            arr[index] = temp;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                x = i % 4;
                y = i / 4;
            }
            darr[i / 4][i % 4] = arr[i];
        }

    }

    //初始化图片
    private void initImage() {
//        清空出现在JFrame上的所有图片
        this.getContentPane().removeAll();


//判断是否胜利
        if (victory()) {
            JLabel winjLable = new JLabel(new ImageIcon("C:\\code\\game\\puzzlegame\\image\\win.png"));
            winjLable.setBounds(203, 283, 197, 73);
            this.getContentPane().add(winjLable);
        }

//添加计步器
        JLabel stepCount = new JLabel("步数为：" + count);
        stepCount.setBounds(20, 10, 100, 30);
        this.getContentPane().add(stepCount);


        for (int i = 0; i < darr.length; i++) {
            for (int j = 0; j < darr[i].length; j++) {
                int number = darr[i][j];
//        创建一个图片ImageIcon的对象
                ImageIcon icon = new ImageIcon(path + number + ".jpg");

//        创建一个JLable的对象（管理容器用来管理图片）
                JLabel njLabel = new JLabel(icon);

//        指定图片位置
                njLabel.setBounds(j * 105 + 83, i * 105 + 134, 105, 105);

//        给图片添加边框
                njLabel.setBorder(new BevelBorder(BevelBorder.RAISED));

//        把管理容器添加到界面中
                this.getContentPane().add(njLabel);
            }
        }
        initBackGround();
        this.getContentPane().repaint();

    }

    private boolean victory() {
        for (int i = 0; i < darr.length; i++) {
            for (int j = 0; j < darr[i].length; j++) {
                if (darr[i][j] != win[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void initBackGround() {
        JLabel jLabel = new JLabel(new ImageIcon("puzzlegame\\image\\background.png"));
        jLabel.setBounds(40, 40, 508, 560);
        this.getContentPane().add(jLabel);
    }


    private void initJMenuBar() {
        //        创建整个菜单的对象
        JMenuBar jMenuBar = new JMenuBar();

//        创建菜单上的功能选项的对象
        JMenu functionjMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");


        replaceImage.add(girl);
        replaceImage.add(animals);
        replaceImage.add(sports);

//        将条目添加到选项下面
        functionjMenu.add(replayItem);
        functionjMenu.add(reLoginItem);
        functionjMenu.add(closeItem);
        functionjMenu.add(replaceImage);
        aboutJMenu.add(accountItem);


//        给条目绑定事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        girl.addActionListener(this);
        animals.addActionListener(this);
        sports.addActionListener(this);

//        将选项添加到菜单中
        jMenuBar.add(functionjMenu);
        jMenuBar.add(aboutJMenu);

//
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        this.setSize(603,680);
//        设置界面标题
        this.setTitle("拼图");
//        设置界面置顶
        this.setAlwaysOnTop(true);
//        设置界面居中
        this.setLocationRelativeTo(null);
//        设置界面关闭模式
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//        取消默认居中放置,才能将添加的组件按xy轴坐标布局
        this.setLayout(null);
//        给界面添加键盘监听
        this.addKeyListener(this);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (victory()) {
            return;
        }
        int code = e.getKeyCode();
        if (code == 65) {
            this.getContentPane().removeAll();
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(all);
            this.initBackGround();
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (victory()) {
            return;
        }
        int code = e.getKeyCode();
        if (code == 37) {
            if (x == 3) {
                return;
            }

            count++;

            darr[y][x] = darr[y][x + 1];
            darr[y][x + 1] = 0;

            x++;

            initImage();
        } else if (code == 38) {
            if (y == 3) {
                return;
            }

            count++;

            darr[y][x] = darr[y + 1][x];
            darr[y + 1][x] = 0;

            y++;

            initImage();
        } else if (code == 39) {
            if (x == 0) {
                return;
            }

            count++;

            darr[y][x] = darr[y][x - 1];
            darr[y][x - 1] = 0;

            x--;

            initImage();
        } else if (code == 40) {
            if (y == 0) {
                return;
            }

            count++;

            darr[y][x] = darr[y - 1][x];
            darr[y - 1][x] = 0;

            y--;

            initImage();
        } else if (code == 65) {
            initImage();
        } else if (code == 87) {
            darr = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0}
            };
            initImage();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object code = e.getSource();
        if (code == replayItem) {
//            初始化计步器
            count = 0;
//            重新打乱图片
            initData();
//            重新加载被打乱的图片
            initImage();
        } else if (code == closeItem) {

            System.exit(0);

        } else if (code == accountItem) {
//            创建一个弹窗对象
            JDialog jDialog = new JDialog();
//            创建一个管理图片的JLbale容器对象
            JLabel jLabel = new JLabel(new ImageIcon("C:\\code\\game\\puzzlegame\\image\\damie.jpg"));
            jLabel.setBounds(0, 0, 258, 258);
//            将容器添加到弹窗内
            jDialog.getContentPane().add(jLabel);

//            设置弹窗大小
            jDialog.setSize(344,344);
//            让弹窗置顶
            jDialog.setAlwaysOnTop(true);
//            让弹窗居中
            jDialog.setLocationRelativeTo(null);
//            弹窗不关闭无法进行下面的操作
            jDialog.setModal(true);

            jDialog.setVisible(true);

        } else if (code == reLoginItem) {
            this.setVisible(false);
            new LoginJFrame();
        } else if (code == girl) {
            Random ra = new Random();
            int index = ra.nextInt(11)+1;
            path = "C:\\code\\game\\puzzlegame\\image\\girl\\girl"+index+"\\";
            initData();
            initImage();
        }else if(code == animals){
            Random ra = new Random();
            int index = ra.nextInt(8)+1;
            path = "C:\\code\\game\\puzzlegame\\image\\animal\\animal"+index+"\\";
            initData();
            initImage();
        }else if(code == sports){
            Random ra = new Random();
            int index = ra.nextInt(10)+1;
            path = "C:\\code\\game\\puzzlegame\\image\\sport\\sport"+index+"\\";
            initData();
            initImage();
        }
    }
}
