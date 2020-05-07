import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Console {

    static JFrame f = new JFrame("Developer Console");
    static JTextArea out = new JTextArea();
    static JTextArea in = new JTextArea();
    static JButton enter = new JButton("Enter");

    public static void make(){
        f.setSize(600,600);
        f.setResizable(false);
        f.setVisible(true);

        out.setEditable(false);
        out.setBounds(0,0,600,500);

    }


}
