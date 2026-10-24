// вампир загадывает число от 1 до 10, надо угадать
public class GuessMonster extends Monster {

    public GuessMonster(int sizeBoard) {
        super(sizeBoard);
        setImage("🧛"); // 🧛
    }

    @Override
    public boolean taskMonster(int difficultGame) {
        int tries = 4;
        if (difficultGame == 2) tries = 3;
        else if (difficultGame == 3) tries = 2;

        int secret = 1 + Main.random.nextInt(10);
        System.out.println("Вампир загадал число от 1 до 10. Угадай! Попыток: " + tries);

        for (int i = 1; i <= tries; i++) {
            int guess = Main.scanner.nextInt();
            if (guess == secret) {
                return true;
            }
            if (guess < secret) {
                System.out.println("Больше!");
            } else {
                System.out.println("Меньше!");
            }
        }

        System.out.println("Вампир загадал " + secret);
        return false;
    }
}
