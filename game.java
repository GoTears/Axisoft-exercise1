import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

//ActionListener 接口是 Java AWT 和 Swing GUI 库中的一个事件监听器接口，用于处理用户界面中发生的动作事件
public class game extends JFrame implements ActionListener {
    private static JButton[][] checker = new JButton[3][3];
    private static int[][] judge = new int[3][3];
    Font font = new Font("宋体", Font.BOLD, 100); // 定义字体
    public game() {
        setBounds(300, 150, 800, 500);// 设置窗口位置及大小
        setVisible(true); // 设置窗口可见
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭界面同时停止运行程序
        setResizable(false); // 不可改变窗口大小
        setTitle("Axisoft-exercise1"); // 标题
        setLayout(null); // 取消布局管理器

        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                checker[a][b] = new JButton(); // 将按钮全部实例化
                checker[a][b].setBounds(40 + 130 * a, 40 + 130 * b, 130, 130); // 按钮的位置及大小
                checker[a][b].setFont(font);
                checker[a][b].addActionListener(this);
                add(checker[a][b]);
            }
        }
    }
    public void actionPerformed(ActionEvent event) // 单击按钮事件发生时的处理操作
    {
        JButton batton = (JButton) event.getSource();
        for(int a=0;a<3;a++) {
            for(int b=0;b<3;b++) {
                if (batton == checker[a][b]&&judge[a][b] == 0) {//当按下的位置无子
                    checker[a][b].setText("O");
                    judge[a][b]=1;
                    if(check(0,3)>0) {
                        judge=new int [3][3];
                        int n=JOptionPane.showConfirmDialog(this,"玩家赢！！！"+'\n'+"是否重玩游戏。","游戏结束",JOptionPane.YES_NO_OPTION);
                        if(n==JOptionPane.NO_OPTION) {
                            this.dispose();
                        }
                        if(n==JOptionPane.YES_OPTION) {
                            this.dispose();
                            new game();
                        }
                    }else if(check(0,3)==-1) {
                        judge=new int [3][3];
                        int n=JOptionPane.showConfirmDialog(this,"平局！！！"+'\n'+"是否重玩游戏。","游戏结束",JOptionPane.YES_NO_OPTION);
                        if(n==JOptionPane.NO_OPTION) {
                            this.dispose();
                        }
                        if(n==JOptionPane.YES_OPTION) {
                            this.dispose();
                            new game();
                        }
                    }
                    machineDown();
                    if(check(0,-3)>0) {
                        judge=new int [3][3];
                        int n=JOptionPane.showConfirmDialog(this,"机器赢！！！"+'\n'+"是否重玩游戏。","游戏结束",JOptionPane.YES_NO_OPTION);
                        if(n==JOptionPane.NO_OPTION) {
                            this.dispose();
                        }
                        if(n==JOptionPane.YES_OPTION) {
                            this.dispose();
                            new game();
                        }
                    }else if(check(0,-3)==-1) {
                        judge=new int [3][3];
                        int n=JOptionPane.showConfirmDialog(this,"平局！！！"+'\n'+"是否重玩游戏。","游戏结束",JOptionPane.YES_NO_OPTION);
                        if(n==JOptionPane.NO_OPTION) {
                            this.dispose();
                        }
                        if(n==JOptionPane.YES_OPTION) {
                            this.dispose();
                            new game();
                        }
                    }
                }
            }
        }
    }
    public static void machineDown() {
        if(judge[1][1]==0) {
            checker[1][1].setText("X");
            judge[1][1]=-1;
        }else if(judge[0][0]==0&&judge[0][2]==0&&judge[2][0]==0&&judge[2][2]==0){
            int x=(int) (4*Math.random());
            if(x==0) {
                checker[0][0].setText("X");
                judge[0][0]=-1;
            }
            else if(x==1) {
                checker[0][2].setText("X");
                judge[0][2]=-1;
            }
            else if(x==2) {
                checker[2][0].setText("X");
                judge[2][0]=-1;
            }
            else if(x==3) {
                checker[2][2].setText("X");
                judge[2][2]=-1;
            }
        }
        else if(check(0,-2)>0) {
            checker[(check(0,-2)-1)/3][(check(0,-2)-1)%3].setText("X");
            judge[(check(0,-2)-1)/3][(check(0,-2)-1)%3]=-1;
        }
        else if(check(0,2)>0){
            checker[(check(0,2)-1)/3][(check(0,2)-1)%3].setText("X");
            judge[(check(0,2)-1)/3][(check(0,2)-1)%3]=-1;
        }else if(check(0,-1)>0){
            checker[(check(0,-1)-1)/3][(check(0,-1)-1)%3].setText("X");
            judge[(check(0,-1)-1)/3][(check(0,-1)-1)%3]=-1;
        }else {
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    if(judge[i][j]==0&&(i+j==1||i+j==3)) {
                        checker[i][j].setText("X");
                        judge[i][j]=-1;
                        return ;
                    }
                }
            }
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    if(judge[i][j]==0) {
                        checker[i][j].setText("X");
                        judge[i][j]=-1;
                        return ;
                    }
                }
            }
        }
    }

