public class Game {
    private final Viewer window;
    private long cookies;
    private int clickVal;
    private int upgradeMultiplier;

    public Game()
    {
        this.window = new Viewer(this);
        cookies = 0;
        clickVal = 1;
        upgradeMultiplier = 1;
    }

    public long getCookies() {
        return cookies;
    }

    public int getClickVal() {
        return clickVal;
    }

    public int getUpgradeMultiplier() {
        return upgradeMultiplier;
    }

    public void incrementCookies()
    {
        cookies += clickVal * upgradeMultiplier;
    }



    public static void main(String[] args)
    {
        Game game = new Game();
    }
}
