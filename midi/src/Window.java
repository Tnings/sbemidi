import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Window{
     static String betaVersion = "0.4.1";
     static JButton b = new JButton("Set record button");;
     static JButton rb = new JButton("Map recording to key");
     static JPanel p = new JPanel();
     static JScrollPane sp = new JScrollPane(p);
     static JTextArea cellHeader = new JTextArea("Audio clips \n");
     static JFrame f=new JFrame("SBE MIDI "+betaVersion);
     static JMenuBar mb = new JMenuBar();
     static Color textArea;
     static Color button;
     static Color frame;
     static Color Menu;
     static String themeColor = "Dark";
     static Font universalFont;
     static ImageIcon img = new ImageIcon("ICON.png");
     static JLabel l;

    public static void make() {

        try {
            setThemeColor();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        f.setSize(600,500);
        f.setResizable(false);
        b.setBounds(100,100,200, 40);

        f.setIconImage(img.getImage());

        l = new JLabel(new ImageIcon("Banner.png"));

        l.setBounds(0,0,400,100);
        f.add(l);


        cellHeader.setText(recordingCells());
        cellHeader.setEditable(false);
        cellHeader.setBounds(400,0, 200,500);

        p.add(cellHeader);

        universalFont = new Font("Calibri",Font.PLAIN,18);

        cellHeader.setFont(universalFont);
        b.setFont(universalFont);
        b.setForeground(Color.white);
        b.setBackground(button);
        f.getContentPane().setBackground(frame);
        f.setBackground(frame);

        cellHeader.setBackground(textArea);
        cellHeader.setForeground(Color.white);
        sp.getViewport().getView().setBackground(textArea);

        JMenu File = new JMenu("File");
        JMenu Mode = new JMenu("Mode");
        JMenu Theme = new JMenu("Theme");
        JMenu Develepor = new JMenu("Developer Tools");

        JMenuItem Light = new JMenuItem("Light Theme");
        JMenuItem Dark = new JMenuItem("Dark Theme");
        JMenuItem Midi = new JMenuItem("Midi Keyboard");
        JMenuItem keyBoard = new JMenuItem("Normal Keyboard - Not available in this Beta");
        JMenuItem m1 = new JMenuItem("Open Recording Location");
        JMenuItem console = new JMenuItem("Dev console");

        mb.setBackground(Menu);

        if(themeColor.equals("Dark")){
            b.setForeground(Color.white);
        }
        else
            b.setForeground(Color.black);

        sp.createVerticalScrollBar();
        sp.setVisible(true);
        sp.setSize(200,500);
        sp.setLocation(400,0);

        rb.setBounds(100,140,200, 40);
        rb.setFont(universalFont);
        rb.setBackground(button);
        if(themeColor.equals("Dark")){
            rb.setForeground(Color.white);
        }
        else
            rb.setForeground(Color.black);

        m1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().open(new File("Recordings"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        Dark.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                themeColor = "Dark";
                try {
                    FileManagement.saveFile();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        Light.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                themeColor = "Light";
                try {
                    FileManagement.saveFile();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                StartUpManager.c.removeKey();
                MidiSource.settingKey = true;
                b.setEnabled(false);
                System.out.println("Test ");
            }
        });

        rb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MidiSource.mappingKey = true;
                MidiSource.fileMappingName = "Test.wav";
            }
        });


        Develepor.add(console);

        File.add(m1);

        Mode.add(Midi);
        Mode.add(keyBoard);

        Theme.add(Light);
        Theme.add(Dark);

        mb.add(Theme);
        mb.add(Mode);
        mb.add(File);
        mb.add(Develepor);

        f.add(rb);
        f.setJMenuBar(mb);
        f.add(b);
        f.add(sp);


        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
    }

    public static void enableRecordButton(){
        b.setEnabled(true);
    }

    public static String recordingCells(){
        File[] files = FileManagement.getEverySavedRecording();
        int numOfCells = 0;
        int x = 400, y = 0, width = 200, height = 10;
        StringBuilder str = new StringBuilder("File Recordings\n\n");
        for(File i: files){
            String fileName = i.getName();
            str.append(fileName+"\n");
        }
        String fullList = str.toString();
        return fullList;
    }

    public static void setThemeColor() throws FileNotFoundException {
        if(!FileManagement.checkIfFirstSetup()){
            if(FileManagement.readThemeSetting().equals("Dark")){
                themeColor = "Dark";
                System.out.println("Starting in dark");
                textArea = new Color(59, 59, 59);
                button = new Color(140, 140, 140);
                frame = new Color(74, 74, 74);
                Menu = new Color(153, 153, 153);
            }
            else {
                themeColor = "Light";
                System.out.println("Starting in light");
                textArea = new Color(156, 156, 156);
                button = new Color(227, 227, 227);
                frame = new Color(242, 242, 242);
                Menu = new Color(153, 153, 153);
            }
        }
    }

}