//    cur 和 ans 是两个参数，用于指示当前检查的状态和期望的结果。cur：表示当前检查的状态。ans：表示期望的结果
    public static int check(int cur,int ans) {
        if(ans>1||ans<-1) {
            if(judge[1][0]+judge[1][1]+judge[1][2]==ans) {
                if(judge[1][0]==0)return 4;
                else if(judge[1][1]==0)return 5;
                else if(judge[1][2]==0)return 6;
                else return 10;
            }
            else if(judge[0][1]+judge[1][1]+judge[2][1]==ans) {
                if(judge[0][1]==0)return 2;
                else if(judge[1][1]==0)return 5;
                else if(judge[2][1]==0)return 8;
                else return 10;
            }
            else if(judge[0][0]+judge[0][1]+judge[0][2]==ans) {
                if(judge[0][0]==0)return 1;
                else if(judge[0][1]==0)return 2;
                else if(judge[0][2]==0)return 3;
                else return 10;
            }
            else if(judge[2][0]+judge[2][1]+judge[2][2]==ans) {
                if(judge[2][0]==0)return 7;
                else if(judge[2][1]==0)return 8;
                else if(judge[2][2]==0)return 9;
                else return 10;
            }
            else if(judge[0][0]+judge[1][0]+judge[2][0]==ans) {
                if(judge[0][0]==0)return 1;
                else if(judge[1][0]==0)return 4;
                else if(judge[2][0]==0)return 7;
                else return 10;
            }

            else if(judge[0][2]+judge[1][2]+judge[2][2]==ans) {
                if(judge[0][2]==0)return 3;
                else if(judge[1][2]==0)return 6;
                else if(judge[2][2]==0)return 9;
                else return 10;
            }
            else if(judge[0][0]+judge[1][1]+judge[2][2]==ans) {
                if(judge[0][0]==0)return 1;
                else if(judge[1][1]==0)return 5;
                else if(judge[2][2]==0)return 9;
                else return 10;
            }
            else if(judge[0][2]+judge[1][1]+judge[2][0]==ans) {
                if(judge[0][2]==0)return 3;
                else if(judge[1][1]==0)return 5;
                else if(judge[2][0]==0)return 7;
                else return 10;
            }
        }else {
            if(judge[1][0]+judge[1][1]+judge[1][2]==ans&&(judge[1][0]==0||judge[1][1]==0||judge[1][2]==0)) {
                if(judge[1][0]==0)return 4;
                else if(judge[1][2]==0)return 6;
                else if(judge[1][1]==0)return 5;
                else return 10;
            }
            else if(judge[0][1]+judge[1][1]+judge[2][1]==ans&&(judge[0][1]==0||judge[1][1]==0||judge[2][1]==0)) {
                if(judge[0][1]==0)return 2;
                else if(judge[1][1]==0)return 5;
                else if(judge[2][1]==0)return 8;
                else return 10;
            }
            else if(judge[0][0]+judge[0][1]+judge[0][2]==ans&&(judge[0][0]==0||judge[0][1]==0||judge[0][2]==0)) {
                if(judge[0][0]==0)return 1;
                else if(judge[0][2]==0)return 3;
                else if(judge[0][1]==0)return 2;
                else return 10;
            }
            else if(judge[2][0]+judge[2][1]+judge[2][2]==ans&&(judge[2][0]==0||judge[2][1]==0||judge[2][2]==0)) {
                if(judge[2][0]==0)return 7;
                else if(judge[2][2]==0)return 9;
                else if(judge[2][1]==0)return 8;
                else return 10;
            }
            else if(judge[0][0]+judge[1][0]+judge[2][0]==ans&&(judge[0][0]==0||judge[1][0]==0||judge[2][0]==0)) {
                if(judge[0][0]==0)return 1;
                else if(judge[2][0]==0)return 7;
                else if(judge[1][0]==0)return 4;
                else return 10;
            }

            else if(judge[0][2]+judge[1][2]+judge[2][2]==ans&&(judge[0][2]==0||judge[1][2]==0||judge[2][2]==0)) {
                if(judge[0][2]==0)return 3;
                else if(judge[2][2]==0)return 9;
                else if(judge[1][2]==0)return 6;
                else return 10;
            }
            else if(judge[0][0]+judge[1][1]+judge[2][2]==ans&&(judge[0][0]==0||judge[1][1]==0||judge[2][2]==0)) {
                if(judge[0][0]==0)return 1;
                else if(judge[2][2]==0)return 9;
                else if(judge[1][1]==0)return 5;
                else return 10;
            }
            else if(judge[0][2]+judge[1][1]+judge[2][0]==ans&&(judge[0][2]==0||judge[1][1]==0||judge[2][0]==0)) {
                if(judge[0][2]==0)return 3;
                else if(judge[2][0]==0)return 7;
                else if(judge[1][1]==0)return 5;
                else return 10;
            }
        }
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(judge[i][j]==0) {
                    return -2;//可以继续下棋
                }
            }
        }
        return -1;//平局
    }
    public static void main(String[] args) {
        new game();
    }
}

