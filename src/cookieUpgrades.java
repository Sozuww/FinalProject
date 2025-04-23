import javax.swing.*;
import java.awt.*;

public class cookieUpgrades
{
    private Viewer game;
    private boolean clickable;

    public void draw(Graphics g)
    {
        int width = game.getWidth();
        int height = game.getHeight();
        Image img = new ImageIcon("Resources/goldenCookie.png").getImage();


        g.drawImage(img, width, height, null);
    }
}
