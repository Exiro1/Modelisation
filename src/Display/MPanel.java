package Display;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MPanel extends JPanel {



    private BufferedImage image;

    public MPanel(MFrame frame) {

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image,0,0,null);
    }


    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
