import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class cookieUpgrades
{
    private Viewer game;
    private ArrayList<SmallCookie> smallCookies;
    private Random random;
    private Image smallCookieImage;

    public cookieUpgrades(Viewer game)
    {
        this.game = game;
        this.smallCookies = new ArrayList<>();
        this.random = new Random();
        this.smallCookieImage = new ImageIcon("Resources/goldenCookie.png").getImage();
    }

    public void spawnCookie()
    {
        int x = random.nextInt(game.getWidth() - 50);
        int y = random.nextInt(game.getHeight() - 50);

        while (x < 200)
        {
            x = random.nextInt(game.getWidth() - 50);
        }
        smallCookies.add(new SmallCookie(x, y));
    }

    public boolean checkClick(int mouseX, int mouseY)
    {
        for (int i = smallCookies.size() - 1; i >= 0; i--)
        {
            SmallCookie cookie = smallCookies.get(i);
            if (cookie.isClicked(mouseX, mouseY))
            {
                smallCookies.remove(i);
                game.getGame().setUpgradeMultiplier(game.getGame().getUpgradeMultiplier() + 1);
                return true;
            }
        }
        return false;
    }

    public void draw(Graphics g)
    {
        for (SmallCookie cookie : smallCookies)
        {
            cookie.draw(g, smallCookieImage);
        }
    }

    private static class SmallCookie
    {
        private int x;
        private int y;
        private static final int SIZE = 30;

        public SmallCookie(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        public boolean isClicked(int mouseX, int mouseY)
        {
            return mouseX >= x && mouseX <= x + SIZE &&
                    mouseY >= y && mouseY <= y + SIZE;
        }

        public void draw(Graphics g, Image image)
        {
            g.drawImage(image, x, y, SIZE, SIZE, null);
        }
    }
}