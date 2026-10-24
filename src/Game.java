public class Game {
    private int sizeBoard;
    private int difficultGame;
    private int step = 0;
    private int castleY = 1;
    private String castle = "🏰"; // 🏰
    private String empty = "  ";

    private String leftBlock = " | ";
    private String rightBlock = " |";
    private String wall = " + —— + —— + —— + —— + —— + ";

    private String[][] board;
    private Person person;
    private Monster[] monsters;
    private Chest chest;

    public Game(Person person, int sizeBoard, int difficultGame) {
        this.person = person;
        this.sizeBoard = sizeBoard;
        this.difficultGame = difficultGame;
    }

    public void play() {
        createBoard();
        createMonsters();
        createChest();

        System.out.println("\n--- ИГРА НАЧАЛАСЬ ---");
        System.out.println("Дойди до замка " + castle + ". Сундук " + chest.getImage() + " дает +1 жизнь.");
        outputBoard();

        // ИГРОВОЙ ЦИКЛ
        while (difficultGame == 3 || person.getLive() > 0) {
            System.out.println("Шаги: " + step);
            System.out.println("Куда идем? W - вверх, S - вниз, A - влево, D - вправо");
            String key = Main.scanner.next();

            int x = person.getX();
            int y = person.getY();

            // русские буквы тоже, если забыл раскладку поменять
            if (key.equalsIgnoreCase("w") || key.equalsIgnoreCase("ц")) {
                y--;
            } else if (key.equalsIgnoreCase("s") || key.equalsIgnoreCase("ы")) {
                y++;
            } else if (key.equalsIgnoreCase("a") || key.equalsIgnoreCase("ф")) {
                x--;
            } else if (key.equalsIgnoreCase("d") || key.equalsIgnoreCase("в")) {
                x++;
            } else {
                System.out.println("Не понял! Нажми W, A, S или D");
                continue;
            }

            if (x < 1 || x > sizeBoard || y < 1 || y > sizeBoard) {
                System.out.println("Выход за границы поля!");
                continue;
            }

            if (!person.isMoveCorrect(x, y)) {
                System.out.println("Некорректный ход! Можно ходить только на 1 клетку.");
                continue;
            }

            String targetCell = board[y - 1][x - 1];

            if (targetCell.equals(empty)) {
                moveHero(x, y);
                System.out.println("Ход выполнен успешно!");
            } else if (targetCell.equals(castle)) {
                step++;
                System.out.println("КРАСАВА! ТЫ ДОШЕЛ ДО ЗАМКА за " + step + " шагов!");
                break;
            } else if (targetCell.equals(chest.getImage())) {
                person.upLive();
                System.out.println("Ты нашел сундук! +1 жизнь");
                moveHero(x, y);
            } else {
                Monster monster = findMonster(x, y);
                System.out.println("ВНИМАНИЕ! На твоем пути враг " + targetCell + "! Защищайся!");

                if (monster.taskMonster(difficultGame)) {
                    System.out.println("Правильно! Враг побежден!");
                    moveHero(x, y);
                } else {
                    System.out.println("Неправильно! Враг тебя ударил.");
                    if (difficultGame != 3) {
                        person.downLive();
                    }
                }
            }

            outputBoard();
        }

        if (difficultGame != 3 && person.getLive() <= 0) {
            System.out.println("Закончились жизни. Вы проиграли!");
        }
    }

    private void createBoard() {
        board = new String[sizeBoard][sizeBoard];
        for (int r = 0; r < sizeBoard; r++) {
            for (int c = 0; c < sizeBoard; c++) {
                board[r][c] = empty;
            }
        }

        int castleX = 1 + Main.random.nextInt(sizeBoard);
        board[castleY - 1][castleX - 1] = castle;
        board[person.getY() - 1][person.getX() - 1] = person.getImage();
    }

    private void createMonsters() {
        // нуб 3-5 монстров, про 6-9, читер 10-12
        int countMonster;
        if (difficultGame == 1) {
            countMonster = 3 + Main.random.nextInt(3);
        } else if (difficultGame == 2) {
            countMonster = 6 + Main.random.nextInt(4);
        } else {
            countMonster = 10 + Main.random.nextInt(3);
        }

        monsters = new Monster[countMonster];

        for (int i = 0; i < countMonster; i++) {
            Monster m;
            do {
                int type = Main.random.nextInt(3);
                if (type == 0) {
                    m = new Monster(sizeBoard);
                } else if (type == 1) {
                    m = new BigMonster(sizeBoard);
                } else {
                    m = new GuessMonster(sizeBoard);
                }
            } while (!board[m.getY() - 1][m.getX() - 1].equals(empty));

            monsters[i] = m;
            board[m.getY() - 1][m.getX() - 1] = m.getImage();
        }
    }

    private void createChest() {
        do {
            chest = new Chest(sizeBoard);
        } while (!board[chest.getY() - 1][chest.getX() - 1].equals(empty));

        board[chest.getY() - 1][chest.getX() - 1] = chest.getImage();
    }

    private Monster findMonster(int x, int y) {
        for (int i = 0; i < monsters.length; i++) {
            if (monsters[i].getX() == x && monsters[i].getY() == y) {
                return monsters[i];
            }
        }
        return null;
    }

    private void moveHero(int x, int y) {
        board[person.getY() - 1][person.getX() - 1] = empty;
        person.move(x, y);
        board[person.getY() - 1][person.getX() - 1] = person.getImage();
        step++;
    }

    private void outputBoard() {
        for (int r = 0; r < board.length; r++) {
            System.out.println(wall);
            for (int c = 0; c < board[r].length; c++) {
                System.out.print(leftBlock + board[r][c]);
            }
            System.out.println(rightBlock);
        }
        System.out.println(wall);
        System.out.println("Количество жизней:\t" + person.getLive() + "\n");
    }
}
