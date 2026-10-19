import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Random rnd = new Random();
        Scanner sc = new Scanner(System.in);

        String castle = "\uD83C\uDFF0";
        String monster = "Мм";

        int castleY = 1;
        int sizeofplace = 5;
        int castleX = 1 + rnd.nextInt(sizeofplace);
        int step = 0;
        int monsters = 5;

        String[][] board = new String[sizeofplace][sizeofplace];

        for (int i = 0; i < sizeofplace; i++) {
            for (int j = 0; j < sizeofplace; j++) {
                board[i][j] = "  ";
            }
        }

        for (int i = 0; i < monsters; i++) {
            int mx = rnd.nextInt(sizeofplace);
            int my = rnd.nextInt(sizeofplace);
            board[my][mx] = monster;
        }

        board[castleY - 1][castleX - 1] = castle;

        Person person = new Person(sizeofplace);
        board[person.getY() - 1][person.getX() - 1] = person.getImage();

        System.out.println("Ты готов начать игру? (Напиши: ДА или НЕТ)");
        String playerans = sc.nextLine();

        int difficultGame = 1;

        switch (playerans) {
            case "ДА":
                System.out.println("Игра началась!");
                System.out.println("Выбери сложность игры (от 1 до 5):");
                difficultGame = sc.nextInt();
                System.out.println("Выбранная сложность:\t" + difficultGame);
                break;
            case "НЕТ":
                System.out.println("Печально, ну бб :(");
                break;
            default:
                System.out.println("Данные введены некорректно");
        }

        if (playerans.equals("ДА")) {

            while (person.getLive() > 0 && !(castleX == person.getX() && castleY == person.getY())) {

                step++;

                outputBoard(board, person.getLive());

                System.out.println("Ход номер: " + step);
                System.out.println("Введите куда будет ходить персонаж (ход возможен только по вертикали и горизонтали на одну клетку)");
                System.out.println("Координаты персонажа - (x: " + person.getX() + ", y: " + person.getY() + ")");

                int x = sc.nextInt();
                int y = sc.nextInt();

                if (person.isMoveCorrect(x, y)) {
                    String next = board[y - 1][x - 1];
                    if (next.equals("  ")) {
                        board[person.getY() - 1][person.getX() - 1] = "  ";
                        person.move(x, y);
                        board[person.getY() - 1][person.getX() - 1] = person.getImage();
                        System.out.println("Ход корректный; Новые координаты: " + person.getX() + ", " + person.getY() +
                                "\nХод номер: " + step);
                    } else if (next.equals(castle)) {
                        System.out.println("Вы прошли игру!");
                        break;
                    } else {
                        System.out.println("Решите задачу:");
                        if (taskMonster(difficultGame)) {
                            board[person.getY() - 1][person.getX() - 1] = "  ";
                            person.move(x, y);
                            board[person.getY() - 1][person.getX() - 1] = person.getImage();
                            step++;
                        } else {
                            person.downLive();
                        }
                    }
                } else {
                    System.out.println("Некорректный ход");
                    person.downLive();
                }
            }

            if (person.getLive() <= 0) {
                System.out.println("Игра окончена");
            }
        }

        sc.close();
    }

    static boolean taskMonster(int difficultGame) {
        if (difficultGame == 1) {
            Random random = new Random();
            int a = random.nextInt(100);
            int b = random.nextInt(100);
            int trueAnswer = a + b;
            System.out.println("Реши пример: " + a + " + " + b + " = ?");
            Scanner sc = new Scanner(System.in);
            int ans = sc.nextInt();
            if (trueAnswer == ans) {
                System.out.println("Верно! Ты победил монстра");
                return true;
            } else {
                System.out.println("Ты проиграл эту битву!");
                return false;
            }
        } else {
            Random random = new Random();
            int a = random.nextInt(300);
            int b = random.nextInt(300);
            int trueAnswer = a + b;
            System.out.println("Реши пример: " + a + " + " + b + " = ?");
            Scanner sc = new Scanner(System.in);
            int ans = sc.nextInt();
            if (trueAnswer == ans) {
                System.out.println("Верно! Ты победил монстра");
                return true;
            } else {
                System.out.println("Ты проиграл эту битву!");
                return false;
            }
        }
    }

    static void outputBoard(String[][] board, int live) {
        String leftBlock = "| ";
        String rightBlock = "|";
        String wall = "+ —— + —— + —— + —— + —— +";

        for (String[] row : board) {
            System.out.println(wall);
            for (String col : row) {
                System.out.print(leftBlock + col + " ");
            }
            System.out.println(rightBlock);
        }
        System.out.println(wall);

        System.out.println("Количество жизней:\t" + live + "\n");
    }
}