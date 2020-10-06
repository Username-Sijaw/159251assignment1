import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.text.*;


public class Editor extends JFrame {
    public JTextPane textPane = new JTextPane(); //文本窗格d，编辑窗口
    public JFileChooser filechooser = new JFileChooser(); //文件选择器
    public Editor()
    {
    }
    private JMenuBar createJMenuBar(Action[] actions)	//创建菜单栏的函数
    {
        JMenuBar menubar=new JMenuBar();
        JMenu menuFile=new JMenu("File(F)");
        JMenu menuSearch=new JMenu("Search(S)");
        JMenu menuView=new JMenu("View(V)");
        JMenu menuManage=new JMenu("Manage(M)");
        JMenu menuAbout=new JMenu("About(A)");
        menuFile.add(new JMenuItem(actions[0]));
        menuFile.add(new JMenuItem(actions[1]));
        menuFile.add(new JMenuItem(actions[2]));
        menuFile.add(new JMenuItem(actions[8]));

        menuView.add(new JMenuItem(actions[4]));
        menuView.add(new JMenuItem(actions[5]));
        menuView.add(new JMenuItem(actions[6]));

        menuAbout.add(new JMenuItem(actions[7]));
        menubar.add(menuFile);
        menubar.add(menuSearch);
        menubar.add(menuView);
        menubar.add(menuManage);
        menubar.add(menuAbout);
        return menubar;
    }
    class NewAction extends AbstractAction		//新建
    {

    }
    class OpenAction extends AbstractAction		//打开
    {

    }
    class SaveAction extends AbstractAction		//保存
    {

    }

    class ExitAction extends AbstractAction		//退出
    {

    }

    class CopyAction extends AbstractAction		//复制
    {

    }

    class PasteAction extends AbstractAction		//粘贴
    {

    }

    class AboutAction extends AbstractAction
    {

    }

    public static void main(String[] args)
    {
        new Editor();
    }
}