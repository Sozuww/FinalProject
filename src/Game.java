/**
 * Cookie Clicker
 *
 * Built by K. Mawakana on May 9, 2025 for CS2 @ Menlo School
 * with inspiration from Julien "Orteil" Thiennot "Cookie Clicker" (2013)
 *
 * Cookie Clicker is an idle game where you bake cookies
 * and upgrade to bake even more and as much as possible.
 */

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Game implements MouseListener, MouseMotionListener{
    private final Viewer window;
    private long cookies;
    private int upgradeMultiplier;
    private cookieUpgrades smallCookies;
    private ArrayList<UpgradeButton> upgradeButtons;

    public Game()
    {
        // Creates new viewer class
        this.window = new Viewer(this);
        startGame();
        window.addMouseListener(this);
    }

    private void startGame()
    {
        // Initialize variables
        smallCookies = new cookieUpgrades(window);
        upgradeButtons = new ArrayList<>();

        cookies = 0;
        upgradeMultiplier = 1;

        // Initialize upgrade buttons
        upgradeButtons.add(new UpgradeButton(20, 100, "Cursor", 15, 0.1));
        upgradeButtons.add(new UpgradeButton(20, 170, "Grandma", 100, 1));
        upgradeButtons.add(new UpgradeButton(20, 240, "Farm", 1100, 8));
        upgradeButtons.add(new UpgradeButton(20, 310, "Mine", 12000, 47));
        upgradeButtons.add(new UpgradeButton(20, 380, "Factory", 130000, 260));
        upgradeButtons.add(new UpgradeButton(20, 450, "Bank", 1400000, 1400));
        upgradeButtons.add(new UpgradeButton(20, 520, "Temple", 20000000, 7800));
        upgradeButtons.add(new UpgradeButton(20, 590, "Wizard Tower", 330000000, 44000));
        upgradeButtons.add(new UpgradeButton(20, 660, "Shipment", 5100000000L, 260000));

        // Starts 2 timers for essential game functions
        startTimers();
    }

    public void startTimers()
    {
        // Spawns small cookies every 12 seconds
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                smallCookies.spawnCookie();
                window.repaint();
            }
        }, 0, 12000);

        // Idle cookie production timer
        Timer idleTimer = new Timer();
        idleTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                double totalCPS = 0;
                for (UpgradeButton button : upgradeButtons) {
                    double cps = button.getCookiesPerSecond();
                    totalCPS += cps;
                    button.addCookiesGenerated(cps);
                }
                cookies += (long) totalCPS;
                window.repaint();
            }
        }, 0, 500); // Updates every half-second
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
        cookies += upgradeMultiplier;
    }

    public cookieUpgrades getSmallCookies() {
        return smallCookies;
    }

    // Checks for  a mouse click is detected: main cookie, golden cookie, and upgrade button
    public void mouseClicked(MouseEvent e) {
        // Check for upgrade button clicks
        for (UpgradeButton button : upgradeButtons) {
            if (button.isClicked(e.getX(), e.getY())) {
                if (cookies >= button.getCost()) {
                    cookies -= button.getCost();
                    button.purchase();
                    window.repaint();
                }
                return;
            }
        }

        // Check for small cookie clicks
        if (smallCookies.checkClick(e.getX(), e.getY())) {
            window.repaint();
            return;
        }

        // Check for main cookie click
        int centerX = window.getWidth() / 2;
        int centerY = window.getHeight() / 2;
        int mid = 150;

        if (e.getX() > centerX - mid && e.getX() < centerX + mid &&
                e.getY() > centerY - mid && e.getY() < centerY + mid) {
            incrementCookies();
            window.repaint();
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

    public ArrayList<UpgradeButton> getUpgradeButtons() {
        return upgradeButtons;
    }
}
