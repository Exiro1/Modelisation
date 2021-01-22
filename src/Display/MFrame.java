package Display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MFrame extends JFrame {

    private MPanel panel;

    public MFrame() throws HeadlessException {
        super("Modelisation");
        setSize(800,650);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
                                public void windowClosing(WindowEvent e) {
                                    System.exit(0);
                                }
                            }
        );
        panel = new MPanel(this);
        getContentPane().add(panel);

    }


    public MPanel getPanel() {
        return panel;
    }

    public void setPanel(MPanel panel) {
        this.panel = panel;
    }
}
