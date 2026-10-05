import java.awt.Desktop;
import java.net.URI;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //название переменных
        int step = 0, personX, personY, personLive = 3, sizeBoard = 5;
        String person = "\uD83E\uDDD8"; //U+1F9B8 U+200D U+2642 U+FE0F-юникод для гг
        String monster = "\uD83D\uDC79";//U+1F479 - юникод для монстра
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
        System.out.println("Привет! Ты готов начать играть в игру? (Напиши: ДА или НЕТ)");
        Scanner scanner = new Scanner(System.in);
        personX = 1;
        personY = sizeBoard;
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("да")) {
            System.out.println("Ваш ответ:\t" + answer);
            System.out.println("Как тебя зовут?");
            String name = scanner.nextLine();
            System.out.println("Тебя зовут: " + name);
            System.out.println("Сколько тебе лет?");
            String age = scanner.nextLine();
            System.out.println("ОГО,правда?! Тебе: " + age);
            System.out.println("Ну,что? Начинаем? Напиши: да или нет");
            String rejoin = scanner.nextLine();
            System.out.println("Правильно что согласился: " + rejoin);
            System.out.println("Вот твое игровое поле");
            System.out.println(zastavka);
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            if (x != personX && y != personY) {
                System.out.println("Некорректный ход");
            } else if (Math.abs(x - personX) == 1) {
                personX = x;
                step += 1;
            } else if (Math.abs(y - personY) == 1) {
                personY = y;
                step += 1;
            } else {
                System.out.println("Координаты не изменены");
            }

        } else {                    //типа пасхалка
            System.out.println("Очень жаль! Введи duck");
            String rickroll = scanner.nextLine();
            if (rickroll.equalsIgnoreCase("duck")) {
                System.out.println("ТЫ АКТИВИРОВАЛ СЕКРЕТ!");

                try {
                    Desktop.getDesktop().browse(
                            new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ")
                    );
                } catch (Exception e) {
                    System.out.println("Не удалось открыть ссылку.");
                }

            } else {
                System.out.println("Ты даже duck правильно написать не можешь");
            }
        }
    }
}