// дракон, у него умножение. чем сложнее режим тем больше числа
public class BigMonster extends Monster {

    public BigMonster(int sizeBoard) {
        super(sizeBoard);
        setImage("🐉"); // 🐉
    }

    @Override
    public boolean taskMonster(int difficultGame) {
        int maxNumber = 5;
        if (difficultGame == 2) maxNumber = 10;
        else if (difficultGame == 3) maxNumber = 20;

        int a = 2 + Main.random.nextInt(maxNumber);
        int b = 2 + Main.random.nextInt(maxNumber);

        System.out.println("Это ДРАКОН! Он сильнее, реши: " + a + " * " + b + " = ?");
        int ans = Main.scanner.nextInt();
        return a * b == ans;
    }
}
