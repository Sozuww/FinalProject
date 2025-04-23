import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;


public class Viewer extends JFrame {
    private Game game;
    private Image backgroundImage;
    private Image cookieImage;
    private Image goldenCookieImage;
    private MouseListener listener;


    public Viewer(Game game)
    {
        this.game = game;
        backgroundImage = new ImageIcon("Resources/backgroundImage.jpeg").getImage();
        cookieImage = new ImageIcon("Resources/PerfectCookie.png").getImage();
        goldenCookieImage = new ImageIcon("Resources/goldenCookie.png").getImage();

        this.setTitle("Cookie Clicker");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 800);
        this.setVisible(true);

    }

    public void addMouseListener(MouseListener listener)
    {
        super.addMouseListener(listener);
    }

    public void paint(Graphics g)
    {
        g.drawImage(backgroundImage, 0, 0, 1200, 800, this);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        g.drawImage(cookieImage, centerX - 150, centerY - 120, 300, 300, null);
        // g.setColor(Color.RED);
        // g.drawRect(centerX - 150,centerY - 150, 300, 300);

        int boxX = 450;
        int boxY = 60;
        int boxWidth = 300;
        int boxHeight = 100;

        Color box = new Color(0, 0, 0, 127);
        g.setColor(box);
        g.fillRect(boxX, boxY, boxWidth, boxHeight);

        g.setFont(new Font("TimesRoman", Font.PLAIN, 35));
        g.setColor(Color.WHITE);

        String cookieText = Long.toString(game.getCookies()) + " cookies";
        FontMetrics metrics = g.getFontMetrics(); // Gets the size info of the current font

        int textWidth = metrics.stringWidth(cookieText);
        int textHeight = metrics.getAscent(); // Used to align text vertically

        // Calculate centered position
        int textX = boxX + (boxWidth - textWidth) / 2;
        int textY = boxY + (boxHeight + textHeight) / 2 - 5; // -5 fine-tunes vertical centering

        g.drawString(cookieText, textX, textY);
    }
}
