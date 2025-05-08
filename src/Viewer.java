import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;


public class Viewer extends JFrame {
    private Game game;
    private Image backgroundImage;
    private Image cookieImage;
    private final int WINDOW_WIDTH = 1200;
    private final int WINDOW_HEIGHT = 800;


    public Viewer(Game game)
    {
        this.game = game;
        backgroundImage = new ImageIcon("Resources/backgroundImage.jpeg").getImage();
        cookieImage = new ImageIcon("Resources/PerfectCookie.png").getImage();

        this.setTitle("Cookie Clicker");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);

    }

    public void addMouseListener(MouseListener listener)
    {
        super.addMouseListener(listener);
    }

    public void paint(Graphics g)
    {
        g.drawImage(backgroundImage, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        g.drawImage(cookieImage, centerX - 150, centerY - 120, 300, 300, null);

        paintCookieCount(g);
        // Draw upgrade buttons
        for (UpgradeButton button : game.getUpgradeButtons()) {
            button.draw(g);
        }

        // Draw small cookies
        game.getSmallCookies().draw(g);
    }

    public void paintCookieCount(Graphics g)
    {
        // Draw cookie count box
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
        int textHeight = metrics.getAscent(); // Aligns text vertically

        // Calculate centered position
        int textX = boxX + (boxWidth - textWidth) / 2;
        int textY = boxY + (boxHeight + textHeight) / 2 - 5;

        g.drawString(cookieText, textX, textY);

        // Draw idle production rate (without box)
        int cpsY = boxY + boxHeight + 20; // Position it below the cookie count box

        // Calculate total cookies per second
        double totalCPS = 0;
        for (UpgradeButton button : game.getUpgradeButtons())
        {
            totalCPS += button.getCookiesPerSecond();
        }

        // Format the CPS to 2 decimal places
        String cpsText = String.format("%.2f cookies per second", totalCPS);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 17));
        metrics = g.getFontMetrics();
        textWidth = metrics.stringWidth(cpsText);
        textX = boxX + (boxWidth - textWidth) / 2;
        textY = cpsY + 15; // Adjust vertical position

        g.drawString(cpsText, textX, textY);

        String clickText = game.getUpgradeMultiplier() + " cookies per click";

        g.drawString(clickText, textX, textY + 20);

    }

    public Game getGame() {
        return game;
    }
}
