package Application;

import javax.swing.*;
import java.awt.*;

public class AnimalForm {
    private JPanel _rootPanel;
    private JTextPane _title;
    private JButton renderButton;



    public void start(){

        JFrame win = new JFrame("Animal Rendering");
        win.setSize(new Dimension(500,600));
        win.setContentPane(_rootPanel);
        win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        win.setVisible(true);
    }
}
