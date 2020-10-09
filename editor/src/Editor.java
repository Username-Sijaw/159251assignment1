import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                        new SearchAction(),
                        new PrintAction(),
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
        JMenu menuPrint=new JMenu("Print(P）");
        menuFile.add(new JMenuItem(actions[0]));
        menuFile.add(new JMenuItem(actions[1]));
        menuFile.add(new JMenuItem(actions[2]));
        menuFile.add(new JMenuItem(actions[8]));

        menuView.add(new JMenuItem(actions[4]));
        menuView.add(new JMenuItem(actions[5]));
        menuView.add(new JMenuItem(actions[6]));

        menuAbout.add(new JMenuItem(actions[7]));
        menuPrint.add(new JMenuItem(actions[9]));
        menubar.add(menuFile);
        menubar.add(menuSearch);
        menubar.add(menuView);
        menubar.add(menuManage);
        menubar.add(menuAbout);
        menubar.add(menuPrint);
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
    class  PrintAction extends AbstractAction
    {
        public PrintAction(){
            super("print");

        }
        public void  actionPerformed(ActionEvent e){

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

    class CutAction extends AbstractAction		//剪切
    {
        public CutAction()
        {
            super("剪切(T)     Ctrl+X");
        }
        public void actionPerformed(ActionEvent e)
        {
            textPane.cut();
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


    class AboutAction extends AbstractAction    //about
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

    class SearchAction extends AbstractAction
    {
        public SearchAction()
        {
            super("Search");
        }
        public void actionPerformed(ActionEvent e)
        {
            //创建窗体
            JFrame f1 = new JFrame();
            f1.setLayout(new FlowLayout());//采用流式布局
            JPanel p1 = new JPanel();
            JLabel j1 = new JLabel("find：");//按布局安排“查找”标签和文本框
            JTextField t1= new JTextField();//创建查找的文本行
            t1.setColumns(10);//将文本框设置为10列
            p1.add(j1);
            p1.add(t1);
            JButton b1 = new JButton("find");
            f1.add(p1);
            p1.add(b1);
            j1.setBounds(690, 400, 500, 150);
            f1.setSize(500,150);//设置框架的大小
            f1.setVisible(true);

            b1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    String key = t1.getText();//获取需要搜索的内容
                    String msg = textPane.getText();//获取文本的全部内容
                    
                    int m = msg.indexOf(key,m);//返回从 m位置开始查找指定字符在字符串中第一次出现处的索引,如果没有这样的字符，则返回 -1。

                    int j;
                    if(m != -1) {//如果存在这样的字符串
                        textPane.select(m,m+key.length());//选择该字符串
                        j = m;
                        m = m + key.length();//从该位置重新开始搜索
                    }
                    else {//从头开始再搜索
                        m = 0;
                        m = msg.indexOf(key,m);
                        textPane.select(m,m+key.length());
                        j=m;
                        m = m+key.length();
                    }
                }
            });
        }
    }

    public static void main(String[] args)
    {
        new Editor();
    }
















    //时间显示

    private JLabel getTimelabel() {

        JLabel timelabel = null;

        timelabel = new JLabel("");

        timelabel.setBounds(5, 65, 200, 20);

        timelabel.setFont(new Font("微软雅黑", Font.BOLD, 12));

        timelabel.setForeground(new Color(182, 229, 248));

        JLabel finalTimelabel = timelabel;
        Timer time = new Timer(1000, new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent arg0) {

                finalTimelabel.setText(new SimpleDateFormat("yyyy年MM月dd日 EEEE hh:mm:ss").format(new Date()));

            }

        });

        time.start();

        return timelabel;
    }

}
