import java.util.Random;

public class Monster {
    // Скрытые поля каждого конкретного монстра (Инкапсуляция)
    private int x;
    private int y;
    private String image;

    // Массив доступных скинов для монстров, скрытый внутри класса
    private static final String[] SKINS = {
            "\uD83D\uDC7E", //  (Пиксельный пришелец)
            "\uD83D\uDC7B", //  (Привидение)
            "\uD83D\uDC7D", //  (Инопланетянин)
            "\uD83D\uDC80", //  (Череп)
            "\uD83D\uDC79" ,// (Монстр Они)
    };

    // Конструктор: при создании монстр сам выбирает случайные координаты и свой скин
    public Monster(int sizeBoard) {
        Random random = new Random();
        this.x = random.nextInt(sizeBoard); // Индексы от 0 до sizeBoard - 1 для массива
        this.y = random.nextInt(sizeBoard);

        // Случайный скин из набора
        this.image = SKINS[random.nextInt(SKINS.length)];
    }

    // Геттеры для безопасного чтения данных из класса Main [image_7mJlMZ.png]
    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public String getImage() { return this.image; }
}
