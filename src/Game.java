import java.awt.event.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Game implements MouseListener, MouseMotionListener{
    private final Viewer window;
    private long cookies;
    private int clickVal;
    private int upgradeMultiplier;
    private ArrayList<cookieUpgrades> upgrades;
    private cookieUpgrades smallCookies;

    public Game()
    {
        this.window = new Viewer(this);
        upgrades = new ArrayList<cookieUpgrades>();
        smallCookies = new cookieUpgrades(window);

        cookies = 0;
        clickVal = 1;
        upgradeMultiplier = 1;

        // Spawns small cookies every 3 seconds
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                smallCookies.spawnCookie();
                window.repaint();
            }
        }, 0, 3000);

        window.addMouseListener(this);
    }



    public long getCookies() {
        return cookies;
    }

    public int getUpgradeMultiplier() {
        return upgradeMultiplier;
    }

    public void setUpgradeMultiplier(int upgradeMultiplier) {
        this.upgradeMultiplier = upgradeMultiplier;
    }


    public void incrementCookies()
    {
        cookies += (long) clickVal * upgradeMultiplier;
    }


    public cookieUpgrades getSmallCookies() {
        return smallCookies;
    }

    public void mouseClicked(MouseEvent e) {
        // Check for small cookie clicks first
        if (smallCookies.checkClick(e.getX(), e.getY())) {
            window.repaint();
            return;
        }

        // Only check for main cookie click if no small cookie was clicked
        int centerX = window.getWidth() / 2;
        int centerY = window.getHeight() / 2;
        int mid = 150;

        if (e.getX() > centerX - mid && e.getX() < centerX + mid &&
                e.getY() > centerY - mid && e.getY() < centerY + mid) {
            incrementCookies();
            window.repaint();
            System.out.println("cookies: " + getCookies());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public static void main(String[] args)
    {
        Game game = new Game();
    }


    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
