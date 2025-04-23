import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static javax.swing.plaf.basic.BasicGraphicsUtils.drawString;

public class Game {
    private final Viewer window;
    private long cookies;
    private int clickVal;
    private int upgradeMultiplier;
    private ArrayList<cookieUpgrades> upgrades;

    public Game()
    {
        this.window = new Viewer(this);
        upgrades = new ArrayList<cookieUpgrades>();

        cookies = 0;
        clickVal = 1;
        upgradeMultiplier = 1;

        window.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e)
            {
                int centerX = window.getWidth() / 2;
                int centerY = window.getHeight() / 2;
                int mid = 150;

                // Checks if the click occurs within 200 pixels of the center of the screen
                if (e.getX() > centerX - mid && e.getX() < centerX + mid &&
                        e.getY() > centerY - mid && e.getY() < centerY + mid)
                {
                    incrementCookies();
                    window.repaint();
                    System.out.println("cookies: " + getCookies());
                }


            }
        });
    }



    public long getCookies() {
        return cookies;
    }


    public void incrementCookies()
    {
        cookies += (long) clickVal * upgradeMultiplier;
    }



    public static void main(String[] args)
    {
        Game game = new Game();
    }
}
