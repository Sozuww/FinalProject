import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class cookieUpgrades
{
    private final Viewer game;
    private final ArrayList<SmallCookie> smallCookies;
    private final Random random;
    private final Image smallCookieImage;

    public cookieUpgrades(Viewer game)
    {
        // Defining instance variables
        this.game = game;
        this.smallCookies = new ArrayList<>();
        this.random = new Random();

        // Initializing golden cookie image
        this.smallCookieImage = new ImageIcon("Resources/goldenCookie.png").getImage();
    }

    public void spawnCookie()
    {
        // Creating a random location
        int x = random.nextInt(game.getWidth() - 50);
        int y = random.nextInt(game.getHeight() - 50);

        // Making sure they don't spawn over upgrade buttons
        while (x < 200 && (y > 50 && y < 750))
        {
            x = random.nextInt(game.getWidth() - 50);
        }

        // Adds a new golden cookie to the array
        smallCookies.add(new SmallCookie(x, y));
    }

    // Checks for click at specific location
    public boolean checkClick(int mouseX, int mouseY)
    {
        // Loops through arraylist of golden cookies
        for (int i = smallCookies.size() - 1; i >= 0; i--)
        {
            SmallCookie cookie = smallCookies.get(i);
            // When cookie is clicked at loc., remove the cookie and increase the upgrade multiplier
            if (cookie.isClicked(mouseX, mouseY))
            {
                smallCookies.remove(i);
                game.getGame().setUpgradeMultiplier(game.getGame().getUpgradeMultiplier() + 1);
                return true;
            }
        }
        return false;
    }

    // Draw method that loops through the whole array
    public void draw(Graphics g)
    {
        for (SmallCookie cookie : smallCookies)
        {
            cookie.draw(g, smallCookieImage);
        }
    }

    private static class SmallCookie
    {
        // Set location variables and cookie size
        private final int x;
        private final int y;
        private static final int SIZE = 30;

        public SmallCookie(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        // Checking if location at (x,y) was clicked
        public boolean isClicked(int mouseX, int mouseY)
        {
            return mouseX >= x && mouseX <= x + SIZE &&
                    mouseY >= y && mouseY <= y + SIZE;
        }

        // Draw helper method
        public void draw(Graphics g, Image image)
        {
            g.drawImage(image, x, y, SIZE, SIZE, null);
        }
    }
}