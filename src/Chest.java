import java.util.Random;

// сундук даёт +1 жизнь
public class Chest {
    private int x;
    private int y;
    private String image = "💎"; // 💎

    public Chest(int sizeBoard) {
        Random random = new Random();
        this.x = 1 + random.nextInt(sizeBoard);
        this.y = 1 + random.nextInt(sizeBoard);
    }

    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public String getImage() { return this.image; }
}
