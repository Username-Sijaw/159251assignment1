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
        super("Test Editor");
        Action[] actions=			//菜单项的各种功能
                {
                        new NewAction(),
                        new OpenAction(),
                        new SaveAction(),
                        new ExitAction(),//3

                        new CutAction(),
                        new CopyAction(),//5
                        new PasteAction(),

                        new AboutAction(),//7
                        new ExitAction(),
                };
        setJMenuBar(createJMenuBar(actions));		//根据actions创建菜单栏
        Container container=getContentPane();
        container.add(textPane, BorderLayout.CENTER);
        setSize(800, 1000);
        setVisible(true);
        //	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        public NewAction()
        {
            super("New(N)     Ctrl+N");
        }
        public void actionPerformed(ActionEvent e)
        {
            textPane.setDocument(new DefaultStyledDocument());
        }
    }
    class OpenAction extends AbstractAction		//打开
    {
        public OpenAction()
        {
            super("Open(O)     Ctrl+O");
        }
        public void actionPerformed(ActionEvent e)
        {
            int i=filechooser.showOpenDialog(Editor.this);			//显示打开文件对话框
            if(i==JFileChooser.APPROVE_OPTION)			//点击对话框打开选项
            {
                File f=filechooser.getSelectedFile();	//得到选择的文件
                try
                {
                    InputStream is=new FileInputStream(f);
                    textPane.read(is, "d");
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }
    class SaveAction extends AbstractAction		//保存
    {
        public SaveAction()
        {
            super("Store(S)     Ctrl+S");
        }
        public void actionPerformed(ActionEvent e)
        {
            int i=filechooser.showSaveDialog(Editor.this);
            if(i==JFileChooser.APPROVE_OPTION)
            {
                File f=filechooser.getSelectedFile();
                try
                {
                    FileOutputStream out=new FileOutputStream(f);
                    out.write(textPane.getText().getBytes());
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }

    class ExitAction extends AbstractAction		//退出
    {
        public ExitAction()
        {
            super("退出(X)");
        }
        public void actionPerformed(ActionEvent e)
        {
            dispose();
        }
    }

    class CopyAction extends AbstractAction		//复制
    {
        public CopyAction()
        {
            super("复制(C)     Ctrl+C");
        }
        public void actionPerformed(ActionEvent e)
        {
            textPane.copy();
        }
    }

    class PasteAction extends AbstractAction		//粘贴
    {
        public PasteAction()
        {
            super("粘贴(P)     Ctrl+V");
        }
        public void actionPerformed(ActionEvent e)
        {
            textPane.paste();
        }
    }

    class AboutAction extends AbstractAction
    {
        public AboutAction()
        {
            super("About us");
        }
        public void actionPerformed(ActionEvent e)
        {
            JOptionPane.showMessageDialog(Editor.this,"Yu Tianchuan and Zheng Yichen"  ,"Managers",JOptionPane.PLAIN_MESSAGE);
        }
    }

    public static void main(String[] args)
    {
        new Editor();
    }
}