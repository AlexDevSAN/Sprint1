import java.awt.Desktop;
import java.util.Random;
import java.net.URI;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    static String leftBlock = " | ";
    static String rightBlock = " |";
    static String wall = " + —— + —— + —— + —— + —— + ";

    public static void main(String[] args) {
        int step = 0, sizeBoard = 5, castleY = 1;
        String castle = "\uD83C\uDFF0"; // 🏰

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
            String heroSkin = "\uD83E\uDDD8"; // 🧙
            int difficultGame = 2;

            if (difficultChoice.equalsIgnoreCase("нуб")) {
                System.out.println("Выбран режим НУБ! У тебя 5 жизней, а твой герой — испуганная обезьянка!");
                startLive = 5;
                heroSkin = "\uD83D\uDE48"; // 🙈
                difficultGame = 1;
            } else if (difficultChoice.equalsIgnoreCase("про")) {
                System.out.println("Выбран режим ПРО! Ты — сильный маг с 3 жизнями.");
                startLive = 3;
                heroSkin = "\uD83E\uDDD8"; // 🧙
                difficultGame = 2;
            } else if (difficultChoice.equalsIgnoreCase("читер")) {
                System.out.println("Выбран режим ЧИТЕР! Жизней бесконечно, а твой герой — крутой робот!");
                startLive = 999;
                heroSkin = "\uD83E\uDD16"; // 🤖
                difficultGame = 3;
            }

            int startX = 1 + random.nextInt(sizeBoard);
            Person person = new Person(startX, sizeBoard, startLive, heroSkin);

            // ИНИЦИАЛИЗАЦИЯ ПОЛЯ
            String[][] board = new String[sizeBoard][sizeBoard];
            for (int r = 0; r < sizeBoard; r++) {
                for (int c = 0; c < sizeBoard; c++) {
                    board[r][c] = "  ";
                }
            }

            // ГЕНЕРАЦИЯ ЗАМКА И ГЕРОЯ
            int castleX = 1 + random.nextInt(sizeBoard);
            board[castleY - 1][castleX - 1] = castle;
            board[person.getY() - 1][person.getX() - 1] = person.getImage();

            // СОЗДАЕМ ЯЩИК-МАССИВ ОБЪЕКТОВ КЛАССА MONSTER [image_l21HAj.png]
            int countMonster = sizeBoard * sizeBoard - sizeBoard - 1;
            Monster[] monsters = new Monster[countMonster];

            for (int i = 0; i < countMonster; i++) {
                Monster m;
                do {
                    m = new Monster(sizeBoard); // Создаем объект монстра со случайным скином и позицией
                } while (!board[m.getY()][m.getX()].equals("  ") || (m.getY() == person.getY() - 1 && m.getX() == person.getX() - 1));

                monsters[i] = m; // Кладем объект в массив монстров
                board[m.getY()][m.getX()] = m.getImage(); // Отрисовываем картинку монстра на поле
            }

            System.out.println("\n--- ИГРА НАЧАЛАСЬ ---");
            outputBoard(board, difficultGame == 1 ? 5 : (difficultGame == 3 ? 999 : person.getLive()));

            // ИГРОВОЙ ЦИКЛ
            while (difficultGame == 3 || person.getLive() > 0) {
                System.out.println("Шаги: " + step);
                System.out.println("Введите координаты хода (X и Y через пробел):");
                int x = scanner.nextInt();
                int y = scanner.nextInt();

                if (x < 1 || x > sizeBoard || y < 1 || y > sizeBoard) {
                    System.out.println("Выход за границы поля!");
                    continue;
                }

                if (!person.isMoveCorrect(x, y)) {
                    System.out.println("Некорректный ход! Можно ходить только на 1 клетку.");
                    continue;
                }

                String targetCell = board[y - 1][x - 1];

                if (targetCell.equals("  ")) {
                    board[person.getY() - 1][person.getX() - 1] = "  ";
                    person.move(x, y);
                    board[person.getY() - 1][person.getX() - 1] = person.getImage();
                    step++;
                    System.out.println("Ход выполнен успешно!");
                } else if (targetCell.equals(castle)) {
                    System.out.println("КРАСАВА! ТЫ ДОШЕЛ ДО ЗАМКА");
                    break;
                } else {
                    // Если наступили не на воздух и не на замок — значит, столкнулись с иконкой монстра!
                    System.out.println("ВНИМАНИЕ! На твоем пути враг " + targetCell + "! Защищайся!");

                    if (taskMonster(difficultGame)) {
                        board[person.getY() - 1][person.getX() - 1] = "  ";
                        person.move(x, y);
                        board[person.getY() - 1][person.getX() - 1] = person.getImage();
                        step++;
                    } else {
                        if (difficultGame != 3) {
                            person.downLive();
                        }
                    }
                }

                outputBoard(board, difficultGame == 1 ? 5 : (difficultGame == 3 ? 999 : person.getLive()));
            }

            if (difficultGame != 3 && person.getLive() <= 0) {
                System.out.println("Закончились жизни. Вы проиграли!");
            }

        } else {
            System.out.println("Очень жаль! Введи duck и включи ВПН");
            scanner.nextLine();
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

    public static boolean taskMonster(int difficultGame) {
        int maxNumber = 100;
        if (difficultGame == 1) maxNumber = 10;
        else if (difficultGame == 3) maxNumber = 1000;

        int a = random.nextInt(maxNumber);
        int b = random.nextInt(maxNumber);
        int trueAnswer = a + b;

        System.out.println("Реши пример: " + a + " + " + b + " = ?");
        int ans = scanner.nextInt();
        return trueAnswer == ans;
    }

    public static void outputBoard(String[][] board, int live) {
        for (int r = 0; r < board.length; r++) {
            System.out.println(wall);
            for (int c = 0; c < board[r].length; c++) {
                System.out.print(leftBlock + board[r][c]);
            }
            System.out.println(rightBlock);
        }
        System.out.println(wall);
        System.out.println("Количество жизней:\t" + live + "\n");
    }
}
