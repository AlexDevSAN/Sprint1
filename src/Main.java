import java.awt.Desktop;
import java.util.Random;
import java.net.URI;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ////////////ПЕРЕМЕННЫЕ
        int step = 0, personX = 1, personY = 5, personLive = 3, sizeBoard = 5, castleY = 1;
        String leftBlock = " | ";
        String rightBlock = " |";
        String wall = " + —— + —— + —— + —— + —— + ";
        String person = "\uD83E\uDDD8";
        String castle = "\uD83C\uDFF0";
        String monster = "\uD83D\uDC79";
        String zastavka = "+ —— + —— + —— + —— + —— +\n"
                + "|    |    |    |    |    |\n"
                + "+ —— + —— + —— + —— + —— +\n"
                + "|    |    |    |    |    |\n"
                + "+ —— + —— + —— + —— + —— +\n"
                + "|    |    | " + monster + " |    |    |\n"
                + "+ —— + —— + —— + —— + —— +\n"
                + "|    |    |    |    |    |\n"
                + "+ —— + —— + —— + —— + —— +\n"
                + "| " + person + " |    |    |    |    |\n"
                + "+ —— + —— + —— + —— + —— +";

        Random random = new Random();
        int castleX = 1 + random.nextInt(sizeBoard);

        Scanner scanner = new Scanner(System.in);

        //вопросы опросы
        System.out.println("Привет! Ты готов начать играть в игру? (Напиши: ДА или НЕТ)");
        String answer = scanner.nextLine();

        if (answer.equalsIgnoreCase("да")) {
            System.out.println("Ваш ответ:\t" + answer);
            System.out.println("Как тебя зовут?");
            String name = scanner.nextLine();
            System.out.println("Тебя зовут: " + name);
            System.out.println("Сколько тебе лет?");
            String age = scanner.nextLine();
            System.out.println("ОГО, правда?! Тебе: " + age);
            System.out.println("Ну, что? Начинаем? Напиши: да или нет");
            String rejoin = scanner.nextLine();
            System.out.println("Правильно что согласился: " + rejoin);
            System.out.println("Вот твое игровое поле");
            System.out.println(zastavka);

            // ИНИЦИАЛИЗАЦИЯ ДВУМЕРНОГО МАССИВА
            String[][] board = new String[sizeBoard][sizeBoard];
            for (int r = 0; r < sizeBoard; r++) {
                for (int c = 0; c < sizeBoard; c++) {
                    board[r][c] = "  ";
                }
            }

            // Заполняем стартовые позиции объектов в двумерной матрице
            board[castleY - 1][castleX - 1] = castle;
            board[personY - 1][personX - 1] = person;

            // Генерируем одного монстра в случайную пустую клетку
            int monsterX = 1 + random.nextInt(sizeBoard);
            int monsterY = 1 + random.nextInt(sizeBoard);
            while (board[monsterY - 1][monsterX - 1].equals(castle) || board[monsterY - 1][monsterX - 1].equals(person)) {
                monsterX = 1 + random.nextInt(sizeBoard);
                monsterY = 1 + random.nextInt(sizeBoard);
            }
            board[monsterY - 1][monsterX - 1] = monster;

            // ИГРОВЫЕ ЦИКЛЫ
            while (personLive > 0) {
                System.out.println("Жизни: " + personLive + " | Введите координаты хода (X и Y через пробел):");
                int x = scanner.nextInt();
                int y = scanner.nextInt();

                // Защита от выхода за границы массива
                if (x < 1 || x > sizeBoard || y < 1 || y > sizeBoard) {
                    System.out.println("Некорректный ход! Выход за границы игрового поля.");
                    continue;
                }

                // Проверка корректности хода по горизонтали или вертикали на 1 шаг
                if (x != personX && y != personY) {
                    System.out.println("Некорректный ход");
                    continue;
                } else if (Math.abs(x - personX) > 1 || Math.abs(y - personY) > 1) {
                    System.out.println("Некорректный ход");
                    continue;
                }

                // ВСТАВЛЕННЫЙ КУСОК КОДА С САЙТА (С адаптированной логикой персонажа)
                if (board[y - 1][x - 1].equals("  ")) {
                    board[personY - 1][personX - 1] = "  "; // Очищаем старое место
                    personX = x;
                    personY = y;
                    board[personY - 1][personX - 1] = person; // Пишем персонажа на новое место
                    step++;
                    System.out.println("Ход корректный; Новые координаты: " + personX + ", " + personY +
                            "\nХод номер: " + step);
                } else if (board[y - 1][x - 1].equals(castle)) {
                    System.out.println("Вы прошли игру!");
                    break;
                } else {
                    int a = random.nextInt(100);
                    int b = random.nextInt(100);
                    int trueAnswer = a + b;
                    System.out.println("Реши пример: " + a + " + " + b + " = ?");
                    int ans = scanner.nextInt();
                    if (trueAnswer == ans) {
                        System.out.println("Верно! Ты победил монстра");
                        board[personY - 1][personX - 1] = "  "; // Очищаем старое место
                        personX = x;
                        personY = y;
                        board[personY - 1][personX - 1] = person; // Занимаем клетку монстра
                        step++;
                    } else {
                        System.out.println("Ты проиграл эту битву!");
                        personLive--;
                    }
                }

                // ОТРИСОВКА ДВУМЕРНОГО МАССИВА ПОЛЯ
                for (int boardY = 1; boardY <= sizeBoard; boardY++) {
                    System.out.println(wall);
                    for (int boardX = 1; boardX <= sizeBoard; boardX++) {
                        System.out.print(leftBlock);
                        System.out.print(board[boardY - 1][boardX - 1]);
                    }
                    System.out.println(rightBlock);
                }
                System.out.println(wall);
            }

            if (personLive <= 0) {
                System.out.println("Закончились жизни. Итог: ТЫ НЕУДАЧНИК, но не переживай!");
            }

        } else { // Пасхалка
            System.out.println("Очень жаль! Введи duck и включи ВПН");
            String rickroll = scanner.nextLine();
            if (rickroll.equalsIgnoreCase("duck")) {
                System.out.println("ТЫ АКТИВИРОВАЛ СЕКРЕТ!");
                try {
                    Desktop.getDesktop().browse(new URI("https://youtube.com"));
                } catch (Exception e) {
                    System.out.println("Не удалось открыть ссылку.");
                }
            } else {
                System.out.println("Ты даже duck правильно написать не можешь");
            }
        }
    }
}
