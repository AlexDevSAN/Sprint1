import java.awt.Desktop;
import java.util.Random;
import java.net.URI;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        int sizeBoard = 5;

        System.out.println("Привет! Ты готов начать играть в игру? (Напиши: ДА или НЕТ)");
        String answer = scanner.nextLine();

        if (answer.equalsIgnoreCase("да")) {
            System.out.println("Как тебя зовут?");
            String name = scanner.nextLine();
            System.out.println("Сколько тебе лет?");
            String age = scanner.nextLine();

            System.out.println("Выбери сложность игры: нуб, про, читер");
            String difficultChoice = scanner.nextLine();

            int startLive = 3;
            String heroSkin = "🧙"; // 🧙
            int difficultGame = 2;

            if (difficultChoice.equalsIgnoreCase("нуб")) {
                System.out.println("Выбран режим НУБ! У тебя 5 жизней, а твой герой — испуганная обезьянка!");
                startLive = 5;
                heroSkin = "🙈"; // 🙈
                difficultGame = 1;
            } else if (difficultChoice.equalsIgnoreCase("про")) {
                System.out.println("Выбран режим ПРО! Ты — сильный маг с 3 жизнями.");
                startLive = 3;
                heroSkin = "🧙"; // 🧙
                difficultGame = 2;
            } else if (difficultChoice.equalsIgnoreCase("читер")) {
                System.out.println("Выбран режим ЧИТЕР! Жизней бесконечно, а твой герой — крутой робот!");
                startLive = 999;
                heroSkin = "🤖"; // 🤖
                difficultGame = 3;
            }

            System.out.println("Удачи, " + name + "! Погнали!");

            int startX = 1 + random.nextInt(sizeBoard);
            Person person = new Person(startX, sizeBoard, startLive, heroSkin);

            Game game = new Game(person, sizeBoard, difficultGame);
            game.play();

        } else {
            System.out.println("Очень жаль! Введи duck и включи ВПН");
            String rickroll = scanner.nextLine();
            if (rickroll.equalsIgnoreCase("duck")) {
                try {
                    Desktop.getDesktop().browse(new URI("https://youtube.com"));
                } catch (Exception e) {
                    System.out.println("Не удалось открыть ссылку.");
                }
            }
        }
    }
}
