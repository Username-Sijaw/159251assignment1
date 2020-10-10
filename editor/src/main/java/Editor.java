import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Calendar;


public class Editor extends JFrame {
    public JTextPane textPane = new JTextPane(); //文本窗格d，编辑窗口
    public JFileChooser filechooser = new JFileChooser(); //文件选择器
    public Editor()
    {
        super("Text Editor");
        Action[] actions=			//菜单项的各种功能
                {
                        new NewAction(),
                        new OpenAction(),
                        new SaveAction(),
                        new Print(),//3
                        new ExitAction(),

                        new CutAction(),
                        new CopyAction(),//6
                        new PasteAction(),

                        new AboutAction(),//8

                        new Time(),
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
        JMenu menuView=new JMenu("Tool(T)");
        JMenu menuTime=new JMenu("T&D(T)");
        JMenu menuAbout=new JMenu("About(A)");

        menuFile.add(new JMenuItem(actions[0]));
        menuFile.add(new JMenuItem(actions[1]));
        menuFile.add(new JMenuItem(actions[2]));
        menuFile.add(new JMenuItem(actions[3]));
        menuFile.add(new JMenuItem(actions[4]));

        menuView.add(new JMenuItem(actions[5]));
        menuView.add(new JMenuItem(actions[6]));
        menuView.add(new JMenuItem(actions[7]));

        menuAbout.add(new JMenuItem(actions[8]));
        menuTime.add(new JMenuItem(actions[9]));

        menubar.add(menuFile);
        menubar.add(menuSearch);
        menubar.add(menuView);
        menubar.add(menuTime);
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
            super("Exit(X)");
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
            super("Cut(T)     Ctrl+X");
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
            super("Copy(C)     Ctrl+C");
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
            super("Paste(P)     Ctrl+V");
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
            JOptionPane.showMessageDialog(Editor.this,"Yu Tianchuan and Zheng Yichen"  ,"About us",JOptionPane.PLAIN_MESSAGE);
        }
    }
    class Print extends AbstractAction{
        public Print()
        {
            super("Print(P)     Ctrl+C");
        }
        public void actionPerformed(ActionEvent e)
        {
            JFileChooser fileChooser = new JFileChooser(); //创建打印作业
            int state = fileChooser.showOpenDialog(null);
            if(state == fileChooser.APPROVE_OPTION){
                File file = new File("D:/assignment.txt"); //获取选择的文件
                //构建打印请求属性集
                HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
                //设置打印格式，因为未确定类型，所以选择autosense
                DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
                //查找所有的可用的打印服务
                PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
                //定位默认的打印服务
                PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
                //显示打印对话框
                PrintService service = ServiceUI.printDialog(null, 200, 200, printService,
                        defaultService, flavor, pras);
                if(service != null){
                    try {
                        DocPrintJob job = service.createPrintJob(); //创建打印作业
                        FileInputStream fis = new FileInputStream(file); //构造待打印的文件流
                        DocAttributeSet das = new HashDocAttributeSet();
                        Doc doc = new SimpleDoc(fis, flavor, das);
                        job.print(doc, pras);
                    } catch (Exception event) {
                        event.printStackTrace();
                    }
                }
            }
        }
    }

    class Time extends AbstractAction{
        public Time(){
            super("Time and Date");
        }
        public void actionPerformed(ActionEvent e)
        {
            Calendar c = Calendar.getInstance();//可以对每个时间域单独修改   对时间进行加减操作等

            int year = c.get(Calendar.YEAR);

            int month = c.get(Calendar.MONTH);

            int date = c.get(Calendar.DATE);

            int hour = c.get(Calendar.HOUR_OF_DAY);

            int minute = c.get(Calendar.MINUTE);

            int second = c.get(Calendar.SECOND);

            JOptionPane.showMessageDialog(Editor.this,year + "/" + month + "/" + date + " " +hour + ":" +minute + ":" + second);
        }
    }

    public static void main(String[] args)
    {
        new Editor();
    }
}
