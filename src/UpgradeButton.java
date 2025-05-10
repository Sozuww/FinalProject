import java.awt.*;

public class UpgradeButton {

    // Initializing instance Variables
    private final int x, y;
    private final int WIDTH = 200;
    private final int HEIGHT = 60;
    private final String name;
    private long cost;
    private int level;
    private final double cookiesPerSecond;
    private double totalCookiesGenerated;
    
    public UpgradeButton(int x, int y, String name, long baseCost, double baseCPS) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.cost = baseCost;
        this.level = 0;
        this.cookiesPerSecond = baseCPS;
        this.totalCookiesGenerated = 0;
    }
    
    public boolean isClicked(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + WIDTH &&
               mouseY >= y && mouseY <= y + HEIGHT;
    }
    
    public void draw(Graphics g) {
        // Draw button background
        g.setColor(new Color(0, 0, 0, 180));
        g.fillRect(x, y, WIDTH, HEIGHT);
        
        // Draw button text
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        String displayText = name + " (Lvl " + level + ") (+" + cookiesPerSecond + "/s)";
        g.drawString(displayText, x + 10, y + 25);

        // Draw cost
        String costText = "Cost: " + cost + " cookies";
        g.drawString(costText, x + 10, y + 45);
        
        // Draw total cookies generated
        String generatedText = String.format("Generated: %.1f", totalCookiesGenerated);
        g.drawString(generatedText, x + WIDTH + 10, y + 35);
    }
    
    public long getCost() {
        return cost;
    }
    
    public void purchase() {
        level++;
        cost = (long)(cost * 1.15); // 15% increase in cost per level
    }
    
    public double getCookiesPerSecond() {
        return cookiesPerSecond * level;
    }
    
    public void addCookiesGenerated(double amount) {
        this.totalCookiesGenerated += amount;
    }

} 