import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BackgroundImagePane extends JPanel {

    public BackgroundImagePane(JPanel panel,String imageName) {
        setLayout(new BorderLayout());
        BackgroundPane bgPane = new BackgroundPane();
        bgPane.setLayout(new GridBagLayout());
        add(bgPane);

        try {
            BufferedImage bg = ImageIO.read(new File(imageName));
            bgPane.setBackgroundImage(bg);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        var xx = panel;
        xx.setBounds(200, 100, 750, 500);

        xx.setOpaque(false);
        bgPane.add(xx);

    }

}

 class BackgroundPane extends JPanel {

    private BufferedImage img;

    @Override
    public Dimension getPreferredSize() {
        BufferedImage img = getBackgroundImage();

        Dimension size = super.getPreferredSize();
        if (img != null) {
            size.width = Math.max(size.width, img.getWidth());
            size.height = Math.max(size.height, img.getHeight());
        }

        return size;
    }

    public BufferedImage getBackgroundImage() {
        return img;
    }

    public void setBackgroundImage(BufferedImage value) {
        if (img != value) {
            BufferedImage old = img;
            img = value;
            firePropertyChange("background", old, img);
            revalidate();
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage bg = getBackgroundImage();
        if (bg != null) {
            int x = (getWidth() - bg.getWidth()) / 2;
            int y = (getHeight() - bg.getHeight()) / 2;
            g.drawImage(bg, x, y, this);
        }
    }
}
