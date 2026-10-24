import java.util.Random;

// обычный монстр, задаёт пример на сложение
public class Monster {
    private int x;
    private int y;
    private String image;

    // скины для обычных монстров
    private static final String[] SKINS = {
            "👾", // 👾
            "👻", // 👻
            "👽", // 👽
            "💀", // 💀
            "👹", // 👹
    };

    public Monster(int sizeBoard) {
        Random random = new Random();
        // координаты как у героя, от 1. в нижнем ряду монстров нет, там старт
        this.x = 1 + random.nextInt(sizeBoard);
        this.y = 1 + random.nextInt(sizeBoard - 1);

        this.image = SKINS[random.nextInt(SKINS.length)];
    }

    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public String getImage() { return this.image; }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean taskMonster(int difficultGame) {
        int maxNumber = 100;
        if (difficultGame == 1) maxNumber = 10;
        else if (difficultGame == 3) maxNumber = 1000;

        int a = Main.random.nextInt(maxNumber);
        int b = Main.random.nextInt(maxNumber);
        int trueAnswer = a + b;

        System.out.println("Реши пример: " + a + " + " + b + " = ?");
        int ans = Main.scanner.nextInt();
        return trueAnswer == ans;
    }
}
