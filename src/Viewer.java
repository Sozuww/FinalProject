import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Viewer extends JFrame {
    private Game game;
    private Image backgroundImage;
    private Image cookieImage;


    public Viewer(Game game)
    {
        this.game = game;
        backgroundImage = new ImageIcon("Resources/backgroundImage.jpeg").getImage();
        cookieImage = new ImageIcon("Resources/PerfectCookie.png").getImage();
        this.setTitle("Cookie Clicker");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 800);
        this.setVisible(true);

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e)
            {
                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;
                int mid = 150;

                // Checks if the click occurs within 200 pixels of the center of the screen
                if (e.getX() > centerX - mid && e.getX() < centerX + mid &&
                e.getY() > centerY - mid && e.getY() < centerY + mid)
                {
                    game.incrementCookies();
                    System.out.println("cookies: " + game.getCookies());
                }

            }
        });
    }

    public void paint(Graphics g)
    {
        g.drawImage(backgroundImage, 0, 0, 1200, 800, this);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        g.drawImage(cookieImage, centerX - 150, centerY - 150, 300, 300, null);
        g.setColor(Color.RED);
        g.drawRect(centerX - 150,centerY - 150, 300, 300);


    }
}
