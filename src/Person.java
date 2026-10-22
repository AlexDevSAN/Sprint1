public class Person {
    private int x;
    private int y;
    private int live;
    private String image;

    public Person(int x, int y, int live, String image) {
        this.x = x;
        this.y = y;
        this.live = live;
        this.image = image;
    }

    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public int getLive() { return this.live; }
    public String getImage() { return this.image; }

    public void move(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }

    public boolean isMoveCorrect(int targetX, int targetY) {
        boolean isAdjacentX = (targetY == this.y && Math.abs(targetX - this.x) == 1);
        boolean isAdjacentY = (targetX == this.x && Math.abs(targetY - this.y) == 1);
        return isAdjacentX || isAdjacentY;
    }

    public void downLive() {
        if (this.live > 0) {
            this.live--;
        }
    }
}
