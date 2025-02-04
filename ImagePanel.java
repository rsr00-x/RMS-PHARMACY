//package gui;

import java.awt.*;
import javax.swing.*;

public class ImagePanel extends JPanel {
    private Image img;

    public ImagePanel(String imgPath) {
        this.img = new ImageIcon("Images/LOGO.jpeg").getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        g.drawImage(img, 0, 0, width, height, this);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(img.getWidth(this), img.getHeight(this));
    }
}
